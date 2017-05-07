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
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class AboutActivity extends FragmentHostActivity
{
    private static final String TAG = "nf_AboutActivity";
    
    public static Intent createStartIntent(final Activity activity) {
        return new Intent((Context)activity, (Class)AboutActivity.class);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new AboutActivity$1(this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return AboutFragment.create();
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903097;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.settings;
    }
    
    @Override
    protected boolean hasUpAction() {
        return false;
    }
    
    @Override
    protected boolean showAboutInMenu() {
        return false;
    }
    
    @Override
    protected boolean showMdxInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return false;
    }
}
