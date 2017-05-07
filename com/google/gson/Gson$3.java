// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import java.lang.reflect.Type;

class Gson$3 implements JsonSerializationContext
{
    final /* synthetic */ Gson this$0;
    
    Gson$3(final Gson this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public JsonElement serialize(final Object o) {
        return this.this$0.toJsonTree(o);
    }
    
    @Override
    public JsonElement serialize(final Object o, final Type type) {
        return this.this$0.toJsonTree(o, type);
    }
}
