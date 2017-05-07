// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.ViewGroup;
import android.widget.LinearLayout$LayoutParams;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoDuplicate;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoHeroDuplicate;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import java.util.EnumMap;
import android.support.v4.view.PagerAdapter;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class LoMoViewPagerAdapter$4 extends BroadcastReceiver
{
    final /* synthetic */ LoMoViewPagerAdapter this$0;
    
    LoMoViewPagerAdapter$4(final LoMoViewPagerAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.w("LoMoViewPagerAdapter", "Received null intent");
        }
        else {
            final String action = intent.getAction();
            if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
                Log.v("LoMoViewPagerAdapter", "browseReceiver inovoked with Action: " + action);
            }
            if ("com.netflix.mediaclient.intent.action.BA_CW_REFRESH".equals(action)) {
                if (LoMoViewPagerAdapter$Type.CW.equals(this.this$0.state)) {
                    Log.v("LoMoViewPagerAdapter", "Reloading cw row ");
                    this.this$0.refresh(this.this$0.loMo, this.this$0.listViewPos);
                }
                this.this$0.pager.invalidateCwCache();
                return;
            }
            if ("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH".equals(action)) {
                if (LoMoViewPagerAdapter$Type.IQ.equals(this.this$0.state)) {
                    Log.v("LoMoViewPagerAdapter", "Reloading iq row ");
                    this.this$0.refresh(this.this$0.loMo, this.this$0.listViewPos);
                }
                this.this$0.pager.invalidateIqCache();
            }
        }
    }
}
