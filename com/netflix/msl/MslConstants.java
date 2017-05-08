// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import java.nio.charset.Charset;

public abstract class MslConstants
{
    public static final Charset DEFAULT_CHARSET;
    public static final long MAX_LONG_VALUE = 9007199254740992L;
    public static final int MAX_MESSAGES = 12;
    
    static {
        DEFAULT_CHARSET = Charset.forName("UTF-8");
    }
}
