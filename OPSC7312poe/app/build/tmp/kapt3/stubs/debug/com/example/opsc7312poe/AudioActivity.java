package com.example.opsc7312poe;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/opsc7312poe/AudioActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "audioAdapter", "Lcom/example/opsc7312poe/AudioAdapter;", "audioList", "", "Lcom/example/opsc7312poe/AudioItem;", "audioRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mediaPlayer", "Landroid/media/MediaPlayer;", "searchBar", "Landroid/widget/EditText;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "playAudio", "audioUrl", "", "app_debug"})
public final class AudioActivity extends androidx.appcompat.app.AppCompatActivity {
    private androidx.recyclerview.widget.RecyclerView audioRecyclerView;
    private com.example.opsc7312poe.AudioAdapter audioAdapter;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.opsc7312poe.AudioItem> audioList;
    private android.widget.EditText searchBar;
    @org.jetbrains.annotations.Nullable
    private android.media.MediaPlayer mediaPlayer;
    
    public AudioActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public final void playAudio(@org.jetbrains.annotations.NotNull
    java.lang.String audioUrl) {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
}