// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class MovieDetailsFrag$3 implements RecyclerViewHeaderAdapter$IViewCreator
{
    private int height;
    final /* synthetic */ MovieDetailsFrag this$0;
    private int width;
    
    MovieDetailsFrag$3(final MovieDetailsFrag this$0) {
        this.this$0 = this$0;
        this.calculateViewDimensions();
    }
    
    private void calculateViewDimensions() {
        this.width = (DeviceUtils.getScreenWidthInPixels((Context)this.this$0.getActivity()) - this.this$0.recyclerView.getPaddingLeft() - this.this$0.recyclerView.getPaddingRight() - (this.this$0.numColumns + 1) * this.this$0.getActivity().getResources().getDimensionPixelOffset(2131362064)) / this.this$0.numColumns;
        this.height = (int)(this.width * 1.43f);
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setScaleType(ImageView$ScaleType.FIT_XY);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(this.width, this.height));
        return (View)videoView;
    }
}
