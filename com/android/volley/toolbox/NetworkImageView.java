// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;

public class NetworkImageView extends ImageView
{
    private int mDefaultImageId;
    private int mErrorImageId;
    private ImageLoader$ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;
    
    public NetworkImageView(final Context context) {
        this(context, null);
    }
    
    public NetworkImageView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NetworkImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    private void loadImageIfNecessary(final boolean b) {
        final int width = this.getWidth();
        final int height = this.getHeight();
        boolean b2;
        if (this.getLayoutParams().height == -2 && this.getLayoutParams().width == -2) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (width != 0 || height != 0 || b2) {
            if (TextUtils.isEmpty((CharSequence)this.mUrl)) {
                if (this.mImageContainer != null) {
                    this.mImageContainer.cancelRequest();
                    this.mImageContainer = null;
                }
                this.setImageBitmap((Bitmap)null);
                return;
            }
            if (this.mImageContainer != null && this.mImageContainer.getRequestUrl() != null) {
                if (this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                    return;
                }
                this.mImageContainer.cancelRequest();
                this.setImageBitmap((Bitmap)null);
            }
            this.mImageContainer = this.mImageLoader.get(this.mUrl, new NetworkImageView$1(this, b));
        }
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.invalidate();
    }
    
    protected void onDetachedFromWindow() {
        if (this.mImageContainer != null) {
            this.mImageContainer.cancelRequest();
            this.setImageBitmap((Bitmap)null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.loadImageIfNecessary(true);
    }
    
    public void setDefaultImageResId(final int mDefaultImageId) {
        this.mDefaultImageId = mDefaultImageId;
    }
    
    public void setErrorImageResId(final int mErrorImageId) {
        this.mErrorImageId = mErrorImageId;
    }
    
    public void setImageUrl(final String mUrl, final ImageLoader mImageLoader) {
        this.mUrl = mUrl;
        this.mImageLoader = mImageLoader;
        this.loadImageIfNecessary(false);
    }
}
