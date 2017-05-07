// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.os.SystemClock;
import java.util.Map;
import android.util.DisplayMetrics;

@ez
public final class ce implements by
{
    private static int a(final DisplayMetrics displayMetrics, Map<String, String> s, final String s2, final int n) {
        s = ((Map<String, String>)s).get(s2);
        int a = n;
        if (s == null) {
            return a;
        }
        try {
            a = gr.a(displayMetrics, Integer.parseInt(s));
            return a;
        }
        catch (NumberFormatException ex) {
            gs.W("Could not parse " + s2 + " in a video GMSG: " + s);
            return n;
        }
    }
    
    @Override
    public void a(gv gv, final Map<String, String> map) {
        final String s = map.get("action");
        if (s == null) {
            gs.W("Action missing from video GMSG.");
            return;
        }
        final dk du = gv.du();
        if (du == null) {
            gs.W("Could not get ad overlay for a video GMSG.");
            return;
        }
        final boolean equalsIgnoreCase = "new".equalsIgnoreCase(s);
        final boolean equalsIgnoreCase2 = "position".equalsIgnoreCase(s);
        if (equalsIgnoreCase || equalsIgnoreCase2) {
            final DisplayMetrics displayMetrics = gv.getContext().getResources().getDisplayMetrics();
            final int a = a(displayMetrics, map, "x", 0);
            final int a2 = a(displayMetrics, map, "y", 0);
            final int a3 = a(displayMetrics, map, "w", -1);
            final int a4 = a(displayMetrics, map, "h", -1);
            if (equalsIgnoreCase && du.bW() == null) {
                du.c(a, a2, a3, a4);
                return;
            }
            du.b(a, a2, a3, a4);
        }
        else {
            final do bw = du.bW();
            if (bw == null) {
                do.a(gv, "no_video_view", (String)null);
                return;
            }
            if ("click".equalsIgnoreCase(s)) {
                final DisplayMetrics displayMetrics2 = gv.getContext().getResources().getDisplayMetrics();
                final int a5 = a(displayMetrics2, map, "x", 0);
                final int a6 = a(displayMetrics2, map, "y", 0);
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float)a5, (float)a6, 0);
                bw.b(obtain);
                obtain.recycle();
                return;
            }
            if ("controls".equalsIgnoreCase(s)) {
                final String s2 = map.get("enabled");
                if (s2 == null) {
                    gs.W("Enabled parameter missing from controls video GMSG.");
                    return;
                }
                bw.q(Boolean.parseBoolean(s2));
            }
            else {
                if ("currentTime".equalsIgnoreCase(s)) {
                    gv = (gv)map.get("time");
                    if (gv == null) {
                        gs.W("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        bw.seekTo((int)(Float.parseFloat((String)gv) * 1000.0f));
                        return;
                    }
                    catch (NumberFormatException ex) {
                        gs.W("Could not parse time parameter from currentTime video GMSG: " + (String)gv);
                        return;
                    }
                }
                if ("hide".equalsIgnoreCase(s)) {
                    bw.setVisibility(4);
                    return;
                }
                if ("load".equalsIgnoreCase(s)) {
                    bw.ci();
                    return;
                }
                if ("pause".equalsIgnoreCase(s)) {
                    bw.pause();
                    return;
                }
                if ("play".equalsIgnoreCase(s)) {
                    bw.play();
                    return;
                }
                if ("show".equalsIgnoreCase(s)) {
                    bw.setVisibility(0);
                    return;
                }
                if ("src".equalsIgnoreCase(s)) {
                    bw.C(map.get("src"));
                    return;
                }
                gs.W("Unknown video action: " + s);
            }
        }
    }
}
