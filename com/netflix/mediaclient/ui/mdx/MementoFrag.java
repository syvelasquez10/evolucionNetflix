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

public class MementoFrag extends NetflixFrag implements ErrorWrapper$Callback
{
    public static final String PAGER_INDEX = "pagerIndex";
    public static final String POS_TAG = "POS_TAG";
    public static final String SHOW_RDP = "showRDP";
    private static final String TAG = "MementoFrag";
    private List<FalkorPerson> actors;
    private int currentTint;
    protected final ErrorWrapper$Callback errorCallback;
    private boolean isLoading;
    private LoadingAndErrorWrapper leWrapper;
    private ViewPager pager;
    private CirclePageIndicator pagerIndicator;
    private List<MementoVideoSwatch> relatedTitles;
    private long requestId;
    boolean resetPager;
    private List<FalkorActorStill> stills;
    private TabLayout tabLayout;
    private int[] tintColors;
    private int tintIndex;
    private String videoId;
    
    public MementoFrag() {
        this.relatedTitles = new ArrayList<MementoVideoSwatch>();
        this.actors = new ArrayList<FalkorPerson>();
        this.stills = new ArrayList<FalkorActorStill>();
        this.tintColors = new int[] { -1157627904, -1141940691, -1148934082, -1142908633, -1156678016 };
        this.tintIndex = -1;
        this.currentTint = this.tintColors[0];
        this.errorCallback = new MementoFrag$2(this);
        this.resetPager = true;
    }
    
    private void fetchActorDetailsAndRelated() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.w("MementoFrag", "Manager is null - can't reload data");
            return;
        }
        this.isLoading = true;
        this.requestId = System.nanoTime();
        this.hideStandardViews();
        Log.v("MementoFrag", "Fetching data for actor ID: " + this.videoId);
        serviceManager.getBrowse().fetchActorDetailsAndRelatedForTitle(this.videoId, new MementoFrag$FetchActorDetailsCallback(this, this.requestId));
    }
    
    private void findViews(final View view) {
        this.pagerIndicator = (CirclePageIndicator)view.findViewById(2131689982);
        this.tabLayout = (TabLayout)view.findViewById(2131689983);
        this.pager = (ViewPager)view.findViewById(2131689981);
    }
    
    private void hideStandardViews() {
        if (this.pager != null) {
            this.pager.setVisibility(4);
        }
        if (this.pagerIndicator != null) {
            this.pagerIndicator.setVisibility(4);
            this.pagerIndicator.setCurrentItem(0);
        }
        if (this.pager != null) {
            this.pager.setCurrentItem(0);
        }
        if (this.leWrapper != null) {
            this.leWrapper.showLoadingView(true);
        }
        if (this.tabLayout != null) {
            this.tabLayout.setEnabled(false);
            this.tabLayout.setVisibility(0);
            this.tabLayout.setAlpha(0.5f);
            this.tabLayout.getTabAt(0).select();
        }
    }
    
    private void init(final View view) {
        this.initTabs();
        this.initPager();
        this.leWrapper = new LoadingAndErrorWrapper(view, this.errorCallback);
    }
    
    private void initPager() {
        this.pagerIndicator.setOnPageChangeListener(new MementoFrag$3(this));
        final MementoFrag$4 adapter = new MementoFrag$4(this);
        if (this.pager != null) {
            this.pager.setAdapter(adapter);
            this.pager.setPageTransformer(false, new MementoFrag$5(this));
        }
        if (this.pagerIndicator != null) {
            this.pagerIndicator.setViewPager(this.pager);
        }
    }
    
    private void initTabs() {
        if (this.tabLayout != null) {
            this.tabLayout.removeAllTabs();
            this.tabLayout.addTab(this.tabLayout.newTab().setCustomView(2130903199), 0, true);
            this.tabLayout.addTab(this.tabLayout.newTab().setCustomView(2130903199), 1, true);
            ((TextView)this.tabLayout.getTabAt(0).getCustomView().findViewById(2131689986)).setText(2131231055);
            ((TextView)this.tabLayout.getTabAt(1).getCustomView().findViewById(2131689986)).setText(2131231057);
            this.tabLayout.setOnTabSelectedListener(new MementoFrag$TabListener(this));
        }
    }
    
    private boolean isRDPShowing() {
        final RoleDetailsFrag roleDetailsFrag = (RoleDetailsFrag)this.getFragmentManager().findFragmentById(2131689997);
        return roleDetailsFrag != null && !roleDetailsFrag.isHidden();
    }
    
    private void resetTranslation(final int n) {
        if (this.pager != null) {
            final View viewWithTag = this.pager.findViewWithTag((Object)("POS_TAG" + String.valueOf(n)));
            if (viewWithTag != null) {
                View view;
                if ((view = viewWithTag.findViewById(2131689944)) == null) {
                    view = viewWithTag.findViewById(2131689949);
                }
                if (view != null) {
                    view.setTranslationX(0.0f);
                }
            }
        }
    }
    
    private void restoreInstanceState(final Bundle bundle) {
        if (bundle != null) {
            new Handler().postDelayed((Runnable)new MementoFrag$1(this, bundle.getInt("pagerIndex", 0), bundle.getBoolean("showRDP", false)), 1000L);
        }
    }
    
    private void showRDP(final String actorId) {
        if (this.isActivityValid() && this.getNetflixActivity().isPanelExpanded()) {
            final RoleDetailsFrag roleDetailsFrag = (RoleDetailsFrag)this.getFragmentManager().findFragmentById(2131689997);
            if (roleDetailsFrag != null) {
                final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
                beginTransaction.setCustomAnimations(2131034114, 2131034115);
                beginTransaction.show((Fragment)roleDetailsFrag);
                roleDetailsFrag.setStillImageHeight(this.pager.getMeasuredHeight());
                roleDetailsFrag.setImageTint(this.currentTint);
                roleDetailsFrag.setVideoId(this.videoId);
                roleDetailsFrag.setActorId(actorId);
                roleDetailsFrag.reload();
                beginTransaction.commitAllowingStateLoss();
                this.getNetflixActivity().setSlidingEnabled(false);
            }
        }
    }
    
    private void showStandardViews() {
        if (this.pagerIndicator != null) {
            this.pagerIndicator.setVisibility(0);
        }
        if (this.leWrapper != null) {
            this.leWrapper.hide(false);
        }
        if (this.pager != null) {
            this.pager.getAdapter().notifyDataSetChanged();
            this.pager.setVisibility(0);
        }
        if (this.tabLayout != null) {
            if (this.actors.size() <= 0 || this.relatedTitles.size() <= 0) {
                this.tabLayout.setEnabled(false);
                this.tabLayout.setAlpha(0.0f);
                this.tabLayout.setVisibility(8);
                return;
            }
            this.tabLayout.setEnabled(true);
            this.tabLayout.setAlpha(1.0f);
            this.tabLayout.setVisibility(0);
        }
    }
    
    private void toggleTabs(final int n) {
        if (this.tabLayout.getAlpha() == 1.0f) {
            if (n < this.actors.size()) {
                if (this.tabLayout.getSelectedTabPosition() != 0) {
                    this.resetPager = false;
                    this.tabLayout.getTabAt(0).select();
                }
            }
            else if (n < this.relatedTitles.size() + this.actors.size() && this.tabLayout.getSelectedTabPosition() != 1) {
                this.resetPager = false;
                this.tabLayout.getTabAt(1).select();
            }
        }
    }
    
    protected void adjustHeight(final ImageView imageView) {
        final float n = this.getActivity().getResources().getDimensionPixelOffset(2131362171);
        final float n2 = 1;
        final ViewGroup$LayoutParams layoutParams = imageView.getLayoutParams();
        final float n3 = (BarkerUtils.getDetailsPageContentWidth((Context)this.getActivity()) - n * (n2 + 1.0f)) / 1;
        float n4;
        if (DeviceUtils.isPortrait((Context)this.getActivity())) {
            n4 = 0.75f;
        }
        else {
            n4 = 0.5625f;
        }
        layoutParams.height = (int)(n4 * n3);
    }
    
    public void fetchData() {
        this.fetchActorDetailsAndRelated();
    }
    
    FalkorActorStill getStill(final String s, final String s2) {
        if (this.stills == null || s == null || s2 == null) {
            return null;
        }
        for (final FalkorActorStill falkorActorStill : this.stills) {
            if (falkorActorStill.summary.videoId != null && s.compareTo(falkorActorStill.summary.videoId) == 0 && s2.compareTo(falkorActorStill.summary.personId) == 0) {
                return falkorActorStill;
            }
        }
        return null;
    }
    
    public void hideLoading() {
        if (this.leWrapper != null) {
            this.leWrapper.hide(true);
        }
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("MementoFrag", "Creating new frag view...");
        final View inflate = layoutInflater.inflate(2130903197, (ViewGroup)null, false);
        this.findViews(inflate);
        this.init(inflate);
        this.restoreInstanceState(bundle);
        return inflate;
    }
    
    @Override
    public void onRetryRequested() {
        this.fetchActorDetailsAndRelated();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.isRDPShowing()) {
            bundle.putBoolean("showRDP", true);
        }
        bundle.putInt("pagerIndex", this.pager.getCurrentItem());
    }
    
    public void setVideoId(final String videoId) {
        this.videoId = videoId;
    }
}
