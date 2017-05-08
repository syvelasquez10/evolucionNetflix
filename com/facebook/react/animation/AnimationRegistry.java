// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animation;

import com.facebook.react.bridge.UiThreadUtil;
import android.util.SparseArray;

public class AnimationRegistry
{
    private final SparseArray<Animation> mRegistry;
    
    public AnimationRegistry() {
        this.mRegistry = (SparseArray<Animation>)new SparseArray();
    }
    
    public Animation getAnimation(final int n) {
        UiThreadUtil.assertOnUiThread();
        return (Animation)this.mRegistry.get(n);
    }
    
    public void registerAnimation(final Animation animation) {
        UiThreadUtil.assertOnUiThread();
        this.mRegistry.put(animation.getAnimationID(), (Object)animation);
    }
    
    public Animation removeAnimation(final int n) {
        UiThreadUtil.assertOnUiThread();
        final Animation animation = (Animation)this.mRegistry.get(n);
        if (animation != null) {
            this.mRegistry.delete(n);
        }
        return animation;
    }
}
