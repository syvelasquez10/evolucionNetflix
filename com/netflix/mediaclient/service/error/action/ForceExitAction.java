// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.action;

import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.app.Activity;

public class ForceExitAction extends BaseAction
{
    public ForceExitAction(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Log.d("ErrorManager", "Waiting 1.5 second to exit app");
                this.wait(1500L);
                Log.d("ErrorManager", "Kill app");
                AndroidUtils.forceStop((Context)this.mActivity);
            }
            catch (Exception ex) {
                Log.w("ErrorManager", "Wait is interrupted", ex);
                continue;
            }
            break;
        }
    }
}
