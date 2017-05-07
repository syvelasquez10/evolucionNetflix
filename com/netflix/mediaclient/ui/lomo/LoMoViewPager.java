// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.servicemgr.BasicLoMo;
import android.view.MotionEvent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v4.view.PagerAdapter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import com.viewpagerindicator.CirclePageIndicator;
import android.os.Handler;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter;
import com.netflix.mediaclient.android.fragment.CustomViewPager;

@SuppressLint({ "ViewConstructor" })
public class LoMoViewPager extends CustomViewPager implements LoMoRowContent
{
    private static final long ROTATE_TO_NEXT_VIEW_DELAY_MS;
    private static final String TAG = "LoMoViewPager";
    private final LoMoViewPagerAdapter adapter;
    private final Handler handler;
    private final CirclePageIndicator pageIndicator;
    private final Runnable rotateToNextViewRunnable;
    private boolean shouldAutoPaginate;
    private State state;
    private String stateKey;
    private final Map<String, Object> stateMap;
    
    static {
        ROTATE_TO_NEXT_VIEW_DELAY_MS = TimeUnit.MILLISECONDS.convert(15L, TimeUnit.SECONDS);
    }
    
    public LoMoViewPager(final LoLoMoFrag loLoMoFrag, final ServiceManager serviceManager, final CirclePageIndicator pageIndicator, final ViewRecycler viewRecycler, final View view, final boolean b) {
        super((Context)loLoMoFrag.getActivity());
        this.rotateToNextViewRunnable = new Runnable() {
            @Override
            public void run() {
                if (LoMoViewPager.this.getActivity().destroyed()) {
                    return;
                }
                int n;
                if ((n = LoMoViewPager.this.getCurrentItem() + 1) >= LoMoViewPager.this.adapter.getCount()) {
                    n = 0;
                }
                if (Log.isLoggable("LoMoViewPager", 2)) {
                    Log.v("LoMoViewPager", "Auto-rotating to next view, id: " + n);
                }
                LoMoViewPager.this.setCurrentItem(n, true, true);
                LoMoViewPager.this.handler.postDelayed((Runnable)this, LoMoViewPager.ROTATE_TO_NEXT_VIEW_DELAY_MS);
            }
        };
        this.stateMap = loLoMoFrag.getStateMap();
        ThreadUtils.assertOnMain();
        this.handler = new Handler();
        this.pageIndicator = pageIndicator;
        this.setAdapter(this.adapter = new LoMoViewPagerAdapter(this, serviceManager, viewRecycler, view, b));
    }
    
    private NetflixActivity getActivity() {
        return (NetflixActivity)this.getContext();
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
    
    private boolean restoreState(final String stateKey) {
        this.stateKey = stateKey;
        this.state = this.stateMap.get(this.stateKey);
        if (this.state == null) {
            Log.v("LoMoViewPager", "No state found for key: " + this.stateKey);
            return false;
        }
        if (Log.isLoggable("LoMoViewPager", 2)) {
            Log.v("LoMoViewPager", "Restoring state for key: " + this.stateKey + ", state: " + this.state);
        }
        this.adapter.restoreFromMemento(this.state.adapterMemento);
        this.adapter.notifyDataSetChanged();
        this.notifyDataSetChanged();
        this.setCurrentItem(this.state.currPage, false, false);
        return true;
    }
    
    private void saveState(final int currPage) {
        this.state = new State();
        this.state.currPage = currPage;
        this.state.adapterMemento = this.adapter.saveToMemento();
        if (Log.isLoggable("LoMoViewPager", 2)) {
            Log.v("LoMoViewPager", "Saving state: " + this.stateKey + ": " + this.state);
        }
        this.stateMap.put(this.stateKey, this.state);
    }
    
    private void updateAutoPagination(final boolean b) {
        if (Log.isLoggable("LoMoViewPager", 2)) {
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
        if (Log.isLoggable("LoMoViewPager", 2)) {
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
        this.stateMap.remove("CW");
    }
    
    public void invalidateIqCache() {
        this.stateMap.remove("IQ");
    }
    
    @Override
    public void invalidateRequestId() {
        if (this.adapter != null) {
            this.adapter.invalidateRequestId();
        }
    }
    
    public boolean isLoading() {
        if (Log.isLoggable("NflxLoading", 2)) {
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
        this.saveStateAndTrack(n);
    }
    
    public void onPause() {
        this.handler.removeCallbacks(this.rotateToNextViewRunnable);
    }
    
    public void onResume() {
        if (this.shouldAutoPaginate) {
            this.updateAutoPagination(true);
        }
    }
    
    @Override
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.handleTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
    
    @Override
    public void refresh(final BasicLoMo basicLoMo, final int n) {
        final boolean b = true;
        ThreadUtils.assertOnMain();
        this.setPagesToOverlap(basicLoMo.getType() != LoMoType.BILLBOARD);
        this.updateAutoPagination(this.shouldAutoPaginate = (basicLoMo.getType() == LoMoType.BILLBOARD && b));
        LoMoViewPagerAdapter.Type type;
        if (basicLoMo.getType() == LoMoType.BILLBOARD) {
            type = LoMoViewPagerAdapter.Type.BILLBOARD;
        }
        else if (basicLoMo.getType() == LoMoType.CONTINUE_WATCHING) {
            type = LoMoViewPagerAdapter.Type.CW;
        }
        else if (basicLoMo.getType() == LoMoType.INSTANT_QUEUE) {
            type = LoMoViewPagerAdapter.Type.IQ;
        }
        else {
            type = LoMoViewPagerAdapter.Type.STANDARD;
        }
        Log.v("LoMoViewPager", "Setting layout params for: " + type);
        this.setLayoutParams((ViewGroup$LayoutParams)this.adapter.getLayoutParams(type));
        if (this.restoreState(basicLoMo.getId())) {
            this.adapter.trackPresentation(this.state.currPage);
            return;
        }
        this.adapter.refresh(type, basicLoMo, n);
        this.updatePageIndicatorVisibility();
    }
    
    void saveStateAndTrack(final int n) {
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
    
    public static class State
    {
        LoMoViewPagerAdapter.Memento adapterMemento;
        int currPage;
        
        @Override
        public String toString() {
            return "Page: " + this.currPage + ", adapter: " + this.adapterMemento.toString();
        }
    }
}
