// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import com.visualon.netflix.netflixPlayerImpl;
import android.view.SurfaceView;
import com.netflix.mediaclient.media.JPlayer.JPlayer;
import android.view.SurfaceHolder;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import android.os.Build;
import com.visualon.netflix.netflixPlayerInterface;

public class VisualOnPlayerHelper implements MediaPlayerHelper
{
    private static final String TAG = "nf_player";
    private static final String VISIO_TABLET = "vtab1008";
    private int pixelFormat;
    private netflixPlayerInterface vome;
    
    public VisualOnPlayerHelper() {
        this.pixelFormat = 1;
    }
    
    @Override
    public int getSurfaceHolderPixelFormat() {
        final String model = Build.MODEL;
        if (model != null && "vtab1008".compareToIgnoreCase(model) == 0) {
            this.pixelFormat = 2;
        }
        return this.pixelFormat;
    }
    
    @Override
    public int getSurfaceHolderType() {
        return 0;
    }
    
    @Override
    public void prepare(final IMedia media, final Surface surface, final Context context) {
        if (this.vome != null) {
            Log.d("nf_player", "VO context being set");
            media.setVOapi(this.vome.voGetAPI(), this.vome.voGetSourceContext());
        }
    }
    
    @Override
    public void prepare(final IMedia media, final SurfaceHolder surfaceHolder, final Context context) {
    }
    
    @Override
    public void prepareJPlayer(final IMedia media, final Surface surface, final JPlayer.JplayerListener jplayerListener, final boolean b) {
    }
    
    @Override
    public void prepareVO(final Context context, final SurfaceView surfaceView) {
        Log.d("nf_player", "VO start video...");
        if (this.vome != null) {
            this.vome.voDestroy();
            this.vome = null;
        }
        (this.vome = new netflixPlayerImpl()).voCreate(context);
        this.vome.voSetDisplay(surfaceView);
        final String model = Build.MODEL;
        if (model != null && "vtab1008".compareToIgnoreCase(model) == 0) {
            this.pixelFormat = 2;
        }
    }
    
    @Override
    public void release() {
        if (this.vome != null) {
            Log.d("nf_player", "Vome de-initialized");
            this.vome.voDestroy();
            this.vome = null;
        }
    }
}
