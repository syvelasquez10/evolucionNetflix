// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.util.zip.ZipEntry;

final class ExtractFromZipSoSource$ZipDso extends UnpackingSoSource$Dso implements Comparable
{
    final int abiScore;
    final ZipEntry backingEntry;
    
    ExtractFromZipSoSource$ZipDso(final String s, final ZipEntry backingEntry, final int abiScore) {
        super(s, makePseudoHash(backingEntry));
        this.backingEntry = backingEntry;
        this.abiScore = abiScore;
    }
    
    private static String makePseudoHash(final ZipEntry zipEntry) {
        return String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), zipEntry.getSize(), zipEntry.getCompressedSize(), zipEntry.getCrc());
    }
    
    @Override
    public int compareTo(final Object o) {
        return this.name.compareTo(((ExtractFromZipSoSource$ZipDso)o).name);
    }
}
