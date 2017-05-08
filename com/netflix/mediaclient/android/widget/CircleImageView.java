// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.Matrix;
import android.graphics.Shader$TileMode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff$Mode;
import android.net.Uri;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.Log;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.os.Build$VERSION;
import android.graphics.Paint$Style;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Bitmap;

public class CircleImageView extends AdvancedImageView
{
    private static final int SHADOW_COLOR = -16777216;
    private static final boolean SHADOW_ENABLED = false;
    private static final float SHADOW_RADIUS = 1.0f;
    private static final float SHADOW_X = 0.0f;
    private static final float SHADOW_Y = 2.0f;
    private static final String TAG = "CircleImageView";
    private int mBorderWidth;
    private int mCanvasSize;
    private boolean mHasBorder;
    private boolean mHasSelector;
    private Bitmap mImage;
    private Paint mPaint;
    private Paint mPaintBorder;
    private Paint mPaintSelectorBorder;
    private boolean mSelected;
    private ColorFilter mSelectorFilter;
    private int mSelectorStrokeWidth;
    private BitmapShader mShader;
    private int mShadowColor;
    private boolean mShadowEnabled;
    private float mShadowRadius;
    private float mShadowX;
    private float mShadowY;
    
    public CircleImageView(final Context context) {
        this(context, null, 0);
    }
    
    public CircleImageView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public CircleImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init(context, set, n);
    }
    
    private void init(final Context context, final AttributeSet set, int n) {
        (this.mPaint = new Paint()).setAntiAlias(true);
        (this.mPaintBorder = new Paint()).setAntiAlias(true);
        this.mPaintBorder.setStyle(Paint$Style.STROKE);
        (this.mPaintSelectorBorder = new Paint()).setAntiAlias(true);
        if (Build$VERSION.SDK_INT >= 11) {
            this.setLayerType(1, (Paint)null);
        }
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.CircleImageView, n, 0);
        this.mHasBorder = obtainStyledAttributes.getBoolean(0, false);
        this.mHasSelector = obtainStyledAttributes.getBoolean(3, false);
        this.mShadowEnabled = obtainStyledAttributes.getBoolean(7, false);
        if (this.mHasBorder) {
            this.setBorderWidth(obtainStyledAttributes.getDimensionPixelOffset(2, (int)(context.getResources().getDisplayMetrics().density * 2.0f + 0.5f)));
            this.setBorderColor(obtainStyledAttributes.getColor(1, -1));
        }
        if (this.mHasSelector) {
            n = (int)(context.getResources().getDisplayMetrics().density * 2.0f + 0.5f);
            this.setSelectorColor(obtainStyledAttributes.getColor(4, 0));
            this.setSelectorStrokeWidth(obtainStyledAttributes.getDimensionPixelOffset(6, n));
            this.setSelectorStrokeColor(obtainStyledAttributes.getColor(5, -16776961));
        }
        if (this.mShadowEnabled) {
            this.mShadowRadius = obtainStyledAttributes.getFloat(8, 1.0f);
            this.mShadowX = obtainStyledAttributes.getFloat(9, 0.0f);
            this.mShadowY = obtainStyledAttributes.getFloat(10, 2.0f);
            this.mShadowColor = obtainStyledAttributes.getColor(11, -16777216);
            this.setShadowEnabled(true);
        }
        obtainStyledAttributes.recycle();
    }
    
    private int measureHeight(int n) {
        final int mode = View$MeasureSpec.getMode(n);
        n = View$MeasureSpec.getSize(n);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            n = this.mCanvasSize;
        }
        return n + 2;
    }
    
    private int measureWidth(int size) {
        final int mode = View$MeasureSpec.getMode(size);
        size = View$MeasureSpec.getSize(size);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            return this.mCanvasSize;
        }
        return size;
    }
    
    private void updateShadow() {
        float mShadowRadius;
        if (this.mShadowEnabled) {
            mShadowRadius = this.mShadowRadius;
        }
        else {
            mShadowRadius = 0.0f;
        }
        this.mPaintBorder.setShadowLayer(mShadowRadius, this.mShadowX, this.mShadowY, this.mShadowColor);
        this.mPaintSelectorBorder.setShadowLayer(mShadowRadius, this.mShadowX, this.mShadowY, this.mShadowColor);
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        if (!this.isClickable()) {
            this.mSelected = false;
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0: {
                this.mSelected = true;
                break;
            }
            case 1:
            case 3:
            case 4:
            case 8: {
                this.mSelected = false;
                break;
            }
        }
        this.invalidate();
        return super.dispatchTouchEvent(motionEvent);
    }
    
    public Bitmap drawableToBitmap(final Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                Log.i("CircleImageView", "Bitmap drawable!");
                return ((BitmapDrawable)drawable).getBitmap();
            }
            final int intrinsicWidth = drawable.getIntrinsicWidth();
            final int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                try {
                    final Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap$Config.ARGB_8888);
                    final Canvas canvas = new Canvas(bitmap);
                    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawable.draw(canvas);
                    return bitmap;
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    Log.e("CircleImageView", "Encountered OutOfMemoryError while generating bitmap!");
                    return null;
                }
            }
        }
        return null;
    }
    
    public boolean isSelected() {
        return this.mSelected;
    }
    
    public void onDraw(final Canvas canvas) {
        int n = 0;
        if (this.mImage != null && this.mImage.getHeight() != 0 && this.mImage.getWidth() != 0) {
            final int mCanvasSize = this.mCanvasSize;
            int mCanvasSize2;
            if (this.getWidth() < this.getHeight()) {
                mCanvasSize2 = this.getWidth();
            }
            else {
                mCanvasSize2 = this.getHeight();
            }
            this.mCanvasSize = mCanvasSize2;
            if (mCanvasSize != this.mCanvasSize) {
                this.updateBitmapShader();
            }
            this.mPaint.setShader((Shader)this.mShader);
            int n2 = this.mCanvasSize / 2;
            if (this.mHasSelector && this.mSelected) {
                n = this.mSelectorStrokeWidth;
                n2 = (this.mCanvasSize - n * 2) / 2;
                this.mPaint.setColorFilter(this.mSelectorFilter);
                canvas.drawCircle((float)(n2 + n), (float)(n2 + n), (this.mCanvasSize - n * 2) / 2 + n - 4.0f, this.mPaintSelectorBorder);
            }
            else if (this.mHasBorder) {
                n = this.mBorderWidth;
                n2 = (this.mCanvasSize - n * 2) / 2;
                this.mPaint.setColorFilter((ColorFilter)null);
                canvas.drawArc(new RectF((float)(n / 2 + 0), (float)(n / 2 + 0), (float)(this.mCanvasSize - n / 2), (float)(this.mCanvasSize - n / 2)), 360.0f, 360.0f, false, this.mPaintBorder);
            }
            else {
                this.mPaint.setColorFilter((ColorFilter)null);
            }
            canvas.drawCircle((float)(n2 + n), (float)(n2 + n), (float)((this.mCanvasSize - n * 2) / 2), this.mPaint);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.setMeasuredDimension(this.measureWidth(n), this.measureHeight(n2));
    }
    
    @Override
    public void setBorderColor(final int color) {
        if (this.mPaintBorder != null) {
            this.mPaintBorder.setColor(color);
        }
        this.invalidate();
    }
    
    @Override
    public void setBorderWidth(final int mBorderWidth) {
        this.mBorderWidth = mBorderWidth;
        if (this.mPaintBorder != null) {
            this.mPaintBorder.setStrokeWidth((float)mBorderWidth);
        }
        this.requestLayout();
        this.invalidate();
    }
    
    @Override
    public void setImageBitmap(final Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.mImage = bitmap;
        if (this.mCanvasSize > 0) {
            this.updateBitmapShader();
        }
    }
    
    @Override
    public void setImageDrawable(final Drawable imageDrawable) {
        super.setImageDrawable(imageDrawable);
        this.mImage = this.drawableToBitmap(this.getDrawable());
        if (this.mCanvasSize > 0) {
            this.updateBitmapShader();
        }
    }
    
    @Override
    public void setImageResource(final int imageResource) {
        super.setImageResource(imageResource);
        this.mImage = this.drawableToBitmap(this.getDrawable());
        if (this.mCanvasSize > 0) {
            this.updateBitmapShader();
        }
    }
    
    @Override
    public void setImageURI(final Uri imageURI) {
        super.setImageURI(imageURI);
        this.mImage = this.drawableToBitmap(this.getDrawable());
        if (this.mCanvasSize > 0) {
            this.updateBitmapShader();
        }
    }
    
    public void setSelectorColor(final int n) {
        this.mSelectorFilter = (ColorFilter)new PorterDuffColorFilter(n, PorterDuff$Mode.SRC_ATOP);
        this.invalidate();
    }
    
    public void setSelectorStrokeColor(final int color) {
        if (this.mPaintSelectorBorder != null) {
            this.mPaintSelectorBorder.setColor(color);
        }
        this.invalidate();
    }
    
    public void setSelectorStrokeWidth(final int mSelectorStrokeWidth) {
        this.mSelectorStrokeWidth = mSelectorStrokeWidth;
        this.requestLayout();
        this.invalidate();
    }
    
    public void setShadow(final float mShadowRadius, final float mShadowX, final float mShadowY, final int mShadowColor) {
        this.mShadowRadius = mShadowRadius;
        this.mShadowX = mShadowX;
        this.mShadowY = mShadowY;
        this.mShadowColor = mShadowColor;
        this.updateShadow();
    }
    
    public void setShadowEnabled(final boolean mShadowEnabled) {
        this.mShadowEnabled = mShadowEnabled;
        this.updateShadow();
    }
    
    public void updateBitmapShader() {
        if (this.mImage != null) {
            this.mShader = new BitmapShader(this.mImage, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
            if (this.mCanvasSize != this.mImage.getWidth() || this.mCanvasSize != this.mImage.getHeight()) {
                final Matrix localMatrix = new Matrix();
                final float n = this.mCanvasSize / this.mImage.getWidth();
                localMatrix.setScale(n, n);
                this.mShader.setLocalMatrix(localMatrix);
            }
        }
    }
}
