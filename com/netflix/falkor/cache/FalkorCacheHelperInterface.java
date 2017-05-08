// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.netflix.falkor.BranchNode;
import java.util.List;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.io.Closeable;

public interface FalkorCacheHelperInterface extends Closeable
{
    void addToCache(final ArrayList<String> p0, final JsonElement p1);
    
    void beginTransaction();
    
    void cancelTransaction();
    
    void close();
    
    void commitTransaction();
    
    void deleteSubPath(final List<Object> p0);
    
    void expireLolomoListsFromCache();
    
    void purgeCache();
    
    Object retrieveFromCache(final List<Object> p0, final int p1, final String p2, final BranchNode p3);
}
