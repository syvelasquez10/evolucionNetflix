// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Collection;
import java.util.Set;

class CachedPathEvaluator$2$2 implements Action1<PathBoundValue>
{
    final /* synthetic */ CachedPathEvaluator$2 this$1;
    final /* synthetic */ PathMap val$pathMap;
    final /* synthetic */ Set val$paths;
    final /* synthetic */ PathMap val$reversePathMap;
    
    CachedPathEvaluator$2$2(final CachedPathEvaluator$2 this$1, final PathMap val$reversePathMap, final PathMap val$pathMap, final Set val$paths) {
        this.this$1 = this$1;
        this.val$reversePathMap = val$reversePathMap;
        this.val$pathMap = val$pathMap;
        this.val$paths = val$paths;
    }
    
    @Override
    public void call(final PathBoundValue pathBoundValue) {
        final Option value = pathBoundValue.getValue();
        if (value.getHasValue()) {
            if (!(value.getValue() instanceof PQL)) {
                this.val$paths.removeAll(this.val$reversePathMap.translate(pathBoundValue.getPath()));
                return;
            }
            this.val$reversePathMap.add(value.getValue(), pathBoundValue.getPath());
            this.val$pathMap.add(pathBoundValue.getPath(), value.getValue());
        }
    }
}
