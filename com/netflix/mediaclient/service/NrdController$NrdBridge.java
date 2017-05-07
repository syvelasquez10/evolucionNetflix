// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.javabridge.invoke.android.SetNetworkInterfaces;
import com.netflix.mediaclient.javabridge.invoke.android.SetLanguage;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.invoke.mdx.InterfaceChanged;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.android.NrdpWrapper;
import com.netflix.mediaclient.javabridge.NrdProxyFactory;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.NrdProxy;
import com.netflix.mediaclient.service.net.IpConnectivityPolicy;
import com.netflix.mediaclient.util.AppStoreHelper;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import android.view.WindowManager;
import android.view.Display;
import com.netflix.mediaclient.repository.UserLocale;
import java.util.Locale;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import android.content.Context;
import com.netflix.mediaclient.javabridge.Bridge;

class NrdController$NrdBridge implements Bridge
{
    final /* synthetic */ NrdController this$0;
    
    private NrdController$NrdBridge(final NrdController this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean enableLowBitrateStreams() {
        return this.this$0.getConfigurationAgent().enableLowBitrateStreams();
    }
    
    @Override
    public int getConfigVideoBufferSize() {
        return this.this$0.getConfigurationAgent().getVideoBufferSize();
    }
    
    @Override
    public Context getContext() {
        return this.this$0.getContext();
    }
    
    @Override
    public PlayerType getCurrentPlayerType() {
        return this.this$0.getConfigurationAgent().getCurrentPlayerType();
    }
    
    @Override
    public DeviceCategory getDeviceCategory() {
        return this.this$0.getConfigurationAgent().getDeviceCategory();
    }
    
    @Override
    public Locale getDeviceLocale() {
        return UserLocale.getDeviceLocale(this.getContext());
    }
    
    @Override
    public Display getDisplaySize() {
        final WindowManager windowManager = (WindowManager)this.getContext().getSystemService("window");
        if (windowManager != null) {
            return windowManager.getDefaultDisplay();
        }
        return null;
    }
    
    @Override
    public EsnProvider getEsnProvider() {
        return this.this$0.getConfigurationAgent().getEsnProvider();
    }
    
    @Override
    public String getFileSystemRoot() {
        return this.getContext().getFilesDir().getAbsolutePath();
    }
    
    @Override
    public String getInstallationSource() {
        return AppStoreHelper.getInstallationSource(this.getContext());
    }
    
    @Override
    public IpConnectivityPolicy getIpConnectivityPolicy() {
        return this.this$0.getConfigurationAgent().getIpConnectivityPolicy();
    }
    
    @Override
    public NrdProxy getNrdProxy() {
        return this.this$0.nrd;
    }
    
    @Override
    public Nrdp getNrdp() {
        return this.this$0.nrdp;
    }
    
    @Override
    public String getSoftwareVersion() {
        return this.this$0.getConfigurationAgent().getSoftwareVersion();
    }
    
    @Override
    public boolean isDeviceLowMem() {
        return this.this$0.getConfigurationAgent().isDeviceLowMem();
    }
}
