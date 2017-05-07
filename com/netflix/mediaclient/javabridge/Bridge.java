// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge;

import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.net.IpConnectivityPolicy;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import android.view.Display;
import java.util.Locale;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;

public interface Bridge
{
    int getBitrateCap();
    
    int getConfigVideoBufferSize();
    
    PlayerType getCurrentPlayerType();
    
    DeviceCategory getDeviceCategory();
    
    Locale getDeviceLocale();
    
    Display getDisplaySize();
    
    EsnProvider getEsnProvider();
    
    String getFileSystemRoot();
    
    String getInstallationSource();
    
    IpConnectivityPolicy getIpConnectivityPolicy();
    
    NrdProxy getNrdProxy();
    
    Nrdp getNrdp();
    
    String getSoftwareVersion();
    
    boolean isDeviceLowMem();
}
