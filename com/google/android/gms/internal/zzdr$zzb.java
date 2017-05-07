// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.app.ActivityManager;
import java.util.Map;
import java.util.List;
import android.content.pm.PackageManager;
import java.util.Collection;
import java.util.ArrayList;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.Intent;

public class zzdr$zzb
{
    public Intent zza(Intent intent, final ResolveInfo resolveInfo) {
        intent = new Intent(intent);
        intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent;
    }
    
    public ResolveInfo zza(final Context context, final Intent intent) {
        return this.zza(context, intent, new ArrayList<ResolveInfo>());
    }
    
    public ResolveInfo zza(final Context context, final Intent intent, final ArrayList<ResolveInfo> list) {
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        final List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
    Label_0089:
        while (true) {
            if (queryIntentActivities != null && resolveActivity != null) {
                for (int i = 0; i < queryIntentActivities.size(); ++i) {
                    final ResolveInfo resolveInfo = queryIntentActivities.get(i);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                        break Label_0089;
                    }
                }
            }
            Label_0107: {
                break Label_0107;
                list.addAll(queryIntentActivities);
                return resolveActivity;
            }
            resolveActivity = null;
            continue Label_0089;
        }
    }
    
    public Intent zzb(final Context context, final Map<String, String> map) {
        final Intent intent = null;
        final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
        final String s = map.get("u");
        Intent zza;
        if (TextUtils.isEmpty((CharSequence)s)) {
            zza = intent;
        }
        else {
            final Uri parse = Uri.parse(s);
            final boolean boolean1 = Boolean.parseBoolean(map.get("use_first_package"));
            final boolean boolean2 = Boolean.parseBoolean(map.get("use_running_process"));
            Uri uri;
            if ("http".equalsIgnoreCase(parse.getScheme())) {
                uri = parse.buildUpon().scheme("https").build();
            }
            else if ("https".equalsIgnoreCase(parse.getScheme())) {
                uri = parse.buildUpon().scheme("http").build();
            }
            else {
                uri = null;
            }
            final ArrayList list = new ArrayList<ResolveInfo>();
            final Intent zzd = this.zzd(parse);
            final Intent zzd2 = this.zzd(uri);
            final ResolveInfo zza2 = this.zza(context, zzd, list);
            if (zza2 != null) {
                return this.zza(zzd, zza2);
            }
            if (zzd2 != null) {
                final ResolveInfo zza3 = this.zza(context, zzd2);
                if (zza3 != null && this.zza(context, zza = this.zza(zzd, zza3)) != null) {
                    return zza;
                }
            }
            if (list.size() == 0) {
                return zzd;
            }
            if (boolean2 && activityManager != null) {
                final List runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    for (final ResolveInfo resolveInfo : list) {
                        final Iterator<ActivityManager$RunningAppProcessInfo> iterator2 = runningAppProcesses.iterator();
                        while (iterator2.hasNext()) {
                            if (iterator2.next().processName.equals(resolveInfo.activityInfo.packageName)) {
                                return this.zza(zzd, resolveInfo);
                            }
                        }
                    }
                }
            }
            if (boolean1) {
                return this.zza(zzd, list.get(0));
            }
            return zzd;
        }
        return zza;
    }
    
    public Intent zzd(final Uri data) {
        if (data == null) {
            return null;
        }
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setData(data);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }
}
