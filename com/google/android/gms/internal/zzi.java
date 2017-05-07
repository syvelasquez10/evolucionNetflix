// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

public class zzi
{
    public final byte[] data;
    public final int statusCode;
    public final Map<String, String> zzA;
    public final boolean zzB;
    public final long zzC;
    
    public zzi(final int statusCode, final byte[] data, final Map<String, String> zzA, final boolean zzB, final long zzC) {
        this.statusCode = statusCode;
        this.data = data;
        this.zzA = zzA;
        this.zzB = zzB;
        this.zzC = zzC;
    }
    
    public zzi(final byte[] array, final Map<String, String> map) {
        this(200, array, map, false, 0L);
    }
}
