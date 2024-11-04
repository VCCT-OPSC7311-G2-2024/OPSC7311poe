package com.example.opsc7312poe;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003/01B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H\u0002J,\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001c\u0010\u001d\u001a\u00020\u00162\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00120\u001fH\u0002J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001a0\u00142\u0006\u0010!\u001a\u00020\u0012H\u0002J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0014H\u0002J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0014H\u0002J\b\u0010$\u001a\u00020\u0016H\u0002J\u001e\u0010%\u001a\u00020\u00162\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0016\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00142\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020\u0016H\u0002J\u0012\u0010+\u001a\u00020\u00162\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\b\u0010.\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/example/opsc7312poe/MoodTrack;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "loadingIndicator", "Landroid/widget/ProgressBar;", "moodChart", "Lcom/github/mikephil/charting/charts/BarChart;", "pieChart", "Lcom/github/mikephil/charting/charts/PieChart;", "timeFrameSpinner", "Landroid/widget/Spinner;", "user", "Lcom/google/firebase/auth/FirebaseUser;", "userMoodsRef", "Lcom/google/firebase/database/DatabaseReference;", "calculateAverageMood", "", "moodValues", "", "displayMoodGraph", "", "moodData", "Lcom/github/mikephil/charting/data/BarEntry;", "lastDays", "", "showWeekly", "", "displayMoodPieChart", "moodCounts", "", "getDaysFromNow", "days", "getLast7Days", "getLast7WeeksDates", "getMoodCounts", "getMoodsForTimeFrame", "dates", "getWeekDates", "calendar", "Ljava/util/Calendar;", "getWeeklyMoodAverages", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupNavigation", "DayAxisValueFormatter", "Mood", "WeeklyAxisValueFormatter", "app_debug"})
public final class MoodTrack extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.Nullable
    private com.google.firebase.auth.FirebaseUser user;
    private com.google.firebase.auth.FirebaseAuth auth;
    private com.github.mikephil.charting.charts.BarChart moodChart;
    private android.widget.Spinner timeFrameSpinner;
    private com.google.firebase.database.DatabaseReference userMoodsRef;
    private android.widget.ProgressBar loadingIndicator;
    private com.github.mikephil.charting.charts.PieChart pieChart;
    
    public MoodTrack() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupNavigation() {
    }
    
    private final void getWeeklyMoodAverages() {
    }
    
    private final java.util.List<java.lang.String> getLast7WeeksDates() {
        return null;
    }
    
    private final java.util.List<java.lang.String> getWeekDates(java.util.Calendar calendar) {
        return null;
    }
    
    private final int calculateAverageMood(java.util.List<java.lang.Integer> moodValues) {
        return 0;
    }
    
    private final java.util.List<java.lang.String> getLast7Days() {
        return null;
    }
    
    private final java.util.List<java.lang.String> getDaysFromNow(int days) {
        return null;
    }
    
    private final void displayMoodGraph(java.util.List<? extends com.github.mikephil.charting.data.BarEntry> moodData, java.util.List<java.lang.String> lastDays, boolean showWeekly) {
    }
    
    private final void getMoodsForTimeFrame(java.util.List<java.lang.String> dates, boolean showWeekly) {
    }
    
    private final void getMoodCounts() {
    }
    
    private final void displayMoodPieChart(java.util.Map<java.lang.String, java.lang.Integer> moodCounts) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/opsc7312poe/MoodTrack$DayAxisValueFormatter;", "Lcom/github/mikephil/charting/formatter/ValueFormatter;", "days", "", "", "(Ljava/util/List;)V", "getAxisLabel", "value", "", "axis", "Lcom/github/mikephil/charting/components/AxisBase;", "app_debug"})
    static final class DayAxisValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        @org.jetbrains.annotations.NotNull
        private final java.util.List<java.lang.String> days = null;
        
        public DayAxisValueFormatter(@org.jetbrains.annotations.NotNull
        java.util.List<java.lang.String> days) {
            super();
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String getAxisLabel(float value, @org.jetbrains.annotations.Nullable
        com.github.mikephil.charting.components.AxisBase axis) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0082\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\r"}, d2 = {"Lcom/example/opsc7312poe/MoodTrack$Mood;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "AWFUL", "BAD", "MEH", "GOOD", "RAD", "Companion", "app_debug"})
    static enum Mood {
        /*public static final*/ AWFUL /* = new AWFUL(0) */,
        /*public static final*/ BAD /* = new BAD(0) */,
        /*public static final*/ MEH /* = new MEH(0) */,
        /*public static final*/ GOOD /* = new GOOD(0) */,
        /*public static final*/ RAD /* = new RAD(0) */;
        private final int value = 0;
        @org.jetbrains.annotations.NotNull
        public static final com.example.opsc7312poe.MoodTrack.Mood.Companion Companion = null;
        
        Mood(int value) {
        }
        
        public final int getValue() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<com.example.opsc7312poe.MoodTrack.Mood> getEntries() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/opsc7312poe/MoodTrack$Mood$Companion;", "", "()V", "fromString", "Lcom/example/opsc7312poe/MoodTrack$Mood;", "mood", "", "app_debug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.example.opsc7312poe.MoodTrack.Mood fromString(@org.jetbrains.annotations.NotNull
            java.lang.String mood) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/opsc7312poe/MoodTrack$WeeklyAxisValueFormatter;", "Lcom/github/mikephil/charting/formatter/ValueFormatter;", "weeks", "", "", "(Ljava/util/List;)V", "getAxisLabel", "value", "", "axis", "Lcom/github/mikephil/charting/components/AxisBase;", "app_debug"})
    static final class WeeklyAxisValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        @org.jetbrains.annotations.NotNull
        private final java.util.List<java.lang.String> weeks = null;
        
        public WeeklyAxisValueFormatter(@org.jetbrains.annotations.NotNull
        java.util.List<java.lang.String> weeks) {
            super();
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String getAxisLabel(float value, @org.jetbrains.annotations.Nullable
        com.github.mikephil.charting.components.AxisBase axis) {
            return null;
        }
    }
}