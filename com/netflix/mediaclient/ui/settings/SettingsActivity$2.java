// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolumeUiList;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolume;
import android.widget.ListView;
import android.text.format.Formatter;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthPreferenceDialog$BandwidthSavingCallback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class SettingsActivity$2 implements ManagerStatusListener
{
    final /* synthetic */ SettingsActivity this$0;
    
    SettingsActivity$2(final SettingsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.getNetflixActionBar().setDisplayHomeAsUpEnabled(serviceManager.isUserLoggedIn());
        ((SettingsFragment)this.this$0.getPrimaryFrag()).onManagerReady(serviceManager, status);
        this.this$0.setupStorageIndicator();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("nf_settings", "NetflixService is NOT available!");
        ((SettingsFragment)this.this$0.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
    }
}
