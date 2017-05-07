// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.io.ObjectStreamClass;
import java.io.ObjectInputStream;

public abstract class UnsafeAllocator
{
    public static UnsafeAllocator create() {
        try {
            final Class<?> forName = Class.forName("sun.misc.Unsafe");
            final Field declaredField = forName.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new UnsafeAllocator$1(forName.getMethod("allocateInstance", Class.class), declaredField.get(null));
        }
        catch (Exception ex) {
            try {
                final Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod.setAccessible(true);
                return new UnsafeAllocator$2(declaredMethod);
            }
            catch (Exception ex2) {
                try {
                    final Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod2.setAccessible(true);
                    final int intValue = (int)declaredMethod2.invoke(null, Object.class);
                    final Method declaredMethod3 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod3.setAccessible(true);
                    return new UnsafeAllocator$3(declaredMethod3, intValue);
                }
                catch (Exception ex3) {
                    return new UnsafeAllocator$4();
                }
            }
        }
    }
    
    public abstract <T> T newInstance(final Class<T> p0);
}
