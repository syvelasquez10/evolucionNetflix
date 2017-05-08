// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthPreferenceDialog$BandwidthSavingCallback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class SettingsActivity extends FragmentHostActivity implements BandwidthPreferenceDialog$BandwidthSavingCallback
{
    private static final String TAG = "nf_settings";
    
    public static Intent createStartIntent(final Activity activity) {
        return new Intent((Context)activity, (Class)SettingsActivity.class);
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
        return 2130903127;
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
    protected boolean showMdxInMenu() {
        return false;
    }
    
    @Override
    public boolean showSettingsInMenu() {
        return false;
    }
}
