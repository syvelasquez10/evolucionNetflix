// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.io.Closeable;

public final class FileLocker implements Closeable
{
    private final FileLock mLock;
    private final FileOutputStream mLockFileOutputStream;
    
    private FileLocker(final File file) {
        this.mLockFileOutputStream = new FileOutputStream(file);
        try {
            final FileLock lock = this.mLockFileOutputStream.getChannel().lock();
            if (lock == null) {
                this.mLockFileOutputStream.close();
            }
            this.mLock = lock;
        }
        finally {
            if (!false) {
                this.mLockFileOutputStream.close();
            }
        }
    }
    
    public static FileLocker lock(final File file) {
        return new FileLocker(file);
    }
    
    @Override
    public void close() {
        try {
            this.mLock.release();
        }
        finally {
            this.mLockFileOutputStream.close();
        }
    }
}
