// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPEngine;

import com.visualon.OSMPSubTitle.voSubTitleManager;
import android.os.Message;
import android.os.Handler;
import com.visualon.OSMPUtils.voOSVideoPerformance;
import com.visualon.OSMPUtils.voOSRect;
import android.os.Build;
import java.util.Date;
import com.visualon.OSMPUtils.voOSOption;
import com.visualon.OSMPUtils.voOSSubtitleLanguageImpl;
import java.util.ArrayList;
import com.visualon.OSMPUtils.voOSSubtitleLanguage;
import java.util.List;
import com.visualon.OSMPUtils.voOSVideoPerformanceImpl;
import android.os.Parcel;
import com.visualon.OSMPUtils.voOSAudioFormatImpl;
import com.visualon.OSMPUtils.voOSVideoFormatImpl;
import com.visualon.OSMPUtils.voOSCPUInfoImpl;
import android.view.ViewGroup$LayoutParams;
import com.visualon.OSMPUtils.voLog;
import com.visualon.OSMPUtils.voOSRectImpl;
import android.app.Activity;
import android.widget.RelativeLayout;
import android.os.Looper;
import java.util.TimerTask;
import java.util.Timer;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.Surface;
import android.content.Context;
import com.visualon.widget.ClosedCaptionManager;

public class voOnStreamSDK
{
    public static final int KEY_START_TIME = 1;
    private static final String TAG = "@@@voOnStreamSDK.java";
    private ClosedCaptionManager ccMan;
    private boolean closeCaptionOutput;
    private boolean enableInnerCloseCaption;
    private int mAspectRatio;
    private voAudioRender mAudioRender;
    private int mChannels;
    private Context mContext;
    private boolean mEventFinish;
    private EventHandler mEventHandler;
    private onEventListener mEventListener;
    private int mEventMsg;
    private int mEventParam1;
    private int mEventParam2;
    private int mHeightVideo;
    private int mNativeContext;
    private int mOldHeightVideo;
    private int mOldWidthVideo;
    private int mSampleRate;
    private int mScreenHeight;
    private int mScreenWidth;
    private boolean mSubtitleInEngine;
    private Surface mSurface;
    private int mSurfaceChangedCount;
    private boolean mSurfaceChangedFinish;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private int mThirdLibOp;
    Thread mThreadEvent;
    private voVideoRender mVideoRender;
    private int mWidthVideo;
    private boolean mbOMXAL;
    private boolean mbPanScan;
    private Timer timer;
    private TimerTask timerTask;
    
    static {
        System.loadLibrary("voOSEng");
    }
    
    public voOnStreamSDK() {
        this.closeCaptionOutput = false;
        this.enableInnerCloseCaption = false;
        this.timer = null;
        this.timerTask = null;
        this.ccMan = null;
        this.mSubtitleInEngine = true;
        final Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(this, myLooper);
        }
        else {
            final Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(this, mainLooper);
            }
            else {
                this.mEventHandler = null;
            }
        }
        this.mAudioRender = null;
        this.mVideoRender = null;
        this.mSurface = null;
        this.mSurfaceHolder = null;
        this.mSurfaceView = null;
        this.mNativeContext = 0;
        this.mEventListener = null;
        this.mContext = null;
        this.mWidthVideo = 0;
        this.mHeightVideo = 0;
        this.mOldWidthVideo = 0;
        this.mOldHeightVideo = 0;
        this.mScreenWidth = 0;
        this.mScreenHeight = 0;
        this.mAspectRatio = 0;
        this.mSampleRate = 0;
        this.mChannels = 0;
        this.mEventMsg = -1;
        this.mEventParam1 = 0;
        this.mEventParam2 = 0;
        this.mSurfaceChangedFinish = true;
        this.mEventFinish = true;
        this.mSurfaceChangedCount = 0;
        this.mThirdLibOp = 0;
        this.mbOMXAL = false;
        this.mbPanScan = false;
    }
    
    private boolean closeTimer() {
        if (this.timerTask != null) {
            this.timerTask.cancel();
        }
        this.timerTask = null;
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.timer = null;
        return true;
    }
    
    private void createCloseCaptionUI() {
        if (this.enableInnerCloseCaption) {
            if (this.ccMan != null) {
                this.ccMan.show(true);
            }
            if (this.ccMan == null && this.mSurfaceView != null && this.mSurfaceView.getParent() != null) {
                final RelativeLayout mainLayout = (RelativeLayout)this.mSurfaceView.getParent();
                (this.ccMan = new ClosedCaptionManager()).setActivity((Activity)this.mContext);
                this.ccMan.setMainLayout(mainLayout);
                this.startTimer();
            }
        }
    }
    
    private void endPanScan() {
        this.mbPanScan = false;
        this.nativeSetParam(this.mNativeContext, 15L, 4);
        this.nativeSetParam(this.mNativeContext, 2L, new voOSRectImpl(0, 0, this.mWidthVideo, this.mHeightVideo));
        this.mSurfaceHolder.setFixedSize(this.mWidthVideo, this.mHeightVideo);
        voLog.i("@@@voOnStreamSDK.java", "Pan & Scan END, width is %d, height is %d", this.mWidthVideo, this.mHeightVideo);
    }
    
    private native long nativeClose(final int p0);
    
    private native long nativeCreate(final Object p0, final String p1, final long p2, final long p3, final long p4);
    
    private native long nativeDestroy(final int p0);
    
    private native long nativeGetAudioData(final int p0, final byte[] p1);
    
    private native Object nativeGetParam(final int p0, final long p1);
    
    private native long nativeGetPos(final int p0);
    
    private native Object nativeGetSubTitleSample(final int p0, final int p1);
    
    private native int nativeGetSubtitleLanguageCount(final int p0);
    
    private native Object nativeGetSubtitleLanguageInfo(final int p0, final int p1);
    
    private native long nativeGetVideoData(final int p0, final byte[] p1);
    
    private native long nativeOpen(final int p0, final Object p1, final long p2);
    
    private native long nativePause(final int p0);
    
    private native long nativeRun(final int p0);
    
    private native int nativeSelectSubtitleLanguage(final int p0, final int p1);
    
    private native long nativeSetParam(final int p0, final long p1, final Object p2);
    
    private native long nativeSetPos(final int p0, final long p1);
    
    private native long nativeSetSurface(final int p0);
    
    private native long nativeStop(final int p0);
    
    private void osmpCallBack(final int n, final int n2, final int n3, final Object o) {
        this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(n, n2, n3, o));
    }
    
    private void startPanScan() {
        this.mbPanScan = true;
        this.nativeSetParam(this.mNativeContext, 15L, 2);
        int mWidthVideo;
        int mHeightVideo;
        if (this.mScreenWidth * this.mHeightVideo > this.mScreenHeight * this.mWidthVideo) {
            mWidthVideo = this.mWidthVideo;
            mHeightVideo = this.mScreenHeight * mWidthVideo / this.mScreenWidth;
        }
        else {
            mHeightVideo = this.mHeightVideo;
            mWidthVideo = this.mScreenWidth * mHeightVideo / this.mScreenHeight;
        }
        final int n = mWidthVideo & 0xFFFFFFF8;
        final int n2 = mHeightVideo & 0xFFFFFFFC;
        this.mSurfaceHolder.setFixedSize(n, n2);
        this.nativeSetParam(this.mNativeContext, 2L, new voOSRectImpl(0, 0, n, n2));
        final ViewGroup$LayoutParams layoutParams = this.mSurfaceView.getLayoutParams();
        layoutParams.width = this.mScreenWidth;
        layoutParams.height = this.mScreenHeight;
        this.mSurfaceView.setLayoutParams(layoutParams);
        voLog.i("@@@voOnStreamSDK.java", "Pan & Scan, width is %d, height is %d", n, n2);
    }
    
    private boolean startTimer() {
        this.closeTimer();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                voOnStreamSDK.this.mEventHandler.sendEmptyMessage(8193);
            }
        };
        if (this.timer == null) {
            this.timer = new Timer();
        }
        this.timer.schedule(this.timerTask, 300L, 100L);
        voLog.v("@@@voOnStreamSDK.java", "Start ClosedCaption!", new Object[0]);
        return true;
    }
    
    public int Close() {
        return (int)this.nativeClose(this.mNativeContext);
    }
    
    public int GetAudioChannels() {
        return this.mChannels;
    }
    
    public long GetAudioData(final byte[] array) {
        return this.nativeGetAudioData(this.mNativeContext, array);
    }
    
    public int GetAudioSampleRate() {
        return this.mSampleRate;
    }
    
    public Object GetParam(final long n) {
        Object o;
        if (n == 10497L) {
            if (this.ccMan != null) {
                return this.ccMan.getSettings();
            }
            o = null;
        }
        else {
            final Object nativeGetParam = this.nativeGetParam(this.mNativeContext, n);
            if (n == 38L) {
                if (nativeGetParam == null) {
                    return null;
                }
                final int[] array = (int[])nativeGetParam;
                voLog.i("@@@voOnStreamSDK.java", "CPU info %d %d %d", array[0], array[1], array[2]);
                return new voOSCPUInfoImpl(array[0], array[1], array[2], 0L);
            }
            else if (n == 21L) {
                if (nativeGetParam == null) {
                    return null;
                }
                final int[] array2 = (int[])nativeGetParam;
                voLog.i("@@@voOnStreamSDK.java", "VOOSMP_PID_VIDEO_FORMAT %d %d %d", array2[0], array2[1], array2[2]);
                return new voOSVideoFormatImpl(array2[0], array2[1], array2[2]);
            }
            else if (n == 20L) {
                if (nativeGetParam == null) {
                    return null;
                }
                final int[] array3 = (int[])nativeGetParam;
                voLog.i("@@@voOnStreamSDK.java", "VOOSMP_PID_AUDIO_FORMAT %d %d %d", array3[0], array3[1], array3[2]);
                return new voOSAudioFormatImpl(array3[0], array3[1], array3[2]);
            }
            else {
                o = nativeGetParam;
                if (n == 53L) {
                    if (nativeGetParam == null) {
                        voLog.i("@@@voOnStreamSDK.java", "Get Performace Data Error!!", new Object[0]);
                        return null;
                    }
                    final Parcel parcel = (Parcel)nativeGetParam;
                    parcel.setDataPosition(0);
                    if (parcel.dataAvail() == 0) {
                        return null;
                    }
                    final voOSVideoPerformanceImpl voOSVideoPerformanceImpl = new voOSVideoPerformanceImpl();
                    voOSVideoPerformanceImpl.parser(parcel);
                    voLog.i("@@@voOnStreamSDK.java", "Get Performance Data, LastTime %d, SourceDropNum %d, CodecDropNum %d, RenderDropNum %d, DecodedNum %d, RenderNum %d, SourceTimeNum %d, CodecTimeNum %d, RenderTimeNum %d, JitterNum %d, CodecErrorsNum %d, CPULoad %d, Frequency %d, MaxFrequency %d, WorstDecodeTime %d, WorstRenderTime %d, AverageDecodeTime %d, AverageRenderTime %d, TotalCPULoad %d", voOSVideoPerformanceImpl.LastTime(), voOSVideoPerformanceImpl.SourceDropNum(), voOSVideoPerformanceImpl.CodecDropNum(), voOSVideoPerformanceImpl.RenderDropNum(), voOSVideoPerformanceImpl.DecodedNum(), voOSVideoPerformanceImpl.RenderNum(), voOSVideoPerformanceImpl.SourceTimeNum(), voOSVideoPerformanceImpl.CodecTimeNum(), voOSVideoPerformanceImpl.RenderTimeNum(), voOSVideoPerformanceImpl.JitterNum(), voOSVideoPerformanceImpl.CodecErrorsNum(), voOSVideoPerformanceImpl.CPULoad(), voOSVideoPerformanceImpl.Frequency(), voOSVideoPerformanceImpl.MaxFrequency(), voOSVideoPerformanceImpl.WorstDecodeTime(), voOSVideoPerformanceImpl.WorstRenderTime(), voOSVideoPerformanceImpl.AverageDecodeTime(), voOSVideoPerformanceImpl.AverageRenderTime(), voOSVideoPerformanceImpl.TotalCPULoad());
                    for (int i = 0; i < voOSVideoPerformanceImpl.CodecErrorsNum(); ++i) {
                        voLog.i("@@@voOnStreamSDK.java", "CodecErrors index = %d, errorcode is %d", i, voOSVideoPerformanceImpl.CodecErrors()[i]);
                    }
                    return voOSVideoPerformanceImpl;
                }
            }
        }
        return o;
    }
    
    public int GetPos() {
        return (int)this.nativeGetPos(this.mNativeContext);
    }
    
    public Parcel GetSubTitleSample(final int n) {
        return (Parcel)this.nativeGetSubTitleSample(this.mNativeContext, n);
    }
    
    public List<voOSSubtitleLanguage> GetSubtitleLanguageInfo() {
        if (this.mNativeContext == 0) {
            voLog.e("@@@voOnStreamSDK.java", "VOOSMP_ERR_Uninitialize", new Object[0]);
            return null;
        }
        final int nativeGetSubtitleLanguageCount = this.nativeGetSubtitleLanguageCount(this.mNativeContext);
        voLog.v("@@@voOnStreamSDK.java", "Language count is %d.", nativeGetSubtitleLanguageCount);
        final ArrayList<voOSSubtitleLanguageImpl> list = (ArrayList<voOSSubtitleLanguageImpl>)new ArrayList<voOSSubtitleLanguage>();
        for (int i = 0; i < nativeGetSubtitleLanguageCount; ++i) {
            list.add((voOSSubtitleLanguageImpl)this.nativeGetSubtitleLanguageInfo(this.mNativeContext, i));
        }
        return (List<voOSSubtitleLanguage>)list;
    }
    
    public long GetVideoData(final byte[] array) {
        return this.nativeGetVideoData(this.mNativeContext, array);
    }
    
    public int GetVideoHeight() {
        return this.mHeightVideo;
    }
    
    public int GetVideoWidth() {
        return this.mWidthVideo;
    }
    
    public int Init(final Context mContext, String lowerCase, final List<voOSOption> list, final long n, final long n2, final long n3) throws IllegalStateException {
        this.mContext = mContext;
        final Date date = new Date(System.currentTimeMillis());
        final int n4 = (int)this.nativeCreate(this, lowerCase, n, n2, n3);
        if (n == 1L) {
            this.mbOMXAL = true;
        }
        else {
            this.mVideoRender = new voVideoRender(this);
            if (this.mVideoRender != null) {
                this.mVideoRender.setSurface(this.mSurface);
            }
            this.mbOMXAL = false;
        }
        this.mAudioRender = new voAudioRender(this);
        lowerCase = Build.MODEL.toLowerCase();
        voLog.v("@@@voOnStreamSDK.java", lowerCase, new Object[0]);
        if (lowerCase.contains("kindle")) {
            this.SetParam(13L, 300);
            this.SetParam(29L, 500);
            this.SetParam(28L, 300);
            voLog.v("@@@voOnStreamSDK.java", "This device is kindle fire, setup audo param!", new Object[0]);
        }
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                final voOSOption voOSOption = list.get(i);
                if (voOSOption.getType() == com.visualon.OSMPUtils.voOSOption.eVoOption.eoVideoRender) {
                    final int n5 = (int)voOSOption.getValue();
                    this.SetParam(22L, n5);
                    voLog.v("@@@voOnStreamSDK.java", "SetParam voOSType.VOOSMP_PID_VIDEO_RENDER_TYPE is %d .", n5);
                }
            }
        }
        voLog.i("@@@voOnStreamSDK.java", "init spend time is %d", (int)(Object)Long.valueOf(new Date(System.currentTimeMillis()).getTime() - date.getTime()));
        return n4;
    }
    
    public int Open(final Object o, final long n) throws IllegalStateException {
        return (int)this.nativeOpen(this.mNativeContext, o, n);
    }
    
    public int Pause() {
        if (this.mVideoRender != null) {
            this.mVideoRender.pause();
        }
        if (this.mAudioRender != null) {
            this.mAudioRender.pause();
        }
        final int n = (int)this.nativePause(this.mNativeContext);
        if (n != 0) {}
        return n;
    }
    
    public int Run() {
        voLog.v("@@@voOnStreamSDK.java", "run", new Object[0]);
        final int n = (int)this.nativeRun(this.mNativeContext);
        if (n == 0) {
            if (this.mVideoRender != null) {
                this.mVideoRender.run();
            }
            if (this.mAudioRender != null) {
                this.mAudioRender.run();
                return n;
            }
        }
        return n;
    }
    
    public int SelectSubtitleLanguage(final int n) {
        if (this.mNativeContext == 0) {
            voLog.e("@@@voOnStreamSDK.java", "VOOSMP_ERR_Uninitialize", new Object[0]);
            return -2147483391;
        }
        return this.nativeSelectSubtitleLanguage(this.mNativeContext, n);
    }
    
    public void SetDisplaySize(final int n, final int n2) {
        if (n > n2) {
            this.mScreenWidth = n;
            this.mScreenHeight = n2;
            return;
        }
        this.mScreenWidth = n2;
        this.mScreenHeight = n;
    }
    
    public int SetParam(final long n, final Object o) {
        if (n == 4135L) {
            this.mSurfaceChangedFinish = true;
        }
        else {
            if (n == 2L) {
                final voOSRect voOSRect = (voOSRect)o;
                return (int)this.nativeSetParam(this.mNativeContext, n, new int[] { voOSRect.Left(), voOSRect.Top(), voOSRect.Right(), voOSRect.Bottom() });
            }
            if (n == 15L) {
                final int intValue = (int)o;
                if (intValue == 2) {
                    this.mbPanScan = true;
                }
                else if (intValue == 4) {
                    this.mbPanScan = false;
                }
            }
            else {
                if (n == 51L || n == 59L) {
                    this.mSurfaceView = (SurfaceView)o;
                    this.mSurfaceHolder = this.mSurfaceView.getHolder();
                    voLog.v("@@@voOnStreamSDK.java", "setDisplay suface is " + this.mSurfaceHolder + " ID = " + n, new Object[0]);
                    if (this.mSurfaceHolder != null) {
                        this.mSurface = this.mSurfaceHolder.getSurface();
                    }
                    else {
                        this.mSurface = null;
                    }
                    if (this.mVideoRender != null && !this.mbOMXAL) {
                        this.mVideoRender.setSurface(this.mSurface);
                        this.mVideoRender.setSurfaceHolder(this.mSurfaceHolder);
                        this.nativeSetSurface(this.mNativeContext);
                    }
                    this.nativeSetParam(this.mNativeContext, n, this.mSurface);
                    return 0;
                }
                if (n == 53L) {
                    if (o == null) {
                        voLog.i("@@@voOnStreamSDK.java", "Param data is NULL!", new Object[0]);
                        return -2147483634;
                    }
                    final int[] array = new int[30];
                    final voOSVideoPerformance voOSVideoPerformance = (voOSVideoPerformance)o;
                    array[0] = voOSVideoPerformance.LastTime();
                    array[1] = voOSVideoPerformance.SourceDropNum();
                    array[2] = voOSVideoPerformance.CodecDropNum();
                    array[3] = voOSVideoPerformance.RenderDropNum();
                    array[4] = voOSVideoPerformance.DecodedNum();
                    array[5] = voOSVideoPerformance.RenderNum();
                    array[6] = voOSVideoPerformance.SourceTimeNum();
                    array[7] = voOSVideoPerformance.CodecTimeNum();
                    array[8] = voOSVideoPerformance.RenderTimeNum();
                    array[9] = voOSVideoPerformance.JitterNum();
                    array[10] = voOSVideoPerformance.CodecErrorsNum();
                    array[11] = 0;
                    array[12] = voOSVideoPerformance.CPULoad();
                    array[13] = voOSVideoPerformance.Frequency();
                    array[14] = voOSVideoPerformance.MaxFrequency();
                    array[15] = voOSVideoPerformance.WorstDecodeTime();
                    array[16] = voOSVideoPerformance.WorstRenderTime();
                    array[17] = voOSVideoPerformance.AverageDecodeTime();
                    array[18] = voOSVideoPerformance.AverageRenderTime();
                    array[19] = voOSVideoPerformance.TotalCPULoad();
                    return (int)this.nativeSetParam(this.mNativeContext, n, array);
                }
                else if (n == 42L) {
                    if ((int)o == 1) {
                        this.nativeSetParam(this.mNativeContext, 42L, 1);
                        if (this.mSubtitleInEngine) {
                            this.enableInnerCloseCaption = true;
                            this.createCloseCaptionUI();
                            return 0;
                        }
                        return 0;
                    }
                    else {
                        this.nativeSetParam(this.mNativeContext, 42L, o);
                        if (!this.mSubtitleInEngine) {
                            return 0;
                        }
                        this.enableInnerCloseCaption = false;
                        if (this.ccMan != null) {
                            this.ccMan.show(false);
                            return 0;
                        }
                        return 0;
                    }
                }
                else if (n == 10496L) {
                    if ((int)o == 1) {
                        this.nativeSetParam(this.mNativeContext, 42L, 1);
                        if (!this.mSubtitleInEngine) {
                            return 0;
                        }
                        this.closeCaptionOutput = true;
                        this.startTimer();
                        if (this.ccMan != null) {
                            this.ccMan.show(false);
                            return 0;
                        }
                        return 0;
                    }
                    else {
                        if (!this.mSubtitleInEngine) {
                            return 0;
                        }
                        this.closeCaptionOutput = false;
                        if (this.ccMan != null) {
                            this.ccMan.show(true);
                            return 0;
                        }
                        return 0;
                    }
                }
                else if (n == 4137L) {
                    if (!(this.mSubtitleInEngine = ((int)o != 0))) {
                        this.closeTimer();
                        this.ccMan = null;
                    }
                }
                else if (n == 4134L && (int)o == 1) {
                    final String lowerCase = Build.MODEL.toLowerCase();
                    if (lowerCase.contains("im-a760s") || lowerCase.contains("galaxy nexus")) {
                        this.SetParam(13L, 300);
                        this.SetParam(29L, 500);
                        this.SetParam(28L, 300);
                        voLog.v("@@@voOnStreamSDK.java", "This device is " + lowerCase + ", setup audo param!", new Object[0]);
                    }
                }
                else if (n == 22L) {
                    final int intValue2 = (int)o;
                    if (this.mVideoRender != null) {
                        this.mVideoRender.setRenderType(intValue2);
                    }
                }
                else if (n == 3L) {
                    final int intValue3 = (int)o;
                    if (this.mVideoRender != null) {
                        this.mVideoRender.setColorType(intValue3);
                    }
                }
            }
            return (int)this.nativeSetParam(this.mNativeContext, n, o);
        }
        return 0;
    }
    
    public int SetPos(final long pos) {
        voLog.v("@@@voOnStreamSDK.java", "setPospos is " + pos, new Object[0]);
        final int n = (int)this.nativeSetPos(this.mNativeContext, pos);
        if (n != 0) {
            return n;
        }
        if (this.mAudioRender != null) {
            this.mAudioRender.flush();
        }
        if (this.mVideoRender != null) {
            this.mVideoRender.setPos(pos);
        }
        return 0;
    }
    
    public void SetView(final SurfaceView mSurfaceView) {
        if (mSurfaceView == null) {
            this.mSurface = null;
            this.nativeSetSurface(this.mNativeContext);
            voLog.v("@@@voOnStreamSDK.java", "Surface Destroy, value is null", new Object[0]);
            return;
        }
        this.mSurfaceView = mSurfaceView;
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        voLog.v("@@@voOnStreamSDK.java", "setDisplay suface is " + this.mSurfaceHolder, new Object[0]);
        if (this.mSurfaceHolder != null) {
            this.mSurface = this.mSurfaceHolder.getSurface();
        }
        else {
            this.mSurface = null;
        }
        if (this.mVideoRender != null) {
            this.mVideoRender.setSurface(this.mSurface);
            this.mVideoRender.setSurfaceHolder(this.mSurfaceHolder);
        }
        this.nativeSetSurface(this.mNativeContext);
    }
    
    public int SetVolume(final float n, final float n2) throws IllegalStateException {
        if (this.mAudioRender != null) {
            this.mAudioRender.arsetVolume(n, n2);
        }
        return 0;
    }
    
    public int Stop() {
        voLog.v("@@@voOnStreamSDK.java", "stop", new Object[0]);
        if (this.mVideoRender != null) {
            this.mVideoRender.stop();
        }
        if (this.mAudioRender != null) {
            this.mAudioRender.stop();
        }
        this.nativeSetParam(this.mNativeContext, 42L, new Integer(0));
        this.enableInnerCloseCaption = false;
        if (this.ccMan != null) {
            this.ccMan.show(false);
            this.ccMan.clearWidget();
            this.ccMan = null;
        }
        return (int)this.nativeStop(this.mNativeContext);
    }
    
    public int Uninit() {
        voLog.v("@@@voOnStreamSDK.java", "destroy", new Object[0]);
        final Date date = new Date(System.currentTimeMillis());
        this.Stop();
        this.closeTimer();
        this.nativeDestroy(this.mNativeContext);
        this.mEventListener = null;
        this.mEventHandler.removeCallbacksAndMessages((Object)null);
        voLog.i("@@@voOnStreamSDK.java", "init spend time is %d", (int)(Object)Long.valueOf(new Date(System.currentTimeMillis()).getTime() - date.getTime()));
        return 0;
    }
    
    public void VideoSizeChanged() {
        this.mSurfaceChangedFinish = false;
        this.mEventFinish = false;
        ++this.mSurfaceChangedCount;
        this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(15, this.mWidthVideo, this.mHeightVideo, (Object)this));
        int n = 0;
        while (!this.mEventFinish) {
            ++n;
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        voLog.i("@@@voOnStreamSDK.java", "VideoSizeChanged, spend %d seconds. mSurfaceChangedCount is %d", n, this.mSurfaceChangedCount);
        int n2 = 0;
        int n3;
        while (true) {
            n3 = n2;
            if (this.mSurfaceChangedFinish) {
                break;
            }
            n3 = n2;
            if (this.mSurfaceChangedCount != 1) {
                break;
            }
            ++n2;
            if (n2 > 200) {
                n3 = n2;
                break;
            }
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {
                ex2.printStackTrace();
            }
        }
        voLog.i("@@@voOnStreamSDK.java", "VideoSizeChanged after surfacechanged, spend %d seconds", n3);
    }
    
    public void setEventListener(final onEventListener mEventListener) {
        this.mEventListener = mEventListener;
    }
    
    public void updateVideoAspectRatio(int n, int n2) {
        final int n3 = 8;
        n &= 0xFFFFFFF8;
        if (n < 8) {
            n = n3;
        }
        final ViewGroup$LayoutParams layoutParams = this.mSurfaceView.getLayoutParams();
        final int mScreenWidth = this.mScreenWidth;
        final int mScreenHeight = this.mScreenHeight;
        final int n4 = mScreenWidth & 0xFFFFFFF8;
        final int n5 = mScreenHeight & 0xFFFFFFFE;
        if (n4 * n2 > n * n5) {
            n2 = n * n5 / n2;
            n = n5;
        }
        else {
            n = n4 * n2 / n;
            n2 = n4;
        }
        layoutParams.width = (n2 & 0xFFFFFFF8);
        layoutParams.height = (n & 0xFFFFFFFC);
        if (this.ccMan != null) {
            this.ccMan.setXYRate(layoutParams.width / layoutParams.height);
        }
        this.mSurfaceView.setLayoutParams(layoutParams);
        voLog.v("@@@voOnStreamSDK.java", String.format("VOOSMP_ASPECT_RATIO on video size changed, width=%d, height=%d", layoutParams.width, layoutParams.height), new Object[0]);
    }
    
    private class EventHandler extends Handler
    {
        private voOnStreamSDK mMediaPlayer;
        
        public EventHandler(final voOnStreamSDK mMediaPlayer, final Looper looper) {
            super(looper);
            this.mMediaPlayer = mMediaPlayer;
        }
        
        public void handleMessage(final Message message) {
            if (this.mMediaPlayer.mNativeContext == 0) {
                voLog.w("@@@voOnStreamSDK.java", "vomeplayer went away with unhandled events", new Object[0]);
            }
            else {
                Message obtainMessage;
                if (message.what == 8193) {
                    if (!voOnStreamSDK.this.mSubtitleInEngine) {
                        return;
                    }
                    final int getPos = voOnStreamSDK.this.GetPos();
                    final Parcel getSubTitleSample = voOnStreamSDK.this.GetSubTitleSample(getPos);
                    if (voOnStreamSDK.this.closeCaptionOutput) {
                        if (getSubTitleSample == null) {
                            return;
                        }
                        voSubTitleManager.voSubtitleInfo voSubtitleInfo;
                        if (voOnStreamSDK.this.ccMan != null) {
                            voSubtitleInfo = voOnStreamSDK.this.ccMan.parcelToSubtitleInfo(getSubTitleSample);
                        }
                        else {
                            voSubtitleInfo = new ClosedCaptionManager().parcelToSubtitleInfo(getSubTitleSample);
                        }
                        obtainMessage = voOnStreamSDK.this.mEventHandler.obtainMessage(8193, 0, 0, (Object)voSubtitleInfo);
                    }
                    else {
                        if (voOnStreamSDK.this.ccMan != null && voOnStreamSDK.this.enableInnerCloseCaption) {
                            if (getSubTitleSample != null) {
                                voOnStreamSDK.this.ccMan.setData(getSubTitleSample, true);
                            }
                            voOnStreamSDK.this.ccMan.checkViewShowStatus(getPos);
                        }
                        return;
                    }
                }
                else if (message.what == 14) {
                    voOnStreamSDK.this.mAspectRatio = message.arg1;
                    obtainMessage = message;
                    if (voOnStreamSDK.this.mOldWidthVideo != 0) {
                        obtainMessage = message;
                        if (voOnStreamSDK.this.mOldHeightVideo != 0) {
                            obtainMessage = message;
                            if (!voOnStreamSDK.this.mbPanScan) {
                                switch (voOnStreamSDK.this.mAspectRatio) {
                                    case 1: {
                                        voOnStreamSDK.this.updateVideoAspectRatio(1, 1);
                                        break;
                                    }
                                    case 2: {
                                        voOnStreamSDK.this.updateVideoAspectRatio(4, 3);
                                        break;
                                    }
                                    case 3: {
                                        voOnStreamSDK.this.updateVideoAspectRatio(16, 9);
                                        break;
                                    }
                                    case 4: {
                                        voOnStreamSDK.this.updateVideoAspectRatio(2, 1);
                                        break;
                                    }
                                }
                                voLog.v("@@@voOnStreamSDK.java", "OnEvent message VOOSMP_CB_VideoAspectRatio, but get later for Video Width height ", new Object[0]);
                                obtainMessage = message;
                            }
                        }
                    }
                }
                else {
                    obtainMessage = message;
                    if (message.what == 15) {
                        final int arg1 = message.arg1;
                        final int arg2 = message.arg2;
                        if (arg1 == 0 || arg2 == 0) {
                            voLog.e("@@@voOnStreamSDK.java", "Error!, Video Width or height is 0, width is %d, height is %d", arg1, arg2);
                            return;
                        }
                        if (voOnStreamSDK.this.mScreenWidth == 0 || voOnStreamSDK.this.mScreenHeight == 0) {
                            voLog.e("@@@voOnStreamSDK.java", "Error!, Screen Size is 0, please first call SetScreenSize, input screen Width or height, width is %d, height is %d", voOnStreamSDK.this.mScreenWidth, voOnStreamSDK.this.mScreenHeight);
                            return;
                        }
                        voLog.i("@@@voOnStreamSDK.java", "Old video width is %d, old video height is %d. \nNew video width is %d, new video height is %d. mAspectRatio is %d ", voOnStreamSDK.this.mOldWidthVideo, voOnStreamSDK.this.mOldHeightVideo, arg1, arg2, voOnStreamSDK.this.mAspectRatio);
                        if (voOnStreamSDK.this.mAspectRatio != 0 && !voOnStreamSDK.this.mbPanScan) {
                            switch (voOnStreamSDK.this.mAspectRatio) {
                                case 1: {
                                    voOnStreamSDK.this.updateVideoAspectRatio(1, 1);
                                    break;
                                }
                                case 2: {
                                    voOnStreamSDK.this.updateVideoAspectRatio(4, 3);
                                    break;
                                }
                                case 3: {
                                    voOnStreamSDK.this.updateVideoAspectRatio(16, 9);
                                    break;
                                }
                                case 4: {
                                    voOnStreamSDK.this.updateVideoAspectRatio(2, 1);
                                    break;
                                }
                            }
                        }
                        else if (!voOnStreamSDK.this.mbPanScan && ((voOnStreamSDK.this.mOldWidthVideo == 0 && arg1 > 0) || voOnStreamSDK.this.mOldWidthVideo * arg2 != voOnStreamSDK.this.mOldHeightVideo * arg1)) {
                            final ViewGroup$LayoutParams layoutParams = voOnStreamSDK.this.mSurfaceView.getLayoutParams();
                            voLog.v("@@@voOnStreamSDK.java", "Screen Size. Width is " + voOnStreamSDK.this.mScreenWidth + " height is " + voOnStreamSDK.this.mScreenHeight, new Object[0]);
                            voLog.v("@@@voOnStreamSDK.java", "Video Size Changed. video Width is %d, height is %d. SurfaceView width is %d, height is %d ", arg1, arg2, layoutParams.width, layoutParams.height);
                            if (arg1 != 0 && arg2 != 0) {
                                final int access$1000 = voOnStreamSDK.this.mScreenWidth;
                                final int access$1001 = voOnStreamSDK.this.mScreenHeight;
                                if (arg1 != 0 && arg2 != 0) {
                                    int n = access$1000 & 0xFFFFFFF8;
                                    int n2 = access$1001 & 0xFFFFFFFE;
                                    if (n * arg2 > arg1 * n2) {
                                        n = arg1 * n2 / arg2;
                                    }
                                    else {
                                        n2 = arg2 * n / arg1;
                                    }
                                    layoutParams.width = (n & 0xFFFFFFF8);
                                    layoutParams.height = (n2 & 0xFFFFFFFC);
                                }
                                if (voOnStreamSDK.this.ccMan != null && voOnStreamSDK.this.mSubtitleInEngine) {
                                    voOnStreamSDK.this.ccMan.setXYRate(layoutParams.width / layoutParams.height);
                                }
                                voOnStreamSDK.this.mSurfaceView.setLayoutParams(layoutParams);
                                voLog.v("@@@voOnStreamSDK.java", String.format("Set SurfaceView Size on video size changed, width=%d, height=%d", layoutParams.width, layoutParams.height), new Object[0]);
                            }
                        }
                        else if (voOnStreamSDK.this.mbPanScan) {}
                        voOnStreamSDK.this.mOldWidthVideo = arg1;
                        voOnStreamSDK.this.mOldHeightVideo = arg2;
                        if (arg1 != 0 && arg2 != 0 && !voOnStreamSDK.this.mbOMXAL && !voOnStreamSDK.this.mbPanScan) {
                            voOnStreamSDK.this.mSurfaceHolder.setFixedSize(arg1, arg2);
                            voOnStreamSDK.this.mSurfaceView.invalidate();
                            voOnStreamSDK.this.mSurfaceView.setVisibility(0);
                            voLog.v("@@@voOnStreamSDK.java", String.format("SurfaceHolder.setFixedSize video Width is %d , Height is %d. PanScan is %b", arg1, arg2, voOnStreamSDK.this.mbPanScan), new Object[0]);
                        }
                        if (this.mMediaPlayer.mEventListener != null) {
                            this.mMediaPlayer.mEventListener.onEvent(message.what, message.arg1, message.arg2, message.obj);
                        }
                        voOnStreamSDK.this.mEventFinish = true;
                        return;
                    }
                }
                if (this.mMediaPlayer.mEventListener != null) {
                    this.mMediaPlayer.mEventListener.onEvent(obtainMessage.what, obtainMessage.arg1, obtainMessage.arg2, obtainMessage.obj);
                }
            }
        }
    }
    
    public interface onEventListener
    {
        int onEvent(final int p0, final int p1, final int p2, final Object p3);
    }
}
