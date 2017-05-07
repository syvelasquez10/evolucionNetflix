// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;
import java.util.Collection;
import java.util.Set;
import java.util.List;

class CachedPathEvaluator$2$3 implements Action
{
    final /* synthetic */ CachedPathEvaluator$2 this$1;
    final /* synthetic */ List val$collapsedPQLs;
    final /* synthetic */ PathMap val$pathMap;
    final /* synthetic */ Set val$paths;
    
    CachedPathEvaluator$2$3(final CachedPathEvaluator$2 this$1, final Set val$paths, final List val$collapsedPQLs, final PathMap val$pathMap) {
        this.this$1 = this$1;
        this.val$paths = val$paths;
        this.val$collapsedPQLs = val$collapsedPQLs;
        this.val$pathMap = val$pathMap;
    }
    
    @Override
    public void call() {
        final Iterator<PQL> iterator = this.val$paths.iterator();
        while (iterator.hasNext()) {
            this.val$collapsedPQLs.addAll(this.val$pathMap.translate((PQL)iterator.next().clone()));
        }
        PQL.collapse(this.val$collapsedPQLs);
    }
}
