// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.View;
import android.view.View$OnClickListener;

class TopPanel$5 implements View$OnClickListener
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$5(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
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
    }
}
