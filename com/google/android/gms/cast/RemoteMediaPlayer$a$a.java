// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import org.json.JSONObject;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.iq;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;

final class RemoteMediaPlayer$a$a implements ResultCallback<Status>
{
    private final long FZ;
    final /* synthetic */ RemoteMediaPlayer$a Ga;
    
    RemoteMediaPlayer$a$a(final RemoteMediaPlayer$a ga, final long fz) {
        this.Ga = ga;
        this.FZ = fz;
    }
    
    public void k(final Status status) {
        if (!status.isSuccess()) {
            this.Ga.FK.FG.b(this.FZ, status.getStatusCode());
        }
    }
}
