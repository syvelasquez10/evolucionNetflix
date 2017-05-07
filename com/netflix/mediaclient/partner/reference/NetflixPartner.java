// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.reference;

import com.netflix.mediaclient.partner.Partner$SSO;
import com.netflix.mediaclient.partner.Partner$Signup;
import android.content.ComponentName;
import com.netflix.mediaclient.partner.PartnerCommunicationHandler;
import com.netflix.mediaclient.partner.Partner;

public class NetflixPartner implements Partner
{
    private PartnerCommunicationHandler comHandler;
    private ComponentName componentName;
    private Partner$Signup signup;
    private Partner$SSO sso;
    
    public NetflixPartner(final String s, final PartnerCommunicationHandler comHandler) {
        this.sso = new NetflixSSO(this, s);
        this.signup = new NetflixSignup(this, s);
        this.comHandler = comHandler;
    }
    
    @Override
    public ComponentName getComponentName() {
        return this.componentName;
    }
    
    @Override
    public PartnerCommunicationHandler getPartnerCommunicationHandler() {
        return this.comHandler;
    }
    
    @Override
    public Partner$SSO getSSO() {
        return this.sso;
    }
    
    @Override
    public Partner$Signup getSignup() {
        return this.signup;
    }
    
    @Override
    public void release() {
        this.sso = null;
        this.signup = null;
        this.comHandler = null;
    }
    
    @Override
    public void setComponentName(final ComponentName componentName) {
        this.componentName = componentName;
    }
}
