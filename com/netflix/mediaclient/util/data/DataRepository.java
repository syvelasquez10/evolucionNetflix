// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.data;

public interface DataRepository
{
    void clear();
    
    DataRepository$Entry[] getEntries();
    
    void load(final String p0, final DataRepository$DataLoadedCallback p1);
    
    void loadAll(final DataRepository$LoadedCallback p0);
    
    void remove(final String p0);
    
    String save(final String p0, final byte[] p1);
    
    String save(final String p0, final byte[] p1, final DataRepository$DataSavedCallback p2);
}
