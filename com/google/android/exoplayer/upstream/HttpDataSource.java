// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import com.google.android.exoplayer.util.Predicate;

public interface HttpDataSource extends UriDataSource
{
    public static final Predicate<String> REJECT_PAYWALL_TYPES = new HttpDataSource$1();
}
