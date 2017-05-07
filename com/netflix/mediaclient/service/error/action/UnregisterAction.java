// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.action;

import android.content.Context;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import android.app.Activity;

public class UnregisterAction extends BaseAction
{
    public UnregisterAction(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void run() {
        this.mActivity.startActivity(LogoutActivity.create((Context)this.mActivity));
        this.mActivity.finish();
    }
}
