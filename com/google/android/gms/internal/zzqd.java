// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.stats.zzf;
import com.google.android.gms.common.stats.zzh;
import android.util.Log;
import com.google.android.gms.common.internal.zzd;
import android.os.PowerManager;
import com.google.android.gms.common.internal.zzx;
import android.os.WorkSource;
import android.os.PowerManager$WakeLock;
import android.content.Context;

public class zzqd
{
    private static boolean DEBUG;
    private static String TAG;
    private final Context mContext;
    private int zzaOA;
    private final PowerManager$WakeLock zzaOu;
    private WorkSource zzaOv;
    private final int zzaOw;
    private final String zzaOx;
    private boolean zzaOy;
    private int zzaOz;
    private final String zzafS;
    
    static {
        zzqd.TAG = "WakeLock";
        zzqd.DEBUG = false;
    }
    
    public zzqd(final Context context, final int n, final String s) {
        this(context, n, s, null, null);
    }
    
    public zzqd(final Context context, final int zzaOw, String packageName, final String zzaOx, final String s) {
        this.zzaOy = true;
        zzx.zzh(packageName, "Wake lock name can NOT be empty");
        this.zzaOw = zzaOw;
        this.zzafS = packageName;
        this.zzaOx = zzaOx;
        this.mContext = context.getApplicationContext();
        this.zzaOu = ((PowerManager)context.getSystemService("power")).newWakeLock(zzaOw, packageName);
        if (zzma.zzaq(this.mContext)) {
            packageName = s;
            if (zzlz.zzcB(s)) {
                if (zzd.zzacF && zzkq.isInitialized()) {
                    Log.e(zzqd.TAG, "callingPackage is not supposed to be empty for wakelock " + this.zzafS + "!");
                    packageName = "com.google.android.gms";
                }
                else {
                    packageName = context.getPackageName();
                }
            }
            this.zzc(this.zzaOv = zzma.zzj(context, packageName));
        }
    }
    
    private void zzen(final String s) {
        final boolean zzep = this.zzep(s);
        final String zzi = this.zzi(s, zzep);
        if (zzqd.DEBUG) {
            Log.d(zzqd.TAG, "Acquire:\n mWakeLockName: " + this.zzafS + "\n mSecondaryName: " + this.zzaOx + "\nmReferenceCounted: " + this.zzaOy + "\nreason: " + s + "\nmOpenEventCount" + this.zzaOA + "\nuseWithReason: " + zzep + "\ntrackingName: " + zzi);
        }
        synchronized (this) {
            if ((this.zzaOy && (this.zzaOz++ == 0 || zzep)) || (!this.zzaOy && this.zzaOA == 0)) {
                zzh.zzpL().zza(this.mContext, zzf.zza(this.zzaOu, zzi), 7, this.zzafS, zzi, this.zzaOw, zzma.zzb(this.zzaOv));
                ++this.zzaOA;
            }
        }
    }
    
    private void zzeo(final String s) {
        final boolean zzep = this.zzep(s);
        final String zzi = this.zzi(s, zzep);
        if (zzqd.DEBUG) {
            Log.d(zzqd.TAG, "Release:\n mWakeLockName: " + this.zzafS + "\n mSecondaryName: " + this.zzaOx + "\nmReferenceCounted: " + this.zzaOy + "\nreason: " + s + "\n mOpenEventCount" + this.zzaOA + "\nuseWithReason: " + zzep + "\ntrackingName: " + zzi);
        }
        synchronized (this) {
            Label_0157: {
                if (this.zzaOy) {
                    final int zzaOz = this.zzaOz - 1;
                    this.zzaOz = zzaOz;
                    if (zzaOz == 0 || zzep) {
                        break Label_0157;
                    }
                }
                if (this.zzaOy || this.zzaOA != 1) {
                    return;
                }
            }
            zzh.zzpL().zza(this.mContext, zzf.zza(this.zzaOu, zzi), 8, this.zzafS, zzi, this.zzaOw, zzma.zzb(this.zzaOv));
            --this.zzaOA;
        }
    }
    
    private boolean zzep(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && !s.equals(this.zzaOx);
    }
    
    private String zzi(final String s, final boolean b) {
        if (!this.zzaOy) {
            return this.zzaOx;
        }
        if (b) {
            return s;
        }
        return this.zzaOx;
    }
    
    public void acquire(final long n) {
        if (!zzlv.zzpR() && this.zzaOy) {
            Log.wtf(zzqd.TAG, "Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: " + this.zzafS);
        }
        this.zzen(null);
        this.zzaOu.acquire(n);
    }
    
    public boolean isHeld() {
        return this.zzaOu.isHeld();
    }
    
    public void release() {
        this.zzeo(null);
        this.zzaOu.release();
    }
    
    public void setReferenceCounted(final boolean b) {
        this.zzaOu.setReferenceCounted(b);
        this.zzaOy = b;
    }
    
    public void zzc(final WorkSource zzaOv) {
        if (zzma.zzaq(this.mContext) && zzaOv != null) {
            if (this.zzaOv != null) {
                this.zzaOv.add(zzaOv);
            }
            else {
                this.zzaOv = zzaOv;
            }
            this.zzaOu.setWorkSource(this.zzaOv);
        }
    }
}
