// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Collection;

class CachedPathEvaluator$2$1$2 implements Action1<PathBoundValue>
{
    final /* synthetic */ CachedPathEvaluator$2$1 this$2;
    
    CachedPathEvaluator$2$1$2(final CachedPathEvaluator$2$1 this$2) {
        this.this$2 = this$2;
    }
    
    @Override
    public void call(final PathBoundValue pathBoundValue) {
        final Option value = pathBoundValue.getValue();
        if (value.getHasValue()) {
            if (!(value.getValue() instanceof PQL)) {
                this.this$2.val$paths.removeAll(this.this$2.val$reversePathMap.translate(pathBoundValue.getPath()));
                return;
            }
            this.this$2.val$reversePathMap.add(value.getValue(), pathBoundValue.getPath());
        }
    }
}
