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
    private boolean doNotWait;
    
    public ForceExitAction(final Activity activity) {
        super(activity);
    }
    
    public ForceExitAction(final Activity activity, final boolean doNotWait) {
        super(activity);
        this.doNotWait = doNotWait;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Log.d("ErrorManager", "Waiting 1.5 second to exit app");
                if (!this.doNotWait) {
                    this.wait(1500L);
                }
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
