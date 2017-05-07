// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.content.pm.ApplicationInfo;
import com.google.android.gms.internal.zzlw;
import android.os.Process;
import com.google.android.gms.common.internal.zzd;
import java.util.HashSet;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import java.util.Set;

public class zzr
{
    private final zzf zzKa;
    private volatile Boolean zzMN;
    private String zzMO;
    private Set<Integer> zzMP;
    
    protected zzr(final zzf zzKa) {
        zzx.zzv(zzKa);
        this.zzKa = zzKa;
    }
    
    public String zzjA() {
        return zzy.zzNq.get();
    }
    
    public String zzjB() {
        return zzy.zzNr.get();
    }
    
    public zzm zzjC() {
        return zzm.zzbh(zzy.zzNt.get());
    }
    
    public zzo zzjD() {
        return zzo.zzbi(zzy.zzNu.get());
    }
    
    public Set<Integer> zzjE() {
        final String zzMO = zzy.zzNz.get();
        Label_0103: {
            if (this.zzMP != null && this.zzMO != null && this.zzMO.equals(zzMO)) {
                break Label_0103;
            }
            final String[] split = TextUtils.split(zzMO, ",");
            final HashSet<Integer> zzMP = new HashSet<Integer>();
            final int length = split.length;
            int n = 0;
        Label_0085_Outer:
            while (true) {
                Label_0092: {
                    if (n >= length) {
                        break Label_0092;
                    }
                    final String s = split[n];
                    while (true) {
                        try {
                            zzMP.add(Integer.parseInt(s));
                            ++n;
                            continue Label_0085_Outer;
                            return this.zzMP;
                            this.zzMO = zzMO;
                            this.zzMP = zzMP;
                            return this.zzMP;
                        }
                        catch (NumberFormatException ex) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    public long zzjF() {
        return zzy.zzNI.get();
    }
    
    public long zzjG() {
        return zzy.zzNJ.get();
    }
    
    public long zzjH() {
        return zzy.zzNM.get();
    }
    
    public int zzjI() {
        return zzy.zzNd.get();
    }
    
    public int zzjJ() {
        return zzy.zzNf.get();
    }
    
    public String zzjK() {
        return "google_analytics_v4.db";
    }
    
    public String zzjL() {
        return "google_analytics2_v4.db";
    }
    
    public long zzjM() {
        return 86400000L;
    }
    
    public int zzjN() {
        return zzy.zzNC.get();
    }
    
    public int zzjO() {
        return zzy.zzND.get();
    }
    
    public long zzjP() {
        return zzy.zzNE.get();
    }
    
    public long zzjQ() {
        return zzy.zzNN.get();
    }
    
    public boolean zzjk() {
        return zzd.zzacF;
    }
    
    public boolean zzjl() {
        Label_0133: {
            if (this.zzMN != null) {
                break Label_0133;
            }
            synchronized (this) {
                if (this.zzMN == null) {
                    final ApplicationInfo applicationInfo = this.zzKa.getContext().getApplicationInfo();
                    final String zzj = zzlw.zzj(this.zzKa.getContext(), Process.myPid());
                    if (applicationInfo != null) {
                        final String processName = applicationInfo.processName;
                        this.zzMN = (processName != null && processName.equals(zzj));
                    }
                    if ((this.zzMN == null || !this.zzMN) && "com.google.android.gms.analytics".equals(zzj)) {
                        this.zzMN = Boolean.TRUE;
                    }
                    if (this.zzMN == null) {
                        this.zzMN = Boolean.TRUE;
                        this.zzKa.zzie().zzbc("My process not in the list of running processes");
                    }
                }
                // monitorexit(this)
                return this.zzMN;
            }
        }
    }
    
    public boolean zzjm() {
        return zzy.zzMZ.get();
    }
    
    public int zzjn() {
        return zzy.zzNs.get();
    }
    
    public int zzjo() {
        return zzy.zzNw.get();
    }
    
    public int zzjp() {
        return zzy.zzNx.get();
    }
    
    public int zzjq() {
        return zzy.zzNy.get();
    }
    
    public long zzjr() {
        return zzy.zzNh.get();
    }
    
    public long zzjs() {
        return zzy.zzNg.get();
    }
    
    public long zzjt() {
        return zzy.zzNk.get();
    }
    
    public long zzju() {
        return zzy.zzNl.get();
    }
    
    public int zzjv() {
        return zzy.zzNm.get();
    }
    
    public int zzjw() {
        return zzy.zzNn.get();
    }
    
    public long zzjx() {
        return zzy.zzNA.get();
    }
    
    public String zzjy() {
        return zzy.zzNp.get();
    }
    
    public String zzjz() {
        return zzy.zzNo.get();
    }
}
