// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import com.netflix.mediaclient.service.player.SessionParams;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.Arrays;
import java.util.Iterator;
import com.netflix.mediaclient.util.MediaDrmUtils;
import com.netflix.mediaclient.Log;
import android.os.Looper;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import java.util.List;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface;
import android.os.Handler;
import java.util.Map;
import android.content.Context;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface$ManifestCacheCallback;
import android.media.MediaDrm$OnEventListener;

public class NfDrmManager implements MediaDrm$OnEventListener, NfDrmManagerInterface, NfManifestCachePlaybackInterface$ManifestCacheCallback
{
    private static final int DEFAULT_DRM_SESSION_COUNT = 8;
    private static final long DRM_PREFETCH_SESSION_LIFE_MS = 7200000L;
    private static final int MAX_DRM_SESSION_USED = 20;
    static final int MSG_ERROR = 0;
    static final int MSG_KEYS = 2;
    static final int MSG_KEYS_LDL = 3;
    static final int MSG_KEYS_OFFLINE = 4;
    static final int MSG_PROVISION = 1;
    private static final int RESERVED_DRM_SESSION_COUNT = 3;
    private static final String TAG = "NfPlayerDrmManager";
    private BladeRunnerClient mBladeRunnerClient;
    private Context mContext;
    private boolean mDeviceHasLowDrmResource;
    private boolean mDisableLicensePreftech;
    private Map<Long, NfDrmSession> mDrmSessionMap;
    private Handler mMainHandler;
    private NfManifestCachePlaybackInterface mManifestCache;
    private int mMaxDrmSesionCount;
    private MediaDrm mMediaDrm;
    private List<Triple<Long, Integer, PlayContext>> mWaitToPrepareList;
    private Handler mWorkHandler;
    
    public NfDrmManager(final Handler p0, final Looper p1, final NfManifestCachePlaybackInterface p2, final BladeRunnerClient p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore          8
        //     3: aload_0        
        //     4: invokespecial   java/lang/Object.<init>:()V
        //     7: aload_0        
        //     8: iconst_5       
        //     9: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mMaxDrmSesionCount:I
        //    12: aload_0        
        //    13: new             Ljava/util/HashMap;
        //    16: dup            
        //    17: invokespecial   java/util/HashMap.<init>:()V
        //    20: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mDrmSessionMap:Ljava/util/Map;
        //    23: aload_0        
        //    24: new             Ljava/util/ArrayList;
        //    27: dup            
        //    28: invokespecial   java/util/ArrayList.<init>:()V
        //    31: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mWaitToPrepareList:Ljava/util/List;
        //    34: aload_0        
        //    35: aload_1        
        //    36: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mMainHandler:Landroid/os/Handler;
        //    39: aload_0        
        //    40: aload           4
        //    42: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mBladeRunnerClient:Lcom/netflix/mediaclient/service/player/bladerunnerclient/BladeRunnerClient;
        //    45: aload_0        
        //    46: aload_3        
        //    47: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mManifestCache:Lcom/netflix/mediaclient/service/player/manifest/NfManifestCachePlaybackInterface;
        //    50: aload_0        
        //    51: new             Lcom/netflix/mediaclient/service/player/drm/NfDrmManager$1;
        //    54: dup            
        //    55: aload_0        
        //    56: aload_2        
        //    57: invokespecial   com/netflix/mediaclient/service/player/drm/NfDrmManager$1.<init>:(Lcom/netflix/mediaclient/service/player/drm/NfDrmManager;Landroid/os/Looper;)V
        //    60: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mWorkHandler:Landroid/os/Handler;
        //    63: aload_0        
        //    64: aload_0        
        //    65: invokestatic    com/netflix/mediaclient/util/MediaDrmUtils.getNewMediaDrmInstance:(Landroid/media/MediaDrm$OnEventListener;)Landroid/media/MediaDrm;
        //    68: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mMediaDrm:Landroid/media/MediaDrm;
        //    71: aload_0        
        //    72: getfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mMediaDrm:Landroid/media/MediaDrm;
        //    75: ldc             "maxNumberOfSessions"
        //    77: invokevirtual   android/media/MediaDrm.getPropertyString:(Ljava/lang/String;)Ljava/lang/String;
        //    80: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
        //    83: invokevirtual   java/lang/Integer.intValue:()I
        //    86: istore          6
        //    88: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    91: ifeq            120
        //    94: ldc             "NfPlayerDrmManager"
        //    96: new             Ljava/lang/StringBuilder;
        //    99: dup            
        //   100: invokespecial   java/lang/StringBuilder.<init>:()V
        //   103: ldc             "maxNumberOfSessions is "
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: iload           6
        //   110: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   113: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   116: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   119: pop            
        //   120: iload           5
        //   122: ifne            132
        //   125: iload           6
        //   127: bipush          8
        //   129: if_icmpge       275
        //   132: iconst_1       
        //   133: istore          7
        //   135: aload_0        
        //   136: iload           7
        //   138: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mDisableLicensePreftech:Z
        //   141: iload           5
        //   143: ifeq            281
        //   146: ldc             "NfPlayerDrmManager"
        //   148: ldc             "license prefetch is disabled by config"
        //   150: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   153: pop            
        //   154: aload_0        
        //   155: iload           6
        //   157: iconst_3       
        //   158: isub           
        //   159: bipush          20
        //   161: invokestatic    java/lang/Math.min:(II)I
        //   164: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mMaxDrmSesionCount:I
        //   167: aload_0        
        //   168: getfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mMaxDrmSesionCount:I
        //   171: bipush          8
        //   173: if_icmpgt       299
        //   176: iload           8
        //   178: istore          5
        //   180: aload_0        
        //   181: iload           5
        //   183: putfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mDeviceHasLowDrmResource:Z
        //   186: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   189: ifeq            199
        //   192: aload_0        
        //   193: getfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mMediaDrm:Landroid/media/MediaDrm;
        //   196: invokestatic    com/netflix/mediaclient/service/player/drm/NfDrmManager.dumpMediaDrmProperty:(Landroid/media/MediaDrm;)V
        //   199: return         
        //   200: astore_1       
        //   201: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   204: ifeq            71
        //   207: ldc             "NfPlayerDrmManager"
        //   209: new             Ljava/lang/StringBuilder;
        //   212: dup            
        //   213: invokespecial   java/lang/StringBuilder.<init>:()V
        //   216: ldc             "fail to instantiate MediaDrm "
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: aload_1        
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   225: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   228: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   231: pop            
        //   232: goto            71
        //   235: astore_1       
        //   236: bipush          8
        //   238: istore          6
        //   240: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   243: ifeq            272
        //   246: ldc             "NfPlayerDrmManager"
        //   248: new             Ljava/lang/StringBuilder;
        //   251: dup            
        //   252: invokespecial   java/lang/StringBuilder.<init>:()V
        //   255: ldc             "default maxNumberOfSessions is "
        //   257: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   260: iload           6
        //   262: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   265: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   268: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   271: pop            
        //   272: goto            120
        //   275: iconst_0       
        //   276: istore          7
        //   278: goto            135
        //   281: aload_0        
        //   282: getfield        com/netflix/mediaclient/service/player/drm/NfDrmManager.mDisableLicensePreftech:Z
        //   285: ifeq            154
        //   288: ldc             "NfPlayerDrmManager"
        //   290: ldc             "license prefetch is disabled due to insufficient session"
        //   292: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   295: pop            
        //   296: goto            154
        //   299: iconst_0       
        //   300: istore          5
        //   302: goto            180
        //   305: astore_1       
        //   306: goto            240
        //   309: astore_1       
        //   310: goto            201
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  63     71     309    313    Landroid/media/UnsupportedSchemeException;
        //  63     71     200    201    Landroid/media/NotProvisionedException;
        //  71     88     235    240    Ljava/lang/Exception;
        //  88     120    305    309    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 145, Size: 145
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    private void clearAll(final boolean b) {
        if (Log.isLoggable()) {
            final StringBuilder append = new StringBuilder().append("clear session ");
            String s;
            if (b) {
                s = "exception ones in use";
            }
            else {
                s = "all";
            }
            Log.d("NfPlayerDrmManager", append.append(s).toString());
        }
        this.mWorkHandler.post((Runnable)new NfDrmManager$3(this, b));
    }
    
    static void dumpKeyStatus(final MediaDrm mediaDrm, final byte[] array) {
        MediaDrmUtils.dumpKeyStatus("NfPlayerDrmManager", mediaDrm, array);
    }
    
    private static void dumpMediaDrmProperty(final MediaDrm mediaDrm) {
        int i = 0;
        final String[] array = { "vendor", "version", "description", "deviceUniqueId", "algorithms", "securityLevel", "systemId", "privacyMode", "sessionSharing", "usageReportingSupport", "appId", "hdcpLevel", "maxHdcpLevel" };
        Log.d("NfPlayerDrmManager", "===== MediaDrm property ======");
    Label_0140_Outer:
        while (i < array.length) {
            final String s = array[i];
            while (true) {
                try {
                    Log.d("NfPlayerDrmManager", s + " : " + mediaDrm.getPropertyString(s));
                    ++i;
                    continue Label_0140_Outer;
                }
                catch (Exception ex) {
                    Log.d("NfPlayerDrmManager", s + " : ");
                    continue;
                }
                break;
            }
            break;
        }
        Log.d("NfPlayerDrmManager", "===== End of MediaDrm property ======");
    }
    
    private PlayContext getAssocaitedPlayContext(final long n) {
        for (final Triple<Long, Integer, PlayContext> triple : this.mWaitToPrepareList) {
            if (triple.first.equals(n)) {
                return triple.third;
            }
        }
        return PlayContext.EMPTY_CONTEXT;
    }
    
    private void purgeCachedSessions(final int n) {
        int i = 0;
        if (!this.mDrmSessionMap.isEmpty()) {
            if (Log.isLoggable()) {
                Log.d("NfPlayerDrmManager", "purgeCachedSessions has  " + this.mDrmSessionMap.size() + ", target is " + n);
            }
            final CacheEntry[] array = new CacheEntry[this.mDrmSessionMap.size()];
            final Iterator<Map.Entry<Long, NfDrmSession>> iterator = this.mDrmSessionMap.entrySet().iterator();
            int n2 = 0;
            while (iterator.hasNext()) {
                final Map.Entry<Long, NfDrmSession> entry = iterator.next();
                final NfDrmSession nfDrmSession = entry.getValue();
                array[n2] = new CacheEntry(entry.getKey(), nfDrmSession.getPriority(), nfDrmSession.getSessionAgeInMs(), nfDrmSession.getInUse());
                ++n2;
            }
            if (n2 > n) {
                Arrays.sort(array, 0, n2);
                while (i < n2 - n) {
                    final Long id = array[i].getId();
                    this.removeSession(id);
                    if (Log.isLoggable()) {
                        Log.d("NfPlayerDrmManager", "purgeCachedSessions remove a cached session " + id);
                    }
                    ++i;
                }
            }
        }
    }
    
    private void purgeStaleSessions() {
        if (!this.mDrmSessionMap.isEmpty()) {
            final CacheEntry[] array = new CacheEntry[this.mDrmSessionMap.size()];
            final Iterator<Map.Entry<Long, NfDrmSession>> iterator = this.mDrmSessionMap.entrySet().iterator();
            int n = 0;
        Label_0147_Outer:
            while (iterator.hasNext()) {
                final Map.Entry<Long, NfDrmSession> entry = iterator.next();
                final NfDrmSession nfDrmSession = entry.getValue();
                if (nfDrmSession.getSessionAgeInMs() >= 7200000L) {
                    entry.getValue().close();
                    iterator.remove();
                    if (!Log.isLoggable()) {
                        continue Label_0147_Outer;
                    }
                    Log.d("NfPlayerDrmManager", "purgeStaleSessions remove a older session " + entry.getKey());
                }
                else if (nfDrmSession.isClosed()) {
                    entry.getValue().close();
                    iterator.remove();
                    if (!Log.isLoggable()) {
                        continue Label_0147_Outer;
                    }
                    Log.d("NfPlayerDrmManager", "purgeStaleSessions remove a closed session " + entry.getKey());
                }
                else {
                    final CacheEntry cacheEntry = new CacheEntry(entry.getKey(), nfDrmSession.getPriority(), nfDrmSession.getSessionAgeInMs(), nfDrmSession.getInUse());
                    final int n2 = n + 1;
                    array[n] = cacheEntry;
                    n = n2;
                }
                while (true) {
                    continue Label_0147_Outer;
                    continue;
                }
            }
        }
    }
    
    private void removeSession(final Long n) {
        final NfDrmSession nfDrmSession = this.mDrmSessionMap.get(n);
        if (nfDrmSession != null) {
            nfDrmSession.close();
            this.mDrmSessionMap.remove(n);
        }
    }
    
    public void clear(final Long n) {
        this.mWorkHandler.post((Runnable)new NfDrmManager$4(this, n));
    }
    
    public void clearAll() {
        this.clearAll(false);
    }
    
    public NfDrmSession getDrmSession(final Long n, final LicenseContext licenseContext) {
        this.purgeStaleSessions();
        Label_0063: {
            if (this.mDrmSessionMap.get(n) != null || licenseContext == null) {
                break Label_0063;
            }
            this.purgeCachedSessions(this.mMaxDrmSesionCount - 1);
            try {
                this.mDrmSessionMap.put(n, NfDrmSession.newWidevineDrmSession(this.mWorkHandler, this.mMediaDrm, (long)n, licenseContext));
                return this.mDrmSessionMap.get(n);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return this.mDrmSessionMap.get(n);
            }
        }
    }
    
    NfDrmSession getDrmSessionWithSessionId(final byte[] array) {
        synchronized (this.mDrmSessionMap) {
            if (!this.mDrmSessionMap.isEmpty()) {
                final Iterator<Map.Entry<Long, NfDrmSession>> iterator = this.mDrmSessionMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    final NfDrmSession nfDrmSession = iterator.next().getValue();
                    if (Arrays.equals(nfDrmSession.getSessionId(), array)) {
                        return nfDrmSession;
                    }
                }
            }
            return null;
        }
    }
    
    public void onEvent(final MediaDrm mediaDrm, final byte[] array, final int n, final int n2, final byte[] array2) {
        Log.logByteArrayRaw("NfPlayerDrmManager", "onEvent [" + n + "]", array);
        if (n == 3) {
            Log.d("NfPlayerDrmManager", "EVENT_KEY_EXPIRED - error");
            return;
        }
        if (n == 2) {
            Log.d("NfPlayerDrmManager", "EVENT_KEY_REQUIRED - to renew");
            this.mWorkHandler.post((Runnable)new NfDrmManager$5(this, array));
            return;
        }
        if (n == 5) {
            Log.d("NfPlayerDrmManager", "EVENT_SESSION_RECLAIMED.");
            this.mWorkHandler.post((Runnable)new NfDrmManager$6(this, array));
            return;
        }
        if (n == 4) {
            Log.d("NfPlayerDrmManager", "EVENT_VENDOR_DEFINED");
            return;
        }
        if (n == 1) {
            Log.d("NfPlayerDrmManager", "EVENT_PROVISION_REQUIRED -  shouldn't happen");
            return;
        }
        Log.d("NfPlayerDrmManager", "EVENT_UNKNOWN");
    }
    
    public void onManifestAvailable(final NfManifest nfManifest) {
        boolean b = false;
        final Long movieId = nfManifest.getMovieId();
        if (this.mDrmSessionMap.get(movieId) != null) {
            if (Log.isLoggable()) {
                Log.d("NfPlayerDrmManager", "onManifestAvailable, however there is already a session " + movieId);
            }
        }
        else {
            final long nanoTime = System.nanoTime();
            final PlayContext assocaitedPlayContext = this.getAssocaitedPlayContext(movieId);
            final LicenseContext licenseContext = new LicenseContext(Integer.toString(assocaitedPlayContext.getTrackId()), Long.toString(nanoTime), 0, NfDrmManagerInterface$LicenseType.LICENSE_TYPE_LDL, nfManifest.getDrmHeader(), new SessionParams(this.mContext, assocaitedPlayContext, ConnectivityUtils.getCurrentNetType(this.mContext)).getParams(), nfManifest.getLicenseLinkJson());
            if (!nfManifest.hasDrm() || this.getDrmSession((long)movieId, licenseContext) != null) {
                b = true;
            }
            if (b) {
                for (final Triple<Long, Integer, PlayContext> triple : this.mWaitToPrepareList) {
                    if (triple.first.equals(movieId)) {
                        this.mWaitToPrepareList.remove(triple);
                        if (Log.isLoggable()) {
                            Log.d("NfPlayerDrmManager", "onManifestAvailable, remove from waiting list " + movieId);
                            return;
                        }
                        break;
                    }
                }
            }
            else if (Log.isLoggable()) {
                Log.d("NfPlayerDrmManager", "can't create drm session for " + movieId);
            }
        }
    }
    
    public void onManifestError(final Long n) {
        if (Log.isLoggable()) {
            Log.d("NfPlayerDrmManager", "onManifestError " + n);
        }
    }
    
    public void onUiHidden() {
        this.clearAll(true);
    }
    
    public void prepare(final List<Triple<Long, Integer, PlayContext>> list) {
        if (this.mDisableLicensePreftech) {
            return;
        }
        this.mWorkHandler.post((Runnable)new NfDrmManager$2(this, list));
    }
    
    public void release() {
        this.clearAll();
    }
}
