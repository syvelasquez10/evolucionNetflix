// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import java.lang.reflect.Field;
import android.os.IBinder;

public final class e<T> extends d.a
{
    private final T Sc;
    
    private e(final T sc) {
        this.Sc = sc;
    }
    
    public static <T> T f(final d d) {
        if (d instanceof e) {
            return (T)((e)d).Sc;
        }
        final IBinder binder = d.asBinder();
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
    
    public static <T> d k(final T t) {
        return new e<Object>(t);
    }
}
