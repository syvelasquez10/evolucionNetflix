// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;

public interface Authenticator
{
    String getAuthToken() throws AuthFailureError;
    
    void invalidateAuthToken(final String p0);
}
