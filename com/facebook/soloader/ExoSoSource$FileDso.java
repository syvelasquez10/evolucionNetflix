// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.File;

final class ExoSoSource$FileDso extends UnpackingSoSource$Dso
{
    final File backingFile;
    
    ExoSoSource$FileDso(final String s, final String s2, final File backingFile) {
        super(s, s2);
        this.backingFile = backingFile;
    }
}
