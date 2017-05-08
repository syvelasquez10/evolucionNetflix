// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.VolleyError;

public interface ProgressiveRequestListener
{
    void onCancelled();
    
    void onError(final VolleyError p0);
    
    void onNext(final byte[] p0, final int p1);
}
