// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

import com.google.gson.TypeAdapterFactory;

public abstract class AutoValueAdapterFactory implements TypeAdapterFactory
{
    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueAdapterFactory();
    }
}
