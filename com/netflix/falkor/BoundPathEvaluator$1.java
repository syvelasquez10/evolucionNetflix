// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

class BoundPathEvaluator$1 implements Func1<PQL, PQL>
{
    final /* synthetic */ BoundPathEvaluator this$0;
    
    BoundPathEvaluator$1(final BoundPathEvaluator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public PQL call(final PQL pql) {
        return pql.prepend(this.this$0.getPath());
    }
}
