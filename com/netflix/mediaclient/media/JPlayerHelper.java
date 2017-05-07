// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONObject;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import android.content.Context;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.JPlayer.JPlayer;

public class JPlayerHelper implements MediaPlayerHelper
{
    private static final String TAG = "NF_JPlayer";
    private JPlayer jp;
    
    static {
        Log.v("NF_JPlayer", "loadLibrary - libnetflix_jpjni.so");
        try {
            System.load("/data/data/com.netflix.mediaclient/lib/libnetflix_jpjni.so");
        }
        catch (Exception ex) {
            Log.v("NF_JPlayer", "loadLibrary - libnetflix_jpjni.so first attempt fails");
            System.loadLibrary("netflix_jpjni");
        }
    }
    
    @Override
    public void prepare(final IMedia media, final Surface surface, final Context context) {
    }
    
    @Override
    public void prepareJPlayer(final IMedia media, final Surface surface, final JPlayer$JplayerListener jplayerListener, final boolean enablePlatformDrs, final JSONObject jsonObject) {
        Log.d("NF_JPlayer", "Create JPlayer");
        if (this.jp != null) {
            this.jp.release();
        }
        this.jp = new JPlayer(surface, jsonObject);
        media.setVOapi(0L, this.jp.getNativePlayer());
        this.jp.setJplayerListener(jplayerListener);
        this.jp.setEnablePlatformDrs(enablePlatformDrs);
    }
    
    @Override
    public void release() {
        if (this.jp != null) {
            this.jp.release();
        }
        this.jp = null;
    }
}
