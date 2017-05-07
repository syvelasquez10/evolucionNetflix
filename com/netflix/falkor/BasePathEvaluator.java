// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

public abstract class BasePathEvaluator extends AbstractPathEvaluator
{
    protected PQL path;
    
    public BasePathEvaluator() {
        this.path = PQL.EMPTY;
    }
    
    @Override
    public AbstractPathEvaluator bind(final PQL pql) {
        return new BoundPathEvaluator(this.getRoot(), this.getPath().append(pql));
    }
    
    @Override
    public Iterable<PathBoundValue> deleteAbsolute(final Iterable<PQL> iterable) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public PQL getPath() {
        return this.path;
    }
    
    @Override
    protected void setPath(final PQL path) {
        this.path = path;
    }
}
