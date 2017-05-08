// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker;

public interface ReLinker$LibraryLoader
{
    void loadLibrary(final String p0);
    
    void loadPath(final String p0);
    
    String mapLibraryName(final String p0);
    
    String[] supportedAbis();
    
    String unmapLibraryName(final String p0);
}
