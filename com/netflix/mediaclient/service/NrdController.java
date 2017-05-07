// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.net.IpConnectivityPolicy;
import com.netflix.mediaclient.util.AppStoreHelper;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import android.view.WindowManager;
import android.view.Display;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.javabridge.invoke.android.SetNetworkInterfaces;
import com.netflix.mediaclient.javabridge.invoke.android.SetLanguage;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Locale;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.invoke.mdx.InterfaceChanged;
import com.netflix.mediaclient.repository.UserLocale;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.javabridge.ui.android.NrdpWrapper;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.NrdProxyFactory;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.NrdProxy;

public class NrdController extends ServiceAgent
{
    private static final String CA_FILENAME = "ca.pem";
    private static final String TAG = "nf_nrdcontroller";
    private NrdJSCmdReceiver mNrdJsCmdReceiver;
    private NrdProxy nrd;
    private Nrdp nrdp;
    
    private void initializeNrdLib() throws Exception {
        Log.d("nf_nrdcontroller", "Initialize NRD bridge first");
        if (this.nrd != null) {
            throw new IllegalStateException("nrd is already created.  This should not happen!");
        }
        (this.nrd = NrdProxyFactory.createInstance(new NrdBridge())).init(null);
        Log.d("nf_nrdcontroller", "NRD bridge initialization done");
        Log.d("nf_nrdcontroller", "Start listening for updates from NRDLIb");
        (this.nrdp = new NrdpWrapper(this.nrd)).addEventListener("init", new EventListener() {
            @Override
            public void received(final UIEvent uiEvent) {
                new BackgroundTask().execute(new Runnable() {
                    final /* synthetic */ EventListener val$el;
                    
                    @Override
                    public void run() {
                        Log.d("nf_nrdcontroller", "Bridge is initialized");
                        NrdController.this.nrdp.removeEventListener("init", this.val$el);
                        NrdController.this.nrdp.getDevice().setUIVersion(NrdController.this.getConfigurationAgent().getSoftwareVersion());
                        NrdController.this.initCompleted(CommonStatus.OK);
                    }
                });
            }
        });
        this.nrd.connect();
    }
    
    private boolean loadNrdLib() {
        try {
            FileUtils.copyFileFromAssetToFS(this.getContext(), "ca.pem", "ca.pem", false);
            if (!SecurityRepository.isLoaded()) {
                Log.e("nf_nrdcontroller", "Native libraries failed to load. Probably not enough space left on device.");
                return false;
            }
            Log.d("nf_nrdcontroller", "Initializing NrdLib");
            this.initializeNrdLib();
            return true;
        }
        catch (Exception ex) {
            Log.e("nf_nrdcontroller", "Failed to initiate NRDLib", ex);
            return false;
        }
    }
    
    private void postNrdInit() {
        this.setDeviceLocale(UserLocale.getDeviceLocale(this.getContext()));
        Log.d("nf_nrdcontroller", "Sets IP address and interface");
        this.nrd.invokeMethod(new InterfaceChanged(this.getContext()));
    }
    
    private void setUIVersion() {
    }
    
    @Override
    public void destroy() {
        if (this.mNrdJsCmdReceiver != null) {
            this.getContext().unregisterReceiver((BroadcastReceiver)this.mNrdJsCmdReceiver);
        }
        if (this.nrd != null) {
            this.nrd.disconnect();
            this.nrd.destroy();
            this.nrd = null;
        }
        this.nrdp = null;
        super.destroy();
    }
    
    @Override
    protected void doInit() {
        Log.d("nf_nrdcontroller", "NrdController starting doInit");
        if (!this.loadNrdLib()) {
            this.initCompleted(CommonStatus.NRD_ERROR);
        }
    }
    
    public Nrdp getNrdp() {
        return this.nrdp;
    }
    
    public void setActivationToken(final String s, final String s2) {
    }
    
    public void setDeviceLocale(final Locale locale) {
        if (locale != null) {
            final String userLocale = UserLocale.toUserLocale(locale);
            if (StringUtils.isNotEmpty(userLocale)) {
                Log.d("nf_nrdcontroller", "Sets device language to JNI to " + userLocale);
                if (this.nrd != null) {
                    this.nrd.invokeMethod(new SetLanguage(userLocale));
                }
            }
        }
    }
    
    public void setNetworkInterfaces() {
        if (this.nrd != null) {
            this.nrd.invokeMethod(new SetNetworkInterfaces(this.getContext()));
        }
    }
    
    public void setPreferredLanguages(final String[] array) {
        StringUtils.joinArray(array);
    }
    
    private class NrdBridge implements Bridge
    {
        @Override
        public int getConfigVideoBufferSize() {
            return NrdController.this.getConfigurationAgent().getVideoBufferSize();
        }
        
        @Override
        public PlayerType getCurrentPlayerType() {
            return NrdController.this.getConfigurationAgent().getCurrentPlayerType();
        }
        
        @Override
        public DeviceCategory getDeviceCategory() {
            return NrdController.this.getConfigurationAgent().getDeviceCategory();
        }
        
        @Override
        public Locale getDeviceLocale() {
            return UserLocale.getDeviceLocale(NrdController.this.getContext());
        }
        
        @Override
        public Display getDisplaySize() {
            final WindowManager windowManager = (WindowManager)NrdController.this.getContext().getSystemService("window");
            if (windowManager != null) {
                return windowManager.getDefaultDisplay();
            }
            return null;
        }
        
        @Override
        public EsnProvider getEsnProvider() {
            return NrdController.this.getConfigurationAgent().getEsnProvider();
        }
        
        @Override
        public String getFileSystemRoot() {
            return NrdController.this.getContext().getFilesDir().getAbsolutePath();
        }
        
        @Override
        public String getInstallationSource() {
            return AppStoreHelper.getInstallationSource(NrdController.this.getContext());
        }
        
        @Override
        public IpConnectivityPolicy getIpConnectivityPolicy() {
            return NrdController.this.getConfigurationAgent().getIpConnectivityPolicy();
        }
        
        @Override
        public NrdProxy getNrdProxy() {
            return NrdController.this.nrd;
        }
        
        @Override
        public Nrdp getNrdp() {
            return NrdController.this.nrdp;
        }
        
        @Override
        public String getSoftwareVersion() {
            return NrdController.this.getConfigurationAgent().getSoftwareVersion();
        }
        
        @Override
        public boolean isDeviceLowMem() {
            return NrdController.this.getConfigurationAgent().isDeviceLowMem();
        }
    }
    
    private class NrdJSCmdReceiver extends BroadcastReceiver
    {
        public static final String JS_BRIDGE_INTENT = "com.netflix.mediaclient.intent.action.JS_BRIDGE_CMD";
        
        public void onReceive(final Context context, final Intent intent) {
            if (Log.isLoggable("nf_nrdcontroller", 3)) {
                Log.d("nf_nrdcontroller", "Received an action: " + intent.getAction());
            }
            if (!"com.netflix.mediaclient.intent.action.JS_BRIDGE_CMD".equals(intent.getAction())) {
                return;
            }
            try {
                final Bundle extras = intent.getExtras();
                if (extras != null) {
                    final String string = extras.getString("object");
                    final String string2 = extras.getString("method");
                    final String string3 = extras.getString("params");
                    if (string != null && string2 != null && string3 != null) {
                        NrdController.this.nrd.invokeMethod(string, string2, string3);
                    }
                    if (Log.isLoggable("nf_nrdcontroller", 3)) {
                        Log.d("nf_nrdcontroller", "JS CMD: object: " + string + " method:  " + string2 + " param: " + string3);
                    }
                }
            }
            catch (Exception ex) {
                Log.e("nf_nrdcontroller", "Unintented Exception thrown ", ex);
            }
        }
    }
}
