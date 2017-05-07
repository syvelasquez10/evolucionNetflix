// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

class AbstractPathEvaluator$1 implements Func1<PathBoundValue, Option>
{
    final /* synthetic */ AbstractPathEvaluator this$0;
    
    AbstractPathEvaluator$1(final AbstractPathEvaluator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Option call(final PathBoundValue pathBoundValue) {
        return pathBoundValue.getValue();
    }
}
