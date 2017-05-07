// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zzgk
public class zzez
{
    private final boolean zzzI;
    private final boolean zzzJ;
    private final boolean zzzK;
    private final boolean zzzL;
    private final boolean zzzM;
    
    private zzez(final zzez$zza zzez$zza) {
        this.zzzI = zzez$zza.zzzI;
        this.zzzJ = zzez$zza.zzzJ;
        this.zzzK = zzez$zza.zzzK;
        this.zzzL = zzez$zza.zzzL;
        this.zzzM = zzez$zza.zzzM;
    }
    
    public JSONObject toJson() {
        try {
            return new JSONObject().put("sms", this.zzzI).put("tel", this.zzzJ).put("calendar", this.zzzK).put("storePicture", this.zzzL).put("inlineVideo", this.zzzM);
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while obtaining the MRAID capabilities.", (Throwable)ex);
            return null;
        }
    }
}
