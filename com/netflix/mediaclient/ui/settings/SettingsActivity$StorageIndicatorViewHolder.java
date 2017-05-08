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
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolumeUiList;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListView;
import android.text.format.Formatter;
import android.content.Intent;
import android.app.Activity;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthPreferenceDialog$BandwidthSavingCallback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import java.io.File;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolume;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;

class SettingsActivity$StorageIndicatorViewHolder extends LinearLayout
{
    TextView deviceName;
    View freeView;
    TextView freeViewLegend;
    TextView isDefault;
    View netflixView;
    TextView netflixViewLegend;
    final /* synthetic */ SettingsActivity this$0;
    View usedView;
    TextView usedViewLegend;
    OfflineStorageVolume volume;
    
    SettingsActivity$StorageIndicatorViewHolder(final SettingsActivity this$0, final Context context, final OfflineStorageVolume volume) {
        this.this$0 = this$0;
        super(context);
        this.volume = volume;
        this$0.getLayoutInflater().inflate(2130903316, (ViewGroup)this);
        this.findViews();
        this.update();
        this.setStorageName();
        this.setIsDefault();
        this.setupClicks();
    }
    
    private void findViews() {
        this.netflixViewLegend = (TextView)this.findViewById(2131755927);
        this.usedViewLegend = (TextView)this.findViewById(2131755926);
        this.freeViewLegend = (TextView)this.findViewById(2131755928);
        this.deviceName = (TextView)this.findViewById(2131755920);
        this.isDefault = (TextView)this.findViewById(2131755921);
        this.netflixView = this.findViewById(2131755924);
        this.usedView = this.findViewById(2131755923);
        this.freeView = this.findViewById(2131755925);
    }
    
    private void setIsDefault() {
        if (this.isDefault != null) {
            ViewUtils.setVisibleOrGone((View)this.isDefault, this.volume.isCurrentlySelected());
        }
    }
    
    private void setStorageName() {
        if (this.deviceName != null) {
            final SettingsActivity this$0 = this.this$0;
            int n;
            if (this.volume.isRemovable()) {
                n = 2131296916;
            }
            else {
                n = 2131296895;
            }
            final String string = this$0.getString(n);
            this.deviceName.setText((CharSequence)string);
            this.setTag((Object)string);
        }
    }
    
    private void setupClicks() {
        this.setOnClickListener((View$OnClickListener)new SettingsActivity$StorageIndicatorViewHolder$1(this));
    }
    
    void update() {
        try {
            final File externalDownloadDirIfAvailable = AndroidUtils.getExternalDownloadDirIfAvailable((Context)this.this$0);
            if (externalDownloadDirIfAvailable == null) {
                Log.e("nf_settings", "SettingsActivity:update fileDir is null");
                return;
            }
            externalDownloadDirIfAvailable.getAbsolutePath();
            if (this.volume == null) {
                Log.e("nf_settings", "SettingsActivity:update volume is null");
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            Log.e("nf_settings", ex.toString());
            ErrorLoggingManager.logHandledException(ex);
            return;
        }
        final long totalSpace = this.volume.getTotalSpace();
        final long freeSpace = this.volume.getFreeSpace();
        final long netflixUsedSpace = this.volume.getNetflixUsedSpace();
        final long n = totalSpace - freeSpace - netflixUsedSpace;
        ((LinearLayout$LayoutParams)this.netflixView.getLayoutParams()).weight = netflixUsedSpace;
        ((LinearLayout$LayoutParams)this.usedView.getLayoutParams()).weight = n;
        ((LinearLayout$LayoutParams)this.freeView.getLayoutParams()).weight = freeSpace;
        this.this$0.netflixStorageSize = this.this$0.formatSize(netflixUsedSpace);
        final String access$100 = this.this$0.formatSize(n);
        final String access$101 = this.this$0.formatSize(freeSpace);
        this.netflixViewLegend.setText((CharSequence)this.this$0.getString(2131296510, new Object[] { this.this$0.netflixStorageSize }));
        this.usedViewLegend.setText((CharSequence)this.this$0.getString(2131296511, new Object[] { access$100 }));
        this.freeViewLegend.setText((CharSequence)this.this$0.getString(2131296509, new Object[] { access$101 }));
        this.forceLayout();
        this.setIsDefault();
    }
}
