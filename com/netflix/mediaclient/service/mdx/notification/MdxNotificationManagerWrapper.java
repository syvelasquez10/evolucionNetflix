// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.app.Service;
import android.graphics.Bitmap;
import android.app.Notification;
import android.util.Pair;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;

public final class MdxNotificationManagerWrapper implements IMdxNotificationManager
{
    private MdxConfiguration mMdxConfiguration;
    private IMdxNotificationManager mRealmanager;
    
    MdxNotificationManagerWrapper(final IMdxNotificationManager mRealmanager, final MdxConfiguration mMdxConfiguration) {
        this.mMdxConfiguration = mMdxConfiguration;
        this.mRealmanager = mRealmanager;
    }
    
    @Override
    public void cancelNotification() {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.cancelNotification();
        }
    }
    
    @Override
    public Pair<Integer, Notification> getNotification(final boolean b) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            return this.mRealmanager.getNotification(b);
        }
        return (Pair<Integer, Notification>)new Pair((Object)null, (Object)null);
    }
    
    @Override
    public boolean isInPostPlay() {
        return this.mRealmanager.isInPostPlay();
    }
    
    @Override
    public void setBoxart(final Bitmap boxart) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.setBoxart(boxart);
        }
    }
    
    @Override
    public void setBoxartNotify(final Bitmap boxartNotify) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.setBoxartNotify(boxartNotify);
        }
    }
    
    @Override
    public void setPlayerStateNotify(final boolean b, final boolean b2) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.setPlayerStateNotify(b, b2);
        }
    }
    
    @Override
    public void setTitlesNotify(final boolean b, final String s, final String s2) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.setTitlesNotify(b, s, s2);
        }
    }
    
    @Override
    public void setUpNextStateNotify(final boolean b, final boolean b2, final boolean b3) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.setUpNextStateNotify(b, b2, b3);
        }
    }
    
    @Override
    public void startNotification(final Notification notification, final Service service, final boolean b) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.startNotification(notification, service, b);
        }
    }
    
    @Override
    public void stopNotification(final Service service) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.stopNotification(service);
        }
    }
    
    @Override
    public void stopPostplayNotification(final Service service) {
        if (this.mMdxConfiguration.isRemoteControlNotificationEnabled()) {
            this.mRealmanager.stopPostplayNotification(service);
        }
    }
}
