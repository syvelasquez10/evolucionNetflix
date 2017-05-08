// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.details.DeviceCapabilityProvider;

final class DeviceUtils$1 implements DeviceCapabilityProvider
{
    final /* synthetic */ ServiceManager val$serviceManager;
    
    DeviceUtils$1(final ServiceManager val$serviceManager) {
        this.val$serviceManager = val$serviceManager;
    }
    
    @Override
    public boolean is3dSupported() {
        return false;
    }
    
    @Override
    public boolean is5dot1Supported() {
        return this.val$serviceManager != null && this.val$serviceManager.isDolbyDigitalPlus51Supported();
    }
    
    @Override
    public boolean isDolbyVisionSupported() {
        return false;
    }
    
    @Override
    public boolean isHdSupported() {
        return this.val$serviceManager != null && this.val$serviceManager.isDeviceHd();
    }
    
    @Override
    public boolean isHdr10Supported() {
        return false;
    }
    
    @Override
    public boolean isUltraHdSupported() {
        return false;
    }
}
