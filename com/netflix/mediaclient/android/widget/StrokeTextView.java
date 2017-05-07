// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.Bitmap$Config;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.content.res.TypedArray;
import android.graphics.Paint$Style;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap;

public class StrokeTextView extends AutoResizeTextView
{
    private Bitmap mCache;
    private final Canvas mCanvas;
    private final Paint mPaint;
    private int mStrokeColor;
    private int mStrokeWidth;
    private int mTextColor;
    private boolean mUpdateCachedBitmap;
    
    public StrokeTextView(final Context context) {
        super(context);
        this.mCanvas = new Canvas();
        this.mPaint = new Paint();
        this.mStrokeColor = -1;
        this.init(context, null, 0);
    }
    
    public StrokeTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mCanvas = new Canvas();
        this.mPaint = new Paint();
        this.mStrokeColor = -1;
        this.init(context, set, 0);
    }
    
    public StrokeTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mCanvas = new Canvas();
        this.mPaint = new Paint();
        this.mStrokeColor = -1;
        this.init(context, set, n);
    }
    
    private void init(final Context context, final AttributeSet set, int i) {
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R$styleable.StrokeTextView, i, 0);
        if (obtainStyledAttributes == null) {
            return;
        }
        int index;
        for (i = 0; i < obtainStyledAttributes.getIndexCount(); ++i) {
            index = obtainStyledAttributes.getIndex(i);
            switch (index) {
                case 1: {
                    this.mStrokeColor = obtainStyledAttributes.getColor(index, 0);
                    break;
                }
                case 0: {
                    this.mStrokeWidth = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    break;
                }
            }
        }
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
                this.mPaint.setStrokeWidth((float)this.mStrokeWidth);
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
    
    @Override
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n > 0 && n2 > 0) {
            this.mUpdateCachedBitmap = true;
            this.mCache = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
            return;
        }
        this.mCache = null;
    }
    
    @Override
    protected void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        super.onTextChanged(charSequence, n, n2, n3);
        this.mUpdateCachedBitmap = true;
    }
    
    public void setStrokeColor(final int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
    }
    
    public void setStrokeWidth(final int mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
    }
    
    public void setTextColor(final int n) {
        super.setTextColor(n);
        this.mTextColor = n;
    }
}
