// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.lang.reflect.Field;

public final class j
{
    public static Object a(final Field field, final Object o) {
        if (field == null || field == null) {
            return null;
        }
        field.setAccessible(true);
        try {
            return field.get(o);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            throw new cj("Unable to get value of field", t);
        }
    }
    
    public static Field a(final Class clazz, final Class clazz2, final boolean b) {
        final Field[] declaredFields = clazz.getDeclaredFields();
        Field field = null;
        Field field2;
        for (int i = 0; i < declaredFields.length; ++i, field = field2) {
            field2 = field;
            if (clazz2.isAssignableFrom(declaredFields[i].getType())) {
                if (field != null) {
                    throw new cj("Field is ambiguous: " + field.getName() + ", " + declaredFields[i].getName());
                }
                field2 = declaredFields[i];
            }
        }
        if (field == null) {
            if (b) {
                throw new cj("Could not find field matching type: " + clazz2.getName());
            }
        }
        else {
            field.setAccessible(true);
        }
        return field;
    }
}
