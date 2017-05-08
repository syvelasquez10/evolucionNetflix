// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.content.Context;
import android.graphics.RectF;
import android.annotation.TargetApi;

@TargetApi(9)
class CardViewGingerbread implements CardViewImpl
{
    final RectF sCornerRect;
    
    CardViewGingerbread() {
        this.sCornerRect = new RectF();
    }
    
    private RoundRectDrawableWithShadow createBackground(final Context context, final ColorStateList list, final float n, final float n2, final float n3) {
        return new RoundRectDrawableWithShadow(context.getResources(), list, n, n2, n3);
    }
    
    private RoundRectDrawableWithShadow getShadowBackground(final CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawableWithShadow)cardViewDelegate.getCardBackground();
    }
    
    @Override
    public ColorStateList getBackgroundColor(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getColor();
    }
    
    @Override
    public float getElevation(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getShadowSize();
    }
    
    @Override
    public float getMaxElevation(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getMaxShadowSize();
    }
    
    @Override
    public float getMinHeight(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getMinHeight();
    }
    
    @Override
    public float getMinWidth(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getMinWidth();
    }
    
    @Override
    public float getRadius(final CardViewDelegate cardViewDelegate) {
        return this.getShadowBackground(cardViewDelegate).getCornerRadius();
    }
    
    @Override
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new CardViewGingerbread$1(this);
    }
    
    @Override
    public void initialize(final CardViewDelegate cardViewDelegate, final Context context, final ColorStateList list, final float n, final float n2, final float n3) {
        final RoundRectDrawableWithShadow background = this.createBackground(context, list, n, n2, n3);
        background.setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        cardViewDelegate.setCardBackground(background);
        this.updatePadding(cardViewDelegate);
    }
    
    @Override
    public void onCompatPaddingChanged(final CardViewDelegate cardViewDelegate) {
    }
    
    @Override
    public void onPreventCornerOverlapChanged(final CardViewDelegate cardViewDelegate) {
        this.getShadowBackground(cardViewDelegate).setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        this.updatePadding(cardViewDelegate);
    }
    
    @Override
    public void setBackgroundColor(final CardViewDelegate cardViewDelegate, final ColorStateList color) {
        this.getShadowBackground(cardViewDelegate).setColor(color);
    }
    
    @Override
    public void setElevation(final CardViewDelegate cardViewDelegate, final float shadowSize) {
        this.getShadowBackground(cardViewDelegate).setShadowSize(shadowSize);
    }
    
    @Override
    public void setMaxElevation(final CardViewDelegate cardViewDelegate, final float maxShadowSize) {
        this.getShadowBackground(cardViewDelegate).setMaxShadowSize(maxShadowSize);
        this.updatePadding(cardViewDelegate);
    }
    
    @Override
    public void setRadius(final CardViewDelegate cardViewDelegate, final float cornerRadius) {
        this.getShadowBackground(cardViewDelegate).setCornerRadius(cornerRadius);
        this.updatePadding(cardViewDelegate);
    }
    
    public void updatePadding(final CardViewDelegate cardViewDelegate) {
        final Rect rect = new Rect();
        this.getShadowBackground(cardViewDelegate).getMaxShadowAndCornerPadding(rect);
        cardViewDelegate.setMinWidthHeightInternal((int)Math.ceil(this.getMinWidth(cardViewDelegate)), (int)Math.ceil(this.getMinHeight(cardViewDelegate)));
        cardViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }
}
