// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.infer.annotation;

public class Assertions
{
    public static void assertCondition(final boolean b) {
        if (!b) {
            throw new AssertionError();
        }
    }
    
    public static void assertCondition(final boolean b, final String s) {
        if (!b) {
            throw new AssertionError((Object)s);
        }
    }
    
    public static <T> T assertNotNull(final T t) {
        if (t == null) {
            throw new AssertionError();
        }
        return t;
    }
    
    public static <T> T assertNotNull(final T t, final String s) {
        if (t == null) {
            throw new AssertionError((Object)s);
        }
        return t;
    }
    
    public static <T> T assumeNotNull(final T t) {
        return t;
    }
}
