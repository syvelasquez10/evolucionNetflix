// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;
import com.android.volley.VolleyError;

class NetworkImageView$1 implements ImageLoader$ImageListener
{
    final /* synthetic */ NetworkImageView this$0;
    final /* synthetic */ boolean val$isInLayoutPass;
    
    NetworkImageView$1(final NetworkImageView this$0, final boolean val$isInLayoutPass) {
        this.this$0 = this$0;
        this.val$isInLayoutPass = val$isInLayoutPass;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (this.this$0.mErrorImageId != 0) {
            this.this$0.setImageResource(this.this$0.mErrorImageId);
        }
    }
    
    @Override
    public void onResponse(final ImageLoader$ImageContainer imageLoader$ImageContainer, final boolean b) {
        if (b && this.val$isInLayoutPass) {
            this.this$0.post((Runnable)new NetworkImageView$1$1(this, imageLoader$ImageContainer));
        }
        else {
            if (imageLoader$ImageContainer.getBitmap() != null) {
                this.this$0.setImageBitmap(imageLoader$ImageContainer.getBitmap());
                return;
            }
            if (this.this$0.mDefaultImageId != 0) {
                this.this$0.setImageResource(this.this$0.mDefaultImageId);
            }
        }
    }
}
