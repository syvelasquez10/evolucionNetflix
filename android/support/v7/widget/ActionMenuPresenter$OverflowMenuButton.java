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
    
    protected boolean setFrame(int n, final int n2, final int n3, final int n4) {
        final boolean setFrame = super.setFrame(n, n2, n3, n4);
        final Drawable drawable = this.getDrawable();
        final Drawable background = this.getBackground();
        if (drawable != null && background != null) {
            final float[] mTempPts = this.mTempPts;
            mTempPts[0] = drawable.getBounds().centerX();
            this.getImageMatrix().mapPoints(mTempPts);
            n = (int)mTempPts[0] - this.getWidth() / 2;
            DrawableCompat.setHotspotBounds(background, n, 0, this.getWidth() + n, this.getHeight());
        }
        return setFrame;
    }
}
