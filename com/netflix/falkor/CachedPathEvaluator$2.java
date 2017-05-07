// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;

class CachedPathEvaluator$2 implements Func<Iterable<PathBoundValue>>
{
    final /* synthetic */ CachedPathEvaluator this$0;
    final /* synthetic */ Iterable val$pqls;
    
    CachedPathEvaluator$2(final CachedPathEvaluator this$0, final Iterable val$pqls) {
        this.this$0 = this$0;
        this.val$pqls = val$pqls;
    }
    
    @Override
    public Iterable<PathBoundValue> call() {
        final ArrayList list = new ArrayList();
        final HashSet set = new HashSet();
        final PathMap pathMap = new PathMap();
        final PathMap pathMap2 = new PathMap();
        final Iterator<PQL> iterator = this.val$pqls.iterator();
        while (iterator.hasNext()) {
            set.addAll(iterator.next().explode());
        }
        return (Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(this.this$0.cache.getAbsolute(this.val$pqls)).doAction(new CachedPathEvaluator$2$2(this, pathMap, pathMap2, set), new CachedPathEvaluator$2$3(this, set, list, pathMap2)).filter(CachedPathEvaluator.stripMissingMemberSentinel).concat((Iterable<PathBoundValue>)IterableBuilder.defer((Func<Iterable<Object>>)new CachedPathEvaluator$2$1(this, list, pathMap, set)));
    }
}
