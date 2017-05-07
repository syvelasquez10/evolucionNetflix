// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.app.PendingIntent;

public class NotificationCompat$Action extends NotificationCompatBase$Action
{
    public static final NotificationCompatBase$Action$Factory FACTORY;
    public PendingIntent actionIntent;
    public int icon;
    private final Bundle mExtras;
    private final RemoteInput[] mRemoteInputs;
    public CharSequence title;
    
    static {
        FACTORY = new NotificationCompat$Action$1();
    }
    
    public NotificationCompat$Action(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
        this(n, charSequence, pendingIntent, new Bundle(), null);
    }
    
    private NotificationCompat$Action(final int icon, final CharSequence charSequence, final PendingIntent actionIntent, Bundle mExtras, final RemoteInput[] mRemoteInputs) {
        this.icon = icon;
        this.title = NotificationCompat$Builder.limitCharSequenceLength(charSequence);
        this.actionIntent = actionIntent;
        if (mExtras == null) {
            mExtras = new Bundle();
        }
        this.mExtras = mExtras;
        this.mRemoteInputs = mRemoteInputs;
    }
    
    @Override
    protected PendingIntent getActionIntent() {
        return this.actionIntent;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    @Override
    protected int getIcon() {
        return this.icon;
    }
    
    public RemoteInput[] getRemoteInputs() {
        return this.mRemoteInputs;
    }
    
    @Override
    protected CharSequence getTitle() {
        return this.title;
    }
}
