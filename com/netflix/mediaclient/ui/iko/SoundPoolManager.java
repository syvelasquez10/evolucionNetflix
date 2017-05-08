// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import android.media.SoundPool$OnLoadCompleteListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AudioUtils;
import java.util.HashMap;
import android.media.SoundPool;
import java.util.Map;

public class SoundPoolManager
{
    public static final int LOOP = 0;
    public static final int PRIORITY = 1;
    public static final float RATE = 1.0f;
    private static final String TAG = "SoundPoolManager";
    Map<Integer, Boolean> soundIdToLoadedMap;
    private SoundPool soundPool;
    Map<String, Integer> soundUrlToIdMap;
    
    public SoundPoolManager() {
        this.soundIdToLoadedMap = new HashMap<Integer, Boolean>();
        this.soundUrlToIdMap = new HashMap<String, Integer>();
        this.createSoundPool();
    }
    
    private void createSoundPool() {
        this.soundPool = AudioUtils.createSoundPool(5);
        if (this.soundPool == null && Log.isLoggable()) {
            Log.e("SoundPoolManager", "SoundPool instance is null.");
            return;
        }
        this.soundPool.setOnLoadCompleteListener((SoundPool$OnLoadCompleteListener)new SoundPoolManager$1(this));
    }
    
    public void autoPause() {
        if (Log.isLoggable()) {
            Log.d("SoundPoolManager", "autoPause: invoked");
        }
        if (this.soundPool != null) {
            this.soundPool.autoPause();
        }
    }
    
    public void autoResume() {
        if (Log.isLoggable()) {
            Log.d("SoundPoolManager", "autoResume: invoked");
        }
        if (this.soundPool != null) {
            this.soundPool.autoResume();
        }
    }
    
    public boolean isReady() {
        return this.soundIdToLoadedMap.size() == this.soundUrlToIdMap.size();
    }
    
    public int loadSoundPoolVo(final LocalCachedFileMetadata p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_m1      
        //     1: istore_2       
        //     2: invokestatic    com/netflix/mediaclient/util/ThreadUtils.assertNotOnMain:()Z
        //     5: pop            
        //     6: aload_0        
        //     7: getfield        com/netflix/mediaclient/ui/iko/SoundPoolManager.soundPool:Landroid/media/SoundPool;
        //    10: ifnonnull       23
        //    13: ldc             "SoundPoolManager"
        //    15: ldc             "Sound is null. Request to load url failed "
        //    17: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    20: pop            
        //    21: iload_2        
        //    22: ireturn        
        //    23: aload_1        
        //    24: ifnull          21
        //    27: aload_1        
        //    28: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getLocalUrl:()Ljava/lang/String;
        //    31: astore          6
        //    33: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    36: ifeq            65
        //    39: ldc             "SoundPoolManager"
        //    41: new             Ljava/lang/StringBuilder;
        //    44: dup            
        //    45: invokespecial   java/lang/StringBuilder.<init>:()V
        //    48: ldc             "Loading audio from cache for Local url = "
        //    50: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    53: aload           6
        //    55: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    58: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    61: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    64: pop            
        //    65: aload_0        
        //    66: getfield        com/netflix/mediaclient/ui/iko/SoundPoolManager.soundUrlToIdMap:Ljava/util/Map;
        //    69: aload           6
        //    71: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //    76: ifeq            132
        //    79: aload_0        
        //    80: getfield        com/netflix/mediaclient/ui/iko/SoundPoolManager.soundUrlToIdMap:Ljava/util/Map;
        //    83: aload           6
        //    85: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    90: checkcast       Ljava/lang/Integer;
        //    93: invokevirtual   java/lang/Integer.intValue:()I
        //    96: istore_3       
        //    97: iload_3        
        //    98: istore_2       
        //    99: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   102: ifeq            21
        //   105: ldc             "SoundPoolManager"
        //   107: new             Ljava/lang/StringBuilder;
        //   110: dup            
        //   111: invokespecial   java/lang/StringBuilder.<init>:()V
        //   114: ldc             "Sound pool id loaded is "
        //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: iload_3        
        //   120: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   123: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   126: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   129: pop            
        //   130: iload_3        
        //   131: ireturn        
        //   132: new             Ljava/io/File;
        //   135: dup            
        //   136: aload           6
        //   138: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   141: astore          4
        //   143: aload           4
        //   145: invokevirtual   java/io/File.exists:()Z
        //   148: ifeq            505
        //   151: new             Ljava/io/FileInputStream;
        //   154: dup            
        //   155: aload           4
        //   157: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   160: astore          4
        //   162: aload           4
        //   164: astore          5
        //   166: aload_0        
        //   167: getfield        com/netflix/mediaclient/ui/iko/SoundPoolManager.soundPool:Landroid/media/SoundPool;
        //   170: aload           4
        //   172: invokevirtual   java/io/FileInputStream.getFD:()Ljava/io/FileDescriptor;
        //   175: aload_1        
        //   176: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getByteOffset:()J
        //   179: aload_1        
        //   180: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getByteSize:()J
        //   183: iconst_1       
        //   184: invokevirtual   android/media/SoundPool.load:(Ljava/io/FileDescriptor;JJI)I
        //   187: istore_2       
        //   188: aload           4
        //   190: astore          5
        //   192: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   195: ifeq            227
        //   198: aload           4
        //   200: astore          5
        //   202: ldc             "SoundPoolManager"
        //   204: new             Ljava/lang/StringBuilder;
        //   207: dup            
        //   208: invokespecial   java/lang/StringBuilder.<init>:()V
        //   211: ldc             "Sound pool id loaded is "
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: iload_2        
        //   217: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   220: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   223: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   226: pop            
        //   227: aload           4
        //   229: invokevirtual   java/io/FileInputStream.close:()V
        //   232: aload_0        
        //   233: getfield        com/netflix/mediaclient/ui/iko/SoundPoolManager.soundUrlToIdMap:Ljava/util/Map;
        //   236: aload           6
        //   238: iload_2        
        //   239: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   242: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   247: pop            
        //   248: iload_2        
        //   249: ireturn        
        //   250: astore_1       
        //   251: ldc             "SoundPoolManager"
        //   253: new             Ljava/lang/StringBuilder;
        //   256: dup            
        //   257: invokespecial   java/lang/StringBuilder.<init>:()V
        //   260: ldc             "IOException while closing input stream for file "
        //   262: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   265: aload           6
        //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   273: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   276: pop            
        //   277: goto            232
        //   280: astore_1       
        //   281: aconst_null    
        //   282: astore_1       
        //   283: iconst_m1      
        //   284: istore_2       
        //   285: ldc             "SoundPoolManager"
        //   287: new             Ljava/lang/StringBuilder;
        //   290: dup            
        //   291: invokespecial   java/lang/StringBuilder.<init>:()V
        //   294: ldc             "FileNotFoundException while loading resource from cache file "
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: aload           6
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   307: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   310: pop            
        //   311: aload_1        
        //   312: invokevirtual   java/io/FileInputStream.close:()V
        //   315: goto            232
        //   318: astore_1       
        //   319: ldc             "SoundPoolManager"
        //   321: new             Ljava/lang/StringBuilder;
        //   324: dup            
        //   325: invokespecial   java/lang/StringBuilder.<init>:()V
        //   328: ldc             "IOException while closing input stream for file "
        //   330: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   333: aload           6
        //   335: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   338: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   341: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   344: pop            
        //   345: goto            232
        //   348: astore_1       
        //   349: aconst_null    
        //   350: astore          4
        //   352: iconst_m1      
        //   353: istore_2       
        //   354: aload           4
        //   356: astore          5
        //   358: ldc             "SoundPoolManager"
        //   360: new             Ljava/lang/StringBuilder;
        //   363: dup            
        //   364: invokespecial   java/lang/StringBuilder.<init>:()V
        //   367: ldc             "IOException while loading resource from cache file "
        //   369: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   372: aload           6
        //   374: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   377: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   380: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   383: pop            
        //   384: aload           4
        //   386: invokevirtual   java/io/FileInputStream.close:()V
        //   389: goto            232
        //   392: astore_1       
        //   393: ldc             "SoundPoolManager"
        //   395: new             Ljava/lang/StringBuilder;
        //   398: dup            
        //   399: invokespecial   java/lang/StringBuilder.<init>:()V
        //   402: ldc             "IOException while closing input stream for file "
        //   404: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   407: aload           6
        //   409: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   412: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   415: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   418: pop            
        //   419: goto            232
        //   422: astore_1       
        //   423: aconst_null    
        //   424: astore          5
        //   426: aload           5
        //   428: invokevirtual   java/io/FileInputStream.close:()V
        //   431: aload_1        
        //   432: athrow         
        //   433: astore          4
        //   435: ldc             "SoundPoolManager"
        //   437: new             Ljava/lang/StringBuilder;
        //   440: dup            
        //   441: invokespecial   java/lang/StringBuilder.<init>:()V
        //   444: ldc             "IOException while closing input stream for file "
        //   446: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   449: aload           6
        //   451: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   454: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   457: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   460: pop            
        //   461: goto            431
        //   464: astore_1       
        //   465: goto            426
        //   468: astore          4
        //   470: aload_1        
        //   471: astore          5
        //   473: aload           4
        //   475: astore_1       
        //   476: goto            426
        //   479: astore_1       
        //   480: iconst_m1      
        //   481: istore_2       
        //   482: goto            354
        //   485: astore_1       
        //   486: goto            354
        //   489: astore_1       
        //   490: aload           4
        //   492: astore_1       
        //   493: iconst_m1      
        //   494: istore_2       
        //   495: goto            285
        //   498: astore_1       
        //   499: aload           4
        //   501: astore_1       
        //   502: goto            285
        //   505: iconst_m1      
        //   506: istore_2       
        //   507: goto            232
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  151    162    280    285    Ljava/io/FileNotFoundException;
        //  151    162    348    354    Ljava/io/IOException;
        //  151    162    422    426    Any
        //  166    188    489    498    Ljava/io/FileNotFoundException;
        //  166    188    479    485    Ljava/io/IOException;
        //  166    188    464    468    Any
        //  192    198    498    505    Ljava/io/FileNotFoundException;
        //  192    198    485    489    Ljava/io/IOException;
        //  192    198    464    468    Any
        //  202    227    498    505    Ljava/io/FileNotFoundException;
        //  202    227    485    489    Ljava/io/IOException;
        //  202    227    464    468    Any
        //  227    232    250    280    Ljava/io/IOException;
        //  285    311    468    479    Any
        //  311    315    318    348    Ljava/io/IOException;
        //  358    384    464    468    Any
        //  384    389    392    422    Ljava/io/IOException;
        //  426    431    433    464    Ljava/io/IOException;
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
    
    public void playSoundPoolId(final int n, final float n2) {
        if (n < 0) {
            Log.d("SoundPoolManager", "Received an invalid sound id. Ignoring request to play sound.");
        }
        else if (!this.soundIdToLoadedMap.containsKey(n)) {
            if (Log.isLoggable()) {
                Log.d("SoundPoolManager", "Received a sound id that is not loaded in to memory yet. Ignoring request to play sound.");
            }
        }
        else {
            final Boolean b = this.soundIdToLoadedMap.get(n);
            if (b != null && !b) {
                if (Log.isLoggable()) {
                    Log.d("SoundPoolManager", "Received a sound id that failed to load to memory. Ignoring request to play sound.");
                }
            }
            else if (this.soundPool != null) {
                this.soundPool.stop(n);
                if (this.soundPool.play(n, n2, n2, 1, 0, 1.0f) == 0) {
                    Log.d("SoundPoolManager", "Request to play stream = " + n + " failed");
                    return;
                }
                if (Log.isLoggable()) {
                    Log.d("SoundPoolManager", "Request to play stream = " + n + " was successful");
                }
            }
        }
    }
    
    public void release() {
        if (Log.isLoggable()) {
            Log.d("SoundPoolManager", "release: invoked");
        }
        if (this.soundPool != null) {
            this.soundPool.release();
            this.soundPool = null;
        }
    }
}
