// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

class CachedPathEvaluator$MissingMember implements PathBound
{
    PQL path;
    final /* synthetic */ CachedPathEvaluator this$0;
    
    private CachedPathEvaluator$MissingMember(final CachedPathEvaluator this$0) {
        this.this$0 = this$0;
        this.path = PQL.EMPTY;
    }
    
    @Override
    public PQL getPath() {
        return this.path;
    }
    
    @Override
    public void setPath(final PQL path) {
        this.path = path;
    }
}
