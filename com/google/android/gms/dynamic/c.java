// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import java.lang.reflect.Field;
import android.os.IBinder;

public final class c<T> extends b.a
{
    private final T sE;
    
    private c(final T se) {
        this.sE = se;
    }
    
    public static <T> T b(final b b) {
        if (b instanceof c) {
            return (T)((c)b).sE;
        }
        final IBinder binder = b.asBinder();
        final Field[] declaredFields = binder.getClass().getDeclaredFields();
        if (declaredFields.length == 1) {
            final Field field = declaredFields[0];
            if (!field.isAccessible()) {
                field.setAccessible(true);
                try {
                    return (T)field.get(binder);
                }
                catch (NullPointerException ex) {
                    throw new IllegalArgumentException("Binder object is null.", ex);
                }
                catch (IllegalArgumentException ex2) {
                    throw new IllegalArgumentException("remoteBinder is the wrong class.", ex2);
                }
                catch (IllegalAccessException ex3) {
                    throw new IllegalArgumentException("Could not access the field in remoteBinder.", ex3);
                }
            }
            throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly one declared *private* field for the wrapped object. Preferably, this is an instance of the ObjectWrapper<T> class.");
        }
        throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly *one* declared private field for the wrapped object.  Preferably, this is an instance of the ObjectWrapper<T> class.");
    }
    
    public static <T> b h(final T t) {
        return new c<Object>(t);
    }
}
