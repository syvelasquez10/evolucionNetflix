// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONObject;
import com.netflix.mediaclient.media.JPlayer.JPlayer;
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
    public void prepare(final IMedia media, final Surface surface, final Context context) {
        Log.d("NF_JPlayer2", "Create JPlayer2");
        if (this.jp != null) {
            this.jp.release();
        }
        this.jp = new JPlayer2(surface);
        media.setVOapi(0L, this.jp.getNativePlayer());
    }
    
    @Override
    public void prepareJPlayer(final IMedia media, final Surface surface, final JPlayer.JplayerListener jplayerListener, final boolean b, final JSONObject jsonObject) {
    }
    
    @Override
    public void release() {
        if (this.jp != null) {
            this.jp.release();
        }
        this.jp = null;
    }
    
    public void setMaxVideoHeight(final VideoResolutionRange maxVideoHeight) {
        if (this.jp != null) {
            this.jp.setMaxVideoHeight(maxVideoHeight);
        }
    }
    
    public void updateSurface(final Surface surface) {
        if (this.jp != null) {
            this.jp.updateSurface(surface);
        }
    }
}
