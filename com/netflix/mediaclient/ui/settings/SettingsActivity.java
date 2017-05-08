// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
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

public class SettingsActivity extends FragmentHostActivity implements BandwidthPreferenceDialog$BandwidthSavingCallback, SettingsFragment$ActivityCallbackListener
{
    private static final String TAG = "nf_settings";
    private final BroadcastReceiver mOsvSpaceUpdatedReceiver;
    private String netflixStorageSize;
    
    public SettingsActivity() {
        this.mOsvSpaceUpdatedReceiver = new SettingsActivity$1(this);
    }
    
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
                final OfflineStorageVolumeUiList offlineStorageVolumeList = this.getServiceManager().getOfflineAgent().getOfflineStorageVolumeList();
                for (int i = 0; i < offlineStorageVolumeList.size(); ++i) {
                    listView.addFooterView((View)new SettingsActivity$StorageIndicatorViewHolder(this, (Context)this, offlineStorageVolumeList.get(i)));
                }
                listView.addFooterView(ViewUtils.createActionBarDummyView(this));
            }
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new SettingsActivity$2(this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return SettingsFragment.create();
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903141;
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
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.registerReceiverLocallyWithAutoUnregister(this.mOsvSpaceUpdatedReceiver, "com.netflix.mediaclient.intent.offline.osv.space.usage.updated");
    }
    
    @Override
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        switch (n) {
            case 1: {
                if (array2.length > 0 && array2[0] == 0) {
                    Log.i("nf_settings", "permission is granted");
                    return;
                }
                if (array2.length <= 0 || array2[0] != -1) {
                    break;
                }
                Log.i("nf_settings", "onRequestPermissionsResult showRationale=%b", ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE"));
                final Fragment primaryFrag = this.getPrimaryFrag();
                if (primaryFrag != null && primaryFrag instanceof SettingsFragment) {
                    ((SettingsFragment)primaryFrag).onExternalStoragePermissionDenied();
                    return;
                }
                break;
            }
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        final OfflineAgentInterface offlineAgent = this.getServiceManager().getOfflineAgent();
        if (offlineAgent != null) {
            offlineAgent.recalculateSpaceUsageForOfflineStorageVolumes();
        }
    }
    
    public void refreshStorageIndicator() {
        final ListView listView = (ListView)this.findViewById(16908298);
        if (listView != null) {
            final SettingsActivity$StorageIndicatorViewHolder settingsActivity$StorageIndicatorViewHolder = (SettingsActivity$StorageIndicatorViewHolder)listView.findViewWithTag((Object)this.getString(2131296895));
            if (settingsActivity$StorageIndicatorViewHolder != null) {
                settingsActivity$StorageIndicatorViewHolder.update();
            }
            final SettingsActivity$StorageIndicatorViewHolder settingsActivity$StorageIndicatorViewHolder2 = (SettingsActivity$StorageIndicatorViewHolder)listView.findViewWithTag((Object)this.getString(2131296916));
            if (settingsActivity$StorageIndicatorViewHolder2 != null) {
                settingsActivity$StorageIndicatorViewHolder2.update();
            }
        }
    }
    
    public void requestExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission((Context)this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            Log.i("nf_settings", "requestExternalStoragePermission requesting permission.");
            ActivityCompat.requestPermissions(this, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
            return;
        }
        Log.i("nf_settings", "requestExternalStoragePermission already have permission.");
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
