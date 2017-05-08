// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.net.CronetHttpURLConnectionFactory;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.MenuItem;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.support.v7.app.AlertDialog$Builder;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$6 implements MenuItem$OnMenuItemClickListener
{
    int choice;
    String[] options;
    final /* synthetic */ DebugMenuItems this$0;
    final /* synthetic */ String val$currentEnvironment;
    final /* synthetic */ String[] val$prodEnvironments;
    final /* synthetic */ String[] val$testEnvironments;
    
    DebugMenuItems$6(final DebugMenuItems this$0, final String[] val$testEnvironments, final String val$currentEnvironment, final String[] val$prodEnvironments) {
        this.this$0 = this$0;
        this.val$testEnvironments = val$testEnvironments;
        this.val$currentEnvironment = val$currentEnvironment;
        this.val$prodEnvironments = val$prodEnvironments;
        this.choice = -1;
    }
    
    private void configureEnvironmentOptions() {
        final String val$currentEnvironment = this.val$currentEnvironment;
        switch (val$currentEnvironment) {
            default: {
                this.options = new String[] { this.val$currentEnvironment };
                this.choice = 0;
            }
            case "api-global.netflix.com": {
                this.options = this.val$prodEnvironments;
                this.choice = 0;
            }
            case "api-staging.netflix.com": {
                this.options = this.val$prodEnvironments;
                this.choice = 1;
            }
            case "api-int.test.netflix.com": {
                this.options = this.val$testEnvironments;
                this.choice = 0;
            }
            case "api.test.netflix.com": {
                this.options = this.val$testEnvironments;
                this.choice = 1;
            }
        }
    }
    
    private void showOptionsDialog(final int n, final boolean b, final String[] array) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.this$0.activity);
        alertDialog$Builder.setTitle("Pick API environment");
        alertDialog$Builder.setSingleChoiceItems(array, n, (DialogInterface$OnClickListener)new DebugMenuItems$6$1(this, b));
        alertDialog$Builder.show();
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.configureEnvironmentOptions();
        this.showOptionsDialog(this.choice, this.options == this.val$testEnvironments, this.options);
        return true;
    }
}
