// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class MovieDetailsFrag$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ MovieDetailsFrag this$0;
    
    MovieDetailsFrag$1(final MovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    private int getImageHeight() {
        return (int)((this.this$0.recyclerView.getWidth() - this.this$0.recyclerView.getPaddingLeft() - this.this$0.recyclerView.getPaddingRight()) / this.this$0.numColumns * 1.43f + 0.5f);
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setScaleType(ImageView$ScaleType.CENTER_CROP);
        videoView.setTag(2131165257, (Object)true);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.getImageHeight()));
        return (View)videoView;
    }
}
