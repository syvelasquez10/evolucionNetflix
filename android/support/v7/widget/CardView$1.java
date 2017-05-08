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
import android.view.View;
import android.graphics.drawable.Drawable;

class CardView$1 implements CardViewDelegate
{
    private Drawable mCardBackground;
    final /* synthetic */ CardView this$0;
    
    CardView$1(final CardView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Drawable getCardBackground() {
        return this.mCardBackground;
    }
    
    @Override
    public View getCardView() {
        return (View)this.this$0;
    }
    
    @Override
    public boolean getPreventCornerOverlap() {
        return this.this$0.getPreventCornerOverlap();
    }
    
    @Override
    public boolean getUseCompatPadding() {
        return this.this$0.getUseCompatPadding();
    }
    
    @Override
    public void setCardBackground(final Drawable drawable) {
        this.mCardBackground = drawable;
        this.this$0.setBackgroundDrawable(drawable);
    }
    
    @Override
    public void setMinWidthHeightInternal(final int n, final int n2) {
        if (n > this.this$0.mUserSetMinWidth) {
            CardView.access$101(this.this$0, n);
        }
        if (n2 > this.this$0.mUserSetMinHeight) {
            CardView.access$201(this.this$0, n2);
        }
    }
    
    @Override
    public void setShadowPadding(final int n, final int n2, final int n3, final int n4) {
        this.this$0.mShadowBounds.set(n, n2, n3, n4);
        CardView.access$001(this.this$0, this.this$0.mContentPadding.left + n, this.this$0.mContentPadding.top + n2, this.this$0.mContentPadding.right + n3, this.this$0.mContentPadding.bottom + n4);
    }
}
