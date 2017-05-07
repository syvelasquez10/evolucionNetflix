// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import java.util.HashMap;
import com.google.gson.reflect.TypeToken;
import java.util.Map;

class Gson$1 extends ThreadLocal<Map<TypeToken<?>, Gson$FutureTypeAdapter<?>>>
{
    final /* synthetic */ Gson this$0;
    
    Gson$1(final Gson this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    protected Map<TypeToken<?>, Gson$FutureTypeAdapter<?>> initialValue() {
        return new HashMap<TypeToken<?>, Gson$FutureTypeAdapter<?>>();
    }
}
