// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public class SoftAssertions
{
    public static void assertCondition(final boolean b, final String s) {
        if (!b) {
            throw new AssertionException(s);
        }
    }
    
    public static <T> T assertNotNull(final T t) {
        if (t == null) {
            throw new AssertionException("Expected object to not be null!");
        }
        return t;
    }
    
    public static void assertUnreachable(final String s) {
        throw new AssertionException(s);
    }
}
