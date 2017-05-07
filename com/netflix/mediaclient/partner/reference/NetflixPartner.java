// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.reference;

import android.content.ComponentName;
import com.netflix.mediaclient.partner.PartnerCommunicationHandler;
import com.netflix.mediaclient.partner.Partner;

public class NetflixPartner implements Partner
{
    private PartnerCommunicationHandler comHandler;
    private ComponentName componentName;
    private Signup signup;
    private SSO sso;
    
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
    public SSO getSSO() {
        return this.sso;
    }
    
    @Override
    public Signup getSignup() {
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
