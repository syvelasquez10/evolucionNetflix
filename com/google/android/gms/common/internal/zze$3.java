// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Arrays;

final class zze$3 extends zze
{
    final /* synthetic */ char[] zzacX;
    
    zze$3(final char[] zzacX) {
        this.zzacX = zzacX;
    }
    
    @Override
    public boolean zzd(final char c) {
        return Arrays.binarySearch(this.zzacX, c) >= 0;
    }
}
