// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

public class BoundPathEvaluator extends BasePathEvaluator
{
    AbstractPathEvaluator root;
    
    public BoundPathEvaluator(final AbstractPathEvaluator root, final PQL path) {
        this.root = root;
        this.setPath(path);
    }
    
    @Override
    public Iterable<PathBoundValue> deleteAbsolute(final Iterable<PQL> iterable) {
        return this.deleteAbsolute((Iterable<PQL>)new IterableBuilder<PQL>(iterable).map((Func1<PQL, Object>)new BoundPathEvaluator$3(this)));
    }
    
    @Override
    public Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> iterable) {
        return this.getAbsolute((Iterable<PQL>)new IterableBuilder<PQL>(iterable).map((Func1<PQL, Object>)new BoundPathEvaluator$1(this)));
    }
    
    @Override
    public AbstractPathEvaluator getRoot() {
        return this.root;
    }
    
    @Override
    public Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> iterable) {
        return this.setAbsolute((Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(iterable).map((Func1<PathBoundValue, Object>)new BoundPathEvaluator$2(this)));
    }
}
