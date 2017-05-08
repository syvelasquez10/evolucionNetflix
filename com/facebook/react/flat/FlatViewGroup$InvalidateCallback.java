// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.image.ImageLoadEvent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import java.lang.ref.WeakReference;

final class FlatViewGroup$InvalidateCallback extends WeakReference<FlatViewGroup>
{
    private FlatViewGroup$InvalidateCallback(final FlatViewGroup flatViewGroup) {
        super(flatViewGroup);
    }
    
    public void dispatchImageLoadEvent(final int n, final int n2) {
        final FlatViewGroup flatViewGroup = this.get();
        if (flatViewGroup == null) {
            return;
        }
        ((ReactContext)flatViewGroup.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(new ImageLoadEvent(n, n2));
    }
    
    public void invalidate() {
        final FlatViewGroup flatViewGroup = this.get();
        if (flatViewGroup != null) {
            flatViewGroup.invalidate();
        }
    }
}
