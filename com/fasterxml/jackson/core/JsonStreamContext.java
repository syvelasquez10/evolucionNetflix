// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

public abstract class JsonStreamContext
{
    protected int _index;
    protected int _type;
    
    public final int getCurrentIndex() {
        if (this._index < 0) {
            return 0;
        }
        return this._index;
    }
    
    public final String getTypeDesc() {
        switch (this._type) {
            default: {
                return "?";
            }
            case 0: {
                return "ROOT";
            }
            case 1: {
                return "ARRAY";
            }
            case 2: {
                return "OBJECT";
            }
        }
    }
    
    public final boolean inArray() {
        return this._type == 1;
    }
    
    public final boolean inObject() {
        return this._type == 2;
    }
    
    public final boolean inRoot() {
        return this._type == 0;
    }
}
