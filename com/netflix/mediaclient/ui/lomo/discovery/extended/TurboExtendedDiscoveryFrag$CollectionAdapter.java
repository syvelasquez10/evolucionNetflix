// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.ImageView$ScaleType;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView$ViewHolder;
import android.support.v7.widget.RecyclerView$Adapter;

class TurboExtendedDiscoveryFrag$CollectionAdapter extends RecyclerView$Adapter<RecyclerView$ViewHolder>
{
    final /* synthetic */ TurboExtendedDiscoveryFrag this$0;
    
    private TurboExtendedDiscoveryFrag$CollectionAdapter(final TurboExtendedDiscoveryFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int getItemCount() {
        if (this.this$0.collectionData == null) {
            return 0;
        }
        return this.this$0.collectionData.size();
    }
    
    @Override
    public void onBindViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        ((TurboExtendedDiscoveryFrag$TurboViewHolder)recyclerView$ViewHolder).updateView(n);
    }
    
    @Override
    public RecyclerView$ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int dimensionPixelOffset) {
        final AdvancedImageView advancedImageView = new AdvancedImageView((Context)this.this$0.getActivity());
        advancedImageView.setScaleType(ImageView$ScaleType.FIT_XY);
        advancedImageView.setAdjustViewBounds(true);
        dimensionPixelOffset = this.this$0.getActivity().getResources().getDimensionPixelOffset(2131362150);
        final float n = (DeviceUtils.getScreenWidthInPixels((Context)this.this$0.getActivity()) - dimensionPixelOffset * 2) / 2;
        advancedImageView.setLayoutParams(new ViewGroup$LayoutParams((int)n, (int)(1.43f * n)));
        return new TurboExtendedDiscoveryFrag$TurboViewHolder(this.this$0, (View)advancedImageView);
    }
}
