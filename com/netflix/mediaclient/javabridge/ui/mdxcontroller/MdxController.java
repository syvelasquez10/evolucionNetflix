// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.mdxcontroller;

import java.util.ArrayList;
import com.netflix.mediaclient.javabridge.ui.Mdx;

public interface MdxController extends Mdx
{
    DiscoveryController getDiscovery();
    
    PairingController getPairing();
    
    SessionController getSession();
    
    void pingNccp();
    
    void removePropertyUpdateListener();
    
    void setPropertyUpdateListener(final PropertyUpdateListener p0);
    
    public interface PropertyUpdateListener
    {
        void onIsReady(final boolean p0);
        
        void onRemoteDeviceMap(final ArrayList<RemoteDevice> p0);
    }
}
