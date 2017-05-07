// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.io.InputStream;
import java.util.Collection;

public interface RequestProvider
{
    InputStream call(final PQL p0, final Collection<Object> p1, final Collection<PQL> p2, final Collection<PQL> p3);
    
    InputStream get(final Collection<PQL> p0);
}
