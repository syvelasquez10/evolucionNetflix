// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.content.Context;
import com.makeramen.RoundedImageView;

public abstract class LoggingImageView extends RoundedImageView
{
    private static final boolean LOG_VERBOSE = false;
    
    public LoggingImageView(final Context context) {
        super(context);
    }
    
    public LoggingImageView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public LoggingImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected abstract String getLogTag();
    
    @Override
    public void setImageBitmap(final Bitmap imageBitmap) {
        super.setImageBitmap(imageBitmap);
    }
    
    @Override
    public void setImageDrawable(final Drawable imageDrawable) {
        super.setImageDrawable(imageDrawable);
    }
    
    @Override
    public void setImageResource(final int imageResource) {
        super.setImageResource(imageResource);
    }
}
