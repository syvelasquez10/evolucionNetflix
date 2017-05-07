// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zzgr
public class zzfh
{
    private final String zzAK;
    private final zziz zzoM;
    
    public zzfh(final zziz zziz) {
        this(zziz, "");
    }
    
    public zzfh(final zziz zzoM, final String zzAK) {
        this.zzoM = zzoM;
        this.zzAK = zzAK;
    }
    
    public void zza(final int n, final int n2, final int n3, final int n4, final float n5, final int n6) {
        try {
            this.zzoM.zzb("onScreenInfoChanged", new JSONObject().put("width", n).put("height", n2).put("maxSizeWidth", n3).put("maxSizeHeight", n4).put("density", (double)n5).put("rotation", n6));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while obtaining screen information.", (Throwable)ex);
        }
    }
    
    public void zzak(final String s) {
        try {
            this.zzoM.zzb("onError", new JSONObject().put("message", (Object)s).put("action", (Object)this.zzAK));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occurred while dispatching error event.", (Throwable)ex);
        }
    }
    
    public void zzal(final String s) {
        try {
            this.zzoM.zzb("onReadyEventReceived", new JSONObject().put("js", (Object)s));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching ready Event.", (Throwable)ex);
        }
    }
    
    public void zzam(final String s) {
        try {
            this.zzoM.zzb("onStateChanged", new JSONObject().put("state", (Object)s));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching state change.", (Throwable)ex);
        }
    }
    
    public void zzb(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzoM.zzb("onSizeChanged", new JSONObject().put("x", n).put("y", n2).put("width", n3).put("height", n4));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching size change.", (Throwable)ex);
        }
    }
    
    public void zzc(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzoM.zzb("onDefaultPositionReceived", new JSONObject().put("x", n).put("y", n2).put("width", n3).put("height", n4));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching default position.", (Throwable)ex);
        }
    }
}
