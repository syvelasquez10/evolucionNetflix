// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.util.List;
import com.netflix.msl.MslError;

public interface ErrorMessageRegistry
{
    String getUserMessage(final MslError p0, final List<String> p1);
    
    String getUserMessage(final Throwable p0, final List<String> p1);
}
