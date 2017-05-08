// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup;
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
import com.viewpagerindicator.android.osp.ViewPager$PageTransformer;
import android.support.v4.view.PagerAdapter;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.view.View;
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
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.graphics.Typeface;
import android.widget.TextView;
import android.support.design.widget.TabLayout$Tab;
import android.support.design.widget.TabLayout$OnTabSelectedListener;

class MementoFrag$TabListener implements TabLayout$OnTabSelectedListener
{
    final /* synthetic */ MementoFrag this$0;
    
    MementoFrag$TabListener(final MementoFrag this$0) {
        this.this$0 = this$0;
    }
    
    private void setPagerPositionOnTabSelect(final TabLayout$Tab tabLayout$Tab) {
        if (!this.this$0.resetPager) {
            this.this$0.resetPager = true;
        }
        else {
            final int position = tabLayout$Tab.getPosition();
            if (position == 0) {
                this.this$0.pager.setCurrentItem(0, false);
                this.this$0.resetTranslation(0);
                return;
            }
            if (position == 1) {
                this.this$0.pager.setCurrentItem(this.this$0.actors.size(), false);
                this.this$0.resetTranslation(this.this$0.actors.size());
            }
        }
    }
    
    @Override
    public void onTabReselected(final TabLayout$Tab pagerPositionOnTabSelect) {
        this.setPagerPositionOnTabSelect(pagerPositionOnTabSelect);
    }
    
    @Override
    public void onTabSelected(final TabLayout$Tab pagerPositionOnTabSelect) {
        pagerPositionOnTabSelect.getCustomView().findViewById(2131690013).setVisibility(0);
        final TextView textView = (TextView)pagerPositionOnTabSelect.getCustomView().findViewById(2131690014);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        this.setPagerPositionOnTabSelect(pagerPositionOnTabSelect);
        if (this.this$0.isActivityValid()) {
            if (this.this$0.getResources().getString(2131231071).compareToIgnoreCase(textView.getText().toString()) == 0) {
                UIViewLogUtils.reportUIViewCommand((Context)this.this$0.getActivity(), UIViewLogging$UIViewCommandName.mementoTabCast, IClientLogging$ModalView.memento, this.this$0.getNetflixActivity().getDataContext());
                return;
            }
            if (this.this$0.getResources().getString(2131231073).compareToIgnoreCase(textView.getText().toString()) == 0) {
                UIViewLogUtils.reportUIViewCommand((Context)this.this$0.getActivity(), UIViewLogging$UIViewCommandName.mementoTabRelated, IClientLogging$ModalView.memento, this.this$0.getNetflixActivity().getDataContext());
            }
        }
    }
    
    @Override
    public void onTabUnselected(final TabLayout$Tab tabLayout$Tab) {
        tabLayout$Tab.getCustomView().findViewById(2131690013).setVisibility(4);
        ((TextView)tabLayout$Tab.getCustomView().findViewById(2131690014)).setTypeface(Typeface.DEFAULT);
    }
}
