// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.graphics.Region$Op;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.content.Context;

public class WPStandardCardImageView extends WPCardImageView
{
    private int cardPlusAntiAliasBorderRight;
    private int cardPlusShadowPlusAntiAliasBorderBottom;
    private int shadowPlusAntiAliasBorderTop;
    private boolean showShadowOnly;
    
    public WPStandardCardImageView(final Context context) {
        super(context, null);
        this.showShadowOnly = false;
    }
    
    public WPStandardCardImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.showShadowOnly = false;
    }
    
    @Override
    protected void init(final Context context) {
        final Resources resources = context.getResources();
        final int dimensionPixelSize = resources.getDimensionPixelSize(2131361951);
        final int dimensionPixelSize2 = resources.getDimensionPixelSize(2131361949);
        final int dimensionPixelSize3 = resources.getDimensionPixelSize(2131361950);
        this.cardPlusAntiAliasBorderRight = dimensionPixelSize + 2;
        this.cardPlusShadowPlusAntiAliasBorderBottom = dimensionPixelSize3 + 2;
        this.shadowPlusAntiAliasBorderTop = dimensionPixelSize2 + 1;
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.showShadowOnly) {
            canvas.clipRect(0.0f, (float)this.shadowPlusAntiAliasBorderTop, (float)this.cardPlusAntiAliasBorderRight, (float)this.cardPlusShadowPlusAntiAliasBorderBottom, Region$Op.INTERSECT);
        }
        else {
            canvas.clipRect(0.0f, 0.0f, (float)this.cardPlusAntiAliasBorderRight, (float)this.cardPlusShadowPlusAntiAliasBorderBottom, Region$Op.INTERSECT);
        }
        super.onDraw(canvas);
    }
    
    public void showShadowOnly(final boolean showShadowOnly) {
        if (this.showShadowOnly != showShadowOnly) {
            this.showShadowOnly = showShadowOnly;
            this.invalidate();
        }
    }
    
    @Override
    public String toString() {
        return "WPStandardCardImageView{showShadowOnly=" + this.showShadowOnly + ", cardPlusAntiAliasBorderRight=" + this.cardPlusAntiAliasBorderRight + ", cardPlusShadowPlusAntiAliasBorderBottom=" + this.cardPlusShadowPlusAntiAliasBorderBottom + ", shadowPlusAntiAliasBorderTop=" + this.shadowPlusAntiAliasBorderTop + "} " + super.toString();
    }
}
