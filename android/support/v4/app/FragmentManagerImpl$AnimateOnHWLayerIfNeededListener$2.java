// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.animation.Animation;
import android.view.View;
import android.view.animation.Animation$AnimationListener;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;

class FragmentManagerImpl$AnimateOnHWLayerIfNeededListener$2 implements Runnable
{
    final /* synthetic */ FragmentManagerImpl$AnimateOnHWLayerIfNeededListener this$0;
    
    FragmentManagerImpl$AnimateOnHWLayerIfNeededListener$2(final FragmentManagerImpl$AnimateOnHWLayerIfNeededListener this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        ViewCompat.setLayerType(this.this$0.mView, 0, null);
    }
}
