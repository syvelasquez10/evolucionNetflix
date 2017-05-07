// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.TextureView;

public abstract class zzi extends TextureView
{
    public abstract int getCurrentPosition();
    
    public abstract int getDuration();
    
    public abstract int getVideoHeight();
    
    public abstract int getVideoWidth();
    
    public abstract void pause();
    
    public abstract void play();
    
    public abstract void seekTo(final int p0);
    
    public abstract void setMimeType(final String p0);
    
    public abstract void setVideoPath(final String p0);
    
    public abstract void stop();
    
    public abstract void zza(final float p0);
    
    public abstract void zza(final zzh p0);
    
    public abstract String zzer();
    
    public abstract void zzex();
    
    public abstract void zzey();
}
