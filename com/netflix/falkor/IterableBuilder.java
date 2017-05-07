// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class IterableBuilder<T> implements Iterable<T>
{
    final Iterable<T> iterable;
    
    public IterableBuilder(final Iterable<T> iterable) {
        this.iterable = iterable;
    }
    
    public static <R> IterableBuilder<R> defer(final Func<Iterable<R>> func) {
        return new IterableBuilder<R>(new Iterable<R>() {
            @Override
            public Iterator<R> iterator() {
                return func.call().iterator();
            }
        });
    }
    
    public static <R> IterableBuilder<R> fromArray(final R[] array) {
        return new IterableBuilder<R>(new Iterable<R>() {
            @Override
            public Iterator<R> iterator() {
                return new Iterator<R>() {
                    Integer counter = null;
                    
                    @Override
                    public boolean hasNext() {
                        boolean b = false;
                        if (this.counter == null) {
                            this.counter = 0;
                        }
                        else {
                            final Integer counter = this.counter;
                            ++this.counter;
                        }
                        if (this.counter < array.length) {
                            b = true;
                        }
                        return b;
                    }
                    
                    @Override
                    public R next() {
                        return array[this.counter];
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        });
    }
    
    public static <R> IterableBuilder<R> fromList(final List<R> list) {
        return new IterableBuilder<R>(new Iterable<R>() {
            @Override
            public Iterator<R> iterator() {
                return new Iterator<R>() {
                    Integer counter = null;
                    
                    @Override
                    public boolean hasNext() {
                        boolean b = false;
                        if (this.counter == null) {
                            this.counter = 0;
                        }
                        else {
                            final Integer counter = this.counter;
                            ++this.counter;
                        }
                        if (this.counter < list.size()) {
                            b = true;
                        }
                        return b;
                    }
                    
                    @Override
                    public R next() {
                        return list.get(this.counter);
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        });
    }
    
    public IterableBuilder<T> concat(final Iterable<T> iterable) {
        return new IterableBuilder<T>(new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    Iterator<T> iterator = IterableBuilder.this.iterable.iterator();
                    boolean onSecond = false;
                    T value;
                    
                    @Override
                    public boolean hasNext() {
                        boolean b = this.iterator.hasNext();
                        if (!b) {
                            if (!this.onSecond) {
                                this.onSecond = true;
                                this.iterator = iterable.iterator();
                                b = this.hasNext();
                            }
                            return b;
                        }
                        this.value = this.iterator.next();
                        return b;
                    }
                    
                    @Override
                    public T next() {
                        return this.value;
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        });
    }
    
    public IterableBuilder<T> doAction(final Action1<T> action1) {
        return new IterableBuilder<T>(new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    Iterator<T> iterator = IterableBuilder.this.iterator();
                    
                    @Override
                    public boolean hasNext() {
                        return this.iterator.hasNext();
                    }
                    
                    @Override
                    public T next() {
                        final T next = this.iterator.next();
                        action1.call(next);
                        return next;
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        });
    }
    
    public IterableBuilder<T> doAction(final Action1<T> action1, final Action action2) {
        return new IterableBuilder<T>(new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    Iterator<T> iterator = IterableBuilder.this.iterator();
                    
                    @Override
                    public boolean hasNext() {
                        final boolean hasNext = this.iterator.hasNext();
                        if (!hasNext) {
                            action2.call();
                        }
                        return hasNext;
                    }
                    
                    @Override
                    public T next() {
                        final T next = this.iterator.next();
                        action1.call(next);
                        return next;
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        });
    }
    
    public IterableBuilder<T> filter(final Func1<T, Boolean> func1) {
        return new IterableBuilder<T>(new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    Iterator iterator = IterableBuilder.this.iterable.iterator();
                    T value;
                    
                    @Override
                    public boolean hasNext() {
                        Func1 val$func;
                        T next;
                        boolean hasNext;
                        do {
                            hasNext = this.iterator.hasNext();
                            if (!hasNext) {
                                break;
                            }
                            val$func = func1;
                            next = this.iterator.next();
                            this.value = next;
                        } while (!val$func.call(next));
                        return hasNext;
                    }
                    
                    @Override
                    public T next() {
                        return this.value;
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        });
    }
    
    @Override
    public Iterator<T> iterator() {
        return this.iterable.iterator();
    }
    
    public <R> IterableBuilder<R> map(final Func1<T, R> func1) {
        return new IterableBuilder<R>(new Iterable<R>() {
            @Override
            public Iterator<R> iterator() {
                return new Iterator<R>() {
                    Iterator iterator = IterableBuilder.this.iterable.iterator();
                    
                    @Override
                    public boolean hasNext() {
                        return this.iterator.hasNext();
                    }
                    
                    @Override
                    public R next() {
                        return func1.call(this.iterator.next());
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        });
    }
    
    public <R> IterableBuilder<R> mapMany(final Func1<T, Iterable<R>> func1) {
        return new IterableBuilder<R>(new Iterable<R>() {
            @Override
            public Iterator<R> iterator() {
                return new Iterator<R>() {
                    Iterator<T> iterator = IterableBuilder.this.iterable.iterator();
                    Iterable<R> subIterable = null;
                    Iterator subIterator = null;
                    
                    @Override
                    public boolean hasNext() {
                        if (this.subIterator == null || !this.subIterator.hasNext()) {
                            boolean hasNext;
                            if (!this.iterator.hasNext()) {
                                hasNext = false;
                            }
                            else {
                                this.subIterable = func1.call(this.iterator.next());
                                this.subIterator = this.subIterable.iterator();
                                while (true) {
                                    final boolean b = hasNext = this.subIterator.hasNext();
                                    if (b) {
                                        break;
                                    }
                                    hasNext = b;
                                    if (!this.iterator.hasNext()) {
                                        break;
                                    }
                                    this.subIterable = func1.call(this.iterator.next());
                                    this.subIterator = this.subIterable.iterator();
                                }
                            }
                            return hasNext;
                        }
                        return true;
                    }
                    
                    @Override
                    public R next() {
                        return this.subIterator.next();
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        });
    }
    
    public List<T> toList() {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<T>();
        final Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return (List<T>)list;
    }
}
