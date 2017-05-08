// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import android.content.Context;
import java.io.File;

public class ExtractFromZipSoSource extends UnpackingSoSource
{
    protected final File mZipFileName;
    protected final String mZipSearchPattern;
    
    public ExtractFromZipSoSource(final Context context, final String s, final File mZipFileName, final String mZipSearchPattern) {
        super(context, s);
        this.mZipFileName = mZipFileName;
        this.mZipSearchPattern = mZipSearchPattern;
    }
    
    @Override
    protected UnpackingSoSource$Unpacker makeUnpacker() {
        return new ExtractFromZipSoSource$ZipUnpacker(this);
    }
}
