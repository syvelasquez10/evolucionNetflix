// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

class CachedPathEvaluator$2$1$1 implements Func<Iterable<PathBoundValue>>
{
    final /* synthetic */ CachedPathEvaluator$2$1 this$2;
    
    CachedPathEvaluator$2$1$1(final CachedPathEvaluator$2$1 this$2) {
        this.this$2 = this$2;
    }
    
    @Override
    public Iterable<PathBoundValue> call() {
        return (Iterable<PathBoundValue>)new IterableBuilder(this.this$2.val$paths).map((Func1)new CachedPathEvaluator$2$1$1$1(this));
    }
}
