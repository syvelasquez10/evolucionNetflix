// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Build$VERSION;
import android.net.Uri;
import android.content.Intent;
import android.os.Environment;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;

@zzgk
public class zzbq
{
    private final Context mContext;
    
    public zzbq(final Context mContext) {
        zzx.zzb(mContext, "Context can not be null");
        this.mContext = mContext;
    }
    
    public static boolean zzcZ() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
    
    public boolean zza(final Intent intent) {
        boolean b = false;
        zzx.zzb(intent, "Intent can not be null");
        if (!this.mContext.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
            b = true;
        }
        return b;
    }
    
    public boolean zzcV() {
        final Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return this.zza(intent);
    }
    
    public boolean zzcW() {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return this.zza(intent);
    }
    
    public boolean zzcX() {
        return zzcZ() && this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
    
    public boolean zzcY() {
        return true;
    }
    
    public boolean zzda() {
        final Intent setType = new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event");
        return Build$VERSION.SDK_INT >= 14 && this.zza(setType);
    }
}
