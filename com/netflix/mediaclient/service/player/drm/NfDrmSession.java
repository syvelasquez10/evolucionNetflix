// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import java.util.HashMap;
import com.netflix.mediaclient.Log;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.util.MediaDrmUtils;
import android.media.MediaDrm;
import android.media.MediaCrypto;
import android.os.Handler;
import java.util.UUID;

public class NfDrmSession
{
    public static final int STATE_CLOSED = 1;
    public static final int STATE_ERROR = 0;
    public static final int STATE_OPENED = 3;
    public static final int STATE_OPENED_WITH_KEYS = 4;
    public static final int STATE_OPENING = 2;
    private static final String TAG = "NfPlayerDrmManager";
    public static final UUID WIDEVINE_UUID;
    private boolean inUse;
    private Exception lastException;
    private Handler mCallbackHandler;
    private int mIdHigh;
    private int mIdLow;
    private LicenseContext mLicenseContext;
    NfDrmSession$LicenseListener mLicenseListener;
    private NfDrmManagerInterface$LicenseType mLicenseType;
    Long mMovieId;
    private MediaCrypto mediaCrypto;
    private MediaDrm mediaDrm;
    private byte[] sessionId;
    private long sessionInitTimeMs;
    private int state;
    
    static {
        WIDEVINE_UUID = MediaDrmUtils.WIDEVINE_SCHEME;
    }
    
    public NfDrmSession(final Handler mCallbackHandler, final MediaDrm mediaDrm, final Long mMovieId, final LicenseContext mLicenseContext) {
        this.state = 1;
        this.mCallbackHandler = mCallbackHandler;
        this.mediaDrm = mediaDrm;
        this.mMovieId = mMovieId;
        this.mIdLow = (int)(mMovieId & -1L);
        this.mIdHigh = (int)(mMovieId >> 32 & -1L);
        this.mLicenseType = mLicenseContext.getLicenseType();
        this.mLicenseContext = mLicenseContext;
        this.state = 2;
        this.sessionInitTimeMs = System.currentTimeMillis();
        try {
            this.openSessionGetKeyRequest();
        }
        catch (NotProvisionedException ex) {
            this.postProvisionRequest();
        }
    }
    
    public static NfDrmSession newWidevineDrmSession(final Handler handler, final MediaDrm mediaDrm, final Long n, final LicenseContext licenseContext) {
        return new NfDrmSession(handler, mediaDrm, n, licenseContext);
    }
    
    private void onError(final Exception lastException) {
        if (Log.isLoggable()) {
            Log.d("NfPlayerDrmManager", "onError: " + lastException);
        }
        this.lastException = lastException;
        this.mCallbackHandler.obtainMessage(0, this.mIdHigh, this.mIdLow, (Object)lastException);
        if (this.state != 4) {
            this.state = 0;
        }
    }
    
    private void openSessionGetKeyRequest() {
        this.sessionId = this.mediaDrm.openSession();
        this.mediaCrypto = new MediaCrypto(NfDrmSession.WIDEVINE_UUID, this.sessionId);
        this.state = 3;
        this.postKeyRequest();
    }
    
    private void postProvisionRequest() {
        this.mCallbackHandler.obtainMessage(1, this.mIdHigh, this.mIdLow, (Object)this.mediaDrm.getProvisionRequest()).sendToTarget();
    }
    
    public void close() {
        this.inUse = false;
        if (this.state == 3 || this.state == 4 || this.state == 0) {
            this.mediaDrm.closeSession(this.sessionId);
        }
        this.mLicenseListener = null;
        this.state = 1;
    }
    
    public boolean getInUse() {
        return this.inUse;
    }
    
    public LicenseContext getLicenseContext() {
        return this.mLicenseContext;
    }
    
    public MediaCrypto getMediaCrypto() {
        return this.mediaCrypto;
    }
    
    int getPriority() {
        return 0;
    }
    
    public long getSessionAgeInMs() {
        return System.currentTimeMillis() - this.sessionInitTimeMs;
    }
    
    byte[] getSessionId() {
        return this.sessionId;
    }
    
    boolean isClosed() {
        return this.state == 1;
    }
    
    public boolean isLicenseAcquired() {
        return this.state == 4;
    }
    
    void postKeyRequest() {
        int n = 2;
        Log.d("NfPlayerDrmManager", "postKeyRequest start.");
        Label_0104: {
            if (!this.mLicenseType.equals(NfDrmManagerInterface$LicenseType.LICENSE_TYPE_OFFLINE)) {
                break Label_0104;
            }
            int n2 = 4;
            try {
                // iftrue(Label_0150:, this.state == 4 || !this.mLicenseType.equals((Object)NfDrmManagerInterface$LicenseType.LICENSE_TYPE_LDL))
                while (true) {
                    this.mLicenseContext.setChallenge(this.mediaDrm.getKeyRequest(this.sessionId, this.mLicenseContext.getDrmHeader(), new String(), n, new HashMap()).getData());
                    this.mCallbackHandler.obtainMessage(n2, this.mIdHigh, this.mIdLow, (Object)this.mLicenseContext).sendToTarget();
                    Log.d("NfPlayerDrmManager", "postKeyRequest succeeds.");
                    return;
                    this.mLicenseContext.setmLicenseType(NfDrmManagerInterface$LicenseType.LICENSE_TYPE_LDL);
                    Log.d("NfPlayerDrmManager", "request LDL.");
                    n2 = 3;
                    n = 1;
                    continue;
                    Label_0150: {
                        this.mLicenseContext.setmLicenseType(NfDrmManagerInterface$LicenseType.LICENSE_TYPE_STANDARD);
                    }
                    Log.d("NfPlayerDrmManager", "request STANDARD.");
                    n2 = 2;
                    n = 1;
                    continue;
                }
            }
            catch (NotProvisionedException ex) {
                Log.d("NfPlayerDrmManager", "keyRequest has NotProvisionedException.");
                throw ex;
            }
        }
    }
    
    void provideKeyResponse(final byte[] array) {
        Log.d("NfPlayerDrmManager", "provideKeyResponse start.");
        while (true) {
            try {
                this.mediaDrm.provideKeyResponse(this.sessionId, (byte[])array);
                this.state = 4;
                Log.d("NfPlayerDrmManager", "provideKeyResponse succeeds.");
                if (this.mLicenseListener != null) {
                    this.mLicenseListener.licenseAcquired(this.mMovieId);
                }
                NfDrmManager.dumpKeyStatus(this.mediaDrm, this.sessionId);
            }
            catch (Exception ex) {
                this.onError(ex);
                continue;
            }
            break;
        }
    }
    
    void provideProvisionResponse(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/netflix/mediaclient/service/player/drm/NfDrmSession.mediaDrm:Landroid/media/MediaDrm;
        //     4: aload_1        
        //     5: checkcast       [B
        //     8: invokevirtual   android/media/MediaDrm.provideProvisionResponse:([B)V
        //    11: aload_0        
        //    12: getfield        com/netflix/mediaclient/service/player/drm/NfDrmSession.state:I
        //    15: iconst_2       
        //    16: if_icmpne       23
        //    19: aload_0        
        //    20: invokespecial   com/netflix/mediaclient/service/player/drm/NfDrmSession.openSessionGetKeyRequest:()V
        //    23: return         
        //    24: astore_1       
        //    25: aload_0        
        //    26: aload_1        
        //    27: invokespecial   com/netflix/mediaclient/service/player/drm/NfDrmSession.onError:(Ljava/lang/Exception;)V
        //    30: return         
        //    31: astore_1       
        //    32: aload_0        
        //    33: aload_1        
        //    34: invokespecial   com/netflix/mediaclient/service/player/drm/NfDrmSession.onError:(Ljava/lang/Exception;)V
        //    37: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  0      11     24     31     Landroid/media/DeniedByServerException;
        //  19     23     31     38     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    
    public void setCachedLicenseIfAny() {
        final byte[] licenseData = this.mLicenseContext.getLicenseData();
        if (licenseData != null && licenseData.length > 0) {
            this.provideKeyResponse(licenseData);
        }
    }
    
    public void setInUse(final boolean inUse) {
        this.inUse = inUse;
    }
    
    public void setLicenseListener(final NfDrmSession$LicenseListener mLicenseListener) {
        this.mLicenseListener = mLicenseListener;
    }
}
