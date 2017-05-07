// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Build$VERSION;
import android.net.Uri;
import android.content.Intent;
import android.os.Environment;
import com.google.android.gms.common.internal.n;
import android.content.Context;

@ez
public class bl
{
    private final Context mContext;
    
    public bl(final Context mContext) {
        n.b(mContext, "Context can not be null");
        this.mContext = mContext;
    }
    
    public static boolean bn() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
    
    public boolean a(final Intent intent) {
        boolean b = false;
        n.b(intent, "Intent can not be null");
        if (!this.mContext.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
            b = true;
        }
        return b;
    }
    
    public boolean bj() {
        final Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return this.a(intent);
    }
    
    public boolean bk() {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return this.a(intent);
    }
    
    public boolean bl() {
        return bn() && this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
    
    public boolean bm() {
        return false;
    }
    
    public boolean bo() {
        final Intent setType = new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event");
        return Build$VERSION.SDK_INT >= 14 && this.a(setType);
    }
}
