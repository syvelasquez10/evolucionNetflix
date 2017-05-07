// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.VolleyError;
import android.widget.ImageView;

final class ImageLoader$1 implements ImageLoader$ImageListener
{
    final /* synthetic */ int val$defaultImageResId;
    final /* synthetic */ int val$errorImageResId;
    final /* synthetic */ ImageView val$view;
    
    ImageLoader$1(final int val$errorImageResId, final ImageView val$view, final int val$defaultImageResId) {
        this.val$errorImageResId = val$errorImageResId;
        this.val$view = val$view;
        this.val$defaultImageResId = val$defaultImageResId;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (this.val$errorImageResId != 0) {
            this.val$view.setImageResource(this.val$errorImageResId);
        }
    }
    
    @Override
    public void onResponse(final ImageLoader$ImageContainer imageLoader$ImageContainer, final boolean b) {
        if (imageLoader$ImageContainer.getBitmap() != null) {
            this.val$view.setImageBitmap(imageLoader$ImageContainer.getBitmap());
        }
        else if (this.val$defaultImageResId != 0) {
            this.val$view.setImageResource(this.val$defaultImageResId);
        }
    }
}
