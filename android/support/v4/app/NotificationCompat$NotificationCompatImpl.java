// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import android.app.Notification;

interface NotificationCompat$NotificationCompatImpl
{
    Notification build(final NotificationCompat$Builder p0);
    
    NotificationCompat$Action getAction(final Notification p0, final int p1);
    
    int getActionCount(final Notification p0);
    
    NotificationCompat$Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> p0);
    
    String getCategory(final Notification p0);
    
    Bundle getExtras(final Notification p0);
    
    String getGroup(final Notification p0);
    
    boolean getLocalOnly(final Notification p0);
    
    ArrayList<Parcelable> getParcelableArrayListForActions(final NotificationCompat$Action[] p0);
    
    String getSortKey(final Notification p0);
    
    boolean isGroupSummary(final Notification p0);
}
