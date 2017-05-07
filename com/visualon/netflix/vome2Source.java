// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.netflix;

import com.visualon.OSMPUtils.voOSVideoPerformance;
import com.visualon.OSMPUtils.voOSVideoPerformanceImpl;
import com.visualon.OSMPEngine.voOnStreamSDK;

public class vome2Source
{
    private static final String TAG = "@@@  vome2Source.java";
    private boolean isOpen;
    private voOnStreamSDK mMediaPlayer;
    public int mNativeContext;
    
    static {
        Log.v("@@@  vome2Source.java", "Before loadLibrary, nfxSource_JNI");
        System.loadLibrary("nfxSource_JNI");
    }
    
    public vome2Source(final voOnStreamSDK mMediaPlayer) {
        this.mNativeContext = 0;
        this.mMediaPlayer = mMediaPlayer;
        this.isOpen = false;
    }
    
    private int open(final String s, final long n, final long n2) throws IllegalStateException {
        Log.v("@@@  vome2Source.java", "The source is " + s + "the flag is " + n);
        if (this.isOpen) {
            return 1;
        }
        try {
            this.mMediaPlayer.Open((int)this.getSourceAPI(), 2L);
            this.nativenfxOpen(this.mNativeContext, s, n);
            this.isOpen = true;
            return 1;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public int Uninit() {
        Log.v("@@@  vome2Source.java", "release");
        this.stop();
        this.nativenfxDestroy(this.mNativeContext);
        return 0;
    }
    
    public int closeCB() {
        Log.i("@@@  vome2Source.java", ">>>>>>>lucv in closeCB>>>>>>>>>>>>");
        this.mMediaPlayer.Stop();
        this.stop();
        Log.i("@@@  vome2Source.java", "<<<<<<<lucv in closeCB<<<<<<<<<<<<");
        return 1;
    }
    
    public int create(final Object o, final String s, final String s2) throws IllegalStateException {
        this.nativenfxCreate(o, s, s2);
        return 0;
    }
    
    public int enablePerformanceCB(final boolean b, final int n) {
        Log.i("@@@  vome2Source.java", ">>> enablePerformanceCB");
        final voOnStreamSDK mMediaPlayer = this.mMediaPlayer;
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        mMediaPlayer.SetParam(56L, n2);
        if (b) {
            this.mMediaPlayer.SetParam(52L, n);
        }
        return 1;
    }
    
    public int flushCB() {
        Log.i("@@@  vome2Source.java", ">>>>>>>lucv in flushCB>>>>>>>>>>>>");
        this.mMediaPlayer.Pause();
        this.mMediaPlayer.SetPos(0L);
        Log.i("@@@  vome2Source.java", "<<<<<<<lucv in flushCB<<<<<<<<<<<<");
        return 1;
    }
    
    public long getAPI() {
        Log.v("@@@  vome2Source.java", "api");
        return this.nativenfxPublicAPI();
    }
    
    public int getDuration() {
        return (int)this.nativenfxGetDuration(this.mNativeContext);
    }
    
    public int[] getPerformanceCB(final int[] array) {
        Log.i("@@@  vome2Source.java", ">>> getPerformance");
        this.mMediaPlayer.SetParam(53L, new voOSVideoPerformanceImpl(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9], array[10], new int[1], array[12], array[13], array[14], array[15], array[16], array[17], array[18], array[19]));
        final voOSVideoPerformance voOSVideoPerformance = (voOSVideoPerformance)this.mMediaPlayer.GetParam(53L);
        if (voOSVideoPerformance == null) {
            Log.i("@@@  vome2Source.java", ">>> getPerformance, engine returns null");
            return null;
        }
        final int[] array2 = new int[voOSVideoPerformance.CodecErrorsNum() + 20];
        array2[0] = voOSVideoPerformance.LastTime();
        array2[1] = voOSVideoPerformance.SourceDropNum();
        array2[2] = voOSVideoPerformance.CodecDropNum();
        array2[3] = voOSVideoPerformance.RenderDropNum();
        array2[4] = voOSVideoPerformance.DecodedNum();
        array2[5] = voOSVideoPerformance.RenderNum();
        array2[6] = voOSVideoPerformance.SourceTimeNum();
        array2[7] = voOSVideoPerformance.CodecTimeNum();
        array2[8] = voOSVideoPerformance.RenderTimeNum();
        array2[9] = voOSVideoPerformance.JitterNum();
        array2[10] = voOSVideoPerformance.CodecErrorsNum();
        array2[11] = 0;
        array2[12] = voOSVideoPerformance.CPULoad();
        array2[13] = voOSVideoPerformance.Frequency();
        array2[14] = voOSVideoPerformance.MaxFrequency();
        array2[15] = voOSVideoPerformance.WorstDecodeTime();
        array2[16] = voOSVideoPerformance.WorstRenderTime();
        array2[17] = voOSVideoPerformance.AverageDecodeTime();
        array2[18] = voOSVideoPerformance.AverageRenderTime();
        array2[19] = voOSVideoPerformance.TotalCPULoad();
        for (int i = 0; i < voOSVideoPerformance.CodecErrorsNum(); ++i) {
            array2[i + 20] = voOSVideoPerformance.CodecErrors()[i];
        }
        return array2;
    }
    
    public int getPos() {
        return (int)this.nativenfxGetPos(this.mNativeContext);
    }
    
    public long getSourceAPI() throws IllegalStateException {
        return this.nativenfxGetAPI(this.mNativeContext);
    }
    
    public native long nativenfxCreate(final Object p0, final String p1, final String p2);
    
    public native long nativenfxDestroy(final int p0);
    
    public native long nativenfxGetAPI(final int p0);
    
    public native long nativenfxGetAudioData(final int p0, final byte[] p1);
    
    public native long nativenfxGetDuration(final int p0);
    
    public native long nativenfxGetParam(final int p0, final long p1);
    
    public native long nativenfxGetPos(final int p0);
    
    public native long nativenfxGetVideoData(final int p0, final byte[] p1);
    
    public native long nativenfxOpen(final int p0, final String p1, final long p2);
    
    public native long nativenfxPause(final int p0);
    
    public native long nativenfxPublicAPI();
    
    public native long nativenfxRun(final int p0);
    
    public native long nativenfxSendBufferingDone(final int p0);
    
    public native long nativenfxSendEOS(final int p0);
    
    public native long nativenfxSendUnderflow(final int p0);
    
    public native long nativenfxSetParam(final int p0, final long p1, final long p2);
    
    public native long nativenfxSetPos(final int p0, final long p1);
    
    public native long nativenfxStop(final int p0);
    
    public int pause() throws IllegalStateException {
        if (this.nativenfxPause(this.mNativeContext) != 0L) {
            return -1;
        }
        return 0;
    }
    
    public int pauseCB() {
        Log.i("@@@  vome2Source.java", ">>> pauseCB");
        this.mMediaPlayer.Pause();
        Log.i("@@@  vome2Source.java", "<<< pauseCB <1");
        return 1;
    }
    
    public int playCB() {
        Log.i("@@@  vome2Source.java", ">>>>>>>lucv in playCB>>>>>>>>>>>>");
        while (true) {
            try {
                this.open("", 0L, 11L);
                this.mMediaPlayer.Run();
                this.run();
                Log.i("@@@  vome2Source.java", "<<<<<<<lucv in playCB<<<<<<<<<<<<");
                return 1;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public int positionCB() {
        return this.mMediaPlayer.GetPos();
    }
    
    public int run() throws IllegalStateException {
        Log.v("@@@  vome2Source.java", "run");
        if (this.nativenfxRun(this.mNativeContext) != 0L) {
            return -1;
        }
        return 0;
    }
    
    public int sendBufferingDone() throws IllegalStateException {
        Log.v("@@@  vome2Source.java", "sendBufferingDone");
        if (this.nativenfxSendBufferingDone(this.mNativeContext) != 0L) {
            return -1;
        }
        return 0;
    }
    
    public int sendEOS() {
        Log.v("@@@  vome2Source.java", "sendEOS");
        this.stop();
        this.nativenfxSendEOS(this.mNativeContext);
        return 0;
    }
    
    public int sendUnderflow() throws IllegalStateException {
        Log.v("@@@  vome2Source.java", "sendUnderflow");
        if (this.nativenfxSendUnderflow(this.mNativeContext) != 0L) {
            return -1;
        }
        return 0;
    }
    
    public int setPos(final long n) throws IllegalStateException {
        Log.v("@@@  vome2Source.java", "setPospos is " + n);
        if (this.nativenfxSetPos(this.mNativeContext, n) != 0L) {
            return -1;
        }
        return 0;
    }
    
    public int stop() throws IllegalStateException {
        Log.v("@@@  vome2Source.java", "stop");
        if (this.nativenfxStop(this.mNativeContext) != 0L) {
            return -1;
        }
        return 0;
    }
}
