// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;

public final class NotificationCompat$Action$WearableExtender implements NotificationCompat$Action$Extender
{
    private static final int DEFAULT_FLAGS = 1;
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    private static final int FLAG_AVAILABLE_OFFLINE = 1;
    private static final String KEY_CANCEL_LABEL = "cancelLabel";
    private static final String KEY_CONFIRM_LABEL = "confirmLabel";
    private static final String KEY_FLAGS = "flags";
    private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
    private CharSequence mCancelLabel;
    private CharSequence mConfirmLabel;
    private int mFlags;
    private CharSequence mInProgressLabel;
    
    public NotificationCompat$Action$WearableExtender() {
        this.mFlags = 1;
    }
    
    public NotificationCompat$Action$WearableExtender(final NotificationCompat$Action notificationCompat$Action) {
        this.mFlags = 1;
        final Bundle bundle = notificationCompat$Action.getExtras().getBundle("android.wearable.EXTENSIONS");
        if (bundle != null) {
            this.mFlags = bundle.getInt("flags", 1);
            this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
            this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
            this.mCancelLabel = bundle.getCharSequence("cancelLabel");
        }
    }
    
    private void setFlag(final int n, final boolean b) {
        if (b) {
            this.mFlags |= n;
            return;
        }
        this.mFlags &= ~n;
    }
    
    public NotificationCompat$Action$WearableExtender clone() {
        final NotificationCompat$Action$WearableExtender notificationCompat$Action$WearableExtender = new NotificationCompat$Action$WearableExtender();
        notificationCompat$Action$WearableExtender.mFlags = this.mFlags;
        notificationCompat$Action$WearableExtender.mInProgressLabel = this.mInProgressLabel;
        notificationCompat$Action$WearableExtender.mConfirmLabel = this.mConfirmLabel;
        notificationCompat$Action$WearableExtender.mCancelLabel = this.mCancelLabel;
        return notificationCompat$Action$WearableExtender;
    }
    
    @Override
    public NotificationCompat$Action$Builder extend(final NotificationCompat$Action$Builder notificationCompat$Action$Builder) {
        final Bundle bundle = new Bundle();
        if (this.mFlags != 1) {
            bundle.putInt("flags", this.mFlags);
        }
        if (this.mInProgressLabel != null) {
            bundle.putCharSequence("inProgressLabel", this.mInProgressLabel);
        }
        if (this.mConfirmLabel != null) {
            bundle.putCharSequence("confirmLabel", this.mConfirmLabel);
        }
        if (this.mCancelLabel != null) {
            bundle.putCharSequence("cancelLabel", this.mCancelLabel);
        }
        notificationCompat$Action$Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
        return notificationCompat$Action$Builder;
    }
    
    public CharSequence getCancelLabel() {
        return this.mCancelLabel;
    }
    
    public CharSequence getConfirmLabel() {
        return this.mConfirmLabel;
    }
    
    public CharSequence getInProgressLabel() {
        return this.mInProgressLabel;
    }
    
    public boolean isAvailableOffline() {
        return (this.mFlags & 0x1) != 0x0;
    }
    
    public NotificationCompat$Action$WearableExtender setAvailableOffline(final boolean b) {
        this.setFlag(1, b);
        return this;
    }
    
    public NotificationCompat$Action$WearableExtender setCancelLabel(final CharSequence mCancelLabel) {
        this.mCancelLabel = mCancelLabel;
        return this;
    }
    
    public NotificationCompat$Action$WearableExtender setConfirmLabel(final CharSequence mConfirmLabel) {
        this.mConfirmLabel = mConfirmLabel;
        return this;
    }
    
    public NotificationCompat$Action$WearableExtender setInProgressLabel(final CharSequence mInProgressLabel) {
        this.mInProgressLabel = mInProgressLabel;
        return this;
    }
}
