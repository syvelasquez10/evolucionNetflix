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
import android.view.View$OnTouchListener;
import android.view.View;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.database.DataSetObserver;

public class LoLoMoFocusHandler extends DataSetObserver
{
    private static final boolean LEFT = false;
    private static final boolean LOG_VERBOSE = false;
    private static final boolean RIGHT = true;
    private static final String TAG = "LoLoMoFocusHandler";
    private static final int VIEW_ID_ROW_MULTIPLIER = 1000;
    private static boolean viewIdsValidated;
    private final NetflixActivity activity;
    private int col;
    private final StickyListHeadersListView listView;
    private View prevFocusView;
    private int row;
    private boolean touchEnabled;
    
    LoLoMoFocusHandler(final NetflixActivity activity, final StickyListHeadersListView listView) {
        this.touchEnabled = true;
        this.validateViewIdsIfNecessary();
        this.activity = activity;
        (this.listView = listView).setOnTouchListener((View$OnTouchListener)new LoLoMoFocusHandler$1(this));
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
        if (view == null) {
            return null;
        }
        ViewParent viewParent2;
        final ViewParent viewParent = viewParent2 = view.getParent();
        if (!(viewParent instanceof VideoViewGroup)) {
            viewParent2 = viewParent.getParent();
        }
        VideoViewGroup<?, ?> videoViewGroup;
        if (viewParent2 instanceof VideoViewGroup) {
            videoViewGroup = (VideoViewGroup<?, ?>)viewParent2;
        }
        else {
            videoViewGroup = null;
        }
        return videoViewGroup;
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
        while (true) {
            Label_0176: {
                switch (n) {
                    default: {
                        Log.w("LoLoMoFocusHandler", "Unknown keyCode");
                        break;
                    }
                    case 20: {
                        this.row = Math.min(this.row + 1, this.listView.getAdapter().getCount() - 1);
                        final int n2 = 0;
                        break Label_0070;
                    }
                    case 19: {
                        this.row = Math.max(0, this.row - 1);
                        final int n2 = 0;
                        break Label_0070;
                    }
                    case 21: {
                        if (this.canScrollHorizontally(false)) {
                            --this.col;
                            final int n2 = 1;
                            break Label_0070;
                        }
                        break Label_0176;
                    }
                    case 22: {
                        if (this.canScrollHorizontally(true)) {
                            ++this.col;
                            final int n2 = 1;
                            break Label_0070;
                        }
                        break Label_0176;
                    }
                }
                return;
                int n2 = 0;
                if (n2 != 0) {
                    this.requestFocusAtCurrPos();
                    this.scrollHorizontallyIfRequired();
                    return;
                }
                if (n != 20) {
                    b = false;
                }
                if (this.handleVerticalKeyEvent(b)) {
                    this.scrollVertically(this.row);
                    return;
                }
                return;
            }
            final int n2 = 1;
            continue;
        }
    }
    
    private boolean handleVerticalKeyEvent(final boolean b) {
        if (this.prevFocusView == null) {
            this.prevFocusView = this.listView.getFocusedChild();
            if (this.prevFocusView == null) {
                this.prevFocusView = (View)this.listView;
            }
        }
        int n;
        if (b) {
            n = 130;
        }
        else {
            n = 33;
        }
        final View focusSearch = this.prevFocusView.focusSearch(n);
        if (focusSearch == null || focusSearch instanceof MenuItem) {
            return false;
        }
        final VideoViewGroup<?, ?> videoViewGroup = this.getVideoViewGroup(focusSearch);
        if (videoViewGroup == null) {
            return false;
        }
        this.col = this.computeColFromViewId(videoViewGroup.getChildAt(0).getId()) + this.col % videoViewGroup.getChildCount();
        this.requestFocusAtCurrPos();
        return true;
    }
    
    private void requestFocusAtCurrPos() {
        final View viewById = this.listView.findViewById(this.computeCurrViewId());
        if (viewById == null) {
            return;
        }
        viewById.requestFocus();
        this.prevFocusView = viewById;
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
    
    private void scrollVertically(final int n) {
        this.listView.smoothScrollToPositionFromTop(n, this.activity.getActionBarHeight());
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
