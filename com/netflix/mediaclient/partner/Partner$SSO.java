// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

public interface Partner$SSO
{
    void getExternalUserToken(final String p0, final int p1, final ResponseListener p2);
    
    String getService();
    
    void requestExternalUserAuth(final String p0, final int p1, final ResponseListener p2);
}
