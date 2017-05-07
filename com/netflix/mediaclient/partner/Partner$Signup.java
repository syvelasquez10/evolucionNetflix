// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

public interface Partner$Signup
{
    void getExternalUserData(final String p0, final String p1, final int p2, final ResponseListener p3);
    
    String getService();
    
    void requestExternalUserConfirmation(final String p0, final String p1, final int p2, final ResponseListener p3);
}
