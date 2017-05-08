// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import java.util.UUID;
import com.netflix.mediaclient.util.MediaDrmUtils;
import android.media.MediaDrm$CryptoSession;
import com.netflix.mediaclient.util.CryptoUtils;
import android.annotation.TargetApi;
import android.media.MediaDrmResetException;
import java.util.Arrays;
import com.netflix.msl.keyx.WidevineKeyRequestData;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.error.crypto.ErrorSource;
import com.netflix.mediaclient.service.error.crypto.CryptoErrorManager;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.msl.client.AndroidWidevineKeyRequestData;
import android.media.MediaDrm;
import java.util.concurrent.atomic.AtomicInteger;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.media.MediaDrm$OnEventListener;

public abstract class BaseCryptoManager implements MediaDrm$OnEventListener, CryptoManager
{
    private static final int CHUNK_SIZE = 16384;
    protected static final byte[] EMPTY_RETURN_ARRAY;
    private static final int MAX_ACTIVE_MEDIADRM_SESSION_WITHKEY = 2;
    private static final int MAX_ACTIVE_MEDIADRM_SESSION_WITHOUTKEY = 2;
    public static final String PROPERTY_SYSTEM_ID = "systemId";
    private static String TAG;
    protected final byte[] init;
    protected CryptoManager$DrmReadyCallback mCallback;
    protected ServiceAgent$ConfigurationAgentInterface mConfiguration;
    protected Context mContext;
    protected CryptoProvider mCryptoProvider;
    protected AtomicInteger mCryptoSessionCounter;
    protected MediaDrm mDrm;
    private AndroidWidevineKeyRequestData mPendingKeyRequestData;
    protected AtomicBoolean mPlaybackInProgress;
    private BaseCryptoManager$PlaybackWatcherReceiver mReceiver;
    
    static {
        BaseCryptoManager.TAG = "nf_msl";
        EMPTY_RETURN_ARRAY = new byte[0];
    }
    
    public BaseCryptoManager(final Context mContext, final CryptoProvider mCryptoProvider, final ServiceAgent$ConfigurationAgentInterface mConfiguration, final CryptoManager$DrmReadyCallback mCallback) {
        this.init = new byte[] { 10, 122, 0, 108, 56, 43 };
        this.mPlaybackInProgress = new AtomicBoolean(false);
        this.mReceiver = new BaseCryptoManager$PlaybackWatcherReceiver(this, null);
        this.mCryptoSessionCounter = new AtomicInteger();
        BaseCryptoManager.TAG = this.getLogTag();
        if (mCallback == null) {
            throw new IllegalArgumentException("Calllback is null!");
        }
        this.mCryptoProvider = mCryptoProvider;
        this.mCallback = mCallback;
        this.mConfiguration = mConfiguration;
        this.mContext = mContext;
        (this.mDrm = this.createMediaDrm()).setOnEventListener((MediaDrm$OnEventListener)this);
        this.setSecurityLevel();
        this.addReceiver();
        this.showProperties();
        this.load();
    }
    
    private void addReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_STARTED");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_ENDED");
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mReceiver, intentFilter);
    }
    
    private boolean closeCryptoSession(final byte[] array) {
        try {
            this.mDrm.closeSession(array);
            this.mCryptoSessionCounter.decrementAndGet();
            return true;
        }
        catch (Throwable t) {
            Log.w(BaseCryptoManager.TAG, t, "closeCryptoSessions failed !");
            return false;
        }
    }
    
    private void detectAndReportMediaDrmResetWithCryptoOutput(final int n, final int n2) {
        if (!AndroidUtils.isAndroid6AndHihger() && n > 0 && n2 < n) {
            CryptoErrorManager.INSTANCE.mediaDrmFailure(ErrorSource.msl, StatusCode.DRM_FAILURE_MEDIADRM_RESET, null);
        }
    }
    
    private CryptoManager$CryptoSession doGetKeyRequestData() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //     3: ldc             "createCryptoSession:: before open session"
        //     5: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //     8: pop            
        //     9: new             Lcom/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession;
        //    12: dup            
        //    13: invokespecial   com/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession.<init>:()V
        //    16: astore_2       
        //    17: aload_2        
        //    18: aload_0        
        //    19: getfield        com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.mDrm:Landroid/media/MediaDrm;
        //    22: invokevirtual   android/media/MediaDrm.openSession:()[B
        //    25: putfield        com/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession.sessionId:[B
        //    28: aload_0        
        //    29: getfield        com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.mCryptoSessionCounter:Ljava/util/concurrent/atomic/AtomicInteger;
        //    32: invokevirtual   java/util/concurrent/atomic/AtomicInteger.incrementAndGet:()I
        //    35: pop            
        //    36: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //    39: ldc             "createCryptoSession:: after open session"
        //    41: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    44: pop            
        //    45: aload_2        
        //    46: aload_0        
        //    47: getfield        com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.mDrm:Landroid/media/MediaDrm;
        //    50: aload_2        
        //    51: getfield        com/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession.sessionId:[B
        //    54: aload_0        
        //    55: getfield        com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.init:[B
        //    58: ldc             "application/xml"
        //    60: iconst_2       
        //    61: new             Ljava/util/HashMap;
        //    64: dup            
        //    65: invokespecial   java/util/HashMap.<init>:()V
        //    68: invokevirtual   android/media/MediaDrm.getKeyRequest:([B[BLjava/lang/String;ILjava/util/HashMap;)Landroid/media/MediaDrm$KeyRequest;
        //    71: invokevirtual   android/media/MediaDrm$KeyRequest.getData:()[B
        //    74: putfield        com/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession.keyRequestData:[B
        //    77: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    80: ifeq            117
        //    83: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //    86: new             Ljava/lang/StringBuilder;
        //    89: dup            
        //    90: invokespecial   java/lang/StringBuilder.<init>:()V
        //    93: ldc             "keyRequestData: |"
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    98: aload_2        
        //    99: invokevirtual   com/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession.getKeyRequestDataAsString:()Ljava/lang/String;
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: ldc             "|"
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   113: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   116: pop            
        //   117: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //   120: ldc_w           "Number of all opened crypto sessions: %d"
        //   123: iconst_1       
        //   124: anewarray       Ljava/lang/Object;
        //   127: dup            
        //   128: iconst_0       
        //   129: aload_0        
        //   130: getfield        com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.mCryptoSessionCounter:Ljava/util/concurrent/atomic/AtomicInteger;
        //   133: invokevirtual   java/util/concurrent/atomic/AtomicInteger.get:()I
        //   136: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   139: aastore        
        //   140: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //   143: pop            
        //   144: aload_2        
        //   145: areturn        
        //   146: astore_1       
        //   147: aconst_null    
        //   148: astore_2       
        //   149: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //   152: aload_1        
        //   153: ldc_w           "createCryptoSession failed !"
        //   156: iconst_0       
        //   157: anewarray       Ljava/lang/Object;
        //   160: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //   163: pop            
        //   164: getstatic       com/netflix/mediaclient/service/error/crypto/CryptoErrorManager.INSTANCE:Lcom/netflix/mediaclient/service/error/crypto/CryptoErrorManager;
        //   167: getstatic       com/netflix/mediaclient/service/error/crypto/ErrorSource.msl:Lcom/netflix/mediaclient/service/error/crypto/ErrorSource;
        //   170: getstatic       com/netflix/mediaclient/StatusCode.DRM_FAILURE_MEDIADRM_GET_KEY_REQUEST:Lcom/netflix/mediaclient/StatusCode;
        //   173: aload_1        
        //   174: invokevirtual   com/netflix/mediaclient/service/error/crypto/CryptoErrorManager.mediaDrmFailure:(Lcom/netflix/mediaclient/service/error/crypto/ErrorSource;Lcom/netflix/mediaclient/StatusCode;Ljava/lang/Throwable;)V
        //   177: aload_2        
        //   178: areturn        
        //   179: astore_1       
        //   180: goto            149
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      17     146    149    Ljava/lang/Throwable;
        //  17     117    179    183    Ljava/lang/Throwable;
        //  117    144    179    183    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0117:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected static String getCipherAlgorithm() {
        return "AES/CBC/NoPadding";
    }
    
    protected static String getMacAlgorithm() {
        return "HmacSHA256";
    }
    
    private CryptoManager$CryptoSession getPendingCryptoSession(final WidevineKeyRequestData widevineKeyRequestData) {
        if (!(widevineKeyRequestData instanceof AndroidWidevineKeyRequestData)) {
            throw new IllegalStateException("Not original request! But: " + widevineKeyRequestData);
        }
        final AndroidWidevineKeyRequestData androidWidevineKeyRequestData = (AndroidWidevineKeyRequestData)widevineKeyRequestData;
        if (this.mPendingKeyRequestData != androidWidevineKeyRequestData) {
            throw new IllegalStateException("Not original request! Instead of: " + this.mPendingKeyRequestData + ", we got: " + widevineKeyRequestData);
        }
        this.mPendingKeyRequestData = null;
        final CryptoManager$CryptoSession keyRequestDataCryptoSession = androidWidevineKeyRequestData.getKeyRequestDataCryptoSession();
        if (keyRequestDataCryptoSession == null) {
            throw new IllegalArgumentException("updateKeyResponse:: pending crypto session can NOT be null!");
        }
        if (androidWidevineKeyRequestData.getKeyRequestData() != null && !androidWidevineKeyRequestData.getKeyRequestData().equals(androidWidevineKeyRequestData.getKeyRequestDataCryptoSession().getKeyRequestDataAsString())) {
            if (Log.isLoggable()) {
                Log.w(BaseCryptoManager.TAG, "updateKeyResponse:: Key request is NOT as expected!");
                Log.dumpVerbose(BaseCryptoManager.TAG, "updateKeyResponse:: Original: |" + androidWidevineKeyRequestData.getKeyRequestDataCryptoSession().getKeyRequestDataAsString() + " |");
                Log.dumpVerbose(BaseCryptoManager.TAG, "updateKeyResponse:: we got  : |" + androidWidevineKeyRequestData.getKeyRequestData() + " |");
            }
            return keyRequestDataCryptoSession;
        }
        Log.d(BaseCryptoManager.TAG, "Key request is as expected.");
        return keyRequestDataCryptoSession;
    }
    
    private void removeReceiver() {
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mReceiver);
    }
    
    private void removeSessionKeys(final CryptoManager$CryptoSession cryptoManager$CryptoSession) {
        if (cryptoManager$CryptoSession != null && this.mDrm != null && cryptoManager$CryptoSession.sessionId != null) {
            if (cryptoManager$CryptoSession.keySetId == null) {
                Log.d(BaseCryptoManager.TAG, "Nothing to remove! Skip!");
            }
            else {
                Log.d(BaseCryptoManager.TAG, "removeSessionKeys");
                try {
                    this.mDrm.removeKeys(cryptoManager$CryptoSession.sessionId);
                }
                catch (Exception ex) {
                    Log.e(BaseCryptoManager.TAG, ex, "removeSessionKeys ", new Object[0]);
                }
            }
        }
    }
    
    private void reportError(final StatusCode statusCode, final Throwable t) {
        CryptoErrorManager.INSTANCE.mediaDrmFailure(ErrorSource.msl, statusCode, t);
    }
    
    private void showProperties() {
        if (Log.isLoggable()) {
            Log.d(BaseCryptoManager.TAG, "vendor: " + this.mDrm.getPropertyString("vendor"));
            Log.d(BaseCryptoManager.TAG, "version: " + this.mDrm.getPropertyString("version"));
            Log.d(BaseCryptoManager.TAG, "description: " + this.mDrm.getPropertyString("description"));
            Log.d(BaseCryptoManager.TAG, "deviceId: " + Arrays.toString(this.mDrm.getPropertyByteArray("deviceUniqueId")));
            Log.d(BaseCryptoManager.TAG, "algorithms: " + this.mDrm.getPropertyString("algorithms"));
            Log.d(BaseCryptoManager.TAG, "security level: " + this.mDrm.getPropertyString("securityLevel"));
            Log.d(BaseCryptoManager.TAG, "system ID: " + this.mDrm.getPropertyString("systemId"));
            Log.i(BaseCryptoManager.TAG, "provisioningId: " + Arrays.toString(this.mDrm.getPropertyByteArray("provisioningUniqueId")));
        }
    }
    
    public boolean closeCryptoSession(final CryptoManager$CryptoSession cryptoManager$CryptoSession) {
        if (cryptoManager$CryptoSession != null && this.mDrm != null && cryptoManager$CryptoSession.sessionId != null) {
            if (Log.isLoggable()) {
                Log.d(BaseCryptoManager.TAG, "closeCryptoSession " + cryptoManager$CryptoSession);
            }
            return this.closeCryptoSession(cryptoManager$CryptoSession.sessionId);
        }
        return false;
    }
    
    protected void closeSessionAndRemoveKeys(final CryptoManager$CryptoSession cryptoManager$CryptoSession) {
        // monitorenter(this)
        if (cryptoManager$CryptoSession == null) {
            return;
        }
        try {
            this.removeSessionKeys(cryptoManager$CryptoSession);
            this.closeCryptoSession(cryptoManager$CryptoSession);
        }
        finally {
        }
        // monitorexit(this)
    }
    
    protected MediaDrm createMediaDrm() {
        return new MediaDrm(this.getSchemeUUID());
    }
    
    @TargetApi(23)
    public byte[] decrypt(final CryptoManager$CryptoSession cryptoManager$CryptoSession, final CryptoManager$KeyId cryptoManager$KeyId, final byte[] array, final byte[] array2) {
        final byte[] array3 = null;
        while (true) {
            try {
                final byte[] doDecrypt = this.doDecrypt(cryptoManager$CryptoSession, cryptoManager$KeyId, array, array2);
                if (doDecrypt != null) {
                    return doDecrypt;
                }
            }
            catch (Throwable t) {
                byte[] doDecrypt = array3;
                if (!AndroidUtils.isAndroid6AndHihger()) {
                    continue;
                }
                doDecrypt = array3;
                if (t instanceof MediaDrmResetException) {
                    this.reportError(StatusCode.DRM_FAILURE_MEDIADRM_RESET, t);
                    doDecrypt = array3;
                }
                continue;
            }
            break;
        }
        return BaseCryptoManager.EMPTY_RETURN_ARRAY;
    }
    
    public void destroy() {
        synchronized (this) {
            this.removeReceiver();
            if (this.mDrm != null) {
                this.mDrm.release();
            }
        }
    }
    
    protected byte[] doDecrypt(final CryptoManager$CryptoSession cryptoManager$CryptoSession, final CryptoManager$KeyId cryptoManager$KeyId, final byte[] array, final byte[] array2) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession(cryptoManager$CryptoSession);
        if (mediaDrmCryptoSession == null) {
            Log.w(BaseCryptoManager.TAG, "decrypt - session NOT found!");
            return null;
        }
        if (cryptoManager$KeyId == null) {
            Log.w(BaseCryptoManager.TAG, "decrypt - kce is null!");
            return null;
        }
        try {
            return CryptoUtils.unpadPerPKCS5Padding(mediaDrmCryptoSession.decrypt(cryptoManager$KeyId.get(), array, array2), 16);
        }
        catch (Throwable t) {
            Log.e(BaseCryptoManager.TAG, t, "Failed to decrypt ", new Object[0]);
            this.reportError(StatusCode.DRM_FAILURE_MEDIADRM_DECRYPT, t);
            return null;
        }
    }
    
    protected byte[] doEncrypt(final CryptoManager$CryptoSession cryptoManager$CryptoSession, final CryptoManager$KeyId cryptoManager$KeyId, final byte[] array, byte[] array2) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession(cryptoManager$CryptoSession);
        if (mediaDrmCryptoSession == null) {
            Log.w(BaseCryptoManager.TAG, "encrypt - session NOT found!");
            return null;
        }
        if (cryptoManager$KeyId == null) {
            Log.w(BaseCryptoManager.TAG, "encrypt - kce is null!");
            return null;
        }
        try {
            final int length = array.length;
            byte[] encrypt;
            if (length <= 16384) {
                encrypt = mediaDrmCryptoSession.encrypt(cryptoManager$KeyId.get(), CryptoUtils.padPerPKCS5Padding(array, 16), array2);
            }
            else {
                Log.w(BaseCryptoManager.TAG, "encrypt piecewise");
                final byte[] array3 = new byte[16 - length % 16 + length];
                int n = 0;
                int n2;
                byte[] copyOfRange;
                do {
                    final byte[] encrypt2 = mediaDrmCryptoSession.encrypt(cryptoManager$KeyId.get(), Arrays.copyOfRange(array, n, n + 16384), array2);
                    copyOfRange = Arrays.copyOfRange(encrypt2, 16368, 16384);
                    System.arraycopy(encrypt2, 0, array3, n, 16384);
                    n2 = (n += 16384);
                    array2 = copyOfRange;
                } while (length - n2 > 16384);
                encrypt = array3;
                if (length - n2 > 0) {
                    if (Log.isLoggable()) {
                        Log.d(BaseCryptoManager.TAG, "partial chunk at offset " + n2 + ",size " + (length - n2));
                    }
                    final byte[] encrypt3 = mediaDrmCryptoSession.encrypt(cryptoManager$KeyId.get(), CryptoUtils.padPerPKCS5Padding(Arrays.copyOfRange(array, n2, length), 16), copyOfRange);
                    System.arraycopy(encrypt3, 0, array3, n2, encrypt3.length);
                    encrypt = array3;
                }
            }
            this.detectAndReportMediaDrmResetWithCryptoOutput(length, encrypt.length);
            return encrypt;
        }
        catch (Throwable t) {
            Log.e(BaseCryptoManager.TAG, t, "Failed to encrypt ", new Object[0]);
            this.reportError(StatusCode.DRM_FAILURE_MEDIADRM_ENCRYPT, t);
            return null;
        }
    }
    
    protected void dumpKeyReqyest(final byte[] array) {
        if (array != null) {
            if (Log.isLoggable()) {
                Log.d(BaseCryptoManager.TAG, "key request created: " + MediaDrmUtils.safeBase64Encode(array));
            }
            return;
        }
        Log.w(BaseCryptoManager.TAG, "key request returned null");
    }
    
    @TargetApi(23)
    public byte[] encrypt(final CryptoManager$CryptoSession cryptoManager$CryptoSession, final CryptoManager$KeyId cryptoManager$KeyId, final byte[] array, final byte[] array2) {
        Log.d(BaseCryptoManager.TAG, "BaseCryptoManager::encrypt...");
        final byte[] array3 = null;
        while (true) {
            try {
                final byte[] doEncrypt = this.doEncrypt(cryptoManager$CryptoSession, cryptoManager$KeyId, array, array2);
                if (doEncrypt != null) {
                    return doEncrypt;
                }
            }
            catch (Throwable t) {
                byte[] doEncrypt = array3;
                if (!AndroidUtils.isAndroid6AndHihger()) {
                    continue;
                }
                doEncrypt = array3;
                if (t instanceof MediaDrmResetException) {
                    this.reportError(StatusCode.DRM_FAILURE_MEDIADRM_RESET, t);
                    doEncrypt = array3;
                }
                continue;
            }
            break;
        }
        return BaseCryptoManager.EMPTY_RETURN_ARRAY;
    }
    
    protected MediaDrm$CryptoSession findMediaDrmCryptoSession(final CryptoManager$CryptoSession cryptoManager$CryptoSession) {
        if (cryptoManager$CryptoSession == null) {
            return null;
        }
        final byte[] sessionId = cryptoManager$CryptoSession.sessionId;
        if (sessionId == null) {
            Log.w(BaseCryptoManager.TAG, "findMediaDrmCryptoSession:: session found but without session ID: " + cryptoManager$CryptoSession);
            return null;
        }
        return this.mDrm.getCryptoSession(sessionId, getCipherAlgorithm(), getMacAlgorithm());
    }
    
    public WidevineKeyRequestData getKeyRequestData() {
        // monitorexit(this)
        while (true) {
            Label_0065: {
                synchronized (this) {
                    if (this.mPendingKeyRequestData != null) {
                        break Label_0065;
                    }
                    Log.d(BaseCryptoManager.TAG, "Key request does not exist, create it!");
                    if (this.doGetKeyRequestData() == null) {
                        throw new IllegalStateException("Crypto session can not be null after ");
                    }
                }
                final CryptoManager$CryptoSession cryptoManager$CryptoSession;
                this.mPendingKeyRequestData = new AndroidWidevineKeyRequestData(cryptoManager$CryptoSession);
                return this.mPendingKeyRequestData;
            }
            Log.d(BaseCryptoManager.TAG, "Key request is already pending, return it");
            continue;
        }
    }
    
    protected abstract String getLogTag();
    
    protected abstract UUID getSchemeUUID();
    
    protected abstract void init();
    
    protected abstract void load();
    
    public void onEvent(final MediaDrm mediaDrm, final byte[] array, final int n, final int n2, final byte[] array2) {
        if (n == 1) {
            Log.d(BaseCryptoManager.TAG, "Provisioning is required");
        }
        else {
            if (n == 2) {
                Log.d(BaseCryptoManager.TAG, "MediaDrm event: Key required");
                return;
            }
            if (n == 3) {
                Log.d(BaseCryptoManager.TAG, "MediaDrm event: Key expired");
                return;
            }
            if (n == 4) {
                if (Log.isLoggable()) {
                    Log.d(BaseCryptoManager.TAG, "MediaDrm event: Vendor defined: " + n);
                }
            }
            else {
                if (n == 5) {
                    if (Log.isLoggable()) {
                        Log.e(BaseCryptoManager.TAG, "EVENT_SESSION_RECLAIMED event.");
                    }
                    this.closeCryptoSession(array);
                    this.mCallback.drmResoureReclaimed();
                    return;
                }
                if (Log.isLoggable()) {
                    Log.e(BaseCryptoManager.TAG, "unknown MediaDrm event " + n);
                }
            }
        }
    }
    
    protected void reset() {
    }
    // monitorenter(this)
    // monitorexit(this)
    
    public void resetCryptoFactory() {
        Log.d(BaseCryptoManager.TAG, "resetCryptoFactory");
        this.reset();
        this.init();
    }
    
    public CryptoManager$CryptoSession restoreCryptoSession(final CryptoManager$KeyId keySetId) {
        try {
            final CryptoManager$CryptoSession cryptoManager$CryptoSession = new CryptoManager$CryptoSession();
            cryptoManager$CryptoSession.keySetId = keySetId;
            cryptoManager$CryptoSession.sessionId = this.mDrm.openSession();
            this.mDrm.restoreKeys(cryptoManager$CryptoSession.sessionId, cryptoManager$CryptoSession.keySetId.get());
            if (Log.isLoggable()) {
                Log.d(BaseCryptoManager.TAG, "restoreKeysToSession succeeded:: " + cryptoManager$CryptoSession.keySetId);
            }
            return cryptoManager$CryptoSession;
        }
        catch (Throwable t) {
            Log.e(BaseCryptoManager.TAG, "Failed to restore keys to DRM session");
            return null;
        }
    }
    
    protected abstract void setSecurityLevel();
    
    public byte[] sign(final CryptoManager$CryptoSession cryptoManager$CryptoSession, final CryptoManager$KeyId cryptoManager$KeyId, final byte[] array) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession(cryptoManager$CryptoSession);
        if (mediaDrmCryptoSession == null) {
            Log.w(BaseCryptoManager.TAG, "sign - session NOT found!");
            return null;
        }
        if (cryptoManager$KeyId == null) {
            Log.w(BaseCryptoManager.TAG, "sign - kch is null!");
            return null;
        }
        try {
            return mediaDrmCryptoSession.sign(cryptoManager$KeyId.get(), array);
        }
        catch (Throwable t) {
            Log.e(BaseCryptoManager.TAG, t, "Failed to sign message ", new Object[0]);
            this.reportError(StatusCode.DRM_FAILURE_MEDIADRM_SIGN, t);
            return BaseCryptoManager.EMPTY_RETURN_ARRAY;
        }
    }
    
    public CryptoManager$CryptoSession updateKeyResponse(final WidevineKeyRequestData p0, final byte[] p1, final CryptoManager$KeyId p2, final CryptoManager$KeyId p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //     5: ldc_w           "Provide key response..."
        //     8: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    11: pop            
        //    12: aload_0        
        //    13: aload_1        
        //    14: invokespecial   com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.getPendingCryptoSession:(Lcom/netflix/msl/keyx/WidevineKeyRequestData;)Lcom/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession;
        //    17: astore_1       
        //    18: aload_0        
        //    19: getfield        com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.mDrm:Landroid/media/MediaDrm;
        //    22: aload_1        
        //    23: getfield        com/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession.sessionId:[B
        //    26: aload_2        
        //    27: invokevirtual   android/media/MediaDrm.provideKeyResponse:([B[B)[B
        //    30: astore_2       
        //    31: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //    34: ldc_w           "Save keys..."
        //    37: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    40: pop            
        //    41: aload_2        
        //    42: ifnonnull       61
        //    45: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //    48: ldc_w           "Something is wrong, this should not happen! KeySetId is null!"
        //    51: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    54: pop            
        //    55: aconst_null    
        //    56: astore_1       
        //    57: aload_0        
        //    58: monitorexit    
        //    59: aload_1        
        //    60: areturn        
        //    61: aload_1        
        //    62: new             Lcom/netflix/mediaclient/service/configuration/crypto/CryptoManager$KeyId;
        //    65: dup            
        //    66: aload_2        
        //    67: invokespecial   com/netflix/mediaclient/service/configuration/crypto/CryptoManager$KeyId.<init>:([B)V
        //    70: putfield        com/netflix/mediaclient/service/configuration/crypto/CryptoManager$CryptoSession.keySetId:Lcom/netflix/mediaclient/service/configuration/crypto/CryptoManager$KeyId;
        //    73: goto            57
        //    76: astore_2       
        //    77: getstatic       com/netflix/mediaclient/service/configuration/crypto/BaseCryptoManager.TAG:Ljava/lang/String;
        //    80: aload_2        
        //    81: ldc_w           "Failed to provide key response"
        //    84: iconst_0       
        //    85: anewarray       Ljava/lang/Object;
        //    88: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //    91: pop            
        //    92: getstatic       com/netflix/mediaclient/service/error/crypto/CryptoErrorManager.INSTANCE:Lcom/netflix/mediaclient/service/error/crypto/CryptoErrorManager;
        //    95: getstatic       com/netflix/mediaclient/service/error/crypto/ErrorSource.msl:Lcom/netflix/mediaclient/service/error/crypto/ErrorSource;
        //    98: getstatic       com/netflix/mediaclient/StatusCode.DRM_FAILURE_MEDIADRM_PROVIDE_KEY_RESPONSE:Lcom/netflix/mediaclient/StatusCode;
        //   101: aload_2        
        //   102: invokevirtual   com/netflix/mediaclient/service/error/crypto/CryptoErrorManager.mediaDrmFailure:(Lcom/netflix/mediaclient/service/error/crypto/ErrorSource;Lcom/netflix/mediaclient/StatusCode;Ljava/lang/Throwable;)V
        //   105: goto            57
        //   108: astore_1       
        //   109: aload_0        
        //   110: monitorexit    
        //   111: aload_1        
        //   112: athrow         
        //   113: astore_2       
        //   114: aconst_null    
        //   115: astore_1       
        //   116: goto            77
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      12     108    113    Any
        //  12     18     113    119    Ljava/lang/Throwable;
        //  12     18     108    113    Any
        //  18     41     76     77     Ljava/lang/Throwable;
        //  18     41     108    113    Any
        //  45     55     76     77     Ljava/lang/Throwable;
        //  45     55     108    113    Any
        //  61     73     76     77     Ljava/lang/Throwable;
        //  61     73     108    113    Any
        //  77     105    108    113    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0057:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean verify(final CryptoManager$CryptoSession cryptoManager$CryptoSession, final CryptoManager$KeyId cryptoManager$KeyId, final byte[] array, final byte[] array2) {
        boolean verify = false;
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession(cryptoManager$CryptoSession);
        if (mediaDrmCryptoSession == null) {
            Log.w(BaseCryptoManager.TAG, "verify - session NOT found!");
        }
        else {
            if (cryptoManager$KeyId == null) {
                Log.w(BaseCryptoManager.TAG, "verify - kch is null!");
                return false;
            }
            try {
                final boolean b = verify = mediaDrmCryptoSession.verify(cryptoManager$KeyId.get(), array, array2);
                if (Log.isLoggable()) {
                    Log.d(BaseCryptoManager.TAG, "Message is verified: " + b);
                    return b;
                }
            }
            catch (Throwable t) {
                Log.e(BaseCryptoManager.TAG, t, "Failed to verify message ", new Object[0]);
                this.reportError(StatusCode.DRM_FAILURE_MEDIADRM_VERIFY, t);
                return false;
            }
        }
        return verify;
    }
}
