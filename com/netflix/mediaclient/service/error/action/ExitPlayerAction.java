// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.action;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import android.app.Activity;

public class ExitPlayerAction extends BaseAction
{
    public ExitPlayerAction(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void run() {
        if (this.mActivity != null && this.mActivity instanceof PlayerActivity) {
            Log.e("ErrorManager", "Exit");
            this.mActivity.finish();
        }
    }
}
