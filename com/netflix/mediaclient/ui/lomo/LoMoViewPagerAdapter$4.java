// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.android.fragment.LoadingView;
import android.view.ViewGroup;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewParent;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import com.netflix.mediaclient.ui.lomo.discovery.PaginatedDiscoveryAdapter;
import android.widget.FrameLayout;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoDuplicate;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoHeroDuplicate;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.ui.lomo.discovery.ProgressiveDiscoveryAdapter;
import com.netflix.mediaclient.ui.lomo.discovery.DiscoveryBackgroundAnimator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.EnumMap;
import android.support.v4.view.PagerAdapter;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
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
            if (Log.isLoggable()) {
                Log.v("LoMoViewPagerAdapter", "browseReceiver inovoked with Action: " + action);
            }
            if ("com.netflix.mediaclient.intent.action.BA_CW_REFRESH".equals(action)) {
                this.this$0.pager.invalidateCwCache();
                if (LoMoViewPagerAdapter$Type.CW.equals(this.this$0.state)) {
                    Log.v("LoMoViewPagerAdapter", "Reloading cw row");
                    this.this$0.refresh(this.this$0.loMo, this.this$0.listViewPos);
                }
            }
            else if ("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH".equals(action)) {
                this.this$0.pager.invalidateIqCache();
                if (LoMoViewPagerAdapter$Type.IQ.equals(this.this$0.state)) {
                    Log.v("LoMoViewPagerAdapter", "Reloading iq row");
                    this.this$0.refresh(this.this$0.loMo, this.this$0.listViewPos);
                    return;
                }
                if (this.this$0.loMo != null && this.this$0.loMo.getType().equals(LoMoType.INSTANT_QUEUE)) {
                    Log.v("LoMoViewPagerAdapter", "Reloading iq row because lomo types match");
                    this.this$0.refresh(this.this$0.loMo, this.this$0.listViewPos);
                }
            }
            else if ("com.netflix.mediaclient.intent.action.BA_POPULAR_TITLES_REFRESH".equals(action)) {
                this.this$0.pager.invalidatePopularTitlesCache();
                if (LoMoViewPagerAdapter$Type.KUBRICK_KIDS_POPULAR.equals(this.this$0.state)) {
                    Log.v("LoMoViewPagerAdapter", "Reloading popular titles row");
                    this.this$0.refresh(this.this$0.loMo, this.this$0.listViewPos);
                }
            }
        }
    }
}
