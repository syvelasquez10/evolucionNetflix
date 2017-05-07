// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Bundle;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public class Drive$b implements Api$ApiOptions$Optional
{
    private final Bundle MZ;
    
    private Drive$b() {
        this(new Bundle());
    }
    
    private Drive$b(final Bundle mz) {
        this.MZ = mz;
    }
    
    public Bundle hM() {
        return this.MZ;
    }
}
