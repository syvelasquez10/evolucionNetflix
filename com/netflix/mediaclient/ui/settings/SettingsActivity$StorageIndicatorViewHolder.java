// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ListView;
import android.text.format.Formatter;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthPreferenceDialog$BandwidthSavingCallback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import java.io.File;
import android.os.Build;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.FileUtils;
import android.os.StatFs;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;

class SettingsActivity$StorageIndicatorViewHolder extends LinearLayout
{
    TextView deviceName;
    View freeView;
    TextView freeViewLegend;
    View netflixView;
    TextView netflixViewLegend;
    final /* synthetic */ SettingsActivity this$0;
    View usedView;
    TextView usedViewLegend;
    
    SettingsActivity$StorageIndicatorViewHolder(final SettingsActivity this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this$0.getLayoutInflater().inflate(2130903302, (ViewGroup)this);
        this.findViews();
        this.update();
        this.setTag((Object)2131689495);
        this.setupClicks();
    }
    
    private void findViews() {
        this.netflixViewLegend = (TextView)this.findViewById(2131690359);
        this.usedViewLegend = (TextView)this.findViewById(2131690358);
        this.freeViewLegend = (TextView)this.findViewById(2131690360);
        this.deviceName = (TextView)this.findViewById(2131690353);
        this.netflixView = this.findViewById(2131690356);
        this.usedView = this.findViewById(2131690355);
        this.freeView = this.findViewById(2131690357);
    }
    
    private void setupClicks() {
        this.setOnClickListener((View$OnClickListener)new SettingsActivity$StorageIndicatorViewHolder$1(this));
    }
    
    void update() {
        File externalDownloadDirIfAvailable;
        String absolutePath;
        try {
            externalDownloadDirIfAvailable = AndroidUtils.getExternalDownloadDirIfAvailable((Context)this.this$0);
            if (externalDownloadDirIfAvailable == null) {
                Log.e("nf_settings", "SettingsActivity:update filedir is null");
                return;
            }
            absolutePath = externalDownloadDirIfAvailable.getAbsolutePath();
            if (StringUtils.isEmpty(absolutePath)) {
                Log.e("nf_settings", "SettingsActivity:update getAbsolutePath call returned is null");
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            Log.e("nf_settings", ex.toString());
            ErrorLoggingManager.logHandledException(ex);
            return;
        }
        final long totalBytes = new StatFs(absolutePath).getTotalBytes();
        final long freeSpaceOnFileSystem = AndroidUtils.getFreeSpaceOnFileSystem(externalDownloadDirIfAvailable);
        final long directorySizeInBytes = FileUtils.getDirectorySizeInBytes(externalDownloadDirIfAvailable.getAbsoluteFile());
        final long n = totalBytes - freeSpaceOnFileSystem - directorySizeInBytes;
        ((LinearLayout$LayoutParams)this.netflixView.getLayoutParams()).weight = directorySizeInBytes;
        ((LinearLayout$LayoutParams)this.usedView.getLayoutParams()).weight = n;
        ((LinearLayout$LayoutParams)this.freeView.getLayoutParams()).weight = freeSpaceOnFileSystem;
        this.this$0.netflixStorageSize = this.this$0.formatSize(directorySizeInBytes);
        final String access$100 = this.this$0.formatSize(n);
        final String access$101 = this.this$0.formatSize(freeSpaceOnFileSystem);
        this.netflixViewLegend.setText((CharSequence)this.this$0.getString(2131230954, new Object[] { this.this$0.netflixStorageSize }));
        this.usedViewLegend.setText((CharSequence)this.this$0.getString(2131230955, new Object[] { access$100 }));
        this.freeViewLegend.setText((CharSequence)this.this$0.getString(2131230953, new Object[] { access$101 }));
        this.deviceName.setText((CharSequence)Build.MODEL);
        this.forceLayout();
    }
}
