// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import android.media.SoundPool$OnLoadCompleteListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AudioUtils;
import java.util.HashMap;
import android.media.SoundPool;
import java.util.Map;

public class KongSoundPoolManager
{
    public static final int LOOP = 0;
    public static final int PRIORITY = 1;
    public static final float RATE = 1.0f;
    private static final String TAG = "KongSoundPoolManager";
    KongInteractivePostPlayManager postPlayManager;
    Map<Integer, Boolean> soundIdToLoadedMap;
    private SoundPool soundPool;
    Map<String, Integer> soundUrlToIdMap;
    
    KongSoundPoolManager(final KongInteractivePostPlayManager postPlayManager) {
        this.soundIdToLoadedMap = new HashMap<Integer, Boolean>();
        this.soundUrlToIdMap = new HashMap<String, Integer>();
        this.postPlayManager = postPlayManager;
    }
    
    public void autoPause() {
        if (this.soundPool != null) {
            this.soundPool.autoPause();
        }
    }
    
    public void autoResume() {
        if (this.soundPool != null) {
            this.soundPool.autoResume();
        }
    }
    
    void createSoundPool() {
        this.soundPool = AudioUtils.createSoundPool(5);
        if (this.soundPool == null && Log.isLoggable()) {
            Log.e("KongSoundPoolManager", "SoundPool instance is null.");
            return;
        }
        this.soundPool.setOnLoadCompleteListener((SoundPool$OnLoadCompleteListener)new KongSoundPoolManager$1(this));
    }
    
    public boolean isReady() {
        return this.soundIdToLoadedMap.size() == this.soundUrlToIdMap.size();
    }
    
    int loadSoundPoolVo(final LocalCachedFileMetadata p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_2       
        //     2: aload_0        
        //     3: getfield        com/netflix/mediaclient/ui/iko/kong/postplay/KongSoundPoolManager.soundPool:Landroid/media/SoundPool;
        //     6: ifnonnull       19
        //     9: ldc             "KongSoundPoolManager"
        //    11: ldc             "Sound is null. Request to load url failed "
        //    13: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    16: pop            
        //    17: iload_2        
        //    18: ireturn        
        //    19: aload_1        
        //    20: ifnull          17
        //    23: aload_1        
        //    24: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getLocalUrl:()Ljava/lang/String;
        //    27: astore          6
        //    29: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    32: ifeq            61
        //    35: ldc             "KongSoundPoolManager"
        //    37: new             Ljava/lang/StringBuilder;
        //    40: dup            
        //    41: invokespecial   java/lang/StringBuilder.<init>:()V
        //    44: ldc             "Loading audio from cache for Local url = "
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    49: aload           6
        //    51: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    54: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    57: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    60: pop            
        //    61: aload_0        
        //    62: getfield        com/netflix/mediaclient/ui/iko/kong/postplay/KongSoundPoolManager.soundUrlToIdMap:Ljava/util/Map;
        //    65: aload           6
        //    67: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //    72: ifeq            128
        //    75: aload_0        
        //    76: getfield        com/netflix/mediaclient/ui/iko/kong/postplay/KongSoundPoolManager.soundUrlToIdMap:Ljava/util/Map;
        //    79: aload           6
        //    81: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    86: checkcast       Ljava/lang/Integer;
        //    89: invokevirtual   java/lang/Integer.intValue:()I
        //    92: istore_3       
        //    93: iload_3        
        //    94: istore_2       
        //    95: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    98: ifeq            17
        //   101: ldc             "KongSoundPoolManager"
        //   103: new             Ljava/lang/StringBuilder;
        //   106: dup            
        //   107: invokespecial   java/lang/StringBuilder.<init>:()V
        //   110: ldc             "Sound pool id loaded is "
        //   112: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   115: iload_3        
        //   116: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   122: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   125: pop            
        //   126: iload_3        
        //   127: ireturn        
        //   128: new             Ljava/io/File;
        //   131: dup            
        //   132: aload           6
        //   134: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   137: astore          4
        //   139: aload           4
        //   141: invokevirtual   java/io/File.exists:()Z
        //   144: ifeq            501
        //   147: new             Ljava/io/FileInputStream;
        //   150: dup            
        //   151: aload           4
        //   153: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   156: astore          4
        //   158: aload           4
        //   160: astore          5
        //   162: aload_0        
        //   163: getfield        com/netflix/mediaclient/ui/iko/kong/postplay/KongSoundPoolManager.soundPool:Landroid/media/SoundPool;
        //   166: aload           4
        //   168: invokevirtual   java/io/FileInputStream.getFD:()Ljava/io/FileDescriptor;
        //   171: aload_1        
        //   172: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getByteOffset:()J
        //   175: aload_1        
        //   176: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getByteSize:()J
        //   179: iconst_1       
        //   180: invokevirtual   android/media/SoundPool.load:(Ljava/io/FileDescriptor;JJI)I
        //   183: istore_2       
        //   184: aload           4
        //   186: astore          5
        //   188: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   191: ifeq            223
        //   194: aload           4
        //   196: astore          5
        //   198: ldc             "KongSoundPoolManager"
        //   200: new             Ljava/lang/StringBuilder;
        //   203: dup            
        //   204: invokespecial   java/lang/StringBuilder.<init>:()V
        //   207: ldc             "Sound pool id loaded is "
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: iload_2        
        //   213: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   216: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   219: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   222: pop            
        //   223: aload           4
        //   225: invokevirtual   java/io/FileInputStream.close:()V
        //   228: aload_0        
        //   229: getfield        com/netflix/mediaclient/ui/iko/kong/postplay/KongSoundPoolManager.soundUrlToIdMap:Ljava/util/Map;
        //   232: aload           6
        //   234: iload_2        
        //   235: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   238: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   243: pop            
        //   244: iload_2        
        //   245: ireturn        
        //   246: astore_1       
        //   247: ldc             "KongSoundPoolManager"
        //   249: new             Ljava/lang/StringBuilder;
        //   252: dup            
        //   253: invokespecial   java/lang/StringBuilder.<init>:()V
        //   256: ldc             "IOException while closing input stream for file "
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: aload           6
        //   263: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   269: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   272: pop            
        //   273: goto            228
        //   276: astore_1       
        //   277: aconst_null    
        //   278: astore_1       
        //   279: iconst_m1      
        //   280: istore_2       
        //   281: ldc             "KongSoundPoolManager"
        //   283: new             Ljava/lang/StringBuilder;
        //   286: dup            
        //   287: invokespecial   java/lang/StringBuilder.<init>:()V
        //   290: ldc             "FileNotFoundException while loading resource from cache file "
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: aload           6
        //   297: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   300: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   303: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   306: pop            
        //   307: aload_1        
        //   308: invokevirtual   java/io/FileInputStream.close:()V
        //   311: goto            228
        //   314: astore_1       
        //   315: ldc             "KongSoundPoolManager"
        //   317: new             Ljava/lang/StringBuilder;
        //   320: dup            
        //   321: invokespecial   java/lang/StringBuilder.<init>:()V
        //   324: ldc             "IOException while closing input stream for file "
        //   326: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   329: aload           6
        //   331: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   334: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   337: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   340: pop            
        //   341: goto            228
        //   344: astore_1       
        //   345: aconst_null    
        //   346: astore          4
        //   348: iconst_m1      
        //   349: istore_2       
        //   350: aload           4
        //   352: astore          5
        //   354: ldc             "KongSoundPoolManager"
        //   356: new             Ljava/lang/StringBuilder;
        //   359: dup            
        //   360: invokespecial   java/lang/StringBuilder.<init>:()V
        //   363: ldc             "IOException while loading resource from cache file "
        //   365: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   368: aload           6
        //   370: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   373: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   376: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   379: pop            
        //   380: aload           4
        //   382: invokevirtual   java/io/FileInputStream.close:()V
        //   385: goto            228
        //   388: astore_1       
        //   389: ldc             "KongSoundPoolManager"
        //   391: new             Ljava/lang/StringBuilder;
        //   394: dup            
        //   395: invokespecial   java/lang/StringBuilder.<init>:()V
        //   398: ldc             "IOException while closing input stream for file "
        //   400: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   403: aload           6
        //   405: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   408: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   411: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   414: pop            
        //   415: goto            228
        //   418: astore_1       
        //   419: aconst_null    
        //   420: astore          5
        //   422: aload           5
        //   424: invokevirtual   java/io/FileInputStream.close:()V
        //   427: aload_1        
        //   428: athrow         
        //   429: astore          4
        //   431: ldc             "KongSoundPoolManager"
        //   433: new             Ljava/lang/StringBuilder;
        //   436: dup            
        //   437: invokespecial   java/lang/StringBuilder.<init>:()V
        //   440: ldc             "IOException while closing input stream for file "
        //   442: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   445: aload           6
        //   447: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   450: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   453: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   456: pop            
        //   457: goto            427
        //   460: astore_1       
        //   461: goto            422
        //   464: astore          4
        //   466: aload_1        
        //   467: astore          5
        //   469: aload           4
        //   471: astore_1       
        //   472: goto            422
        //   475: astore_1       
        //   476: iconst_m1      
        //   477: istore_2       
        //   478: goto            350
        //   481: astore_1       
        //   482: goto            350
        //   485: astore_1       
        //   486: aload           4
        //   488: astore_1       
        //   489: iconst_m1      
        //   490: istore_2       
        //   491: goto            281
        //   494: astore_1       
        //   495: aload           4
        //   497: astore_1       
        //   498: goto            281
        //   501: iconst_m1      
        //   502: istore_2       
        //   503: goto            228
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  147    158    276    281    Ljava/io/FileNotFoundException;
        //  147    158    344    350    Ljava/io/IOException;
        //  147    158    418    422    Any
        //  162    184    485    494    Ljava/io/FileNotFoundException;
        //  162    184    475    481    Ljava/io/IOException;
        //  162    184    460    464    Any
        //  188    194    494    501    Ljava/io/FileNotFoundException;
        //  188    194    481    485    Ljava/io/IOException;
        //  188    194    460    464    Any
        //  198    223    494    501    Ljava/io/FileNotFoundException;
        //  198    223    481    485    Ljava/io/IOException;
        //  198    223    460    464    Any
        //  223    228    246    276    Ljava/io/IOException;
        //  281    307    464    475    Any
        //  307    311    314    344    Ljava/io/IOException;
        //  354    380    460    464    Any
        //  380    385    388    418    Ljava/io/IOException;
        //  422    427    429    460    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    void playSoundPoolId(final int n, final float n2) {
        if (n < 0) {
            Log.e("KongSoundPoolManager", "Received an invalid sound id. Ignoring request to play sound.");
        }
        else {
            if (!this.soundIdToLoadedMap.containsKey(n)) {
                Log.e("KongSoundPoolManager", "Received a sound id that is not loaded in to memory yet. Ignoring request to play sound.");
                return;
            }
            final Boolean b = this.soundIdToLoadedMap.get(n);
            if (b != null && !b) {
                Log.e("KongSoundPoolManager", "Received a sound id that failed to load to memory. Ignoring request to play sound.");
                return;
            }
            if (this.soundPool != null && !this.postPlayManager.isPostPlayPaused()) {
                this.soundPool.stop(n);
                if (this.soundPool.play(n, n2, n2, 1, 0, 1.0f) == 0) {
                    Log.e("KongSoundPoolManager", "Request to play stream = " + n + " failed");
                    return;
                }
                if (Log.isLoggable()) {
                    Log.d("KongSoundPoolManager", "Request to play stream = " + n + " was successful");
                }
            }
        }
    }
    
    public void release() {
        if (this.soundPool != null) {
            this.soundPool.release();
            this.soundPool = null;
        }
    }
}
