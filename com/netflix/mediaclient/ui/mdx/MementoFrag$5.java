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
import android.widget.ImageView;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Handler;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import android.support.design.widget.TabLayout$OnTabSelectedListener;
import android.widget.TextView;
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
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import android.view.View;
import com.viewpagerindicator.android.osp.ViewPager$PageTransformer;

class MementoFrag$5 implements ViewPager$PageTransformer
{
    final /* synthetic */ MementoFrag this$0;
    
    MementoFrag$5(final MementoFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void transformPage(final View view, final float n) {
        view.setAlpha(Math.max(Math.abs(Math.abs(n) - 1.0f), 0.75f));
        if (n <= 1.0f) {
            View view2;
            if ((view2 = view.findViewById(2131689944)) == null) {
                view2 = view.findViewById(2131689949);
            }
            if (view2 != null) {
                view2.setTranslationX(-n * (BarkerUtils.getDetailsPageContentWidth(this.this$0.pager.getContext()) / 2));
            }
        }
    }
}
