// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import org.json.JSONObject;
import com.google.android.gms.common.api.Status;

class RemoteMediaPlayer$b$2 implements RemoteMediaPlayer$MediaChannelResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ RemoteMediaPlayer$b Gc;
    
    RemoteMediaPlayer$b$2(final RemoteMediaPlayer$b gc, final Status cw) {
        this.Gc = gc;
        this.CW = cw;
    }
    
    @Override
    public JSONObject getCustomData() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
