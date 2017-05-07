// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.SubtitleUtils;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;

public abstract class BaseSubtitleParser implements SubtitleParser
{
    protected static final String TAG = "nf_subtitles";
    protected final TextStyle mDefault;
    protected final TextStyle mDeviceDefault;
    protected int mIndexOfLastSearch;
    protected PlayerAgent mPlayer;
    protected boolean mReady;
    protected final TextStyle mRegionDefault;
    protected SubtitleData mSubtitleData;
    protected final TextStyle mUserDefaults;
    protected final float mVideoAspectRatio;
    
    public BaseSubtitleParser(final PlayerAgent mPlayer, final SubtitleData mSubtitleData, final TextStyle mUserDefaults, final TextStyle mRegionDefault, final float mVideoAspectRatio) {
        this.mIndexOfLastSearch = -1;
        this.mDefault = new TextStyle();
        this.mSubtitleData = mSubtitleData;
        this.mPlayer = mPlayer;
        this.mVideoAspectRatio = mVideoAspectRatio;
        this.mUserDefaults = mUserDefaults;
        this.mRegionDefault = mRegionDefault;
        this.mDeviceDefault = SubtitleUtils.getDeviceDefaultTextStyle(mUserDefaults, mRegionDefault);
    }
    
    protected String getCacheName() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //     3: ifeq            55
        //     6: ldc             "nf_subtitles"
        //     8: new             Ljava/lang/StringBuilder;
        //    11: dup            
        //    12: invokespecial   java/lang/StringBuilder.<init>:()V
        //    15: ldc             "Language code from event "
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: aload_0        
        //    21: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mSubtitleData:Lcom/netflix/mediaclient/event/nrdp/media/SubtitleData;
        //    24: invokevirtual   com/netflix/mediaclient/event/nrdp/media/SubtitleData.getLanguage:()Ljava/lang/String;
        //    27: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    30: ldc             " Media current subtitle language for reference is "
        //    32: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    35: aload_0        
        //    36: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    39: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    42: invokevirtual   com/netflix/mediaclient/media/Subtitle.getLanguageCodeIso639_1:()Ljava/lang/String;
        //    45: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    48: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    51: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    54: pop            
        //    55: new             Ljava/lang/StringBuilder;
        //    58: dup            
        //    59: invokespecial   java/lang/StringBuilder.<init>:()V
        //    62: aload_0        
        //    63: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    66: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    69: invokevirtual   com/netflix/mediaclient/media/Subtitle.getLanguageCodeIso639_1:()Ljava/lang/String;
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: ldc             "_"
        //    77: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    80: aload_0        
        //    81: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    84: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    87: invokevirtual   com/netflix/mediaclient/media/Subtitle.getTrackType:()I
        //    90: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    93: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    96: astore_1       
        //    97: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   100: ifeq            143
        //   103: ldc             "nf_subtitles"
        //   105: new             Ljava/lang/StringBuilder;
        //   108: dup            
        //   109: invokespecial   java/lang/StringBuilder.<init>:()V
        //   112: ldc             "Cache for playable id "
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: aload_0        
        //   118: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   121: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //   124: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   127: ldc             " and language "
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload_1        
        //   133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   139: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   142: pop            
        //   143: aload_0        
        //   144: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   147: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getPlayerFileCache:()Lcom/netflix/mediaclient/servicemgr/IPlayerFileCache;
        //   150: aload_0        
        //   151: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   154: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //   157: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   160: aload_1        
        //   161: invokeinterface com/netflix/mediaclient/servicemgr/IPlayerFileCache.getSubtitleCache:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   166: astore_1       
        //   167: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   170: ifeq            213
        //   173: ldc             "nf_subtitles"
        //   175: new             Ljava/lang/StringBuilder;
        //   178: dup            
        //   179: invokespecial   java/lang/StringBuilder.<init>:()V
        //   182: ldc             "Cache created for playable "
        //   184: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: aload_0        
        //   188: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   191: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //   194: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   197: ldc             ", cache name: "
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: aload_1        
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   209: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   212: pop            
        //   213: aload_1        
        //   214: areturn        
        //   215: astore_2       
        //   216: aconst_null    
        //   217: astore_1       
        //   218: ldc             "nf_subtitles"
        //   220: new             Ljava/lang/StringBuilder;
        //   223: dup            
        //   224: invokespecial   java/lang/StringBuilder.<init>:()V
        //   227: ldc             "Failed to create cache "
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: aload_2        
        //   233: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   236: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   239: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   242: pop            
        //   243: aload_1        
        //   244: areturn        
        //   245: astore_2       
        //   246: goto            218
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      55     215    218    Ljava/lang/Throwable;
        //  55     143    215    218    Ljava/lang/Throwable;
        //  143    167    215    218    Ljava/lang/Throwable;
        //  167    213    245    249    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0213:
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
    
    @Override
    public boolean isReady() {
        return this.mReady;
    }
    
    @Override
    public void seeked(final int n) {
        Log.d("nf_subtitles", "Seeked to...");
        this.mIndexOfLastSearch = -1;
    }
}
