// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.ViewGroup;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.content.Context;
import com.netflix.mediaclient.android.widget.VideoView;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
        final GridView gridView = this.this$0.gridView;
        final int n = gridView.getWidth() - gridView.getPaddingLeft() - gridView.getPaddingRight();
        Log.v("SimilarItemsGridViewAdapter", "View dimens: " + n + ", " + gridView.getHeight());
        this.this$0.imgHeight = this.this$0.calculateImageHeight(n);
        Log.v("SimilarItemsGridViewAdapter", "imgHeight: " + this.this$0.imgHeight);
        ViewUtils.removeGlobalLayoutListener((View)gridView, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
