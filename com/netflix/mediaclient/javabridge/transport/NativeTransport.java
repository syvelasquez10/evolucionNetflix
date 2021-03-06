// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.transport;

import org.json.JSONObject;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.media.MediaPlayerHelperFactory;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.StorageStateUtils;
import com.netflix.mediaclient.util.MediaUtils;
import com.netflix.mediaclient.util.SubtitleUtils;
import com.netflix.mediaclient.media.JPlayer.AdaptiveMediaDecoderHelper;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.error.CrashReport;
import android.os.Process;
import com.netflix.mediaclient.javabridge.error.Signal;
import com.netflix.mediaclient.javabridge.invoke.android.InitVisualOn;
import com.netflix.mediaclient.javabridge.invoke.android.SetVideoSurface;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.util.DeviceCategory;
import java.lang.ref.WeakReference;
import android.os.HandlerThread;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.net.IpConnectivityPolicy;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.javabridge.NrdProxy;
import com.netflix.mediaclient.media.PlayerType;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.Bridge;

public class NativeTransport implements Transport
{
    private static final String TAG = "nf-NativeTransport";
    private static final String TAG1 = "nf_net";
    private static boolean isPropertyStreamingVideoDrs;
    private static boolean mpCapable;
    private Bridge bridge;
    private boolean destroyed;
    private String mChannelId;
    private String mCritSessionId;
    private int mDalvikVMHeapSize;
    private String mDeviceId;
    private boolean mDeviceLowMem;
    private String mDeviceModel;
    private String mDeviceStorageInfo;
    private boolean mEnableLowBitrateStreams;
    private String mEsn;
    private final NativeTransport$TransportEventHandler mEventHandler;
    private String mFesn;
    private String mFesn2;
    private int mGmsPkgVersion;
    private int mGoogleApiClientVersion;
    private int mIpConnectivityPolicy;
    private String mMdxJsVersion;
    private String mRootFileSystem;
    private int mScreenHeight;
    private int mScreenWidth;
    private String mStartupCarrierInfo;
    private boolean mSupportVideoSeamlessSwitch;
    private Surface mSurface;
    private int mVideoBufferSize;
    private String mVideoDecoderCapLogging;
    private final Object mWeakThis;
    private PlayerType playerType;
    private final NrdProxy proxy;
    
    static {
        native_init(AndroidUtils.getAndroidVersion());
        NativeTransport.mpCapable = native_is_mp_capable();
        NativeTransport.isPropertyStreamingVideoDrs = native_isPropertyStreamingVideoDrs();
    }
    
    public NativeTransport(final Bridge bridge, final NrdProxy proxy) {
        this.mVideoBufferSize = 0;
        this.mDalvikVMHeapSize = 0;
        this.mGoogleApiClientVersion = 0;
        this.mGmsPkgVersion = 0;
        this.mIpConnectivityPolicy = IpConnectivityPolicy.IP_V6_V4.getValue();
        this.mStartupCarrierInfo = "";
        Log.d("nf-NativeTransport", "NativeTransport constructor start");
        this.bridge = bridge;
        this.proxy = proxy;
        final HandlerThread handlerThread = new HandlerThread("NativeTransport", 10);
        handlerThread.start();
        this.mEventHandler = new NativeTransport$TransportEventHandler(this, handlerThread.getLooper());
        this.mWeakThis = new WeakReference(this);
        Log.d("nf-NativeTransport", "NativeTransport constructor done");
    }
    
    private void connect(final String s, final int n, final DeviceCategory deviceCategory, final int n2) {
        DeviceCategory unknown = deviceCategory;
        if (deviceCategory == null) {
            Log.e("nf-NativeTransport", "Device class is uknown! It should not happen");
            unknown = DeviceCategory.UNKNOWN;
        }
        this.native_connect(s, n, unknown.getValue(), n2);
    }
    
    public static void enableCrashHandler() {
        native_enable_crash_handler();
    }
    
    public static void enableDolbyDigitalPlus20() {
        native_enableDolbyDigitalPlus20();
    }
    
    public static void enableDolbyDigitalPlus51() {
        native_enableDolbyDigitalPlus51();
    }
    
    public static void enableHDPlayback() {
        native_enableHDPlayback();
    }
    
    public static String[] getDnsList() {
        return native_getDnsList();
    }
    
    public static long getNativeTimeMono() {
        return native_getTimeMono();
    }
    
    public static long getNativeTimeNow() {
        return native_getTimeNow();
    }
    
    public static String[] getSupportedVideoProfiles() {
        return native_getSupportedProfiles();
    }
    
    private boolean handleAndroid(final Invoke invoke) {
        if (invoke instanceof SetVideoSurface) {
            return this.handleSetVideoSurface((SetVideoSurface)invoke);
        }
        return invoke instanceof InitVisualOn && this.handleInitVisualOn((InitVisualOn)invoke);
    }
    
    private boolean handleInitVisualOn(final InitVisualOn initVisualOn) {
        this.native_setVOapi(initVisualOn.getPtr(), initVisualOn.getCtxt());
        return true;
    }
    
    private boolean handleSetVideoSurface(final SetVideoSurface setVideoSurface) {
        this.mSurface = setVideoSurface.getSurface();
        this.native_setVideoSurface();
        return true;
    }
    
    public static final boolean isDrmPlayPresent() {
        return false;
    }
    
    public static boolean isHdCapable() {
        return false;
    }
    
    public static boolean isOMXALmpCapable() {
        return NativeTransport.mpCapable;
    }
    
    public static boolean isPropertyStreamingVideoDrs() {
        return NativeTransport.isPropertyStreamingVideoDrs;
    }
    
    private synchronized native void native_connect(final String p0, final int p1, final String p2, final int p3);
    
    private static final synchronized native void native_enableDolbyDigitalPlus20();
    
    private static final synchronized native void native_enableDolbyDigitalPlus51();
    
    private static final synchronized native void native_enableHDPlayback();
    
    private static final synchronized native void native_enable_crash_handler();
    
    private static final synchronized native String[] native_getDnsList();
    
    private static final synchronized native String[] native_getSupportedProfiles();
    
    private static final synchronized native long native_getTimeMono();
    
    private static final synchronized native long native_getTimeNow();
    
    private static final synchronized native boolean native_init(final int p0);
    
    private final synchronized native void native_invokeMethod(final String p0, final String p1, final String p2);
    
    private static final synchronized native boolean native_isPropertyStreamingVideoDrs();
    
    private static final synchronized native boolean native_is_mp_capable();
    
    private synchronized native void native_release();
    
    private final synchronized native void native_setProperty(final String p0, final String p1, final String p2);
    
    private static final synchronized native void native_setSupportMaxVideoHeight(final int p0);
    
    private synchronized native void native_setVOapi(final long p0, final long p1);
    
    private synchronized native void native_setVideoSurface();
    
    private final synchronized native void native_uiLoaded();
    
    private final synchronized native void native_uiUnloaded();
    
    private void onCrashFromNative(final int n, final long n2, final long n3, final long n4) {
        Log.e("nf-NativeTransport", "Got crash from native: " + n + ", signo: " + n2 + ", errno: " + n3 + ", code: " + n4);
        final Signal signal = Signal.toSignal(n);
        if (signal != null) {
            Log.e("nf-NativeTransport", "Got crash from native: " + signal.getDescription());
        }
        else {
            Log.e("nf-NativeTransport", "Got crash from native and it is not know. This should never happen!");
        }
        this.proxy.postCrashReport(new CrashReport(signal, n2, n3, n4, Process.myPid()));
    }
    
    private static void postEventFromNative(final Object o, final String s) {
        if (Log.isLoggable()) {
            Log.d("nf-NativeTransport", "Got event from native: " + s);
        }
        final NativeTransport nativeTransport = (NativeTransport)((WeakReference)o).get();
        if (nativeTransport == null) {
            Log.e("nf-NativeTransport", "Native transport is NULL. This should NOT happen, event can not be handled!");
            return;
        }
        if (nativeTransport.mEventHandler != null) {
            nativeTransport.mEventHandler.sendMessage(nativeTransport.mEventHandler.obtainMessage(0, (Object)s));
            return;
        }
        Log.e("nf-NativeTransport", "Event handler is NULL. Unable to post handler!");
    }
    
    public static void setSupportMaxVideoHeight(final int n) {
        native_setSupportMaxVideoHeight(n);
    }
    
    @Override
    public void close() {
        Log.d("nf-NativeTransport", "close:: noop");
    }
    
    @Override
    public void connect() {
        Log.d("nf-NativeTransport", "connect started");
        if (this.bridge == null) {
            Log.e("nf-NativeTransport", "App is null? This SHOULD NOT happen!");
        }
        final String fileSystemRoot = this.bridge.getFileSystemRoot();
        final EsnProvider esnProvider = this.bridge.getEsnProvider();
        final IpConnectivityPolicy ipConnectivityPolicy = this.bridge.getIpConnectivityPolicy();
        final String channelId = this.bridge.getChannelId();
        this.mRootFileSystem = StringUtils.notNull("rootFileSystemn", fileSystemRoot);
        this.mEsn = StringUtils.notNull("esn", esnProvider.getEsn());
        String fesn;
        if (esnProvider.getFesn() == null) {
            fesn = "";
        }
        else {
            fesn = esnProvider.getFesn();
        }
        this.mFesn = fesn;
        this.mFesn2 = StringUtils.notNull("fesn2", esnProvider.getFesn2());
        String notNull;
        if (StringUtils.isNotEmpty(channelId)) {
            notNull = StringUtils.notNull("channelId", channelId);
        }
        else {
            notNull = "";
        }
        this.mChannelId = notNull;
        this.mDeviceId = StringUtils.notNull("deviceId", esnProvider.getDeviceId());
        this.mDeviceModel = StringUtils.notNull("modelId", esnProvider.getDeviceModel());
        this.mDeviceLowMem = this.bridge.isDeviceLowMem();
        this.mVideoBufferSize = this.bridge.getConfigVideoBufferSize();
        this.mEnableLowBitrateStreams = this.bridge.enableLowBitrateStreams();
        this.mDalvikVMHeapSize = (int)(Runtime.getRuntime().maxMemory() / 1048576L);
        this.mGoogleApiClientVersion = DeviceUtils.getGooglePlayClientSDKVersion(this.bridge.getContext());
        this.mGmsPkgVersion = DeviceUtils.getGMSPkgVersion(this.bridge.getContext());
        this.mCritSessionId = LoggingAgent.gCritSessionId + "";
        if (AndroidUtils.getAndroidVersion() >= 19) {
            this.mSupportVideoSeamlessSwitch = AdaptiveMediaDecoderHelper.isAvcDecoderSupportsAdaptivePlayback();
        }
        else {
            this.mSupportVideoSeamlessSwitch = false;
        }
        this.mMdxJsVersion = "1.1.6-android";
        this.mScreenWidth = SubtitleUtils.getSubtitleImageMaxWidth(this.bridge.getContext());
        this.mScreenHeight = SubtitleUtils.getSubtitleImageMaxHeight(this.bridge.getContext());
        if (ipConnectivityPolicy != null) {
            this.mIpConnectivityPolicy = ipConnectivityPolicy.getValue();
        }
        this.mVideoDecoderCapLogging = MediaUtils.getDecoderCapbilityForFormatIfUpdated();
        this.mDeviceStorageInfo = StorageStateUtils.buildDeviceExternalStorageLogblobInfo(this.bridge.getContext());
        final JSONObject carrierInfo = ConnectivityUtils.getCarrierInfo(this.bridge.getContext());
        if (carrierInfo != null) {
            this.mStartupCarrierInfo = carrierInfo.toString();
        }
        if (Log.isLoggable()) {
            Log.d("nf-NativeTransport", "rootFileSystem: " + this.mRootFileSystem);
            Log.d("nf-NativeTransport", "esn: " + this.mEsn);
            Log.d("nf-NativeTransport", "deviceId: " + this.mDeviceId);
            Log.d("nf-NativeTransport", "deviceModel: " + this.mDeviceModel);
            Log.d("nf-NativeTransport", "LowMemDevice: " + this.mDeviceLowMem);
            Log.d("nf-NativeTransport", "VideoBufferSize: " + this.mVideoBufferSize);
            Log.d("nf-NativeTransport", "IP connectivity policy: " + this.mIpConnectivityPolicy);
            Log.d("nf-NativeTransport", "Enable Low bitratestreams:" + this.mEnableLowBitrateStreams);
            Log.d("nf-NativeTransport", "Dalvik VM HeapSize in MB: " + this.mDalvikVMHeapSize);
            Log.d("nf-NativeTransport", "Embedded screen width in px: " + this.mScreenWidth);
            Log.d("nf-NativeTransport", "Embedded screen height in px: " + this.mScreenHeight);
            Log.d("nf-NativeTransport", "Google API client version:" + this.mGoogleApiClientVersion);
            Log.d("nf-NativeTransport", "GMS pkg version:" + this.mGmsPkgVersion);
            Log.d("nf-NativeTransport", "CRIT Session Id:" + this.mCritSessionId);
            Log.d("nf-NativeTransport", "MdxJS version:" + this.mMdxJsVersion);
            Log.d("nf-NativeTransport", "MDX Version:2013.3");
            Log.d("nf-NativeTransport", "Video decoders: " + this.mVideoDecoderCapLogging);
            Log.d("nf-NativeTransport", "DeviceStorageInfo: " + this.mDeviceStorageInfo);
            Log.d("nf-NativeTransport", "CellularInfo:" + this.mStartupCarrierInfo);
        }
        this.playerType = this.bridge.getCurrentPlayerType();
        if (this.playerType == null) {
            Log.e("nf-NativeTransport", "This should not happen, player type was null at this point! Use default.");
            this.playerType = PlayerTypeFactory.findDefaultPlayerType();
        }
        else if (Log.isLoggable()) {
            Log.d("nf-NativeTransport", "Player type is " + this.playerType.getDescription());
        }
        if (this.playerType == PlayerType.device12 || this.playerType == PlayerType.device10 || this.playerType == PlayerType.device11) {
            MediaPlayerHelperFactory.getInstance(this.bridge.getContext(), this.playerType);
            Log.d("nf-NativeTransport", this.playerType.getDescription() + "helper initialized");
        }
        this.connect(this.bridge.getInstallationSource(), this.playerType.getValue(), this.bridge.getDeviceCategory(), this.bridge.getEsnProvider().getCryptoFactoryType());
        Log.d("nf-NativeTransport", "connect done");
    }
    
    @Override
    public void disconnect() {
        synchronized (this) {
            Log.d("nf-NativeTransport", "disconnect started");
            if (this.bridge != null) {
                this.bridge = null;
            }
            if (this.destroyed) {
                Log.w("nf-NativeTransport", "Trying to destroy already destroyed transport. This should not happen!");
            }
            else {
                this.native_release();
                this.destroyed = true;
            }
            Log.d("nf-NativeTransport", "disconnect done");
        }
    }
    
    @Override
    public void invokeMethod(final Invoke invoke) {
        if (invoke == null) {
            throw new IllegalArgumentException("Command can not be null!");
        }
        if (this.handleAndroid(invoke)) {
            Log.d("nf-NativeTransport", "Handled directly by JNI");
            return;
        }
        Log.d("nf-NativeTransport", "Handled by bridge");
        try {
            this.native_invokeMethod(invoke.getObject(), invoke.getMethod(), invoke.getArguments());
        }
        catch (Throwable t) {
            Log.w("nf-NativeTransport", "Failure in JNI. It may happend than NRDApp is null!", t);
        }
    }
    
    @Override
    public void invokeMethod(String string, final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("nf-NativeTransport", " invokeMethod subobject = " + string + " method = " + s + " jsonString = " + s2);
        }
        Label_0080: {
            if (string != null) {
                break Label_0080;
            }
            string = "nrdp";
        Block_5_Outer:
            while (true) {
                String s3 = s2;
                if (s2 == null) {
                    s3 = "";
                }
                try {
                    this.native_invokeMethod(string, s, s3);
                    return;
                    Label_0102: {
                        string = "nrdp." + string;
                    }
                    continue Block_5_Outer;
                    while (true) {
                        Log.d("nf-NativeTransport", "setProperty:: Already starts nrdp");
                        continue Block_5_Outer;
                        continue;
                    }
                }
                // iftrue(Label_0102:, !string.startsWith("nrdp"))
                catch (Throwable t) {
                    Log.w("nf-NativeTransport", "Failure in JNI. It may happend than NRDApp is null!", t);
                }
                break;
            }
        }
    }
    
    @Override
    public void setProperty(String string, final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("nf-NativeTransport", " setProperty subobject = " + string + " property = " + s + " jsonString = " + s2);
        }
        Label_0068: {
            if (string != null) {
                break Label_0068;
            }
            string = "nrdp";
            try {
                // iftrue(Label_0090:, !string.startsWith("nrdp"))
                while (true) {
                    while (true) {
                        this.native_setProperty(string, s, s2);
                        return;
                        Log.d("nf-NativeTransport", "setProperty:: Already starts nrdp");
                        continue;
                        Label_0090: {
                            string = "nrdp." + string;
                        }
                        continue;
                    }
                    continue;
                }
            }
            catch (Throwable t) {
                Log.w("nf-NativeTransport", "Failure in JNI. It may happend than NRDApp is null!", t);
            }
        }
    }
    
    @Override
    public void uiLoaded() {
        this.native_uiLoaded();
    }
    
    @Override
    public void uiUnloaded() {
        try {
            this.native_uiUnloaded();
        }
        catch (Throwable t) {
            Log.w("nf-NativeTransport", t, "Catching runtime exception, so service will shutdown...");
        }
    }
}
