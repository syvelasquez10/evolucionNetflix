// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.Map;
import java.util.HashMap;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.google.android.gms.common.internal.zzb;
import android.view.View;
import com.google.android.gms.internal.zzcc;
import com.google.android.gms.internal.zzcd;
import android.content.Context;
import com.google.android.gms.internal.zzip;
import android.widget.TextView;
import com.google.android.gms.internal.zzgk;
import android.widget.FrameLayout;

@zzgk
public class zzk extends FrameLayout implements zzh
{
    private final FrameLayout zzBb;
    private final zzq zzBc;
    private zzi zzBd;
    private boolean zzBe;
    private boolean zzBf;
    private TextView zzBg;
    private long zzBh;
    private String zzBj;
    private final zzip zzoL;
    private String zzxs;
    
    public zzk(final Context context, final zzip zzoL, final int n, final zzcd zzcd, final zzcc zzcc) {
        super(context);
        this.zzoL = zzoL;
        this.addView((View)(this.zzBb = new FrameLayout(context)));
        zzb.zzr(zzoL.zzgP());
        this.zzBd = zzoL.zzgP().zzoG.zza(context, zzoL, n, zzcd, zzcc);
        this.zzBb.addView((View)this.zzBd, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 17));
        (this.zzBg = new TextView(context)).setBackgroundColor(-16777216);
        this.zzeS();
        (this.zzBc = new zzq(this)).zzfa();
        this.zzBd.zza(this);
    }
    
    private void zza(String s, final String... array) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", s);
        final int length = array.length;
        int i = 0;
        s = null;
        while (i < length) {
            final String s2 = array[i];
            if (s == null) {
                s = s2;
            }
            else {
                hashMap.put(s, s2);
                s = null;
            }
            ++i;
        }
        this.zzoL.zzc("onVideoEvent", hashMap);
    }
    
    public static void zzd(final zzip zzip) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "no_video_view");
        zzip.zzc("onVideoEvent", hashMap);
    }
    
    private void zzeS() {
        if (!this.zzeU()) {
            this.zzBb.addView((View)this.zzBg, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
            this.zzBb.bringChildToFront((View)this.zzBg);
        }
    }
    
    private void zzeT() {
        if (this.zzeU()) {
            this.zzBb.removeView((View)this.zzBg);
        }
    }
    
    private boolean zzeU() {
        return this.zzBg.getParent() != null;
    }
    
    private void zzeW() {
        if (this.zzoL.zzgN() != null && this.zzBe && !this.zzBf) {
            this.zzoL.zzgN().getWindow().clearFlags(128);
            this.zzBe = false;
        }
    }
    
    public void destroy() {
        this.zzBc.cancel();
        this.zzBd.stop();
        this.zzeW();
    }
    
    public void pause() {
        this.zzBd.pause();
    }
    
    public void play() {
        this.zzBd.play();
    }
    
    public void seekTo(final int n) {
        this.zzBd.seekTo(n);
    }
    
    public void setMimeType(final String zzBj) {
        this.zzBj = zzBj;
    }
    
    public void zza(final float n) {
        this.zzBd.zza(n);
    }
    
    public void zzak(final String zzxs) {
        this.zzxs = zzxs;
    }
    
    public void zzd(final MotionEvent motionEvent) {
        this.zzBd.dispatchTouchEvent(motionEvent);
    }
    
    public void zze(final int n, final int n2, final int n3, final int n4) {
        if (n3 == 0 || n4 == 0) {
            return;
        }
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(n3 + 2, n4 + 2);
        layoutParams.setMargins(n - 1, n2 - 1, 0, 0);
        this.zzBb.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.requestLayout();
    }
    
    public void zzeP() {
        if (!TextUtils.isEmpty((CharSequence)this.zzxs)) {
            this.zzBd.setMimeType(this.zzBj);
            this.zzBd.setVideoPath(this.zzxs);
            return;
        }
        this.zza("no_src", new String[0]);
    }
    
    public void zzeQ() {
        final TextView textView = new TextView(this.zzBd.getContext());
        textView.setText((CharSequence)("AdMob - " + this.zzBd.zzek()));
        textView.setTextColor(-65536);
        textView.setBackgroundColor(-256);
        this.zzBb.addView((View)textView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 17));
        this.zzBb.bringChildToFront((View)textView);
    }
    
    void zzeR() {
        final long zzBh = this.zzBd.getCurrentPosition();
        if (this.zzBh != zzBh && zzBh > 0L) {
            this.zzeT();
            this.zza("timeupdate", "time", String.valueOf(zzBh / 1000.0f));
            this.zzBh = zzBh;
        }
    }
    
    public void zzeq() {
        this.zzBd.zzeq();
    }
    
    public void zzer() {
        this.zzBd.zzer();
    }
}
