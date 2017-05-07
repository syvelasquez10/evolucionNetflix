// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

public interface Authenticator
{
    String getAuthToken();
    
    void invalidateAuthToken(final String p0);
}
