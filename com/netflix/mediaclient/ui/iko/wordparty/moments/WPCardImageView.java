// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.Log;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public abstract class WPCardImageView extends ImageView
{
    public static final String TAG = "WPCardImageView";
    protected BitmapDrawable closedDrawable;
    protected BitmapDrawable currentBitmapDrawable;
    protected boolean isCardClosed;
    protected BitmapDrawable openDrawable;
    
    public WPCardImageView(final Context context) {
        this(context, null);
    }
    
    public WPCardImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.isCardClosed = true;
        this.init(context);
    }
    
    protected abstract void init(final Context p0);
    
    public boolean isOpen() {
        return !this.isCardClosed;
    }
    
    public void recycleDrawables(final BitmapDrawable... array) {
        if (array != null && array.length >= 1) {
            for (int length = array.length, i = 0; i < length; ++i) {
                final BitmapDrawable bitmapDrawable = array[i];
                if (bitmapDrawable == null) {
                    break;
                }
                final Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }
    
    public void releaseResources() {
        this.recycleDrawables(this.closedDrawable, this.openDrawable, this.currentBitmapDrawable);
        this.closedDrawable = null;
        this.openDrawable = null;
        this.currentBitmapDrawable = null;
    }
    
    public void reset(final boolean isCardClosed) {
        this.isCardClosed = isCardClosed;
        this.updateDrawableBitmap();
    }
    
    public void setDrawables(final BitmapDrawable closedDrawable, final BitmapDrawable openDrawable) {
        if (Log.isLoggable()) {
            Log.d("WPCardImageView", "setDrawables closed=" + closedDrawable);
        }
        this.isCardClosed = true;
        this.closedDrawable = closedDrawable;
        this.openDrawable = openDrawable;
        this.updateDrawableBitmap();
    }
    
    public String toString() {
        return "WPCardImageView{closedDrawable=" + this.closedDrawable + ", openDrawable=" + this.openDrawable + ", currentBitmapDrawable=" + this.currentBitmapDrawable + ", isCardClosed=" + this.isCardClosed + '}';
    }
    
    public void toggleCardClosed() {
        Log.d("WPCardImageView", "toggleCardClosed");
        this.isCardClosed = !this.isCardClosed;
    }
    
    public void updateDrawableBitmap() {
        BitmapDrawable currentBitmapDrawable;
        if (this.isCardClosed) {
            currentBitmapDrawable = this.closedDrawable;
        }
        else {
            currentBitmapDrawable = this.openDrawable;
        }
        this.setImageDrawable((Drawable)(this.currentBitmapDrawable = currentBitmapDrawable));
    }
}
