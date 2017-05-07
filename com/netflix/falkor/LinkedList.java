// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T>
{
    LinkedList<T> next;
    T value;
    
    public LinkedList(final T value) {
        this.value = value;
    }
    
    public LinkedList(final T value, final LinkedList<T> next) {
        this.value = value;
        this.next = next;
    }
    
    public LinkedList<T> getNext() {
        return this.next;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public List<T> toList() {
        final ArrayList<T> list = new ArrayList<T>();
        for (LinkedList next = this; next != null; next = next.next) {
            list.add(next.value);
        }
        return list;
    }
}
