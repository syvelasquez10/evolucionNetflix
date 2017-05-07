// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public abstract class AccountActivity extends NetflixActivity
{
    private static final String ACTION_FINISH_ACCOUNT_ACTIVITIES = "com.netflix.mediaclient.ui.login.ACTION_FINISH_ACCOUNT_ACTIVITIES";
    
    public static void finishAllAccountActivities(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.ui.login.ACTION_FINISH_ACCOUNT_ACTIVITIES"));
    }
    
    @Override
    protected boolean hasUpAction() {
        return false;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.registerFinishReceiverWithAutoUnregister("com.netflix.mediaclient.ui.login.ACTION_FINISH_ACCOUNT_ACTIVITIES");
    }
    
    @Override
    public void performUpAction() {
    }
    
    public boolean showMdxInMenu() {
        return false;
    }
    
    @Override
    public boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    public boolean showSignOutInMenu() {
        return false;
    }
}
