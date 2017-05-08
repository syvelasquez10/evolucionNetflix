// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import android.os.Build;
import android.system.ErrnoException;
import java.io.IOException;
import android.system.OsConstants;
import android.system.Os;
import java.io.FileDescriptor;

final class SysUtil$LollipopSysdeps
{
    public static void fallocateIfSupported(final FileDescriptor fileDescriptor, final long n) {
        try {
            Os.posix_fallocate(fileDescriptor, 0L, n);
        }
        catch (ErrnoException ex) {
            if (ex.errno != OsConstants.EOPNOTSUPP && ex.errno != OsConstants.ENOSYS && ex.errno != OsConstants.EINVAL) {
                throw new IOException(ex.toString(), (Throwable)ex);
            }
        }
    }
    
    public static String[] getSupportedAbis() {
        return Build.SUPPORTED_32_BIT_ABIS;
    }
}
