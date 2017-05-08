// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.Iterator;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import android.widget.ImageView;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Handler;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import android.support.design.widget.TabLayout$OnTabSelectedListener;
import android.widget.TextView;
import com.viewpagerindicator.android.osp.ViewPager$PageTransformer;
import android.support.v4.view.PagerAdapter;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.ArrayList;
import android.support.design.widget.TabLayout;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.android.osp.ViewPager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.Log;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.branches.MementoVideoSwatch;
import com.netflix.model.branches.FalkorPerson;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class MementoFrag$FetchActorDetailsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ MementoFrag this$0;
    
    public MementoFrag$FetchActorDetailsCallback(final MementoFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("MementoFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onActorDetailsAndRelatedFetched(final List<FalkorPerson> list, final List<MementoVideoSwatch> list2, final Status status, final List<FalkorActorStill> list3) {
        final boolean b = true;
        boolean b2 = false;
        super.onActorDetailsAndRelatedFetched(list, list2, status, list3);
        if (this.requestId != this.this$0.requestId || this.this$0.isDestroyed()) {
            Log.v("MementoFrag", "Ignoring stale callback");
        }
        else {
            this.this$0.isLoading = false;
            if (status.isError()) {
                Log.w("MementoFrag", "Invalid status code");
                this.this$0.leWrapper.showErrorView(false);
                return;
            }
            if (list != null) {
                this.this$0.actors = list;
                b2 = true;
            }
            if (list2 != null) {
                this.this$0.relatedTitles = list2;
                b2 = true;
            }
            if (list3 != null) {
                this.this$0.stills = list3;
                b2 = b;
            }
            if (b2) {
                this.this$0.showStandardViews();
            }
        }
    }
}
