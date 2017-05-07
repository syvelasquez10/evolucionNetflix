// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzb;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.google.android.gms.internal.zzce;
import com.google.android.gms.internal.zzcg;
import android.content.Context;
import com.google.android.gms.internal.zziz;
import android.widget.TextView;
import com.google.android.gms.internal.zzgr;
import android.widget.FrameLayout;

@zzgr
public class zzk extends FrameLayout implements zzh
{
    private final FrameLayout zzBN;
    private final zzq zzBO;
    private zzi zzBP;
    private boolean zzBQ;
    private boolean zzBR;
    private TextView zzBS;
    private long zzBT;
    private String zzBV;
    private final zziz zzoM;
    private String zzxZ;
    
    public zzk(final Context context, final zziz zzoM, final int n, final zzcg zzcg, final zzce zzce) {
        super(context);
        this.zzoM = zzoM;
        this.addView((View)(this.zzBN = new FrameLayout(context)), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        zzb.zzs(zzoM.zzhb());
        this.zzBP = zzoM.zzhb().zzoH.zza(context, zzoM, n, zzcg, zzce);
        if (this.zzBP != null) {
            this.zzBN.addView((View)this.zzBP, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 17));
        }
        (this.zzBS = new TextView(context)).setBackgroundColor(-16777216);
        this.zzeY();
        (this.zzBO = new zzq(this)).zzfg();
        if (this.zzBP != null) {
            this.zzBP.zza(this);
        }
        if (this.zzBP == null) {
            this.zzh("AdVideoUnderlay Error", "Allocating player failed.");
        }
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
        this.zzoM.zzb("onVideoEvent", hashMap);
    }
    
    public static void zzd(final zziz zziz) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "no_video_view");
        zziz.zzb("onVideoEvent", hashMap);
    }
    
    private void zzeY() {
        if (!this.zzfa()) {
            this.zzBN.addView((View)this.zzBS, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
            this.zzBN.bringChildToFront((View)this.zzBS);
        }
    }
    
    private void zzeZ() {
        if (this.zzfa()) {
            this.zzBN.removeView((View)this.zzBS);
        }
    }
    
    private boolean zzfa() {
        return this.zzBS.getParent() != null;
    }
    
    private void zzfc() {
        if (this.zzoM.zzgZ() != null && this.zzBQ && !this.zzBR) {
            this.zzoM.zzgZ().getWindow().clearFlags(128);
            this.zzBQ = false;
        }
    }
    
    public void destroy() {
        this.zzBO.cancel();
        if (this.zzBP != null) {
            this.zzBP.stop();
        }
        this.zzfc();
    }
    
    public void pause() {
        if (this.zzBP == null) {
            return;
        }
        this.zzBP.pause();
    }
    
    public void play() {
        if (this.zzBP == null) {
            return;
        }
        this.zzBP.play();
    }
    
    public void seekTo(final int n) {
        if (this.zzBP == null) {
            return;
        }
        this.zzBP.seekTo(n);
    }
    
    public void setMimeType(final String zzBV) {
        this.zzBV = zzBV;
    }
    
    public void zza(final float n) {
        if (this.zzBP == null) {
            return;
        }
        this.zzBP.zza(n);
    }
    
    public void zzan(final String zzxZ) {
        this.zzxZ = zzxZ;
    }
    
    public void zzd(final int n, final int n2, final int n3, final int n4) {
        if (n3 == 0 || n4 == 0) {
            return;
        }
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(n3 + 2, n4 + 2);
        layoutParams.setMargins(n - 1, n2 - 1, 0, 0);
        this.zzBN.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.requestLayout();
    }
    
    public void zzd(final MotionEvent motionEvent) {
        if (this.zzBP == null) {
            return;
        }
        this.zzBP.dispatchTouchEvent(motionEvent);
    }
    
    public void zzeV() {
        if (this.zzBP == null) {
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzxZ)) {
            this.zzBP.setMimeType(this.zzBV);
            this.zzBP.setVideoPath(this.zzxZ);
            return;
        }
        this.zza("no_src", new String[0]);
    }
    
    public void zzeW() {
        if (this.zzBP == null) {
            return;
        }
        final TextView textView = new TextView(this.zzBP.getContext());
        textView.setText((CharSequence)("AdMob - " + this.zzBP.zzer()));
        textView.setTextColor(-65536);
        textView.setBackgroundColor(-256);
        this.zzBN.addView((View)textView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 17));
        this.zzBN.bringChildToFront((View)textView);
    }
    
    void zzeX() {
        if (this.zzBP != null) {
            final long zzBT = this.zzBP.getCurrentPosition();
            if (this.zzBT != zzBT && zzBT > 0L) {
                this.zzeZ();
                this.zza("timeupdate", "time", String.valueOf(zzBT / 1000.0f));
                this.zzBT = zzBT;
            }
        }
    }
    
    public void zzex() {
        if (this.zzBP == null) {
            return;
        }
        this.zzBP.zzex();
    }
    
    public void zzey() {
        if (this.zzBP == null) {
            return;
        }
        this.zzBP.zzey();
    }
    
    public void zzh(final String s, final String s2) {
        this.zza("error", "what", s, "extra", s2);
    }
}
