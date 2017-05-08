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
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
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
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.support.design.widget.TabLayout;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.model.branches.MementoVideoSwatch;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.android.osp.ViewPager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.model.branches.FalkorPerson;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.view.View;

class MementoFrag$1 implements Runnable
{
    final /* synthetic */ MementoFrag this$0;
    final /* synthetic */ int val$pagerIndex;
    final /* synthetic */ boolean val$showRDP;
    
    MementoFrag$1(final MementoFrag this$0, final int val$pagerIndex, final boolean val$showRDP) {
        this.this$0 = this$0;
        this.val$pagerIndex = val$pagerIndex;
        this.val$showRDP = val$showRDP;
    }
    
    @Override
    public void run() {
        this.this$0.pager.setCurrentItem(this.val$pagerIndex, false);
        if (this.val$showRDP) {
            final View viewWithTag = this.this$0.pager.findViewWithTag((Object)("POS_TAG" + String.valueOf(this.val$pagerIndex)));
            if (viewWithTag != null) {
                final View viewById = viewWithTag.findViewById(2131689972);
                if (viewById != null) {
                    viewById.performClick();
                }
            }
        }
    }
}
