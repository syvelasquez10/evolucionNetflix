// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.view.KeyEvent;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.MenuItem;
import com.netflix.mediaclient.Log;
import com.viewpagerindicator.android.osp.ViewPager;
import android.view.ViewParent;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView$OnScrollListener;
import android.widget.ListView;
import android.database.DataSetObserver;

public class LoLoMoFocusHandler extends DataSetObserver
{
    private static final boolean LEFT = false;
    private static final boolean LOG_VERBOSE = false;
    private static final boolean RIGHT = true;
    private static final String TAG = "LoLoMoFocusHandler";
    private static final int VERTICAL_SCROLL_POSITION_OFFSET_PX = 16;
    private static final int VIEW_ID_ROW_MULTIPLIER = 1000;
    private static boolean viewIdsValidated;
    private int col;
    private final ListView listView;
    private int row;
    private boolean touchEnabled;
    
    LoLoMoFocusHandler(final ListView listView) {
        this.touchEnabled = true;
        this.validateViewIdsIfNecessary();
        (this.listView = listView).setOnScrollListener((AbsListView$OnScrollListener)new AbsListView$OnScrollListener() {
            public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
            }
            
            public void onScrollStateChanged(final AbsListView absListView, final int n) {
                if (!LoLoMoFocusHandler.this.touchEnabled) {
                    LoLoMoFocusHandler.this.requestFocusAtCurrPos();
                }
            }
        });
        listView.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                LoLoMoFocusHandler.this.touchEnabled = true;
                return false;
            }
        });
    }
    
    private boolean canScrollHorizontally(final boolean b) {
        int n;
        if (b) {
            n = this.col + 1;
        }
        else {
            n = this.col - 1;
        }
        return this.listView.findViewById(computeViewId(this.row, n)) != null;
    }
    
    private int computeColFromViewId(final int n) {
        return n % 1000;
    }
    
    private int computeCurrViewId() {
        return computeViewId(this.row, this.col);
    }
    
    public static int computeViewId(final int n, final int n2) {
        return (n + 1) * 1000 + n2;
    }
    
    private boolean disableTouchMode() {
        if (this.touchEnabled) {
            this.touchEnabled = false;
            this.requestFocusAtCurrPos();
            return false;
        }
        return true;
    }
    
    private VideoViewGroup<?, ?> getVideoViewGroup(final View view) {
        if (view != null) {
            ViewParent viewParent2;
            final ViewParent viewParent = viewParent2 = view.getParent();
            if (!(viewParent instanceof VideoViewGroup)) {
                viewParent2 = viewParent.getParent();
            }
            if (viewParent2 instanceof VideoViewGroup) {
                return (VideoViewGroup<?, ?>)viewParent2;
            }
        }
        return null;
    }
    
    private ViewPager getViewPager(final View view) {
        final VideoViewGroup<?, ?> videoViewGroup = this.getVideoViewGroup(view);
        if (videoViewGroup == null) {
            return null;
        }
        return (ViewPager)videoViewGroup.getParent();
    }
    
    private void handleDpadEvent(final int n) {
        boolean b = true;
        boolean b2 = false;
        switch (n) {
            default: {
                Log.w("LoLoMoFocusHandler", "Unknown keyCode");
                return;
            }
            case 20: {
                this.row = Math.min(this.row + 1, this.listView.getAdapter().getCount() - 1);
                break;
            }
            case 19: {
                this.row = Math.max(0, this.row - 1);
                break;
            }
            case 21: {
                b2 = true;
                if (this.canScrollHorizontally(false)) {
                    --this.col;
                    b2 = b2;
                    break;
                }
                break;
            }
            case 22: {
                final boolean b3 = b2 = true;
                if (this.canScrollHorizontally(true)) {
                    ++this.col;
                    b2 = b3;
                    break;
                }
                break;
            }
        }
        if (b2) {
            this.scrollHorizontallyIfRequired();
            this.requestFocusAtCurrPos();
            return;
        }
        this.scrollVertically(this.listView, this.row);
        if (n != 20) {
            b = false;
        }
        this.handleVerticalKeyEvent(b);
    }
    
    private void handleVerticalKeyEvent(final boolean b) {
        final ListView listView = this.listView;
        int n;
        if (b) {
            n = 130;
        }
        else {
            n = 33;
        }
        final View focusSearch = listView.focusSearch(n);
        if (focusSearch != null && !(focusSearch instanceof MenuItem)) {
            final VideoViewGroup<?, ?> videoViewGroup = this.getVideoViewGroup(focusSearch);
            if (videoViewGroup != null) {
                this.col = this.computeColFromViewId(videoViewGroup.getChildAt(0).getId()) + this.col % videoViewGroup.getChildCount();
                this.requestFocusAtCurrPos();
            }
        }
    }
    
    private void requestFocusAtCurrPos() {
        final View viewById = this.listView.findViewById(this.computeCurrViewId());
        if (viewById == null) {
            return;
        }
        viewById.requestFocus();
    }
    
    private void scrollHorizontallyIfRequired() {
        final View viewById = this.listView.findViewById(this.computeCurrViewId());
        final ViewPager viewPager = this.getViewPager(viewById);
        if (viewPager == null) {
            return;
        }
        final int[] array = new int[2];
        viewById.getLocationOnScreen(array);
        final int n = array[0];
        final int width = viewById.getWidth();
        final int screenWidthInPixels = DeviceUtils.getScreenWidthInPixels(this.listView.getContext());
        final int currentItem = viewPager.getCurrentItem();
        int n2;
        if (n < 0) {
            n2 = currentItem - 1;
        }
        else {
            n2 = currentItem;
            if (n + width > screenWidthInPixels) {
                n2 = currentItem + 1;
            }
        }
        viewPager.setCurrentItem(n2, true, true);
    }
    
    private void scrollVertically(final ListView listView, final int n) {
        listView.smoothScrollToPositionFromTop(n, 16);
    }
    
    private void validateViewIdsIfNecessary() {
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            switch (keyEvent.getKeyCode()) {
                case 19:
                case 20:
                case 21:
                case 22: {
                    if (this.disableTouchMode()) {
                        this.handleDpadEvent(keyEvent.getKeyCode());
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public void onChanged() {
        super.onChanged();
    }
}
