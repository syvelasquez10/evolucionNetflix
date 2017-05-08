// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.Outline;
import android.annotation.TargetApi;

@TargetApi(21)
class ActionBarBackgroundDrawableV21 extends ActionBarBackgroundDrawable
{
    public ActionBarBackgroundDrawableV21(final ActionBarContainer actionBarContainer) {
        super(actionBarContainer);
    }
    
    public void getOutline(final Outline outline) {
        if (this.mContainer.mIsSplit) {
            if (this.mContainer.mSplitBackground != null) {
                this.mContainer.mSplitBackground.getOutline(outline);
            }
        }
        else if (this.mContainer.mBackground != null) {
            this.mContainer.mBackground.getOutline(outline);
        }
    }
}
