// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.Collection;
import java.util.HashMap;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.Map;

public class AppVisibleCustomProperties$a
{
    private final Map<CustomPropertyKey, CustomProperty> PA;
    
    public AppVisibleCustomProperties$a() {
        this.PA = new HashMap<CustomPropertyKey, CustomProperty>();
    }
    
    public AppVisibleCustomProperties im() {
        return new AppVisibleCustomProperties(this.PA.values(), null);
    }
}
