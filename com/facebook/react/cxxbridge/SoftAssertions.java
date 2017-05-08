// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.AssertionException;

public class SoftAssertions
{
    public static void assertCondition(final boolean b, final String s) {
        if (!b) {
            throw new AssertionException(s);
        }
    }
}
