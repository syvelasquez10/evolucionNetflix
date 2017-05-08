// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.util;

import java.util.regex.Pattern;

public class VersionUtil
{
    private static final Pattern V_SEP;
    
    static {
        V_SEP = Pattern.compile("[-_./;:]");
    }
    
    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }
}
