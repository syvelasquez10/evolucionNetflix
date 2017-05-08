// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.jni;

import java.util.Map;
import java.util.Iterator;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class MapIteratorHelper
{
    @DoNotStrip
    private final Iterator<Map.Entry> mIterator;
    @DoNotStrip
    private Object mKey;
    @DoNotStrip
    private Object mValue;
    
    public MapIteratorHelper(final Map map) {
        this.mIterator = (Iterator<Map.Entry>)map.entrySet().iterator();
    }
    
    @DoNotStrip
    boolean hasNext() {
        if (this.mIterator.hasNext()) {
            final Map.Entry entry = this.mIterator.next();
            this.mKey = entry.getKey();
            this.mValue = entry.getValue();
            return true;
        }
        this.mKey = null;
        this.mValue = null;
        return false;
    }
}
