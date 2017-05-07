// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.text.util.Linkify$MatchFilter;

final class InternalLinkify$1 implements Linkify$MatchFilter
{
    public final boolean acceptMatch(final CharSequence charSequence, final int n, final int n2) {
        return n == 0 || charSequence.charAt(n - 1) != '@';
    }
}
