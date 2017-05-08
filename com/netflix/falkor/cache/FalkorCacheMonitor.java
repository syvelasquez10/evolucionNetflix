// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.netflix.mediaclient.android.app.ApplicationStateListener;
import com.netflix.mediaclient.NetflixApplication;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.ArrayList;
import android.os.Looper;
import io.realm.Realm;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import android.os.Handler;

public class FalkorCacheMonitor
{
    private static final long DEBOUNCE_DELAY_MS = 15L;
    private static final long MEASURE_FILE_SIZE_INTERVAL_MS = 5000L;
    private static final String TAG = "FalkorCache.Monitor";
    private static final Handler sHandler;
    final AtomicInteger cacheAdded;
    final AtomicLong cacheAddedBytes;
    final AtomicInteger cacheDeleted;
    final AtomicInteger cacheHit;
    final AtomicLong cacheHitBytes;
    final AtomicInteger cacheMisses;
    final AtomicLong lolomoCount;
    final AtomicLong lruBasedCount;
    private final List<FalkorCacheMonitor$DataUpdated> mDataUpdated;
    private boolean mInitialized;
    private final List<FalkorCacheMonitor$OnUpdateListener> mListeners;
    private final Runnable mMeasureFileSize;
    private final Runnable mNotifyListeners;
    private Realm mRealm;
    final AtomicLong realmFileSize;
    final AtomicLong timeBasedCount;
    
    static {
        sHandler = new Handler(Looper.getMainLooper());
    }
    
    public FalkorCacheMonitor() {
        this.cacheHit = new AtomicInteger(0);
        this.cacheMisses = new AtomicInteger(0);
        this.cacheAdded = new AtomicInteger(0);
        this.cacheDeleted = new AtomicInteger(0);
        this.cacheHitBytes = new AtomicLong(0L);
        this.cacheAddedBytes = new AtomicLong(0L);
        this.lruBasedCount = new AtomicLong(0L);
        this.timeBasedCount = new AtomicLong(0L);
        this.lolomoCount = new AtomicLong(0L);
        this.realmFileSize = new AtomicLong(0L);
        this.mListeners = new ArrayList<FalkorCacheMonitor$OnUpdateListener>();
        this.mDataUpdated = new ArrayList<FalkorCacheMonitor$DataUpdated>();
        this.mInitialized = false;
        this.mNotifyListeners = new FalkorCacheMonitor$1(this);
        this.mMeasureFileSize = new FalkorCacheMonitor$2(this);
    }
    
    public static JSONObject getRealmTableCounts(final Realm realm) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("homeTable", realm.where(FalkorRealmCacheHomeLolomo.class).count());
            jsonObject.put("byTimeTable", realm.where(FalkorRealmCacheTimeBased.class).count());
            jsonObject.put("byLruTable", realm.where(FalkorRealmCacheLruBased.class).count());
            return jsonObject;
        }
        catch (JSONException ex) {
            Log.d("FalkorCache.Monitor", "getRealmTableCounts had exception %s", ex);
            return jsonObject;
        }
    }
    
    private void notifyListeners(final List<FalkorCacheMonitor$DataUpdated> list) {
        if (this.mRealm != null) {
            final long count = this.mRealm.where(FalkorRealmCacheLruBased.class).count();
            if (count != this.lruBasedCount.get()) {
                list.add(FalkorCacheMonitor$DataUpdated.CountLruBased);
                this.lruBasedCount.set(count);
            }
            final long count2 = this.mRealm.where(FalkorRealmCacheTimeBased.class).count();
            if (count2 != this.timeBasedCount.get()) {
                list.add(FalkorCacheMonitor$DataUpdated.CountTimeBased);
                this.timeBasedCount.set(count2);
            }
            final long count3 = this.mRealm.where(FalkorRealmCacheHomeLolomo.class).count();
            if (count3 != this.lolomoCount.get()) {
                list.add(FalkorCacheMonitor$DataUpdated.CountLolomo);
                this.lolomoCount.set(count3);
            }
        }
        for (int i = 0; i < this.mListeners.size(); ++i) {
            this.mListeners.get(i).onUpdate(list);
        }
    }
    
    private void sendUpdate(final FalkorCacheMonitor$DataUpdated falkorCacheMonitor$DataUpdated) {
        this.mDataUpdated.add(falkorCacheMonitor$DataUpdated);
        FalkorCacheMonitor.sHandler.removeCallbacks(this.mNotifyListeners);
        FalkorCacheMonitor.sHandler.postDelayed(this.mNotifyListeners, 15L);
    }
    
    public void add(final String s) {
        this.cacheAdded.incrementAndGet();
        if (s != null) {
            this.cacheAddedBytes.addAndGet(s.getBytes().length);
        }
        this.sendUpdate(FalkorCacheMonitor$DataUpdated.Add);
    }
    
    public void addOnUpdateListener(final FalkorCacheMonitor$OnUpdateListener falkorCacheMonitor$OnUpdateListener) {
        this.mListeners.add(falkorCacheMonitor$OnUpdateListener);
    }
    
    public void delete(final int n) {
        this.cacheDeleted.addAndGet(n);
        this.sendUpdate(FalkorCacheMonitor$DataUpdated.Delete);
    }
    
    public void hit(final String s) {
        this.cacheHit.incrementAndGet();
        if (s != null) {
            this.cacheHitBytes.addAndGet(s.getBytes().length);
        }
        this.sendUpdate(FalkorCacheMonitor$DataUpdated.Hit);
    }
    
    void init(final Realm mRealm, final NetflixApplication netflixApplication) {
        if (this.mInitialized) {
            throw new IllegalStateException("FalkorCacheMonitor already initialized");
        }
        this.mInitialized = true;
        this.mRealm = mRealm;
        this.mMeasureFileSize.run();
        this.sendUpdate(FalkorCacheMonitor$DataUpdated.All);
        netflixApplication.getUserInput().addListener(new FalkorCacheMonitor$3(this));
    }
    
    public void logCacheAction(final String p0, final int p1, final JSONObject p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore          4
        //     9: ldc             "FalkorCache.Monitor"
        //    11: ldc_w           "logCacheAction %s %dms"
        //    14: iconst_2       
        //    15: anewarray       Ljava/lang/Object;
        //    18: dup            
        //    19: iconst_0       
        //    20: aload_1        
        //    21: aastore        
        //    22: dup            
        //    23: iconst_1       
        //    24: iload_2        
        //    25: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    28: aastore        
        //    29: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //    32: pop            
        //    33: aload           4
        //    35: ldc_w           "action"
        //    38: aload_1        
        //    39: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    42: pop            
        //    43: aload           4
        //    45: ldc_w           "added"
        //    48: aload_0        
        //    49: getfield        com/netflix/falkor/cache/FalkorCacheMonitor.cacheAdded:Ljava/util/concurrent/atomic/AtomicInteger;
        //    52: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    55: pop            
        //    56: aload           4
        //    58: ldc_w           "deleted"
        //    61: aload_0        
        //    62: getfield        com/netflix/falkor/cache/FalkorCacheMonitor.cacheDeleted:Ljava/util/concurrent/atomic/AtomicInteger;
        //    65: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    68: pop            
        //    69: aload           4
        //    71: ldc_w           "hits"
        //    74: aload_0        
        //    75: getfield        com/netflix/falkor/cache/FalkorCacheMonitor.cacheHit:Ljava/util/concurrent/atomic/AtomicInteger;
        //    78: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    81: pop            
        //    82: aload           4
        //    84: ldc_w           "misses"
        //    87: aload_0        
        //    88: getfield        com/netflix/falkor/cache/FalkorCacheMonitor.cacheMisses:Ljava/util/concurrent/atomic/AtomicInteger;
        //    91: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    94: pop            
        //    95: iload_2        
        //    96: ifle            109
        //    99: aload           4
        //   101: ldc_w           "duration"
        //   104: iload_2        
        //   105: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   108: pop            
        //   109: aload_3        
        //   110: ifnull          123
        //   113: aload           4
        //   115: ldc_w           "prevSizes"
        //   118: aload_3        
        //   119: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   122: pop            
        //   123: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.getInstance:()Lio/realm/Realm;
        //   126: astore          5
        //   128: aload           4
        //   130: ldc_w           "sizes"
        //   133: aload           5
        //   135: invokestatic    com/netflix/falkor/cache/FalkorCacheMonitor.getRealmTableCounts:(Lio/realm/Realm;)Lorg/json/JSONObject;
        //   138: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   141: pop            
        //   142: aload           5
        //   144: ifnull          156
        //   147: iconst_0       
        //   148: ifeq            199
        //   151: aload           5
        //   153: invokevirtual   io/realm/Realm.close:()V
        //   156: invokestatic    com/netflix/mediaclient/NetflixApplication.getContext:()Landroid/content/Context;
        //   159: ldc_w           "FalkorCache"
        //   162: aconst_null    
        //   163: aload           4
        //   165: invokestatic    com/netflix/mediaclient/util/log/UserActionLogUtils.reportCustomAction:(Landroid/content/Context;Ljava/lang/String;Lcom/netflix/mediaclient/servicemgr/IClientLogging$ModalView;Lorg/json/JSONObject;)V
        //   168: return         
        //   169: astore_1       
        //   170: new             Ljava/lang/NullPointerException;
        //   173: dup            
        //   174: invokespecial   java/lang/NullPointerException.<init>:()V
        //   177: athrow         
        //   178: astore_1       
        //   179: ldc             "FalkorCache.Monitor"
        //   181: ldc_w           "logCacheAction had exception %s"
        //   184: iconst_1       
        //   185: anewarray       Ljava/lang/Object;
        //   188: dup            
        //   189: iconst_0       
        //   190: aload_1        
        //   191: aastore        
        //   192: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //   195: pop            
        //   196: goto            156
        //   199: aload           5
        //   201: invokevirtual   io/realm/Realm.close:()V
        //   204: goto            156
        //   207: astore_3       
        //   208: aload_3        
        //   209: athrow         
        //   210: astore_1       
        //   211: aload           5
        //   213: ifnull          225
        //   216: aload_3        
        //   217: ifnull          238
        //   220: aload           5
        //   222: invokevirtual   io/realm/Realm.close:()V
        //   225: aload_1        
        //   226: athrow         
        //   227: astore          5
        //   229: aload_3        
        //   230: aload           5
        //   232: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   235: goto            225
        //   238: aload           5
        //   240: invokevirtual   io/realm/Realm.close:()V
        //   243: goto            225
        //   246: astore_1       
        //   247: aconst_null    
        //   248: astore_3       
        //   249: goto            211
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  33     95     178    199    Lorg/json/JSONException;
        //  99     109    178    199    Lorg/json/JSONException;
        //  113    123    178    199    Lorg/json/JSONException;
        //  123    128    178    199    Lorg/json/JSONException;
        //  128    142    207    211    Ljava/lang/Throwable;
        //  128    142    246    252    Any
        //  151    156    169    178    Ljava/lang/Throwable;
        //  151    156    178    199    Lorg/json/JSONException;
        //  170    178    178    199    Lorg/json/JSONException;
        //  199    204    178    199    Lorg/json/JSONException;
        //  208    210    210    211    Any
        //  220    225    227    238    Ljava/lang/Throwable;
        //  220    225    178    199    Lorg/json/JSONException;
        //  225    227    178    199    Lorg/json/JSONException;
        //  229    235    178    199    Lorg/json/JSONException;
        //  238    243    178    199    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 126, Size: 126
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
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
    
    public void miss() {
        this.cacheMisses.incrementAndGet();
        this.sendUpdate(FalkorCacheMonitor$DataUpdated.Miss);
    }
    
    public void removeOnUpdateListener(final FalkorCacheMonitor$OnUpdateListener falkorCacheMonitor$OnUpdateListener) {
        this.mListeners.remove(falkorCacheMonitor$OnUpdateListener);
    }
    
    void resetAll() {
        this.cacheHit.set(0);
        this.cacheMisses.set(0);
        this.cacheAdded.set(0);
        this.cacheDeleted.set(0);
        this.cacheHitBytes.set(0L);
        this.cacheAddedBytes.set(0L);
        this.lruBasedCount.set(0L);
        this.timeBasedCount.set(0L);
        this.lolomoCount.set(0L);
        this.sendUpdate(FalkorCacheMonitor$DataUpdated.All);
    }
}
