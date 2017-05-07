// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.widget.ImageView$ScaleType;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.UiUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import android.app.Activity;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class SimilarItemsGridViewAdapter$1 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ SimilarItemsGridViewAdapter this$0;
    
    SimilarItemsGridViewAdapter$1(final SimilarItemsGridViewAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        final GridView access$000 = this.this$0.gridView;
        final int n = access$000.getWidth() - access$000.getPaddingLeft() - access$000.getPaddingRight();
        Log.v("SimilarItemsGridViewAdapter", "View dimens: " + n + ", " + access$000.getHeight());
        this.this$0.imgHeight = (int)(n / this.this$0.numGridCols * 1.43f + 0.5f);
        Log.v("SimilarItemsGridViewAdapter", "imgHeight: " + this.this$0.imgHeight);
        ViewUtils.removeGlobalLayoutListener((View)access$000, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
