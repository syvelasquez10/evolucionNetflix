// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.action;

import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.app.Activity;

public class RestartApplicationAction extends BaseAction
{
    public RestartApplicationAction(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void run() {
        Log.e("ErrorManager", "restartApp");
        if (this.mActivity instanceof NetflixActivity) {
            NetflixActivity.finishAllActivities((Context)this.mActivity);
        }
        final Intent intent = new Intent();
        intent.setClass((Context)this.mActivity, (Class)NetflixService.class);
        this.mActivity.stopService(intent);
    }
}
