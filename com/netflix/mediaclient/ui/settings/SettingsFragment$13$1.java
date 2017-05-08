// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.preference.Preference;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class SettingsFragment$13$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ SettingsFragment$13 this$1;
    
    SettingsFragment$13$1(final SettingsFragment$13 this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int currentOfflineStorageVolume) {
        final boolean storageRemovable = this.this$1.val$offlineAgent.getOfflineStorageVolumeList().isStorageRemovable(currentOfflineStorageVolume);
        Log.i("SettingsFragment", "selected=%d isRemovable=%b", currentOfflineStorageVolume, storageRemovable);
        final Preference val$dlLocationPref = this.this$1.val$dlLocationPref;
        int summary;
        if (storageRemovable) {
            summary = 2131296916;
        }
        else {
            summary = 2131296895;
        }
        val$dlLocationPref.setSummary(summary);
        this.this$1.val$offlineAgent.setCurrentOfflineStorageVolume(currentOfflineStorageVolume);
        dialogInterface.dismiss();
        final SettingsActivity settingsActivity = AndroidUtils.getContextAs((Context)this.this$1.this$0.getActivity(), SettingsActivity.class);
        if (settingsActivity != null) {
            settingsActivity.refreshStorageIndicator();
            if (storageRemovable) {
                settingsActivity.requestExternalStoragePermission();
            }
        }
    }
}
