// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.os.SystemClock;
import java.util.Map;
import android.util.DisplayMetrics;

public final class ap implements an
{
    private static int a(final DisplayMetrics displayMetrics, Map<String, String> s, final String s2, final int n) {
        s = ((Map<String, String>)s).get(s2);
        int a = n;
        if (s == null) {
            return a;
        }
        try {
            a = cs.a(displayMetrics, Integer.parseInt(s));
            return a;
        }
        catch (NumberFormatException ex) {
            ct.v("Could not parse " + s2 + " in a video GMSG: " + s);
            return n;
        }
    }
    
    @Override
    public void a(cw cw, final Map<String, String> map) {
        final String s = map.get("action");
        if (s == null) {
            ct.v("Action missing from video GMSG.");
            return;
        }
        final bk ab = cw.aB();
        if (ab == null) {
            ct.v("Could not get ad overlay for a video GMSG.");
            return;
        }
        final boolean equalsIgnoreCase = "new".equalsIgnoreCase(s);
        final boolean equalsIgnoreCase2 = "position".equalsIgnoreCase(s);
        if (equalsIgnoreCase || equalsIgnoreCase2) {
            final DisplayMetrics displayMetrics = cw.getContext().getResources().getDisplayMetrics();
            final int a = a(displayMetrics, map, "x", 0);
            final int a2 = a(displayMetrics, map, "y", 0);
            final int a3 = a(displayMetrics, map, "w", -1);
            final int a4 = a(displayMetrics, map, "h", -1);
            if (equalsIgnoreCase && ab.W() == null) {
                ab.c(a, a2, a3, a4);
                return;
            }
            ab.b(a, a2, a3, a4);
        }
        else {
            final bo w = ab.W();
            if (w == null) {
                bo.a(cw, "no_video_view", (String)null);
                return;
            }
            if ("click".equalsIgnoreCase(s)) {
                final DisplayMetrics displayMetrics2 = cw.getContext().getResources().getDisplayMetrics();
                final int a5 = a(displayMetrics2, map, "x", 0);
                final int a6 = a(displayMetrics2, map, "y", 0);
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float)a5, (float)a6, 0);
                w.b(obtain);
                obtain.recycle();
                return;
            }
            if ("controls".equalsIgnoreCase(s)) {
                final String s2 = map.get("enabled");
                if (s2 == null) {
                    ct.v("Enabled parameter missing from controls video GMSG.");
                    return;
                }
                w.i(Boolean.parseBoolean(s2));
            }
            else {
                if ("currentTime".equalsIgnoreCase(s)) {
                    cw = (cw)map.get("time");
                    if (cw == null) {
                        ct.v("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        w.seekTo((int)(Float.parseFloat((String)cw) * 1000.0f));
                        return;
                    }
                    catch (NumberFormatException ex) {
                        ct.v("Could not parse time parameter from currentTime video GMSG: " + (String)cw);
                        return;
                    }
                }
                if ("hide".equalsIgnoreCase(s)) {
                    w.setVisibility(4);
                    return;
                }
                if ("load".equalsIgnoreCase(s)) {
                    w.af();
                    return;
                }
                if ("pause".equalsIgnoreCase(s)) {
                    w.pause();
                    return;
                }
                if ("play".equalsIgnoreCase(s)) {
                    w.play();
                    return;
                }
                if ("show".equalsIgnoreCase(s)) {
                    w.setVisibility(0);
                    return;
                }
                if ("src".equalsIgnoreCase(s)) {
                    w.n(map.get("src"));
                    return;
                }
                ct.v("Unknown video action: " + s);
            }
        }
    }
}
