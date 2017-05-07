// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

class PlusClient$7 implements BaseImplementation$b<Status>
{
    final /* synthetic */ PlusClient akU;
    final /* synthetic */ PlusClient$OnAccessRevokedListener akW;
    
    PlusClient$7(final PlusClient akU, final PlusClient$OnAccessRevokedListener akW) {
        this.akU = akU;
        this.akW = akW;
    }
    
    public void aA(final Status status) {
        this.akW.onAccessRevoked(status.getStatus().gu());
    }
}
