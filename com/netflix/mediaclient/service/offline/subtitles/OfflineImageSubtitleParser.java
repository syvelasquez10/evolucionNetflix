// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.subtitles;

import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.service.player.subtitles.image.v1.SegmentIndex;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser$DownloadFailedCallback;
import com.netflix.mediaclient.servicemgr.IPlayer;
import java.io.File;
import com.netflix.mediaclient.ui.offline.OfflineImageSubtitle;
import com.netflix.mediaclient.service.player.subtitles.ImageSubtitleParser;

public class OfflineImageSubtitleParser extends ImageSubtitleParser implements OfflineSubtitleParser
{
    protected OfflineImageSubtitle mSubtitle;
    protected File mSubtitleFile;
    
    public OfflineImageSubtitleParser(final IPlayer player, final OfflineImageSubtitle mSubtitle, final long n, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n2) {
        super(player, mSubtitle.getSubtitleUrl(), n, subtitleParser$DownloadFailedCallback, n2);
        Log.d("nf_subtitles", "Create image based offline subtitle parser");
        this.mSubtitle = mSubtitle;
        this.mSubtitleFile = new File(mSubtitle.getLocalFilePath());
    }
    
    @Override
    protected String getCacheName() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/service/offline/subtitles/OfflineImageSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //     4: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getCurrentPlayableId:()J
        //     9: lstore_1       
        //    10: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    13: ifeq            53
        //    16: ldc             "nf_subtitles"
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: ldc             "Cache for playable id "
        //    27: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    30: lload_1        
        //    31: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    34: ldc             " and language "
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    39: aload_0        
        //    40: getfield        com/netflix/mediaclient/service/offline/subtitles/OfflineImageSubtitleParser.mSubtitle:Lcom/netflix/mediaclient/ui/offline/OfflineImageSubtitle;
        //    43: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    46: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    49: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    52: pop            
        //    53: new             Ljava/lang/StringBuilder;
        //    56: dup            
        //    57: invokespecial   java/lang/StringBuilder.<init>:()V
        //    60: aload_0        
        //    61: getfield        com/netflix/mediaclient/service/offline/subtitles/OfflineImageSubtitleParser.mSubtitle:Lcom/netflix/mediaclient/ui/offline/OfflineImageSubtitle;
        //    64: invokevirtual   com/netflix/mediaclient/ui/offline/OfflineImageSubtitle.getLanguageCodeIso639_1:()Ljava/lang/String;
        //    67: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    70: ldc             "_"
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: aload_0        
        //    76: getfield        com/netflix/mediaclient/service/offline/subtitles/OfflineImageSubtitleParser.mSubtitle:Lcom/netflix/mediaclient/ui/offline/OfflineImageSubtitle;
        //    79: invokevirtual   com/netflix/mediaclient/ui/offline/OfflineImageSubtitle.getTrackType:()I
        //    82: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    85: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    88: astore_3       
        //    89: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    92: ifeq            129
        //    95: ldc             "nf_subtitles"
        //    97: new             Ljava/lang/StringBuilder;
        //   100: dup            
        //   101: invokespecial   java/lang/StringBuilder.<init>:()V
        //   104: ldc             "Cache for playable id "
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: lload_1        
        //   110: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   113: ldc             " and language "
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: aload_3        
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   125: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   128: pop            
        //   129: aload_0        
        //   130: getfield        com/netflix/mediaclient/service/offline/subtitles/OfflineImageSubtitleParser.mPlayer:Lcom/netflix/mediaclient/servicemgr/IPlayer;
        //   133: invokeinterface com/netflix/mediaclient/servicemgr/IPlayer.getPlayerFileCache:()Lcom/netflix/mediaclient/servicemgr/IPlayerFileCache;
        //   138: lload_1        
        //   139: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   142: aload_3        
        //   143: invokeinterface com/netflix/mediaclient/servicemgr/IPlayerFileCache.getSubtitleCache:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   148: astore_3       
        //   149: aload_3        
        //   150: astore          4
        //   152: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   155: ifeq            195
        //   158: ldc             "nf_subtitles"
        //   160: new             Ljava/lang/StringBuilder;
        //   163: dup            
        //   164: invokespecial   java/lang/StringBuilder.<init>:()V
        //   167: ldc             "Cache created for playable "
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: lload_1        
        //   173: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   176: ldc             ", cache name: "
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: aload_3        
        //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   188: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   191: pop            
        //   192: aload_3        
        //   193: astore          4
        //   195: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   198: ifeq            227
        //   201: ldc             "nf_subtitles"
        //   203: new             Ljava/lang/StringBuilder;
        //   206: dup            
        //   207: invokespecial   java/lang/StringBuilder.<init>:()V
        //   210: ldc             "Cache name: "
        //   212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   215: aload           4
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   223: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   226: pop            
        //   227: aload           4
        //   229: areturn        
        //   230: astore          4
        //   232: aconst_null    
        //   233: astore_3       
        //   234: ldc             "nf_subtitles"
        //   236: new             Ljava/lang/StringBuilder;
        //   239: dup            
        //   240: invokespecial   java/lang/StringBuilder.<init>:()V
        //   243: ldc             "Failed to create cache "
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   248: aload           4
        //   250: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   253: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   256: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   259: pop            
        //   260: aload_3        
        //   261: astore          4
        //   263: goto            195
        //   266: astore          4
        //   268: goto            234
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      53     230    234    Ljava/lang/Throwable;
        //  53     129    230    234    Ljava/lang/Throwable;
        //  129    149    230    234    Ljava/lang/Throwable;
        //  152    192    266    271    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0195:
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
    public Subtitle getCurrentSubtitle() {
        return this.mSubtitle;
    }
    
    @Override
    protected boolean handleImport() {
        int i = 0;
        Log.d("nf_subtitles", "Try to import data from existing cache!");
        if (!this.importMasterIndex()) {
            Log.e("nf_subtitles", "Failed to parse master index file from cache");
            return false;
        }
        Log.d("nf_subtitles", "Parsed master index file from cache");
        if (this.importSegmentIndex()) {
            Log.d("nf_subtitles", "Parsed segment index file from cache");
            Log.d("nf_subtitles", "Ready to serve subtitles...");
            this.mReady = true;
            Log.d("nf_subtitles", "Import all segments on start");
            for (SegmentIndex[] mSegmentIndexes = this.mSegmentIndexes; i < mSegmentIndexes.length; ++i) {
                this.importSegment(mSegmentIndexes[i]);
            }
            return true;
        }
        Log.e("nf_subtitles", "Failed to parse segment index file from cache");
        return false;
    }
    
    @Override
    protected boolean importMasterIndex() {
        if (this.mSubtitleFile == null || !this.mSubtitleFile.exists()) {
            return false;
        }
        try {
            Log.d("nf_subtitles", "Reading master index file from downloaded file %s from position %d for %d bytes.", this.mSubtitleFile.getAbsolutePath(), this.mSubtitle.getMasterIndexOffset(), this.mSubtitle.getMasterIndexSize());
            final boolean masterIndex = this.parseMasterIndex(FileUtils.readBytesFromFile(this.mSubtitleFile, this.mSubtitle.getMasterIndexOffset(), this.mSubtitle.getMasterIndexSize()));
            Log.d("nf_subtitles", "Loading master index file from cache %s was success %b", this.mSubtitleFile.getAbsolutePath(), masterIndex);
            return masterIndex;
        }
        catch (Throwable t) {
            Log.e("nf_subtitles", "Failed to load and parse Master index from cache", t);
            return false;
        }
    }
    
    @Override
    protected boolean importSegment(final SegmentIndex segmentIndex) {
        try {
            Log.d("nf_subtitles", "Reading segment file from cache %s", this.mSubtitleFile.getAbsolutePath());
            this.parseSegment(FileUtils.readBytesFromFile(this.mSubtitleFile, (int)segmentIndex.getSegmentStartPosition(), (int)segmentIndex.getSegmentSize()), segmentIndex);
            Log.d("nf_subtitles", "Loaded segment file from cache %s", this.mSubtitleFile.getAbsolutePath());
            return true;
        }
        catch (Throwable t) {
            Log.e("nf_subtitles", "Failed to load and parse segment index from cache", t);
            return false;
        }
    }
    
    @Override
    protected boolean importSegmentIndex() {
        try {
            Log.d("nf_subtitles", "Reading segment index file from cache %s", this.mSubtitleFile.getAbsolutePath());
            final int segmentIndexesSize = this.mMasterIndex.getSegmentIndexesSize();
            this.parseSegmentIndexes(FileUtils.readBytesFromFile(this.mSubtitleFile, (int)this.mMasterIndex.getStartOffset(), segmentIndexesSize), segmentIndexesSize);
            Log.d("nf_subtitles", "Loaded segment index file from cache %s", this.mSubtitleFile.getAbsolutePath());
            return true;
        }
        catch (Throwable t) {
            Log.e("nf_subtitles", "Failed to load and parse segment index from cache", t);
            return false;
        }
    }
    
    @Override
    public void load() {
        this.initCache();
        this.handleImport();
    }
    
    @Override
    protected void saveFileSafelyToCache(final String s, final byte[] array) {
    }
}
