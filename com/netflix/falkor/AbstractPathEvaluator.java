// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;

public abstract class AbstractPathEvaluator
{
    private static Iterable<PathBoundValue> makeRelative(final Iterable<PathBoundValue> iterable) {
        return (Iterable<PathBoundValue>)IterableBuilder.defer((Func<Iterable<Object>>)new AbstractPathEvaluator$5(iterable));
    }
    
    public abstract AbstractPathEvaluator bind(final PQL p0);
    
    public AbstractPathEvaluator cache(final AbstractPathEvaluator abstractPathEvaluator) {
        if (PQL.EMPTY.equals(this.getPath())) {
            return new CachedPathEvaluator(this, abstractPathEvaluator);
        }
        return new CachedPathEvaluator(this.getRoot(), abstractPathEvaluator.getRoot()).bind(this.getPath());
    }
    
    public Iterable<PathBoundValue> delete(final PQL... array) {
        return (Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(this.deleteRelative(IterableBuilder.fromArray(array))).filter(new AbstractPathEvaluator$4(this));
    }
    
    public abstract Iterable<PathBoundValue> deleteAbsolute(final Iterable<PQL> p0);
    
    public Iterable<PathBoundValue> deleteRelative(final Iterable<PQL> iterable) {
        return makeRelative(this.deleteAbsolute(iterable));
    }
    
    public Iterable<PathBoundValue> get(final PQL... array) {
        return (Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(this.getRelative(IterableBuilder.fromArray(array))).filter(new AbstractPathEvaluator$3(this));
    }
    
    public abstract Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> p0);
    
    public abstract PQL getPath();
    
    public Iterable<PathBoundValue> getRelative(final Iterable<PQL> iterable) {
        return makeRelative(this.getAbsolute(iterable));
    }
    
    public abstract AbstractPathEvaluator getRoot();
    
    public Option getValue(final PQL pql) {
        final Iterator<T> iterator = new IterableBuilder<PathBoundValue>(this.getRelative(new Option<PQL>(pql))).filter(new AbstractPathEvaluator$2(this)).map((Func1<PathBoundValue, T>)new AbstractPathEvaluator$1(this)).iterator();
        if (iterator.hasNext()) {
            return new Option(iterator.next());
        }
        return new Option();
    }
    
    public abstract Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> p0);
    
    protected abstract void setPath(final PQL p0);
}
