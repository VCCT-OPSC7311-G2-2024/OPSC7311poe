package com.example.opsc7312poe;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\u0012\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u0019H\u0014J(\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020$H\u0002J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020$H\u0002J0\u0010-\u001a\u00020.2\u0006\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020$2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010/\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/example/opsc7312poe/Register;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "confirmPasswordEditText", "Lcom/google/android/material/textfield/TextInputEditText;", "emailEditText", "googleSignInButton", "Landroid/widget/Button;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "googleSignInLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "loginTextView", "Landroid/widget/TextView;", "mAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "nameEditText", "passwordEditText", "progressBar", "Landroid/widget/ProgressBar;", "registerButton", "surnameEditText", "checkAndSaveUserData", "", "firebaseAuthWithGoogle", "account", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "navigateToMainActivity", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "registerUser", "email", "", "password", "name", "surname", "saveUserData", "user", "Lcom/google/firebase/auth/FirebaseUser;", "showToast", "message", "validateInput", "", "confirmPassword", "app_debug"})
public final class Register extends androidx.appcompat.app.AppCompatActivity {
    private com.google.android.material.textfield.TextInputEditText nameEditText;
    private com.google.android.material.textfield.TextInputEditText surnameEditText;
    private com.google.android.material.textfield.TextInputEditText emailEditText;
    private com.google.android.material.textfield.TextInputEditText passwordEditText;
    private com.google.android.material.textfield.TextInputEditText confirmPasswordEditText;
    private android.widget.Button registerButton;
    private com.google.firebase.auth.FirebaseAuth mAuth;
    private com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient;
    private android.widget.ProgressBar progressBar;
    private android.widget.TextView loginTextView;
    private android.widget.Button googleSignInButton;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> googleSignInLauncher = null;
    
    public Register() {
        super();
    }
    
    @java.lang.Override
    protected void onStart() {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void firebaseAuthWithGoogle(com.google.android.gms.auth.api.signin.GoogleSignInAccount account) {
    }
    
    private final void checkAndSaveUserData() {
    }
    
    private final void saveUserData(com.google.firebase.auth.FirebaseUser user) {
    }
    
    private final boolean validateInput(java.lang.String name, java.lang.String surname, java.lang.String email, java.lang.String password, java.lang.String confirmPassword) {
        return false;
    }
    
    private final void registerUser(java.lang.String email, java.lang.String password, java.lang.String name, java.lang.String surname) {
    }
    
    private final void navigateToMainActivity() {
    }
    
    private final void showToast(java.lang.String message) {
    }
}