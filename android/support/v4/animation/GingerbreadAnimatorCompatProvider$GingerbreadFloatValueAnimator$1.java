// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.animation;

import java.util.ArrayList;
import android.view.View;
import java.util.List;

class GingerbreadAnimatorCompatProvider$GingerbreadFloatValueAnimator$1 implements Runnable
{
    final /* synthetic */ GingerbreadAnimatorCompatProvider$GingerbreadFloatValueAnimator this$0;
    
    GingerbreadAnimatorCompatProvider$GingerbreadFloatValueAnimator$1(final GingerbreadAnimatorCompatProvider$GingerbreadFloatValueAnimator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        float n = (this.this$0.getTime() - this.this$0.mStartTime) * 1.0f / this.this$0.mDuration;
        if (n > 1.0f || this.this$0.mTarget.getParent() == null) {
            n = 1.0f;
        }
        this.this$0.mFraction = n;
        this.this$0.notifyUpdateListeners();
        if (this.this$0.mFraction >= 1.0f) {
            this.this$0.dispatchEnd();
            return;
        }
        this.this$0.mTarget.postDelayed(this.this$0.mLoopRunnable, 16L);
    }
}
