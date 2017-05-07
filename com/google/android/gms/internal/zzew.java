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
import com.google.android.gms.ads.internal.client.zzk;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import java.util.Map;
import com.google.android.gms.ads.internal.zzp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import java.util.Set;

@zzgk
public class zzew extends zzfb
{
    static final Set<String> zzzo;
    private int zznP;
    private int zznQ;
    private final zzip zzoL;
    private final Object zzpc;
    private AdSizeParcel zzyK;
    private RelativeLayout zzzA;
    private ViewGroup zzzB;
    private String zzzp;
    private boolean zzzq;
    private int zzzr;
    private int zzzs;
    private int zzzt;
    private int zzzu;
    private final Activity zzzv;
    private ImageView zzzw;
    private LinearLayout zzzx;
    private zzfc zzzy;
    private PopupWindow zzzz;
    
    static {
        zzzo = new HashSet<String>(Arrays.asList("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"));
    }
    
    public zzew(final zzip zzoL, final zzfc zzzy) {
        super(zzoL, "resize");
        this.zzzp = "top-right";
        this.zzzq = true;
        this.zzzr = 0;
        this.zzzs = 0;
        this.zznQ = -1;
        this.zzzt = 0;
        this.zzzu = 0;
        this.zznP = -1;
        this.zzpc = new Object();
        this.zzoL = zzoL;
        this.zzzv = zzoL.zzgN();
        this.zzzy = zzzy;
    }
    
    private int[] zzdX() {
        if (!this.zzdZ()) {
            return null;
        }
        if (this.zzzq) {
            return new int[] { this.zzzr + this.zzzt, this.zzzs + this.zzzu };
        }
        final int[] zzh = zzp.zzbx().zzh(this.zzzv);
        final int[] zzj = zzp.zzbx().zzj(this.zzzv);
        final int n = zzh[0];
        final int n2 = this.zzzr + this.zzzt;
        final int n3 = this.zzzs + this.zzzu;
        int n4;
        if (n2 < 0) {
            n4 = 0;
        }
        else {
            n4 = n2;
            if (this.zznP + n2 > n) {
                n4 = n - this.zznP;
            }
        }
        int n5;
        if (n3 < zzj[0]) {
            n5 = zzj[0];
        }
        else {
            n5 = n3;
            if (this.zznQ + n3 > zzj[1]) {
                n5 = zzj[1] - this.zznQ;
            }
        }
        return new int[] { n4, n5 };
    }
    
    private void zzf(final Map<String, String> map) {
        if (!TextUtils.isEmpty((CharSequence)map.get("width"))) {
            this.zznP = zzp.zzbx().zzax(map.get("width"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("height"))) {
            this.zznQ = zzp.zzbx().zzax(map.get("height"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("offsetX"))) {
            this.zzzt = zzp.zzbx().zzax(map.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("offsetY"))) {
            this.zzzu = zzp.zzbx().zzax(map.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("allowOffscreen"))) {
            this.zzzq = Boolean.parseBoolean(map.get("allowOffscreen"));
        }
        final String zzzp = map.get("customClosePosition");
        if (!TextUtils.isEmpty((CharSequence)zzzp)) {
            this.zzzp = zzzp;
        }
    }
    
    void zzb(final int n, final int n2) {
        if (this.zzzy != null) {
            this.zzzy.zza(n, n2, this.zznP, this.zznQ);
        }
    }
    
    void zzc(final int n, final int n2) {
        this.zzb(n, n2 - zzp.zzbx().zzj(this.zzzv)[0], this.zznP, this.zznQ);
    }
    
    public void zzd(final int zzzr, final int zzzs) {
        this.zzzr = zzzr;
        this.zzzs = zzzs;
    }
    
    boolean zzdW() {
        return this.zznP > -1 && this.zznQ > -1;
    }
    
    public boolean zzdY() {
        while (true) {
            synchronized (this.zzpc) {
                if (this.zzzz != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    boolean zzdZ() {
        final int[] zzh = zzp.zzbx().zzh(this.zzzv);
        final int[] zzj = zzp.zzbx().zzj(this.zzzv);
        final int n = zzh[0];
        final int n2 = zzh[1];
        if (this.zznP < 50 || this.zznP > n) {
            zzb.zzaE("Width is too small or too large.");
        }
        else {
            if (this.zznQ < 50 || this.zznQ > n2) {
                zzb.zzaE("Height is too small or too large.");
                return false;
            }
            if (this.zznQ == n2 && this.zznP == n) {
                zzb.zzaE("Cannot resize to a full-screen ad.");
                return false;
            }
            if (this.zzzq) {
                final String zzzp = this.zzzp;
                int n3 = -1;
                switch (zzzp.hashCode()) {
                    case -1012429441: {
                        if (zzzp.equals("top-left")) {
                            n3 = 0;
                            break;
                        }
                        break;
                    }
                    case 1755462605: {
                        if (zzzp.equals("top-center")) {
                            n3 = 1;
                            break;
                        }
                        break;
                    }
                    case -1364013995: {
                        if (zzzp.equals("center")) {
                            n3 = 2;
                            break;
                        }
                        break;
                    }
                    case -655373719: {
                        if (zzzp.equals("bottom-left")) {
                            n3 = 3;
                            break;
                        }
                        break;
                    }
                    case 1288627767: {
                        if (zzzp.equals("bottom-center")) {
                            n3 = 4;
                            break;
                        }
                        break;
                    }
                    case 1163912186: {
                        if (zzzp.equals("bottom-right")) {
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
                        n4 = this.zzzr + this.zzzt + this.zznP - 50;
                        n5 = this.zzzs + this.zzzu;
                        break;
                    }
                    case 0: {
                        n4 = this.zzzt + this.zzzr;
                        n5 = this.zzzs + this.zzzu;
                        break;
                    }
                    case 1: {
                        n4 = this.zzzr + this.zzzt + this.zznP / 2 - 25;
                        n5 = this.zzzs + this.zzzu;
                        break;
                    }
                    case 2: {
                        n4 = this.zzzr + this.zzzt + this.zznP / 2 - 25;
                        n5 = this.zzzs + this.zzzu + this.zznQ / 2 - 25;
                        break;
                    }
                    case 3: {
                        n4 = this.zzzt + this.zzzr;
                        n5 = this.zzzs + this.zzzu + this.zznQ - 50;
                        break;
                    }
                    case 4: {
                        n4 = this.zzzr + this.zzzt + this.zznP / 2 - 25;
                        n5 = this.zzzs + this.zzzu + this.zznQ - 50;
                        break;
                    }
                    case 5: {
                        n4 = this.zzzr + this.zzzt + this.zznP - 50;
                        n5 = this.zzzs + this.zzzu + this.zznQ - 50;
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
        synchronized (this.zzpc) {
            if (this.zzzv == null) {
                this.zzah("Not an activity context. Cannot resize.");
                return;
            }
            if (this.zzoL.zzaN() == null) {
                this.zzah("Webview is not yet available, size is not set.");
                return;
            }
        }
        if (this.zzoL.zzaN().zzsH) {
            this.zzah("Is interstitial. Cannot resize an interstitial.");
            // monitorexit(o)
            return;
        }
        if (this.zzoL.zzgW()) {
            this.zzah("Cannot resize an expanded banner.");
            // monitorexit(o)
            return;
        }
        final Map<String, String> map2;
        this.zzf(map2);
        if (!this.zzdW()) {
            this.zzah("Invalid width and height options. Cannot resize.");
            // monitorexit(o)
            return;
        }
        final Window window = this.zzzv.getWindow();
        if (window == null || window.getDecorView() == null) {
            this.zzah("Activity context is not ready, cannot get window or decor view.");
            // monitorexit(o)
            return;
        }
        final int[] zzdX = this.zzdX();
        if (zzdX == null) {
            this.zzah("Resize location out of screen or close button is not visible.");
            // monitorexit(o)
            return;
        }
        final int zzb = zzk.zzcE().zzb((Context)this.zzzv, this.zznP);
        final int zzb2 = zzk.zzcE().zzb((Context)this.zzzv, this.zznQ);
        final ViewParent parent = this.zzoL.getWebView().getParent();
        RelativeLayout$LayoutParams relativeLayout$LayoutParams = null;
        String zzzp;
        int n = 0;
        Label_0773_Outer:Label_0850_Outer:
        while (true) {
        Label_0911_Outer:
            while (true) {
            Label_0877_Outer:
                while (true) {
                Label_0833_Outer:
                    while (true) {
                    Label_0867_Outer:
                        while (true) {
                        Label_0894_Outer:
                            while (true) {
                                while (true) {
                                    Label_1025: {
                                    Label_0818_Outer:
                                        while (true) {
                                            while (true) {
                                            Label_0758_Outer:
                                                while (true) {
                                                Label_0803_Outer:
                                                    while (true) {
                                                        while (true) {
                                                            while (true) {
                                                                Label_0732: {
                                                                    if (parent == null || !(parent instanceof ViewGroup)) {
                                                                        break Label_0732;
                                                                    }
                                                                    ((ViewGroup)parent).removeView((View)this.zzoL.getWebView());
                                                                    if (this.zzzz == null) {
                                                                        this.zzzB = (ViewGroup)parent;
                                                                        (this.zzzw = new ImageView((Context)this.zzzv)).setImageBitmap(zzp.zzbx().zzj((View)this.zzoL.getWebView()));
                                                                        this.zzyK = this.zzoL.zzaN();
                                                                        this.zzzB.addView((View)this.zzzw);
                                                                        break Label_0732;
                                                                    }
                                                                    Label_0722: {
                                                                        break Label_0722;
                                                                        while (true) {
                                                                            this.zzzx.setOnClickListener((View$OnClickListener)new zzew$1(this));
                                                                            this.zzzx.setContentDescription((CharSequence)"Close button");
                                                                            this.zzzA.addView((View)this.zzzx, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
                                                                            try {
                                                                                this.zzzz.showAtLocation(window.getDecorView(), 0, zzk.zzcE().zzb((Context)this.zzzv, zzdX[0]), zzk.zzcE().zzb((Context)this.zzzv, zzdX[1]));
                                                                                this.zzb(zzdX[0], zzdX[1]);
                                                                                this.zzoL.zza(new AdSizeParcel((Context)this.zzzv, new AdSize(this.zznP, this.zznQ)));
                                                                                this.zzc(zzdX[0], zzdX[1]);
                                                                                this.zzaj("resized");
                                                                                // monitorexit(o)
                                                                                return;
                                                                                this.zzah("Webview is detached, probably in the middle of a resize or expand.");
                                                                                // monitorexit(o)
                                                                                return;
                                                                                // iftrue(Label_1023:, !zzzp.equals((Object)"center"))
                                                                                // iftrue(Label_1023:, !zzzp.equals((Object)"bottom-right"))
                                                                                // iftrue(Label_1023:, !zzzp.equals((Object)"bottom-left"))
                                                                                // iftrue(Label_1023:, !zzzp.equals((Object)"top-center"))
                                                                                // iftrue(Label_1023:, !zzzp.equals((Object)"bottom-center"))
                                                                                Block_18: {
                                                                                    Block_22: {
                                                                                        Block_20: {
                                                                                            Block_19: {
                                                                                                break Block_19;
                                                                                                break Block_22;
                                                                                                relativeLayout$LayoutParams.addRule(10);
                                                                                                relativeLayout$LayoutParams.addRule(14);
                                                                                                continue Label_0773_Outer;
                                                                                                relativeLayout$LayoutParams.addRule(12);
                                                                                                relativeLayout$LayoutParams.addRule(11);
                                                                                                continue Label_0773_Outer;
                                                                                                relativeLayout$LayoutParams.addRule(12);
                                                                                                relativeLayout$LayoutParams.addRule(9);
                                                                                                continue Label_0773_Outer;
                                                                                                relativeLayout$LayoutParams.addRule(10);
                                                                                                relativeLayout$LayoutParams.addRule(9);
                                                                                                continue Label_0773_Outer;
                                                                                                break Block_20;
                                                                                                break Block_18;
                                                                                            }
                                                                                            n = 2;
                                                                                            break Label_1025;
                                                                                            n = 4;
                                                                                            break Label_1025;
                                                                                        }
                                                                                        n = 3;
                                                                                        break Label_1025;
                                                                                    }
                                                                                    n = 5;
                                                                                    break Label_1025;
                                                                                    this.zzzz.dismiss();
                                                                                    break;
                                                                                }
                                                                                n = 1;
                                                                                break Label_1025;
                                                                                relativeLayout$LayoutParams.addRule(13);
                                                                                continue Label_0773_Outer;
                                                                                relativeLayout$LayoutParams.addRule(12);
                                                                                relativeLayout$LayoutParams.addRule(14);
                                                                                continue Label_0773_Outer;
                                                                                // iftrue(Label_1023:, !zzzp.equals((Object)"top-left"))
                                                                                n = 0;
                                                                                break Label_1025;
                                                                            }
                                                                            catch (RuntimeException ex) {
                                                                                this.zzah("Cannot show popup window: " + ex.getMessage());
                                                                                this.zzzA.removeView((View)this.zzoL.getWebView());
                                                                                if (this.zzzB != null) {
                                                                                    this.zzzB.removeView((View)this.zzzw);
                                                                                    this.zzzB.addView((View)this.zzoL.getWebView());
                                                                                    this.zzoL.zza(this.zzyK);
                                                                                }
                                                                                // monitorexit(o)
                                                                                return;
                                                                            }
                                                                            break Label_0818_Outer;
                                                                        }
                                                                    }
                                                                }
                                                                (this.zzzA = new RelativeLayout((Context)this.zzzv)).setBackgroundColor(0);
                                                                this.zzzA.setLayoutParams(new ViewGroup$LayoutParams(zzb, zzb2));
                                                                (this.zzzz = zzp.zzbx().zza((View)this.zzzA, zzb, zzb2, false)).setOutsideTouchable(true);
                                                                this.zzzz.setTouchable(true);
                                                                this.zzzz.setClippingEnabled(!this.zzzq);
                                                                this.zzzA.addView((View)this.zzoL.getWebView(), -1, -1);
                                                                this.zzzx = new LinearLayout((Context)this.zzzv);
                                                                relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(zzk.zzcE().zzb((Context)this.zzzv, 50), zzk.zzcE().zzb((Context)this.zzzv, 50));
                                                                zzzp = this.zzzp;
                                                                switch (zzzp.hashCode()) {
                                                                    case -1012429441: {
                                                                        continue;
                                                                    }
                                                                    case 1755462605: {
                                                                        continue Label_0803_Outer;
                                                                    }
                                                                    case -1364013995: {
                                                                        continue Label_0818_Outer;
                                                                    }
                                                                    case -655373719: {
                                                                        continue Label_0758_Outer;
                                                                    }
                                                                    case 1288627767: {
                                                                        continue Label_0867_Outer;
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
                                            continue Label_0867_Outer;
                                        }
                                        case 1: {
                                            continue Label_0911_Outer;
                                        }
                                        case 2: {
                                            continue Label_0894_Outer;
                                        }
                                        case 3: {
                                            continue Label_0833_Outer;
                                        }
                                        case 4: {
                                            continue;
                                        }
                                        case 5: {
                                            continue Label_0877_Outer;
                                        }
                                        default: {
                                            relativeLayout$LayoutParams.addRule(10);
                                            relativeLayout$LayoutParams.addRule(11);
                                            continue Label_0773_Outer;
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
        synchronized (this.zzpc) {
            if (this.zzzz != null) {
                this.zzzz.dismiss();
                this.zzzA.removeView((View)this.zzoL.getWebView());
                if (this.zzzB != null) {
                    this.zzzB.removeView((View)this.zzzw);
                    this.zzzB.addView((View)this.zzoL.getWebView());
                    this.zzoL.zza(this.zzyK);
                }
                if (b) {
                    this.zzaj("default");
                    if (this.zzzy != null) {
                        this.zzzy.zzbc();
                    }
                }
                this.zzzz = null;
                this.zzzA = null;
                this.zzzB = null;
                this.zzzx = null;
            }
        }
    }
}
