// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

class hz$1 extends hz$c<hu$a>
{
    @Override
    protected void a(final hv hv) {
        hv.a(new hz$1$1(this, this));
    }
    
    public hu$a b(final Status status) {
        return new hz$b(status, null);
    }
}
