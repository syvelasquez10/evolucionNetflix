// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.data;

public interface DataRepository
{
    void clear();
    
    Entry[] getEntries();
    
    void load(final String p0, final DataLoadedCallback p1);
    
    void loadAll(final LoadedCallback p0);
    
    void remove(final String p0);
    
    String save(final String p0, final byte[] p1);
    
    public interface DataLoadedCallback
    {
        void onDataLoaded(final String p0, final byte[] p1, final long p2);
    }
    
    public interface Entry
    {
        String getKey();
        
        long getSizeInBytes();
        
        long getTs();
    }
    
    public interface LoadedCallback
    {
        void onLoaded(final Entry[] p0);
    }
}
