// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;

public class Option<T> implements Iterable<T>
{
    private final boolean hasValue;
    private final T value;
    
    public Option() {
        this.value = null;
        this.hasValue = false;
    }
    
    public Option(final T value) {
        this.value = value;
        this.hasValue = (value != null);
    }
    
    public boolean getHasValue() {
        return this.hasValue;
    }
    
    public T getValue() {
        return this.value;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            boolean moved = false;
            
            @Override
            public boolean hasNext() {
                if (!this.moved) {
                    this.moved = true;
                    return Option.this.getHasValue();
                }
                return false;
            }
            
            @Override
            public T next() {
                return Option.this.getValue();
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public String toString() {
        if (this.getHasValue()) {
            return this.getValue().toString();
        }
        return "undefined";
    }
}
