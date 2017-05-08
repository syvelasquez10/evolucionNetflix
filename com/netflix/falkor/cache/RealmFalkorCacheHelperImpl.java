// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.google.gson.JsonSyntaxException;
import com.netflix.mediaclient.util.LogUtils;
import org.json.JSONObject;
import io.realm.Realm$Transaction;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.ArrayList;
import com.netflix.model.leafs.ListOfListOfGenres;
import com.netflix.falkor.Sentinel;
import com.netflix.model.leafs.BaseFalkorLeafItem;
import com.netflix.falkor.PQL;
import com.netflix.falkor.Undefined;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.falkor.Ref;
import com.netflix.falkor.BranchNode;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import io.realm.RealmModel;
import java.util.Date;
import com.google.gson.JsonElement;
import java.util.concurrent.TimeUnit;
import java.util.Collection;
import java.util.Arrays;
import io.realm.Realm;
import java.util.HashSet;

public class RealmFalkorCacheHelperImpl implements FalkorCacheHelperInterface
{
    private static long DEFAULT_LOLOMO_EXPIRY_MS = 0L;
    private static final long DEFAULT_LRU_EXPIRY_MS;
    private static final String TAG = "FalkorCache.RealmHelper";
    private static final HashSet VOLATILE_LEAF_NODES;
    private Realm mRealm;
    
    static {
        VOLATILE_LEAF_NODES = new HashSet((Collection<? extends E>)Arrays.asList("bookmark", "current", "inQueue", "rating", "hasWatched"));
        RealmFalkorCacheHelperImpl.DEFAULT_LOLOMO_EXPIRY_MS = TimeUnit.HOURS.toMillis(16L);
        DEFAULT_LRU_EXPIRY_MS = TimeUnit.DAYS.toMillis(90L);
    }
    
    public RealmFalkorCacheHelperImpl(final long default_LOLOMO_EXPIRY_MS) {
        this.mRealm = null;
        RealmFalkorCacheHelperImpl.DEFAULT_LOLOMO_EXPIRY_MS = default_LOLOMO_EXPIRY_MS;
        this.mRealm = FalkorCache$RealmAccess.getInstance();
    }
    
    private static Date createExpirationDate(final JsonElement jsonElement, final Class<? extends RealmModel> clazz) {
        Date dateFromExpires;
        final Date date = dateFromExpires = FalkorParseUtils.createDateFromExpires(jsonElement);
        if (date == null) {
            if (clazz == FalkorRealmCacheHomeLolomo.class) {
                dateFromExpires = new Date(System.currentTimeMillis() + RealmFalkorCacheHelperImpl.DEFAULT_LOLOMO_EXPIRY_MS);
            }
            else {
                if (clazz == FalkorRealmCacheTimeBased.class) {
                    return new Date(System.currentTimeMillis() + RealmFalkorCacheHelperImpl.DEFAULT_LOLOMO_EXPIRY_MS);
                }
                dateFromExpires = date;
                if (clazz == FalkorRealmCacheLruBased.class) {
                    return new Date(System.currentTimeMillis() + RealmFalkorCacheHelperImpl.DEFAULT_LRU_EXPIRY_MS);
                }
            }
        }
        return dateFromExpires;
    }
    
    private Object doRetrieveFromCache(final List<Object> list, final int n, final String s, final BranchNode branchNode, final Class clazz, final boolean b) {
        final Undefined undefined = null;
        Object orCreate;
        Object instance = orCreate = branchNode.getOrCreate(s);
        if (instance != null) {
            if (instance instanceof Ref || instance instanceof JsonPopulator) {
                final String string = list.subList(0, n).toString();
                FalkorCache$FalkorCacheable falkorCache$FalkorCacheable;
                if (b) {
                    falkorCache$FalkorCacheable = (FalkorCache$FalkorCacheable)this.mRealm.where((Class<RealmModel>)clazz).equalTo("path", string).greaterThan("expiry", new Date()).findFirst();
                }
                else {
                    falkorCache$FalkorCacheable = (FalkorCache$FalkorCacheable)this.mRealm.where((Class<RealmModel>)clazz).equalTo("path", string).findFirst();
                }
                if (falkorCache$FalkorCacheable instanceof FalkorCache$FalkorEvictable) {
                    FalkorCache$LruBackup.markAccessed((FalkorCache$FalkorEvictable)falkorCache$FalkorCacheable);
                }
                Object o;
                if (falkorCache$FalkorCacheable == null || StringUtils.isEmpty(falkorCache$FalkorCacheable.getPayload())) {
                    FalkorCache.getMonitor().miss();
                    o = undefined;
                }
                else {
                    final JsonElement jsonElement = NetflixApplication.getGson().fromJson(falkorCache$FalkorCacheable.getPayload(), JsonElement.class);
                    if (instance instanceof Ref) {
                        if (falkorCache$FalkorCacheable.getSentinel() || !jsonElement.isJsonArray()) {
                            instance = Undefined.getInstance();
                        }
                        else {
                            ((Ref)instance).setRefPath(PQL.fromJsonArray(jsonElement.getAsJsonArray()));
                        }
                        branchNode.set(s, instance);
                        o = instance;
                    }
                    else {
                        if (instance instanceof JsonPopulator) {
                            ((JsonPopulator)instance).populate(jsonElement);
                            branchNode.set(s, instance);
                        }
                        o = instance;
                    }
                    if (o instanceof BaseFalkorLeafItem && o != Undefined.getInstance()) {
                        ((BaseFalkorLeafItem)o).setLastModifiedInCache(falkorCache$FalkorCacheable.getLastModifiedTime());
                    }
                    FalkorCache.getMonitor().hit(falkorCache$FalkorCacheable.getPayload());
                }
                orCreate = o;
            }
            else {
                orCreate = instance;
                if (instance instanceof Sentinel) {
                    orCreate = instance;
                    if (((Sentinel<JsonPopulator>)instance).getValue() instanceof ListOfListOfGenres) {
                        final String string2 = list.subList(0, n).toString();
                        FalkorCache$FalkorCacheable falkorCache$FalkorCacheable2;
                        if (b) {
                            falkorCache$FalkorCacheable2 = (FalkorCache$FalkorCacheable)this.mRealm.where((Class<RealmModel>)clazz).equalTo("path", string2).greaterThan("expiry", new Date()).findFirst();
                        }
                        else {
                            falkorCache$FalkorCacheable2 = (FalkorCache$FalkorCacheable)this.mRealm.where((Class<RealmModel>)clazz).equalTo("path", string2).findFirst();
                        }
                        if (falkorCache$FalkorCacheable2 == null || StringUtils.isEmpty(falkorCache$FalkorCacheable2.getPayload())) {
                            return null;
                        }
                        ((Sentinel<JsonPopulator>)instance).getValue().populate(NetflixApplication.getGson().fromJson(falkorCache$FalkorCacheable2.getPayload(), JsonElement.class));
                        FalkorCache.getMonitor().hit(falkorCache$FalkorCacheable2.getPayload());
                        return instance;
                    }
                }
            }
        }
        return orCreate;
    }
    
    private static Class<? extends RealmModel> getRealmModelClass(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "lolomo": {
                return (Class<? extends RealmModel>)FalkorRealmCacheHomeLolomo.class;
            }
            case "flatGenre":
            case "topGenres":
            case "genreList":
            case "evidenceLists":
            case "lolomos":
            case "lists": {
                return (Class<? extends RealmModel>)FalkorRealmCacheTimeBased.class;
            }
            case "characters":
            case "movies":
            case "shows": {
                return (Class<? extends RealmModel>)FalkorRealmCacheLruBased.class;
            }
        }
    }
    
    @Override
    public void addToCache(ArrayList<String> string, final JsonElement jsonElement) {
        ThreadUtils.assertNotOnMain();
        try {
            final Class<? extends RealmModel> realmModelClass = getRealmModelClass(((ArrayList<String>)string).get(0));
            if (realmModelClass != null) {
                if (realmModelClass == FalkorRealmCacheHomeLolomo.class) {
                    string = jsonElement.toString();
                    FalkorCache$RealmAccess.executeTransaction(this.mRealm, (Realm$Transaction)new RealmFalkorCacheHelperImpl$1(this, string, jsonElement));
                    FalkorCache.getMonitor().add(string);
                    return;
                }
                FalkorCache$RealmAccess.executeTransaction(this.mRealm, (Realm$Transaction)new RealmFalkorCacheHelperImpl$FalkorTransaction(realmModelClass, (ArrayList)string, jsonElement, null));
            }
        }
        catch (IllegalArgumentException ex) {}
        catch (IllegalStateException string) {
            goto Label_0076;
        }
    }
    
    @Override
    public void beginTransaction() {
        FalkorCache$RealmAccess.beginTransaction(this.mRealm);
    }
    
    @Override
    public void cancelTransaction() {
        FalkorCache$RealmAccess.cancelTransaction(this.mRealm);
    }
    
    @Override
    public void close() {
        FalkorCache$RealmAccess.close(this.mRealm);
    }
    
    @Override
    public void commitTransaction() {
        FalkorCache$RealmAccess.commitTransaction(this.mRealm);
    }
    
    @Override
    public void deleteSubPath(final List<Object> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.getInstance:()Lio/realm/Realm;
        //     3: astore          5
        //     5: aconst_null    
        //     6: astore          4
        //     8: aload_1        
        //     9: iconst_0       
        //    10: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    15: checkcast       Ljava/lang/String;
        //    18: invokestatic    com/netflix/falkor/cache/RealmFalkorCacheHelperImpl.getRealmModelClass:(Ljava/lang/String;)Ljava/lang/Class;
        //    21: astore          7
        //    23: aload           7
        //    25: ifnull          76
        //    28: invokestatic    com/netflix/falkor/cache/FalkorCache.getMonitor:()Lcom/netflix/falkor/cache/FalkorCacheMonitor;
        //    31: pop            
        //    32: aload           5
        //    34: invokestatic    com/netflix/falkor/cache/FalkorCacheMonitor.getRealmTableCounts:(Lio/realm/Realm;)Lorg/json/JSONObject;
        //    37: astore          6
        //    39: invokestatic    java/lang/System.currentTimeMillis:()J
        //    42: lstore_2       
        //    43: aload           5
        //    45: new             Lcom/netflix/falkor/cache/RealmFalkorCacheHelperImpl$3;
        //    48: dup            
        //    49: aload_0        
        //    50: aload_1        
        //    51: aload           7
        //    53: invokespecial   com/netflix/falkor/cache/RealmFalkorCacheHelperImpl$3.<init>:(Lcom/netflix/falkor/cache/RealmFalkorCacheHelperImpl;Ljava/util/List;Ljava/lang/Class;)V
        //    56: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.executeTransaction:(Lio/realm/Realm;Lio/realm/Realm$Transaction;)V
        //    59: invokestatic    com/netflix/falkor/cache/FalkorCache.getMonitor:()Lcom/netflix/falkor/cache/FalkorCacheMonitor;
        //    62: ldc_w           "trim"
        //    65: invokestatic    java/lang/System.currentTimeMillis:()J
        //    68: lload_2        
        //    69: lsub           
        //    70: l2i            
        //    71: aload           6
        //    73: invokevirtual   com/netflix/falkor/cache/FalkorCacheMonitor.logCacheAction:(Ljava/lang/String;ILorg/json/JSONObject;)V
        //    76: aload           5
        //    78: ifnull          90
        //    81: iconst_0       
        //    82: ifeq            109
        //    85: aload           5
        //    87: invokevirtual   io/realm/Realm.close:()V
        //    90: return         
        //    91: astore_1       
        //    92: new             Ljava/lang/NullPointerException;
        //    95: dup            
        //    96: invokespecial   java/lang/NullPointerException.<init>:()V
        //    99: athrow         
        //   100: astore_1       
        //   101: ldc_w           "expireLolomoListsFromCache"
        //   104: aload_1        
        //   105: invokestatic    com/netflix/mediaclient/util/LogUtils.reportErrorSafely:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   108: return         
        //   109: aload           5
        //   111: invokevirtual   io/realm/Realm.close:()V
        //   114: return         
        //   115: astore          4
        //   117: aload           4
        //   119: athrow         
        //   120: astore_1       
        //   121: aload           5
        //   123: ifnull          136
        //   126: aload           4
        //   128: ifnull          150
        //   131: aload           5
        //   133: invokevirtual   io/realm/Realm.close:()V
        //   136: aload_1        
        //   137: athrow         
        //   138: astore          5
        //   140: aload           4
        //   142: aload           5
        //   144: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   147: goto            136
        //   150: aload           5
        //   152: invokevirtual   io/realm/Realm.close:()V
        //   155: goto            136
        //   158: astore_1       
        //   159: goto            121
        //    Signature:
        //  (Ljava/util/List<Ljava/lang/Object;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      5      100    109    Ljava/lang/Exception;
        //  8      23     115    121    Ljava/lang/Throwable;
        //  8      23     158    162    Any
        //  28     76     115    121    Ljava/lang/Throwable;
        //  28     76     158    162    Any
        //  85     90     91     100    Ljava/lang/Throwable;
        //  85     90     100    109    Ljava/lang/Exception;
        //  92     100    100    109    Ljava/lang/Exception;
        //  109    114    100    109    Ljava/lang/Exception;
        //  117    120    120    121    Any
        //  131    136    138    150    Ljava/lang/Throwable;
        //  131    136    100    109    Ljava/lang/Exception;
        //  136    138    100    109    Ljava/lang/Exception;
        //  140    147    100    109    Ljava/lang/Exception;
        //  150    155    100    109    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 77, Size: 77
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
    
    @Override
    public void expireLolomoListsFromCache() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.getInstance:()Lio/realm/Realm;
        //     3: astore          5
        //     5: aconst_null    
        //     6: astore_3       
        //     7: invokestatic    com/netflix/falkor/cache/FalkorCache.getMonitor:()Lcom/netflix/falkor/cache/FalkorCacheMonitor;
        //    10: pop            
        //    11: aload           5
        //    13: invokestatic    com/netflix/falkor/cache/FalkorCacheMonitor.getRealmTableCounts:(Lio/realm/Realm;)Lorg/json/JSONObject;
        //    16: astore          4
        //    18: invokestatic    java/lang/System.currentTimeMillis:()J
        //    21: lstore_1       
        //    22: aload           5
        //    24: new             Lcom/netflix/falkor/cache/RealmFalkorCacheHelperImpl$2;
        //    27: dup            
        //    28: aload_0        
        //    29: invokespecial   com/netflix/falkor/cache/RealmFalkorCacheHelperImpl$2.<init>:(Lcom/netflix/falkor/cache/RealmFalkorCacheHelperImpl;)V
        //    32: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.executeTransaction:(Lio/realm/Realm;Lio/realm/Realm$Transaction;)V
        //    35: invokestatic    com/netflix/falkor/cache/FalkorCache.getMonitor:()Lcom/netflix/falkor/cache/FalkorCacheMonitor;
        //    38: ldc_w           "trim"
        //    41: invokestatic    java/lang/System.currentTimeMillis:()J
        //    44: lload_1        
        //    45: lsub           
        //    46: l2i            
        //    47: aload           4
        //    49: invokevirtual   com/netflix/falkor/cache/FalkorCacheMonitor.logCacheAction:(Ljava/lang/String;ILorg/json/JSONObject;)V
        //    52: aload           5
        //    54: ifnull          66
        //    57: iconst_0       
        //    58: ifeq            85
        //    61: aload           5
        //    63: invokevirtual   io/realm/Realm.close:()V
        //    66: return         
        //    67: astore_3       
        //    68: new             Ljava/lang/NullPointerException;
        //    71: dup            
        //    72: invokespecial   java/lang/NullPointerException.<init>:()V
        //    75: athrow         
        //    76: astore_3       
        //    77: ldc_w           "expireLolomoListsFromCache"
        //    80: aload_3        
        //    81: invokestatic    com/netflix/mediaclient/util/LogUtils.reportErrorSafely:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    84: return         
        //    85: aload           5
        //    87: invokevirtual   io/realm/Realm.close:()V
        //    90: return         
        //    91: astore_3       
        //    92: aload_3        
        //    93: athrow         
        //    94: astore          4
        //    96: aload           5
        //    98: ifnull          110
        //   101: aload_3        
        //   102: ifnull          124
        //   105: aload           5
        //   107: invokevirtual   io/realm/Realm.close:()V
        //   110: aload           4
        //   112: athrow         
        //   113: astore          5
        //   115: aload_3        
        //   116: aload           5
        //   118: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   121: goto            110
        //   124: aload           5
        //   126: invokevirtual   io/realm/Realm.close:()V
        //   129: goto            110
        //   132: astore          4
        //   134: goto            96
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      5      76     85     Ljava/lang/Exception;
        //  7      52     91     96     Ljava/lang/Throwable;
        //  7      52     132    137    Any
        //  61     66     67     76     Ljava/lang/Throwable;
        //  61     66     76     85     Ljava/lang/Exception;
        //  68     76     76     85     Ljava/lang/Exception;
        //  85     90     76     85     Ljava/lang/Exception;
        //  92     94     94     96     Any
        //  105    110    113    124    Ljava/lang/Throwable;
        //  105    110    76     85     Ljava/lang/Exception;
        //  110    113    76     85     Ljava/lang/Exception;
        //  115    121    76     85     Ljava/lang/Exception;
        //  124    129    76     85     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 67, Size: 67
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
    
    @Override
    public void purgeCache() {
        FalkorCache.getMonitor().logCacheAction("purge", 0, null);
        FalkorCache.getMonitor().resetAll();
        try {
            FalkorCache$RealmAccess.purge();
        }
        catch (IllegalStateException ex) {
            LogUtils.reportErrorSafely("purgeCache", (Throwable)ex);
        }
    }
    
    @Override
    public Object retrieveFromCache(List<Object> doRetrieveFromCache, final int n, final String s, final BranchNode branchNode) {
        Object o = null;
        if (!((List)doRetrieveFromCache).isEmpty()) {
            o = o;
            if (!s.isEmpty()) {
                Label_0191: {
                    try {
                        final String s2 = ((List<String>)doRetrieveFromCache).get(0);
                        final Class<? extends RealmModel> realmModelClass = getRealmModelClass(s2);
                        if (realmModelClass != null) {
                            if (realmModelClass != FalkorRealmCacheHomeLolomo.class) {
                                doRetrieveFromCache = this.doRetrieveFromCache((List<Object>)doRetrieveFromCache, n, s, branchNode, realmModelClass, true);
                                break Label_0191;
                            }
                            doRetrieveFromCache = new Date();
                            final FalkorRealmCacheHomeLolomo falkorRealmCacheHomeLolomo = (FalkorRealmCacheHomeLolomo)this.mRealm.where(FalkorRealmCacheHomeLolomo.class).greaterThan("expiry", (Date)doRetrieveFromCache).findFirst();
                            if (falkorRealmCacheHomeLolomo != null) {
                                doRetrieveFromCache = new Ref(PQL.fromJsonArray(NetflixApplication.getGson().fromJson(falkorRealmCacheHomeLolomo.getLolomosRef(), JsonElement.class).getAsJsonArray()));
                                branchNode.set(s2, doRetrieveFromCache);
                                FalkorCache.getMonitor().hit(falkorRealmCacheHomeLolomo.getLolomosRef());
                                break Label_0191;
                            }
                            FalkorCache.getMonitor().miss();
                            doRetrieveFromCache = null;
                            break Label_0191;
                        }
                    }
                    catch (IllegalStateException ex) {}
                    catch (JsonSyntaxException doRetrieveFromCache) {
                        goto Label_0172;
                    }
                    catch (IllegalArgumentException doRetrieveFromCache) {
                        goto Label_0172;
                    }
                    doRetrieveFromCache = null;
                }
                o = doRetrieveFromCache;
            }
        }
        return o;
    }
}
