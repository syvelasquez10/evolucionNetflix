// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.content.Intent;
import android.content.Context;

@ez
public final class dh
{
    public static boolean a(final Context context, final dj dj, final dq dq) {
        if (dj == null) {
            gs.W("No intent data for launcher overlay.");
            return false;
        }
        final Intent intent = new Intent();
        if (TextUtils.isEmpty((CharSequence)dj.rq)) {
            gs.W("Open GMSG did not contain a URL.");
            return false;
        }
        if (!TextUtils.isEmpty((CharSequence)dj.mimeType)) {
            intent.setDataAndType(Uri.parse(dj.rq), dj.mimeType);
        }
        else {
            intent.setData(Uri.parse(dj.rq));
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty((CharSequence)dj.packageName)) {
            intent.setPackage(dj.packageName);
        }
        if (!TextUtils.isEmpty((CharSequence)dj.rr)) {
            final String[] split = dj.rr.split("/", 2);
            if (split.length < 2) {
                gs.W("Could not parse component name from open GMSG: " + dj.rr);
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        try {
            gs.V("Launching an intent: " + intent);
            context.startActivity(intent);
            dq.ab();
            return true;
        }
        catch (ActivityNotFoundException ex) {
            gs.W(ex.getMessage());
            return false;
        }
    }
}
