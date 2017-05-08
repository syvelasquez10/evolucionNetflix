// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import java.util.List;
import com.netflix.msl.MslError;
import com.netflix.msl.msg.ErrorMessageRegistry;

public class DummyMessageRegistry implements ErrorMessageRegistry
{
    @Override
    public String getUserMessage(final MslError mslError, final List<String> list) {
        return null;
    }
    
    @Override
    public String getUserMessage(final Throwable t, final List<String> list) {
        return null;
    }
}
