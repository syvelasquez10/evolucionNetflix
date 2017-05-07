// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.osp;

import android.graphics.Bitmap$Config;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.content.res.TypedArray;
import android.graphics.Paint$Style;
import com.netflix.mediaclient.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.widget.TextView;

public class SimpleStrokedTextView extends TextView
{
    private Bitmap mCache;
    private final Canvas mCanvas;
    private final Paint mPaint;
    private int mStrokeColor;
    private float mStrokeWidth;
    private int mTextColor;
    private boolean mUpdateCachedBitmap;
    
    public SimpleStrokedTextView(final Context context) {
        super(context);
        this.mCanvas = new Canvas();
        this.mPaint = new Paint();
        this.init(context, null, 0);
    }
    
    public SimpleStrokedTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mCanvas = new Canvas();
        this.mPaint = new Paint();
        this.init(context, set, 0);
    }
    
    public SimpleStrokedTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mCanvas = new Canvas();
        this.mPaint = new Paint();
        this.init(context, set, n);
    }
    
    private void init(final Context context, final AttributeSet set, final int n) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.SimpleStrokedTextView, n, 0);
        this.mStrokeColor = obtainStyledAttributes.getColor(0, -16777216);
        this.mStrokeWidth = obtainStyledAttributes.getFloat(1, 0.0f);
        this.mTextColor = obtainStyledAttributes.getColor(2, -1);
        obtainStyledAttributes.recycle();
        this.mUpdateCachedBitmap = true;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint$Style.FILL_AND_STROKE);
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.mCache != null) {
            if (this.mUpdateCachedBitmap) {
                final int measuredWidth = this.getMeasuredWidth();
                final int measuredHeight = this.getMeasuredHeight();
                final String string = this.getText().toString();
                final Rect rect = new Rect();
                final TextPaint paint = this.getPaint();
                final int n = (int)((Paint)paint).measureText(string);
                ((Paint)paint).getTextBounds("x", 0, 1, rect);
                this.mCanvas.setBitmap(this.mCache);
                this.mCanvas.drawColor(0, PorterDuff$Mode.CLEAR);
                final int paddingLeft = this.getPaddingLeft();
                final int paddingTop = this.getPaddingTop();
                final Drawable[] compoundDrawables = this.getCompoundDrawables();
                for (int i = 0; i < compoundDrawables.length; ++i) {
                    if (compoundDrawables[i] != null) {
                        compoundDrawables[i].setBounds(paddingLeft, paddingTop, compoundDrawables[i].getIntrinsicWidth() + paddingLeft, compoundDrawables[i].getIntrinsicHeight() + paddingTop);
                        compoundDrawables[i].draw(this.mCanvas);
                    }
                }
                final int n2 = measuredWidth - this.getPaddingRight() - n;
                final int n3 = (rect.height() + measuredHeight) / 2;
                this.mPaint.setStrokeWidth(this.mStrokeWidth);
                this.mPaint.setColor(this.mStrokeColor);
                this.mPaint.setTextSize(this.getTextSize());
                this.mCanvas.drawText(string, (float)n2, (float)n3, this.mPaint);
                this.mPaint.setStrokeWidth(0.0f);
                this.mPaint.setColor(this.mTextColor);
                this.mCanvas.drawText(string, (float)n2, (float)n3, this.mPaint);
                this.mUpdateCachedBitmap = false;
            }
            canvas.drawBitmap(this.mCache, 0.0f, 0.0f, this.mPaint);
            return;
        }
        super.onDraw(canvas);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n > 0 && n2 > 0) {
            this.mUpdateCachedBitmap = true;
            this.mCache = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
            return;
        }
        this.mCache = null;
    }
    
    protected void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        super.onTextChanged(charSequence, n, n2, n3);
        this.mUpdateCachedBitmap = true;
    }
    
    public void setBoldEnabled(final boolean fakeBoldText) {
        this.mPaint.setFakeBoldText(fakeBoldText);
        this.invalidate();
    }
}
