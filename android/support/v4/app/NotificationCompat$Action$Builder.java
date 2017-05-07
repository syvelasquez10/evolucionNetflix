// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.ArrayList;
import android.app.PendingIntent;
import android.os.Bundle;

public final class NotificationCompat$Action$Builder
{
    private final Bundle mExtras;
    private final int mIcon;
    private final PendingIntent mIntent;
    private ArrayList<RemoteInput> mRemoteInputs;
    private final CharSequence mTitle;
    
    public NotificationCompat$Action$Builder(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
        this(n, charSequence, pendingIntent, new Bundle());
    }
    
    private NotificationCompat$Action$Builder(final int mIcon, final CharSequence charSequence, final PendingIntent mIntent, final Bundle mExtras) {
        this.mIcon = mIcon;
        this.mTitle = NotificationCompat$Builder.limitCharSequenceLength(charSequence);
        this.mIntent = mIntent;
        this.mExtras = mExtras;
    }
    
    public NotificationCompat$Action$Builder(final NotificationCompat$Action notificationCompat$Action) {
        this(notificationCompat$Action.icon, notificationCompat$Action.title, notificationCompat$Action.actionIntent, new Bundle(notificationCompat$Action.mExtras));
    }
    
    public NotificationCompat$Action$Builder addExtras(final Bundle bundle) {
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        return this;
    }
    
    public NotificationCompat$Action$Builder addRemoteInput(final RemoteInput remoteInput) {
        if (this.mRemoteInputs == null) {
            this.mRemoteInputs = new ArrayList<RemoteInput>();
        }
        this.mRemoteInputs.add(remoteInput);
        return this;
    }
    
    public NotificationCompat$Action build() {
        RemoteInput[] array;
        if (this.mRemoteInputs != null) {
            array = this.mRemoteInputs.toArray(new RemoteInput[this.mRemoteInputs.size()]);
        }
        else {
            array = null;
        }
        return new NotificationCompat$Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, array, null);
    }
    
    public NotificationCompat$Action$Builder extend(final NotificationCompat$Action$Extender notificationCompat$Action$Extender) {
        notificationCompat$Action$Extender.extend(this);
        return this;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
}
