// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View$OnTouchListener;
import android.view.View;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.content.Context;
import android.support.v7.internal.widget.TintImageView;

class ActionMenuPresenter$OverflowMenuButton extends TintImageView implements ActionMenuView$ActionMenuChildView
{
    private final float[] mTempPts;
    final /* synthetic */ ActionMenuPresenter this$0;
    
    public ActionMenuPresenter$OverflowMenuButton(final ActionMenuPresenter this$0, final Context context) {
        this.this$0 = this$0;
        super(context, null, R$attr.actionOverflowButtonStyle);
        this.mTempPts = new float[2];
        this.setClickable(true);
        this.setFocusable(true);
        this.setVisibility(0);
        this.setEnabled(true);
        this.setOnTouchListener((View$OnTouchListener)new ActionMenuPresenter$OverflowMenuButton$1(this, (View)this, this$0));
    }
    
    @Override
    public boolean needsDividerAfter() {
        return false;
    }
    
    @Override
    public boolean needsDividerBefore() {
        return false;
    }
    
    public boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        this.playSoundEffect(0);
        this.this$0.showOverflowMenu();
        return true;
    }
    
    protected boolean setFrame(int n, int height, int paddingTop, int paddingBottom) {
        final boolean setFrame = super.setFrame(n, height, paddingTop, paddingBottom);
        final Drawable drawable = this.getDrawable();
        final Drawable background = this.getBackground();
        if (drawable != null && background != null) {
            final int width = this.getWidth();
            height = this.getHeight();
            n = Math.max(width, height) / 2;
            final int paddingLeft = this.getPaddingLeft();
            final int paddingRight = this.getPaddingRight();
            paddingTop = this.getPaddingTop();
            paddingBottom = this.getPaddingBottom();
            final int n2 = (width + (paddingLeft - paddingRight)) / 2;
            height = (height + (paddingTop - paddingBottom)) / 2;
            DrawableCompat.setHotspotBounds(background, n2 - n, height - n, n2 + n, height + n);
        }
        return setFrame;
    }
}
