// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appindexing;

import android.view.View;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import java.util.List;
import android.net.Uri;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApiClient;

public interface AppIndexApi
{
    PendingResult<Status> view(final GoogleApiClient p0, final Activity p1, final Intent p2, final String p3, final Uri p4, final List<AppIndexingLink> p5);
    
    PendingResult<Status> view(final GoogleApiClient p0, final Activity p1, final Uri p2, final String p3, final Uri p4, final List<AppIndexingLink> p5);
    
    PendingResult<Status> viewEnd(final GoogleApiClient p0, final Activity p1, final Intent p2);
    
    PendingResult<Status> viewEnd(final GoogleApiClient p0, final Activity p1, final Uri p2);
    
    public static final class AppIndexingLink
    {
        public final Uri appIndexingUrl;
        public final int viewId;
        public final Uri webUrl;
        
        public AppIndexingLink(final Uri appIndexingUrl, final Uri webUrl, final View view) {
            this.appIndexingUrl = appIndexingUrl;
            this.webUrl = webUrl;
            this.viewId = view.getId();
        }
    }
}
