// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class i
{
    public static String a(final boolean b, final String s) {
        String s2;
        if (b) {
            s2 = "installed ";
        }
        else {
            s2 = "did not install ";
        }
        return String.format("%s%s%s", s2, s, " service monitoring");
    }
}
