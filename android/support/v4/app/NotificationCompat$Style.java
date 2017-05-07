// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

public abstract class NotificationCompat$Style
{
    CharSequence mBigContentTitle;
    NotificationCompat$Builder mBuilder;
    CharSequence mSummaryText;
    boolean mSummaryTextSet;
    
    public NotificationCompat$Style() {
        this.mSummaryTextSet = false;
    }
    
    public void setBuilder(final NotificationCompat$Builder mBuilder) {
        if (this.mBuilder != mBuilder) {
            this.mBuilder = mBuilder;
            if (this.mBuilder != null) {
                this.mBuilder.setStyle(this);
            }
        }
    }
}
