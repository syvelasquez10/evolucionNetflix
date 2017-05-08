// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.app.Activity;
import com.netflix.mediaclient.ui.offline.OfflineActivity;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View;
import android.view.View$OnClickListener;

class SettingsActivity$StorageIndicatorViewHolder$1 implements View$OnClickListener
{
    final /* synthetic */ SettingsActivity$StorageIndicatorViewHolder this$1;
    
    SettingsActivity$StorageIndicatorViewHolder$1(final SettingsActivity$StorageIndicatorViewHolder this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$1.this$0) && this.this$1.this$0.getServiceManager().isOfflineFeatureAvailable()) {
            this.this$1.this$0.startActivity(OfflineActivity.showAllDownloads(this.this$1.this$0));
        }
    }
}
