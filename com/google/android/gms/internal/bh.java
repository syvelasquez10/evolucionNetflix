// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.content.Intent;
import android.content.Context;

public final class bh
{
    public static boolean a(final Context context, final bj bj, final bq bq) {
        if (bj == null) {
            ct.v("No intent data for launcher overlay.");
            return false;
        }
        final Intent intent = new Intent();
        if (TextUtils.isEmpty((CharSequence)bj.go)) {
            ct.v("Open GMSG did not contain a URL.");
            return false;
        }
        if (!TextUtils.isEmpty((CharSequence)bj.mimeType)) {
            intent.setDataAndType(Uri.parse(bj.go), bj.mimeType);
        }
        else {
            intent.setData(Uri.parse(bj.go));
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty((CharSequence)bj.packageName)) {
            intent.setPackage(bj.packageName);
        }
        if (!TextUtils.isEmpty((CharSequence)bj.gp)) {
            final String[] split = bj.gp.split("/", 2);
            if (split.length < 2) {
                ct.v("Could not parse component name from open GMSG: " + bj.gp);
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        try {
            ct.u("Launching an intent: " + intent);
            context.startActivity(intent);
            bq.z();
            return true;
        }
        catch (ActivityNotFoundException ex) {
            ct.v(ex.getMessage());
            return false;
        }
    }
}
