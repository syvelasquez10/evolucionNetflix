// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import android.text.TextUtils;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.KubrickShowDetails;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.Bitmap;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class BarkerShowDetailsFrag$HeroSlideshow$2 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ BarkerShowDetailsFrag$HeroSlideshow this$1;
    
    BarkerShowDetailsFrag$HeroSlideshow$2(final BarkerShowDetailsFrag$HeroSlideshow this$1) {
        this.this$1 = this$1;
    }
    
    private void setImage(final String tag, final Bitmap imageBitmap, final AdvancedImageView advancedImageView, final AdvancedImageView advancedImageView2) {
        final Object tag2 = advancedImageView2.getTag();
        if (tag2 != null && tag2 == tag) {
            return;
        }
        advancedImageView.setTag((Object)tag);
        advancedImageView.setImageBitmap(imageBitmap);
        advancedImageView.animate().alpha(1.0f).setDuration(2000L);
        advancedImageView2.animate().alpha(0.0f).setDuration(2000L);
        this.this$1.isHorzDispImgShowing = !this.this$1.isHorzDispImgShowing;
    }
    
    public void onErrorResponse(final String s) {
        this.this$1.currentSlideshowIndex = 0;
        if (Log.isLoggable()) {
            Log.e("BarkerShowDetailsFrag", "HeroSlideshow: " + s);
        }
    }
    
    public void onResponse(final Bitmap bitmap, final String s) {
        if (bitmap != null && !this.this$1.stopRequested) {
            if (!this.this$1.isHorzDispImgShowing) {
                this.setImage(s, bitmap, this.this$1.horzDispImg, this.this$1.horzDispImg2);
                return;
            }
            this.setImage(s, bitmap, this.this$1.horzDispImg2, this.this$1.horzDispImg);
        }
    }
}
