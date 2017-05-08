// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.common.futures.SimpleSettableFuture;

final class ModuleHolder$1 implements Runnable
{
    final /* synthetic */ SimpleSettableFuture val$future;
    final /* synthetic */ NativeModule val$module;
    
    ModuleHolder$1(final NativeModule val$module, final SimpleSettableFuture val$future) {
        this.val$module = val$module;
        this.val$future = val$future;
    }
    
    @Override
    public void run() {
        Systrace.beginSection(0L, "initializeOnUiThread");
        while (true) {
            try {
                this.val$module.initialize();
                this.val$future.set(null);
                Systrace.endSection(0L);
            }
            catch (Exception exception) {
                this.val$future.setException(exception);
                continue;
            }
            break;
        }
    }
}
