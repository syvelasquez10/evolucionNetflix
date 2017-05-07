// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appindexing;

import android.view.View;
import android.net.Uri;

public final class AppIndexApi$AppIndexingLink
{
    public final Uri appIndexingUrl;
    public final int viewId;
    public final Uri webUrl;
    
    public AppIndexApi$AppIndexingLink(final Uri appIndexingUrl, final Uri webUrl, final View view) {
        this.appIndexingUrl = appIndexingUrl;
        this.webUrl = webUrl;
        this.viewId = view.getId();
    }
}
