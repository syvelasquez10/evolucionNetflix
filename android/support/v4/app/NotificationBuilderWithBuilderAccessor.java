// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification$Builder;
import android.app.Notification;
import android.annotation.TargetApi;

@TargetApi(11)
public interface NotificationBuilderWithBuilderAccessor
{
    Notification build();
    
    Notification$Builder getBuilder();
}
