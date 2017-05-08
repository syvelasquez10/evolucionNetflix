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

public class ExtractFromZipSoSource$ZipUnpacker extends UnpackingSoSource$Unpacker
{
    private ExtractFromZipSoSource$ZipDso[] mDsos;
    private final ZipFile mZipFile;
    final /* synthetic */ ExtractFromZipSoSource this$0;
    
    ExtractFromZipSoSource$ZipUnpacker(final ExtractFromZipSoSource this$0) {
        this.this$0 = this$0;
        this.mZipFile = new ZipFile(this$0.mZipFileName);
    }
    
    @Override
    public void close() {
        this.mZipFile.close();
    }
    
    final ExtractFromZipSoSource$ZipDso[] ensureDsos() {
        final int n = 0;
        if (this.mDsos == null) {
            final HashMap<String, ExtractFromZipSoSource$ZipDso> hashMap = new HashMap<String, ExtractFromZipSoSource$ZipDso>();
            final Pattern compile = Pattern.compile(this.this$0.mZipSearchPattern);
            final String[] supportedAbis = SysUtil.getSupportedAbis();
            final Enumeration<? extends ZipEntry> entries = this.mZipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
                final Matcher matcher = compile.matcher(zipEntry.getName());
                if (matcher.matches()) {
                    final String group = matcher.group(1);
                    final String group2 = matcher.group(2);
                    final int abiScore = SysUtil.findAbiScore(supportedAbis, group);
                    if (abiScore < 0) {
                        continue;
                    }
                    final ExtractFromZipSoSource$ZipDso extractFromZipSoSource$ZipDso = hashMap.get(group2);
                    if (extractFromZipSoSource$ZipDso != null && abiScore >= extractFromZipSoSource$ZipDso.abiScore) {
                        continue;
                    }
                    hashMap.put(group2, new ExtractFromZipSoSource$ZipDso(group2, zipEntry, abiScore));
                }
            }
            final ExtractFromZipSoSource$ZipDso[] array = hashMap.values().toArray(new ExtractFromZipSoSource$ZipDso[hashMap.size()]);
            Arrays.sort(array);
            int i = 0;
            int n2 = 0;
            while (i < array.length) {
                final ExtractFromZipSoSource$ZipDso extractFromZipSoSource$ZipDso2 = array[i];
                if (this.shouldExtract(extractFromZipSoSource$ZipDso2.backingEntry, extractFromZipSoSource$ZipDso2.name)) {
                    ++n2;
                }
                else {
                    array[i] = null;
                }
                ++i;
            }
            final ExtractFromZipSoSource$ZipDso[] mDsos = new ExtractFromZipSoSource$ZipDso[n2];
            int j = 0;
            int n3 = n;
            while (j < array.length) {
                final ExtractFromZipSoSource$ZipDso extractFromZipSoSource$ZipDso3 = array[j];
                int n4 = n3;
                if (extractFromZipSoSource$ZipDso3 != null) {
                    mDsos[n3] = extractFromZipSoSource$ZipDso3;
                    n4 = n3 + 1;
                }
                ++j;
                n3 = n4;
            }
            this.mDsos = mDsos;
        }
        return this.mDsos;
    }
    
    @Override
    protected final UnpackingSoSource$DsoManifest getDsoManifest() {
        return new UnpackingSoSource$DsoManifest(this.ensureDsos());
    }
    
    @Override
    protected final UnpackingSoSource$InputDsoIterator openDsoIterator() {
        return new ExtractFromZipSoSource$ZipUnpacker$ZipBackedInputDsoIterator(this, null);
    }
    
    protected boolean shouldExtract(final ZipEntry zipEntry, final String s) {
        return true;
    }
}
