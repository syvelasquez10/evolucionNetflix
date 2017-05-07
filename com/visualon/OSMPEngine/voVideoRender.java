// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPEngine;

import android.os.Build;
import android.graphics.Canvas;
import android.view.Surface$OutOfResourcesException;
import android.graphics.Rect;
import android.graphics.Bitmap$Config;
import com.visualon.OSMPUtils.voLog;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.view.SurfaceHolder;
import android.view.Surface;
import android.graphics.Paint;
import java.nio.ByteBuffer;
import android.graphics.Bitmap;

public class voVideoRender
{
    private final String TAG;
    private Bitmap mBitmap;
    private ByteBuffer mByteBuffer;
    private int mHeightBitmap;
    private Paint mPaint;
    private int mPixelBytes;
    private voOnStreamSDK mPlayer;
    private boolean mSeeking;
    private int mStatus;
    private Surface mSurface;
    private SurfaceHolder mSurfaceHolder;
    Thread mThreadPlayback;
    private boolean mUseOptimisedBitmapRendering;
    private int mWidthBitmap;
    private boolean mbNativeRender;
    private long mlJavaRenderFrames;
    runPlayback mrunPlayback;
    
    public voVideoRender(final voOnStreamSDK mPlayer) {
        this.TAG = this.getClass().getSimpleName() + ".java";
        this.mPlayer = mPlayer;
        this.mSurface = null;
        this.mSurfaceHolder = null;
        this.mBitmap = null;
        this.mByteBuffer = null;
        this.mWidthBitmap = 0;
        this.mHeightBitmap = 0;
        this.mPixelBytes = 2;
        this.mStatus = 0;
        (this.mPaint = new Paint()).setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.SRC));
        this.mPaint.setDither(false);
        this.mPaint.setFilterBitmap(false);
        this.mUseOptimisedBitmapRendering = false;
        this.mlJavaRenderFrames = 0L;
        this.mbNativeRender = true;
        voLog.v(this.TAG, "Construct!", new Object[0]);
    }
    
    public boolean init(final int mWidthBitmap, final int mHeightBitmap) {
        boolean b = true;
        voLog.v(this.TAG, "Init W X H " + mWidthBitmap + mHeightBitmap, new Object[0]);
        if (mWidthBitmap == 0 || mHeightBitmap == 0) {
            b = false;
        }
        else if (this.mWidthBitmap != mWidthBitmap || this.mHeightBitmap != mHeightBitmap) {
            if (this.mBitmap != null) {
                this.mBitmap.recycle();
                this.mBitmap = null;
            }
            while (true) {
            Label_0274:
                while (true) {
                    try {
                        if (!this.mbNativeRender && !this.mUseOptimisedBitmapRendering) {
                            this.mByteBuffer = ByteBuffer.allocate(mWidthBitmap * mHeightBitmap * this.mPixelBytes);
                            if (this.mByteBuffer == null) {
                                voLog.e(this.TAG, "Failed to allocate buffer", new Object[0]);
                                return false;
                            }
                            voLog.i(this.TAG, "mByteBuffer allocate is %d.", mWidthBitmap * mHeightBitmap * this.mPixelBytes);
                        }
                        else {
                            this.mByteBuffer = ByteBuffer.allocate(4);
                        }
                        if (this.mbNativeRender) {
                            break;
                        }
                        if (this.mPixelBytes == 2) {
                            this.mBitmap = Bitmap.createBitmap(mWidthBitmap, mHeightBitmap, Bitmap$Config.RGB_565);
                            if (this.mBitmap == null) {
                                voLog.e(this.TAG, "Failed to Create Bitmap buffer", new Object[0]);
                                return false;
                            }
                            break Label_0274;
                        }
                    }
                    catch (Exception ex) {
                        voLog.e(this.TAG, "Failed to Create Bitmap buffer on catch! " + ex.getMessage(), new Object[0]);
                        return false;
                    }
                    this.mBitmap = Bitmap.createBitmap(mWidthBitmap, mHeightBitmap, Bitmap$Config.ARGB_8888);
                    continue;
                }
                voLog.i(this.TAG, "mBitmap createBitmap is %d X %d. mPixelBytes is %d ", mWidthBitmap, mHeightBitmap, this.mPixelBytes);
                break;
            }
            this.mWidthBitmap = mWidthBitmap;
            this.mHeightBitmap = mHeightBitmap;
            if (this.mBitmap != null && this.mUseOptimisedBitmapRendering) {
                voLog.i(this.TAG, "new bitmap created! " + this.mPlayer.GetVideoWidth() + "x" + this.mPlayer.GetVideoHeight(), new Object[0]);
                this.mPlayer.SetParam(62L, this.mBitmap);
            }
            this.mPlayer.VideoSizeChanged();
            return true;
        }
        return b;
    }
    
    public void pause() {
        voLog.v(this.TAG, "pause  mStatus is " + this.mStatus, new Object[0]);
        this.mStatus = 2;
    }
    
    public void playback() {
        this.mThreadPlayback.setPriority(5);
        voLog.v(this.TAG, "playbackVideo started! Is native " + this.mbNativeRender, new Object[0]);
        while (this.mStatus == 1 || this.mStatus == 2) {
            if (this.mStatus == 1) {
                long n;
                if (this.mByteBuffer == null) {
                    n = this.mPlayer.GetVideoData(null);
                }
                else {
                    n = this.mPlayer.GetVideoData(this.mByteBuffer.array());
                }
                if (this.mWidthBitmap != this.mPlayer.GetVideoWidth() || this.mHeightBitmap != this.mPlayer.GetVideoHeight()) {
                    this.init(this.mPlayer.GetVideoWidth(), this.mPlayer.GetVideoHeight());
                    continue;
                }
                if (n == 0L && !this.mbNativeRender) {
                    this.render();
                    if (this.mSeeking) {
                        this.mSeeking = false;
                        this.mStatus = 2;
                    }
                }
            }
            try {
                Thread.sleep(2L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.mThreadPlayback = null;
        voLog.v(this.TAG, "playbackVideo stopped!", new Object[0]);
    }
    
    public long render() {
        if (this.mSurface == null || this.mBitmap == null) {
            return -1L;
        }
        try {
            final Canvas lockCanvas = this.mSurface.lockCanvas((Rect)null);
            if (lockCanvas == null) {
                this.mSurface.unlockCanvasAndPost(lockCanvas);
                return -1L;
            }
            goto Label_0050;
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        catch (Surface$OutOfResourcesException ex2) {
            ex2.printStackTrace();
            goto Label_0048;
        }
    }
    
    public void run() {
        voLog.v(this.TAG, "run  mStatus is " + this.mStatus, new Object[0]);
        if (this.mSeeking) {
            this.mSeeking = false;
        }
        if (this.mStatus != 1) {
            this.mStatus = 1;
            if (this.mrunPlayback == null) {
                this.mrunPlayback = new runPlayback(this);
            }
            if (this.mThreadPlayback == null) {
                (this.mThreadPlayback = new Thread(this.mrunPlayback, "vomeVideo Playback")).setPriority(5);
                this.mThreadPlayback.start();
            }
        }
    }
    
    public void setColorType(final int n) {
        if (n == 26) {
            this.mPixelBytes = 4;
            return;
        }
        if (n == 23) {
            this.mPixelBytes = 4;
            return;
        }
        this.mPixelBytes = 2;
    }
    
    public void setPos(final long n) {
        if (this.mStatus == 2) {
            this.mSeeking = true;
            this.mStatus = 1;
        }
    }
    
    public void setRenderType(final int n) {
        if (n == 0) {
            this.mbNativeRender = false;
            this.mUseOptimisedBitmapRendering = false;
            return;
        }
        if (n == 2) {
            this.mbNativeRender = false;
            this.mUseOptimisedBitmapRendering = true;
            return;
        }
        this.mbNativeRender = true;
    }
    
    public void setSurface(final Surface mSurface) {
        this.mSurface = mSurface;
    }
    
    public void setSurfaceHolder(final SurfaceHolder mSurfaceHolder) {
        this.mSurfaceHolder = mSurfaceHolder;
        voLog.v(this.TAG, "setSurfaceHolder use RGBA8888!", new Object[0]);
        if (Build.MODEL.toLowerCase().compareToIgnoreCase("vtab1008") == 0) {
            this.mPlayer.SetParam(3L, 23);
        }
        else {
            this.mPlayer.SetParam(3L, 26);
        }
        this.mPixelBytes = 4;
    }
    
    public void stop() {
        voLog.v(this.TAG, "stop  mStatus is " + this.mStatus, new Object[0]);
        this.mStatus = 0;
        while (this.mThreadPlayback != null) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
    }
    
    private class runPlayback implements Runnable
    {
        private voVideoRender mVideoRender;
        
        public runPlayback(final voVideoRender mVideoRender) {
            this.mVideoRender = mVideoRender;
        }
        
        @Override
        public void run() {
            this.mVideoRender.playback();
        }
    }
}
