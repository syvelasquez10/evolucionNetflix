// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.view.View;
import android.support.v7.widget.RecyclerView$ViewHolder;

class TurboExtendedDiscoveryFrag$TurboViewHolder extends RecyclerView$ViewHolder
{
    final /* synthetic */ TurboExtendedDiscoveryFrag this$0;
    
    public TurboExtendedDiscoveryFrag$TurboViewHolder(final TurboExtendedDiscoveryFrag this$0, final View view) {
        this.this$0 = this$0;
        super(view);
    }
    
    public void updateView(final int n) {
        final Video video = this.this$0.collectionData.get(n);
        NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg((AdvancedImageView)this.itemView, video.getBoxshotUrl(), IClientLogging$AssetType.merchStill, video.getTitle(), ImageLoader$StaticImgConfig.DARK, true, 1);
        this.itemView.setOnClickListener((View$OnClickListener)new TurboExtendedDiscoveryFrag$TurboViewHolder$1(this, n));
    }
}
