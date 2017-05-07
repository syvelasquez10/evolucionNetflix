// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import java.util.Iterator;
import android.text.TextPaint;
import android.graphics.MaskFilter;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter$Blur;
import android.graphics.Paint;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import com.netflix.mediaclient.R$styleable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Bitmap$Config;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint$Join;
import java.util.ArrayList;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Pair;
import java.util.WeakHashMap;
import android.annotation.SuppressLint;
import android.widget.TextView;

@SuppressLint({ "DefaultLocale" })
public class MagicTextView extends TextView
{
    private WeakHashMap<String, Pair<Canvas, Bitmap>> canvasStore;
    private Drawable foregroundDrawable;
    private boolean frozen;
    private ArrayList<MagicTextView$Shadow> innerShadows;
    private int[] lockedCompoundPadding;
    private ArrayList<MagicTextView$Shadow> outerShadows;
    private Integer strokeColor;
    private Paint$Join strokeJoin;
    private float strokeMiter;
    private float strokeWidth;
    private Bitmap tempBitmap;
    private Canvas tempCanvas;
    
    public MagicTextView(final Context context) {
        super(context);
        this.frozen = false;
        this.init(null);
    }
    
    public MagicTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.frozen = false;
        this.init(set);
    }
    
    public MagicTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.frozen = false;
        this.init(set);
    }
    
    private void generateTempCanvas() {
        final String format = String.format("%dx%d", this.getWidth(), this.getHeight());
        final Pair<Canvas, Bitmap> pair = this.canvasStore.get(format);
        if (pair != null) {
            this.tempCanvas = (Canvas)pair.first;
            this.tempBitmap = (Bitmap)pair.second;
            return;
        }
        this.tempCanvas = new Canvas();
        this.tempBitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap$Config.ARGB_8888);
        this.tempCanvas.setBitmap(this.tempBitmap);
        this.canvasStore.put(format, (Pair<Canvas, Bitmap>)new Pair((Object)this.tempCanvas, (Object)this.tempBitmap));
    }
    
    public void addInnerShadow(final float n, final float n2, final float n3, final int n4) {
        float n5 = n;
        if (n == 0.0f) {
            n5 = 1.0E-4f;
        }
        this.innerShadows.add(new MagicTextView$Shadow(n5, n2, n3, n4));
    }
    
    public void addOuterShadow(final float n, final float n2, final float n3, final int n4) {
        float n5 = n;
        if (n == 0.0f) {
            n5 = 1.0E-4f;
        }
        this.outerShadows.add(new MagicTextView$Shadow(n5, n2, n3, n4));
    }
    
    public void clearInnerShadows() {
        this.innerShadows.clear();
    }
    
    public void clearOuterShadows() {
        this.outerShadows.clear();
    }
    
    public void freeze() {
        this.lockedCompoundPadding = new int[] { this.getCompoundPaddingLeft(), this.getCompoundPaddingRight(), this.getCompoundPaddingTop(), this.getCompoundPaddingBottom() };
        this.frozen = true;
    }
    
    public int getCompoundPaddingBottom() {
        if (!this.frozen) {
            return super.getCompoundPaddingBottom();
        }
        return this.lockedCompoundPadding[3];
    }
    
    public int getCompoundPaddingLeft() {
        if (!this.frozen) {
            return super.getCompoundPaddingLeft();
        }
        return this.lockedCompoundPadding[0];
    }
    
    public int getCompoundPaddingRight() {
        if (!this.frozen) {
            return super.getCompoundPaddingRight();
        }
        return this.lockedCompoundPadding[1];
    }
    
    public int getCompoundPaddingTop() {
        if (!this.frozen) {
            return super.getCompoundPaddingTop();
        }
        return this.lockedCompoundPadding[2];
    }
    
    public Drawable getForeground() {
        if (this.foregroundDrawable == null) {
            return this.foregroundDrawable;
        }
        return (Drawable)new ColorDrawable(this.getCurrentTextColor());
    }
    
    public void init(final AttributeSet set) {
        this.outerShadows = new ArrayList<MagicTextView$Shadow>();
        this.innerShadows = new ArrayList<MagicTextView$Shadow>();
        if (this.canvasStore == null) {
            this.canvasStore = new WeakHashMap<String, Pair<Canvas, Bitmap>>();
        }
        if (set != null) {
            final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R$styleable.MagicTextView);
            final String string = obtainStyledAttributes.getString(8);
            if (string != null) {
                this.setTypeface(Typeface.createFromAsset(this.getContext().getAssets(), String.format("fonts/%s.ttf", string)));
            }
            if (obtainStyledAttributes.hasValue(9)) {
                final Drawable drawable = obtainStyledAttributes.getDrawable(9);
                if (drawable != null) {
                    this.setForegroundDrawable(drawable);
                }
                else {
                    this.setTextColor(obtainStyledAttributes.getColor(9, -16777216));
                }
            }
            if (obtainStyledAttributes.hasValue(10)) {
                final Drawable drawable2 = obtainStyledAttributes.getDrawable(10);
                if (drawable2 != null) {
                    this.setBackgroundDrawable(drawable2);
                }
                else {
                    this.setBackgroundColor(obtainStyledAttributes.getColor(10, -16777216));
                }
            }
            if (obtainStyledAttributes.hasValue(0)) {
                this.addInnerShadow(obtainStyledAttributes.getFloat(1, 0.0f), obtainStyledAttributes.getFloat(2, 0.0f), obtainStyledAttributes.getFloat(3, 0.0f), obtainStyledAttributes.getColor(0, -16777216));
            }
            if (obtainStyledAttributes.hasValue(4)) {
                this.addOuterShadow(obtainStyledAttributes.getFloat(5, 0.0f), obtainStyledAttributes.getFloat(6, 0.0f), obtainStyledAttributes.getFloat(7, 0.0f), obtainStyledAttributes.getColor(4, -16777216));
            }
            if (obtainStyledAttributes.hasValue(13)) {
                final float float1 = obtainStyledAttributes.getFloat(11, 1.0f);
                final int color = obtainStyledAttributes.getColor(13, -16777216);
                final float float2 = obtainStyledAttributes.getFloat(12, 10.0f);
                Paint$Join paint$Join = null;
                switch (obtainStyledAttributes.getInt(14, 0)) {
                    case 0: {
                        paint$Join = Paint$Join.MITER;
                        break;
                    }
                    case 1: {
                        paint$Join = Paint$Join.BEVEL;
                        break;
                    }
                    case 2: {
                        paint$Join = Paint$Join.ROUND;
                        break;
                    }
                }
                this.setStroke(float1, color, paint$Join, float2);
            }
            obtainStyledAttributes.recycle();
        }
    }
    
    public void invalidate() {
        if (!this.frozen) {
            super.invalidate();
        }
    }
    
    public void invalidate(final int n, final int n2, final int n3, final int n4) {
        if (!this.frozen) {
            super.invalidate(n, n2, n3, n4);
        }
    }
    
    public void invalidate(final Rect rect) {
        if (!this.frozen) {
            super.invalidate(rect);
        }
    }
    
    @SuppressLint({ "DrawAllocation" })
    public void onDraw(final Canvas canvas) {
        final int currentTextColor = this.getCurrentTextColor();
        if (this.strokeColor != null) {
            final TextPaint paint = this.getPaint();
            paint.setStyle(Paint$Style.STROKE);
            paint.setStrokeJoin(this.strokeJoin);
            paint.setStrokeMiter(this.strokeMiter);
            this.setTextColor((int)this.strokeColor);
            paint.setStrokeWidth(this.strokeWidth);
            super.onDraw(canvas);
            paint.setStyle(Paint$Style.FILL);
            this.setTextColor(currentTextColor);
        }
        super.onDraw(canvas);
        this.freeze();
        final Drawable background = this.getBackground();
        final Drawable[] compoundDrawables = this.getCompoundDrawables();
        this.setCompoundDrawables((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
        for (final MagicTextView$Shadow magicTextView$Shadow : this.outerShadows) {
            this.setShadowLayer(magicTextView$Shadow.r, magicTextView$Shadow.dx, magicTextView$Shadow.dy, magicTextView$Shadow.color);
            super.onDraw(canvas);
        }
        this.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        this.setTextColor(currentTextColor);
        if (this.foregroundDrawable != null && this.foregroundDrawable instanceof BitmapDrawable) {
            this.generateTempCanvas();
            super.onDraw(this.tempCanvas);
            ((BitmapDrawable)this.foregroundDrawable).getPaint().setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.SRC_ATOP));
            this.foregroundDrawable.setBounds(canvas.getClipBounds());
            this.foregroundDrawable.draw(this.tempCanvas);
            canvas.drawBitmap(this.tempBitmap, 0.0f, 0.0f, (Paint)null);
            this.tempCanvas.drawColor(0, PorterDuff$Mode.CLEAR);
        }
        if (this.innerShadows.size() > 0) {
            this.generateTempCanvas();
            final TextPaint paint2 = this.getPaint();
            for (final MagicTextView$Shadow magicTextView$Shadow2 : this.innerShadows) {
                this.setTextColor(magicTextView$Shadow2.color);
                super.onDraw(this.tempCanvas);
                this.setTextColor(-16777216);
                paint2.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.DST_OUT));
                paint2.setMaskFilter((MaskFilter)new BlurMaskFilter(magicTextView$Shadow2.r, BlurMaskFilter$Blur.NORMAL));
                this.tempCanvas.save();
                this.tempCanvas.translate(magicTextView$Shadow2.dx, magicTextView$Shadow2.dy);
                super.onDraw(this.tempCanvas);
                this.tempCanvas.restore();
                canvas.drawBitmap(this.tempBitmap, 0.0f, 0.0f, (Paint)null);
                this.tempCanvas.drawColor(0, PorterDuff$Mode.CLEAR);
                paint2.setXfermode((Xfermode)null);
                paint2.setMaskFilter((MaskFilter)null);
                this.setTextColor(currentTextColor);
                this.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            }
        }
        if (compoundDrawables != null) {
            this.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        }
        this.setBackgroundDrawable(background);
        this.setTextColor(currentTextColor);
        this.unfreeze();
    }
    
    public void postInvalidate() {
        if (!this.frozen) {
            super.postInvalidate();
        }
    }
    
    public void postInvalidate(final int n, final int n2, final int n3, final int n4) {
        if (!this.frozen) {
            super.postInvalidate(n, n2, n3, n4);
        }
    }
    
    public void requestLayout() {
        if (!this.frozen) {
            super.requestLayout();
        }
    }
    
    public void setForegroundDrawable(final Drawable foregroundDrawable) {
        this.foregroundDrawable = foregroundDrawable;
    }
    
    public void setStroke(final float n, final int n2) {
        this.setStroke(n, n2, Paint$Join.MITER, 10.0f);
    }
    
    public void setStroke(final float strokeWidth, final int n, final Paint$Join strokeJoin, final float strokeMiter) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = n;
        this.strokeJoin = strokeJoin;
        this.strokeMiter = strokeMiter;
    }
    
    public void unfreeze() {
        this.frozen = false;
    }
}
