// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import java.util.List;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import com.netflix.mediaclient.service.player.subtitles.image.ImageDescriptor;
import java.io.File;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.net.DnsManager;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.image.v1.SegmentIndex;
import com.netflix.mediaclient.service.player.subtitles.image.v1.MasterIndex;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleMetadata;

public class ImageSubtitleParser extends BaseImageSubtitleParser implements ImageSubtitleMetadata
{
    private MasterIndex mMasterIndex;
    private SegmentIndex[] mSegmentIndexes;
    
    public ImageSubtitleParser(final PlayerAgent playerAgent, final SubtitleUrl subtitleUrl, final long n, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n2) {
        super(playerAgent, subtitleUrl, n, subtitleParser$DownloadFailedCallback, n2);
        Log.d("nf_subtitles", "Create image based subtitle parser");
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
            Log.d("nf_subtitles", "Downloading segment " + segmentIndex + " from URL " + this.mSubtitleData.getDownloadUrl() + ", start position [b]: " + segmentIndex.getSegmentStartPosition() + ", size [b]: " + segmentIndex.getSegmentSize());
        }
        segmentIndex.downloadStarted();
        this.mPlayer.getResourceFetcher().fetchResource(this.mSubtitleData.getDownloadUrl(), IClientLogging$AssetType.imageSubtitlesSegment, segmentIndex.getSegmentStartPosition(), segmentIndex.getSegmentSize(), new ImageSubtitleParser$3(this, segmentIndex));
    }
    
    private int getCurrentSegmentIndex() {
        if (this.mSegmentIndexes == null) {
            Log.d("nf_subtitles", "Indexes not available yet!");
        }
        else {
            for (int i = 0; i < this.mSegmentIndexes.length; ++i) {
                final SegmentIndex segmentIndex = this.mSegmentIndexes[i];
                if (segmentIndex == null) {
                    ErrorLoggingManager.logHandledException("Image based subtitles: Segment index is null, this should NOT happen! It may happen only if current segment was requested BEFORE we finish parsing segment indexes.");
                    Log.e("nf_subtitles", "Image based subtitles: Segment index is null, this should NOT happen! It may happen only if current segment was requested BEFORE we finish parsing segment indexes.");
                    return 0;
                }
                if (segmentIndex.inRange(this.mLastKnownPosition)) {
                    return i;
                }
            }
        }
        return 0;
    }
    
    private SegmentIndex getSegmentForPosition(final long n) {
        int i;
        if ((i = this.mIndexOfLastSearch) < 0) {
            i = 0;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Start searching from index " + i);
        }
        if (this.mSegmentIndexes == null) {
            Log.d("nf_subtitles", "Indexes not available yet!");
            return null;
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
        if (this.mSubtitleData == null || this.mSubtitleData.getDownloadUrl() == null) {
            Log.e("nf_subtitles", "Subtitle data is null!");
            return;
        }
        final String[] nameServers = DnsManager.getInstance().getNameServers();
        if (StringUtils.isEmpty(this.mSubtitleData.getDownloadUrl())) {
            Log.e("nf_subtitles", "Subtitle URL is empty!");
            this.onError("", nameServers, IMedia$SubtitleFailure.badMasterIndex, null);
            return;
        }
        if (this.mSubtitleData.getMasterIndexSize() <= 0) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles", "Subtitle master index size is wrong " + this.mSubtitleData.getMasterIndexSize());
            }
            this.onError(this.mSubtitleData.getDownloadUrl(), nameServers, IMedia$SubtitleFailure.badMasterIndex, null);
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Subtitle data " + this.mSubtitleData);
        }
        this.mPlayer.getResourceFetcher().fetchResource(this.mSubtitleData.getDownloadUrl(), IClientLogging$AssetType.imageSubtitlesMasterIndex, this.mSubtitleData.getMasterIndexOffset(), this.mSubtitleData.getMasterIndexSize(), new ImageSubtitleParser$1(this, nameServers));
    }
    
    private void handleDownloadSegmentIndexes() {
        Log.d("nf_subtitles", "Start to download segment indexes");
        final int segmentIndexesSize = this.mMasterIndex.getSegmentIndexesSize();
        this.mPlayer.getResourceFetcher().fetchResource(this.mSubtitleData.getDownloadUrl(), IClientLogging$AssetType.imageSubtitlesSegmentIndex, this.mMasterIndex.getStartOffset(), segmentIndexesSize, new ImageSubtitleParser$2(this, segmentIndexesSize));
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
            final ImageDescriptor[] images = segmentIndex.getImages();
            for (int length = images.length, i = 0; i < length; ++i) {
                final ImageDescriptor imageDescriptor = images[i];
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Check if image " + imageDescriptor.getName() + " exist in cache");
                }
                final File file = this.mPlayer.getPlayerFileCache().getFile(this.mKey, imageDescriptor.getName());
                final boolean b2 = b;
                if (file == null) {
                    return b2;
                }
                if (!file.exists()) {
                    return false;
                }
                imageDescriptor.setLocalImagePath(file.getAbsolutePath());
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "" + imageDescriptor);
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
        //    31: new             Lcom/netflix/mediaclient/service/player/subtitles/image/v1/MasterIndex;
        //    34: dup            
        //    35: aload           5
        //    37: invokespecial   com/netflix/mediaclient/service/player/subtitles/image/v1/MasterIndex.<init>:(Ljava/io/DataInputStream;)V
        //    40: putfield        com/netflix/mediaclient/service/player/subtitles/ImageSubtitleParser.mMasterIndex:Lcom/netflix/mediaclient/service/player/subtitles/image/v1/MasterIndex;
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
        //    62: invokevirtual   com/netflix/mediaclient/service/player/subtitles/ImageSubtitleParser.saveFileSafelyToCache:(Ljava/lang/String;[B)V
        //    65: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    68: ifeq            99
        //    71: ldc             "nf_subtitles"
        //    73: new             Ljava/lang/StringBuilder;
        //    76: dup            
        //    77: invokespecial   java/lang/StringBuilder.<init>:()V
        //    80: ldc             ""
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: aload_0        
        //    86: getfield        com/netflix/mediaclient/service/player/subtitles/ImageSubtitleParser.mMasterIndex:Lcom/netflix/mediaclient/service/player/subtitles/image/v1/MasterIndex;
        //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    92: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    95: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    98: pop            
        //    99: iload_2        
        //   100: ireturn        
        //   101: astore_1       
        //   102: iconst_0       
        //   103: istore_2       
        //   104: iload_2        
        //   105: ifeq            121
        //   108: ldc             "nf_subtitles"
        //   110: ldc_w           "Failed to close master index input stream"
        //   113: aload_1        
        //   114: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   117: pop            
        //   118: goto            65
        //   121: ldc             "nf_subtitles"
        //   123: ldc_w           "Failed to parse master index"
        //   126: aload_1        
        //   127: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   130: pop            
        //   131: goto            65
        //   134: astore_1       
        //   135: iload_3        
        //   136: istore_2       
        //   137: goto            104
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  30     43     101    104    Ljava/lang/Throwable;
        //  47     65     134    140    Ljava/lang/Throwable;
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
                final ImageDescriptor[] images = segmentIndex.getImages();
                for (int length = images.length, i = 0; i < length; ++i) {
                    final ImageDescriptor imageDescriptor = images[i];
                    final int n = (int)(imageDescriptor.getImageStartPosition() - segmentStartPosition);
                    final int n2 = imageDescriptor.getSize() + n;
                    if (Log.isLoggable()) {
                        Log.d("nf_subtitles", "Extract image " + imageDescriptor.getName() + ", start byte " + n + ", size " + imageDescriptor.getSize() + ", end byte " + n2);
                    }
                    imageDescriptor.setLocalImagePath(this.mPlayer.getPlayerFileCache().saveFile(this.mKey, imageDescriptor.getName(), Arrays.copyOfRange(array, n, n2)));
                    if (Log.isLoggable()) {
                        Log.d("nf_subtitles", "" + imageDescriptor);
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
    
    @Override
    protected void downloadSegment(final int n) {
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
    
    public MasterIndex getMasterIndex() {
        return this.mMasterIndex;
    }
    
    @Override
    public int getNumberOfDisplayedSubtitles() {
        synchronized (this) {
            new ArrayList();
            int n3;
            if (this.mSegmentIndexes != null) {
                int n = 0;
                int n2 = 0;
                while (true) {
                    n3 = n2;
                    if (n >= this.mSegmentIndexes.length) {
                        break;
                    }
                    final ImageDescriptor[] images = this.mSegmentIndexes[n].getImages();
                    for (int length = images.length, i = 0; i < length; ++i) {
                        n2 += images[i].getNumberOfDisplays();
                    }
                    ++n;
                }
            }
            else {
                n3 = 0;
            }
            return n3;
        }
    }
    
    @Override
    public int getNumberOfSubtitlesExpectedToBeDisplayed() {
    Label_0169_Outer:
        while (true) {
            int n = 0;
            while (true) {
                int mNumberOfSubtitlesExpectedToBeDisplayed = 0;
                ArrayList<ImageDescriptor> list;
                ImageDescriptor[] images;
                int length;
                int n2 = 0;
                ImageDescriptor imageDescriptor;
                int n3 = 0;
                Label_0269:Label_0103_Outer:
                while (true) {
                Label_0262:
                    while (true) {
                        Label_0252: {
                            synchronized (this) {
                                if (this.mIndexOfLastSearch == -1) {
                                    if (Log.isLoggable()) {
                                        Log.d("nf_subtitles", "User just seeked, there was no pts update after that, just return already known value " + this.mNumberOfSubtitlesExpectedToBeDisplayed);
                                    }
                                    mNumberOfSubtitlesExpectedToBeDisplayed = this.mNumberOfSubtitlesExpectedToBeDisplayed;
                                }
                                else {
                                    list = new ArrayList<ImageDescriptor>();
                                    if (this.mSegmentIndexes != null) {
                                        n = 0;
                                        mNumberOfSubtitlesExpectedToBeDisplayed = 0;
                                        if (n >= this.mSegmentIndexes.length) {
                                            break Label_0269;
                                        }
                                        images = this.mSegmentIndexes[n].getImages();
                                        length = images.length;
                                        n2 = 0;
                                        if (n2 >= length) {
                                            break Label_0262;
                                        }
                                        imageDescriptor = images[n2];
                                        n3 = mNumberOfSubtitlesExpectedToBeDisplayed;
                                        if (!imageDescriptor.isVisibleInGivenTimeRange(this.mStartPlayPositionInTitleInMs, this.mLastRenderedPositionInTitleInMs)) {
                                            break Label_0252;
                                        }
                                        n3 = mNumberOfSubtitlesExpectedToBeDisplayed;
                                        if (!list.contains(imageDescriptor)) {
                                            list.add(imageDescriptor);
                                            n3 = mNumberOfSubtitlesExpectedToBeDisplayed + 1;
                                        }
                                        break Label_0252;
                                    }
                                    else {
                                        n2 = (mNumberOfSubtitlesExpectedToBeDisplayed = this.mNumberOfSubtitlesExpectedToBeDisplayed + n);
                                        if (Log.isLoggable()) {
                                            Log.d("nf_subtitles", n + " where supposed to be visible between " + this.mStartPlayPositionInTitleInMs + " and " + this.mLastRenderedPositionInTitleInMs + " for total of " + n2);
                                            mNumberOfSubtitlesExpectedToBeDisplayed = n2;
                                        }
                                    }
                                }
                                return mNumberOfSubtitlesExpectedToBeDisplayed;
                            }
                        }
                        ++n2;
                        mNumberOfSubtitlesExpectedToBeDisplayed = n3;
                        continue Label_0169_Outer;
                    }
                    ++n;
                    continue Label_0103_Outer;
                }
                n = mNumberOfSubtitlesExpectedToBeDisplayed;
                continue;
            }
        }
    }
    
    @Override
    public short getRootContainerExtentX() {
        if (this.mMasterIndex != null) {
            return this.mMasterIndex.getRootContainerExtentX();
        }
        return 0;
    }
    
    @Override
    public short getRootContainerExtentY() {
        if (this.mMasterIndex != null) {
            return this.mMasterIndex.getRootContainerExtentY();
        }
        return 0;
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
            allVisibleSubtitleBlocks = segmentForPosition.getAllVisibleSubtitleBlocks(mLastKnownPosition);
            this.downloadIfNeeded(segmentForPosition);
        }
        else {
            Log.d("nf_subtitles", "Segment not found");
            allVisibleSubtitleBlocks = null;
        }
        return new SubtitleScreen(this, allVisibleSubtitleBlocks, null, 2000, mLastKnownPosition);
    }
    
    @Override
    public void load() {
        if (!this.handleImport()) {
            this.handleDownloadMasterIndex();
            return;
        }
        Log.d("nf_subtitles", "Succesfully loaded master index.");
    }
    
    @Override
    public void seeked(final int n) {
        super.seeked(n);
        this.mLastKnownPosition = n;
        final ArrayList<ImageDescriptor> list = new ArrayList<ImageDescriptor>();
        int n4;
        if (this.mSegmentIndexes != null) {
            int n2 = 0;
            int n3 = 0;
            while (true) {
                n4 = n3;
                if (n2 >= this.mSegmentIndexes.length) {
                    break;
                }
                final ImageDescriptor[] images = this.mSegmentIndexes[n2].getImages();
                int n5;
                for (int length = images.length, i = 0; i < length; ++i, n3 = n5) {
                    final ImageDescriptor imageDescriptor = images[i];
                    n5 = n3;
                    if (imageDescriptor.isVisibleInGivenTimeRange(this.mStartPlayPositionInTitleInMs, this.mLastRenderedPositionInTitleInMs)) {
                        n5 = n3;
                        if (!list.contains(imageDescriptor)) {
                            list.add(imageDescriptor);
                            n5 = n3 + 1;
                            imageDescriptor.seeked(n);
                        }
                    }
                }
                ++n2;
            }
        }
        else {
            n4 = 0;
        }
        this.mNumberOfSubtitlesExpectedToBeDisplayed += n4;
        this.mStartPlayPositionInTitleInMs = n;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", n4 + " where supposed to be visible between " + this.mStartPlayPositionInTitleInMs + " and " + this.mLastRenderedPositionInTitleInMs + " for total of " + this.mNumberOfSubtitlesExpectedToBeDisplayed);
        }
        this.downloadIfNeeded(this.getSegmentForPosition(this.mLastKnownPosition));
    }
}
