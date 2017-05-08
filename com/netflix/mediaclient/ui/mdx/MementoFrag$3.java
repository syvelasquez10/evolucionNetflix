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
import android.support.v4.view.PagerAdapter;
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
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;

class MementoFrag$3 implements ViewPager$OnPageChangeListener
{
    final /* synthetic */ MementoFrag this$0;
    
    MementoFrag$3(final MementoFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onPageScrollStateChanged(final int n) {
    }
    
    @Override
    public void onPageScrolled(final int n, final float n2, final int n3) {
    }
    
    @Override
    public void onPageSelected(final int n) {
        this.this$0.toggleTabs(n);
        final View viewWithTag = this.this$0.pager.findViewWithTag((Object)("POS_TAG" + String.valueOf(n)));
        if (viewWithTag instanceof MementoFrag$ActorDetailsView) {
            this.this$0.currentTint = ((MementoFrag$ActorDetailsView)viewWithTag).getImageTint();
        }
    }
}
