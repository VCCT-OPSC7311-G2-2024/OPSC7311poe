package com.example.opsc7312poe

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class Register : AppCompatActivity() {

    private lateinit var nameEditText: TextInputEditText
    private lateinit var surnameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var confirmPasswordEditText: TextInputEditText
    private lateinit var registerButton: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var progressBar: ProgressBar
    private lateinit var loginTextView: TextView
    private lateinit var googleSignInButton: Button

    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                firebaseAuthWithGoogle(account)
            }
        } catch (e: ApiException) {
            showToast("Google Sign-In failed: ${e.message}")
            Log.e("Register", "Google Sign-In failed", e)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            navigateToMainActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.editTextName)
        surnameEditText = findViewById(R.id.editTextSurname)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword)
        progressBar = findViewById(R.id.progressBar)
        mAuth = FirebaseAuth.getInstance()
        registerButton = findViewById(R.id.buttonRegister)
        loginTextView = findViewById(R.id.textViewLogin)
        googleSignInButton = findViewById(R.id.buttonGoogleSignIn)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth.signOut()

        googleSignInButton.setOnClickListener {
            // Sign out to clear any cached account
            googleSignInClient.signOut().addOnCompleteListener {
                // After signing out, launch the sign-in intent
                val signInIntent = googleSignInClient.signInIntent
                googleSignInLauncher.launch(signInIntent)
            }
        }


        loginTextView.setOnClickListener {
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val surname = surnameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (validateInput(name, surname, email, password, confirmPassword)) {
                registerUser(email, password, name, surname)
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showToast("Google Sign-In successful")
                    checkAndSaveUserData()
                } else {
                    showToast("Google Sign-In failed: ${task.exception?.message}")
                }
            }
    }

    private fun checkAndSaveUserData() {
        val user = mAuth.currentUser
        if (user != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    if (!document.exists()) {
                        saveUserData(user)
                    } else {
                        navigateToMainActivity()
                    }
                }
                .addOnFailureListener { e ->
                    showToast("Failed to check user data: ${e.message}")
                }
        }
    }

    private fun saveUserData(user: FirebaseUser) {
        val db = FirebaseFirestore.getInstance()
        val userData = hashMapOf(
            "name" to user.displayName,
            "email" to user.email,
            "uid" to user.uid
        )

        db.collection("users").document(user.uid).set(userData)
            .addOnSuccessListener {
                showToast("User data saved successfully")
                navigateToMainActivity()
            }
            .addOnFailureListener { e ->
                showToast("Error saving user data: ${e.message}")
            }
    }

    private fun validateInput(name: String, surname: String, email: String, password: String, confirmPassword: String): Boolean {
        if (TextUtils.isEmpty(name)) {
            showToast("Enter your name")
            return false
        }

        if (TextUtils.isEmpty(surname)) {
            showToast("Enter your surname")
            return false
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Enter a valid email address")
            return false
        }

        if (TextUtils.isEmpty(password) || password.length < 6) {
            showToast("Password should be at least 6 characters")
            return false
        }

        if (password != confirmPassword) {
            showToast("Passwords do not match")
            return false
        }

        return true
    }

    private fun registerUser(email: String, password: String, name: String, surname: String) {
        progressBar.visibility = View.VISIBLE

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    if (user != null) {
                        val db = FirebaseFirestore.getInstance()
                        val userData = hashMapOf(
                            "name" to name,
                            "surname" to surname,
                            "email" to email,
                            "uid" to user.uid
                        )

                        db.collection("users").document(user.uid).set(userData)
                            .addOnSuccessListener {
                                showToast("Account Created Successfully")
                                navigateToMainActivity()
                            }
                            .addOnFailureListener { e ->
                                showToast("Error saving user data: ${e.message}")
                            }
                    }
                } else {
                    showToast("Registration failed: ${task.exception?.localizedMessage}")
                }
            }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
