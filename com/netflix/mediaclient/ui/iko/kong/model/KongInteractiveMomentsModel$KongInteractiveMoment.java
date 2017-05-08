// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

import android.graphics.Bitmap;

public class KongInteractiveMomentsModel$KongInteractiveMoment
{
    Bitmap imageBackgroundBitmap;
    Bitmap imageBitmap;
    String name;
    KongInteractiveMomentsModel$KongNotification notification;
    int sfxSoundPoolId;
    final /* synthetic */ KongInteractiveMomentsModel this$0;
    
    public KongInteractiveMomentsModel$KongInteractiveMoment(final KongInteractiveMomentsModel this$0) {
        this.this$0 = this$0;
    }
    
    public Integer getDurationMS() {
        return this.getMomentEndTimeMS() - this.getMomentStartTimeMS();
    }
    
    public Bitmap getImageBackgroundBitmap() {
        return this.imageBackgroundBitmap;
    }
    
    public Bitmap getImageBitmap() {
        return this.imageBitmap;
    }
    
    public String getMomentAnimationImageUrl() {
        if (this.notification != null && this.notification.states != null && this.notification.states._default != null && this.notification.states._default.animationImage != null) {
            return this.notification.states._default.animationImage.getUrl();
        }
        return null;
    }
    
    public Integer getMomentEndTimeMS() {
        if (this.notification != null) {
            return this.notification.endTimeMS;
        }
        return null;
    }
    
    public String getMomentImageUrl() {
        if (this.notification != null && this.notification.states != null && this.notification.states._default != null && this.notification.states._default.image != null) {
            return this.notification.states._default.image.url;
        }
        return null;
    }
    
    public Integer getMomentStartTimeMS() {
        if (this.notification != null) {
            return this.notification.startTimeMS;
        }
        return null;
    }
    
    public String getName() {
        if (this.notification != null && this.notification.strings != null) {
            return this.notification.strings.unlocked;
        }
        return this.name;
    }
    
    public KongInteractiveMomentsModel$KongNotification getNotification() {
        return this.notification;
    }
    
    public int getSfxSoundPoolId() {
        return this.sfxSoundPoolId;
    }
    
    public String getUnlockSfxSoundUrl() {
        if (this.notification != null && this.notification.audio != null && this.notification.audio.itemUnlockSfx != null && this.notification.audio.itemUnlockSfx.size() > 0) {
            return this.notification.audio.itemUnlockSfx.get(0).url;
        }
        return null;
    }
    
    public float getUnlockSfxSoundVolume() {
        if (this.notification != null && this.notification.audio != null && this.notification.audio.itemUnlockSfx != null && this.notification.audio.itemUnlockSfx.size() > 0) {
            return this.notification.audio.itemUnlockSfx.get(0).getVolume();
        }
        return 0.0f;
    }
    
    public void setImageBackgroundBitmap(final Bitmap imageBackgroundBitmap) {
        this.imageBackgroundBitmap = imageBackgroundBitmap;
    }
    
    public void setImageBitmap(final Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }
    
    public void setSfxSoundPoolId(final int sfxSoundPoolId) {
        this.sfxSoundPoolId = sfxSoundPoolId;
    }
    
    @Override
    public String toString() {
        return "KongInteractiveMoment{name='" + this.name + '\'' + ", notification=" + this.notification + '}';
    }
}
