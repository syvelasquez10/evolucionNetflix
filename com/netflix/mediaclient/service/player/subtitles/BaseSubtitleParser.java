// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleFailure;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.servicemgr.IPlayer;

public abstract class BaseSubtitleParser implements SubtitleParser
{
    protected static final String TAG = "nf_subtitles";
    protected SubtitleParser$DownloadFailedCallback mCallback;
    protected int mIndexOfLastSearch;
    protected long mLastRenderedPositionInTitleInMs;
    protected int mNumberOfSubtitlesExpectedToBeDisplayed;
    protected IPlayer mPlayer;
    protected boolean mReady;
    protected long mStartPlayPositionInTitleInMs;
    protected SubtitleUrl mSubtitleData;
    
    public BaseSubtitleParser(final IPlayer mPlayer, final SubtitleUrl mSubtitleData, final SubtitleParser$DownloadFailedCallback mCallback, final long n) {
        this.mIndexOfLastSearch = -1;
        this.mPlayer = mPlayer;
        this.mCallback = mCallback;
        this.mStartPlayPositionInTitleInMs = n;
        this.mLastRenderedPositionInTitleInMs = n;
        this.mSubtitleData = mSubtitleData;
    }
    
    protected String getCacheName() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //     3: ifeq            56
        //     6: ldc             "nf_subtitles"
        //     8: new             Ljava/lang/StringBuilder;
        //    11: dup            
        //    12: invokespecial   java/lang/StringBuilder.<init>:()V
        //    15: ldc             "Cache for playable id "
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: aload_0        
        //    21: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //    24: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getCurrentPlayableId:()J
        //    29: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    32: ldc             " and language "
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: aload_0        
        //    38: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //    41: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    49: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    52: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    55: pop            
        //    56: new             Ljava/lang/StringBuilder;
        //    59: dup            
        //    60: invokespecial   java/lang/StringBuilder.<init>:()V
        //    63: aload_0        
        //    64: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //    67: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    72: invokeinterface com/netflix/mediaclient/media/Subtitle.getLanguageCodeIso639_1:()Ljava/lang/String;
        //    77: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    80: ldc             "_"
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: aload_0        
        //    86: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //    89: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getCurrentSubtitleTrack:()Lcom/netflix/mediaclient/media/Subtitle;
        //    94: invokeinterface com/netflix/mediaclient/media/Subtitle.getTrackType:()I
        //    99: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   102: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   105: astore_1       
        //   106: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   109: ifeq            154
        //   112: ldc             "nf_subtitles"
        //   114: new             Ljava/lang/StringBuilder;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuilder.<init>:()V
        //   121: ldc             "Cache for playable id "
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: aload_0        
        //   127: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //   130: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getCurrentPlayableId:()J
        //   135: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   138: ldc             " and language "
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: aload_1        
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   150: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   153: pop            
        //   154: aload_0        
        //   155: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //   158: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getPlayerFileCache:()Lcom/netflix/mediaclient/servicemgr/IPlayerFileCache;
        //   163: aload_0        
        //   164: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //   167: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getCurrentPlayableId:()J
        //   172: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   175: aload_1        
        //   176: invokeinterface com/netflix/mediaclient/servicemgr/IPlayerFileCache.getSubtitleCache:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   181: astore_1       
        //   182: aload_1        
        //   183: astore_2       
        //   184: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   187: ifeq            234
        //   190: ldc             "nf_subtitles"
        //   192: new             Ljava/lang/StringBuilder;
        //   195: dup            
        //   196: invokespecial   java/lang/StringBuilder.<init>:()V
        //   199: ldc             "Cache created for playable "
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: aload_0        
        //   205: getfield        com/netflix/mediaclient/service/player/subtitles/BaseSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //   208: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getCurrentPlayableId:()J
        //   213: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   216: ldc             ", cache name: "
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: aload_1        
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   228: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   231: pop            
        //   232: aload_1        
        //   233: astore_2       
        //   234: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   237: ifeq            265
        //   240: ldc             "nf_subtitles"
        //   242: new             Ljava/lang/StringBuilder;
        //   245: dup            
        //   246: invokespecial   java/lang/StringBuilder.<init>:()V
        //   249: ldc             "Cache name: "
        //   251: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   254: aload_2        
        //   255: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   258: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   261: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   264: pop            
        //   265: aload_2        
        //   266: areturn        
        //   267: astore_2       
        //   268: aconst_null    
        //   269: astore_1       
        //   270: ldc             "nf_subtitles"
        //   272: new             Ljava/lang/StringBuilder;
        //   275: dup            
        //   276: invokespecial   java/lang/StringBuilder.<init>:()V
        //   279: ldc             "Failed to create cache "
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: aload_2        
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   288: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   291: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   294: pop            
        //   295: aload_1        
        //   296: astore_2       
        //   297: goto            234
        //   300: astore_2       
        //   301: goto            270
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      56     267    270    Ljava/lang/Throwable;
        //  56     154    267    270    Ljava/lang/Throwable;
        //  154    182    267    270    Ljava/lang/Throwable;
        //  184    232    300    304    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0234:
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
    
    protected ResourceFetcher getResourceFetcher() {
        if (this.mPlayer instanceof ServiceAgent) {
            return ((ServiceAgent)this.mPlayer).getResourceFetcher();
        }
        return null;
    }
    
    @Override
    public SubtitleUrl getSubtitleUrl() {
        return this.mSubtitleData;
    }
    
    @Override
    public boolean isReady() {
        return this.mReady;
    }
    
    protected boolean onError(final String s, final String[] array, final ISubtitleDef$SubtitleFailure subtitleDef$SubtitleFailure, final Status status) {
        boolean downloadFailed = false;
        if (this.mCallback != null) {
            Log.d("nf_subtitles", "onError: callback");
            downloadFailed = this.mCallback.downloadFailed(this.mSubtitleData, subtitleDef$SubtitleFailure, s);
        }
        else {
            Log.w("nf_subtitles", "onError: no callback");
        }
        this.mPlayer.reportFailedSubtitle(s, this.mSubtitleData, subtitleDef$SubtitleFailure, downloadFailed, status, array);
        return downloadFailed;
    }
    
    protected void reportHandledException(final Exception ex) {
        if (this.mPlayer instanceof ServiceAgent) {
            ((ServiceAgent)this.mPlayer).reportHandledException(ex);
        }
    }
    
    @Override
    public void seeked(final int n) {
        synchronized (this) {
            Log.d("nf_subtitles", "Seeked to...");
            this.mIndexOfLastSearch = -1;
        }
    }
}
