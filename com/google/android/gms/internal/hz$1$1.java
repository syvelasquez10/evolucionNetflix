// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

class hz$1$1 extends hx<hu$a>
{
    final /* synthetic */ hz$1 CI;
    
    hz$1$1(final hz$1 ci, final BaseImplementation$b baseImplementation$b) {
        this.CI = ci;
        super(baseImplementation$b);
    }
    
    @Override
    public void a(final Status status, final ParcelFileDescriptor parcelFileDescriptor) {
        this.CH.b((T)new hz$b(status, parcelFileDescriptor));
    }
}
