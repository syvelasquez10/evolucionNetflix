// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.InputStream;
import java.io.FileInputStream;

final class ExoSoSource$ExoUnpacker$FileBackedInputDsoIterator extends UnpackingSoSource$InputDsoIterator
{
    private int mCurrentDso;
    final /* synthetic */ ExoSoSource$ExoUnpacker this$1;
    
    private ExoSoSource$ExoUnpacker$FileBackedInputDsoIterator(final ExoSoSource$ExoUnpacker this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public boolean hasNext() {
        return this.mCurrentDso < this.this$1.mDsos.length;
    }
    
    @Override
    public UnpackingSoSource$InputDso next() {
        final ExoSoSource$FileDso exoSoSource$FileDso = this.this$1.mDsos[this.mCurrentDso++];
        final FileInputStream fileInputStream = new FileInputStream(exoSoSource$FileDso.backingFile);
        try {
            final UnpackingSoSource$InputDso unpackingSoSource$InputDso = new UnpackingSoSource$InputDso(exoSoSource$FileDso, fileInputStream);
            if (false) {
                throw new NullPointerException();
            }
            return unpackingSoSource$InputDso;
        }
        finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
