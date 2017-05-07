// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;

public class c
{
    public static boolean a(final int n, final DriveId driveId) {
        return driveId != null || (0x4L & 1 << n) != 0x0L;
    }
}
