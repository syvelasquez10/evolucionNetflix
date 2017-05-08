// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.image.v2.BoxHeader;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.subtitles.image.ImageDescriptor;
import java.io.File;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleFailure;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.net.DnsManager;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.player.subtitles.image.v2.SegmentIndex;
import com.netflix.mediaclient.service.player.subtitles.image.v2.SegmentEncryptionInfo$ImageEncryptionInfo;
import com.netflix.mediaclient.service.player.subtitles.image.v2.ImageDecryptorFactory;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.service.player.subtitles.image.v2.ISCSegment;
import com.netflix.mediaclient.service.player.subtitles.image.v2.ISCTrack;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleMetadata;

public class ImageV2SubtitleParser extends BaseImageSubtitleParser implements ImageSubtitleMetadata
{
    protected static final String TAG = "nf_subtitles_imv2";
    protected ISCTrack mMasterIndexContainer;
    private boolean mSaveImagesToCache;
    protected ISCSegment[] mSegmentIndexContainers;
    
    public ImageV2SubtitleParser(final IPlayer player, final SubtitleUrl subtitleUrl, final long n, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n2) {
        super(player, subtitleUrl, n, subtitleParser$DownloadFailedCallback, n2);
        Log.d("nf_subtitles_imv2", "Create image V2 based subtitle parser");
    }
    
    private byte[] decodeImageIfNeeded(byte[] decrypt, final ISCSegment iscSegment, final int n, final String s, final int n2) {
        try {
            final SegmentEncryptionInfo$ImageEncryptionInfo imageEncryptionInfoForImage = iscSegment.getImageEncryptionInfoForImage(n);
            decrypt = ImageDecryptorFactory.getDecryptor(imageEncryptionInfoForImage).decrypt(decrypt, imageEncryptionInfoForImage, s, n2);
            return decrypt;
        }
        catch (Throwable t) {
            Log.e("nf_subtitles_imv2", "Failed to decrypt image", t);
            return null;
        }
    }
    
    private void downloadIfNeeded(final SegmentIndex segmentIndex) {
        if (segmentIndex == null) {
            return;
        }
        if (segmentIndex.shouldDownload()) {
            Log.d("nf_subtitles_imv2", "Current segment is not downloaded yet, go and fetch current range (this and next segment)...");
            this.downloadNextRange(segmentIndex.getIndex());
            return;
        }
        Log.d("nf_subtitles_imv2", "Current segment is not downloaded, go and fetch next range (2 segments after current one)...");
        this.downloadNextRange(segmentIndex.getIndex() + 1);
    }
    
    private void downloadSegment(final SegmentIndex segmentIndex) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Download segment " + segmentIndex + " if needed");
        }
        if (segmentIndex == null) {
            Log.e("nf_subtitles_imv2", "Segment is null!");
            return;
        }
        if (!segmentIndex.shouldDownload()) {
            Log.w("nf_subtitles_imv2", "Segment is already downloaded");
            return;
        }
        final String downloadUrl = this.mSubtitleData.getDownloadUrl();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Downloading segment " + segmentIndex + " from URL " + downloadUrl + ", start position [b]: " + segmentIndex.getSegmentStartPosition() + ", size [b]: " + segmentIndex.getSegmentSize());
        }
        final String decryptionKey = this.mSubtitleData.getDecryptionKey();
        segmentIndex.downloadStarted();
        this.getResourceFetcher().fetchResource(downloadUrl, IClientLogging$AssetType.imageSubtitlesSegment, segmentIndex.getSegmentStartPosition(), segmentIndex.getSegmentSize(), new ImageV2SubtitleParser$3(this, segmentIndex, downloadUrl, decryptionKey));
    }
    
    private int getCurrentSegmentIndex() {
        if (this.mSegmentIndexContainers == null) {
            Log.d("nf_subtitles_imv2", "Indexes not available yet!");
        }
        else {
            for (int i = 0; i < this.mSegmentIndexContainers.length; ++i) {
                if (this.mSegmentIndexContainers[i] != null && this.mSegmentIndexContainers[i].getSegmentIndex() != null && this.mSegmentIndexContainers[i].getSegmentIndex().inRange(this.mLastKnownPosition)) {
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
            Log.d("nf_subtitles_imv2", "Start searching from index " + i);
        }
        if (this.mSegmentIndexContainers == null) {
            Log.d("nf_subtitles_imv2", "Indexes not available yet!");
            return null;
        }
        while (i < this.mSegmentIndexContainers.length) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Searching for index " + i + "...");
            }
            final ISCSegment iscSegment = this.mSegmentIndexContainers[i];
            if (iscSegment != null && iscSegment.getSegmentIndex() != null && iscSegment.getSegmentIndex().inRange(n)) {
                this.mIndexOfLastSearch = i;
                return iscSegment.getSegmentIndex();
            }
            ++i;
        }
        return null;
    }
    
    private void handleDownloadMasterIndexContainer() {
        if (this.mSubtitleData == null || this.mSubtitleData.getDownloadUrl() == null) {
            Log.e("nf_subtitles_imv2", "Subtitle data is null!");
            return;
        }
        final String[] nameServers = DnsManager.getInstance().getNameServers();
        if (StringUtils.isEmpty(this.mSubtitleData.getDownloadUrl())) {
            Log.e("nf_subtitles_imv2", "Subtitle URL is empty!");
            this.onError("", nameServers, ISubtitleDef$SubtitleFailure.badMasterIndex, null);
            return;
        }
        if (this.mSubtitleData.getMasterIndexSize() <= 0) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles_imv2", "Subtitle master index size is wrong " + this.mSubtitleData.getMasterIndexSize());
            }
            this.onError(this.mSubtitleData.getDownloadUrl(), nameServers, ISubtitleDef$SubtitleFailure.badMasterIndex, null);
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Subtitle data " + this.mSubtitleData);
        }
        this.getResourceFetcher().fetchResource(this.mSubtitleData.getDownloadUrl(), IClientLogging$AssetType.imageSubtitlesMasterIndex, this.mSubtitleData.getMasterIndexOffset(), this.mSubtitleData.getMasterIndexSize(), new ImageV2SubtitleParser$1(this, nameServers));
    }
    
    private void handleDownloadSegmentIndexes() {
        Log.d("nf_subtitles_imv2", "Start to download segment indexes");
        final int segmentIndexesSize = this.mMasterIndexContainer.getMasterIndex().getSegmentIndexesSize();
        this.getResourceFetcher().fetchResource(this.mSubtitleData.getDownloadUrl(), IClientLogging$AssetType.imageSubtitlesSegmentIndex, this.mMasterIndexContainer.getMasterIndex().getSegmentOffset(), segmentIndexesSize, new ImageV2SubtitleParser$2(this, segmentIndexesSize));
    }
    
    private boolean handleImport() {
        Log.d("nf_subtitles_imv2", "Try to import data from existing cache!");
        if (!this.importMasterIndexContainer()) {
            Log.e("nf_subtitles_imv2", "Failed to parse master index container from cache");
            return false;
        }
        Log.d("nf_subtitles_imv2", "Parsed master index container from cache");
        if (this.importSegmentIndexContainers()) {
            Log.d("nf_subtitles_imv2", "Parsed segment index containers from cache");
            Log.d("nf_subtitles_imv2", "Ready to serve subtitles...");
            this.mReady = true;
            final int currentSegmentIndex = this.getCurrentSegmentIndex();
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Download segment " + currentSegmentIndex + " on start");
            }
            this.downloadNextRange(currentSegmentIndex);
            return true;
        }
        Log.e("nf_subtitles_imv2", "Failed to parse segment index file from cache");
        return false;
    }
    
    private boolean importMasterIndexContainer() {
        final File file = this.mPlayer.getPlayerFileCache().getFile(this.mKey, "master.idx");
        boolean masterIndexContainer;
        if (file == null || !file.exists()) {
            masterIndexContainer = false;
        }
        else {
            try {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_imv2", "Reading master index file from cache " + file.getAbsolutePath());
                }
                final boolean b = masterIndexContainer = this.parseMasterIndexContainer(FileUtils.readFileToByteArray(file));
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_imv2", "Loading master index file from cache " + file.getAbsolutePath() + " was success " + b);
                    return b;
                }
            }
            catch (Throwable t) {
                Log.e("nf_subtitles_imv2", "Failed to load and parse Master index from cache", t);
                return false;
            }
        }
        return masterIndexContainer;
    }
    
    private boolean importSegment(final SegmentIndex segmentIndex) {
        final boolean b = false;
        try {
            final ImageDescriptor[] images = segmentIndex.getImages();
            for (int length = images.length, i = 0; i < length; ++i) {
                final ImageDescriptor imageDescriptor = images[i];
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_imv2", "Check if image " + imageDescriptor.getName() + " exist in cache");
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
                    Log.d("nf_subtitles_imv2", "" + imageDescriptor);
                }
            }
        }
        catch (Throwable t) {
            Log.e("nf_subtitles_imv2", "Failed to parse segment", t);
            return false;
        }
        segmentIndex.downloadStarted();
        return true;
    }
    
    private boolean importSegmentIndexContainers() {
        final File file = this.mPlayer.getPlayerFileCache().getFile(this.mKey, "segment.idx");
        boolean segmentIndexes;
        if (file == null || !file.exists()) {
            segmentIndexes = false;
        }
        else {
            try {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_imv2", "Reading segment index file from cache " + file.getAbsolutePath());
                }
                segmentIndexes = this.parseSegmentIndexes(FileUtils.readFileToByteArray(file), this.mMasterIndexContainer.getMasterIndex().getSegmentIndexesSize());
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_imv2", "Loaded segment index file from cache " + file.getAbsolutePath());
                    return segmentIndexes;
                }
            }
            catch (Throwable t) {
                Log.e("nf_subtitles_imv2", "Failed to load and parse segment index from cache", t);
                return false;
            }
        }
        return segmentIndexes;
    }
    
    @Override
    protected void downloadSegment(final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Download segment " + n + " if needed");
        }
        if (n < 0 || this.mSegmentIndexContainers == null || n >= this.mSegmentIndexContainers.length) {
            Log.w("nf_subtitles_imv2", "Invalid index requested!");
            return;
        }
        final SegmentIndex segmentIndex = this.mSegmentIndexContainers[n].getSegmentIndex();
        if (segmentIndex == null) {
            Log.e("nf_subtitles_imv2", "Segment index is null, this should NOT happen!");
            return;
        }
        if (this.importSegment(segmentIndex)) {
            Log.d("nf_subtitles_imv2", "No need to download segment images, succesfully loaded from cache");
            return;
        }
        Log.d("nf_subtitles_imv2", "Image(s) missing, go and download...");
        this.downloadSegment(segmentIndex);
    }
    
    @Override
    public int getNumberOfDisplayedSubtitles() {
        synchronized (this) {
            new ArrayList();
            int n3;
            if (this.mSegmentIndexContainers != null) {
                int n = 0;
                int n2 = 0;
                while (true) {
                    n3 = n2;
                    if (n >= this.mSegmentIndexContainers.length) {
                        break;
                    }
                    final ImageDescriptor[] images = this.mSegmentIndexContainers[n].getSegmentIndex().getImages();
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
    Label_0172_Outer:
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
                Label_0272:Label_0106_Outer:
                while (true) {
                Label_0265:
                    while (true) {
                        Label_0255: {
                            synchronized (this) {
                                if (this.mIndexOfLastSearch == -1) {
                                    if (Log.isLoggable()) {
                                        Log.d("nf_subtitles_imv2", "User just seeked, there was no pts update after that, just return already known value " + this.mNumberOfSubtitlesExpectedToBeDisplayed);
                                    }
                                    mNumberOfSubtitlesExpectedToBeDisplayed = this.mNumberOfSubtitlesExpectedToBeDisplayed;
                                }
                                else {
                                    list = new ArrayList<ImageDescriptor>();
                                    if (this.mSegmentIndexContainers != null) {
                                        n = 0;
                                        mNumberOfSubtitlesExpectedToBeDisplayed = 0;
                                        if (n >= this.mSegmentIndexContainers.length) {
                                            break Label_0272;
                                        }
                                        images = this.mSegmentIndexContainers[n].getSegmentIndex().getImages();
                                        length = images.length;
                                        n2 = 0;
                                        if (n2 >= length) {
                                            break Label_0265;
                                        }
                                        imageDescriptor = images[n2];
                                        n3 = mNumberOfSubtitlesExpectedToBeDisplayed;
                                        if (!imageDescriptor.isVisibleInGivenTimeRange(this.mStartPlayPositionInTitleInMs, this.mLastRenderedPositionInTitleInMs)) {
                                            break Label_0255;
                                        }
                                        n3 = mNumberOfSubtitlesExpectedToBeDisplayed;
                                        if (!list.contains(imageDescriptor)) {
                                            list.add(imageDescriptor);
                                            n3 = mNumberOfSubtitlesExpectedToBeDisplayed + 1;
                                        }
                                        break Label_0255;
                                    }
                                    else {
                                        n2 = (mNumberOfSubtitlesExpectedToBeDisplayed = this.mNumberOfSubtitlesExpectedToBeDisplayed + n);
                                        if (Log.isLoggable()) {
                                            Log.d("nf_subtitles_imv2", n + " where supposed to be visible between " + this.mStartPlayPositionInTitleInMs + " and " + this.mLastRenderedPositionInTitleInMs + " for total of " + n2);
                                            mNumberOfSubtitlesExpectedToBeDisplayed = n2;
                                        }
                                    }
                                }
                                return mNumberOfSubtitlesExpectedToBeDisplayed;
                            }
                        }
                        ++n2;
                        mNumberOfSubtitlesExpectedToBeDisplayed = n3;
                        continue Label_0172_Outer;
                    }
                    ++n;
                    continue Label_0106_Outer;
                }
                n = mNumberOfSubtitlesExpectedToBeDisplayed;
                continue;
            }
        }
    }
    
    @Override
    public short getRootContainerExtentX() {
        if (this.mMasterIndexContainer != null && this.mMasterIndexContainer.getHeader() != null) {
            return this.mMasterIndexContainer.getHeader().getRootContainerExtentX();
        }
        return 0;
    }
    
    @Override
    public short getRootContainerExtentY() {
        if (this.mMasterIndexContainer != null && this.mMasterIndexContainer.getHeader() != null) {
            return this.mMasterIndexContainer.getHeader().getRootContainerExtentY();
        }
        return 0;
    }
    
    @Override
    public ISubtitleDef$SubtitleProfile getSubtitleProfile() {
        return ISubtitleDef$SubtitleProfile.IMAGE_ENC;
    }
    
    @Override
    public SubtitleScreen getSubtitlesForPosition(final long mLastKnownPosition) {
        this.mLastKnownPosition = mLastKnownPosition;
        final SegmentIndex segmentForPosition = this.getSegmentForPosition(mLastKnownPosition);
        List<SubtitleBlock> allVisibleSubtitleBlocks;
        if (segmentForPosition != null) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "Segment found for ts " + mLastKnownPosition + ": " + segmentForPosition);
            }
            allVisibleSubtitleBlocks = segmentForPosition.getAllVisibleSubtitleBlocks(mLastKnownPosition);
            this.downloadIfNeeded(segmentForPosition);
        }
        else {
            Log.d("nf_subtitles_imv2", "Segment not found");
            allVisibleSubtitleBlocks = null;
        }
        return new SubtitleScreen(this, allVisibleSubtitleBlocks, null, 2000, mLastKnownPosition);
    }
    
    @Override
    protected String initCache() {
        return this.mKey = this.getCacheName();
    }
    
    @Override
    public void load() {
        this.initCache();
        if (this.handleImport()) {
            Log.d("nf_subtitles_imv2", "Sucesfully imported cached data!");
            return;
        }
        Log.d("nf_subtitles_imv2", "Unable to import from cached data, go and start downloading itrk!");
        this.handleDownloadMasterIndexContainer();
    }
    
    protected boolean parseMasterIndexContainer(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "nf_subtitles_imv2"
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
        //    30: new             Lcom/netflix/mediaclient/service/player/subtitles/image/v2/BoxHeader;
        //    33: dup            
        //    34: aload           5
        //    36: invokespecial   com/netflix/mediaclient/service/player/subtitles/image/v2/BoxHeader.<init>:(Ljava/io/DataInputStream;)V
        //    39: astore          6
        //    41: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    44: ifeq            74
        //    47: ldc             "nf_subtitles_imv2"
        //    49: new             Ljava/lang/StringBuilder;
        //    52: dup            
        //    53: invokespecial   java/lang/StringBuilder.<init>:()V
        //    56: ldc_w           "Header succeafully parsed "
        //    59: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    62: aload           6
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    67: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    70: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    73: pop            
        //    74: aload_0        
        //    75: new             Lcom/netflix/mediaclient/service/player/subtitles/image/v2/ISCTrack;
        //    78: dup            
        //    79: aload           6
        //    81: aload           5
        //    83: invokespecial   com/netflix/mediaclient/service/player/subtitles/image/v2/ISCTrack.<init>:(Lcom/netflix/mediaclient/service/player/subtitles/image/v2/BoxHeader;Ljava/io/DataInputStream;)V
        //    86: putfield        com/netflix/mediaclient/service/player/subtitles/ImageV2SubtitleParser.mMasterIndexContainer:Lcom/netflix/mediaclient/service/player/subtitles/image/v2/ISCTrack;
        //    89: iconst_1       
        //    90: istore_3       
        //    91: iconst_1       
        //    92: istore_2       
        //    93: aload           5
        //    95: invokevirtual   java/io/DataInputStream.close:()V
        //    98: aload           4
        //   100: invokevirtual   java/io/InputStream.close:()V
        //   103: aload_0        
        //   104: ldc_w           "master.idx"
        //   107: aload_1        
        //   108: invokevirtual   com/netflix/mediaclient/service/player/subtitles/ImageV2SubtitleParser.saveFileSafelyToCache:(Ljava/lang/String;[B)V
        //   111: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   114: ifeq            145
        //   117: ldc             "nf_subtitles_imv2"
        //   119: new             Ljava/lang/StringBuilder;
        //   122: dup            
        //   123: invokespecial   java/lang/StringBuilder.<init>:()V
        //   126: ldc             ""
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: aload_0        
        //   132: getfield        com/netflix/mediaclient/service/player/subtitles/ImageV2SubtitleParser.mMasterIndexContainer:Lcom/netflix/mediaclient/service/player/subtitles/image/v2/ISCTrack;
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   138: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   141: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   144: pop            
        //   145: iload_2        
        //   146: ireturn        
        //   147: astore_1       
        //   148: iconst_0       
        //   149: istore_2       
        //   150: iload_2        
        //   151: ifeq            167
        //   154: ldc             "nf_subtitles_imv2"
        //   156: ldc_w           "Failed to close master index input stream"
        //   159: aload_1        
        //   160: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   163: pop            
        //   164: goto            111
        //   167: ldc             "nf_subtitles_imv2"
        //   169: ldc_w           "Failed to parse master index"
        //   172: aload_1        
        //   173: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   176: pop            
        //   177: goto            111
        //   180: astore_1       
        //   181: iload_3        
        //   182: istore_2       
        //   183: goto            150
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  30     74     147    150    Ljava/lang/Throwable;
        //  74     89     147    150    Ljava/lang/Throwable;
        //  93     111    180    186    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
    
    protected void parseSegment(final byte[] array, final SegmentIndex segmentIndex, final String s, final String s2) {
        if (array == null) {
            Log.e("nf_subtitles_imv2", "Response data for segment is NULL");
        }
        else {
            while (true) {
                Log.d("nf_subtitles_imv2", "Segment received, parse it...");
                while (true) {
                    int n;
                    try {
                        final long segmentStartPosition = segmentIndex.getSegmentStartPosition();
                        n = 0;
                        if (n >= segmentIndex.getImages().length) {
                            break;
                        }
                        final ImageDescriptor imageDescriptor = segmentIndex.getImages()[n];
                        final int n2 = (int)(imageDescriptor.getImageStartPosition() - segmentStartPosition);
                        final int n3 = imageDescriptor.getSize() + n2;
                        if (Log.isLoggable()) {
                            Log.d("nf_subtitles_imv2", "Extract image " + imageDescriptor.getName() + ", start byte " + n2 + ", size " + imageDescriptor.getSize() + ", end byte " + n3);
                        }
                        final byte[] decodeImageIfNeeded = this.decodeImageIfNeeded(Arrays.copyOfRange(array, n2, n3), segmentIndex.getContainer(), n, s2, imageDescriptor.getTotalIndex());
                        if (decodeImageIfNeeded != null) {
                            imageDescriptor.setLocalImagePath(this.mPlayer.getPlayerFileCache().saveFile(this.mKey, imageDescriptor.getName(), decodeImageIfNeeded));
                            if (Log.isLoggable()) {
                                Log.d("nf_subtitles_imv2", "" + imageDescriptor);
                            }
                        }
                        else if (Log.isLoggable()) {
                            Log.e("nf_subtitles_imv2", "No image for " + imageDescriptor);
                        }
                    }
                    catch (Throwable t) {
                        Log.e("nf_subtitles_imv2", "Failed to parse segment", t);
                        this.onError(s, DnsManager.getInstance().getNameServers(), ISubtitleDef$SubtitleFailure.parsing, null);
                        return;
                    }
                    ++n;
                    continue;
                }
            }
        }
    }
    
    protected boolean parseSegmentIndexes(byte[] t, int index) {
        boolean b = true;
        if (t == null) {
            Log.e("nf_subtitles_imv2", "Response data for segment indexes is NULL");
            return false;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Downloaded " + t.length + " bytes of all segment indexes. Expecting " + index);
        }
        Label_0269: {
            if (t.length == index) {
                break Label_0269;
            }
            Log.e("nf_subtitles_imv2", "Size mismatch!");
            if (t.length < index) {
                Log.e("nf_subtitles_imv2", "Not enough data, abort parsing");
                return false;
            }
            Log.w("nf_subtitles_imv2", "More data than expected, start parsing...");
        Label_0149_Outer:
            while (true) {
                t = (Throwable)new ByteArrayInputStream((byte[])(Object)t);
                final DataInputStream dataInputStream = new DataInputStream((InputStream)t);
                final int segmentCount = this.mMasterIndexContainer.getMasterIndex().getSegmentCount();
                this.mSegmentIndexContainers = new ISCSegment[segmentCount];
                index = 0;
                int n = 0;
                int n2 = 1;
            Label_0293_Outer:
                while (true) {
                    Label_0281: {
                        if (index >= segmentCount) {
                            break Label_0281;
                        }
                        while (true) {
                            try {
                                final ISCSegment iscSegment = new ISCSegment(new BoxHeader(dataInputStream), n2, n, dataInputStream);
                                this.mSegmentIndexContainers[index] = iscSegment;
                                n2 += iscSegment.getSegmentIndex().getDuration();
                                n += iscSegment.getSegmentIndex().getSampleCount();
                                iscSegment.getSegmentIndex().setIndex(index);
                                if (Log.isLoggable()) {
                                    Log.d("nf_subtitles_imv2", "Segment index " + index + " " + iscSegment);
                                }
                                ++index;
                                continue Label_0293_Outer;
                                Label_0311: {
                                    Log.e("nf_subtitles_imv2", "Failed to parse segment index", t);
                                }
                                return b;
                                Log.d("nf_subtitles_imv2", "Expected data, start parsing...");
                                continue Label_0149_Outer;
                                try {
                                    dataInputStream.close();
                                    ((InputStream)t).close();
                                    return true;
                                }
                                catch (Throwable t2) {}
                                // iftrue(Label_0311:, !b)
                                Log.e("nf_subtitles_imv2", "Failed to close segment indexes input stream", t);
                                return b;
                            }
                            catch (Throwable t) {
                                b = false;
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public void seeked(final int n) {
        super.seeked(n);
        this.mLastKnownPosition = n;
        final ArrayList<ImageDescriptor> list = new ArrayList<ImageDescriptor>();
        int n4;
        if (this.mSegmentIndexContainers != null) {
            int n2 = 0;
            int n3 = 0;
            while (true) {
                n4 = n3;
                if (n2 >= this.mSegmentIndexContainers.length) {
                    break;
                }
                final ImageDescriptor[] images = this.mSegmentIndexContainers[n2].getSegmentIndex().getImages();
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
            Log.d("nf_subtitles_imv2", n4 + " where supposed to be visible between " + this.mStartPlayPositionInTitleInMs + " and " + this.mLastRenderedPositionInTitleInMs + " for total of " + this.mNumberOfSubtitlesExpectedToBeDisplayed);
        }
        this.downloadIfNeeded(this.getSegmentForPosition(this.mLastKnownPosition));
    }
}
