// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import com.netflix.mediaclient.service.NetflixService;
import android.graphics.Bitmap;
import android.app.Notification;
import android.util.Pair;

public interface IMdxNotificationManager
{
    void cancelNotification();
    
    Pair<Integer, Notification> getNotification(final boolean p0);
    
    boolean isInPostPlay();
    
    void setBoxart(final Bitmap p0);
    
    void setBoxartNotify(final Bitmap p0);
    
    void setPlayerStateNotify(final boolean p0, final boolean p1);
    
    void setTitlesNotify(final boolean p0, final String p1, final String p2);
    
    void setUpNextStateNotify(final boolean p0, final boolean p1, final boolean p2);
    
    void startNotification(final Notification p0, final NetflixService p1, final boolean p2);
    
    void stopNotification(final NetflixService p0);
    
    void stopPostplayNotification(final NetflixService p0);
}
