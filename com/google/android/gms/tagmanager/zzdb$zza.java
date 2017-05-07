// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.util.HashSet;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;
import java.util.Set;
import android.os.HandlerThread;
import android.os.Handler;
import android.content.Context;
import java.util.Iterator;
import android.os.Process;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.os.PowerManager;
import android.app.KeyguardManager;
import android.app.ActivityManager;

final class zzdb$zza implements Runnable
{
    private final long zzOC;
    private final long zzaBW;
    private final String zzaSF;
    private final String zzaSG;
    private final long zzaSH;
    private long zzaSI;
    final /* synthetic */ zzdb zzaSJ;
    
    zzdb$zza(final zzdb zzaSJ, final String zzaSF, final String zzaSG, final long zzaBW, final long zzaSH) {
        this.zzaSJ = zzaSJ;
        this.zzaSF = zzaSF;
        this.zzaSG = zzaSG;
        this.zzaBW = zzaBW;
        this.zzaSH = zzaSH;
        this.zzOC = System.currentTimeMillis();
    }
    
    @Override
    public void run() {
        if (this.zzaSH > 0L && this.zzaSI >= this.zzaSH) {
            if (!"0".equals(this.zzaSG)) {
                this.zzaSJ.zzaSE.remove(this.zzaSG);
            }
            return;
        }
        ++this.zzaSI;
        if (this.zzcu()) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.zzaSJ.zzaOT.push(DataLayer.mapOf("event", this.zzaSF, "gtm.timerInterval", String.valueOf(this.zzaBW), "gtm.timerLimit", String.valueOf(this.zzaSH), "gtm.timerStartTime", String.valueOf(this.zzOC), "gtm.timerCurrentTime", String.valueOf(currentTimeMillis), "gtm.timerElapsedTime", String.valueOf(currentTimeMillis - this.zzOC), "gtm.timerEventNumber", String.valueOf(this.zzaSI), "gtm.triggers", this.zzaSG));
        }
        this.zzaSJ.mHandler.postDelayed((Runnable)this, this.zzaBW);
    }
    
    protected boolean zzcu() {
        if (this.zzaSJ.zzaSC) {
            return this.zzaSJ.zzaSB;
        }
        final ActivityManager activityManager = (ActivityManager)this.zzaSJ.mContext.getSystemService("activity");
        final KeyguardManager keyguardManager = (KeyguardManager)this.zzaSJ.mContext.getSystemService("keyguard");
        final PowerManager powerManager = (PowerManager)this.zzaSJ.mContext.getSystemService("power");
        for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (Process.myPid() == activityManager$RunningAppProcessInfo.pid && activityManager$RunningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && powerManager.isScreenOn()) {
                return true;
            }
        }
        return false;
    }
}
