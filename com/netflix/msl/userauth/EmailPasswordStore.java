// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

import com.netflix.msl.tokens.MslUser;

public interface EmailPasswordStore
{
    MslUser isUser(final String p0, final String p1);
}
