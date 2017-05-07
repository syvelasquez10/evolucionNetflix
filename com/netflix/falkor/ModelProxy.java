// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.List;
import com.netflix.model.branches.FalkorObject;
import java.util.Collection;

public interface ModelProxy<T>
{
    CachedModelProxy$GetResult get(final Collection<PQL> p0);
    
     <I extends FalkorObject> List<I> getItemsAsList(final PQL p0);
    
     <I extends FalkorObject> List<I> getItemsAsList(final Collection<PQL> p0);
    
    ServiceProvider getServiceProvider();
    
    Object getValue(final PQL p0);
}
