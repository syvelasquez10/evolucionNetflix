// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.util.Log;
import android.view.MotionEvent;
import android.annotation.TargetApi;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.View$MeasureSpec;
import android.content.res.Resources;
import android.support.v4.content.res.ConfigurationHelper;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.support.design.R$dimen;
import android.widget.ImageView;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatImageHelper;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;

@CoordinatorLayout$DefaultBehavior(FloatingActionButton$Behavior.class)
public class FloatingActionButton extends VisibilityAwareImageButton
{
    private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
    private static final String LOG_TAG = "FloatingActionButton";
    public static final int SIZE_AUTO = -1;
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL = 0;
    private ColorStateList mBackgroundTint;
    private PorterDuff$Mode mBackgroundTintMode;
    private int mBorderWidth;
    boolean mCompatPadding;
    private AppCompatImageHelper mImageHelper;
    int mImagePadding;
    private FloatingActionButtonImpl mImpl;
    private int mMaxImageSize;
    private int mRippleColor;
    final Rect mShadowPadding;
    private int mSize;
    private final Rect mTouchArea;
    
    public FloatingActionButton(final Context context) {
        this(context, null);
    }
    
    public FloatingActionButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public FloatingActionButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mShadowPadding = new Rect();
        this.mTouchArea = new Rect();
        ThemeUtils.checkAppCompatTheme(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.FloatingActionButton, n, R$style.Widget_Design_FloatingActionButton);
        this.mBackgroundTint = obtainStyledAttributes.getColorStateList(R$styleable.FloatingActionButton_backgroundTint);
        this.mBackgroundTintMode = ViewUtils.parseTintMode(obtainStyledAttributes.getInt(R$styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.mRippleColor = obtainStyledAttributes.getColor(R$styleable.FloatingActionButton_rippleColor, 0);
        this.mSize = obtainStyledAttributes.getInt(R$styleable.FloatingActionButton_fabSize, -1);
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.FloatingActionButton_borderWidth, 0);
        final float dimension = obtainStyledAttributes.getDimension(R$styleable.FloatingActionButton_elevation, 0.0f);
        final float dimension2 = obtainStyledAttributes.getDimension(R$styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.mCompatPadding = obtainStyledAttributes.getBoolean(R$styleable.FloatingActionButton_useCompatPadding, false);
        obtainStyledAttributes.recycle();
        (this.mImageHelper = new AppCompatImageHelper((ImageView)this)).loadFromAttributes(set, n);
        this.mMaxImageSize = (int)this.getResources().getDimension(R$dimen.design_fab_image_size);
        this.getImpl().setBackgroundDrawable(this.mBackgroundTint, this.mBackgroundTintMode, this.mRippleColor, this.mBorderWidth);
        this.getImpl().setElevation(dimension);
        this.getImpl().setPressedTranslationZ(dimension2);
    }
    
    private FloatingActionButtonImpl createImpl() {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            return new FloatingActionButtonLollipop(this, new FloatingActionButton$ShadowDelegateImpl(this), ViewUtils.DEFAULT_ANIMATOR_CREATOR);
        }
        if (sdk_INT >= 14) {
            return new FloatingActionButtonIcs(this, new FloatingActionButton$ShadowDelegateImpl(this), ViewUtils.DEFAULT_ANIMATOR_CREATOR);
        }
        return new FloatingActionButtonGingerbread(this, new FloatingActionButton$ShadowDelegateImpl(this), ViewUtils.DEFAULT_ANIMATOR_CREATOR);
    }
    
    private FloatingActionButtonImpl getImpl() {
        if (this.mImpl == null) {
            this.mImpl = this.createImpl();
        }
        return this.mImpl;
    }
    
    private int getSizeDimension(final int n) {
        final Resources resources = this.getResources();
        switch (n) {
            default: {
                return resources.getDimensionPixelSize(R$dimen.design_fab_size_normal);
            }
            case -1: {
                if (Math.max(ConfigurationHelper.getScreenWidthDp(resources), ConfigurationHelper.getScreenHeightDp(resources)) < 470) {
                    return this.getSizeDimension(1);
                }
                return this.getSizeDimension(0);
            }
            case 1: {
                return resources.getDimensionPixelSize(R$dimen.design_fab_size_mini);
            }
        }
    }
    
    private static int resolveAdjustedSize(final int n, int size) {
        final int mode = View$MeasureSpec.getMode(size);
        size = View$MeasureSpec.getSize(size);
        switch (mode) {
            default: {
                return n;
            }
            case Integer.MIN_VALUE: {
                return Math.min(n, size);
            }
            case 1073741824: {
                return size;
            }
        }
    }
    
    private FloatingActionButtonImpl$InternalVisibilityChangedListener wrapOnVisibilityChangedListener(final FloatingActionButton$OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener) {
        if (floatingActionButton$OnVisibilityChangedListener == null) {
            return null;
        }
        return new FloatingActionButton$1(this, floatingActionButton$OnVisibilityChangedListener);
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.getImpl().onDrawableStateChanged(this.getDrawableState());
    }
    
    public ColorStateList getBackgroundTintList() {
        return this.mBackgroundTint;
    }
    
    public PorterDuff$Mode getBackgroundTintMode() {
        return this.mBackgroundTintMode;
    }
    
    public float getCompatElevation() {
        return this.getImpl().getElevation();
    }
    
    public Drawable getContentBackground() {
        return this.getImpl().getContentBackground();
    }
    
    public boolean getContentRect(final Rect rect) {
        boolean b = false;
        if (ViewCompat.isLaidOut((View)this)) {
            rect.set(0, 0, this.getWidth(), this.getHeight());
            rect.left += this.mShadowPadding.left;
            rect.top += this.mShadowPadding.top;
            rect.right -= this.mShadowPadding.right;
            rect.bottom -= this.mShadowPadding.bottom;
            b = true;
        }
        return b;
    }
    
    public int getRippleColor() {
        return this.mRippleColor;
    }
    
    public int getSize() {
        return this.mSize;
    }
    
    int getSizeDimension() {
        return this.getSizeDimension(this.mSize);
    }
    
    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }
    
    public void hide() {
        this.hide(null);
    }
    
    public void hide(final FloatingActionButton$OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener) {
        this.hide(floatingActionButton$OnVisibilityChangedListener, true);
    }
    
    void hide(final FloatingActionButton$OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener, final boolean b) {
        this.getImpl().hide(this.wrapOnVisibilityChangedListener(floatingActionButton$OnVisibilityChangedListener), b);
    }
    
    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.getImpl().jumpDrawableToCurrentState();
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getImpl().onAttachedToWindow();
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.getImpl().onDetachedFromWindow();
    }
    
    protected void onMeasure(int min, final int n) {
        final int sizeDimension = this.getSizeDimension();
        this.mImagePadding = (sizeDimension - this.mMaxImageSize) / 2;
        this.getImpl().updatePadding();
        min = Math.min(resolveAdjustedSize(sizeDimension, min), resolveAdjustedSize(sizeDimension, n));
        this.setMeasuredDimension(this.mShadowPadding.left + min + this.mShadowPadding.right, min + this.mShadowPadding.top + this.mShadowPadding.bottom);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                if (this.getContentRect(this.mTouchArea) && !this.mTouchArea.contains((int)motionEvent.getX(), (int)motionEvent.getY())) {
                    return false;
                }
                break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setBackgroundColor(final int n) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }
    
    public void setBackgroundDrawable(final Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }
    
    public void setBackgroundResource(final int n) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }
    
    public void setBackgroundTintList(final ColorStateList list) {
        if (this.mBackgroundTint != list) {
            this.mBackgroundTint = list;
            this.getImpl().setBackgroundTintList(list);
        }
    }
    
    public void setBackgroundTintMode(final PorterDuff$Mode porterDuff$Mode) {
        if (this.mBackgroundTintMode != porterDuff$Mode) {
            this.mBackgroundTintMode = porterDuff$Mode;
            this.getImpl().setBackgroundTintMode(porterDuff$Mode);
        }
    }
    
    public void setCompatElevation(final float elevation) {
        this.getImpl().setElevation(elevation);
    }
    
    public void setImageResource(final int imageResource) {
        this.mImageHelper.setImageResource(imageResource);
    }
    
    public void setRippleColor(final int n) {
        if (this.mRippleColor != n) {
            this.mRippleColor = n;
            this.getImpl().setRippleColor(n);
        }
    }
    
    public void setSize(final int mSize) {
        if (mSize != this.mSize) {
            this.mSize = mSize;
            this.requestLayout();
        }
    }
    
    public void setUseCompatPadding(final boolean mCompatPadding) {
        if (this.mCompatPadding != mCompatPadding) {
            this.mCompatPadding = mCompatPadding;
            this.getImpl().onCompatShadowChanged();
        }
    }
    
    public void show() {
        this.show(null);
    }
    
    public void show(final FloatingActionButton$OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener) {
        this.show(floatingActionButton$OnVisibilityChangedListener, true);
    }
    
    void show(final FloatingActionButton$OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener, final boolean b) {
        this.getImpl().show(this.wrapOnVisibilityChangedListener(floatingActionButton$OnVisibilityChangedListener), b);
    }
}
