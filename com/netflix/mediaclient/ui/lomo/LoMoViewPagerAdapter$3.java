// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.fragment.LoadingView;
import android.view.ViewGroup;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewParent;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import com.netflix.mediaclient.ui.lomo.discovery.PaginatedDiscoveryAdapter;
import android.content.Context;
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
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.lomo.discovery.DiscoveryBackgroundAnimator;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.EnumMap;
import android.support.v4.view.PagerAdapter;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
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
        Log.v("LoMoViewPagerAdapter", "Notified parent of data set change");
        if (LocalizationUtils.isCurrentLocaleRTL()) {
            final int n = this.this$0.currentAdapter.getCount() - 1;
            if (Log.isLoggable()) {
                Log.d("LoMoViewPagerAdapter", "Current locale is RTL, we got oour first data, set to last page " + n);
                Log.d("LoMoViewPagerAdapter", "Current page " + this.this$0.pager.getCurrentItem());
                Log.d("LoMoViewPagerAdapter", "Previous last page " + this.this$0.previousLastPage);
            }
            if (this.this$0.pager.getCurrentItem() == this.this$0.previousLastPage) {
                Log.d("LoMoViewPagerAdapter", "LOMO stayed on same last page, just adjust");
                this.this$0.pager.setCurrentItem(n, false, false);
            }
            else {
                final int n2 = this.this$0.pager.getCurrentItem() + n - this.this$0.previousLastPage;
                if (Log.isLoggable()) {
                    Log.d("LoMoViewPagerAdapter", "User moved from last page, adjust to page " + n2);
                }
                if (n2 >= 0) {
                    this.this$0.pager.setCurrentItem(n2, false, false);
                }
                else {
                    Log.d("LoMoViewPagerAdapter", "Wrong position, reset to end");
                    this.this$0.pager.setCurrentItem(n, false, false);
                }
            }
            this.this$0.previousLastPage = n;
        }
        else {
            Log.d("LoMoViewPagerAdapter", "Current locale is LTR...");
            if (this.this$0.state == LoMoViewPagerAdapter$Type.DISCOVERY) {
                this.this$0.pager.setCurrentItem(0, true);
            }
        }
        this.this$0.notifyDataSetChanged();
        this.this$0.pager.notifyDataSetChanged();
        if (this.this$0.pager.getCurrentItem() == 0) {
            Log.v("LoMoViewPagerAdapter", "Data loaded for page 0 - saving state");
            this.this$0.pager.saveStateAndTrackPresentation(0);
        }
    }
    
    @Override
    public void notifyParentOfError() {
        if (this.this$0.state != LoMoViewPagerAdapter$Type.ERROR) {
            this.this$0.preErrorState = this.this$0.state;
        }
        if (Log.isLoggable()) {
            Log.v("LoMoViewPagerAdapter", "Pre-error state: " + this.this$0.preErrorState);
        }
        this.this$0.setState(LoMoViewPagerAdapter$Type.ERROR);
        this.this$0.notifyDataSetChanged();
        this.this$0.showReloadViews();
    }
}
