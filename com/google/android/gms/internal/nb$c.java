// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.panorama.PanoramaApi$PanoramaResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class nb$c extends mz$a
{
    private final BaseImplementation$b<PanoramaApi$PanoramaResult> De;
    
    public nb$c(final BaseImplementation$b<PanoramaApi$PanoramaResult> de) {
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
        this.De.b(new nd(new Status(n, null, pendingIntent), intent));
    }
}
