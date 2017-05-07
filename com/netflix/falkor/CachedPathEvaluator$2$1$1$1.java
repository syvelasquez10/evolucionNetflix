// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

class CachedPathEvaluator$2$1$1$1 implements Func1<PQL, PathBoundValue>
{
    final /* synthetic */ CachedPathEvaluator$2$1$1 this$3;
    
    CachedPathEvaluator$2$1$1$1(final CachedPathEvaluator$2$1$1 this$3) {
        this.this$3 = this$3;
    }
    
    @Override
    public PathBoundValue call(final PQL pql) {
        return new PathBoundValue(pql, new Option<Object>(new CachedPathEvaluator$MissingMember(this.this$3.this$2.this$1.this$0, null)));
    }
}
