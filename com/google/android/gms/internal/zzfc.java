// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewParent;
import android.view.Window;
import android.widget.RelativeLayout$LayoutParams;
import com.google.android.gms.ads.AdSize;
import android.view.ViewGroup$LayoutParams;
import android.view.View$OnClickListener;
import android.view.View;
import android.content.Context;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import java.util.Map;
import com.google.android.gms.ads.internal.zzp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.app.Activity;
import java.util.Set;

@zzgr
public class zzfc extends zzfh
{
    static final Set<String> zzAb;
    private String zzAc;
    private boolean zzAd;
    private int zzAe;
    private int zzAf;
    private int zzAg;
    private int zzAh;
    private final Activity zzAi;
    private ImageView zzAj;
    private LinearLayout zzAk;
    private zzfi zzAl;
    private PopupWindow zzAm;
    private RelativeLayout zzAn;
    private ViewGroup zzAo;
    private int zznQ;
    private int zznR;
    private final zziz zzoM;
    private final Object zzpd;
    private AdSizeParcel zzzm;
    
    static {
        zzAb = new HashSet<String>(Arrays.asList("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"));
    }
    
    public zzfc(final zziz zzoM, final zzfi zzAl) {
        super(zzoM, "resize");
        this.zzAc = "top-right";
        this.zzAd = true;
        this.zzAe = 0;
        this.zzAf = 0;
        this.zznR = -1;
        this.zzAg = 0;
        this.zzAh = 0;
        this.zznQ = -1;
        this.zzpd = new Object();
        this.zzoM = zzoM;
        this.zzAi = zzoM.zzgZ();
        this.zzAl = zzAl;
    }
    
    private int[] zzee() {
        if (!this.zzeg()) {
            return null;
        }
        if (this.zzAd) {
            return new int[] { this.zzAe + this.zzAg, this.zzAf + this.zzAh };
        }
        final int[] zzh = zzp.zzbv().zzh(this.zzAi);
        final int[] zzj = zzp.zzbv().zzj(this.zzAi);
        final int n = zzh[0];
        final int n2 = this.zzAe + this.zzAg;
        final int n3 = this.zzAf + this.zzAh;
        int n4;
        if (n2 < 0) {
            n4 = 0;
        }
        else {
            n4 = n2;
            if (this.zznQ + n2 > n) {
                n4 = n - this.zznQ;
            }
        }
        int n5;
        if (n3 < zzj[0]) {
            n5 = zzj[0];
        }
        else {
            n5 = n3;
            if (this.zznR + n3 > zzj[1]) {
                n5 = zzj[1] - this.zznR;
            }
        }
        return new int[] { n4, n5 };
    }
    
    private void zzf(final Map<String, String> map) {
        if (!TextUtils.isEmpty((CharSequence)map.get("width"))) {
            this.zznQ = zzp.zzbv().zzaA(map.get("width"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("height"))) {
            this.zznR = zzp.zzbv().zzaA(map.get("height"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("offsetX"))) {
            this.zzAg = zzp.zzbv().zzaA(map.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("offsetY"))) {
            this.zzAh = zzp.zzbv().zzaA(map.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("allowOffscreen"))) {
            this.zzAd = Boolean.parseBoolean(map.get("allowOffscreen"));
        }
        final String zzAc = map.get("customClosePosition");
        if (!TextUtils.isEmpty((CharSequence)zzAc)) {
            this.zzAc = zzAc;
        }
    }
    
    void zzb(final int n, final int n2) {
        if (this.zzAl != null) {
            this.zzAl.zza(n, n2, this.zznQ, this.zznR);
        }
    }
    
    void zzc(final int n, final int n2) {
        this.zzb(n, n2 - zzp.zzbv().zzj(this.zzAi)[0], this.zznQ, this.zznR);
    }
    
    public void zzd(final int zzAe, final int zzAf) {
        this.zzAe = zzAe;
        this.zzAf = zzAf;
    }
    
    boolean zzed() {
        return this.zznQ > -1 && this.zznR > -1;
    }
    
    public boolean zzef() {
        while (true) {
            synchronized (this.zzpd) {
                if (this.zzAm != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    boolean zzeg() {
        final int[] zzh = zzp.zzbv().zzh(this.zzAi);
        final int[] zzj = zzp.zzbv().zzj(this.zzAi);
        final int n = zzh[0];
        final int n2 = zzh[1];
        if (this.zznQ < 50 || this.zznQ > n) {
            zzb.zzaH("Width is too small or too large.");
        }
        else {
            if (this.zznR < 50 || this.zznR > n2) {
                zzb.zzaH("Height is too small or too large.");
                return false;
            }
            if (this.zznR == n2 && this.zznQ == n) {
                zzb.zzaH("Cannot resize to a full-screen ad.");
                return false;
            }
            if (this.zzAd) {
                final String zzAc = this.zzAc;
                int n3 = -1;
                switch (zzAc.hashCode()) {
                    case -1012429441: {
                        if (zzAc.equals("top-left")) {
                            n3 = 0;
                            break;
                        }
                        break;
                    }
                    case 1755462605: {
                        if (zzAc.equals("top-center")) {
                            n3 = 1;
                            break;
                        }
                        break;
                    }
                    case -1364013995: {
                        if (zzAc.equals("center")) {
                            n3 = 2;
                            break;
                        }
                        break;
                    }
                    case -655373719: {
                        if (zzAc.equals("bottom-left")) {
                            n3 = 3;
                            break;
                        }
                        break;
                    }
                    case 1288627767: {
                        if (zzAc.equals("bottom-center")) {
                            n3 = 4;
                            break;
                        }
                        break;
                    }
                    case 1163912186: {
                        if (zzAc.equals("bottom-right")) {
                            n3 = 5;
                            break;
                        }
                        break;
                    }
                }
                int n4 = 0;
                int n5 = 0;
                switch (n3) {
                    default: {
                        n4 = this.zzAe + this.zzAg + this.zznQ - 50;
                        n5 = this.zzAf + this.zzAh;
                        break;
                    }
                    case 0: {
                        n4 = this.zzAg + this.zzAe;
                        n5 = this.zzAf + this.zzAh;
                        break;
                    }
                    case 1: {
                        n4 = this.zzAe + this.zzAg + this.zznQ / 2 - 25;
                        n5 = this.zzAf + this.zzAh;
                        break;
                    }
                    case 2: {
                        n4 = this.zzAe + this.zzAg + this.zznQ / 2 - 25;
                        n5 = this.zzAf + this.zzAh + this.zznR / 2 - 25;
                        break;
                    }
                    case 3: {
                        n4 = this.zzAg + this.zzAe;
                        n5 = this.zzAf + this.zzAh + this.zznR - 50;
                        break;
                    }
                    case 4: {
                        n4 = this.zzAe + this.zzAg + this.zznQ / 2 - 25;
                        n5 = this.zzAf + this.zzAh + this.zznR - 50;
                        break;
                    }
                    case 5: {
                        n4 = this.zzAe + this.zzAg + this.zznQ - 50;
                        n5 = this.zzAf + this.zzAh + this.zznR - 50;
                        break;
                    }
                }
                if (n4 < 0 || n4 + 50 > n || n5 < zzj[0] || n5 + 50 > zzj[1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public void zzg(final Map<String, String> map) {
        synchronized (this.zzpd) {
            if (this.zzAi == null) {
                this.zzak("Not an activity context. Cannot resize.");
                return;
            }
            if (this.zzoM.zzaN() == null) {
                this.zzak("Webview is not yet available, size is not set.");
                return;
            }
        }
        if (this.zzoM.zzaN().zztf) {
            this.zzak("Is interstitial. Cannot resize an interstitial.");
            // monitorexit(o)
            return;
        }
        if (this.zzoM.zzhi()) {
            this.zzak("Cannot resize an expanded banner.");
            // monitorexit(o)
            return;
        }
        final Map<String, String> map2;
        this.zzf(map2);
        if (!this.zzed()) {
            this.zzak("Invalid width and height options. Cannot resize.");
            // monitorexit(o)
            return;
        }
        final Window window = this.zzAi.getWindow();
        if (window == null || window.getDecorView() == null) {
            this.zzak("Activity context is not ready, cannot get window or decor view.");
            // monitorexit(o)
            return;
        }
        final int[] zzee = this.zzee();
        if (zzee == null) {
            this.zzak("Resize location out of screen or close button is not visible.");
            // monitorexit(o)
            return;
        }
        final int zzb = zzl.zzcF().zzb((Context)this.zzAi, this.zznQ);
        final int zzb2 = zzl.zzcF().zzb((Context)this.zzAi, this.zznR);
        final ViewParent parent = this.zzoM.getView().getParent();
        RelativeLayout$LayoutParams relativeLayout$LayoutParams = null;
        String zzAc;
        int n = 0;
        Label_0788_Outer:Label_0833_Outer:
        while (true) {
        Label_0850_Outer:
            while (true) {
            Label_0911_Outer:
                while (true) {
                Label_0894_Outer:
                    while (true) {
                    Label_0877_Outer:
                        while (true) {
                        Label_0867_Outer:
                            while (true) {
                                while (true) {
                                    Label_1025: {
                                    Label_0758_Outer:
                                        while (true) {
                                        Label_0773_Outer:
                                            while (true) {
                                                while (true) {
                                                Label_0818_Outer:
                                                    while (true) {
                                                        while (true) {
                                                            while (true) {
                                                                Label_0732: {
                                                                    if (parent == null || !(parent instanceof ViewGroup)) {
                                                                        break Label_0732;
                                                                    }
                                                                    ((ViewGroup)parent).removeView(this.zzoM.getView());
                                                                    if (this.zzAm == null) {
                                                                        this.zzAo = (ViewGroup)parent;
                                                                        (this.zzAj = new ImageView((Context)this.zzAi)).setImageBitmap(zzp.zzbv().zzk(this.zzoM.getView()));
                                                                        this.zzzm = this.zzoM.zzaN();
                                                                        this.zzAo.addView((View)this.zzAj);
                                                                        break Label_0732;
                                                                    }
                                                                    Label_0722: {
                                                                        break Label_0722;
                                                                        while (true) {
                                                                            this.zzAk.setOnClickListener((View$OnClickListener)new zzfc$1(this));
                                                                            this.zzAk.setContentDescription((CharSequence)"Close button");
                                                                            this.zzAn.addView((View)this.zzAk, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
                                                                            try {
                                                                                this.zzAm.showAtLocation(window.getDecorView(), 0, zzl.zzcF().zzb((Context)this.zzAi, zzee[0]), zzl.zzcF().zzb((Context)this.zzAi, zzee[1]));
                                                                                this.zzb(zzee[0], zzee[1]);
                                                                                this.zzoM.zza(new AdSizeParcel((Context)this.zzAi, new AdSize(this.zznQ, this.zznR)));
                                                                                this.zzc(zzee[0], zzee[1]);
                                                                                this.zzam("resized");
                                                                                // monitorexit(o)
                                                                                return;
                                                                                // iftrue(Label_1023:, !zzAc.equals((Object)"bottom-left"))
                                                                                // iftrue(Label_1023:, !zzAc.equals((Object)"top-center"))
                                                                                // iftrue(Label_1023:, !zzAc.equals((Object)"center"))
                                                                                // iftrue(Label_1023:, !zzAc.equals((Object)"bottom-center"))
                                                                                // iftrue(Label_1023:, !zzAc.equals((Object)"bottom-right"))
                                                                                // iftrue(Label_1023:, !zzAc.equals((Object)"top-left"))
                                                                                // monitorexit(o)
                                                                                Block_22: {
                                                                                    while (true) {
                                                                                        Block_18: {
                                                                                            Block_21: {
                                                                                                Block_19: {
                                                                                                    Block_20: {
                                                                                                        break Block_20;
                                                                                                        break Block_18;
                                                                                                        break Block_19;
                                                                                                        relativeLayout$LayoutParams.addRule(10);
                                                                                                        relativeLayout$LayoutParams.addRule(9);
                                                                                                        continue Label_0788_Outer;
                                                                                                        this.zzAm.dismiss();
                                                                                                        break;
                                                                                                    }
                                                                                                    n = 3;
                                                                                                    break Label_1025;
                                                                                                    break Block_21;
                                                                                                    break Block_22;
                                                                                                }
                                                                                                n = 2;
                                                                                                break Label_1025;
                                                                                                relativeLayout$LayoutParams.addRule(10);
                                                                                                relativeLayout$LayoutParams.addRule(14);
                                                                                                continue Label_0788_Outer;
                                                                                            }
                                                                                            n = 4;
                                                                                            break Label_1025;
                                                                                            n = 0;
                                                                                            break Label_1025;
                                                                                            relativeLayout$LayoutParams.addRule(12);
                                                                                            relativeLayout$LayoutParams.addRule(11);
                                                                                            continue Label_0788_Outer;
                                                                                        }
                                                                                        n = 1;
                                                                                        break Label_1025;
                                                                                        relativeLayout$LayoutParams.addRule(12);
                                                                                        relativeLayout$LayoutParams.addRule(14);
                                                                                        continue Label_0788_Outer;
                                                                                        relativeLayout$LayoutParams.addRule(12);
                                                                                        relativeLayout$LayoutParams.addRule(9);
                                                                                        continue Label_0788_Outer;
                                                                                        continue Label_0911_Outer;
                                                                                    }
                                                                                    this.zzak("Webview is detached, probably in the middle of a resize or expand.");
                                                                                    return;
                                                                                    relativeLayout$LayoutParams.addRule(13);
                                                                                    continue Label_0788_Outer;
                                                                                }
                                                                                n = 5;
                                                                                break Label_1025;
                                                                            }
                                                                            catch (RuntimeException ex) {
                                                                                this.zzak("Cannot show popup window: " + ex.getMessage());
                                                                                this.zzAn.removeView(this.zzoM.getView());
                                                                                if (this.zzAo != null) {
                                                                                    this.zzAo.removeView((View)this.zzAj);
                                                                                    this.zzAo.addView(this.zzoM.getView());
                                                                                    this.zzoM.zza(this.zzzm);
                                                                                }
                                                                                // monitorexit(o)
                                                                                return;
                                                                            }
                                                                            break Label_0758_Outer;
                                                                        }
                                                                    }
                                                                }
                                                                (this.zzAn = new RelativeLayout((Context)this.zzAi)).setBackgroundColor(0);
                                                                this.zzAn.setLayoutParams(new ViewGroup$LayoutParams(zzb, zzb2));
                                                                (this.zzAm = zzp.zzbv().zza((View)this.zzAn, zzb, zzb2, false)).setOutsideTouchable(true);
                                                                this.zzAm.setTouchable(true);
                                                                this.zzAm.setClippingEnabled(!this.zzAd);
                                                                this.zzAn.addView(this.zzoM.getView(), -1, -1);
                                                                this.zzAk = new LinearLayout((Context)this.zzAi);
                                                                relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(zzl.zzcF().zzb((Context)this.zzAi, 50), zzl.zzcF().zzb((Context)this.zzAi, 50));
                                                                zzAc = this.zzAc;
                                                                switch (zzAc.hashCode()) {
                                                                    case -1012429441: {
                                                                        continue Label_0867_Outer;
                                                                    }
                                                                    case 1755462605: {
                                                                        continue Label_0773_Outer;
                                                                    }
                                                                    case -1364013995: {
                                                                        continue Label_0833_Outer;
                                                                    }
                                                                    case -655373719: {
                                                                        continue Label_0758_Outer;
                                                                    }
                                                                    case 1288627767: {
                                                                        continue Label_0818_Outer;
                                                                    }
                                                                    case 1163912186: {
                                                                        continue Label_0850_Outer;
                                                                    }
                                                                }
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            break;
                                        }
                                        n = -1;
                                    }
                                    switch (n) {
                                        case 0: {
                                            continue Label_0850_Outer;
                                        }
                                        case 1: {
                                            continue Label_0911_Outer;
                                        }
                                        case 2: {
                                            continue;
                                        }
                                        case 3: {
                                            continue Label_0867_Outer;
                                        }
                                        case 4: {
                                            continue Label_0877_Outer;
                                        }
                                        case 5: {
                                            continue Label_0894_Outer;
                                        }
                                        default: {
                                            relativeLayout$LayoutParams.addRule(10);
                                            relativeLayout$LayoutParams.addRule(11);
                                            continue Label_0788_Outer;
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    break;
                }
                break;
            }
            break;
        }
    }
    
    public void zzn(final boolean b) {
        synchronized (this.zzpd) {
            if (this.zzAm != null) {
                this.zzAm.dismiss();
                this.zzAn.removeView(this.zzoM.getView());
                if (this.zzAo != null) {
                    this.zzAo.removeView((View)this.zzAj);
                    this.zzAo.addView(this.zzoM.getView());
                    this.zzoM.zza(this.zzzm);
                }
                if (b) {
                    this.zzam("default");
                    if (this.zzAl != null) {
                        this.zzAl.zzbc();
                    }
                }
                this.zzAm = null;
                this.zzAn = null;
                this.zzAo = null;
                this.zzAk = null;
            }
        }
    }
}
