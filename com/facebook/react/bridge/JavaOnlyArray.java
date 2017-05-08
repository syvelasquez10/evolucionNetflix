// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class JavaOnlyArray implements ReadableArray, WritableArray
{
    private final List mBackingList;
    
    public JavaOnlyArray() {
        this.mBackingList = new ArrayList();
    }
    
    private JavaOnlyArray(final List list) {
        this.mBackingList = new ArrayList(list);
    }
    
    public static JavaOnlyArray from(final List list) {
        return new JavaOnlyArray(list);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final JavaOnlyArray javaOnlyArray = (JavaOnlyArray)o;
            if (this.mBackingList != null) {
                if (this.mBackingList.equals(javaOnlyArray.mBackingList)) {
                    return true;
                }
            }
            else if (javaOnlyArray.mBackingList == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public JavaOnlyArray getArray(final int n) {
        return this.mBackingList.get(n);
    }
    
    @Override
    public boolean getBoolean(final int n) {
        return this.mBackingList.get(n);
    }
    
    @Override
    public double getDouble(final int n) {
        return this.mBackingList.get(n);
    }
    
    @Override
    public int getInt(final int n) {
        return this.mBackingList.get(n);
    }
    
    @Override
    public JavaOnlyMap getMap(final int n) {
        return this.mBackingList.get(n);
    }
    
    @Override
    public String getString(final int n) {
        return this.mBackingList.get(n);
    }
    
    @Override
    public int hashCode() {
        if (this.mBackingList != null) {
            return this.mBackingList.hashCode();
        }
        return 0;
    }
    
    @Override
    public void pushArray(final WritableArray writableArray) {
        this.mBackingList.add(writableArray);
    }
    
    @Override
    public void pushBoolean(final boolean b) {
        this.mBackingList.add(b);
    }
    
    @Override
    public void pushInt(final int n) {
        this.mBackingList.add(n);
    }
    
    @Override
    public void pushMap(final WritableMap writableMap) {
        this.mBackingList.add(writableMap);
    }
    
    @Override
    public void pushNull() {
        this.mBackingList.add(null);
    }
    
    @Override
    public void pushString(final String s) {
        this.mBackingList.add(s);
    }
    
    @Override
    public int size() {
        return this.mBackingList.size();
    }
    
    @Override
    public String toString() {
        return this.mBackingList.toString();
    }
}
