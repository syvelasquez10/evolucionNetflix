// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Bundle;
import com.google.android.gms.common.api.Api$ApiOptions;

final class Drive$2 extends Drive$a<Drive$b>
{
    @Override
    protected Bundle a(final Drive$b drive$b) {
        if (drive$b == null) {
            return new Bundle();
        }
        return drive$b.hM();
    }
}
