// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.MathUtils;
import com.makeramen.RoundedDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Paint;
import android.widget.ImageView;

public abstract class DebugImageView extends ImageView
{
    private static final float BAD_BITMAP_SIZE_SCALE_FACTOR = 0.75f;
    private static final int BG_ALPHA = 96;
    private static final boolean DRAW_BITMAP_SIZE_OVERLAY_FOR_GOOD_CASES = false;
    private static final boolean LOGCAT_VERBOSE = false;
    private static final float TEXT_SIZE_DP = 12.0f;
    private static final int X_OFFSET_PX = 12;
    private static final int Y_OFFSET_PX = 4;
    private static Paint bgPaintAwful;
    private static Paint bgPaintBad;
    private static Paint bgPaintGood;
    private static Paint bgPaintUnknown;
    private static Rect bgRect;
    private static Paint fillPaint;
    private static boolean staticInitComplete;
    private static int yPosPx;
    private String overlayMsg;
    
    static {
        DebugImageView.staticInitComplete = false;
    }
    
    public DebugImageView(final Context context) {
        super(context);
        this.init();
    }
    
    public DebugImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public DebugImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private Paint computeBgPaint(final int n, final int n2) {
        if (n > this.getWidth() || n2 > this.getHeight()) {
            return DebugImageView.bgPaintAwful;
        }
        if (n > this.getWidth() * 0.75f || n2 > this.getHeight() * 0.75f) {
            return DebugImageView.bgPaintBad;
        }
        return null;
    }
    
    private void drawBitmapSizeOverlay(final Canvas canvas) {
        this.overlayMsg = "n/a";
        final Drawable drawable = this.getDrawable();
        Paint paint = DebugImageView.bgPaintUnknown;
        if (drawable instanceof BitmapDrawable) {
            final Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            if (bitmap != null) {
                this.overlayMsg = "" + bitmap.getWidth() + "x" + bitmap.getHeight();
                paint = this.computeBgPaint(bitmap.getWidth(), bitmap.getHeight());
            }
        }
        else if (drawable instanceof RoundedDrawable) {
            final RoundedDrawable roundedDrawable = (RoundedDrawable)drawable;
            this.overlayMsg = "" + roundedDrawable.getIntrinsicWidth() + "x" + roundedDrawable.getIntrinsicHeight();
            paint = this.computeBgPaint(roundedDrawable.getIntrinsicWidth(), roundedDrawable.getIntrinsicHeight());
        }
        while (true) {
            if (paint != null) {
                DebugImageView.fillPaint.getTextBounds(this.overlayMsg, 0, this.overlayMsg.length(), DebugImageView.bgRect);
                DebugImageView.bgRect.offset(12, DebugImageView.yPosPx);
                MathUtils.expandRect(DebugImageView.bgRect, 8);
                canvas.drawRect(DebugImageView.bgRect, paint);
                canvas.drawText(this.overlayMsg, 12.0f, (float)DebugImageView.yPosPx, DebugImageView.fillPaint);
                Log.d(this.getLogTag(), "bitmap size: %s, view x: %d, view y: %d, bgRect: %s", this.overlayMsg, this.getWidth(), this.getHeight(), DebugImageView.bgRect.toShortString());
            }
            return;
            continue;
        }
    }
    
    private void init() {
    }
    
    protected abstract String getLogTag();
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
    }
    
    public void setImageBitmap(final Bitmap imageBitmap) {
        super.setImageBitmap(imageBitmap);
    }
    
    public void setImageDrawable(final Drawable imageDrawable) {
        super.setImageDrawable(imageDrawable);
    }
    
    public void setImageResource(final int imageResource) {
        super.setImageResource(imageResource);
    }
}
