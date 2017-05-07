// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;

class r$2 implements ResultCallback<Status>
{
    final /* synthetic */ r OD;
    
    r$2(final r od) {
        this.OD = od;
    }
    
    public void k(final Status status) {
        if (!status.isSuccess()) {
            v.q("DriveContentsImpl", "Error discarding contents");
            return;
        }
        v.n("DriveContentsImpl", "Contents discarded");
    }
}
