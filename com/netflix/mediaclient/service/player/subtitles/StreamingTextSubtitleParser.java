// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.net.DnsManager;
import java.io.File;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.servicemgr.IPlayer;

public class StreamingTextSubtitleParser extends BaseTextSubtitleParser
{
    protected String mCacheName;
    protected boolean mDisplayed;
    protected int mNumberOfDisplays;
    
    public StreamingTextSubtitleParser(final IPlayer player, final SubtitleUrl subtitleUrl, final TextStyle textStyle, final TextStyle textStyle2, final float n, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n2) {
        super(player, subtitleUrl, textStyle, textStyle2, n, subtitleParser$DownloadFailedCallback, n2);
        Log.d("nf_subtitles", "Create text based subtitle parser");
        this.mCacheName = this.getCacheName();
    }
    
    protected void handleDownloadedSubtitleData(final byte[] array, final String s, final String[] array2) {
        Log.d("nf_subtitles", "MEDIA_SUBTITLE_DATA 100");
        new BackgroundTask().execute(new StreamingTextSubtitleParser$2(this, array, s, array2));
    }
    
    protected boolean handleImport() {
        Log.d("nf_subtitles", "Check if cache exist!");
        final File file = this.mPlayer.getPlayerFileCache().getFile(this.mCacheName, "manifest_ttml.xml");
        if (file != null) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "File " + file.getAbsolutePath() + " exist");
            }
            try {
                final String fileWithUTF8Encoding = FileUtils.readFileWithUTF8Encoding(file.getAbsolutePath());
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Importing subtitles metadata from cached file " + file.getAbsolutePath() + ":\n" + fileWithUTF8Encoding);
                }
                this.parse(fileWithUTF8Encoding);
                Log.d("nf_subtitles", "Imported data from existing cache!");
                return true;
            }
            catch (Throwable t) {
                Log.e("nf_subtitles", "We failed to parse subtitle metadata from cached file", t);
            }
        }
        return false;
    }
    
    protected void handleSubtitleData(final String s) {
        final String[] nameServers = DnsManager.getInstance().getNameServers();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Download file " + s);
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Subtitles download started from URL " + s);
            if (nameServers == null || nameServers.length < 1) {
                Log.d("nf_subtitles", "Name servers missing");
            }
            else {
                for (int length = nameServers.length, i = 0; i < length; ++i) {
                    Log.d("nf_subtitles", "Name server: " + nameServers[i]);
                }
            }
        }
        this.getResourceFetcher().fetchResourceDirectly(s, IClientLogging$AssetType.subtitles, new StreamingTextSubtitleParser$1(this, s, nameServers));
        Log.d("nf_subtitles", "Subtitles download start done.");
    }
    
    protected void injectContent(final byte[] array) {
        final String s = new String(array, "UTF-8");
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Downloaded subtitles metadata:\n" + s);
        }
        this.parse(s);
        if (this.mPlayer.getPlayerFileCache().saveFile(this.mCacheName, "manifest_ttml.xml", array) != null) {
            Log.d("nf_subtitles", "Text subtitle xml saved to cache.");
            return;
        }
        Log.w("nf_subtitles", "Failed to cache text subtitle xml!!!");
        this.reportHandledException(new NullPointerException("Failed to cache text subtitle xml!"));
    }
    
    @Override
    public void load() {
        if (!this.handleImport()) {
            this.handleSubtitleData(this.mSubtitleData.getDownloadUrl());
        }
    }
}
