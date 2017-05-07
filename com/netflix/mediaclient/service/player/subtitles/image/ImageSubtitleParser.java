// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image;

import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.io.File;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.BaseSubtitleParser;

public class ImageSubtitleParser extends BaseSubtitleParser
{
    private String mKey;
    private long mLastKnownPosition;
    private MasterIndex mMasterIndex;
    private SegmentIndex[] mSegmentIndexes;
    
    public ImageSubtitleParser(final PlayerAgent playerAgent, final SubtitleData subtitleData, final TextStyle textStyle, final TextStyle textStyle2, final float n, final long mLastKnownPosition) {
        super(playerAgent, subtitleData, textStyle, textStyle2, n);
        Log.d("nf_subtitles", "Create image based subtitle parser");
        this.mLastKnownPosition = mLastKnownPosition;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Cache for playable id " + this.mPlayer.getCurrentPlayableId() + " and language " + this.mPlayer.getCurrentSubtitleTrack());
        }
        this.mKey = this.getCacheName();
    }
    
    private void downloadIfNeeded(final SegmentIndex segmentIndex) {
        if (segmentIndex == null) {
            return;
        }
        if (segmentIndex.shouldDownload()) {
            Log.d("nf_subtitles", "Current segment is not downloaded yet, go and fetch current range (this and next segment)...");
            this.downloadNextRange(segmentIndex.getIndex());
            return;
        }
        Log.d("nf_subtitles", "Current segment is not downloaded, go and fetch next range (2 segments after current one)...");
        this.downloadNextRange(segmentIndex.getIndex() + 1);
    }
    
    private void downloadNextRange(final int n) {
        this.downloadSegment(n);
        this.downloadSegment(n + 1);
    }
    
    private void downloadSegment(final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Download segment " + n + " if needed");
        }
        if (n < 0 || this.mSegmentIndexes == null || n >= this.mSegmentIndexes.length) {
            Log.w("nf_subtitles", "Invalid index requested!");
            return;
        }
        final SegmentIndex segmentIndex = this.mSegmentIndexes[n];
        if (segmentIndex == null) {
            Log.e("nf_subtitles", "Segment index is null, this should NOT happen!");
            return;
        }
        if (this.importSegment(segmentIndex)) {
            Log.d("nf_subtitles", "No need to download segment images, succesfully loaded from cache");
            return;
        }
        Log.d("nf_subtitles", "Image(s) missing, go and download...");
        this.downloadSegment(segmentIndex);
    }
    
    private void downloadSegment(final SegmentIndex segmentIndex) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Download segment " + segmentIndex + " if needed");
        }
        if (segmentIndex == null) {
            Log.e("nf_subtitles", "Segment is null!");
            return;
        }
        if (!segmentIndex.shouldDownload()) {
            Log.w("nf_subtitles", "Segment is already downloaded");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Downloading segment " + segmentIndex + " from URL " + this.mSubtitleData.getUrl() + ", start position [b]: " + segmentIndex.getSegmentStartPosition() + ", size [b]: " + segmentIndex.getSegmentSize());
        }
        segmentIndex.downloadStarted();
        this.mPlayer.getResourceFetcher().fetchResource(this.mSubtitleData.getUrl(), IClientLogging$AssetType.imageSubtitlesSegment, segmentIndex.getSegmentStartPosition(), segmentIndex.getSegmentSize(), new ImageSubtitleParser$3(this, segmentIndex));
    }
    
    private int getCurrentSegmentIndex() {
        for (int i = 0; i < this.mSegmentIndexes.length; ++i) {
            if (this.mSegmentIndexes[i].inRange(this.mLastKnownPosition)) {
                return i;
            }
        }
        return 0;
    }
    
    private SegmentIndex getSegmentForPosition(final long n) {
        int mIndexOfLastSearch;
        if ((mIndexOfLastSearch = this.mIndexOfLastSearch) < 0) {
            mIndexOfLastSearch = 0;
        }
        int i = mIndexOfLastSearch;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Start searching from index " + mIndexOfLastSearch);
            i = mIndexOfLastSearch;
        }
        while (i < this.mSegmentIndexes.length) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Searching for index " + i + "...");
            }
            final SegmentIndex segmentIndex = this.mSegmentIndexes[i];
            if (segmentIndex != null && segmentIndex.inRange(n)) {
                this.mIndexOfLastSearch = i;
                return segmentIndex;
            }
            ++i;
        }
        return null;
    }
    
    private void handleDownloadMasterIndex() {
        if (this.mSubtitleData == null) {
            Log.e("nf_subtitles", "Subtitle data is null!");
        }
        else {
            if (StringUtils.isEmpty(this.mSubtitleData.getUrl())) {
                Log.e("nf_subtitles", "Subtitle URL is empty!");
                return;
            }
            if (this.mSubtitleData.getMasterIndexSize() > 0) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Subtitle data " + this.mSubtitleData);
                }
                this.mPlayer.getResourceFetcher().fetchResource(this.mSubtitleData.getUrl(), IClientLogging$AssetType.imageSubtitlesMasterIndex, this.mSubtitleData.getMasterIndexOffset(), this.mSubtitleData.getMasterIndexSize(), new ImageSubtitleParser$1(this));
                return;
            }
            if (Log.isLoggable()) {
                Log.e("nf_subtitles", "Subtitle master index size is wrong " + this.mSubtitleData.getMasterIndexSize());
            }
        }
    }
    
    private void handleDownloadSegmentIndexes() {
        Log.d("nf_subtitles", "Start to download segment indexes");
        final int segmentIndexesSize = this.mMasterIndex.getSegmentIndexesSize();
        this.mPlayer.getResourceFetcher().fetchResource(this.mSubtitleData.getUrl(), IClientLogging$AssetType.imageSubtitlesSegmentIndex, this.mMasterIndex.getStartOffset(), segmentIndexesSize, new ImageSubtitleParser$2(this, segmentIndexesSize));
    }
    
    private boolean handleImport() {
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
            final int currentSegmentIndex = this.getCurrentSegmentIndex();
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Download segment " + currentSegmentIndex + " on start");
            }
            this.downloadNextRange(currentSegmentIndex);
            return true;
        }
        Log.e("nf_subtitles", "Failed to parse segment index file from cache");
        return false;
    }
    
    private boolean importMasterIndex() {
        final File file = this.mPlayer.getPlayerFileCache().getFile(this.mKey, "master.idx");
        boolean masterIndex;
        if (file == null || !file.exists()) {
            masterIndex = false;
        }
        else {
            try {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Reading master index file from cache " + file.getAbsolutePath());
                }
                final boolean b = masterIndex = this.parseMasterIndex(FileUtils.readFileToByteArray(file));
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Loading master index file from cache " + file.getAbsolutePath() + " was success " + b);
                    return b;
                }
            }
            catch (Throwable t) {
                Log.e("nf_subtitles", "Failed to load and parse Master index from cache", t);
                return false;
            }
        }
        return masterIndex;
    }
    
    private boolean importSegment(final SegmentIndex segmentIndex) {
        final boolean b = false;
        try {
            final SegmentIndex$ImageDescriptor[] images = segmentIndex.getImages();
            for (int length = images.length, i = 0; i < length; ++i) {
                final SegmentIndex$ImageDescriptor segmentIndex$ImageDescriptor = images[i];
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Check if image " + segmentIndex$ImageDescriptor.getName() + " exist in cache");
                }
                final File file = this.mPlayer.getPlayerFileCache().getFile(this.mKey, segmentIndex$ImageDescriptor.getName());
                final boolean b2 = b;
                if (file == null) {
                    return b2;
                }
                if (!file.exists()) {
                    return false;
                }
                segmentIndex$ImageDescriptor.setLocalImagePath(file.getAbsolutePath());
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "" + segmentIndex$ImageDescriptor);
                }
            }
        }
        catch (Throwable t) {
            Log.e("nf_subtitles", "Failed to parse segment", t);
            return false;
        }
        segmentIndex.downloadStarted();
        return true;
    }
    
    private boolean importSegmentIndex() {
        final File file = this.mPlayer.getPlayerFileCache().getFile(this.mKey, "segment.idx");
        if (file == null || !file.exists()) {
            return false;
        }
        try {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Reading segment index file from cache " + file.getAbsolutePath());
            }
            this.parseSegmentIndexes(FileUtils.readFileToByteArray(file), this.mMasterIndex.getSegmentIndexesSize());
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Loaded segment index file from cache " + file.getAbsolutePath());
            }
            return true;
        }
        catch (Throwable t) {
            Log.e("nf_subtitles", "Failed to load and parse Master index from cache", t);
            return false;
        }
    }
    
    private boolean parseMasterIndex(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "nf_subtitles"
        //     2: ldc_w           "Master index received, parse it..."
        //     5: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //     8: pop            
        //     9: new             Ljava/io/ByteArrayInputStream;
        //    12: dup            
        //    13: aload_1        
        //    14: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    17: astore          4
        //    19: new             Ljava/io/DataInputStream;
        //    22: dup            
        //    23: aload           4
        //    25: invokespecial   java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
        //    28: astore          5
        //    30: aload_0        
        //    31: new             Lcom/netflix/mediaclient/service/player/subtitles/image/MasterIndex;
        //    34: dup            
        //    35: aload           5
        //    37: invokespecial   com/netflix/mediaclient/service/player/subtitles/image/MasterIndex.<init>:(Ljava/io/DataInputStream;)V
        //    40: putfield        com/netflix/mediaclient/service/player/subtitles/image/ImageSubtitleParser.mMasterIndex:Lcom/netflix/mediaclient/service/player/subtitles/image/MasterIndex;
        //    43: iconst_1       
        //    44: istore_3       
        //    45: iconst_1       
        //    46: istore_2       
        //    47: aload           5
        //    49: invokevirtual   java/io/DataInputStream.close:()V
        //    52: aload           4
        //    54: invokevirtual   java/io/InputStream.close:()V
        //    57: aload_0        
        //    58: ldc_w           "master.idx"
        //    61: aload_1        
        //    62: invokespecial   com/netflix/mediaclient/service/player/subtitles/image/ImageSubtitleParser.saveFileSafelyToCache:(Ljava/lang/String;[B)V
        //    65: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    68: ifeq            100
        //    71: ldc             "nf_subtitles"
        //    73: new             Ljava/lang/StringBuilder;
        //    76: dup            
        //    77: invokespecial   java/lang/StringBuilder.<init>:()V
        //    80: ldc_w           ""
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: aload_0        
        //    87: getfield        com/netflix/mediaclient/service/player/subtitles/image/ImageSubtitleParser.mMasterIndex:Lcom/netflix/mediaclient/service/player/subtitles/image/MasterIndex;
        //    90: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    93: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    96: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    99: pop            
        //   100: iload_2        
        //   101: ireturn        
        //   102: astore_1       
        //   103: iconst_0       
        //   104: istore_2       
        //   105: iload_2        
        //   106: ifeq            122
        //   109: ldc             "nf_subtitles"
        //   111: ldc_w           "Failed to close master index input stream"
        //   114: aload_1        
        //   115: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   118: pop            
        //   119: goto            65
        //   122: ldc             "nf_subtitles"
        //   124: ldc_w           "Failed to parse master index"
        //   127: aload_1        
        //   128: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   131: pop            
        //   132: goto            65
        //   135: astore_1       
        //   136: iload_3        
        //   137: istore_2       
        //   138: goto            105
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  30     43     102    105    Ljava/lang/Throwable;
        //  47     65     135    141    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    private void parseSegment(final byte[] array, final SegmentIndex segmentIndex) {
        if (array == null) {
            Log.e("nf_subtitles", "Response data for segment is NULL");
        }
        else {
            Log.d("nf_subtitles", "Segment received, parse it...");
            try {
                final long segmentStartPosition = segmentIndex.getSegmentStartPosition();
                final SegmentIndex$ImageDescriptor[] images = segmentIndex.getImages();
                for (int length = images.length, i = 0; i < length; ++i) {
                    final SegmentIndex$ImageDescriptor segmentIndex$ImageDescriptor = images[i];
                    final int n = (int)(segmentIndex$ImageDescriptor.getImageStartPosition() - segmentStartPosition);
                    final int n2 = segmentIndex$ImageDescriptor.getSize() + n;
                    if (Log.isLoggable()) {
                        Log.d("nf_subtitles", "Extract image " + segmentIndex$ImageDescriptor.getName() + ", start byte " + n + ", size " + segmentIndex$ImageDescriptor.getSize() + ", end byte " + n2);
                    }
                    segmentIndex$ImageDescriptor.setLocalImagePath(this.mPlayer.getPlayerFileCache().saveFile(this.mKey, segmentIndex$ImageDescriptor.getName(), Arrays.copyOfRange(array, n, n2)));
                    if (Log.isLoggable()) {
                        Log.d("nf_subtitles", "" + segmentIndex$ImageDescriptor);
                    }
                }
            }
            catch (Throwable t) {
                Log.e("nf_subtitles", "Failed to parse segment", t);
            }
        }
    }
    
    private void parseSegmentIndexes(final byte[] array, int i) {
        final int n = 0;
        if (array == null) {
            Log.e("nf_subtitles", "Response data for segment indexes is NULL");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Downloaded " + array.length + " bytes of all segment indexes. Expecting " + i);
        }
        Label_0247: {
            if (array.length == i) {
                break Label_0247;
            }
            Log.e("nf_subtitles", "Size mismatch!");
            if (array.length < i) {
                Log.e("nf_subtitles", "Not enough data, abort parsing");
                return;
            }
            Log.w("nf_subtitles", "More data than expected, start parsing...");
        Label_0272_Outer:
            while (true) {
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
                final DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                this.mSegmentIndexes = new SegmentIndex[this.mMasterIndex.getEntryCount()];
                i = 0;
                short n2 = 0;
                int n3 = 1;
                while (true) {
                    try {
                        while (i < this.mMasterIndex.getEntryCount()) {
                            final SegmentIndex segmentIndex = new SegmentIndex(dataInputStream, n3, n2);
                            this.mSegmentIndexes[i] = segmentIndex;
                            n3 += segmentIndex.getDuration();
                            n2 += segmentIndex.getEntryCount();
                            segmentIndex.setIndex(i);
                            if (Log.isLoggable()) {
                                Log.d("nf_subtitles", "Segment index " + i + " " + segmentIndex);
                            }
                            ++i;
                        }
                        try {
                            dataInputStream.close();
                            byteArrayInputStream.close();
                            return;
                        }
                        catch (Throwable t) {
                            i = 1;
                        }
                        final Throwable t;
                        if (i != 0) {
                            Log.e("nf_subtitles", "Failed to close segment indexes input stream", t);
                            return;
                        }
                        Log.e("nf_subtitles", "Failed to parse segment index", t);
                        return;
                        Log.d("nf_subtitles", "Expected data, start parsing...");
                        continue Label_0272_Outer;
                    }
                    catch (Throwable t) {
                        i = n;
                        continue;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    private void saveFileSafelyToCache(final String s, final byte[] array) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Saving " + s + "...");
        }
        try {
            this.mPlayer.getPlayerFileCache().saveFile(this.mKey, s, array);
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Saved " + s + ".");
            }
        }
        catch (Throwable t) {
            Log.e("nf_subtitles", "Failed to save " + s, t);
        }
    }
    
    public MasterIndex getMasterIndex() {
        return this.mMasterIndex;
    }
    
    public SegmentIndex[] getSegmentIndexes() {
        return this.mSegmentIndexes;
    }
    
    @Override
    public IMedia$SubtitleProfile getSubtitleProfile() {
        return IMedia$SubtitleProfile.IMAGE;
    }
    
    @Override
    public SubtitleScreen getSubtitlesForPosition(final long mLastKnownPosition) {
        this.mLastKnownPosition = mLastKnownPosition;
        final SegmentIndex segmentForPosition = this.getSegmentForPosition(mLastKnownPosition);
        List<SubtitleBlock> allVisibleSubtitleBlocks;
        if (segmentForPosition != null) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Segment found for ts " + mLastKnownPosition + ": " + segmentForPosition);
            }
            allVisibleSubtitleBlocks = segmentForPosition.getAllVisibleSubtitleBlocks(mLastKnownPosition);
            this.downloadIfNeeded(segmentForPosition);
        }
        else {
            Log.d("nf_subtitles", "Segment not found");
            allVisibleSubtitleBlocks = null;
        }
        return new SubtitleScreen(this, allVisibleSubtitleBlocks, null, 2000);
    }
    
    @Override
    public void load() {
        if (!this.handleImport()) {
            this.handleDownloadMasterIndex();
        }
    }
    
    @Override
    public void seeked(final int n) {
        super.seeked(n);
        this.mLastKnownPosition = n;
        this.downloadIfNeeded(this.getSegmentForPosition(this.mLastKnownPosition));
    }
}
