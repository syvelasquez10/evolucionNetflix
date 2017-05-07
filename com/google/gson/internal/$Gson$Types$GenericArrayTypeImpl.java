// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.lang.reflect.Type;
import java.lang.reflect.GenericArrayType;
import java.io.Serializable;

final class $Gson$Types$GenericArrayTypeImpl implements Serializable, GenericArrayType
{
    private static final long serialVersionUID = 0L;
    private final Type componentType;
    
    public $Gson$Types$GenericArrayTypeImpl(final Type type) {
        this.componentType = $Gson$Types.canonicalize(type);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof GenericArrayType && $Gson$Types.equals(this, (Type)o);
    }
    
    @Override
    public Type getGenericComponentType() {
        return this.componentType;
    }
    
    @Override
    public int hashCode() {
        return this.componentType.hashCode();
    }
    
    @Override
    public String toString() {
        return $Gson$Types.typeToString(this.componentType) + "[]";
    }
}
