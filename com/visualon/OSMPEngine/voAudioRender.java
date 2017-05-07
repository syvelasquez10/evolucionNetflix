// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPEngine;

import com.visualon.OSMPUtils.voLog;
import java.nio.ByteBuffer;
import android.media.AudioTrack;

public class voAudioRender
{
    private static String TAG;
    private AudioTrack mAudioTrack;
    private ByteBuffer mByteBuffer;
    private int mChannels;
    private voOnStreamSDK mPlayer;
    private int mSampleRate;
    private int mStatus;
    Thread mThreadPlayback;
    private boolean mbWrite;
    runPlayback mrunPlayback;
    
    static {
        voAudioRender.TAG = "voAudioRender";
    }
    
    public voAudioRender(final voOnStreamSDK mPlayer) {
        this.mPlayer = mPlayer;
        this.mAudioTrack = null;
        this.mSampleRate = 0;
        this.mChannels = 0;
        this.mByteBuffer = null;
        this.mStatus = 0;
        this.mbWrite = false;
        voLog.i(voAudioRender.TAG, "voAudioRender Construct", new Object[0]);
    }
    
    public void arsetVolume(final float n, final float n2) {
        if (this.mAudioTrack != null) {
            this.mAudioTrack.setStereoVolume(n, n2);
        }
    }
    
    public void closeTrack() {
        if (this.mAudioTrack != null) {
            this.mAudioTrack.stop();
            this.mAudioTrack.release();
            this.mAudioTrack = null;
            this.mSampleRate = 0;
            this.mChannels = 0;
        }
    }
    
    public void flush() {
        voLog.v(voAudioRender.TAG, "flush", new Object[0]);
        if (this.mAudioTrack != null) {
            this.mAudioTrack.flush();
        }
    }
    
    public int openTrack(final int mSampleRate, final int mChannels, int n, int minBufferSize) {
        if (this.mAudioTrack != null) {
            this.closeTrack();
        }
        if (mChannels == 1) {
            n = 2;
        }
        else {
            n = 3;
        }
        minBufferSize = AudioTrack.getMinBufferSize(mSampleRate, n, 2);
        if (minBufferSize == -2 || minBufferSize == -1) {
            return -1;
        }
        if ((minBufferSize *= 2) < 2048) {
            minBufferSize = 2048;
        }
        this.mAudioTrack = new AudioTrack(3, mSampleRate, n, 2, minBufferSize, 1);
        voLog.i(voAudioRender.TAG, "Create AudioTrack SampleRate " + mSampleRate + "ChannelCount " + mChannels + " nMinBufSize = " + minBufferSize, new Object[0]);
        n = minBufferSize * 1000 / (mSampleRate * mChannels * 2);
        if (this.mPlayer != null) {
            this.mPlayer.SetParam(18L, n + 100);
        }
        this.mSampleRate = mSampleRate;
        this.mChannels = mChannels;
        this.mByteBuffer = ByteBuffer.allocate(this.mSampleRate * this.mChannels * 3);
        if (this.mByteBuffer == null) {
            voLog.e(voAudioRender.TAG, "Failed to allocate buffer", new Object[0]);
            return -1;
        }
        return 0;
    }
    
    public void pause() {
        voLog.v(voAudioRender.TAG, "pause  mStatus is " + this.mStatus, new Object[0]);
        this.mStatus = 2;
        while (this.mbWrite) {
            try {
                Thread.sleep(2L);
                voLog.w(voAudioRender.TAG, "Audio Pause wait for write completed.", new Object[0]);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (this.mAudioTrack != null) {
            this.mAudioTrack.pause();
        }
    }
    
    public void playback() {
        voLog.v(voAudioRender.TAG, "playbackVideo started!", new Object[0]);
        this.mThreadPlayback.setPriority(8);
        while (this.mStatus == 1 || this.mStatus == 2) {
            if (this.mStatus == 1) {
                this.mbWrite = true;
                long n;
                if (this.mByteBuffer == null) {
                    n = this.mPlayer.GetAudioData(null);
                }
                else {
                    n = this.mPlayer.GetAudioData(this.mByteBuffer.array());
                }
                if (n == 0L && (this.mPlayer.GetAudioSampleRate() != this.mSampleRate || this.mPlayer.GetAudioChannels() != this.mChannels)) {
                    this.openTrack(this.mPlayer.GetAudioSampleRate(), this.mPlayer.GetAudioChannels(), 0, 0);
                    if (this.mAudioTrack != null) {
                        this.mAudioTrack.play();
                    }
                }
                if (n > 0L) {
                    this.writeData(this.mByteBuffer.array(), n);
                }
                this.mbWrite = false;
            }
            else {
                try {
                    Thread.sleep(2L);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.mThreadPlayback = null;
        this.closeTrack();
        voLog.v("vomeaudio Render", "playbackaudio stopped!", new Object[0]);
    }
    
    public void run() {
        voLog.v(voAudioRender.TAG, "run  mStatus is " + this.mStatus, new Object[0]);
        if (this.mStatus != 1) {
            if (this.mStatus == 2 && this.mAudioTrack != null && this.mAudioTrack.getPlayState() == 1) {
                this.mAudioTrack.play();
            }
            this.mStatus = 1;
            if (this.mrunPlayback == null) {
                this.mrunPlayback = new runPlayback(this);
            }
            if (this.mThreadPlayback == null) {
                (this.mThreadPlayback = new Thread(this.mrunPlayback, "vomeAudio Playback")).setPriority(8);
                this.mThreadPlayback.start();
            }
        }
    }
    
    public void stop() {
        voLog.v(voAudioRender.TAG, "stop  mStatus is " + this.mStatus, new Object[0]);
        this.mStatus = 0;
        while (this.mThreadPlayback != null) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public long writeData(final byte[] array, final long n) {
        if (this.mAudioTrack != null && n > 0L) {
            if (this.mAudioTrack.getPlayState() == 2) {
                this.mAudioTrack.play();
                voLog.v("@@@OSMPBasePlayer", "AudioTracker pause end, start run.", new Object[0]);
            }
            this.mAudioTrack.write(array, 0, (int)n);
        }
        return 0L;
    }
    
    private class runPlayback implements Runnable
    {
        private voAudioRender mAudioRender;
        
        public runPlayback(final voAudioRender mAudioRender) {
            this.mAudioRender = mAudioRender;
        }
        
        @Override
        public void run() {
            this.mAudioRender.playback();
        }
    }
}
