// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.webclient.model.leafs.ThumbMessaging;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaCta;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

public final class AutoValueGson_AutoValueAdapterFactory extends AutoValueAdapterFactory
{
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Class<? super T> rawType = typeToken.getRawType();
        if (UmaCta.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>)UmaCta.typeAdapter(gson);
        }
        if (UmaAlert.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>)UmaAlert.typeAdapter(gson);
        }
        if (ThumbMessaging.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>)ThumbMessaging.typeAdapter(gson);
        }
        if (EogAlert.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>)EogAlert.typeAdapter(gson);
        }
        return null;
    }
}
