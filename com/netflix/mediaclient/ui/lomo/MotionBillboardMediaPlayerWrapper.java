// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.graphics.SurfaceTexture;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.TextureView;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;

public class MotionBillboardMediaPlayerWrapper extends MediaPlayerWrapper
{
    private static final String TAG = "MotionBillboardMediaPlayerWrapper";
    
    public MotionBillboardMediaPlayerWrapper(final TextureView textureView, final boolean b, final int n, final float n2, final IClientLogging$AssetType clientLogging$AssetType, final MediaPlayerWrapper$PlaybackEventsListener mediaPlayerWrapper$PlaybackEventsListener) {
        super(textureView, b, n, n2, clientLogging$AssetType, mediaPlayerWrapper$PlaybackEventsListener);
    }
    
    @Override
    public void onSurfaceTextureAvailable(final SurfaceTexture p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: new             Landroid/view/Surface;
        //     4: dup            
        //     5: aload_1        
        //     6: invokespecial   android/view/Surface.<init>:(Landroid/graphics/SurfaceTexture;)V
        //     9: putfield        com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.videoSurface:Landroid/view/Surface;
        //    12: aload_0        
        //    13: iconst_1       
        //    14: putfield        com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.surfaceReady:Z
        //    17: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    20: ifeq            51
        //    23: ldc             "MotionBillboardMediaPlayerWrapper"
        //    25: new             Ljava/lang/StringBuilder;
        //    28: dup            
        //    29: invokespecial   java/lang/StringBuilder.<init>:()V
        //    32: aload_0        
        //    33: getfield        com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.assetType:Lcom/netflix/mediaclient/servicemgr/IClientLogging$AssetType;
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    39: ldc             ": SurfaceTexture available, starting playback"
        //    41: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    44: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    47: invokestatic    com/netflix/mediaclient/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    50: pop            
        //    51: aload_0        
        //    52: getfield        com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.localUrl:Ljava/lang/String;
        //    55: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    58: ifne            196
        //    61: new             Ljava/io/File;
        //    64: dup            
        //    65: aload_0        
        //    66: getfield        com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.localUrl:Ljava/lang/String;
        //    69: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    72: astore_1       
        //    73: new             Ljava/io/FileInputStream;
        //    76: dup            
        //    77: aload_1        
        //    78: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    81: astore_1       
        //    82: aload_1        
        //    83: invokevirtual   java/io/FileInputStream.getFD:()Ljava/io/FileDescriptor;
        //    86: astore          6
        //    88: new             Landroid/media/MediaMetadataRetriever;
        //    91: dup            
        //    92: invokespecial   android/media/MediaMetadataRetriever.<init>:()V
        //    95: astore          7
        //    97: aload           7
        //    99: aload           6
        //   101: aload_0        
        //   102: getfield        com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.offset:J
        //   105: aload_0        
        //   106: getfield        com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.length:J
        //   109: invokevirtual   android/media/MediaMetadataRetriever.setDataSource:(Ljava/io/FileDescriptor;JJ)V
        //   112: aload           7
        //   114: bipush          19
        //   116: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //   119: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   122: fstore          5
        //   124: aload           7
        //   126: bipush          18
        //   128: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //   131: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   134: fstore          4
        //   136: aload_1        
        //   137: invokevirtual   java/io/FileInputStream.close:()V
        //   140: iload_2        
        //   141: iload_3        
        //   142: if_icmple       252
        //   145: iload_2        
        //   146: i2f            
        //   147: fload           4
        //   149: fdiv           
        //   150: iload_3        
        //   151: i2f            
        //   152: fload           5
        //   154: fdiv           
        //   155: fdiv           
        //   156: fstore          4
        //   158: iload_2        
        //   159: iconst_2       
        //   160: idiv           
        //   161: istore_2       
        //   162: fload           5
        //   164: ldc             0.4
        //   166: fmul           
        //   167: f2i            
        //   168: istore_3       
        //   169: new             Landroid/graphics/Matrix;
        //   172: dup            
        //   173: invokespecial   android/graphics/Matrix.<init>:()V
        //   176: astore_1       
        //   177: aload_1        
        //   178: fconst_1       
        //   179: fload           4
        //   181: iload_2        
        //   182: i2f            
        //   183: iload_3        
        //   184: i2f            
        //   185: invokevirtual   android/graphics/Matrix.setScale:(FFFF)V
        //   188: aload_0        
        //   189: getfield        com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.mTextureView:Landroid/view/TextureView;
        //   192: aload_1        
        //   193: invokevirtual   android/view/TextureView.setTransform:(Landroid/graphics/Matrix;)V
        //   196: aload_0        
        //   197: invokevirtual   com/netflix/mediaclient/ui/lomo/MotionBillboardMediaPlayerWrapper.initializeMediaPlayer:()V
        //   200: return         
        //   201: astore_1       
        //   202: fconst_0       
        //   203: fstore          4
        //   205: fconst_0       
        //   206: fstore          5
        //   208: ldc             "SPY-9199 Failed to retrieve MediaMetadata"
        //   210: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/String;)V
        //   213: aload_1        
        //   214: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   217: goto            140
        //   220: astore_1       
        //   221: fconst_0       
        //   222: fstore          4
        //   224: goto            208
        //   227: astore_1       
        //   228: goto            208
        //   231: astore_1       
        //   232: fconst_0       
        //   233: fstore          4
        //   235: fconst_0       
        //   236: fstore          5
        //   238: goto            208
        //   241: astore_1       
        //   242: fconst_0       
        //   243: fstore          4
        //   245: goto            208
        //   248: astore_1       
        //   249: goto            208
        //   252: fconst_1       
        //   253: fstore          4
        //   255: goto            158
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  73     124    231    241    Ljava/io/IOException;
        //  73     124    201    208    Ljava/lang/IllegalStateException;
        //  124    136    241    248    Ljava/io/IOException;
        //  124    136    220    227    Ljava/lang/IllegalStateException;
        //  136    140    248    252    Ljava/io/IOException;
        //  136    140    227    231    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 136, Size: 136
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
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
}
