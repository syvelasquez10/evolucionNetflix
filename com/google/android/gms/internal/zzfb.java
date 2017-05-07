// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zzgk
public class zzfb
{
    private final zzip zzoL;
    private final String zzzX;
    
    public zzfb(final zzip zzip) {
        this(zzip, "");
    }
    
    public zzfb(final zzip zzoL, final String zzzX) {
        this.zzoL = zzoL;
        this.zzzX = zzzX;
    }
    
    public void zza(final int n, final int n2, final int n3, final int n4, final float n5, final int n6) {
        try {
            this.zzoL.zzb("onScreenInfoChanged", new JSONObject().put("width", n).put("height", n2).put("maxSizeWidth", n3).put("maxSizeHeight", n4).put("density", (double)n5).put("rotation", n6));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while obtaining screen information.", (Throwable)ex);
        }
    }
    
    public void zzah(final String s) {
        try {
            this.zzoL.zzb("onError", new JSONObject().put("message", (Object)s).put("action", (Object)this.zzzX));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occurred while dispatching error event.", (Throwable)ex);
        }
    }
    
    public void zzai(final String s) {
        try {
            this.zzoL.zzb("onReadyEventReceived", new JSONObject().put("js", (Object)s));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching ready Event.", (Throwable)ex);
        }
    }
    
    public void zzaj(final String s) {
        try {
            this.zzoL.zzb("onStateChanged", new JSONObject().put("state", (Object)s));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching state change.", (Throwable)ex);
        }
    }
    
    public void zzb(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzoL.zzb("onSizeChanged", new JSONObject().put("x", n).put("y", n2).put("width", n3).put("height", n4));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching size change.", (Throwable)ex);
        }
    }
    
    public void zzc(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzoL.zzb("onDefaultPositionReceived", new JSONObject().put("x", n).put("y", n2).put("width", n3).put("height", n4));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching default position.", (Throwable)ex);
        }
    }
}
