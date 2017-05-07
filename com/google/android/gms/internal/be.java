// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.os.SystemClock;
import java.util.Map;
import android.util.DisplayMetrics;

public final class be implements bb
{
    private static int a(final DisplayMetrics displayMetrics, Map<String, String> s, final String s2, final int n) {
        s = ((Map<String, String>)s).get(s2);
        int a = n;
        if (s == null) {
            return a;
        }
        try {
            a = dv.a(displayMetrics, Integer.parseInt(s));
            return a;
        }
        catch (NumberFormatException ex) {
            dw.z("Could not parse " + s2 + " in a video GMSG: " + s);
            return n;
        }
    }
    
    @Override
    public void b(dz dz, final Map<String, String> map) {
        final String s = map.get("action");
        if (s == null) {
            dw.z("Action missing from video GMSG.");
            return;
        }
        final cc bh = dz.bH();
        if (bh == null) {
            dw.z("Could not get ad overlay for a video GMSG.");
            return;
        }
        final boolean equalsIgnoreCase = "new".equalsIgnoreCase(s);
        final boolean equalsIgnoreCase2 = "position".equalsIgnoreCase(s);
        if (equalsIgnoreCase || equalsIgnoreCase2) {
            final DisplayMetrics displayMetrics = dz.getContext().getResources().getDisplayMetrics();
            final int a = a(displayMetrics, map, "x", 0);
            final int a2 = a(displayMetrics, map, "y", 0);
            final int a3 = a(displayMetrics, map, "w", -1);
            final int a4 = a(displayMetrics, map, "h", -1);
            if (equalsIgnoreCase && bh.aK() == null) {
                bh.c(a, a2, a3, a4);
                return;
            }
            bh.b(a, a2, a3, a4);
        }
        else {
            final cg ak = bh.aK();
            if (ak == null) {
                cg.a(dz, "no_video_view", (String)null);
                return;
            }
            if ("click".equalsIgnoreCase(s)) {
                final DisplayMetrics displayMetrics2 = dz.getContext().getResources().getDisplayMetrics();
                final int a5 = a(displayMetrics2, map, "x", 0);
                final int a6 = a(displayMetrics2, map, "y", 0);
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float)a5, (float)a6, 0);
                ak.b(obtain);
                obtain.recycle();
                return;
            }
            if ("controls".equalsIgnoreCase(s)) {
                final String s2 = map.get("enabled");
                if (s2 == null) {
                    dw.z("Enabled parameter missing from controls video GMSG.");
                    return;
                }
                ak.k(Boolean.parseBoolean(s2));
            }
            else {
                if ("currentTime".equalsIgnoreCase(s)) {
                    dz = (dz)map.get("time");
                    if (dz == null) {
                        dw.z("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        ak.seekTo((int)(Float.parseFloat((String)dz) * 1000.0f));
                        return;
                    }
                    catch (NumberFormatException ex) {
                        dw.z("Could not parse time parameter from currentTime video GMSG: " + (String)dz);
                        return;
                    }
                }
                if ("hide".equalsIgnoreCase(s)) {
                    ak.setVisibility(4);
                    return;
                }
                if ("load".equalsIgnoreCase(s)) {
                    ak.aU();
                    return;
                }
                if ("pause".equalsIgnoreCase(s)) {
                    ak.pause();
                    return;
                }
                if ("play".equalsIgnoreCase(s)) {
                    ak.play();
                    return;
                }
                if ("show".equalsIgnoreCase(s)) {
                    ak.setVisibility(0);
                    return;
                }
                if ("src".equalsIgnoreCase(s)) {
                    ak.o(map.get("src"));
                    return;
                }
                dw.z("Unknown video action: " + s);
            }
        }
    }
}
