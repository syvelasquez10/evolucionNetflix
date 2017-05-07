// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.app.Activity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class TopPanel$6 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$6(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        boolean b = false;
        final boolean booleanPref = PreferenceUtils.getBooleanPref((Context)this.this$0.playerFragment.getActivity(), "ui.playeroverlay", false);
        if (booleanPref) {
            Log.d("screen", "Disable debug metadata on player UI");
        }
        else {
            Log.d("screen", "Enable debug metadata on `player UI");
        }
        final Activity activity = this.this$0.playerFragment.getActivity();
        if (!booleanPref) {
            b = true;
        }
        PreferenceUtils.putBooleanPref((Context)activity, "ui.playeroverlay", b);
        return true;
    }
}
