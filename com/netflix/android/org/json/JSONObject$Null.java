// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

final class JSONObject$Null
{
    @Override
    protected final Object clone() {
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == null || o == this;
    }
    
    @Override
    public String toString() {
        return "null";
    }
}
