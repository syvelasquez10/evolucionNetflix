// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.internal.b;
import com.google.android.gms.drive.metadata.internal.f;
import com.google.android.gms.drive.metadata.MetadataField;

public class kh
{
    public static final MetadataField<Integer> Qr;
    public static final MetadataField<Boolean> Qs;
    
    static {
        Qr = new f("contentAvailability", 4300000);
        Qs = new b("isPinnable", 4300000);
    }
}
