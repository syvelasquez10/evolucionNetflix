// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.util.regex.Matcher;
import java.util.Enumeration;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.zip.ZipFile;
import java.io.InputStream;

final class ExtractFromZipSoSource$ZipUnpacker$ZipBackedInputDsoIterator extends UnpackingSoSource$InputDsoIterator
{
    private int mCurrentDso;
    final /* synthetic */ ExtractFromZipSoSource$ZipUnpacker this$1;
    
    private ExtractFromZipSoSource$ZipUnpacker$ZipBackedInputDsoIterator(final ExtractFromZipSoSource$ZipUnpacker this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public boolean hasNext() {
        this.this$1.ensureDsos();
        return this.mCurrentDso < this.this$1.mDsos.length;
    }
    
    @Override
    public UnpackingSoSource$InputDso next() {
        this.this$1.ensureDsos();
        final ExtractFromZipSoSource$ZipDso extractFromZipSoSource$ZipDso = this.this$1.mDsos[this.mCurrentDso++];
        final InputStream inputStream = this.this$1.mZipFile.getInputStream(extractFromZipSoSource$ZipDso.backingEntry);
        try {
            final UnpackingSoSource$InputDso unpackingSoSource$InputDso = new UnpackingSoSource$InputDso(extractFromZipSoSource$ZipDso, inputStream);
            if (false) {
                throw new NullPointerException();
            }
            return unpackingSoSource$InputDso;
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
