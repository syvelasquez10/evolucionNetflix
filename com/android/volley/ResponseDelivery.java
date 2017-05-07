// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

public interface ResponseDelivery
{
    void postError(final Request<?> p0, final VolleyError p1);
    
    void postResponse(final Request<?> p0, final Response<?> p1);
    
    void postResponse(final Request<?> p0, final Response<?> p1, final Runnable p2);
}
