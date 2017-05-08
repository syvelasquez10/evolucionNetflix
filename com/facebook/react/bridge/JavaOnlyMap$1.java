// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

class JavaOnlyMap$1 implements ReadableMapKeySetIterator
{
    Iterator<String> mIterator;
    final /* synthetic */ JavaOnlyMap this$0;
    
    JavaOnlyMap$1(final JavaOnlyMap this$0) {
        this.this$0 = this$0;
        this.mIterator = this.this$0.mBackingMap.keySet().iterator();
    }
    
    @Override
    public boolean hasNextKey() {
        return this.mIterator.hasNext();
    }
    
    @Override
    public String nextKey() {
        return this.mIterator.next();
    }
}
