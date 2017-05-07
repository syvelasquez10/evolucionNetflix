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

public interface MediaPlayerHelper
{
    int getSurfaceHolderPixelFormat();
    
    int getSurfaceHolderType();
    
    void prepare(final IMedia p0, final Surface p1, final Context p2);
    
    void prepare(final IMedia p0, final SurfaceHolder p1, final Context p2);
    
    void prepareJPlayer(final IMedia p0, final Surface p1, final JPlayer.JplayerListener p2, final boolean p3);
    
    void prepareVO(final Context p0, final SurfaceView p1);
    
    void release();
}
