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
import com.netflix.mediaclient.Log;

public class AndroidMediaPlayerHelper implements MediaPlayerHelper
{
    private static final String TAG = "NF_AndroidMediaPlayer";
    private NetflixAndroidMediaPlayer amp;
    
    static {
        Log.v("NF_AndroidMediaPlayer", "loadLibrary - libnetflix_nfampjni.so");
        try {
            System.load("/data/data/com.netflix.mediaclient/lib/libnetflix_nfampjni.so");
        }
        catch (Exception ex) {
            Log.v("NF_AndroidMediaPlayer", "loadLibrary - libnetflix_nfampjni.so first attempt fails");
            System.loadLibrary("netflix_nfampjni");
        }
    }
    
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
        Log.d("NF_AndroidMediaPlayer", "Create AMP");
        this.amp = new NetflixAndroidMediaPlayer(surface);
        media.setVOapi(0L, this.amp.getNativePlayer());
    }
    
    @Override
    public void prepare(final IMedia media, final SurfaceHolder surfaceHolder, final Context context) {
        Log.d("NF_AndroidMediaPlayer", "Create AMP, with SurfaceHolder");
        this.amp = new NetflixAndroidMediaPlayer(surfaceHolder);
        media.setVOapi(0L, this.amp.getNativePlayer());
    }
    
    @Override
    public void prepareJPlayer(final IMedia media, final Surface surface, final JPlayer.JplayerListener jplayerListener, final boolean b) {
    }
    
    @Override
    public void prepareVO(final Context context, final SurfaceView surfaceView) {
    }
    
    @Override
    public void release() {
        Log.d("NF_AndroidMediaPlayer", "Releasing resources");
        if (this.amp != null) {
            this.amp.release();
        }
        this.amp = null;
    }
}
