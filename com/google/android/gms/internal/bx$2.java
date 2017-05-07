// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import java.util.HashMap;
import android.text.TextUtils;
import java.util.Map;

final class bx$2 implements by
{
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        final String s = map.get("urls");
        if (TextUtils.isEmpty((CharSequence)s)) {
            gs.W("URLs missing in canOpenURLs GMSG.");
            return;
        }
        final String[] split = s.split(",");
        final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        final PackageManager packageManager = gv.getContext().getPackageManager();
        for (int length = split.length, i = 0; i < length; ++i) {
            final String s2 = split[i];
            final String[] split2 = s2.split(";", 2);
            final String trim = split2[0].trim();
            String trim2;
            if (split2.length > 1) {
                trim2 = split2[1].trim();
            }
            else {
                trim2 = "android.intent.action.VIEW";
            }
            hashMap.put(s2, packageManager.resolveActivity(new Intent(trim2, Uri.parse(trim)), 65536) != null);
        }
        gv.a("openableURLs", hashMap);
    }
}
