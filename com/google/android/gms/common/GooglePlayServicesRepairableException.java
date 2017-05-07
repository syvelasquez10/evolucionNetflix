// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException
{
    private final int zzOD;
    
    GooglePlayServicesRepairableException(final int zzOD, final String s, final Intent intent) {
        super(s, intent);
        this.zzOD = zzOD;
    }
}
