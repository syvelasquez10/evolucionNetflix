// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import android.view.SurfaceView;
import com.netflix.mediaclient.media.JPlayer.JPlayer;
import android.view.SurfaceHolder;
import android.content.Context;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.ui.IMedia;

public final class DefaultMediaPlayerHelper implements MediaPlayerHelper
{
    @Override
    public int getSurfaceHolderPixelFormat() {
        return -2;
    }
    
    @Override
    public int getSurfaceHolderType() {
        return 3;
    }
    
    @Override
    public void prepare(final IMedia media, final Surface surface, final Context context) {
    }
    
    @Override
    public void prepare(final IMedia media, final SurfaceHolder surfaceHolder, final Context context) {
    }
    
    @Override
    public void prepareJPlayer(final IMedia media, final Surface surface, final JPlayer.JplayerListener jplayerListener, final boolean b) {
    }
    
    @Override
    public void prepareVO(final Context context, final SurfaceView surfaceView) {
    }
    
    @Override
    public void release() {
    }
}
