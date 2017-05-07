// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import java.io.IOException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.ir;

class RemoteMediaPlayer$a implements ir
{
    final /* synthetic */ RemoteMediaPlayer FK;
    private GoogleApiClient FX;
    private long FY;
    
    public RemoteMediaPlayer$a(final RemoteMediaPlayer fk) {
        this.FK = fk;
        this.FY = 0L;
    }
    
    @Override
    public void a(final String s, final String s2, final long n, final String s3) {
        if (this.FX == null) {
            throw new IOException("No GoogleApiClient available");
        }
        Cast.CastApi.sendMessage(this.FX, s, s2).setResultCallback(new RemoteMediaPlayer$a$a(this, n));
    }
    
    public void b(final GoogleApiClient fx) {
        this.FX = fx;
    }
    
    @Override
    public long fy() {
        return ++this.FY;
    }
}
