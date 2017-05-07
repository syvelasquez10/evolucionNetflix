// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.content.Intent;
import android.content.Context;

public final class bz
{
    public static boolean a(final Context context, final cb cb, final ci ci) {
        if (cb == null) {
            dw.z("No intent data for launcher overlay.");
            return false;
        }
        final Intent intent = new Intent();
        if (TextUtils.isEmpty((CharSequence)cb.nO)) {
            dw.z("Open GMSG did not contain a URL.");
            return false;
        }
        if (!TextUtils.isEmpty((CharSequence)cb.mimeType)) {
            intent.setDataAndType(Uri.parse(cb.nO), cb.mimeType);
        }
        else {
            intent.setData(Uri.parse(cb.nO));
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty((CharSequence)cb.packageName)) {
            intent.setPackage(cb.packageName);
        }
        if (!TextUtils.isEmpty((CharSequence)cb.nP)) {
            final String[] split = cb.nP.split("/", 2);
            if (split.length < 2) {
                dw.z("Could not parse component name from open GMSG: " + cb.nP);
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        try {
            dw.y("Launching an intent: " + intent);
            context.startActivity(intent);
            ci.U();
            return true;
        }
        catch (ActivityNotFoundException ex) {
            dw.z(ex.getMessage());
            return false;
        }
    }
}
