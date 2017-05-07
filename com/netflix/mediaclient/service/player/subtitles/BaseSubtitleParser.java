// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.SubtitleUtils;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;

public abstract class BaseSubtitleParser implements SubtitleParser
{
    protected static final String TAG = "nf_subtitles";
    protected SubtitleParser$DownloadFailedCallback mCallback;
    protected final TextStyle mDefault;
    protected final TextStyle mDeviceDefault;
    protected int mIndexOfLastSearch;
    protected PlayerAgent mPlayer;
    protected boolean mReady;
    protected final TextStyle mRegionDefault;
    protected SubtitleUrl mSubtitleData;
    protected final TextStyle mUserDefaults;
    protected final float mVideoAspectRatio;
    
    public BaseSubtitleParser(final PlayerAgent mPlayer, final SubtitleUrl mSubtitleData, final TextStyle mUserDefaults, final TextStyle mRegionDefault, final float mVideoAspectRatio, final SubtitleParser$DownloadFailedCallback mCallback) {
        this.mIndexOfLastSearch = -1;
        this.mDefault = new TextStyle();
        this.mSubtitleData = mSubtitleData;
        this.mPlayer = mPlayer;
        this.mVideoAspectRatio = mVideoAspectRatio;
        this.mUserDefaults = mUserDefaults;
        this.mRegionDefault = mRegionDefault;
        this.mDeviceDefault = SubtitleUtils.getDeviceDefaultTextStyle(mUserDefaults, mRegionDefault);
        this.mCallback = mCallback;
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
        //    52: new             Ljava/lang/StringBuilder;
        //    55: dup            
        //    56: invokespecial   java/lang/StringBuilder.<init>:()V
        //    59: aload_0        
        //    60: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    63: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    66: invokevirtual   com/netflix/mediaclient/media/Subtitle.getLanguageCodeIso639_1:()Ljava/lang/String;
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    72: ldc             "_"
        //    74: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    77: aload_0        
        //    78: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //    81: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    84: invokevirtual   com/netflix/mediaclient/media/Subtitle.getTrackType:()I
        //    87: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: astore_1       
        //    94: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    97: ifeq            140
        //   100: ldc             "nf_subtitles"
        //   102: new             Ljava/lang/StringBuilder;
        //   105: dup            
        //   106: invokespecial   java/lang/StringBuilder.<init>:()V
        //   109: ldc             "Cache for playable id "
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: aload_0        
        //   115: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   118: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //   121: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   124: ldc             " and language "
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: aload_1        
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   136: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   139: pop            
        //   140: aload_0        
        //   141: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   144: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getPlayerFileCache:()Lcom/netflix/mediaclient/servicemgr/IPlayerFileCache;
        //   147: aload_0        
        //   148: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   151: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //   154: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   157: aload_1        
        //   158: invokeinterface com/netflix/mediaclient/servicemgr/IPlayerFileCache.getSubtitleCache:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   163: astore_1       
        //   164: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   167: ifeq            210
        //   170: ldc             "nf_subtitles"
        //   172: new             Ljava/lang/StringBuilder;
        //   175: dup            
        //   176: invokespecial   java/lang/StringBuilder.<init>:()V
        //   179: ldc             "Cache created for playable "
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: aload_0        
        //   185: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/service/player/PlayerAgent;
        //   188: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getCurrentPlayableId:()J
        //   191: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   194: ldc             ", cache name: "
        //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   199: aload_1        
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   206: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   209: pop            
        //   210: aload_1        
        //   211: areturn        
        //   212: astore_2       
        //   213: aconst_null    
        //   214: astore_1       
        //   215: ldc             "nf_subtitles"
        //   217: new             Ljava/lang/StringBuilder;
        //   220: dup            
        //   221: invokespecial   java/lang/StringBuilder.<init>:()V
        //   224: ldc             "Failed to create cache "
        //   226: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   229: aload_2        
        //   230: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   233: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   236: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   239: pop            
        //   240: aload_1        
        //   241: areturn        
        //   242: astore_2       
        //   243: goto            215
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      52     212    215    Ljava/lang/Throwable;
        //  52     140    212    215    Ljava/lang/Throwable;
        //  140    164    212    215    Ljava/lang/Throwable;
        //  164    210    242    246    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0210:
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
    
    protected boolean onError() {
        if (this.mCallback != null) {
            Log.d("nf_subtitles", "onError: callback");
            return this.mCallback.downloadFailed(this.mSubtitleData);
        }
        Log.w("nf_subtitles", "onError: no callback");
        return false;
    }
    
    @Override
    public void seeked(final int n) {
        Log.d("nf_subtitles", "Seeked to...");
        this.mIndexOfLastSearch = -1;
    }
}
