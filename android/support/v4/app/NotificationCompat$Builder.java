// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Iterator;
import android.os.Build$VERSION;
import android.widget.RemoteViews;
import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.content.Context;
import android.app.PendingIntent;
import java.util.ArrayList;

public class NotificationCompat$Builder
{
    public ArrayList<NotificationCompat$Action> mActions;
    String mCategory;
    int mColor;
    public CharSequence mContentInfo;
    PendingIntent mContentIntent;
    public CharSequence mContentText;
    public CharSequence mContentTitle;
    public Context mContext;
    Bundle mExtras;
    PendingIntent mFullScreenIntent;
    String mGroupKey;
    boolean mGroupSummary;
    public Bitmap mLargeIcon;
    boolean mLocalOnly;
    public Notification mNotification;
    public int mNumber;
    public ArrayList<String> mPeople;
    int mPriority;
    int mProgress;
    boolean mProgressIndeterminate;
    int mProgressMax;
    Notification mPublicVersion;
    boolean mShowWhen;
    String mSortKey;
    public NotificationCompat$Style mStyle;
    public CharSequence mSubText;
    RemoteViews mTickerView;
    public boolean mUseChronometer;
    int mVisibility;
    
    public NotificationCompat$Builder(final Context mContext) {
        this.mShowWhen = true;
        this.mActions = new ArrayList<NotificationCompat$Action>();
        this.mLocalOnly = false;
        this.mColor = 0;
        this.mVisibility = 0;
        this.mNotification = new Notification();
        this.mContext = mContext;
        this.mNotification.when = System.currentTimeMillis();
        this.mNotification.audioStreamType = -1;
        this.mPriority = 0;
        this.mPeople = new ArrayList<String>();
    }
    
    protected static CharSequence limitCharSequenceLength(final CharSequence charSequence) {
        if (charSequence != null && charSequence.length() > 5120) {
            return charSequence.subSequence(0, 5120);
        }
        return charSequence;
    }
    
    private void setFlag(final int n, final boolean b) {
        if (b) {
            final Notification mNotification = this.mNotification;
            mNotification.flags |= n;
            return;
        }
        final Notification mNotification2 = this.mNotification;
        mNotification2.flags &= ~n;
    }
    
    public NotificationCompat$Builder addAction(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
        this.mActions.add(new NotificationCompat$Action(n, charSequence, pendingIntent));
        return this;
    }
    
    public Notification build() {
        return NotificationCompat.IMPL.build(this, this.getExtender());
    }
    
    protected NotificationCompat$BuilderExtender getExtender() {
        return new NotificationCompat$BuilderExtender();
    }
    
    public NotificationCompat$Builder setAutoCancel(final boolean b) {
        this.setFlag(16, b);
        return this;
    }
    
    public NotificationCompat$Builder setCategory(final String mCategory) {
        this.mCategory = mCategory;
        return this;
    }
    
    public NotificationCompat$Builder setColor(final int mColor) {
        this.mColor = mColor;
        return this;
    }
    
    public NotificationCompat$Builder setContent(final RemoteViews contentView) {
        this.mNotification.contentView = contentView;
        return this;
    }
    
    public NotificationCompat$Builder setContentIntent(final PendingIntent mContentIntent) {
        this.mContentIntent = mContentIntent;
        return this;
    }
    
    public NotificationCompat$Builder setContentText(final CharSequence charSequence) {
        this.mContentText = limitCharSequenceLength(charSequence);
        return this;
    }
    
    public NotificationCompat$Builder setContentTitle(final CharSequence charSequence) {
        this.mContentTitle = limitCharSequenceLength(charSequence);
        return this;
    }
    
    public NotificationCompat$Builder setDeleteIntent(final PendingIntent deleteIntent) {
        this.mNotification.deleteIntent = deleteIntent;
        return this;
    }
    
    public NotificationCompat$Builder setGroup(final String mGroupKey) {
        this.mGroupKey = mGroupKey;
        return this;
    }
    
    public NotificationCompat$Builder setGroupSummary(final boolean mGroupSummary) {
        this.mGroupSummary = mGroupSummary;
        return this;
    }
    
    public NotificationCompat$Builder setLargeIcon(final Bitmap mLargeIcon) {
        this.mLargeIcon = mLargeIcon;
        return this;
    }
    
    public NotificationCompat$Builder setOngoing(final boolean b) {
        this.setFlag(2, b);
        return this;
    }
    
    public NotificationCompat$Builder setOnlyAlertOnce(final boolean b) {
        this.setFlag(8, b);
        return this;
    }
    
    public NotificationCompat$Builder setPriority(final int mPriority) {
        this.mPriority = mPriority;
        return this;
    }
    
    public NotificationCompat$Builder setSmallIcon(final int icon) {
        this.mNotification.icon = icon;
        return this;
    }
    
    public NotificationCompat$Builder setStyle(final NotificationCompat$Style mStyle) {
        if (this.mStyle != mStyle) {
            this.mStyle = mStyle;
            if (this.mStyle != null) {
                this.mStyle.setBuilder(this);
            }
        }
        return this;
    }
    
    public NotificationCompat$Builder setTicker(final CharSequence charSequence) {
        this.mNotification.tickerText = limitCharSequenceLength(charSequence);
        return this;
    }
    
    public NotificationCompat$Builder setVisibility(final int mVisibility) {
        this.mVisibility = mVisibility;
        return this;
    }
    
    public NotificationCompat$Builder setWhen(final long when) {
        this.mNotification.when = when;
        return this;
    }
}
