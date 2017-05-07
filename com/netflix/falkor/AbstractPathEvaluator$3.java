// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

class AbstractPathEvaluator$3 implements Func1<PathBoundValue, Boolean>
{
    final /* synthetic */ AbstractPathEvaluator this$0;
    
    AbstractPathEvaluator$3(final AbstractPathEvaluator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Boolean call(final PathBoundValue pathBoundValue) {
        return !(pathBoundValue.getValue().getValue() instanceof PQL);
    }
}
