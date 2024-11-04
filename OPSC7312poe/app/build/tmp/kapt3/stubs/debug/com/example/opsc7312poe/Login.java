package com.example.opsc7312poe;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\"\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010\u0011H\u0014J\u0012\u0010%\u001a\u00020\u001a2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0014J\b\u0010(\u001a\u00020\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/example/opsc7312poe/Login;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "biometricLoginButton", "Landroid/widget/Button;", "biometricPrompt", "Landroidx/biometric/BiometricPrompt;", "buttonLogin", "editTextEmail", "Lcom/google/android/material/textfield/TextInputEditText;", "editTextPassword", "executor", "Ljava/util/concurrent/Executor;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "googleSignInLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "googleSignInTextView", "Landroid/widget/TextView;", "mAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "progressBar", "Landroid/widget/ProgressBar;", "textView", "firebaseAuthWithGoogle", "", "account", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "goToMainActivity", "isBiometricSupported", "", "onActivityResult", "requestCode", "", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "Companion", "app_debug"})
public final class Login extends androidx.appcompat.app.AppCompatActivity {
    private com.google.android.material.textfield.TextInputEditText editTextEmail;
    private com.google.android.material.textfield.TextInputEditText editTextPassword;
    private android.widget.Button buttonLogin;
    private android.widget.TextView googleSignInTextView;
    private android.widget.Button biometricLoginButton;
    private com.google.firebase.auth.FirebaseAuth mAuth;
    private com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient;
    private android.widget.ProgressBar progressBar;
    private android.widget.TextView textView;
    private java.util.concurrent.Executor executor;
    private androidx.biometric.BiometricPrompt biometricPrompt;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> googleSignInLauncher;
    private static final int RC_SIGN_IN = 9001;
    @org.jetbrains.annotations.NotNull
    public static final com.example.opsc7312poe.Login.Companion Companion = null;
    
    public Login() {
        super();
    }
    
    @java.lang.Override
    public void onStart() {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final boolean isBiometricSupported() {
        return false;
    }
    
    private final void goToMainActivity() {
    }
    
    @java.lang.Override
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    private final void firebaseAuthWithGoogle(com.google.android.gms.auth.api.signin.GoogleSignInAccount account) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/opsc7312poe/Login$Companion;", "", "()V", "RC_SIGN_IN", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}