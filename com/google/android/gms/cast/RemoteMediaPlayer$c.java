// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import org.json.JSONObject;
import com.google.android.gms.common.api.Status;

final class RemoteMediaPlayer$c implements RemoteMediaPlayer$MediaChannelResult
{
    private final Status CM;
    private final JSONObject Fl;
    
    RemoteMediaPlayer$c(final Status cm, final JSONObject fl) {
        this.CM = cm;
        this.Fl = fl;
    }
    
    @Override
    public JSONObject getCustomData() {
        return this.Fl;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
