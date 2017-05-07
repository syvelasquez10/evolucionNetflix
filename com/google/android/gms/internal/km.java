// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class km
{
    private final byte[] adH;
    private int adI;
    private int adJ;
    
    public km(final byte[] array) {
        this.adH = new byte[256];
        for (int i = 0; i < 256; ++i) {
            this.adH[i] = (byte)i;
        }
        int n = 0;
        for (int j = 0; j < 256; ++j) {
            n = (n + this.adH[j] + array[j % array.length] & 0xFF);
            final byte b = this.adH[j];
            this.adH[j] = this.adH[n];
            this.adH[n] = b;
        }
        this.adI = 0;
        this.adJ = 0;
    }
    
    public void m(final byte[] array) {
        int adI = this.adI;
        int adJ = this.adJ;
        for (int i = 0; i < array.length; ++i) {
            adI = (adI + 1 & 0xFF);
            adJ = (adJ + this.adH[adI] & 0xFF);
            final byte b = this.adH[adI];
            this.adH[adI] = this.adH[adJ];
            this.adH[adJ] = b;
            array[i] ^= this.adH[this.adH[adI] + this.adH[adJ] & 0xFF];
        }
        this.adI = adI;
        this.adJ = adJ;
    }
}
