// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.Context;
import android.util.Log;
import android.os.WorkSource;
import java.lang.reflect.Method;

public class zzma
{
    private static final Method zzagh;
    private static final Method zzagi;
    private static final Method zzagj;
    private static final Method zzagk;
    private static final Method zzagl;
    
    static {
        zzagh = zzpZ();
        zzagi = zzqa();
        zzagj = zzqb();
        zzagk = zzqc();
        zzagl = zzqd();
    }
    
    public static int zza(final WorkSource workSource) {
        if (zzma.zzagj != null) {
            try {
                return (int)zzma.zzagj.invoke(workSource, new Object[0]);
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource");
            }
        }
        return 0;
    }
    
    public static String zza(final WorkSource workSource, final int n) {
        if (zzma.zzagl != null) {
            try {
                return (String)zzma.zzagl.invoke(workSource, n);
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource");
            }
        }
        return null;
    }
    
    public static void zza(final WorkSource workSource, final int n, final String s) {
        Label_0049: {
            if (zzma.zzagi == null) {
                break Label_0049;
            }
            String s2;
            if ((s2 = s) == null) {
                s2 = "";
            }
            try {
                zzma.zzagi.invoke(workSource, n, s2);
                return;
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource");
                return;
            }
        }
        if (zzma.zzagh == null) {
            return;
        }
        try {
            zzma.zzagh.invoke(workSource, n);
        }
        catch (Exception ex2) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource");
        }
    }
    
    public static boolean zzaq(final Context context) {
        return context.getPackageManager().checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) == 0;
    }
    
    public static List<String> zzb(final WorkSource workSource) {
        int n = 0;
        int zza;
        if (workSource == null) {
            zza = 0;
        }
        else {
            zza = zza(workSource);
        }
        List<String> empty_LIST;
        if (zza == 0) {
            empty_LIST = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            final ArrayList<String> list = new ArrayList<String>();
            while (true) {
                empty_LIST = list;
                if (n >= zza) {
                    break;
                }
                final String zza2 = zza(workSource, n);
                if (!zzlz.zzcB(zza2)) {
                    list.add(zza2);
                }
                ++n;
            }
        }
        return empty_LIST;
    }
    
    public static WorkSource zze(final int n, final String s) {
        final WorkSource workSource = new WorkSource();
        zza(workSource, n, s);
        return workSource;
    }
    
    public static WorkSource zzj(final Context context, final String s) {
        if (context == null) {
            return null;
        }
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(s, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: " + s);
                return null;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("WorkSourceUtil", "Could not find package: " + s);
            return null;
        }
        return zze(applicationInfo.uid, s);
    }
    
    private static Method zzpZ() {
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzqa() {
        Method method = null;
        if (!zzlv.zzpU()) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("add", Integer.TYPE, String.class);
            return method;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzqb() {
        try {
            return WorkSource.class.getMethod("size", (Class<?>[])new Class[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzqc() {
        try {
            return WorkSource.class.getMethod("get", Integer.TYPE);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzqd() {
        Method method = null;
        if (!zzlv.zzpU()) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("getName", Integer.TYPE);
            return method;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
