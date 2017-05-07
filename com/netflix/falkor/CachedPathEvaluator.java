// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

public class CachedPathEvaluator extends BasePathEvaluator
{
    private static Func1<PathBoundValue, Boolean> stripMissingMemberSentinel;
    AbstractPathEvaluator cache;
    AbstractPathEvaluator source;
    
    static {
        CachedPathEvaluator.stripMissingMemberSentinel = new CachedPathEvaluator$1();
    }
    
    public CachedPathEvaluator(final AbstractPathEvaluator source, final AbstractPathEvaluator cache) {
        this.source = source;
        this.cache = cache;
    }
    
    @Override
    public Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> iterable) {
        return (Iterable<PathBoundValue>)IterableBuilder.defer((Func<Iterable<Object>>)new CachedPathEvaluator$2(this, iterable));
    }
    
    @Override
    public AbstractPathEvaluator getRoot() {
        return this;
    }
    
    @Override
    public Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> iterable) {
        throw new UnsupportedOperationException();
    }
}
