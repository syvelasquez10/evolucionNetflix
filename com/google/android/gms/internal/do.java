// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.MotionEvent;
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

@ez
public final class do extends FrameLayout implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener
{
    private final gv md;
    private final MediaController rX;
    private final a rY;
    private final VideoView rZ;
    private long sa;
    private String sb;
    
    public do(final Context context, final gv md) {
        super(context);
        this.md = md;
        this.addView((View)(this.rZ = new VideoView(context)), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 17));
        this.rX = new MediaController(context);
        (this.rY = new a(this)).ck();
        this.rZ.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
        this.rZ.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
        this.rZ.setOnErrorListener((MediaPlayer$OnErrorListener)this);
    }
    
    private static void a(final gv gv, final String s) {
        a(gv, s, new HashMap<String, String>(1));
    }
    
    public static void a(final gv gv, final String s, final String s2) {
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
        a(gv, "error", (Map<String, String>)hashMap);
    }
    
    private static void a(final gv gv, final String s, final String s2, final String s3) {
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put(s2, s3);
        a(gv, s, hashMap);
    }
    
    private static void a(final gv gv, final String s, final Map<String, String> map) {
        map.put("event", s);
        gv.a("onVideoEvent", map);
    }
    
    public void C(final String sb) {
        this.sb = sb;
    }
    
    public void b(final MotionEvent motionEvent) {
        this.rZ.dispatchTouchEvent(motionEvent);
    }
    
    public void ci() {
        if (!TextUtils.isEmpty((CharSequence)this.sb)) {
            this.rZ.setVideoPath(this.sb);
            return;
        }
        a(this.md, "no_src", (String)null);
    }
    
    public void cj() {
        final long sa = this.rZ.getCurrentPosition();
        if (this.sa != sa) {
            a(this.md, "timeupdate", "time", String.valueOf(sa / 1000.0f));
            this.sa = sa;
        }
    }
    
    public void destroy() {
        this.rY.cancel();
        this.rZ.stopPlayback();
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        a(this.md, "ended");
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        a(this.md, String.valueOf(n), String.valueOf(n2));
        return true;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        a(this.md, "canplaythrough", "duration", String.valueOf(this.rZ.getDuration() / 1000.0f));
    }
    
    public void pause() {
        this.rZ.pause();
    }
    
    public void play() {
        this.rZ.start();
    }
    
    public void q(final boolean b) {
        if (b) {
            this.rZ.setMediaController(this.rX);
            return;
        }
        this.rX.hide();
        this.rZ.setMediaController((MediaController)null);
    }
    
    public void seekTo(final int n) {
        this.rZ.seekTo(n);
    }
    
    private static final class a
    {
        private final Runnable mk;
        private volatile boolean sc;
        
        public a(final do do1) {
            this.sc = false;
            this.mk = new Runnable() {
                private final WeakReference<do> sd = new WeakReference<do>(do1);
                
                @Override
                public void run() {
                    final do do1 = this.sd.get();
                    if (!a.this.sc && do1 != null) {
                        do1.cj();
                        a.this.ck();
                    }
                }
            };
        }
        
        public void cancel() {
            this.sc = true;
            gr.wC.removeCallbacks(this.mk);
        }
        
        public void ck() {
            gr.wC.postDelayed(this.mk, 250L);
        }
    }
}
