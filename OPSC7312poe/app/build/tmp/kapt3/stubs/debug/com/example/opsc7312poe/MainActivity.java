package com.example.opsc7312poe;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010\u001c\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0017H\u0014J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\"\u001a\u00020\u0017H\u0002J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u0011H\u0002J\b\u0010%\u001a\u00020\u0017H\u0002J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\'H\u0002J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/example/opsc7312poe/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "moodDatabase", "Lcom/example/opsc7312poe/MoodDatabase;", "moodRepository", "Lcom/example/opsc7312poe/MoodRepository;", "networkChangeReceiver", "Landroid/content/BroadcastReceiver;", "quoteApi", "Lcom/example/opsc7312poe/QuoteApi;", "kotlin.jvm.PlatformType", "retrofit", "Lretrofit2/Retrofit;", "selectedMood", "", "user", "Lcom/google/firebase/auth/FirebaseUser;", "userMoodsRef", "Lcom/google/firebase/database/DatabaseReference;", "createNotificationChannel", "", "fetchMoodQuote", "mood", "isNetworkAvailable", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMoodSelected", "saveMood", "scheduleMoodWorker", "sendMoodQuoteNotification", "quote", "syncAllMoodsToFirebase", "syncMoodToFirebase", "Lcom/example/opsc7312poe/Mood;", "updateMoodStreak", "date", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.google.firebase.auth.FirebaseAuth auth;
    private com.google.firebase.database.DatabaseReference userMoodsRef;
    @org.jetbrains.annotations.Nullable
    private com.google.firebase.auth.FirebaseUser user;
    @org.jetbrains.annotations.Nullable
    private java.lang.String selectedMood;
    private com.example.opsc7312poe.MoodDatabase moodDatabase;
    private com.example.opsc7312poe.MoodRepository moodRepository;
    private final retrofit2.Retrofit retrofit = null;
    private final com.example.opsc7312poe.QuoteApi quoteApi = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.BroadcastReceiver networkChangeReceiver = null;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void onMoodSelected(java.lang.String mood) {
    }
    
    private final void saveMood(java.lang.String mood) {
    }
    
    private final void fetchMoodQuote(java.lang.String mood) {
    }
    
    private final void sendMoodQuoteNotification(java.lang.String quote) {
    }
    
    private final void syncMoodToFirebase(com.example.opsc7312poe.Mood mood) {
    }
    
    private final boolean isNetworkAvailable() {
        return false;
    }
    
    private final void syncAllMoodsToFirebase() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    private final void createNotificationChannel() {
    }
    
    private final void scheduleMoodWorker() {
    }
    
    private final void updateMoodStreak(java.lang.String date) {
    }
}