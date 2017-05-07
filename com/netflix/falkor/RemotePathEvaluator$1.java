// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;

class RemotePathEvaluator$1 implements Iterable<PathBoundValue>
{
    final /* synthetic */ RemotePathEvaluator this$0;
    final /* synthetic */ StringBuilder val$builder;
    
    RemotePathEvaluator$1(final RemotePathEvaluator this$0, final StringBuilder val$builder) {
        this.this$0 = this$0;
        this.val$builder = val$builder;
    }
    
    @Override
    public Iterator<PathBoundValue> iterator() {
        return new RemotePathEvaluator$1$1(this);
    }
}
