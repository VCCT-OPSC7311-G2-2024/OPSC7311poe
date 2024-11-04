package com.example.opsc7312poe;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0010"}, d2 = {"Lcom/example/opsc7312poe/RetrofitClient;", "", "()V", "BASE_URL", "", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "Lkotlin/Lazy;", "zenQuotesService", "Lcom/example/opsc7312poe/QuoteApi;", "getZenQuotesService", "()Lcom/example/opsc7312poe/QuoteApi;", "zenQuotesService$delegate", "app_debug"})
public final class RetrofitClient {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String BASE_URL = "https://zenquotes.io";
    @org.jetbrains.annotations.NotNull
    private static final kotlin.Lazy retrofit$delegate = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlin.Lazy zenQuotesService$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.opsc7312poe.RetrofitClient INSTANCE = null;
    
    private RetrofitClient() {
        super();
    }
    
    private final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.opsc7312poe.QuoteApi getZenQuotesService() {
        return null;
    }
}