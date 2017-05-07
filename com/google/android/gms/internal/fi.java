// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.internal.b;
import java.util.Date;
import com.google.android.gms.drive.metadata.OrderedMetadataField;

public class fi
{
    public static final OrderedMetadataField<Date> rJ;
    public static final OrderedMetadataField<Date> rK;
    public static final OrderedMetadataField<Date> rL;
    public static final OrderedMetadataField<Date> rM;
    
    static {
        rJ = new b("modified");
        rK = new b("modifiedByMe");
        rL = new b("created");
        rM = new b("sharedWithMe");
    }
}
