// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.panorama.PanoramaApi$a;
import com.google.android.gms.common.api.BaseImplementation$b;

final class nb$a extends mz$a
{
    private final BaseImplementation$b<PanoramaApi$a> De;
    
    public nb$a(final BaseImplementation$b<PanoramaApi$a> de) {
        this.De = de;
    }
    
    public void a(final int n, final Bundle bundle, final int n2, final Intent intent) {
        PendingIntent pendingIntent;
        if (bundle != null) {
            pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
        }
        else {
            pendingIntent = null;
        }
        this.De.b(new my(new Status(n, null, pendingIntent), intent, n2));
    }
}
