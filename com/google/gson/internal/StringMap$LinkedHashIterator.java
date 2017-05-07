// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.util.Arrays;
import java.util.Random;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.AbstractMap;
import java.util.NoSuchElementException;
import java.util.Iterator;

abstract class StringMap$LinkedHashIterator<T> implements Iterator<T>
{
    StringMap$LinkedEntry lastReturned;
    StringMap$LinkedEntry next;
    final /* synthetic */ StringMap this$0;
    
    private StringMap$LinkedHashIterator(final StringMap this$0) {
        this.this$0 = this$0;
        this.next = this.this$0.header.nxt;
        this.lastReturned = null;
    }
    
    @Override
    public final boolean hasNext() {
        return this.next != this.this$0.header;
    }
    
    final StringMap$LinkedEntry nextEntry() {
        final StringMap$LinkedEntry next = this.next;
        if (next == this.this$0.header) {
            throw new NoSuchElementException();
        }
        this.next = next.nxt;
        return this.lastReturned = next;
    }
    
    @Override
    public final void remove() {
        if (this.lastReturned == null) {
            throw new IllegalStateException();
        }
        this.this$0.remove(this.lastReturned.key);
        this.lastReturned = null;
    }
}
