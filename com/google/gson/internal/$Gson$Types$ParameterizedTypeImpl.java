// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.util.NoSuchElementException;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Properties;
import java.util.Collection;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.lang.reflect.GenericArrayType;
import java.util.Arrays;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.io.Serializable;

final class $Gson$Types$ParameterizedTypeImpl implements Serializable, ParameterizedType
{
    private static final long serialVersionUID = 0L;
    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;
    
    public $Gson$Types$ParameterizedTypeImpl(Type canonicalize, final Type type, final Type... array) {
        final boolean b = true;
        int i = 0;
        if (type instanceof Class) {
            final Class clazz = (Class)type;
            $Gson$Preconditions.checkArgument(canonicalize != null || clazz.getEnclosingClass() == null);
            boolean b2 = b;
            if (canonicalize != null) {
                b2 = (clazz.getEnclosingClass() != null && b);
            }
            $Gson$Preconditions.checkArgument(b2);
        }
        if (canonicalize == null) {
            canonicalize = null;
        }
        else {
            canonicalize = $Gson$Types.canonicalize(canonicalize);
        }
        this.ownerType = canonicalize;
        this.rawType = $Gson$Types.canonicalize(type);
        this.typeArguments = array.clone();
        while (i < this.typeArguments.length) {
            $Gson$Preconditions.checkNotNull(this.typeArguments[i]);
            checkNotPrimitive(this.typeArguments[i]);
            this.typeArguments[i] = $Gson$Types.canonicalize(this.typeArguments[i]);
            ++i;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof ParameterizedType && $Gson$Types.equals(this, (Type)o);
    }
    
    @Override
    public Type[] getActualTypeArguments() {
        return this.typeArguments.clone();
    }
    
    @Override
    public Type getOwnerType() {
        return this.ownerType;
    }
    
    @Override
    public Type getRawType() {
        return this.rawType;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ hashCodeOrZero(this.ownerType);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
        sb.append($Gson$Types.typeToString(this.rawType));
        if (this.typeArguments.length == 0) {
            return sb.toString();
        }
        sb.append("<").append($Gson$Types.typeToString(this.typeArguments[0]));
        for (int i = 1; i < this.typeArguments.length; ++i) {
            sb.append(", ").append($Gson$Types.typeToString(this.typeArguments[i]));
        }
        return sb.append(">").toString();
    }
}
