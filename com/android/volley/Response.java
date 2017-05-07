// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

public class Response<T>
{
    public final Cache$Entry cacheEntry;
    public final VolleyError error;
    public boolean intermediate;
    public final T result;
    
    private Response(final VolleyError error) {
        this.intermediate = false;
        this.result = null;
        this.cacheEntry = null;
        this.error = error;
    }
    
    private Response(final T result, final Cache$Entry cacheEntry) {
        this.intermediate = false;
        this.result = result;
        this.cacheEntry = cacheEntry;
        this.error = null;
    }
    
    public static <T> Response<T> error(final VolleyError volleyError) {
        return new Response<T>(volleyError);
    }
    
    public static <T> Response<T> success(final T t, final Cache$Entry cache$Entry) {
        return new Response<T>(t, cache$Entry);
    }
    
    public boolean isSuccess() {
        return this.error == null;
    }
}
