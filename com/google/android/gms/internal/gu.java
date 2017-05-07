// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import java.util.Arrays;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.h;

public class gu extends h<DriveId>
{
    public static final gu Gx;
    
    static {
        Gx = new gu();
    }
    
    private gu() {
        super("driveId", Arrays.asList("sqlId", "resourceId"), 4100000);
    }
    
    protected DriveId j(final DataHolder dataHolder, final int n, final int n2) {
        final long long1 = dataHolder.getMetadata().getLong("dbInstanceId");
        String string;
        final String s = string = dataHolder.getString("resourceId", n, n2);
        if (s != null) {
            string = s;
            if (s.startsWith("generated-android-")) {
                string = null;
            }
        }
        return new DriveId(string, Long.valueOf(dataHolder.getLong("sqlId", n, n2)), long1);
    }
}
