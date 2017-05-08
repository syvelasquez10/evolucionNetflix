// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.util.Predicate;

final class HttpDataSource$1 implements Predicate<String>
{
    @Override
    public boolean evaluate(String lowerInvariant) {
        lowerInvariant = Util.toLowerInvariant(lowerInvariant);
        return !TextUtils.isEmpty((CharSequence)lowerInvariant) && (!lowerInvariant.contains("text") || lowerInvariant.contains("text/vtt")) && !lowerInvariant.contains("html") && !lowerInvariant.contains("xml");
    }
}
