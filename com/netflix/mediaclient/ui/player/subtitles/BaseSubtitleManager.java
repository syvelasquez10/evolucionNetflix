// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.util.SubtitleUtils;
import java.util.Comparator;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import java.util.Iterator;
import android.app.Activity;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import java.util.Collections;
import java.util.ArrayList;
import android.view.View;
import com.netflix.mediaclient.ui.player.PlayScreen;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import android.os.Handler;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.ui.player.PlayerActivity;

abstract class BaseSubtitleManager implements SubtitleManager
{
    protected static final int H_REGION_PADDING = 5;
    protected static final int PLAYER_PADDING_PHONE = 46;
    protected static final int PLAYER_PADDING_TABLET = 59;
    protected static final String TAG = "nf_subtitles_render";
    protected static final int TOP_PADDING = 5;
    protected static final int V_REGION_PADDING = 1;
    protected PlayerActivity mActivity;
    protected int mBottomPanelHeight;
    protected int mBottomPanelPadding;
    protected RelativeLayout mDisplayArea;
    protected Handler mHandler;
    protected int mHorizontalRegionPadding;
    protected SubtitleParser mParser;
    protected List<Runnable> mPendingActions;
    protected PlayScreen mScreen;
    protected Integer mSubtitleParserId;
    protected View mTopPanel;
    protected int mTopPanelPadding;
    protected int mTransparent;
    protected int mVerticalRegionPadding;
    
    BaseSubtitleManager(final PlayerActivity mActivity) {
        this.mPendingActions = Collections.synchronizedList(new ArrayList<Runnable>());
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", this.getClass().getSimpleName() + " created");
        }
        this.mActivity = mActivity;
        this.mScreen = this.mActivity.getScreen();
        if (this.mScreen == null) {
            throw new IllegalArgumentException("Player screen is not initialized!");
        }
        Log.v("nf_subtitles_render", "Create handler.");
        this.mHandler = new Handler();
        this.mDisplayArea = (RelativeLayout)this.mActivity.findViewById(2131427705);
        if (this.mDisplayArea.getWidth() == 0 || this.mDisplayArea.getHeight() == 0) {
            Log.w("nf_subtitles_render", "Display area w/h are 0, display area is not visible yet!");
        }
        this.mHorizontalRegionPadding = AndroidUtils.dipToPixels((Context)mActivity, 5);
        this.mVerticalRegionPadding = AndroidUtils.dipToPixels((Context)mActivity, 1);
        int n;
        if (mActivity.isTablet()) {
            n = 59;
        }
        else {
            n = 46;
        }
        this.mBottomPanelPadding = AndroidUtils.dipToPixels((Context)mActivity, n);
        this.mBottomPanelHeight = mActivity.getResources().getDimensionPixelSize(2131296518);
        this.mTopPanelPadding = AndroidUtils.dipToPixels((Context)mActivity, 5);
        this.mTopPanel = mActivity.findViewById(2131427713);
    }
    
    protected abstract Runnable createRunnable(final SubtitleBlock p0, final boolean p1);
    
    @Override
    public Context getContext() {
        return (Context)this.mActivity;
    }
    
    protected int getDisplayAreaMarginBottom() {
        if (DeviceUtils.hasHardwareNavigationKeys() || ViewUtils.isNavigationBarRightOfContent(this.mActivity)) {
            return this.mBottomPanelHeight + this.mBottomPanelPadding;
        }
        if (ViewUtils.isNavigationBarBelowContent(this.mActivity)) {
            return this.mBottomPanelHeight + this.mBottomPanelPadding + ViewUtils.getNavigationBarHeight((Context)this.mActivity, false);
        }
        return this.mBottomPanelHeight + this.mBottomPanelPadding;
    }
    
    protected int getDisplayAreaMarginTop() {
        final int statusBarHeight = ViewUtils.getStatusBarHeight((Context)this.mActivity);
        if (this.mTopPanel == null) {
            Log.w("nf_subtitles_render", "Top panel is null");
            return statusBarHeight + this.mTopPanelPadding;
        }
        return statusBarHeight + (this.mTopPanel.getHeight() + this.mTopPanelPadding);
    }
    
    protected void handleDelayedSubtitleBlocks(final List<SubtitleBlock> list, final boolean b) {
        if (list != null) {
            for (final SubtitleBlock subtitleBlock : list) {
                if (Log.isLoggable()) {
                    Log.v("nf_subtitles_render", "Posting delayed block by " + subtitleBlock.getEnd() + " [ms]. Display " + b + " for block " + subtitleBlock.getId());
                }
                this.mHandler.postDelayed(this.createRunnable(subtitleBlock, b), subtitleBlock.getEnd());
            }
        }
    }
    
    protected boolean isDisplayAreaVisible() {
        if (this.mDisplayArea == null) {
            Log.e("nf_subtitles_render", "Display area NOT found! This should NOT happen!");
            return false;
        }
        if (this.mDisplayArea.getWidth() == 0 || this.mDisplayArea.getHeight() == 0) {
            Log.w("nf_subtitles_render", "Display area w/h are 0, display area is not visible yet, postpone getting w/h");
            return false;
        }
        return true;
    }
    
    protected void makeItVisible(final List<ViewUtils$ViewComparator> list) {
        for (final ViewUtils$ViewComparator viewUtils$ViewComparator : list) {
            viewUtils$ViewComparator.getView().setTag((Object)null);
            viewUtils$ViewComparator.getView().setVisibility(0);
        }
    }
    
    protected void moveBlocksAppartIfNeeded(final List<ViewUtils$ViewComparator> list) {
        Collections.sort((List<Object>)list, (Comparator<? super Object>)ViewUtils$ViewComparator.REVERSE_SORT_BY_TOP);
        if (this.moveBlocksUpIfNeeded(list)) {
            Log.d("nf_subtitles_render", "Blocks moved appart, but we hit top, reorder blocks and move appart by pushing to bottom");
            Collections.sort((List<Object>)list, (Comparator<? super Object>)ViewUtils$ViewComparator.SORT_BY_TOP);
            Log.d("nf_subtitles_render", "Sorted");
            this.moveBlocksBottomIfNeeded(list);
            return;
        }
        Log.d("nf_subtitles_render", "Blocks moved appart, no issues");
    }
    
    protected void moveBlocksBottomIfNeeded(final List<ViewUtils$ViewComparator> list) {
        for (int size = list.size(), i = 0; i < size - 1; ++i) {
            for (int j = i + 1; j < list.size(); ++j) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "Blocks " + i + " and " + j + " test " + list.size());
                }
                SubtitleUtils.adjustIfIntersectByMovingSecondDown(list.get(i).getView(), list.get(j).getView(), this.mDisplayArea.getHeight());
            }
        }
    }
    
    protected boolean moveBlocksUpIfNeeded(final List<ViewUtils$ViewComparator> list) {
        final int size = list.size();
        int i = 0;
        boolean b = false;
        while (i < size - 1) {
            for (int j = i + 1; j < list.size(); ++j) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "Blocks " + i + " and " + j + " test " + list.size());
                }
                if (SubtitleUtils.adjustIfIntersectByMovingFirstUp(list.get(j).getView(), list.get(i).getView()) == -1) {
                    Log.w("nf_subtitles_render", "We hit top!");
                    b = true;
                }
            }
            ++i;
        }
        return b;
    }
    
    protected abstract void removeVisibleSubtitleBlocks(final boolean p0);
}
