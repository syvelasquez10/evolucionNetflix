// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.File;
import android.content.Context;

public class ApkSoSource extends ExtractFromZipSoSource
{
    private final int mFlags;
    
    public ApkSoSource(final Context context, final String s, final int mFlags) {
        super(context, s, new File(context.getApplicationInfo().sourceDir), "^lib/([^/]+)/([^/]+\\.so)$");
        this.mFlags = mFlags;
    }
    
    @Override
    protected byte[] getDepsBlock() {
        return SysUtil.makeApkDepBlock(this.mZipFileName);
    }
    
    @Override
    protected UnpackingSoSource$Unpacker makeUnpacker() {
        return new ApkSoSource$ApkUnpacker(this);
    }
}
