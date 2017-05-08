// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.OnBatchCompleteListener;

class NativeModuleRegistry$1 implements OnBatchCompleteListener
{
    final /* synthetic */ NativeModuleRegistry this$0;
    final /* synthetic */ ModuleHolder val$holder;
    
    NativeModuleRegistry$1(final NativeModuleRegistry this$0, final ModuleHolder val$holder) {
        this.this$0 = this$0;
        this.val$holder = val$holder;
    }
    
    @Override
    public void onBatchComplete() {
        ((OnBatchCompleteListener)this.val$holder.getModule()).onBatchComplete();
    }
}
