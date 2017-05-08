// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View;
import android.widget.ListView;
import android.text.format.Formatter;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthPreferenceDialog$BandwidthSavingCallback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class SettingsActivity extends FragmentHostActivity implements BandwidthPreferenceDialog$BandwidthSavingCallback, SettingsFragment$ActivityCallbackListener
{
    private static final String TAG = "nf_settings";
    private String netflixStorageSize;
    
    public static Intent createStartIntent(final Activity activity) {
        return new Intent((Context)activity, (Class)SettingsActivity.class);
    }
    
    private String formatSize(final long n) {
        return Formatter.formatShortFileSize(this.getApplicationContext(), n);
    }
    
    private void setupStorageIndicator() {
        if (this.getServiceManager() != null && this.getServiceManager().isOfflineFeatureAvailable()) {
            final ListView listView = (ListView)this.findViewById(16908298);
            if (listView != null) {
                listView.addFooterView((View)new SettingsActivity$StorageIndicatorViewHolder(this, (Context)this));
                listView.addFooterView(ViewUtils.createActionBarDummyView(this));
            }
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new SettingsActivity$1(this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return SettingsFragment.create();
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903136;
    }
    
    @Override
    public String getDownloadsSize() {
        return this.netflixStorageSize;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.settings;
    }
    
    @Override
    public void onBandwidthSettingsDone(final Context context) {
        ((SettingsFragment)this.getPrimaryFrag()).onBandwidthSettingsDone(context);
    }
    
    public void refreshStorageIndicator() {
        final ListView listView = (ListView)this.findViewById(16908298);
        if (listView != null) {
            final SettingsActivity$StorageIndicatorViewHolder settingsActivity$StorageIndicatorViewHolder = (SettingsActivity$StorageIndicatorViewHolder)listView.findViewWithTag((Object)2131689495);
            if (settingsActivity$StorageIndicatorViewHolder != null) {
                settingsActivity$StorageIndicatorViewHolder.update();
            }
        }
    }
    
    @Override
    protected boolean showMdxInMenu() {
        return false;
    }
    
    @Override
    public boolean showSettingsInMenu() {
        return false;
    }
}
