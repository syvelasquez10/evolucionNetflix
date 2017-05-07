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
        //     3: ifeq            52
        //     6: ldc             "nf_subtitles"
        //     8: new             Ljava/lang/StringBuilder;
        //    11: dup            
        //    12: invokespecial   java/lang/StringBuilder.<init>:()V
        //    15: ldc             "Cache for playable id "
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: aload_0        
        //    21: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    24: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //    27: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    30: ldc             " and language "
        //    32: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    35: aload_0        
        //    36: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    39: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    45: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    48: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    51: pop            
        //    52: aload_0        
        //    53: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    56: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getPlayerFileCache:()Lcom/netflix/mediaclient/servicemgr/IPlayerFileCache;
        //    59: aload_0        
        //    60: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    63: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //    66: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    69: aload_0        
        //    70: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    73: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    76: invokevirtual   com/netflix/mediaclient/media/Subtitle.getLanguageCodeIso639_1:()Ljava/lang/String;
        //    79: invokeinterface com/netflix/mediaclient/servicemgr/IPlayerFileCache.getSubtitleCache:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    84: astore_1       
        //    85: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    88: ifeq            131
        //    91: ldc             "nf_subtitles"
        //    93: new             Ljava/lang/StringBuilder;
        //    96: dup            
        //    97: invokespecial   java/lang/StringBuilder.<init>:()V
        //   100: ldc             "Cache created for playable "
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: aload_0        
        //   106: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   109: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //   112: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   115: ldc             ", cache name: "
        //   117: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   120: aload_1        
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   130: pop            
        //   131: aload_1        
        //   132: areturn        
        //   133: astore_2       
        //   134: aconst_null    
        //   135: astore_1       
        //   136: ldc             "nf_subtitles"
        //   138: new             Ljava/lang/StringBuilder;
        //   141: dup            
        //   142: invokespecial   java/lang/StringBuilder.<init>:()V
        //   145: ldc             "Failed to create cache "
        //   147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: aload_2        
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   160: pop            
        //   161: aload_1        
        //   162: areturn        
        //   163: astore_2       
        //   164: goto            136
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      52     133    136    Ljava/lang/Throwable;
        //  52     85     133    136    Ljava/lang/Throwable;
        //  85     131    163    167    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
