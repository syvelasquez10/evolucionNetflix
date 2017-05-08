// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import java.util.HashMap;
import java.util.Map;

public class JavaOnlyMap implements ReadableMap, WritableMap
{
    private final Map mBackingMap;
    
    public JavaOnlyMap() {
        this.mBackingMap = new HashMap();
    }
    
    private JavaOnlyMap(final Object... array) {
        if (array.length % 2 != 0) {
            throw new IllegalArgumentException("You must provide the same number of keys and values");
        }
        this.mBackingMap = new HashMap();
        for (int i = 0; i < array.length; i += 2) {
            this.mBackingMap.put(array[i], array[i + 1]);
        }
    }
    
    public static JavaOnlyMap of(final Object... array) {
        return new JavaOnlyMap(array);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final JavaOnlyMap javaOnlyMap = (JavaOnlyMap)o;
            if (this.mBackingMap != null) {
                if (this.mBackingMap.equals(javaOnlyMap.mBackingMap)) {
                    return true;
                }
            }
            else if (javaOnlyMap.mBackingMap == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public JavaOnlyArray getArray(final String s) {
        return this.mBackingMap.get(s);
    }
    
    @Override
    public boolean getBoolean(final String s) {
        return this.mBackingMap.get(s);
    }
    
    @Override
    public double getDouble(final String s) {
        return this.mBackingMap.get(s);
    }
    
    @Override
    public Dynamic getDynamic(final String s) {
        return DynamicFromMap.create(this, s);
    }
    
    @Override
    public int getInt(final String s) {
        return this.mBackingMap.get(s);
    }
    
    @Override
    public JavaOnlyMap getMap(final String s) {
        return this.mBackingMap.get(s);
    }
    
    @Override
    public String getString(final String s) {
        return this.mBackingMap.get(s);
    }
    
    @Override
    public ReadableType getType(final String s) {
        final Dynamic value = this.mBackingMap.get(s);
        if (value == null) {
            return ReadableType.Null;
        }
        if (value instanceof Number) {
            return ReadableType.Number;
        }
        if (value instanceof String) {
            return ReadableType.String;
        }
        if (value instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if (value instanceof ReadableMap) {
            return ReadableType.Map;
        }
        if (value instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (value instanceof Dynamic) {
            return value.getType();
        }
        throw new IllegalArgumentException("Invalid value " + value.toString() + " for key " + s + "contained in JavaOnlyMap");
    }
    
    @Override
    public boolean hasKey(final String s) {
        return this.mBackingMap.containsKey(s);
    }
    
    @Override
    public int hashCode() {
        if (this.mBackingMap != null) {
            return this.mBackingMap.hashCode();
        }
        return 0;
    }
    
    @Override
    public boolean isNull(final String s) {
        return this.mBackingMap.get(s) == null;
    }
    
    @Override
    public ReadableMapKeySetIterator keySetIterator() {
        return new JavaOnlyMap$1(this);
    }
    
    @Override
    public void putArray(final String s, final WritableArray writableArray) {
        this.mBackingMap.put(s, writableArray);
    }
    
    @Override
    public void putBoolean(final String s, final boolean b) {
        this.mBackingMap.put(s, b);
    }
    
    @Override
    public void putDouble(final String s, final double n) {
        this.mBackingMap.put(s, n);
    }
    
    @Override
    public void putInt(final String s, final int n) {
        this.mBackingMap.put(s, n);
    }
    
    @Override
    public void putMap(final String s, final WritableMap writableMap) {
        this.mBackingMap.put(s, writableMap);
    }
    
    @Override
    public void putString(final String s, final String s2) {
        this.mBackingMap.put(s, s2);
    }
    
    @Override
    public String toString() {
        return this.mBackingMap.toString();
    }
}
