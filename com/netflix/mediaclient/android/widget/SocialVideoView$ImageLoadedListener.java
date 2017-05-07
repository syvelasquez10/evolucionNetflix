// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.text.Layout$Alignment;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Paint$Align;
import android.content.res.Resources;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.graphics.Paint;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;
import android.graphics.Rect;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class SocialVideoView$ImageLoadedListener implements ImageLoader$ImageLoaderListener
{
    private final int index;
    final /* synthetic */ SocialVideoView this$0;
    
    public SocialVideoView$ImageLoadedListener(final SocialVideoView this$0, final int index) {
        this.this$0 = this$0;
        this.index = index;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        Log.w("SocialVideoView", "Could not load img: " + s + ", index: " + this.index);
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        if (bitmap == null) {
            return;
        }
        Log.v("SocialVideoView", "Loaded bitmap: " + s + ", index: " + this.index + ", size: " + bitmap.getWidth() + "x" + bitmap.getHeight());
        this.this$0.bitmaps[this.index] = bitmap;
        this.this$0.invalidate();
    }
}
