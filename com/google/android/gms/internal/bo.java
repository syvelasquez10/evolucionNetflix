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

public final class bo extends FrameLayout implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener
{
    private final MediaController gQ;
    private final a gR;
    private final VideoView gS;
    private long gT;
    private String gU;
    private final cw gv;
    
    public bo(final Context context, final cw gv) {
        super(context);
        this.gv = gv;
        this.addView((View)(this.gS = new VideoView(context)), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 17));
        this.gQ = new MediaController(context);
        (this.gR = new a(this)).ah();
        this.gS.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
        this.gS.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
        this.gS.setOnErrorListener((MediaPlayer$OnErrorListener)this);
    }
    
    private static void a(final cw cw, final String s) {
        a(cw, s, new HashMap<String, String>(1));
    }
    
    public static void a(final cw cw, final String s, final String s2) {
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
        a(cw, "error", (Map<String, String>)hashMap);
    }
    
    private static void a(final cw cw, final String s, final String s2, final String s3) {
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put(s2, s3);
        a(cw, s, hashMap);
    }
    
    private static void a(final cw cw, final String s, final Map<String, String> map) {
        map.put("event", s);
        cw.a("onVideoEvent", map);
    }
    
    public void af() {
        if (!TextUtils.isEmpty((CharSequence)this.gU)) {
            this.gS.setVideoPath(this.gU);
            return;
        }
        a(this.gv, "no_src", (String)null);
    }
    
    public void ag() {
        final long gt = this.gS.getCurrentPosition();
        if (this.gT != gt) {
            a(this.gv, "timeupdate", "time", String.valueOf(gt / 1000.0f));
            this.gT = gt;
        }
    }
    
    public void b(final MotionEvent motionEvent) {
        this.gS.dispatchTouchEvent(motionEvent);
    }
    
    public void destroy() {
        this.gR.cancel();
        this.gS.stopPlayback();
    }
    
    public void i(final boolean b) {
        if (b) {
            this.gS.setMediaController(this.gQ);
            return;
        }
        this.gQ.hide();
        this.gS.setMediaController((MediaController)null);
    }
    
    public void n(final String gu) {
        this.gU = gu;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        a(this.gv, "ended");
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        a(this.gv, String.valueOf(n), String.valueOf(n2));
        return true;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        a(this.gv, "canplaythrough", "duration", String.valueOf(this.gS.getDuration() / 1000.0f));
    }
    
    public void pause() {
        this.gS.pause();
    }
    
    public void play() {
        this.gS.start();
    }
    
    public void seekTo(final int n) {
        this.gS.seekTo(n);
    }
    
    private static final class a
    {
        private final Runnable ep;
        private volatile boolean gV;
        
        public a(final bo bo) {
            this.gV = false;
            this.ep = new Runnable() {
                private final WeakReference<bo> gW = new WeakReference<bo>(bo);
                
                @Override
                public void run() {
                    final bo bo = this.gW.get();
                    if (!a.this.gV && bo != null) {
                        bo.ag();
                        a.this.ah();
                    }
                }
            };
        }
        
        public void ah() {
            cs.iI.postDelayed(this.ep, 250L);
        }
        
        public void cancel() {
            this.gV = true;
            cs.iI.removeCallbacks(this.ep);
        }
    }
}
