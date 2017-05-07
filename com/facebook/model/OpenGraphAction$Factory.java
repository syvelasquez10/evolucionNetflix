// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

public final class OpenGraphAction$Factory
{
    @Deprecated
    public static OpenGraphAction createForPost() {
        return createForPost(OpenGraphAction.class, null);
    }
    
    public static <T extends OpenGraphAction> T createForPost(final Class<T> clazz, final String type) {
        final OpenGraphAction openGraphAction = GraphObject$Factory.create(clazz);
        if (type != null) {
            openGraphAction.setType(type);
        }
        return (T)openGraphAction;
    }
    
    public static OpenGraphAction createForPost(final String s) {
        return createForPost(OpenGraphAction.class, s);
    }
}
