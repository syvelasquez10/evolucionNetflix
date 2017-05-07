// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import org.json.JSONObject;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.iq;

class RemoteMediaPlayer$1 extends iq
{
    final /* synthetic */ RemoteMediaPlayer FK;
    
    RemoteMediaPlayer$1(final RemoteMediaPlayer fk) {
        this.FK = fk;
    }
    
    @Override
    protected void onMetadataUpdated() {
        this.FK.onMetadataUpdated();
    }
    
    @Override
    protected void onStatusUpdated() {
        this.FK.onStatusUpdated();
    }
}
