// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.Trackable;
import java.util.List;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.android.fragment.CustomViewPager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import android.widget.LinearLayout;
import com.netflix.mediaclient.servicemgr.Video;

public abstract class VideoViewGroup<T extends Video, V extends View> extends LinearLayout
{
    protected static final String TAG = "VideoViewGroup";
    
    public VideoViewGroup(final Context context, final boolean b) {
        super(context);
        this.setId(2131165238);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        this.setOrientation(0);
        if (b) {
            CustomViewPager.applyContentOverlapPadding((NetflixActivity)this.getContext(), (View)this);
        }
    }
    
    protected abstract V createChildView(final Context p0);
    
    public void init(final int n) {
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -1, 1.0f);
        for (int i = 0; i < n; ++i) {
            final View childView = this.createChildView(this.getContext());
            if (this.shouldApplyPaddingToChildren()) {
                final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(2131361870);
                childView.setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            }
            this.addView(childView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        }
    }
    
    protected abstract boolean shouldApplyPaddingToChildren();
    
    public void updateDataThenViews(final List<T> list, final int n, final int n2, final int n3, final Trackable trackable) {
        if (Log.isLoggable("VideoViewGroup", 2)) {
            Log.v("VideoViewGroup", "Setting data, first: " + DataUtil.getFirstItemSafely(list) + ", hash: " + this.hashCode() + ", num per page: " + n + ", page: " + n2 + ", listViewPos: " + n3);
        }
        final int n4 = n2 * n;
        for (int i = 0; i < n; ++i) {
            final View child = this.getChildAt(i);
            if (child == null) {
                Log.w("VideoViewGroup", "Expected valid child but child is null, index: " + i);
            }
            else {
                this.updateViewIds((V)child, n3, n4, i);
                if (list != null && i < list.size()) {
                    ((IVideoView<Object>)child).update(list.get(i), trackable, n4 + i, n2 == 0);
                }
                else {
                    ((IVideoView<Object>)child).hide();
                }
            }
        }
    }
    
    protected abstract void updateViewIds(final V p0, final int p1, final int p2, final int p3);
    
    public interface IVideoView<T> extends PlayContextProvider
    {
        void hide();
        
        void update(final T p0, final Trackable p1, final int p2, final boolean p3);
    }
}
