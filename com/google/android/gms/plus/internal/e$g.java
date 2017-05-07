// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.internal.d$b;
import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

final class e$g extends a
{
    private final BaseImplementation$b<Status> alk;
    final /* synthetic */ e all;
    
    public e$g(final e all, final BaseImplementation$b<Status> alk) {
        this.all = all;
        this.alk = alk;
    }
    
    @Override
    public void h(final int n, final Bundle bundle) {
        PendingIntent pendingIntent;
        if (bundle != null) {
            pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
        }
        else {
            pendingIntent = null;
        }
        this.all.a(new e$h(this.alk, new Status(n, null, pendingIntent)));
    }
}
