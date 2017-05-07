// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import android.view.SurfaceView;
import org.json.JSONObject;
import com.netflix.mediaclient.media.JPlayer.JPlayer;
import android.view.SurfaceHolder;
import com.netflix.mediaclient.service.configuration.BitrateRangeFactory;
import android.content.Context;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.JPlayer.JPlayer2;

public class JPlayer2Helper implements MediaPlayerHelper
{
    private static final String TAG = "NF_JPlayer2";
    private JPlayer2 jp;
    
    static {
        Log.v("NF_JPlayer2", "loadLibrary - libnetflix_jp2jni.so");
        try {
            System.load("/data/data/com.netflix.mediaclient/lib/libnetflix_jp2jni.so");
        }
        catch (Exception ex) {
            Log.v("NF_JPlayer2", "loadLibrary - libnetflix_jp2jni.so first attempt fails");
            System.loadLibrary("netflix_jp2jni");
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
        Log.d("NF_JPlayer2", "Create JPlayer2");
        if (this.jp != null) {
            this.jp.release();
        }
        (this.jp = new JPlayer2(surface)).setMaxVideoBitrate(BitrateRangeFactory.getBitrateCap(context));
        media.setVOapi(0L, this.jp.getNativePlayer());
    }
    
    @Override
    public void prepare(final IMedia media, final SurfaceHolder surfaceHolder, final Context context) {
    }
    
    @Override
    public void prepareJPlayer(final IMedia media, final Surface surface, final JPlayer.JplayerListener jplayerListener, final boolean b, final JSONObject jsonObject) {
    }
    
    @Override
    public void prepareVO(final Context context, final SurfaceView surfaceView) {
    }
    
    @Override
    public void release() {
        if (this.jp != null) {
            this.jp.release();
        }
        this.jp = null;
    }
}
