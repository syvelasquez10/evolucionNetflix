// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.mdxcontroller;

import com.netflix.mediaclient.javabridge.ui.Mdx;

public interface MdxController extends Mdx
{
    DiscoveryController getDiscovery();
    
    PairingController getPairing();
    
    SessionController getSession();
    
    void pingNccp();
    
    void removePropertyUpdateListener();
    
    void setPropertyUpdateListener(final MdxController$PropertyUpdateListener p0);
}
