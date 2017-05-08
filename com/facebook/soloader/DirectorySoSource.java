// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.File;

public class DirectorySoSource extends SoSource
{
    protected final int flags;
    protected final File soDirectory;
    
    public DirectorySoSource(final File soDirectory, final int flags) {
        this.soDirectory = soDirectory;
        this.flags = flags;
    }
    
    private static String[] getDependencies(final File file) {
        try {
            return MinElf.extract_DT_NEEDED(file);
        }
        finally {}
    }
    
    @Override
    public int loadLibrary(final String s, final int n) {
        return this.loadLibraryFrom(s, n, this.soDirectory);
    }
    
    protected int loadLibraryFrom(final String s, final int n, final File file) {
        int i = 0;
        final File file2 = new File(file, s);
        if (!file2.exists()) {
            return 0;
        }
        if ((n & 0x1) != 0x0 && (this.flags & 0x2) != 0x0) {
            return 2;
        }
        if ((this.flags & 0x1) != 0x0) {
            for (String[] dependencies = getDependencies(file2); i < dependencies.length; ++i) {
                final String s2 = dependencies[i];
                if (!s2.startsWith("/")) {
                    SoLoader.loadLibraryBySoName(s2, n | 0x1);
                }
            }
        }
        System.load(file2.getAbsolutePath());
        return 1;
    }
    
    @Override
    public File unpackLibrary(final String s) {
        final File file = new File(this.soDirectory, s);
        if (file.exists()) {
            return file;
        }
        return null;
    }
}
