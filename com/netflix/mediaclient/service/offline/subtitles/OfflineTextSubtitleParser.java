// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.subtitles;

import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser$DownloadFailedCallback;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.ui.offline.OfflineSubtitle;
import com.netflix.mediaclient.service.player.subtitles.BaseTextSubtitleParser;

public class OfflineTextSubtitleParser extends BaseTextSubtitleParser implements OfflineSubtitleParser
{
    protected OfflineSubtitle mOfflineSubtitle;
    
    public OfflineTextSubtitleParser(final IPlayer player, final OfflineSubtitle mOfflineSubtitle, final TextStyle textStyle, final TextStyle textStyle2, final float n) {
        super(player, mOfflineSubtitle.getSubtitleUrl(), textStyle, textStyle2, n, null, 0L);
        Log.d("nf_subtitles", "Create text based offline subtitle parser");
        this.mOfflineSubtitle = mOfflineSubtitle;
    }
    
    @Override
    public Subtitle getCurrentSubtitle() {
        return this.mOfflineSubtitle;
    }
    
    protected boolean handleImport() {
        Log.d("nf_subtitles", "Check if cache exist!");
        final File file = new File(this.mOfflineSubtitle.getLocalFilePath());
        if (file != null && file.exists()) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "File " + file.getAbsolutePath() + " exist");
            }
            try {
                final String fileWithUTF8Encoding = FileUtils.readFileWithUTF8Encoding(file.getAbsolutePath());
                Log.d("nf_subtitles", "Importing subtitles metadata from offline directory %s :\n%s ", file.getAbsolutePath(), fileWithUTF8Encoding);
                this.parse(fileWithUTF8Encoding);
                Log.d("nf_subtitles", "Imported data from offline directory!");
                return true;
            }
            catch (Throwable t) {
                Log.e("nf_subtitles", "We failed to parse subtitle metadata from cached file", t);
            }
        }
        else {
            Log.e("nf_subtitles", "Offline text subtitle NOT found at " + this.mOfflineSubtitle.getLocalFilePath());
        }
        return false;
    }
    
    @Override
    public void load() {
        this.handleImport();
    }
}
