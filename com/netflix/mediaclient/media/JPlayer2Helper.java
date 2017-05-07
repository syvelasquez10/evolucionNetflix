// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONObject;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.media.JPlayer.JPlayer2;

public class JPlayer2Helper implements MediaPlayerHelper
{
    private static final String TAG = "NF_JPlayer2";
    private static boolean sLoaded;
    private JPlayer2 jp;
    
    JPlayer2Helper(final Context context) {
        loadLibraries(context);
    }
    
    private static boolean loadLibraries(final Context context) {
        synchronized (JPlayer2Helper.class) {
            boolean sLoaded;
            if (JPlayer2Helper.sLoaded) {
                Log.w("NF_JPlayer2", "We already loaded native libraries!");
                sLoaded = true;
            }
            else {
                JPlayer2Helper.sLoaded = DeviceUtils.loadNativeLibrary(context, "netflix_jp2jni");
                sLoaded = JPlayer2Helper.sLoaded;
            }
            return sLoaded;
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
    public void prepareJPlayer(final IMedia media, final Surface surface, final JPlayer$JplayerListener player$JplayerListener, final boolean b, final JSONObject jsonObject) {
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
