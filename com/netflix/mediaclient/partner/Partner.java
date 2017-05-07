// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import android.content.ComponentName;

public interface Partner
{
    ComponentName getComponentName();
    
    PartnerCommunicationHandler getPartnerCommunicationHandler();
    
    Partner$SSO getSSO();
    
    Partner$Signup getSignup();
    
    void release();
    
    void setComponentName(final ComponentName p0);
}
