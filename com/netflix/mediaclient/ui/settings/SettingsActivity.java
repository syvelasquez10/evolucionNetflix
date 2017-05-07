// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class SettingsActivity extends FragmentHostActivity
{
    public static Intent createStartIntent(final Activity activity) {
        return new Intent((Context)activity, (Class)SettingsActivity.class);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return SettingsFragment.create();
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903086;
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.settings;
    }
    
    @Override
    protected boolean showMdxInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
}
