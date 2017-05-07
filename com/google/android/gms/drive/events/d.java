// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;

public class d
{
    public static boolean a(final int n, final DriveId driveId) {
        return driveId != null || bd(n);
    }
    
    public static boolean bd(final int n) {
        return (0x2L & 1 << n) != 0x0L;
    }
}
