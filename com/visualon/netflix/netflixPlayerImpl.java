// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.netflix;

import android.app.Activity;
import android.util.DisplayMetrics;
import java.util.List;
import com.visualon.OSMPUtils.voOSOption;
import java.util.ArrayList;
import android.os.Build$VERSION;
import android.view.SurfaceView;
import android.content.Context;
import com.visualon.OSMPEngine.voOnStreamSDK;

public class netflixPlayerImpl implements netflixPlayerInterface, onEventListener
{
    private static final String TAG = "@@@  netflixPlayerImpl.java";
    Context mContext;
    private voOnStreamSDK mMediaPlayer;
    private vome2Source mSource;
    private SurfaceView mSurfaceView;
    private int mVideoHeight;
    private int mVideoWidth;
    
    public netflixPlayerImpl() {
        this.mSurfaceView = null;
        this.mContext = null;
    }
    
    private boolean isGingerbreadOrHigher() {
        return Build$VERSION.SDK_INT >= 9;
    }
    
    private boolean isHoneyComb() {
        return Build$VERSION.SDK_INT > 10 && Build$VERSION.SDK_INT < 14;
    }
    
    private void onVideoSizeChanged(final int mVideoWidth, final int mVideoHeight) {
        Log.v("@@@  netflixPlayerImpl.java", "Old: " + this.mVideoWidth + "x" + this.mVideoHeight);
        Log.v("@@@  netflixPlayerImpl.java", "New: " + mVideoWidth + "x" + mVideoHeight);
        if (this.mVideoWidth != mVideoWidth || this.mVideoHeight != mVideoHeight) {
            this.mVideoWidth = mVideoWidth;
            this.mVideoHeight = mVideoHeight;
            if (this.mVideoWidth != 0 && this.mVideoHeight != 0) {
                this.mSurfaceView.getHolder().setFixedSize(this.mVideoWidth, this.mVideoHeight);
                Log.v("@@@  netflixPlayerImpl.java", "onVideoSizeChanged video Width: " + this.mVideoWidth + "  Height: " + this.mVideoHeight);
            }
        }
    }
    
    @Override
    public int onEvent(final int n, final int n2, final int n3, final Object o) {
        if (n == -2147483636) {
            Log.i("@@@  netflixPlayerImpl.java", "VOME_EVENT_ENGINE_ERROR");
            return 0;
        }
        if (n == 1) {
            Log.i("@@@  netflixPlayerImpl.java", "VOME_EVENT_ENGINE_PLAY_COMPLETE");
            this.mSource.sendEOS();
            return 0;
        }
        if (n == 13) {
            Log.i("@@@  netflixPlayerImpl.java", "VOME_EVENT_ENGINE_SEEK_COMPLETE");
            return 0;
        }
        if (n == 2) {
            Log.i("@@@  netflixPlayerImpl.java", "VOME_EVENT_BUFFERING_UPDATE");
            return 0;
        }
        if (n == 15) {
            Log.i("@@@  netflixPlayerImpl.java", "VOME_EVENT_VIDEOSIZE_CHANGE");
            this.onVideoSizeChanged(n2, n3);
            return 0;
        }
        if (n == 4) {
            Log.i("@@@  netflixPlayerImpl.java", "VOME_EVENT_VIDEO_STARTPLAY");
            this.mSource.sendBufferingDone();
            return 0;
        }
        if (n == 3) {
            Log.i("@@@  netflixPlayerImpl.java", "VOME_EVENT_VDIEO_STOPPLAY");
            this.mSource.sendUnderflow();
            return 0;
        }
        Log.i("@@@  netflixPlayerImpl.java", "nID: " + n);
        return 0;
    }
    
    @Override
    public int voCreate(final Context mContext) throws IllegalStateException {
        Log.i("@@@  netflixPlayerImpl.java", "voCreate enters");
        this.mContext = mContext;
        this.mMediaPlayer = new voOnStreamSDK();
        final ArrayList<voOSOption> list = new ArrayList<voOSOption>();
        try {
            int n;
            if (this.isGingerbreadOrHigher()) {
                if (this.isHoneyComb()) {
                    n = 4;
                }
                else {
                    n = 1;
                }
            }
            else {
                n = 2;
            }
            list.add(voOSOption.make(voOSOption.eVoOption.eoVideoRender, n));
            this.mMediaPlayer.Init(mContext, "/data/data/" + mContext.getPackageName() + "/lib/", list, 0L, 0L, 0L);
            this.mMediaPlayer.setEventListener((voOnStreamSDK.onEventListener)this);
            this.mMediaPlayer.SetParam(52L, 300);
            this.mMediaPlayer.SetParam(56L, 1);
            (this.mSource = new vome2Source(this.mMediaPlayer)).create(this.mSource, "", "");
            this.mVideoWidth = 0;
            this.mVideoHeight = 0;
            Log.v("@@@  netflixPlayerImpl.java", "MediaPlayer is created.");
            return 1;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public int voDestroy() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.Uninit();
            this.mMediaPlayer = null;
        }
        if (this.mSource != null) {
            this.mSource.Uninit();
            this.mSource = null;
        }
        this.mContext = null;
        return 1;
    }
    
    @Override
    public long voGetAPI() {
        return this.mSource.getAPI();
    }
    
    @Override
    public int voGetSourceContext() {
        return this.mSource.mNativeContext;
    }
    
    @Override
    public int voGetVideoHeight() {
        return this.mVideoHeight;
    }
    
    @Override
    public int voGetVideoWidth() {
        return this.mVideoWidth;
    }
    
    @Override
    public void voSetDisplay(final SurfaceView mSurfaceView) {
        this.mSurfaceView = mSurfaceView;
        this.mMediaPlayer.SetView(mSurfaceView);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)this.mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mMediaPlayer.SetDisplaySize(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
    
    @Override
    public int voSetVolume(final float n, final float n2) throws IllegalStateException {
        return this.mMediaPlayer.SetVolume(n, n2);
    }
}
