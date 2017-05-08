// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.Log;
import android.app.Activity;
import com.netflix.mediaclient.service.error.action.RestartApplicationAction;

public class FailbackToLegacyCryptoAction extends RestartApplicationAction
{
    public FailbackToLegacyCryptoAction(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void run() {
        Log.d("ErrorManager", "Blacklisted Widevine L3 plugin, fail back to legacy crypto scheme ");
        PreferenceUtils.putBooleanPref((Context)this.mActivity, "disable_widevine_l3", true);
        super.run();
    }
}
