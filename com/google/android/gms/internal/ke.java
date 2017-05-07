// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import java.util.Arrays;
import java.util.Collections;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.j;

public class ke extends j<AppVisibleCustomProperties>
{
    public ke(final int n) {
        super("customProperties", Collections.singleton("customProperties"), Arrays.asList("customPropertiesExtra"), n);
    }
    
    protected AppVisibleCustomProperties l(final DataHolder dataHolder, final int n, final int n2) {
        return (AppVisibleCustomProperties)dataHolder.gz().getSparseParcelableArray("customPropertiesExtra").get(n, (Object)AppVisibleCustomProperties.Py);
    }
}
