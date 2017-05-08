// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;

class ReactModalHostView$DialogRootViewGroup$1 implements Runnable
{
    final /* synthetic */ ReactModalHostView$DialogRootViewGroup this$0;
    final /* synthetic */ int val$h;
    final /* synthetic */ int val$w;
    
    ReactModalHostView$DialogRootViewGroup$1(final ReactModalHostView$DialogRootViewGroup this$0, final int val$w, final int val$h) {
        this.this$0 = this$0;
        this.val$w = val$w;
        this.val$h = val$h;
    }
    
    @Override
    public void run() {
        ((ReactContext)this.this$0.getContext()).getNativeModule(UIManagerModule.class).updateNodeSize(this.this$0.getChildAt(0).getId(), this.val$w, this.val$h);
    }
}
