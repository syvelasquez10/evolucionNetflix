// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.app.PendingIntent;

public abstract class NotificationCompatBase$Action
{
    protected abstract PendingIntent getActionIntent();
    
    protected abstract Bundle getExtras();
    
    protected abstract int getIcon();
    
    protected abstract RemoteInputCompatBase$RemoteInput[] getRemoteInputs();
    
    protected abstract CharSequence getTitle();
}
