// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import java.util.Arrays;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.j;

public class kg extends j<DriveId>
{
    public static final kg Qq;
    
    static {
        Qq = new kg();
    }
    
    private kg() {
        super("driveId", Arrays.asList("sqlId", "resourceId"), Arrays.asList("dbInstanceId"), 4100000);
    }
    
    protected DriveId m(final DataHolder dataHolder, final int n, final int n2) {
        final long long1 = dataHolder.gz().getLong("dbInstanceId");
        String c;
        final String s = c = dataHolder.c("resourceId", n, n2);
        if (s != null) {
            c = s;
            if (s.startsWith("generated-android-")) {
                c = null;
            }
        }
        return new DriveId(c, Long.valueOf(dataHolder.a("sqlId", n, n2)), long1);
    }
}
