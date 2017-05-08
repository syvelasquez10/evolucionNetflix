// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View$MeasureSpec;
import android.content.res.TypedArray;
import android.content.res.ColorStateList;
import android.support.v7.cardview.R$color;
import android.graphics.Color;
import android.support.v7.cardview.R$style;
import android.support.v7.cardview.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.widget.FrameLayout;

public class CardView extends FrameLayout
{
    private static final int[] COLOR_BACKGROUND_ATTR;
    private static final CardViewImpl IMPL;
    private final CardViewDelegate mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    int mUserSetMinHeight;
    int mUserSetMinWidth;
    
    static {
        COLOR_BACKGROUND_ATTR = new int[] { 16842801 };
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = new CardViewApi21();
        }
        else if (Build$VERSION.SDK_INT >= 17) {
            IMPL = new CardViewJellybeanMr1();
        }
        else {
            IMPL = new CardViewGingerbread();
        }
        CardView.IMPL.initStatic();
    }
    
    public CardView(final Context context) {
        super(context);
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.mCardViewDelegate = new CardView$1(this);
        this.initialize(context, null, 0);
    }
    
    public CardView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.mCardViewDelegate = new CardView$1(this);
        this.initialize(context, set, 0);
    }
    
    public CardView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.mCardViewDelegate = new CardView$1(this);
        this.initialize(context, set, n);
    }
    
    private void initialize(final Context context, final AttributeSet set, int n) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.CardView, n, R$style.CardView);
        ColorStateList list;
        if (obtainStyledAttributes.hasValue(R$styleable.CardView_cardBackgroundColor)) {
            list = obtainStyledAttributes.getColorStateList(R$styleable.CardView_cardBackgroundColor);
        }
        else {
            final TypedArray obtainStyledAttributes2 = this.getContext().obtainStyledAttributes(CardView.COLOR_BACKGROUND_ATTR);
            n = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            final float[] array = new float[3];
            Color.colorToHSV(n, array);
            if (array[2] > 0.5f) {
                n = this.getResources().getColor(R$color.cardview_light_background);
            }
            else {
                n = this.getResources().getColor(R$color.cardview_dark_background);
            }
            list = ColorStateList.valueOf(n);
        }
        final float dimension = obtainStyledAttributes.getDimension(R$styleable.CardView_cardCornerRadius, 0.0f);
        final float dimension2 = obtainStyledAttributes.getDimension(R$styleable.CardView_cardElevation, 0.0f);
        final float dimension3 = obtainStyledAttributes.getDimension(R$styleable.CardView_cardMaxElevation, 0.0f);
        this.mCompatPadding = obtainStyledAttributes.getBoolean(R$styleable.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = obtainStyledAttributes.getBoolean(R$styleable.CardView_cardPreventCornerOverlap, true);
        n = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPadding, 0);
        this.mContentPadding.left = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingLeft, n);
        this.mContentPadding.top = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingTop, n);
        this.mContentPadding.right = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingRight, n);
        this.mContentPadding.bottom = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingBottom, n);
        float n2 = dimension3;
        if (dimension2 > dimension3) {
            n2 = dimension2;
        }
        this.mUserSetMinWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minWidth, 0);
        this.mUserSetMinHeight = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minHeight, 0);
        obtainStyledAttributes.recycle();
        CardView.IMPL.initialize(this.mCardViewDelegate, context, list, dimension, dimension2, n2);
    }
    
    public ColorStateList getCardBackgroundColor() {
        return CardView.IMPL.getBackgroundColor(this.mCardViewDelegate);
    }
    
    public float getCardElevation() {
        return CardView.IMPL.getElevation(this.mCardViewDelegate);
    }
    
    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }
    
    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }
    
    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }
    
    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }
    
    public float getMaxCardElevation() {
        return CardView.IMPL.getMaxElevation(this.mCardViewDelegate);
    }
    
    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }
    
    public float getRadius() {
        return CardView.IMPL.getRadius(this.mCardViewDelegate);
    }
    
    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }
    
    protected void onMeasure(int measureSpec, int measureSpec2) {
        if (!(CardView.IMPL instanceof CardViewApi21)) {
            final int mode = View$MeasureSpec.getMode(measureSpec);
            switch (mode) {
                case Integer.MIN_VALUE:
                case 1073741824: {
                    measureSpec = View$MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(CardView.IMPL.getMinWidth(this.mCardViewDelegate)), View$MeasureSpec.getSize(measureSpec)), mode);
                    break;
                }
            }
            final int mode2 = View$MeasureSpec.getMode(measureSpec2);
            switch (mode2) {
                case Integer.MIN_VALUE:
                case 1073741824: {
                    measureSpec2 = View$MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(CardView.IMPL.getMinHeight(this.mCardViewDelegate)), View$MeasureSpec.getSize(measureSpec2)), mode2);
                    break;
                }
            }
            super.onMeasure(measureSpec, measureSpec2);
            return;
        }
        super.onMeasure(measureSpec, measureSpec2);
    }
    
    public void setCardBackgroundColor(final int n) {
        CardView.IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(n));
    }
    
    public void setCardBackgroundColor(final ColorStateList list) {
        CardView.IMPL.setBackgroundColor(this.mCardViewDelegate, list);
    }
    
    public void setCardElevation(final float n) {
        CardView.IMPL.setElevation(this.mCardViewDelegate, n);
    }
    
    public void setMaxCardElevation(final float n) {
        CardView.IMPL.setMaxElevation(this.mCardViewDelegate, n);
    }
    
    public void setMinimumHeight(final int mUserSetMinHeight) {
        super.setMinimumHeight(this.mUserSetMinHeight = mUserSetMinHeight);
    }
    
    public void setMinimumWidth(final int mUserSetMinWidth) {
        super.setMinimumWidth(this.mUserSetMinWidth = mUserSetMinWidth);
    }
    
    public void setPadding(final int n, final int n2, final int n3, final int n4) {
    }
    
    public void setPaddingRelative(final int n, final int n2, final int n3, final int n4) {
    }
    
    public void setPreventCornerOverlap(final boolean mPreventCornerOverlap) {
        if (mPreventCornerOverlap != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = mPreventCornerOverlap;
            CardView.IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
        }
    }
    
    public void setRadius(final float n) {
        CardView.IMPL.setRadius(this.mCardViewDelegate, n);
    }
    
    public void setUseCompatPadding(final boolean mCompatPadding) {
        if (this.mCompatPadding != mCompatPadding) {
            this.mCompatPadding = mCompatPadding;
            CardView.IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
        }
    }
}
