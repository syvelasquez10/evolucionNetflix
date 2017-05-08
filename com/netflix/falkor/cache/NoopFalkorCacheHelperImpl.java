// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.netflix.falkor.BranchNode;
import java.util.List;
import com.google.gson.JsonElement;
import java.util.ArrayList;

class NoopFalkorCacheHelperImpl implements FalkorCacheHelperInterface
{
    @Override
    public void addToCache(final ArrayList<String> list, final JsonElement jsonElement) {
    }
    
    @Override
    public void beginTransaction() {
    }
    
    @Override
    public void cancelTransaction() {
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public void commitTransaction() {
    }
    
    @Override
    public void deleteSubPath(final List<Object> list) {
    }
    
    @Override
    public void expireLolomoListsFromCache() {
    }
    
    @Override
    public void purgeCache() {
    }
    
    @Override
    public Object retrieveFromCache(final List<Object> list, final int n, final String s, final BranchNode branchNode) {
        return null;
    }
}
