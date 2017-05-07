// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

class BoundPathEvaluator$3 implements Func1<PQL, PQL>
{
    final /* synthetic */ BoundPathEvaluator this$0;
    
    BoundPathEvaluator$3(final BoundPathEvaluator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public PQL call(final PQL pql) {
        return this.this$0.getPath().prepend(pql);
    }
}
