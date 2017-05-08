// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.LayoutInflater;
import java.util.Iterator;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
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
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import android.support.v4.view.PagerAdapter;

class MementoFrag$4 extends PagerAdapter
{
    final /* synthetic */ MementoFrag this$0;
    
    MementoFrag$4(final MementoFrag this$0) {
        this.this$0 = this$0;
    }
    
    View createActorView(final int n) {
        final MementoFrag$ActorDetailsView mementoFrag$ActorDetailsView = new MementoFrag$ActorDetailsView(this.this$0, (Context)this.this$0.getActivity());
        mementoFrag$ActorDetailsView.updateDetails(n);
        return (View)mementoFrag$ActorDetailsView;
    }
    
    View createRelatedTitleView(final int n) {
        final MementoFrag$RelatedTitleView mementoFrag$RelatedTitleView = new MementoFrag$RelatedTitleView(this.this$0, (Context)this.this$0.getActivity());
        mementoFrag$RelatedTitleView.updateDetails(n);
        return (View)mementoFrag$RelatedTitleView;
    }
    
    @Override
    public void destroyItem(final ViewGroup viewGroup, final int n, final Object o) {
        viewGroup.removeView((View)o);
    }
    
    @Override
    public int getCount() {
        int size = 0;
        int size2;
        if (this.this$0.actors == null) {
            size2 = 0;
        }
        else {
            size2 = this.this$0.actors.size();
        }
        if (this.this$0.relatedTitles != null) {
            size = this.this$0.relatedTitles.size();
        }
        return size2 + size;
    }
    
    @Override
    public int getItemPosition(final Object o) {
        return -2;
    }
    
    @Override
    public Object instantiateItem(final ViewGroup viewGroup, final int n) {
        View view = null;
        if (n < this.this$0.actors.size()) {
            view = this.createActorView(n);
            viewGroup.addView(view);
        }
        else if (n < this.this$0.relatedTitles.size() + this.this$0.actors.size()) {
            view = this.createRelatedTitleView(n - this.this$0.actors.size());
            viewGroup.addView(view);
        }
        view.setTag((Object)("POS_TAG" + String.valueOf(n)));
        return view;
    }
    
    @Override
    public boolean isViewFromObject(final View view, final Object o) {
        return view == o;
    }
}
