// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Iterator;
import android.net.Uri;
import android.widget.RemoteViews;
import android.app.Notification;
import android.content.Context;
import java.util.ArrayList;
import android.graphics.Bitmap;
import android.app.PendingIntent;
import android.os.Build$VERSION;

public class NotificationCompat
{
    public static final int FLAG_HIGH_PRIORITY = 128;
    private static final NotificationCompatImpl IMPL;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplJellybean();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplIceCreamSandwich();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplHoneycomb();
            return;
        }
        IMPL = (NotificationCompatImpl)new NotificationCompatImplBase();
    }
    
    public static class Action
    {
        public PendingIntent actionIntent;
        public int icon;
        public CharSequence title;
        
        public Action(final int icon, final CharSequence title, final PendingIntent actionIntent) {
            this.icon = icon;
            this.title = title;
            this.actionIntent = actionIntent;
        }
    }
    
    public static class BigPictureStyle extends Style
    {
        Bitmap mBigLargeIcon;
        boolean mBigLargeIconSet;
        Bitmap mPicture;
        
        public BigPictureStyle() {
        }
        
        public BigPictureStyle(final Builder builder) {
            ((Style)this).setBuilder(builder);
        }
        
        public BigPictureStyle bigLargeIcon(final Bitmap mBigLargeIcon) {
            this.mBigLargeIcon = mBigLargeIcon;
            this.mBigLargeIconSet = true;
            return this;
        }
        
        public BigPictureStyle bigPicture(final Bitmap mPicture) {
            this.mPicture = mPicture;
            return this;
        }
        
        public BigPictureStyle setBigContentTitle(final CharSequence mBigContentTitle) {
            this.mBigContentTitle = mBigContentTitle;
            return this;
        }
        
        public BigPictureStyle setSummaryText(final CharSequence mSummaryText) {
            this.mSummaryText = mSummaryText;
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    public static class BigTextStyle extends Style
    {
        CharSequence mBigText;
        
        public BigTextStyle() {
        }
        
        public BigTextStyle(final Builder builder) {
            ((Style)this).setBuilder(builder);
        }
        
        public BigTextStyle bigText(final CharSequence mBigText) {
            this.mBigText = mBigText;
            return this;
        }
        
        public BigTextStyle setBigContentTitle(final CharSequence mBigContentTitle) {
            this.mBigContentTitle = mBigContentTitle;
            return this;
        }
        
        public BigTextStyle setSummaryText(final CharSequence mSummaryText) {
            this.mSummaryText = mSummaryText;
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    public static class Builder
    {
        ArrayList<Action> mActions;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        Context mContext;
        PendingIntent mFullScreenIntent;
        Bitmap mLargeIcon;
        Notification mNotification;
        int mNumber;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        boolean mUseChronometer;
        
        public Builder(final Context mContext) {
            this.mActions = new ArrayList<Action>();
            this.mNotification = new Notification();
            this.mContext = mContext;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
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
        
        public Builder addAction(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
            this.mActions.add(new Action(n, charSequence, pendingIntent));
            return this;
        }
        
        public Notification build() {
            return NotificationCompat.IMPL.build(this);
        }
        
        @Deprecated
        public Notification getNotification() {
            return NotificationCompat.IMPL.build(this);
        }
        
        public Builder setAutoCancel(final boolean b) {
            this.setFlag(16, b);
            return this;
        }
        
        public Builder setContent(final RemoteViews contentView) {
            this.mNotification.contentView = contentView;
            return this;
        }
        
        public Builder setContentInfo(final CharSequence mContentInfo) {
            this.mContentInfo = mContentInfo;
            return this;
        }
        
        public Builder setContentIntent(final PendingIntent mContentIntent) {
            this.mContentIntent = mContentIntent;
            return this;
        }
        
        public Builder setContentText(final CharSequence mContentText) {
            this.mContentText = mContentText;
            return this;
        }
        
        public Builder setContentTitle(final CharSequence mContentTitle) {
            this.mContentTitle = mContentTitle;
            return this;
        }
        
        public Builder setDefaults(final int defaults) {
            this.mNotification.defaults = defaults;
            if ((defaults & 0x4) != 0x0) {
                final Notification mNotification = this.mNotification;
                mNotification.flags |= 0x1;
            }
            return this;
        }
        
        public Builder setDeleteIntent(final PendingIntent deleteIntent) {
            this.mNotification.deleteIntent = deleteIntent;
            return this;
        }
        
        public Builder setFullScreenIntent(final PendingIntent mFullScreenIntent, final boolean b) {
            this.mFullScreenIntent = mFullScreenIntent;
            this.setFlag(128, b);
            return this;
        }
        
        public Builder setLargeIcon(final Bitmap mLargeIcon) {
            this.mLargeIcon = mLargeIcon;
            return this;
        }
        
        public Builder setLights(int ledARGB, int flags, final int ledOffMS) {
            final int n = 1;
            this.mNotification.ledARGB = ledARGB;
            this.mNotification.ledOnMS = flags;
            this.mNotification.ledOffMS = ledOffMS;
            if (this.mNotification.ledOnMS != 0 && this.mNotification.ledOffMS != 0) {
                ledARGB = 1;
            }
            else {
                ledARGB = 0;
            }
            final Notification mNotification = this.mNotification;
            flags = this.mNotification.flags;
            if (ledARGB != 0) {
                ledARGB = n;
            }
            else {
                ledARGB = 0;
            }
            mNotification.flags = (ledARGB | (flags & 0xFFFFFFFE));
            return this;
        }
        
        public Builder setNumber(final int mNumber) {
            this.mNumber = mNumber;
            return this;
        }
        
        public Builder setOngoing(final boolean b) {
            this.setFlag(2, b);
            return this;
        }
        
        public Builder setOnlyAlertOnce(final boolean b) {
            this.setFlag(8, b);
            return this;
        }
        
        public Builder setPriority(final int mPriority) {
            this.mPriority = mPriority;
            return this;
        }
        
        public Builder setProgress(final int mProgressMax, final int mProgress, final boolean mProgressIndeterminate) {
            this.mProgressMax = mProgressMax;
            this.mProgress = mProgress;
            this.mProgressIndeterminate = mProgressIndeterminate;
            return this;
        }
        
        public Builder setSmallIcon(final int icon) {
            this.mNotification.icon = icon;
            return this;
        }
        
        public Builder setSmallIcon(final int icon, final int iconLevel) {
            this.mNotification.icon = icon;
            this.mNotification.iconLevel = iconLevel;
            return this;
        }
        
        public Builder setSound(final Uri sound) {
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = -1;
            return this;
        }
        
        public Builder setSound(final Uri sound, final int audioStreamType) {
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = audioStreamType;
            return this;
        }
        
        public Builder setStyle(final Style mStyle) {
            if (this.mStyle != mStyle) {
                this.mStyle = mStyle;
                if (this.mStyle != null) {
                    this.mStyle.setBuilder(this);
                }
            }
            return this;
        }
        
        public Builder setSubText(final CharSequence mSubText) {
            this.mSubText = mSubText;
            return this;
        }
        
        public Builder setTicker(final CharSequence tickerText) {
            this.mNotification.tickerText = tickerText;
            return this;
        }
        
        public Builder setTicker(final CharSequence tickerText, final RemoteViews mTickerView) {
            this.mNotification.tickerText = tickerText;
            this.mTickerView = mTickerView;
            return this;
        }
        
        public Builder setUsesChronometer(final boolean mUseChronometer) {
            this.mUseChronometer = mUseChronometer;
            return this;
        }
        
        public Builder setVibrate(final long[] vibrate) {
            this.mNotification.vibrate = vibrate;
            return this;
        }
        
        public Builder setWhen(final long when) {
            this.mNotification.when = when;
            return this;
        }
    }
    
    public static class InboxStyle extends Style
    {
        ArrayList<CharSequence> mTexts;
        
        public InboxStyle() {
            this.mTexts = new ArrayList<CharSequence>();
        }
        
        public InboxStyle(final Builder builder) {
            this.mTexts = new ArrayList<CharSequence>();
            ((Style)this).setBuilder(builder);
        }
        
        public InboxStyle addLine(final CharSequence charSequence) {
            this.mTexts.add(charSequence);
            return this;
        }
        
        public InboxStyle setBigContentTitle(final CharSequence mBigContentTitle) {
            this.mBigContentTitle = mBigContentTitle;
            return this;
        }
        
        public InboxStyle setSummaryText(final CharSequence mSummaryText) {
            this.mSummaryText = mSummaryText;
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    interface NotificationCompatImpl
    {
        Notification build(final Builder p0);
    }
    
    static class NotificationCompatImplBase implements NotificationCompatImpl
    {
        @Override
        public Notification build(final Builder builder) {
            final Notification mNotification = builder.mNotification;
            mNotification.setLatestEventInfo(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent);
            if (builder.mPriority > 0) {
                mNotification.flags |= 0x80;
            }
            return mNotification;
        }
    }
    
    static class NotificationCompatImplHoneycomb implements NotificationCompatImpl
    {
        @Override
        public Notification build(final Builder builder) {
            return NotificationCompatHoneycomb.add(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon);
        }
    }
    
    static class NotificationCompatImplIceCreamSandwich implements NotificationCompatImpl
    {
        @Override
        public Notification build(final Builder builder) {
            return NotificationCompatIceCreamSandwich.add(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate);
        }
    }
    
    static class NotificationCompatImplJellybean implements NotificationCompatImpl
    {
        @Override
        public Notification build(final Builder builder) {
            final NotificationCompatJellybean notificationCompatJellybean = new NotificationCompatJellybean(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mUseChronometer, builder.mPriority, builder.mSubText);
            for (final Action action : builder.mActions) {
                notificationCompatJellybean.addAction(action.icon, action.title, action.actionIntent);
            }
            if (builder.mStyle != null) {
                if (builder.mStyle instanceof BigTextStyle) {
                    final BigTextStyle bigTextStyle = (BigTextStyle)builder.mStyle;
                    notificationCompatJellybean.addBigTextStyle(bigTextStyle.mBigContentTitle, bigTextStyle.mSummaryTextSet, bigTextStyle.mSummaryText, bigTextStyle.mBigText);
                }
                else if (builder.mStyle instanceof InboxStyle) {
                    final InboxStyle inboxStyle = (InboxStyle)builder.mStyle;
                    notificationCompatJellybean.addInboxStyle(inboxStyle.mBigContentTitle, inboxStyle.mSummaryTextSet, inboxStyle.mSummaryText, inboxStyle.mTexts);
                }
                else if (builder.mStyle instanceof BigPictureStyle) {
                    final BigPictureStyle bigPictureStyle = (BigPictureStyle)builder.mStyle;
                    notificationCompatJellybean.addBigPictureStyle(bigPictureStyle.mBigContentTitle, bigPictureStyle.mSummaryTextSet, bigPictureStyle.mSummaryText, bigPictureStyle.mPicture, bigPictureStyle.mBigLargeIcon, bigPictureStyle.mBigLargeIconSet);
                }
            }
            return notificationCompatJellybean.build();
        }
    }
    
    public abstract static class Style
    {
        CharSequence mBigContentTitle;
        Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;
        
        public Style() {
            this.mSummaryTextSet = false;
        }
        
        public Notification build() {
            Notification build = null;
            if (this.mBuilder != null) {
                build = this.mBuilder.build();
            }
            return build;
        }
        
        public void setBuilder(final Builder mBuilder) {
            if (this.mBuilder != mBuilder) {
                this.mBuilder = mBuilder;
                if (this.mBuilder != null) {
                    this.mBuilder.setStyle(this);
                }
            }
        }
    }
}
