// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class TopPanel$5 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$5(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        boolean b = false;
        final boolean booleanPref = PreferenceUtils.getBooleanPref((Context)this.this$0.context, "ui.playeroverlay", false);
        if (booleanPref) {
            Log.d("screen", "Disable debug metadata on player UI");
        }
        else {
            Log.d("screen", "Enable debug metadata on `player UI");
        }
        final PlayerActivity context = this.this$0.context;
        if (!booleanPref) {
            b = true;
        }
        PreferenceUtils.putBooleanPref((Context)context, "ui.playeroverlay", b);
        return true;
    }
}
