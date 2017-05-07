// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzd;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.graphics.Color;
import android.text.TextUtils;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.client.zzk;
import android.content.Context;
import java.util.WeakHashMap;
import java.util.Map;

@zzgk
public final class zzdo implements zzdg
{
    private final Map<zzip, Integer> zzxq;
    
    public zzdo() {
        this.zzxq = new WeakHashMap<zzip, Integer>();
    }
    
    private static int zza(final Context context, Map<String, String> s, final String s2, final int n) {
        s = ((Map<String, String>)s).get(s2);
        int zzb = n;
        if (s == null) {
            return zzb;
        }
        try {
            zzb = zzk.zzcE().zzb(context, Integer.parseInt(s));
            return zzb;
        }
        catch (NumberFormatException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaE("Could not parse " + s2 + " in a video GMSG: " + s);
            return n;
        }
    }
    
    @Override
    public void zza(zzip zzip, final Map<String, String> map) {
        final String s = map.get("action");
        if (s == null) {
            zzb.zzaE("Action missing from video GMSG.");
        }
        else {
            if (zzb.zzM(3)) {
                final JSONObject jsonObject = new JSONObject((Map)map);
                jsonObject.remove("google.afma.Notify_dt");
                zzb.zzaC("Video GMSG: " + s + " " + jsonObject.toString());
            }
            if ("background".equals(s)) {
                final String s2 = map.get("color");
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    zzb.zzaE("Color parameter missing from color video GMSG.");
                    return;
                }
                int color;
                try {
                    color = Color.parseColor(s2);
                    final zzd zzgQ = zzip.zzgQ();
                    if (zzgQ != null) {
                        final com.google.android.gms.ads.internal.overlay.zzk zzew = zzgQ.zzew();
                        if (zzew != null) {
                            zzew.setBackgroundColor(color);
                            return;
                        }
                    }
                }
                catch (IllegalArgumentException ex) {
                    zzb.zzaE("Invalid color parameter in video GMSG.");
                    return;
                }
                this.zzxq.put(zzip, color);
            }
            else {
                final zzd zzgQ2 = zzip.zzgQ();
                if (zzgQ2 == null) {
                    zzb.zzaE("Could not get ad overlay for a video GMSG.");
                    return;
                }
                final boolean equals = "new".equals(s);
                final boolean equals2 = "position".equals(s);
                if (equals || equals2) {
                    final Context context = zzip.getContext();
                    final int zza = zza(context, map, "x", 0);
                    final int zza2 = zza(context, map, "y", 0);
                    final int zza3 = zza(context, map, "w", -1);
                    final int zza4 = zza(context, map, "h", -1);
                    while (true) {
                        try {
                            final int int1 = Integer.parseInt(map.get("player"));
                            if (equals && zzgQ2.zzew() == null) {
                                zzgQ2.zza(zza, zza2, zza3, zza4, int1);
                                if (this.zzxq.containsKey(zzip)) {
                                    zzgQ2.zzew().setBackgroundColor((int)this.zzxq.get(zzip));
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
                    zzgQ2.zzd(zza, zza2, zza3, zza4);
                    return;
                }
                final com.google.android.gms.ads.internal.overlay.zzk zzew2 = zzgQ2.zzew();
                if (zzew2 == null) {
                    com.google.android.gms.ads.internal.overlay.zzk.zzd(zzip);
                    return;
                }
                if ("click".equals(s)) {
                    final Context context2 = zzip.getContext();
                    final int zza5 = zza(context2, map, "x", 0);
                    final int zza6 = zza(context2, map, "y", 0);
                    final long uptimeMillis = SystemClock.uptimeMillis();
                    final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float)zza5, (float)zza6, 0);
                    zzew2.zzd(obtain);
                    obtain.recycle();
                    return;
                }
                if ("currentTime".equals(s)) {
                    zzip = (zzip)map.get("time");
                    if (zzip == null) {
                        zzb.zzaE("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        zzew2.seekTo((int)(Float.parseFloat((String)zzip) * 1000.0f));
                        return;
                    }
                    catch (NumberFormatException ex3) {
                        zzb.zzaE("Could not parse time parameter from currentTime video GMSG: " + (String)zzip);
                        return;
                    }
                }
                if ("hide".equals(s)) {
                    zzew2.setVisibility(4);
                    return;
                }
                if ("load".equals(s)) {
                    zzew2.zzeP();
                    return;
                }
                if ("mimetype".equals(s)) {
                    zzew2.setMimeType(map.get("mimetype"));
                    return;
                }
                if ("muted".equals(s)) {
                    if (Boolean.parseBoolean(map.get("muted"))) {
                        zzew2.zzeq();
                        return;
                    }
                    zzew2.zzer();
                }
                else {
                    if ("pause".equals(s)) {
                        zzew2.pause();
                        return;
                    }
                    if ("play".equals(s)) {
                        zzew2.play();
                        return;
                    }
                    if ("show".equals(s)) {
                        zzew2.setVisibility(0);
                        return;
                    }
                    if ("src".equals(s)) {
                        zzew2.zzak(map.get("src"));
                        return;
                    }
                    if ("volume".equals(s)) {
                        zzip = (zzip)map.get("volume");
                        if (zzip == null) {
                            zzb.zzaE("Level parameter missing from volume video GMSG.");
                            return;
                        }
                        try {
                            zzew2.zza(Float.parseFloat((String)zzip));
                            return;
                        }
                        catch (NumberFormatException ex4) {
                            zzb.zzaE("Could not parse volume parameter from volume video GMSG: " + (String)zzip);
                            return;
                        }
                    }
                    if ("watermark".equals(s)) {
                        zzew2.zzeQ();
                        return;
                    }
                    zzb.zzaE("Unknown video action: " + s);
                }
            }
        }
    }
}
