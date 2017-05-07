// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.content.Context;
import android.app.Activity;

class ActionBarDrawerToggle$DrawerArrowDrawableToggle extends DrawerArrowDrawable implements ActionBarDrawerToggle$DrawerToggle
{
    private final Activity mActivity;
    
    public ActionBarDrawerToggle$DrawerArrowDrawableToggle(final Activity mActivity, final Context context) {
        super(context);
        this.mActivity = mActivity;
    }
    
    @Override
    boolean isLayoutRtl() {
        return ViewCompat.getLayoutDirection(this.mActivity.getWindow().getDecorView()) == 1;
    }
    
    @Override
    public void setPosition(final float progress) {
        if (progress == 1.0f) {
            this.setVerticalMirror(true);
        }
        else if (progress == 0.0f) {
            this.setVerticalMirror(false);
        }
        super.setProgress(progress);
    }
}
