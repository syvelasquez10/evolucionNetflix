// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;

public class CachedPathEvaluator extends BasePathEvaluator
{
    private static Func1<PathBoundValue, Boolean> stripMissingMemberSentinel;
    AbstractPathEvaluator cache;
    AbstractPathEvaluator source;
    
    static {
        CachedPathEvaluator.stripMissingMemberSentinel = new Func1<PathBoundValue, Boolean>() {
            @Override
            public Boolean call(final PathBoundValue pathBoundValue) {
                final Option value = pathBoundValue.getValue();
                return !value.getHasValue() || !(value.getValue() instanceof MissingMember);
            }
        };
    }
    
    public CachedPathEvaluator(final AbstractPathEvaluator source, final AbstractPathEvaluator cache) {
        this.source = source;
        this.cache = cache;
    }
    
    @Override
    public Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> iterable) {
        return (Iterable<PathBoundValue>)IterableBuilder.defer((Func<Iterable<Object>>)new Func<Iterable<PathBoundValue>>() {
            @Override
            public Iterable<PathBoundValue> call() {
                final ArrayList list = new ArrayList();
                final HashSet set = new HashSet();
                final PathMap pathMap = new PathMap();
                final PathMap pathMap2 = new PathMap();
                final Iterator<PQL> iterator = iterable.iterator();
                while (iterator.hasNext()) {
                    set.addAll(iterator.next().explode());
                }
                return (Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(CachedPathEvaluator.this.cache.getAbsolute(iterable)).doAction(new Action1<PathBoundValue>() {
                    @Override
                    public void call(final PathBoundValue pathBoundValue) {
                        final Option value = pathBoundValue.getValue();
                        if (value.getHasValue()) {
                            if (!(value.getValue() instanceof PQL)) {
                                set.removeAll(pathMap.translate(pathBoundValue.getPath()));
                                return;
                            }
                            pathMap.add(value.getValue(), pathBoundValue.getPath());
                            pathMap2.add(pathBoundValue.getPath(), value.getValue());
                        }
                    }
                }, new Action() {
                    @Override
                    public void call() {
                        final Iterator<PQL> iterator = set.iterator();
                        while (iterator.hasNext()) {
                            list.addAll(pathMap2.translate((PQL)iterator.next().clone()));
                        }
                        PQL.collapse(list);
                    }
                }).filter(CachedPathEvaluator.stripMissingMemberSentinel).concat((Iterable<PathBoundValue>)IterableBuilder.defer((Func<Iterable<Object>>)new Func<Iterable<PathBoundValue>>() {
                    @Override
                    public Iterable<PathBoundValue> call() {
                        return (Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(new IterableBuilder<PathBoundValue>(CachedPathEvaluator.this.cache.setAbsolute((Iterable<PathBoundValue>)new IterableBuilder<PathBoundValue>(CachedPathEvaluator.this.source.getAbsolute(list)).doAction(new Action1<PathBoundValue>() {
                            @Override
                            public void call(final PathBoundValue pathBoundValue) {
                                final Option value = pathBoundValue.getValue();
                                if (value.getHasValue()) {
                                    if (!(value.getValue() instanceof PQL)) {
                                        set.removeAll(pathMap.translate(pathBoundValue.getPath()));
                                        return;
                                    }
                                    pathMap.add(value.getValue(), pathBoundValue.getPath());
                                }
                            }
                        }).concat((Iterable<PathBoundValue>)IterableBuilder.defer((Func<Iterable<Object>>)new Func<Iterable<PathBoundValue>>() {
                            @Override
                            public Iterable<PathBoundValue> call() {
                                return (Iterable<PathBoundValue>)new IterableBuilder(set).map((Func1)new Func1<PQL, PathBoundValue>() {
                                    @Override
                                    public PathBoundValue call(final PQL pql) {
                                        return new PathBoundValue(pql, new Option<Object>(new MissingMember()));
                                    }
                                });
                            }
                        }))))).filter(CachedPathEvaluator.stripMissingMemberSentinel);
                    }
                }));
            }
        });
    }
    
    @Override
    public AbstractPathEvaluator getRoot() {
        return this;
    }
    
    @Override
    public Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> iterable) {
        throw new UnsupportedOperationException();
    }
    
    private class MissingMember implements PathBound
    {
        PQL path;
        
        private MissingMember() {
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
}
