// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.cxxbridge.ModuleHolder;
import com.facebook.react.bridge.OnBatchCompleteListener;

class NativeModuleRegistryBuilder$1 implements OnBatchCompleteListener
{
    final /* synthetic */ NativeModuleRegistryBuilder this$0;
    final /* synthetic */ ModuleHolder val$moduleHolder;
    
    NativeModuleRegistryBuilder$1(final NativeModuleRegistryBuilder this$0, final ModuleHolder val$moduleHolder) {
        this.this$0 = this$0;
        this.val$moduleHolder = val$moduleHolder;
    }
    
    @Override
    public void onBatchComplete() {
        ((OnBatchCompleteListener)this.val$moduleHolder.getModule()).onBatchComplete();
    }
}
