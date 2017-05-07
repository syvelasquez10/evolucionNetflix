// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

final class AbstractPathEvaluator$5 implements Func<Iterable<PathBoundValue>>
{
    final PathMap pathMap;
    final /* synthetic */ Iterable val$pathBoundValues;
    
    AbstractPathEvaluator$5(final Iterable val$pathBoundValues) {
        this.val$pathBoundValues = val$pathBoundValues;
        this.pathMap = new PathMap();
    }
    
    @Override
    public Iterable<PathBoundValue> call() {
        return (Iterable<PathBoundValue>)new IterableBuilder(this.val$pathBoundValues).mapMany((Func1)new AbstractPathEvaluator$5$1(this));
    }
}
