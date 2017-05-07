// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.View;
import android.os.Build$VERSION;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import android.support.v4.view.ViewCompat;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.InsetDrawable;

class ActionBarDrawerToggle$SlideDrawable extends InsetDrawable implements Drawable$Callback
{
    private final boolean mHasMirroring;
    private float mOffset;
    private float mPosition;
    private final Rect mTmpRect;
    final /* synthetic */ ActionBarDrawerToggle this$0;
    
    public void draw(final Canvas canvas) {
        int n = 1;
        this.copyBounds(this.mTmpRect);
        canvas.save();
        boolean b;
        if (ViewCompat.getLayoutDirection(this.this$0.mActivity.getWindow().getDecorView()) == 1) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            n = -1;
        }
        final int width = this.mTmpRect.width();
        canvas.translate(n * (-this.mOffset * width * this.mPosition), 0.0f);
        if (b && !this.mHasMirroring) {
            canvas.translate((float)width, 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        super.draw(canvas);
        canvas.restore();
    }
    
    public float getPosition() {
        return this.mPosition;
    }
    
    public void setPosition(final float mPosition) {
        this.mPosition = mPosition;
        this.invalidateSelf();
    }
}
