// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;
import java.util.ArrayList;

public abstract class AbstractPathEvaluator
{
    private static Iterable<PathBoundValue> makeRelative(final Iterable<PathBoundValue> iterable) {
        return (Iterable<PathBoundValue>)IterableBuilder.defer((Func<Iterable<Object>>)new Func<Iterable<PathBoundValue>>() {
            final PathMap pathMap = new PathMap();
            
            @Override
            public Iterable<PathBoundValue> call() {
                return (Iterable<PathBoundValue>)new IterableBuilder(iterable).mapMany((Func1)new Func1<PathBoundValue, Iterable<PathBoundValue>>() {
                    @Override
                    public Iterable<PathBoundValue> call(final PathBoundValue pathBoundValue) {
                        final Option value = pathBoundValue.getValue();
                        if (value.getHasValue() && value.getValue() instanceof PQL) {
                            Func.this.pathMap.add((PQL)pathBoundValue.getValue().getValue(), pathBoundValue.getPath());
                        }
                        final ArrayList<PathBoundValue> list = new ArrayList<PathBoundValue>();
                        final Iterator<PQL> iterator = Func.this.pathMap.translate(pathBoundValue.getPath()).iterator();
                        while (iterator.hasNext()) {
                            list.add(new PathBoundValue(iterator.next(), value));
                        }
                        return (Iterable<PathBoundValue>)list;
                    }
                });
            }
        });
    }
    
    public abstract AbstractPathEvaluator bind(final PQL p0);
    
    public AbstractPathEvaluator cache(final AbstractPathEvaluator abstractPathEvaluator) {
        if (PQL.EMPTY.equals(this.getPath())) {
            return new CachedPathEvaluator(this, abstractPathEvaluator);
        }
        return new CachedPathEvaluator(this.getRoot(), abstractPathEvaluator.getRoot()).bind(this.getPath());
    }
    
    public Iterable<PathBoundValue> delete(final PQL... array) {
        return (Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(this.deleteRelative(IterableBuilder.fromArray(array))).filter(new Func1<PathBoundValue, Boolean>() {
            @Override
            public Boolean call(final PathBoundValue pathBoundValue) {
                return !(pathBoundValue.getValue().getValue() instanceof PQL);
            }
        });
    }
    
    public abstract Iterable<PathBoundValue> deleteAbsolute(final Iterable<PQL> p0);
    
    public Iterable<PathBoundValue> deleteRelative(final Iterable<PQL> iterable) {
        return makeRelative(this.deleteAbsolute(iterable));
    }
    
    public Iterable<PathBoundValue> get(final PQL... array) {
        return (Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(this.getRelative(IterableBuilder.fromArray(array))).filter(new Func1<PathBoundValue, Boolean>() {
            @Override
            public Boolean call(final PathBoundValue pathBoundValue) {
                return !(pathBoundValue.getValue().getValue() instanceof PQL);
            }
        });
    }
    
    public abstract Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> p0);
    
    public abstract PQL getPath();
    
    public Iterable<PathBoundValue> getRelative(final Iterable<PQL> iterable) {
        return makeRelative(this.getAbsolute(iterable));
    }
    
    public abstract AbstractPathEvaluator getRoot();
    
    public Option getValue(final PQL pql) {
        final Iterator<T> iterator = new IterableBuilder<PathBoundValue>(this.getRelative(new Option<PQL>(pql))).filter(new Func1<PathBoundValue, Boolean>() {
            @Override
            public Boolean call(final PathBoundValue pathBoundValue) {
                return !(pathBoundValue.getValue().getValue() instanceof PQL);
            }
        }).map((Func1<PathBoundValue, T>)new Func1<PathBoundValue, Option>() {
            @Override
            public Option call(final PathBoundValue pathBoundValue) {
                return pathBoundValue.getValue();
            }
        }).iterator();
        if (iterator.hasNext()) {
            return new Option(iterator.next());
        }
        return new Option();
    }
    
    public abstract Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> p0);
    
    protected abstract void setPath(final PQL p0);
}
