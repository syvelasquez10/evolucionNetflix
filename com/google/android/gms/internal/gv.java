// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.internal.a;
import com.google.android.gms.drive.metadata.internal.d;
import com.google.android.gms.drive.metadata.MetadataField;

public class gv
{
    public static final MetadataField<Integer> Gy;
    public static final MetadataField<Boolean> Gz;
    
    static {
        Gy = new d("contentAvailability", 4300000);
        Gz = new a("isPinnable", 4300000);
    }
}
