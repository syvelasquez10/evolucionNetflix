// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zzgr
public class zzff
{
    private final boolean zzAv;
    private final boolean zzAw;
    private final boolean zzAx;
    private final boolean zzAy;
    private final boolean zzAz;
    
    private zzff(final zzff$zza zzff$zza) {
        this.zzAv = zzff$zza.zzAv;
        this.zzAw = zzff$zza.zzAw;
        this.zzAx = zzff$zza.zzAx;
        this.zzAy = zzff$zza.zzAy;
        this.zzAz = zzff$zza.zzAz;
    }
    
    public JSONObject toJson() {
        try {
            return new JSONObject().put("sms", this.zzAv).put("tel", this.zzAw).put("calendar", this.zzAx).put("storePicture", this.zzAy).put("inlineVideo", this.zzAz);
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while obtaining the MRAID capabilities.", (Throwable)ex);
            return null;
        }
    }
}
