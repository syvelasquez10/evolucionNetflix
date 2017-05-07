// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import java.util.Arrays;

public final class OpenGraphObject$Factory
{
    public static <T extends OpenGraphObject> T createForPost(final Class<T> clazz, final String s) {
        return createForPost(clazz, s, null, null, null, null);
    }
    
    public static <T extends OpenGraphObject> T createForPost(final Class<T> clazz, final String type, final String title, final String s, final String url, final String description) {
        final OpenGraphObject openGraphObject = GraphObject$Factory.create(clazz);
        if (type != null) {
            openGraphObject.setType(type);
        }
        if (title != null) {
            openGraphObject.setTitle(title);
        }
        if (s != null) {
            openGraphObject.setImageUrls(Arrays.asList(s));
        }
        if (url != null) {
            openGraphObject.setUrl(url);
        }
        if (description != null) {
            openGraphObject.setDescription(description);
        }
        openGraphObject.setCreateObject(true);
        openGraphObject.setData(GraphObject$Factory.create());
        return (T)openGraphObject;
    }
    
    public static OpenGraphObject createForPost(final String s) {
        return createForPost(OpenGraphObject.class, s);
    }
}
