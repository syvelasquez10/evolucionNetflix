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
import com.netflix.mediaclient.media.JPlayer.JPlayer;

public class JPlayerHelper implements MediaPlayerHelper
{
    private static final String TAG = "NF_JPlayer";
    private static boolean sLoaded;
    private JPlayer jp;
    
    JPlayerHelper(final Context context) {
        loadLibraries(context);
    }
    
    private static boolean loadLibraries(final Context context) {
        synchronized (JPlayerHelper.class) {
            boolean sLoaded;
            if (JPlayerHelper.sLoaded) {
                Log.w("NF_JPlayer", "We already loaded native libraries!");
                sLoaded = true;
            }
            else {
                JPlayerHelper.sLoaded = DeviceUtils.loadNativeLibrary(context, "netflix_jpjni");
                sLoaded = JPlayerHelper.sLoaded;
            }
            return sLoaded;
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
