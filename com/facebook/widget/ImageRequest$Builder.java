// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.internal.Validate;
import java.net.URL;
import android.content.Context;

class ImageRequest$Builder
{
    private boolean allowCachedRedirects;
    private ImageRequest$Callback callback;
    private Object callerTag;
    private Context context;
    private URL imageUrl;
    
    ImageRequest$Builder(final Context context, final URL imageUrl) {
        Validate.notNull(imageUrl, "imageUrl");
        this.context = context;
        this.imageUrl = imageUrl;
    }
    
    ImageRequest build() {
        return new ImageRequest(this, null);
    }
    
    ImageRequest$Builder setAllowCachedRedirects(final boolean allowCachedRedirects) {
        this.allowCachedRedirects = allowCachedRedirects;
        return this;
    }
    
    ImageRequest$Builder setCallback(final ImageRequest$Callback callback) {
        this.callback = callback;
        return this;
    }
    
    ImageRequest$Builder setCallerTag(final Object callerTag) {
        this.callerTag = callerTag;
        return this;
    }
}
