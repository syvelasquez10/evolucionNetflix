// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import android.net.NetworkInfo;
import android.content.Context;
import com.netflix.mediaclient.javabridge.invoke.android.SetNetworkInterfaces;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.javabridge.invoke.android.SetLanguage;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Locale;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.invoke.mdx.InterfaceChanged;
import com.netflix.mediaclient.util.l10n.UserLocale;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.javabridge.ui.android.NrdpWrapper;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.NrdProxyFactory;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.NrdProxy;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class NrdController$1$1 implements Runnable
{
    final /* synthetic */ NrdController$1 this$1;
    final /* synthetic */ EventListener val$el;
    
    NrdController$1$1(final NrdController$1 this$1, final EventListener val$el) {
        this.this$1 = this$1;
        this.val$el = val$el;
    }
    
    @Override
    public void run() {
        Log.d("nf_nrdcontroller", "Bridge is initialized");
        this.this$1.this$0.nrdp.removeEventListener("init", this.val$el);
        this.this$1.this$0.nrdp.getDevice().setUIVersion(this.this$1.this$0.getConfigurationAgent().getSoftwareVersion());
        if (this.this$1.this$0.getConfigurationAgent() != null && this.this$1.this$0.getConfigurationAgent().getPlaybackConfiguration() != null) {
            this.this$1.this$0.nrdp.getMedia().setPreviewContentConfig(this.this$1.this$0.getConfigurationAgent().getPreviewContentConfiguration());
        }
        this.this$1.this$0.initCompleted(CommonStatus.OK);
    }
}
