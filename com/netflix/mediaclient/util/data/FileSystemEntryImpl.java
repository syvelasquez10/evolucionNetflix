// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.data;

import java.io.File;

public class FileSystemEntryImpl implements DataRepository$Entry
{
    private String mFileName;
    private long mSizeInBytes;
    private long mTs;
    
    FileSystemEntryImpl(final File file) {
        this.mFileName = file.getName();
        this.mTs = file.lastModified();
        this.mSizeInBytes = file.length();
    }
    
    FileSystemEntryImpl(final String s, final long mTs, final long mSizeInBytes) {
        this.mFileName = getFilenameForKey(s);
        this.mTs = mTs;
        this.mSizeInBytes = mSizeInBytes;
    }
    
    static String getFilenameForKey(final String s) {
        final int n = s.length() / 2;
        return String.valueOf(s.substring(0, n).hashCode()) + String.valueOf(s.substring(n).hashCode());
    }
    
    @Override
    public String getKey() {
        return this.mFileName;
    }
    
    @Override
    public long getSizeInBytes() {
        return this.mSizeInBytes;
    }
    
    @Override
    public long getTs() {
        return this.mTs;
    }
}
