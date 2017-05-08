// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.IrisUtils;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.Coppola2Utils;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.mediaclient.ui.player.PostPlayRequestContext;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;
import com.netflix.mediaclient.service.job.NetflixJob;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import java.util.Iterator;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import java.io.Reader;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import android.content.BroadcastReceiver;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;

public class FalkorAgent extends ServiceAgent implements ServiceProvider, ServiceAgent$BrowseAgentInterface
{
    private static final int REFRESH_NOTIFICATIONS_INTERVAL_MS = 3600000;
    private static final String TAG = "FalkorAgent";
    private static final Boolean USE_CACHE_AND_REMOTE;
    private static final Boolean USE_CACHE_ONLY;
    private static volatile boolean isCacheWarmed;
    private static final AtomicBoolean isCurrentProfileActive;
    private static long sLastSerializeTimeToDisk;
    private Root cache;
    private CachedModelProxy<Root> cmp;
    private boolean hasProfileChanged;
    public final BroadcastReceiver playReceiver;
    private final Runnable refreshNotificationsRunnable;
    private long requestId;
    private boolean stopPrefetchLolomoSchedulerJob;
    public final BroadcastReceiver userAgentIntentReceiver;
    
    static {
        boolean b = true;
        USE_CACHE_ONLY = true;
        if (FalkorAgent.USE_CACHE_ONLY) {
            b = false;
        }
        USE_CACHE_AND_REMOTE = b;
        isCurrentProfileActive = new AtomicBoolean();
        FalkorAgent.isCacheWarmed = false;
    }
    
    public FalkorAgent() {
        this.playReceiver = new FalkorAgent$1(this);
        this.userAgentIntentReceiver = new FalkorAgent$2(this);
        this.refreshNotificationsRunnable = new FalkorAgent$9(this);
    }
    
    private void cacheImage(final CountDownLatch countDownLatch, final String s) {
        this.getService().getImageLoader().getImg(s, IClientLogging$AssetType.boxArt, 0, 0, new FalkorAgent$18(this, countDownLatch));
    }
    
    private void cacheLolomoImages(final CountDownLatch countDownLatch) {
        if (!this.isInPrefetchLolomoTest(this.getContext())) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "cacheLolomoImages: not in prefetch lolomo test");
            }
        }
        else {
            if (!this.stopPrefetchLolomoSchedulerJob) {
                if (Log.isLoggable()) {
                    Log.d("FalkorAgent", "caching images for all visible videos in the view port.");
                }
                this.fetchLoMos(0, 19, new FalkorAgent$16(this, countDownLatch));
                return;
            }
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "cacheLolomoImages: stopPrefetchLolomoSchedulerJob = true");
            }
        }
    }
    
    private static boolean canDoDataFetches() {
        if (!FalkorAgent.isCurrentProfileActive.get()) {
            Log.d("FalkorAgent", "wrong state - canDoDataFetches false - skipping browse request");
        }
        return FalkorAgent.isCurrentProfileActive.get();
    }
    
    private void cancelPrefetchLolomoSchedulerJob() {
        if (this.getService() != null) {
            final NetflixJobScheduler jobScheduler = this.getService().getJobScheduler();
            if (jobScheduler.isJobScheduled(NetflixJob$NetflixJobId.FALKOR_METADATA)) {
                if (Log.isLoggable()) {
                    Log.d("FalkorAgent", "cancelPrefetchLolomoSchedulerJob: cancelling prefetch lolomo job...");
                }
                jobScheduler.cancelJob(NetflixJob$NetflixJobId.FALKOR_METADATA);
            }
        }
    }
    
    private boolean checkAndDeserializeFalcorCacheFromDisk() {
        if (!this.isInPrefetchLolomoTest(this.getContext())) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "checkAndDeserializeFalcorCacheFromDisk: is not in test return early.");
            }
            this.cleanUpFalcorDiskCacheAsync();
            return false;
        }
        return this.deserializePrefetchMetadata();
    }
    
    private void checkAndInitPrefetchLolomoJob() {
        if (!this.isInPrefetchLolomoTest(this.getContext())) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "checkAndInitPrefetchLolomoJob: is not in test return early.");
            }
            this.cancelPrefetchLolomoSchedulerJob();
            return;
        }
        this.schedulePrefetchLolomoJob();
    }
    
    private void cleanUpFalcorDiskCacheAsync() {
        new BackgroundTask().execute(new FalkorAgent$15(this));
    }
    
    private void countDownLatch(final CountDownLatch countDownLatch) {
        if (countDownLatch == null) {
            return;
        }
        countDownLatch.countDown();
    }
    
    private void deserialize(final Reader reader) {
        this.cmp.deserializeStream(reader);
    }
    
    private boolean deserializePrefetchMetadata() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_3       
        //     2: iconst_1       
        //     3: istore_2       
        //     4: aconst_null    
        //     5: astore          11
        //     7: aconst_null    
        //     8: astore          12
        //    10: aconst_null    
        //    11: astore          9
        //    13: invokestatic    com/netflix/mediaclient/util/ThreadUtils.assertNotOnMain:()Z
        //    16: pop            
        //    17: aload_0        
        //    18: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getService:()Lcom/netflix/mediaclient/service/NetflixService;
        //    21: ifnonnull       43
        //    24: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    27: ifeq            39
        //    30: ldc             "FalkorAgent"
        //    32: ldc_w           "deserializePrefetchMetadata: service is null"
        //    35: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    38: pop            
        //    39: iconst_0       
        //    40: istore_3       
        //    41: iload_3        
        //    42: ireturn        
        //    43: aload_0        
        //    44: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //    47: aconst_null    
        //    48: aconst_null    
        //    49: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportDeserializeLolomoStarted:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/UserActionLogging$CommandName;Lcom/netflix/mediaclient/servicemgr/IClientLogging$ModalView;)V
        //    52: aload_0        
        //    53: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //    56: ldc_w           "prefs_prefetch_json_last_write_system_time_ms"
        //    59: ldc2_w          -1
        //    62: invokestatic    com/netflix/mediaclient/util/PreferenceUtils.getLongPref:(Landroid/content/Context;Ljava/lang/String;J)J
        //    65: lstore          4
        //    67: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    70: lload           4
        //    72: lsub           
        //    73: lstore          6
        //    75: lload           6
        //    77: ldc2_w          36000000
        //    80: lsub           
        //    81: lconst_0       
        //    82: lcmp           
        //    83: ifle            165
        //    86: iconst_1       
        //    87: istore_1       
        //    88: iload_1        
        //    89: ifeq            170
        //    92: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    95: ifeq            125
        //    98: ldc             "FalkorAgent"
        //   100: new             Ljava/lang/StringBuilder;
        //   103: dup            
        //   104: invokespecial   java/lang/StringBuilder.<init>:()V
        //   107: ldc_w           "deserializePrefetchMetadata: lolomo data in cache is expired - "
        //   110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   113: lload           6
        //   115: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   118: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   121: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   124: pop            
        //   125: ldc_w           "lolomo expired"
        //   128: astore          8
        //   130: lload           4
        //   132: lconst_0       
        //   133: lcmp           
        //   134: ifge            142
        //   137: ldc_w           "No prefetch lolomo data in cache"
        //   140: astore          8
        //   142: aload           8
        //   144: invokestatic    com/netflix/mediaclient/service/logging/client/model/UIError.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //   147: astore          8
        //   149: aload_0        
        //   150: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //   153: getstatic       com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.canceled:Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //   156: aload           8
        //   158: lload           6
        //   160: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportDeserializeLolomoEnded:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;J)V
        //   163: iconst_0       
        //   164: ireturn        
        //   165: iconst_0       
        //   166: istore_1       
        //   167: goto            88
        //   170: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   173: ifeq            185
        //   176: ldc             "FalkorAgent"
        //   178: ldc_w           "deserializePrefetchMetadata: start"
        //   181: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   184: pop            
        //   185: new             Ljava/lang/StringBuilder;
        //   188: dup            
        //   189: invokespecial   java/lang/StringBuilder.<init>:()V
        //   192: aload_0        
        //   193: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getService:()Lcom/netflix/mediaclient/service/NetflixService;
        //   196: invokevirtual   com/netflix/mediaclient/service/NetflixService.getFilesDir:()Ljava/io/File;
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   202: getstatic       java/io/File.separator:Ljava/lang/String;
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: ldc_w           "prefetch.json"
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   217: astore          8
        //   219: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   222: ifeq            252
        //   225: ldc             "FalkorAgent"
        //   227: new             Ljava/lang/StringBuilder;
        //   230: dup            
        //   231: invokespecial   java/lang/StringBuilder.<init>:()V
        //   234: ldc_w           "deserializePrefetchMetadata: file path = "
        //   237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: aload           8
        //   242: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   245: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   248: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   251: pop            
        //   252: new             Ljava/io/FileReader;
        //   255: dup            
        //   256: aload           8
        //   258: invokespecial   java/io/FileReader.<init>:(Ljava/lang/String;)V
        //   261: astore          9
        //   263: aload           9
        //   265: astore          8
        //   267: aload_0        
        //   268: aload           9
        //   270: invokespecial   com/netflix/mediaclient/service/falkor/FalkorAgent.deserialize:(Ljava/io/Reader;)V
        //   273: aload           9
        //   275: astore          8
        //   277: aload_0        
        //   278: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //   281: getstatic       com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.success:Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //   284: aconst_null    
        //   285: lload           6
        //   287: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportDeserializeLolomoEnded:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;J)V
        //   290: aload           9
        //   292: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   295: iconst_1       
        //   296: istore_2       
        //   297: iload_2        
        //   298: istore_3       
        //   299: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   302: ifeq            41
        //   305: ldc             "FalkorAgent"
        //   307: ldc_w           "deserializePrefetchMetadata: end"
        //   310: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   313: pop            
        //   314: iload_2        
        //   315: ireturn        
        //   316: astore          10
        //   318: aconst_null    
        //   319: astore          9
        //   321: iconst_0       
        //   322: istore_2       
        //   323: aload           9
        //   325: astore          8
        //   327: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   330: ifeq            367
        //   333: aload           9
        //   335: astore          8
        //   337: ldc             "FalkorAgent"
        //   339: new             Ljava/lang/StringBuilder;
        //   342: dup            
        //   343: invokespecial   java/lang/StringBuilder.<init>:()V
        //   346: ldc_w           "deserializePrefetchMetadata: failure - "
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: aload           10
        //   354: invokevirtual   java/io/FileNotFoundException.getMessage:()Ljava/lang/String;
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   363: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   366: pop            
        //   367: aload           9
        //   369: astore          8
        //   371: aload           10
        //   373: invokevirtual   java/io/FileNotFoundException.getMessage:()Ljava/lang/String;
        //   376: invokestatic    com/netflix/mediaclient/service/logging/client/model/UIError.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //   379: astore          10
        //   381: aload           9
        //   383: astore          8
        //   385: aload_0        
        //   386: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //   389: getstatic       com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.failed:Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //   392: aload           10
        //   394: lload           6
        //   396: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportDeserializeLolomoEnded:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;J)V
        //   399: aload           9
        //   401: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   404: goto            297
        //   407: astore          10
        //   409: aconst_null    
        //   410: astore          9
        //   412: iconst_0       
        //   413: istore_2       
        //   414: aload           9
        //   416: astore          8
        //   418: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   421: ifeq            458
        //   424: aload           9
        //   426: astore          8
        //   428: ldc             "FalkorAgent"
        //   430: new             Ljava/lang/StringBuilder;
        //   433: dup            
        //   434: invokespecial   java/lang/StringBuilder.<init>:()V
        //   437: ldc_w           "deserializePrefetchMetadata: failure - "
        //   440: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   443: aload           10
        //   445: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   448: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   451: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   454: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   457: pop            
        //   458: aload           9
        //   460: astore          8
        //   462: aload           10
        //   464: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   467: invokestatic    com/netflix/mediaclient/service/logging/client/model/UIError.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //   470: astore          10
        //   472: aload           9
        //   474: astore          8
        //   476: aload_0        
        //   477: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //   480: getstatic       com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.failed:Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //   483: aload           10
        //   485: lload           6
        //   487: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportDeserializeLolomoEnded:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;J)V
        //   490: aload           9
        //   492: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   495: goto            297
        //   498: astore          8
        //   500: aconst_null    
        //   501: astore          10
        //   503: aload           8
        //   505: astore          9
        //   507: aload           10
        //   509: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   512: aload           9
        //   514: athrow         
        //   515: astore          9
        //   517: aload           8
        //   519: astore          10
        //   521: goto            507
        //   524: astore          8
        //   526: aload           12
        //   528: astore          10
        //   530: goto            472
        //   533: astore          10
        //   535: iconst_0       
        //   536: istore_2       
        //   537: goto            414
        //   540: astore          10
        //   542: iload_3        
        //   543: istore_2       
        //   544: goto            414
        //   547: astore          8
        //   549: aload           11
        //   551: astore          10
        //   553: goto            381
        //   556: astore          10
        //   558: iconst_0       
        //   559: istore_2       
        //   560: goto            323
        //   563: astore          10
        //   565: goto            323
        //   568: astore          8
        //   570: aload           9
        //   572: astore          8
        //   574: goto            149
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  142    149    568    577    Lorg/json/JSONException;
        //  185    252    316    323    Ljava/io/FileNotFoundException;
        //  185    252    407    414    Ljava/io/IOException;
        //  185    252    498    507    Any
        //  252    263    316    323    Ljava/io/FileNotFoundException;
        //  252    263    407    414    Ljava/io/IOException;
        //  252    263    498    507    Any
        //  267    273    556    563    Ljava/io/FileNotFoundException;
        //  267    273    533    540    Ljava/io/IOException;
        //  267    273    515    524    Any
        //  277    290    563    568    Ljava/io/FileNotFoundException;
        //  277    290    540    547    Ljava/io/IOException;
        //  277    290    515    524    Any
        //  327    333    515    524    Any
        //  337    367    515    524    Any
        //  371    381    547    556    Lorg/json/JSONException;
        //  371    381    515    524    Any
        //  385    399    515    524    Any
        //  418    424    515    524    Any
        //  428    458    515    524    Any
        //  462    472    524    533    Lorg/json/JSONException;
        //  462    472    515    524    Any
        //  476    490    515    524    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 268, Size: 268
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private IrisNotificationSummary getFirstUnreadNotification(final IrisNotificationsList list) {
        final List<IrisNotificationSummary> socialNotifications = list.getSocialNotifications();
        if (socialNotifications != null) {
            int currentMaxNotificationsNum = SlidingMenuNotificationsFrag.getCurrentMaxNotificationsNum();
            for (final IrisNotificationSummary irisNotificationSummary : socialNotifications) {
                if (currentMaxNotificationsNum == 0) {
                    return null;
                }
                final IrisNotificationSummary irisNotificationSummary2 = irisNotificationSummary;
                if (!irisNotificationSummary.getWasRead()) {
                    return irisNotificationSummary2;
                }
                --currentMaxNotificationsNum;
            }
            return null;
        }
        return null;
    }
    
    private void handleAccountDeactive() {
        this.cancelPrefetchLolomoSchedulerJob();
    }
    
    private void handleProfileActive() {
        if (this.shouldFlushCache()) {
            Log.i("FalkorAgent", "handleProfileActive: Flushing all caches because new profile activated...");
            this.flushCaches();
        }
        this.hasProfileChanged = false;
        this.checkAndInitPrefetchLolomoJob();
        FalkorAgent.isCurrentProfileActive.set(true);
    }
    
    private void handleProfileDeactive() {
        FalkorAgent.isCurrentProfileActive.set(false);
        this.hasProfileChanged = true;
        if (this.isInPrefetchLolomoTest(this.getContext())) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "handleProfileDeactive: deleting prefetch lolomo disk cache.");
            }
            this.cleanUpFalcorDiskCacheAsync();
        }
    }
    
    private void handleResponse(final List<? extends Video> list, final Status status, final LoMo loMo, final CountDownLatch countDownLatch) {
        if (status.isError()) {
            Log.w("FalkorAgent", "Invalid status code");
            this.countDownLatch(countDownLatch);
            return;
        }
        if (list == null || list.size() <= 0) {
            Log.d("FalkorAgent", "No videos in response");
            this.countDownLatch(countDownLatch);
            return;
        }
        final BrowseExperience value = BrowseExperience.get();
        if (value == null) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "handleResponse: current experience is null");
            }
            this.countDownLatch(countDownLatch);
            return;
        }
        final int numVideosPerPageTableByOrientation = LomoConfig.getNumVideosPerPageTableByOrientation(2, DeviceUtils.getScreenSizeCategory((Context)this.getService()));
        final CountDownLatch countDownLatch2 = new CountDownLatch(numVideosPerPageTableByOrientation);
        for (int i = 0; i < numVideosPerPageTableByOrientation; ++i) {
            if (this.stopPrefetchLolomoSchedulerJob) {
                if (Log.isLoggable()) {
                    Log.d("FalkorAgent", "handleResponse: stopPrefetchLolomoSchedulerJob = true");
                }
                countDownLatch2.countDown();
            }
            else if (list.size() <= i) {
                countDownLatch2.countDown();
            }
            else {
                final List<String> prefetchLolomoImageUrlList = value.getPrefetchLolomoImageUrlList((Context)this.getService(), (Video)list.get(i), loMo.getType());
                if (prefetchLolomoImageUrlList == null) {
                    countDownLatch2.countDown();
                }
                else {
                    final Iterator<String> iterator = prefetchLolomoImageUrlList.iterator();
                    while (iterator.hasNext()) {
                        this.cacheImage(countDownLatch2, iterator.next());
                    }
                }
            }
        }
        this.videoImagesRequestCountdownAsync(countDownLatch, countDownLatch2);
    }
    
    private boolean isCachePreWarmed() {
        return FalkorAgent.isCacheWarmed || !this.isReady();
    }
    
    private boolean isInPrefetchLolomoTest(final Context context) {
        return PrefetchLolomoABTestUtils.hasJobScheduler(context);
    }
    
    private void lomoImagesCachedCountDownAsync(final CountDownLatch countDownLatch, final CountDownLatch countDownLatch2) {
        new BackgroundTask().execute(new FalkorAgent$17(this, countDownLatch2, countDownLatch));
    }
    
    private void notifyJobFinished(final boolean b, final boolean b2, final boolean b3, final String s) {
        if (Log.isLoggable()) {
            Log.d("FalkorAgent", "notifyJobFinished: success - " + b + " cancelled - " + b2);
        }
        final IClientLogging loggingAgent = this.getLoggingAgent();
        if (loggingAgent != null) {
            loggingAgent.getBreadcrumbLogging().leaveBreadcrumb(s);
        }
        this.getService().getJobScheduler().onJobFinished(NetflixJob$NetflixJobId.FALKOR_METADATA, false);
        IClientLogging$CompletionReason clientLogging$CompletionReason = IClientLogging$CompletionReason.failed;
        if (b2) {
            clientLogging$CompletionReason = IClientLogging$CompletionReason.canceled;
        }
        else if (b) {
            clientLogging$CompletionReason = IClientLogging$CompletionReason.success;
        }
        if (b3) {
            UserActionLogUtils.reportPrefetchLolomoJobEnded(this.getContext(), clientLogging$CompletionReason, null);
        }
    }
    
    private void notifyJobSchedulerFinishedAsync(final CountDownLatch countDownLatch) {
        new BackgroundTask().execute(new FalkorAgent$13(this, countDownLatch));
    }
    
    private void rescheduleNotificationsRefresh() {
        this.getMainHandler().removeCallbacks(this.refreshNotificationsRunnable);
        this.getMainHandler().postDelayed(this.refreshNotificationsRunnable, 3600000L);
    }
    
    private void schedulePrefetchLolomoJob() {
        if (this.getService() == null) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "checkAndInitPrefetchLolomoJob: getService() is null");
            }
            return;
        }
        final NetflixJobScheduler jobScheduler = this.getService().getJobScheduler();
        final NetflixJob$NetflixJobId falkor_METADATA = NetflixJob$NetflixJobId.FALKOR_METADATA;
        NetflixJob$NetflixJobId netflixJobId;
        if (jobScheduler.isJobScheduled(falkor_METADATA)) {
            netflixJobId = falkor_METADATA;
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "schedulePrefetchLolomoJob: prefetch job already scheduled");
                netflixJobId = falkor_METADATA;
            }
        }
        else {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "schedulePrefetchLolomoJob: scheduling prefetch lolomo job.");
            }
            final NetflixJob buildPrefetchLolomoJob = NetflixJob.buildPrefetchLolomoJob(PrefetchLolomoABTestUtils.isJobNetworkTypeUnmetered(this.getContext()), PrefetchLolomoABTestUtils.isJobPeriodic(this.getContext()), PrefetchLolomoABTestUtils.getJobPeriodicInterval(), PrefetchLolomoABTestUtils.doesJobRequireCharging(this.getContext()), PrefetchLolomoABTestUtils.doesJobRequireDeviceIdle(this.getContext()));
            netflixJobId = buildPrefetchLolomoJob.getNetflixJobId();
            jobScheduler.scheduleJob(buildPrefetchLolomoJob);
        }
        Log.d("FalkorAgent", "schedulePrefetchLolomoJob: registering JobExecutor PrefetchLolomoSchedulerJob with NetflixService ");
        this.getService().registerJobExecutor(netflixJobId, new FalkorAgent$PrefetchLolomoSchedulerJob(this, null));
    }
    
    private boolean shouldBeNotificationSentToStatusBar(final IrisNotificationSummary irisNotificationSummary) {
        return irisNotificationSummary != null && !NetflixApplication.isActivityVisible() && this.getService().getPushNotification().isOptIn();
    }
    
    private boolean shouldFlushCache() {
        return this.hasProfileChanged || !this.isCachePreWarmed();
    }
    
    private void videoImagesRequestCountdownAsync(final CountDownLatch countDownLatch, final CountDownLatch countDownLatch2) {
        new BackgroundTask().execute(new FalkorAgent$19(this, countDownLatch2, countDownLatch));
    }
    
    public void addToQueue(final String s, final VideoType videoType, final int n, final boolean b, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.addToQueue(s, videoType, n, b, s2, browseAgentCallback);
    }
    
    @Override
    public void destroy() {
        this.serializeFalkorMetadataAsync();
        IntentUtils.unregisterSafelyLocalBroadcastReceiver(this.getContext(), this.userAgentIntentReceiver);
        IntentUtils.unregisterSafelyLocalBroadcastReceiver(this.getContext(), this.playReceiver);
        super.destroy();
    }
    
    public void doInit() {
        this.cache = new Root();
        this.cmp = new CachedModelProxy<Root>(this, this.cache, (FalkorVolleyWebClient)this.getResourceFetcher().getApiNextWebClient());
        this.cache.setProxy(this.cmp);
        IntentUtils.registerSafelyLocalBroadcastReceiver(this.getContext(), this.userAgentIntentReceiver, UserAgentBroadcastIntents.getNotificationIntentFilter());
        IntentUtils.registerSafelyBroadcastReceiver(this.getContext(), this.playReceiver, null, "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START", "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP");
        FalkorAgent.isCacheWarmed = this.checkAndDeserializeFalcorCacheFromDisk();
        if (!this.isInPrefetchLolomoTest(this.getContext())) {
            this.cancelPrefetchLolomoSchedulerJob();
        }
        this.initCompleted(CommonStatus.OK);
    }
    
    public void dumpCacheToDisk() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.dumpCacheToDisk();
    }
    
    public void endBrowsePlaySession(final long n, final int n2, final int n3, final int n4) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.endBrowsePlaySession(n, n2, n3, n4, new FalkorAgent$7(this));
    }
    
    public void fetchActorDetailsAndRelatedForTitle(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchActorDetailsAndRelatedForTitle(s, browseAgentCallback);
    }
    
    public void fetchAdvisories(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchAdvisories(s, browseAgentCallback);
    }
    
    @Override
    public void fetchBillboards(final int n, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchBBVideos(0, n - 1, b, browseAgentCallback);
    }
    
    @Override
    public void fetchCW(final int n, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchCWVideos(0, n - 1, b, browseAgentCallback);
    }
    
    @Override
    public void fetchCWFromNetwork(final int n, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchCWVideosFromNetwork(0, n - 1, browseAgentCallback);
    }
    
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchCWVideos(n, n2, FalkorAgent.USE_CACHE_AND_REMOTE, browseAgentCallback);
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchEpisodeDetails(s, s2, browseAgentCallback);
    }
    
    public void fetchEpisodes(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchEpisodes(s, videoType, n, n2, browseAgentCallback);
    }
    
    public void fetchEpisodesForSeason(final Asset asset) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        final String playableId = asset.getPlayableId();
        final boolean episode = asset.isEpisode();
        final String parentId = asset.getParentId();
        if (StringUtils.isEmpty(playableId) || (episode && StringUtils.isEmpty(parentId))) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", String.format("fetchEpisodesForSeason - parentId %s or videoId %s null - skip!", parentId, playableId));
            }
        }
        else {
            if (episode) {
                this.fetchShowDetails(parentId, null, false, new FalkorAgent$4(this));
                return;
            }
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", String.format("fetchEpisodesForSeason - parentId %s or videoId %s is Movie - skip!", parentId, playableId));
            }
        }
    }
    
    public void fetchGenreList(final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchGenreList(browseAgentCallback);
    }
    
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchVideos(loMo, n, n2, FalkorAgent.USE_CACHE_AND_REMOTE, b, false, browseAgentCallback);
    }
    
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchGenres(s, n, n2, browseAgentCallback);
    }
    
    @Override
    public void fetchIQ(final int n, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchIQVideos(0, n - 1, b, false, browseAgentCallback);
    }
    
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchIQVideos(loMo, n, n2, FalkorAgent.USE_CACHE_AND_REMOTE, b, browseAgentCallback);
    }
    
    @Override
    public void fetchInteractiveVideoMoments(final VideoType videoType, final String s, final String s2, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchInteractiveVideoMoments(videoType, s, s2, n, n2, browseAgentCallback);
    }
    
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchKidsCharacterDetails(s, browseAgentCallback);
    }
    
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchGenreLoLoMoSummary(s, browseAgentCallback);
    }
    
    @Override
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchLoMos(n, n2, browseAgentCallback);
    }
    
    @Override
    public void fetchMovieDetails(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchMovieDetails(s, s2, browseAgentCallback);
    }
    
    @Override
    public void fetchNonMemberVideos(final int n, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchNonMemberVideos(n - 1, b, browseAgentCallback);
    }
    
    public void fetchNotificationsList(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchNotifications(n, n2, false, browseAgentCallback);
    }
    
    public void fetchPersonDetail(final String s, final BrowseAgentCallback browseAgentCallback, final String s2) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchPersonDetail(s, browseAgentCallback, s2);
    }
    
    public void fetchPersonRelated(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchPersonRelated(s, browseAgentCallback);
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final VideoType videoType, final PostPlayRequestContext postPlayRequestContext, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchPostPlayVideos(s, videoType, postPlayRequestContext, browseAgentCallback);
    }
    
    public void fetchPreAppData(final int n, int n2) {
        final FalkorAgent$10 falkorAgent$10 = new FalkorAgent$10(this);
        --n2;
        this.prefetchLoLoMo(0, n - 1, 0, n2, 0, n2, false, false, false, false, falkorAgent$10);
    }
    
    @Override
    public void fetchRecommendedList(final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchLoMos(0, 10, new FalkorAgent$11(this, n, n2, b, browseAgentCallback));
    }
    
    public void fetchScenePosition(final VideoType videoType, final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchScenePosition(videoType, s, s2, browseAgentCallback);
    }
    
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSeasonDetails(s, browseAgentCallback);
    }
    
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSeasons(s, n, n2, browseAgentCallback);
    }
    
    @Override
    public void fetchShowDetails(final String s, final String s2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchShowDetails(s, s2, false, b, false, browseAgentCallback);
    }
    
    @Override
    public void fetchShowDetailsAndSeasons(final String s, final String s2, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchShowDetails(s, s2, true, b, b2, browseAgentCallback);
    }
    
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSimilarVideos(Falkor$SimilarRequestType.PEOPLE, s, n, s2, browseAgentCallback);
    }
    
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSimilarVideos(Falkor$SimilarRequestType.QUERY_SUGGESTION, s, n, s2, browseAgentCallback);
    }
    
    @Override
    public void fetchTask(final CachedModelProxy$CmpTaskDetails cachedModelProxy$CmpTaskDetails, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchTask(cachedModelProxy$CmpTaskDetails, browseAgentCallback);
    }
    
    @Override
    public void fetchVideoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchVideoSummary(s, browseAgentCallback);
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final boolean b, final boolean b2, final boolean b3, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchVideos(loMo, n, n2, b, b2, b3, browseAgentCallback);
    }
    
    public void flushCaches() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.flushCaches();
    }
    
    public void forceFetchFromLocalCache(final boolean b) {
        this.cmp.forceFetchFromLocalCache(b);
    }
    
    public String getLolomoId() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        return this.cmp.getCurrLolomoId();
    }
    
    public ModelProxy<?> getModelProxy() {
        return this.cmp;
    }
    
    @Override
    public NetflixService getService() {
        return super.getService();
    }
    
    public void invalidateCachedEpisodes(final String s, final VideoType videoType) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.invalidateEpisodes(s, videoType);
    }
    
    public void logBillboardActivity(final Video video, final BillboardInteractionType billboardInteractionType, final Map<String, String> map) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.logBillboardActivity(video, billboardInteractionType, map);
    }
    
    @Override
    public void logPostPlayImpression(final String s, final VideoType videoType, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.logPostPlayImpression(s, videoType, s2, browseAgentCallback);
    }
    
    public void markNotificationAsRead(final IrisNotificationSummary irisNotificationSummary) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.markNotificationAsRead(irisNotificationSummary, new FalkorAgent$5(this));
    }
    
    public void markNotificationsAsRead(final List<IrisNotificationSummary> list) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.markNotificationsAsRead(list, new FalkorAgent$6(this));
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.prefetchGenreLoLoMo(s, n, n2, n3, n4, b, browseAgentCallback);
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final boolean b3, final boolean b4, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        PerformanceProfiler.getInstance().startSession(Sessions.LOLOMO_PREFETCH, null);
        this.cmp.prefetchLoLoMo(n, n2, n3, n4, n5, n6, b, b2, b3, b4, new FalkorAgent$3(this, browseAgentCallback));
    }
    
    @Override
    public void prefetchVideoListDetails(final List<? extends Video> list, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.prefetchVideoListDetails(list, browseAgentCallback);
    }
    
    public void refreshCw(final boolean b, final boolean b2) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        if (BrowseExperience.showKidsExperience()) {
            if (Log.isLoggable()) {
                Log.v("FalkorAgent", "Showing Kids experience - refresh popular titles...");
            }
            this.cmp.refreshPopularTitlesLomo();
        }
        if (this.cmp.doesCwExist()) {
            this.cmp.refreshCw();
        }
        else if ((Coppola2Utils.isCoppolaWithoutNormalCW(this.getContext()) && this.cmp.doesDiscoveryRowExist()) || (Coppola1Utils.isCoppolaExperience(this.getContext()) && b) || b2) {
            Log.v("FalkorAgent", "This is a special use case for Coppola - no need to refresh the whole LoLoMo");
        }
        else if (ConnectivityUtils.isConnected(this.getContext())) {
            this.refreshLolomo();
        }
        if (Coppola2Utils.isCoppolaDiscovery(this.getContext())) {
            if (ConnectivityUtils.isConnectedOrConnecting(this.getContext())) {
                this.cmp.refreshDiscoveryRow();
                return;
            }
            Log.w("FalkorAgent", "No connectivity - no need to refresh Discovery row");
        }
    }
    
    public void refreshIq() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.refreshIq();
    }
    
    public void refreshIrisNotifications(final boolean b, final boolean b2, final MessageData messageData) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchNotifications(0, IrisUtils.PAGE_NOTIFICATIONS_SIZE - 1, b, new FalkorAgent$8(this, b2, messageData));
        if (this.getService() != null && this.getService().getCurrentProfile() != null) {
            this.rescheduleNotificationsRefresh();
        }
    }
    
    public void refreshLolomo() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.flushCaches();
        ServiceManager.sendHomeRefreshBrodcast((Context)this.getService());
    }
    
    public void removeFromQueue(final String s, final VideoType videoType, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.removeFromQueue(s, videoType, s2, browseAgentCallback);
    }
    
    public void searchNetflix(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.searchNetflix(s, browseAgentCallback);
    }
    
    public void serializeFalcorCache() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getService:()Lcom/netflix/mediaclient/service/NetflixService;
        //     4: ifnonnull       23
        //     7: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    10: ifeq            22
        //    13: ldc             "FalkorAgent"
        //    15: ldc_w           "serializeFalkorMetadataAsync: service is null"
        //    18: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    21: pop            
        //    22: return         
        //    23: aload_0        
        //    24: aload_0        
        //    25: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //    28: invokespecial   com/netflix/mediaclient/service/falkor/FalkorAgent.isInPrefetchLolomoTest:(Landroid/content/Context;)Z
        //    31: ifne            50
        //    34: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    37: ifeq            22
        //    40: ldc             "FalkorAgent"
        //    42: ldc_w           "serializeFalkorMetadataAsync: not in prefetch lolomo test"
        //    45: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    48: pop            
        //    49: return         
        //    50: getstatic       com/netflix/mediaclient/service/falkor/FalkorAgent.sLastSerializeTimeToDisk:J
        //    53: invokestatic    com/netflix/falkor/CachedModelProxy.getLastWriteTimeToCacheMS:()J
        //    56: lsub           
        //    57: lconst_0       
        //    58: lcmp           
        //    59: iflt            78
        //    62: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    65: ifeq            22
        //    68: ldc             "FalkorAgent"
        //    70: ldc_w           "serializeFalkorMetadataAsync: no changes in falcor cache, already serialized to disk"
        //    73: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    76: pop            
        //    77: return         
        //    78: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    81: ifeq            93
        //    84: ldc             "FalkorAgent"
        //    86: ldc_w           "serializeFalkorMetadataAsync: start"
        //    89: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    92: pop            
        //    93: aload_0        
        //    94: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //    97: aconst_null    
        //    98: aconst_null    
        //    99: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportSerializeLolomoStarted:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/UserActionLogging$CommandName;Lcom/netflix/mediaclient/servicemgr/IClientLogging$ModalView;)V
        //   102: new             Ljava/io/FileWriter;
        //   105: dup            
        //   106: new             Ljava/io/File;
        //   109: dup            
        //   110: aload_0        
        //   111: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getService:()Lcom/netflix/mediaclient/service/NetflixService;
        //   114: invokevirtual   com/netflix/mediaclient/service/NetflixService.getFilesDir:()Ljava/io/File;
        //   117: ldc_w           "prefetch.json"
        //   120: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   123: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   126: invokespecial   java/io/FileWriter.<init>:(Ljava/lang/String;)V
        //   129: astore          6
        //   131: aload           6
        //   133: astore          5
        //   135: aload_0        
        //   136: getfield        com/netflix/mediaclient/service/falkor/FalkorAgent.cmp:Lcom/netflix/falkor/CachedModelProxy;
        //   139: aload           6
        //   141: invokevirtual   com/netflix/falkor/CachedModelProxy.serialize:(Ljava/io/Writer;)V
        //   144: aload           6
        //   146: astore          5
        //   148: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   151: putstatic       com/netflix/mediaclient/service/falkor/FalkorAgent.sLastSerializeTimeToDisk:J
        //   154: aload           6
        //   156: astore          5
        //   158: getstatic       com/netflix/mediaclient/service/falkor/FalkorAgent.sLastSerializeTimeToDisk:J
        //   161: lstore_1       
        //   162: aload           6
        //   164: astore          5
        //   166: aload_0        
        //   167: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //   170: ldc_w           "prefs_prefetch_json_last_write_system_time_ms"
        //   173: ldc2_w          -1
        //   176: invokestatic    com/netflix/mediaclient/util/PreferenceUtils.getLongPref:(Landroid/content/Context;Ljava/lang/String;J)J
        //   179: lstore_3       
        //   180: aload           6
        //   182: astore          5
        //   184: aload_0        
        //   185: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //   188: getstatic       com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.success:Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //   191: aconst_null    
        //   192: lload_1        
        //   193: lload_3        
        //   194: lsub           
        //   195: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportSerializeLolomoEnded:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;J)V
        //   198: aload           6
        //   200: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   203: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   206: ifeq            22
        //   209: ldc             "FalkorAgent"
        //   211: ldc_w           "serializeFalkorMetadataAsync: end"
        //   214: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   217: pop            
        //   218: return         
        //   219: astore          7
        //   221: aconst_null    
        //   222: astore          6
        //   224: aload           6
        //   226: astore          5
        //   228: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   231: ifeq            268
        //   234: aload           6
        //   236: astore          5
        //   238: ldc             "FalkorAgent"
        //   240: new             Ljava/lang/StringBuilder;
        //   243: dup            
        //   244: invokespecial   java/lang/StringBuilder.<init>:()V
        //   247: ldc_w           "serializeFalkorMetadataAsync: serializing data to disk failed "
        //   250: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   253: aload           7
        //   255: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   264: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   267: pop            
        //   268: aload           6
        //   270: astore          5
        //   272: aload_0        
        //   273: invokevirtual   com/netflix/mediaclient/service/falkor/FalkorAgent.getContext:()Landroid/content/Context;
        //   276: getstatic       com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.failed:Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //   279: aconst_null    
        //   280: ldc2_w          -1
        //   283: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportSerializeLolomoEnded:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;J)V
        //   286: aload           6
        //   288: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   291: goto            203
        //   294: astore          6
        //   296: aconst_null    
        //   297: astore          5
        //   299: aload           5
        //   301: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   304: aload           6
        //   306: athrow         
        //   307: astore          6
        //   309: goto            299
        //   312: astore          7
        //   314: goto            224
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  102    131    219    224    Ljava/io/IOException;
        //  102    131    294    299    Any
        //  135    144    312    317    Ljava/io/IOException;
        //  135    144    307    312    Any
        //  148    154    312    317    Ljava/io/IOException;
        //  148    154    307    312    Any
        //  158    162    312    317    Ljava/io/IOException;
        //  158    162    307    312    Any
        //  166    180    312    317    Ljava/io/IOException;
        //  166    180    307    312    Any
        //  184    198    312    317    Ljava/io/IOException;
        //  184    198    307    312    Any
        //  228    234    307    312    Any
        //  238    268    307    312    Any
        //  272    286    307    312    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0203:
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
    
    public void serializeFalkorMetadataAsync() {
        this.serializeFalkorMetadataAsync(null);
    }
    
    public void serializeFalkorMetadataAsync(final CountDownLatch countDownLatch) {
        if (!this.isInPrefetchLolomoTest(this.getContext())) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "serializeFalkorMetadataAsync: not in prefetch lolomo test");
            }
            return;
        }
        new BackgroundTask().execute(new FalkorAgent$14(this, countDownLatch));
    }
    
    public void setVideoRating(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.setVideoRating(s, videoType, n, n2, browseAgentCallback);
    }
    
    public boolean startLolomoFetchJob(final boolean b) {
        if (!b && AndroidUtils.isApplicationInForeground(this.getContext())) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "startLolomoFetchJob: app is in foreground right now - cancel job");
            }
            return false;
        }
        Log.d("FalkorAgent", "Prefetching lolomo from network");
        this.requestId = System.nanoTime();
        final long requestId = this.requestId;
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        this.prefetchLoLoMo(0, 19, 0, LomoConfig.computeNumVideosToFetchPerBatch((Context)this.getService(), LoMoType.STANDARD) - 1, 0, LomoConfig.computeNumVideosToFetchPerBatch((Context)this.getService(), LoMoType.CONTINUE_WATCHING) - 1, BrowseExperience.shouldLoadExtraCharacterLeaves(), BrowseExperience.shouldLoadKubrickLeavesInLolomo(), false, true, new FalkorAgent$12(this, requestId, countDownLatch));
        this.notifyJobSchedulerFinishedAsync(countDownLatch);
        return true;
    }
    
    public void updateCachedVideoPosition(final Asset asset) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.updateBookmarkPosition(asset);
    }
    
    public void updateExpiredContentAdvisoryStatus(final String s, final ExpiringContentAdvisory$ContentAction expiringContentAdvisory$ContentAction) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.updateExpiredContentAdvisoryStatus(s, expiringContentAdvisory$ContentAction);
    }
    
    @Override
    public void updateOfflineGeoPlayability(final List<String> list, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        if (this.isReady()) {
            this.cmp.fetchOfflineGeoPlayability(list, browseAgentCallback);
        }
    }
}
