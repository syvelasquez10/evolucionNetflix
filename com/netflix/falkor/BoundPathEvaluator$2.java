// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

class BoundPathEvaluator$2 implements Func1<PathBoundValue, PathBoundValue>
{
    final /* synthetic */ BoundPathEvaluator this$0;
    
    BoundPathEvaluator$2(final BoundPathEvaluator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public PathBoundValue call(final PathBoundValue pathBoundValue) {
        return new PathBoundValue(this.this$0.getPath().prepend(pathBoundValue.getPath()), pathBoundValue.getValue());
    }
}
