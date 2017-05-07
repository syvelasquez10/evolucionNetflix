// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class Cast$c extends Cast$a<Cast$ApplicationConnectionResult>
{
    public Cast$ApplicationConnectionResult j(final Status status) {
        return new Cast$c$1(this, status);
    }
}
