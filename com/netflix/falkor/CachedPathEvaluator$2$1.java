// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Set;
import java.util.List;

class CachedPathEvaluator$2$1 implements Func<Iterable<PathBoundValue>>
{
    final /* synthetic */ CachedPathEvaluator$2 this$1;
    final /* synthetic */ List val$collapsedPQLs;
    final /* synthetic */ Set val$paths;
    final /* synthetic */ PathMap val$reversePathMap;
    
    CachedPathEvaluator$2$1(final CachedPathEvaluator$2 this$1, final List val$collapsedPQLs, final PathMap val$reversePathMap, final Set val$paths) {
        this.this$1 = this$1;
        this.val$collapsedPQLs = val$collapsedPQLs;
        this.val$reversePathMap = val$reversePathMap;
        this.val$paths = val$paths;
    }
    
    @Override
    public Iterable<PathBoundValue> call() {
        return (Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(new IterableBuilder<PathBoundValue>(this.this$1.this$0.cache.setAbsolute((Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(this.this$1.this$0.source.getAbsolute(this.val$collapsedPQLs)).doAction(new CachedPathEvaluator$2$1$2(this)).concat((Iterable<PathBoundValue>)IterableBuilder.defer((Func<Iterable<Object>>)new CachedPathEvaluator$2$1$1(this)))))).filter(CachedPathEvaluator.stripMissingMemberSentinel);
    }
}
