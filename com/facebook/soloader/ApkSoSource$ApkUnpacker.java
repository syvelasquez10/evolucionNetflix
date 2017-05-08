// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import android.content.Context;
import android.util.Log;
import java.util.zip.ZipEntry;
import java.io.File;

public class ApkSoSource$ApkUnpacker extends ExtractFromZipSoSource$ZipUnpacker
{
    private final int mFlags;
    private File mLibDir;
    final /* synthetic */ ApkSoSource this$0;
    
    ApkSoSource$ApkUnpacker(final ApkSoSource this$0) {
        this.this$0 = this$0;
        super(this$0);
        this.mLibDir = new File(this$0.mContext.getApplicationInfo().nativeLibraryDir);
        this.mFlags = this$0.mFlags;
    }
    
    @Override
    protected boolean shouldExtract(final ZipEntry zipEntry, final String s) {
        final String name = zipEntry.getName();
        if ((this.mFlags & 0x1) == 0x0) {
            Log.d("ApkSoSource", "allowing consideration of " + name + ": self-extraction preferred");
            return true;
        }
        final File file = new File(this.mLibDir, s);
        if (!file.isFile()) {
            Log.d("ApkSoSource", String.format("allowing considering of %s: %s not in system lib dir", name, s));
            return true;
        }
        final long length = file.length();
        final long size = zipEntry.getSize();
        if (length != size) {
            Log.d("ApkSoSource", String.format("allowing consideration of %s: sysdir file length is %s, but the file is %s bytes long in the APK", file, length, size));
            return true;
        }
        Log.d("ApkSoSource", "not allowing consideration of " + name + ": deferring to libdir");
        return false;
    }
}
