// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.text.TextUtils;
import java.util.Map;
import java.util.HashMap;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import android.widget.VideoView;
import android.widget.MediaController;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.widget.FrameLayout;

public final class cg extends FrameLayout implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener
{
    private final dz lC;
    private final MediaController os;
    private final a ot;
    private final VideoView ou;
    private long ov;
    private String ow;
    
    public cg(final Context context, final dz lc) {
        super(context);
        this.lC = lc;
        this.addView((View)(this.ou = new VideoView(context)), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 17));
        this.os = new MediaController(context);
        (this.ot = new a(this)).aW();
        this.ou.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
        this.ou.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
        this.ou.setOnErrorListener((MediaPlayer$OnErrorListener)this);
    }
    
    private static void a(final dz dz, final String s) {
        a(dz, s, new HashMap<String, String>(1));
    }
    
    public static void a(final dz dz, final String s, final String s2) {
        boolean b;
        if (s2 == null) {
            b = true;
        }
        else {
            b = false;
        }
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 3;
        }
        final HashMap hashMap = new HashMap<String, String>(n);
        hashMap.put("what", s);
        if (!b) {
            hashMap.put("extra", s2);
        }
        a(dz, "error", (Map<String, String>)hashMap);
    }
    
    private static void a(final dz dz, final String s, final String s2, final String s3) {
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put(s2, s3);
        a(dz, s, hashMap);
    }
    
    private static void a(final dz dz, final String s, final Map<String, String> map) {
        map.put("event", s);
        dz.a("onVideoEvent", map);
    }
    
    public void aU() {
        if (!TextUtils.isEmpty((CharSequence)this.ow)) {
            this.ou.setVideoPath(this.ow);
            return;
        }
        a(this.lC, "no_src", (String)null);
    }
    
    public void aV() {
        final long ov = this.ou.getCurrentPosition();
        if (this.ov != ov) {
            a(this.lC, "timeupdate", "time", String.valueOf(ov / 1000.0f));
            this.ov = ov;
        }
    }
    
    public void b(final MotionEvent motionEvent) {
        this.ou.dispatchTouchEvent(motionEvent);
    }
    
    public void destroy() {
        this.ot.cancel();
        this.ou.stopPlayback();
    }
    
    public void k(final boolean b) {
        if (b) {
            this.ou.setMediaController(this.os);
            return;
        }
        this.os.hide();
        this.ou.setMediaController((MediaController)null);
    }
    
    public void o(final String ow) {
        this.ow = ow;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        a(this.lC, "ended");
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        a(this.lC, String.valueOf(n), String.valueOf(n2));
        return true;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        a(this.lC, "canplaythrough", "duration", String.valueOf(this.ou.getDuration() / 1000.0f));
    }
    
    public void pause() {
        this.ou.pause();
    }
    
    public void play() {
        this.ou.start();
    }
    
    public void seekTo(final int n) {
        this.ou.seekTo(n);
    }
    
    private static final class a
    {
        private final Runnable kW;
        private volatile boolean ox;
        
        public a(final cg cg) {
            this.ox = false;
            this.kW = new Runnable() {
                private final WeakReference<cg> oy = new WeakReference<cg>(cg);
                
                @Override
                public void run() {
                    final cg cg = this.oy.get();
                    if (!a.this.ox && cg != null) {
                        cg.aV();
                        a.this.aW();
                    }
                }
            };
        }
        
        public void aW() {
            dv.rp.postDelayed(this.kW, 250L);
        }
        
        public void cancel() {
            this.ox = true;
            dv.rp.removeCallbacks(this.kW);
        }
    }
}
