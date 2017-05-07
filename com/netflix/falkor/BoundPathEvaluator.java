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
        return this.deleteAbsolute((Iterable<PQL>)new IterableBuilder<PQL>(iterable).map((Func1<PQL, Object>)new Func1<PQL, PQL>() {
            @Override
            public PQL call(final PQL pql) {
                return BoundPathEvaluator.this.getPath().prepend(pql);
            }
        }));
    }
    
    @Override
    public Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> iterable) {
        return this.getAbsolute((Iterable<PQL>)new IterableBuilder<PQL>(iterable).map((Func1<PQL, Object>)new Func1<PQL, PQL>() {
            @Override
            public PQL call(final PQL pql) {
                return pql.prepend(BoundPathEvaluator.this.getPath());
            }
        }));
    }
    
    @Override
    public AbstractPathEvaluator getRoot() {
        return this.root;
    }
    
    @Override
    public Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> iterable) {
        return this.setAbsolute((Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(iterable).map((Func1<PathBoundValue, Object>)new Func1<PathBoundValue, PathBoundValue>() {
            @Override
            public PathBoundValue call(final PathBoundValue pathBoundValue) {
                return new PathBoundValue(BoundPathEvaluator.this.getPath().prepend(pathBoundValue.getPath()), pathBoundValue.getValue());
            }
        }));
    }
}
