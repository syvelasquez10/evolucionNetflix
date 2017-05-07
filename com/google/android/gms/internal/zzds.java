// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.overlay.zzk;
import android.graphics.Color;
import android.text.TextUtils;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.client.zzl;
import android.content.Context;
import java.util.WeakHashMap;
import java.util.Map;

@zzgr
public final class zzds implements zzdk
{
    private final Map<zziz, Integer> zzxX;
    
    public zzds() {
        this.zzxX = new WeakHashMap<zziz, Integer>();
    }
    
    private static int zza(final Context context, Map<String, String> s, final String s2, final int n) {
        s = ((Map<String, String>)s).get(s2);
        int zzb = n;
        if (s == null) {
            return zzb;
        }
        try {
            zzb = zzl.zzcF().zzb(context, Integer.parseInt(s));
            return zzb;
        }
        catch (NumberFormatException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaH("Could not parse " + s2 + " in a video GMSG: " + s);
            return n;
        }
    }
    
    @Override
    public void zza(zziz zzgX, final Map<String, String> map) {
        final String s = map.get("action");
        if (s == null) {
            zzb.zzaH("Action missing from video GMSG.");
        }
        else {
            if (zzb.zzN(3)) {
                final JSONObject jsonObject = new JSONObject((Map)map);
                jsonObject.remove("google.afma.Notify_dt");
                zzb.zzaF("Video GMSG: " + s + " " + jsonObject.toString());
            }
            if ("background".equals(s)) {
                final String s2 = map.get("color");
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    zzb.zzaH("Color parameter missing from color video GMSG.");
                    return;
                }
                int color;
                try {
                    color = Color.parseColor(s2);
                    final zziy zzhl = zzgX.zzhl();
                    if (zzhl != null) {
                        final zzk zzgX2 = zzhl.zzgX();
                        if (zzgX2 != null) {
                            zzgX2.setBackgroundColor(color);
                            return;
                        }
                    }
                }
                catch (IllegalArgumentException ex) {
                    zzb.zzaH("Invalid color parameter in video GMSG.");
                    return;
                }
                this.zzxX.put(zzgX, color);
            }
            else {
                final zziy zzhl2 = zzgX.zzhl();
                if (zzhl2 == null) {
                    zzb.zzaH("Could not get underlay container for a video GMSG.");
                    return;
                }
                final boolean equals = "new".equals(s);
                final boolean equals2 = "position".equals(s);
                if (equals || equals2) {
                    final Context context = zzgX.getContext();
                    final int zza = zza(context, map, "x", 0);
                    final int zza2 = zza(context, map, "y", 0);
                    final int zza3 = zza(context, map, "w", -1);
                    final int zza4 = zza(context, map, "h", -1);
                    while (true) {
                        try {
                            final int int1 = Integer.parseInt(map.get("player"));
                            if (equals && zzhl2.zzgX() == null) {
                                zzhl2.zza(zza, zza2, zza3, zza4, int1);
                                if (this.zzxX.containsKey(zzgX)) {
                                    final int intValue = this.zzxX.get(zzgX);
                                    zzgX = (zziz)zzhl2.zzgX();
                                    ((zzk)zzgX).setBackgroundColor(intValue);
                                    ((zzk)zzgX).zzeW();
                                }
                                return;
                            }
                        }
                        catch (NumberFormatException ex2) {
                            final int int1 = 0;
                            continue;
                        }
                        break;
                    }
                    zzhl2.zze(zza, zza2, zza3, zza4);
                    return;
                }
                final zzk zzgX3 = zzhl2.zzgX();
                if (zzgX3 == null) {
                    zzk.zzd(zzgX);
                    return;
                }
                if ("click".equals(s)) {
                    final Context context2 = zzgX.getContext();
                    final int zza5 = zza(context2, map, "x", 0);
                    final int zza6 = zza(context2, map, "y", 0);
                    final long uptimeMillis = SystemClock.uptimeMillis();
                    final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float)zza5, (float)zza6, 0);
                    zzgX3.zzd(obtain);
                    obtain.recycle();
                    return;
                }
                if ("currentTime".equals(s)) {
                    zzgX = (zziz)map.get("time");
                    if (zzgX == null) {
                        zzb.zzaH("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        zzgX3.seekTo((int)(Float.parseFloat((String)zzgX) * 1000.0f));
                        return;
                    }
                    catch (NumberFormatException ex3) {
                        zzb.zzaH("Could not parse time parameter from currentTime video GMSG: " + (String)zzgX);
                        return;
                    }
                }
                if ("hide".equals(s)) {
                    zzgX3.setVisibility(4);
                    return;
                }
                if ("load".equals(s)) {
                    zzgX3.zzeV();
                    return;
                }
                if ("mimetype".equals(s)) {
                    zzgX3.setMimeType(map.get("mimetype"));
                    return;
                }
                if ("muted".equals(s)) {
                    if (Boolean.parseBoolean(map.get("muted"))) {
                        zzgX3.zzex();
                        return;
                    }
                    zzgX3.zzey();
                }
                else {
                    if ("pause".equals(s)) {
                        zzgX3.pause();
                        return;
                    }
                    if ("play".equals(s)) {
                        zzgX3.play();
                        return;
                    }
                    if ("show".equals(s)) {
                        zzgX3.setVisibility(0);
                        return;
                    }
                    if ("src".equals(s)) {
                        zzgX3.zzan(map.get("src"));
                        return;
                    }
                    if ("volume".equals(s)) {
                        zzgX = (zziz)map.get("volume");
                        if (zzgX == null) {
                            zzb.zzaH("Level parameter missing from volume video GMSG.");
                            return;
                        }
                        try {
                            zzgX3.zza(Float.parseFloat((String)zzgX));
                            return;
                        }
                        catch (NumberFormatException ex4) {
                            zzb.zzaH("Could not parse volume parameter from volume video GMSG: " + (String)zzgX);
                            return;
                        }
                    }
                    if ("watermark".equals(s)) {
                        zzgX3.zzeW();
                        return;
                    }
                    zzb.zzaH("Unknown video action: " + s);
                }
            }
        }
    }
}
