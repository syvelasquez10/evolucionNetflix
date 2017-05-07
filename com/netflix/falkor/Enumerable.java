// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Enumerable<T> implements Iterable<T>
{
    final Iterable<T> iterable;
    
    public Enumerable(final Iterable<T> iterable) {
        this.iterable = iterable;
    }
    
    public static <R> Enumerable<R> fromArray(final R[] array) {
        return new Enumerable<R>(new Iterable<R>() {
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
    
    public static <R> Enumerable<R> fromList(final List<R> list) {
        return new Enumerable<R>(new Iterable<R>() {
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
    
    @Override
    public Iterator<T> iterator() {
        return this.iterable.iterator();
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
