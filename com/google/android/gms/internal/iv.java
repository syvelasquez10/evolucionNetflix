// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class iv
{
    private final byte[] Hm;
    private int Hn;
    private int Ho;
    
    public iv(final byte[] array) {
        this.Hm = new byte[256];
        for (int i = 0; i < 256; ++i) {
            this.Hm[i] = (byte)i;
        }
        int n = 0;
        for (int j = 0; j < 256; ++j) {
            n = (n + this.Hm[j] + array[j % array.length] & 0xFF);
            final byte b = this.Hm[j];
            this.Hm[j] = this.Hm[n];
            this.Hm[n] = b;
        }
        this.Hn = 0;
        this.Ho = 0;
    }
    
    public void h(final byte[] array) {
        int hn = this.Hn;
        int ho = this.Ho;
        for (int i = 0; i < array.length; ++i) {
            hn = (hn + 1 & 0xFF);
            ho = (ho + this.Hm[hn] & 0xFF);
            final byte b = this.Hm[hn];
            this.Hm[hn] = this.Hm[ho];
            this.Hm[ho] = b;
            array[i] ^= this.Hm[this.Hm[hn] + this.Hm[ho] & 0xFF];
        }
        this.Hn = hn;
        this.Ho = ho;
    }
}
