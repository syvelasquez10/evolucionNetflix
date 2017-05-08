// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.imagehelper;

import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import android.net.Uri;

public class ImageSource
{
    private boolean isResource;
    private double mSize;
    private String mSource;
    private Uri mUri;
    
    public ImageSource(final Context context, final String s) {
        this(context, s, 0.0, 0.0);
    }
    
    public ImageSource(final Context context, final String mSource, final double n, final double n2) {
        this.mSource = mSource;
        this.mSize = n * n2;
        this.mUri = this.computeUri(context);
    }
    
    private Uri computeLocalUri(final Context context) {
        this.isResource = true;
        return ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(context, this.mSource);
    }
    
    private Uri computeUri(final Context context) {
        try {
            Uri uri;
            if ((uri = Uri.parse(this.mSource)).getScheme() == null) {
                uri = this.computeLocalUri(context);
            }
            return uri;
        }
        catch (Exception ex) {
            return this.computeLocalUri(context);
        }
    }
    
    public double getSize() {
        return this.mSize;
    }
    
    public String getSource() {
        return this.mSource;
    }
    
    public Uri getUri() {
        return Assertions.assertNotNull(this.mUri);
    }
    
    public boolean isResource() {
        return this.isResource;
    }
}
