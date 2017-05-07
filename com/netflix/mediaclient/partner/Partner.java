// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import android.content.ComponentName;

public interface Partner
{
    ComponentName getComponentName();
    
    PartnerCommunicationHandler getPartnerCommunicationHandler();
    
    SSO getSSO();
    
    Signup getSignup();
    
    void release();
    
    void setComponentName(final ComponentName p0);
    
    public interface SSO
    {
        void getExternalUserToken(final String p0, final int p1, final ResponseListener p2);
        
        String getService();
        
        void requestExternalUserAuth(final String p0, final int p1, final ResponseListener p2);
    }
    
    public interface Signup
    {
        void getExternalUserData(final String p0, final String p1, final int p2, final ResponseListener p3);
        
        String getService();
        
        void requestExternalUserConfirmation(final String p0, final String p1, final int p2, final ResponseListener p3);
    }
}
