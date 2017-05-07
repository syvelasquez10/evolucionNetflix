// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.app.PendingIntent;

public interface MdxNotificationManagerLollipop$MdxNotificationIntentRetriever
{
    PendingIntent getNoActionIntent();
    
    PendingIntent getPauseIntent();
    
    PendingIntent getPlayNextIntent();
    
    PendingIntent getResumeIntent();
    
    PendingIntent getSkipbackIntent(final int p0);
    
    PendingIntent getStopIntent();
}
