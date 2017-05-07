// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.text.TextUtils;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.KubrickShowDetails;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
        this.this$1.currentSlideshowIndex = 0;
        if (Log.isLoggable()) {
            Log.e("KubrickShowDetailsFrag", "HeroSlideshow: " + s);
        }
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String tag) {
        if (bitmap != null && !this.this$1.stopRequested) {
            final Object tag2 = this.val$horzDispImg.getTag();
            if (tag2 == null || tag2 != tag) {
                AnimationUtils.setImageBitmapWithPropertyFade(this.val$horzDispImg, bitmap);
                this.val$horzDispImg.setTag((Object)tag);
            }
        }
    }
}
