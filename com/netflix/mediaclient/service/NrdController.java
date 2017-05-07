// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.javabridge.invoke.android.SetNetworkInterfaces;
import com.netflix.mediaclient.javabridge.invoke.android.SetLanguage;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Locale;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.invoke.mdx.InterfaceChanged;
import com.netflix.mediaclient.repository.UserLocale;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.android.NrdpWrapper;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.NrdProxyFactory;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.NrdProxy;

public class NrdController extends ServiceAgent
{
    private static final String CA_FILENAME = "ca.pem";
    private static final String TAG = "nf_nrdcontroller";
    private NrdController$NrdJSCmdReceiver mNrdJsCmdReceiver;
    private NrdProxy nrd;
    private Nrdp nrdp;
    
    public NrdController() {
        this.nrd = NrdProxyFactory.createInstance(new NrdController$NrdBridge(this, null));
        this.nrdp = new NrdpWrapper(this.nrd);
    }
    
    private void initializeNrdLib() {
        Log.d("nf_nrdcontroller", "Initialize NRD bridge first");
        this.nrd.init(null);
        Log.d("nf_nrdcontroller", "NRD bridge initialization done");
        Log.d("nf_nrdcontroller", "Start listening for updates from NRDLIb");
        this.nrdp.addEventListener("init", new NrdController$1(this));
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
}
