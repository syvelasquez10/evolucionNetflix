// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

public final class Throwables
{
    public static RuntimeException propagate(final Throwable t) {
        propagateIfPossible(Preconditions.checkNotNull(t));
        throw new RuntimeException(t);
    }
    
    public static <X extends Throwable> void propagateIfInstanceOf(final Throwable t, final Class<X> clazz) throws X {
        if (t != null && clazz.isInstance(t)) {
            throw clazz.cast(t);
        }
    }
    
    public static void propagateIfPossible(final Throwable t) {
        propagateIfInstanceOf(t, Error.class);
        propagateIfInstanceOf(t, RuntimeException.class);
    }
}
