// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.net.URI;
import android.content.Context;

public class ImageRequest$Builder
{
    private boolean allowCachedRedirects;
    private ImageRequest$Callback callback;
    private Object callerTag;
    private Context context;
    private URI imageUrl;
    
    public ImageRequest$Builder(final Context context, final URI imageUrl) {
        Validate.notNull(imageUrl, "imageUrl");
        this.context = context;
        this.imageUrl = imageUrl;
    }
    
    public ImageRequest build() {
        return new ImageRequest(this, null);
    }
    
    public ImageRequest$Builder setAllowCachedRedirects(final boolean allowCachedRedirects) {
        this.allowCachedRedirects = allowCachedRedirects;
        return this;
    }
    
    public ImageRequest$Builder setCallback(final ImageRequest$Callback callback) {
        this.callback = callback;
        return this;
    }
    
    public ImageRequest$Builder setCallerTag(final Object callerTag) {
        this.callerTag = callerTag;
        return this;
    }
}
