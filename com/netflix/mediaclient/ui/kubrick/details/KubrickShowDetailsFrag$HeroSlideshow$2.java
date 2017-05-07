// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.widget.ImageView;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class KubrickShowDetailsFrag$HeroSlideshow$2 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ KubrickShowDetailsFrag$HeroSlideshow this$1;
    final /* synthetic */ AdvancedImageView val$horzDispImg;
    
    KubrickShowDetailsFrag$HeroSlideshow$2(final KubrickShowDetailsFrag$HeroSlideshow this$1, final AdvancedImageView val$horzDispImg) {
        this.this$1 = this$1;
        this.val$horzDispImg = val$horzDispImg;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        if (Log.isLoggable("KubrickShowDetailsFrag", 6)) {
            Log.e("KubrickShowDetailsFrag", "HeroSlideshow: " + s);
        }
    }
    
    @Override
    public void onResponse(final Bitmap imageBitmap, final String s) {
        if (imageBitmap != null) {
            this.val$horzDispImg.setImageBitmap(imageBitmap);
            AnimationUtils.setImageBitmapWithPropertyFade(this.val$horzDispImg, imageBitmap);
        }
    }
}
