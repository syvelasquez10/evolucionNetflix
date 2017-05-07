// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.Log;
import android.view.MotionEvent;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoHeroDuplicate;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v4.view.PagerAdapter;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import com.viewpagerindicator.CirclePageIndicator;
import android.os.Handler;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$LoMoRowContent;
import com.netflix.mediaclient.android.fragment.CustomViewPager;

@SuppressLint({ "ViewConstructor" })
public class LoMoViewPager extends CustomViewPager implements BaseLoLoMoAdapter$LoMoRowContent
{
    private static final String CW_CACHE_KEY = "cw";
    public static final boolean DO_NOT_OVERLAP_PAGES_CONST = false;
    private static final String IQ_CACHE_KEY = "iq";
    private static final String IQ_DUPLICATE_CACHE_KEY = "iq_duplicate";
    private static final float KIDS_TOUCH_SLOP_SCALE_FACTOR = 0.75f;
    public static final boolean OVERLAP_PAGES_CONST = true;
    private static final String POPULAR_TITLES_CACHE_KEY = "pt";
    private static final String POPULAR_TITLES_DUPLICATE_CACHE_KEY = "ptd";
    private static final long ROTATE_TO_NEXT_VIEW_DELAY_MS;
    private static final String TAG = "LoMoViewPager";
    private final LoMoViewPagerAdapter adapter;
    private final Handler handler;
    private final CirclePageIndicator pageIndicator;
    private final Runnable rotateToNextViewRunnable;
    private boolean shouldAutoPaginate;
    private LoMoViewPager$State state;
    private String stateKey;
    private final Map<String, Object> stateMap;
    
    static {
        ROTATE_TO_NEXT_VIEW_DELAY_MS = TimeUnit.MILLISECONDS.convert(15L, TimeUnit.SECONDS);
    }
    
    public LoMoViewPager(final LoLoMoFrag loLoMoFrag, final ServiceManager serviceManager, final CirclePageIndicator pageIndicator, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final View view, final boolean b) {
        super((Context)loLoMoFrag.getActivity());
        this.rotateToNextViewRunnable = new LoMoViewPager$1(this);
        this.stateMap = loLoMoFrag.getStateMap();
        ThreadUtils.assertOnMain();
        this.handler = new Handler();
        this.pageIndicator = pageIndicator;
        this.setAdapter(this.adapter = new LoMoViewPagerAdapter(this, serviceManager, objectRecycler$ViewRecycler, view, b));
        if (BrowseExperience.isKubrickKids()) {
            this.setTouchSlop((int)(this.getTouchSlop() * 0.75f));
        }
    }
    
    public static int computeViewPagerWidth(final NetflixActivity netflixActivity, final boolean b) {
        return computeViewPagerWidth(netflixActivity, b, LoMoUtils$LoMoWidthType.STANDARD);
    }
    
    public static int computeViewPagerWidth(final NetflixActivity netflixActivity, final boolean b, final LoMoUtils$LoMoWidthType loMoUtils$LoMoWidthType) {
        if (b) {
            return DeviceUtils.getScreenWidthInPixels((Context)netflixActivity) - LoMoUtils.getLomoFragOffsetLeftPx(netflixActivity) - LoMoUtils.getLomoFragOffsetRightPx(netflixActivity, loMoUtils$LoMoWidthType);
        }
        return DeviceUtils.getScreenWidthInPixels((Context)netflixActivity);
    }
    
    private NetflixActivity getActivity() {
        return (NetflixActivity)this.getContext();
    }
    
    private String getStateKeyForLomo(final BasicLoMo basicLoMo) {
        switch (LoMoViewPager$2.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[basicLoMo.getType().ordinal()]) {
            default: {
                return String.valueOf(basicLoMo.hashCode());
            }
            case 1: {
                return "cw";
            }
            case 2: {
                if (basicLoMo instanceof KubrickLoMoHeroDuplicate) {
                    return "iq_duplicate";
                }
                return "iq";
            }
            case 3: {
                if (basicLoMo instanceof KubrickLoMoHeroDuplicate) {
                    return "ptd";
                }
                return "pt";
            }
        }
    }
    
    private void handleTouchEvent(final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            default: {}
            case 0: {
                this.updateAutoPagination(false);
            }
            case 1:
            case 3: {
                this.updateAutoPagination(this.shouldAutoPaginate);
            }
        }
    }
    
    private void onCurrentItemSet(final int currentItem) {
        Log.v("LoMoViewPager", "onCurrentItemSet: " + currentItem);
        if (this.pageIndicator != null) {
            this.pageIndicator.setCurrentItem(currentItem);
        }
    }
    
    private boolean restoreState(final BasicLoMo basicLoMo) {
        this.stateKey = this.getStateKeyForLomo(basicLoMo);
        this.state = this.stateMap.get(this.stateKey);
        if (this.state == null) {
            Log.v("LoMoViewPager", "No state found for key: " + this.stateKey);
            return false;
        }
        if (Log.isLoggable()) {
            Log.v("LoMoViewPager", "Restoring state for key: " + this.stateKey + ", state: " + this.state);
        }
        this.adapter.restoreFromMemento(this.state.adapterMemento);
        this.setPagesToOverlap(this.adapter.shouldOverlapPages(), basicLoMo.getType(), this.adapter.getState());
        this.adapter.notifyDataSetChanged();
        this.notifyDataSetChanged();
        this.setCurrentItem(this.state.currPage, false, false);
        return true;
    }
    
    private void saveState(final int currPage) {
        this.state = new LoMoViewPager$State();
        this.state.currPage = currPage;
        this.state.adapterMemento = this.adapter.saveToMemento();
        if (Log.isLoggable()) {
            Log.v("LoMoViewPager", "Saving state: " + this.stateKey + ": " + this.state);
        }
        this.stateMap.put(this.stateKey, this.state);
    }
    
    private void setPagesToOverlap(final boolean b, final LoMoType loMoType, final LoMoViewPagerAdapter$Type loMoViewPagerAdapter$Type) {
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        final LoMoUtils$LoMoWidthType standard = LoMoUtils$LoMoWidthType.STANDARD;
        LoMoUtils$LoMoWidthType loMoUtils$LoMoWidthType;
        if (loMoViewPagerAdapter$Type == LoMoViewPagerAdapter$Type.KUBRICK_KIDS_TOP_TEN) {
            loMoUtils$LoMoWidthType = LoMoUtils$LoMoWidthType.KUBRICK_KIDS_TOP_TEN_ROW;
        }
        else if (BrowseExperience.isKubrickKids() && loMoType == LoMoType.CHARACTERS) {
            loMoUtils$LoMoWidthType = LoMoUtils$LoMoWidthType.KUBRICK_KIDS_CHARACTER_ROW;
        }
        else {
            loMoUtils$LoMoWidthType = standard;
            if (BrowseExperience.isKubrick()) {
                loMoUtils$LoMoWidthType = standard;
                if (loMoType == LoMoType.CONTINUE_WATCHING) {
                    loMoUtils$LoMoWidthType = KubrickUtils.getCwGalleryWidthType(netflixActivity);
                }
            }
        }
        int pageMargin = -(LoMoUtils.getLomoFragOffsetRightPx(netflixActivity, loMoUtils$LoMoWidthType) + LoMoUtils.getLomoFragOffsetLeftPx(netflixActivity));
        if (!b) {
            pageMargin = 0;
        }
        this.setPageMargin(pageMargin);
    }
    
    private void updateAutoPagination(final boolean b) {
        if (Log.isLoggable()) {
            Log.v("LoMoViewPager", "updateAutoPagination, enabled: " + b);
        }
        this.handler.removeCallbacks(this.rotateToNextViewRunnable);
        if (b) {
            this.handler.postDelayed(this.rotateToNextViewRunnable, LoMoViewPager.ROTATE_TO_NEXT_VIEW_DELAY_MS);
        }
    }
    
    private void updatePageIndicatorVisibility() {
        boolean b = true;
        int visibility = 0;
        if (!this.adapter.isShowingBillboard() || this.adapter.getCount() <= 1) {
            b = false;
        }
        if (Log.isLoggable()) {
            Log.v("LoMoViewPager", "hasBillboardData: " + b);
        }
        if (this.pageIndicator != null) {
            final CirclePageIndicator pageIndicator = this.pageIndicator;
            if (!b) {
                visibility = 8;
            }
            pageIndicator.setVisibility(visibility);
        }
    }
    
    public void destroy() {
        this.adapter.destroy();
    }
    
    public void invalidateCwCache() {
        Log.v("LoMoViewPager", "Invalidating CW cache");
        this.stateMap.remove("cw");
    }
    
    public void invalidateIqCache() {
        Log.v("LoMoViewPager", "Invalidating IQ cache");
        this.stateMap.remove("iq");
        this.stateMap.remove("iq_duplicate");
    }
    
    public void invalidatePopularTitlesCache() {
        Log.v("LoMoViewPager", "Invalidating popular titles cache");
        this.stateMap.remove("pt");
        this.stateMap.remove("ptd");
    }
    
    public boolean isLoading() {
        if (Log.isLoggable()) {
            Log.v("NflxLoading", "Class: " + this.getClass().getSimpleName() + ", loading: " + this.adapter.isLoading());
        }
        return this.adapter.isLoading();
    }
    
    public void notifyDataSetChanged() {
        Log.v("LoMoViewPager", "Notifying pager indicator of data set change");
        if (this.pageIndicator != null) {
            this.pageIndicator.notifyDataSetChanged();
        }
        this.updatePageIndicatorVisibility();
    }
    
    @Override
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.handleTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }
    
    @Override
    protected void onPageSelected(final int n) {
        super.onPageSelected(n);
        Log.v("LoMoViewPager", "onPageSelected: " + n);
        this.saveStateAndTrackPresentation(n);
    }
    
    public void onPause() {
        this.handler.removeCallbacks(this.rotateToNextViewRunnable);
    }
    
    public void onResume() {
        if (this.shouldAutoPaginate) {
            this.updateAutoPagination(true);
        }
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    @Override
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.handleTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
    
    @Override
    public void onViewMovedToScrapHeap() {
        this.handler.removeCallbacks(this.rotateToNextViewRunnable);
        if (this.adapter != null) {
            this.adapter.invalidateRequestId();
        }
    }
    
    @Override
    public void refresh(final BasicLoMo basicLoMo, final int n) {
        ThreadUtils.assertOnMain();
        if (Log.isLoggable()) {
            Log.v("LoMoViewPager", "Setting layout params for: " + basicLoMo.getType() + ", listViewPos: " + n);
        }
        if (this.restoreState(basicLoMo)) {
            this.adapter.trackPresentation(this.state.currPage);
        }
        else {
            this.adapter.refresh(basicLoMo, n);
            this.setPagesToOverlap(this.adapter.shouldOverlapPages(), basicLoMo.getType(), this.adapter.getState());
            this.updatePageIndicatorVisibility();
        }
        this.setLayoutParams((ViewGroup$LayoutParams)this.adapter.createLayoutParams());
        this.updateAutoPagination(this.shouldAutoPaginate = (basicLoMo.getType() == LoMoType.BILLBOARD));
    }
    
    void saveStateAndTrackPresentation(final int n) {
        this.saveState(n);
        this.adapter.trackPresentation(n);
    }
    
    @Override
    public void setCurrentItem(final int currentItem) {
        super.setCurrentItem(currentItem);
        this.onCurrentItemSet(currentItem);
    }
    
    @Override
    public void setCurrentItem(final int n, final boolean b) {
        super.setCurrentItem(n, b);
        this.onCurrentItemSet(n);
    }
    
    @Override
    public void setCurrentItem(final int n, final boolean b, final boolean b2) {
        super.setCurrentItem(n, b, b2);
        this.onCurrentItemSet(n);
    }
}
