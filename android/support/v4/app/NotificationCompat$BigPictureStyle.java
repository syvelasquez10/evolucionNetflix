// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.graphics.Bitmap;

public class NotificationCompat$BigPictureStyle extends NotificationCompat$Style
{
    Bitmap mBigLargeIcon;
    boolean mBigLargeIconSet;
    Bitmap mPicture;
    
    public NotificationCompat$BigPictureStyle bigPicture(final Bitmap mPicture) {
        this.mPicture = mPicture;
        return this;
    }
    
    public NotificationCompat$BigPictureStyle setSummaryText(final CharSequence charSequence) {
        this.mSummaryText = NotificationCompat$Builder.limitCharSequenceLength(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }
}
