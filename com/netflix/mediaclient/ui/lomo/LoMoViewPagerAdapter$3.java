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
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import java.util.EnumMap;
import android.support.v4.view.PagerAdapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class LoMoViewPagerAdapter$3 implements RowAdapterCallbacks
{
    final /* synthetic */ LoMoViewPagerAdapter this$0;
    
    LoMoViewPagerAdapter$3(final LoMoViewPagerAdapter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public NetflixActivity getActivity() {
        return this.this$0.activity;
    }
    
    @Override
    public void notifyParentOfDataSetChange() {
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "Notified parent of data set change");
        }
        this.this$0.notifyDataSetChanged();
        this.this$0.pager.notifyDataSetChanged();
        if (this.this$0.pager.getCurrentItem() == 0) {
            if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
                Log.v("LoMoViewPagerAdapter", "Data loaded for page 0 - saving state");
            }
            this.this$0.pager.saveStateAndTrackPresentation(0);
        }
    }
    
    @Override
    public void notifyParentOfError() {
        if (this.this$0.state != LoMoViewPagerAdapter$Type.ERROR) {
            this.this$0.preErrorState = this.this$0.state;
        }
        if (Log.isLoggable("LoMoViewPagerAdapter", 2)) {
            Log.v("LoMoViewPagerAdapter", "Pre-error state: " + this.this$0.preErrorState);
        }
        this.this$0.setState(LoMoViewPagerAdapter$Type.ERROR);
        this.this$0.notifyDataSetChanged();
        this.this$0.showReloadViews();
    }
}
