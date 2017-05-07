// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

class GraphObject$Factory$GraphObjectProxy$1 implements ParameterizedType
{
    final /* synthetic */ GraphObject$Factory$GraphObjectProxy this$0;
    final /* synthetic */ Class val$expectedType;
    
    GraphObject$Factory$GraphObjectProxy$1(final GraphObject$Factory$GraphObjectProxy this$0, final Class val$expectedType) {
        this.this$0 = this$0;
        this.val$expectedType = val$expectedType;
    }
    
    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] { this.val$expectedType };
    }
    
    @Override
    public Type getOwnerType() {
        return null;
    }
    
    @Override
    public Type getRawType() {
        return GraphObjectList.class;
    }
}
