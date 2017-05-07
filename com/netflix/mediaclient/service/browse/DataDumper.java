// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.util.Set;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.service.browse.cache.HardCache;

public class DataDumper
{
    private static final int BOXSHOT_HEIGHT = 118;
    private static final int BOXSHOT_WIDTH = 83;
    private static final String CR = "\n";
    private static final int MAX_ROWS_IN_LIST = 100;
    private static final int MAX_VIDEOS_IN_ROW = 250;
    private static final String TAG = "DataDumper";
    private boolean dumpErrorOccurred;
    private final HardCache hardCache;
    private final LinkedHashMap<LoMo, List<Video>> lomoVideos;
    private final BrowseWebClient mBrowseWebClient;
    private final SoftCache softCache;
    private String title;
    
    public DataDumper(final BrowseWebClient mBrowseWebClient, final HardCache hardCache, final SoftCache softCache) {
        this.lomoVideos = new LinkedHashMap<LoMo, List<Video>>();
        this.mBrowseWebClient = mBrowseWebClient;
        this.hardCache = hardCache;
        this.softCache = softCache;
    }
    
    private void dumpGenre(final String s) {
        Log.v("DataDumper", "Taking a dump for genre: " + s);
        this.mBrowseWebClient.fetchGenres(s, 0, 100, new DataDumper$2(this));
    }
    
    private void dumpHomeLoLomo() {
        Log.v("DataDumper", "Taking a dump for home lolomo");
        this.mBrowseWebClient.fetchLoMos(0, 100, new DataDumper$1(this));
    }
    
    private void dumpVideoList(final HardCache hardCache, final SoftCache softCache, final String s, int n) {
        final List list = (List)this.getFromCache(hardCache, softCache, s);
        if (list != null) {
            Log.d("DataDumper", String.format("Videos in key %s", s));
            for (final Video video : list) {
                Log.d("DataDumper", String.format("(%d) %s, %s, %s", n, video.getId(), video.getType(), video.getTitle()));
                ++n;
            }
        }
    }
    
    private Object getFromCache(final HardCache hardCache, final SoftCache softCache, final String s) {
        Object o;
        if ((o = this.hardCache.get(s)) == null) {
            o = softCache.get(s);
        }
        return o;
    }
    
    private int getVideoStartIndexFromKey(final String s, final String s2) {
        final boolean b = false;
        final String[] split = s.substring(s2.length() + 1).split("_");
        int int1 = b ? 1 : 0;
        if (split.length > 0) {
            int1 = (b ? 1 : 0);
            if (StringUtils.isNotEmpty(split[0])) {
                int1 = Integer.parseInt(split[0]);
            }
        }
        return int1;
    }
    
    private void handleDataLoadCompleted(final LinkedHashMap<LoMo, List<Video>> linkedHashMap) {
        if (this.dumpErrorOccurred) {
            Log.e("DataDumper", "Error occurred - bailing on data dump");
            return;
        }
        final StringBuilder append = new StringBuilder("<!DOCTYPE html><html><body>").append("\n");
        append.append("<h1>").append(this.title).append("</h1>").append("\n");
        for (final LoMo loMo : linkedHashMap.keySet()) {
            Log.v("DataDumper", "lomo: " + loMo.getTitle() + ", " + loMo.getId());
            append.append("<p>");
            append.append("<b>").append(loMo.getTitle()).append("</b>").append("<br />").append("\n");
            append.append("Total video count: ").append(loMo.getNumVideos()).append(", ");
            append.append("ID: ").append(loMo.getId()).append("<br />").append("\n");
            append.append("</p>").append("\n");
            append.append("<table>").append("\n");
            append.append("<tr>").append("\n");
            for (final Video video : linkedHashMap.get(loMo)) {
                Log.v("DataDumper", "lomo: " + loMo.getTitle() + ", video: " + video.getId() + ", " + video.getTitle());
                append.append("<td><img src=\"").append(video.getBoxshotURL()).append("\" alt=\"").append(video.getTitle()).append("\"  width=\"").append(83).append("\" height=\"").append(118).append("\"></td>").append("\n");
            }
            append.append("</tr>").append("\n");
            append.append("</table>").append("\n");
        }
        append.append("</body></html>").append("\n");
        Log.v("DataDumper", "Writing to file...");
        FileUtils.writeStringToFile("DataDumper", append.toString(), "lolomo.html");
        Log.v("DataDumper", "Writing to file complete");
    }
    
    public void dumpHomeLoLoMosAndVideos(final String s, final String title) {
        this.dumpErrorOccurred = false;
        this.lomoVideos.clear();
        if ("lolomo".equals(s)) {
            this.title = "Home LoLoMo";
            this.dumpHomeLoLomo();
            return;
        }
        this.title = title;
        this.dumpGenre(s);
    }
    
    public void dumpHomeLoLoMosAndVideosToLog() {
        final Set<?> keySet = this.hardCache.getKeySet();
        final Set<?> keySet2 = this.softCache.getKeySet();
        while (true) {
            for (final String s : keySet) {
                if (s.contains(BrowseAgent.CACHE_KEY_PREFIX_LOMO)) {
                    final List<LoMo> list = (List<LoMo>)this.getFromCache(this.hardCache, this.softCache, s);
                    if (list != null) {
                        for (final LoMo loMo : list) {
                            Log.d("DataDumper", String.format("%s, %d, %s", loMo.getId(), loMo.getNumVideos(), loMo.getTitle()));
                            final String string = BrowseAgent.CACHE_KEY_PREFIX_VIDEOS + "_" + loMo.getId();
                            for (final String s2 : keySet) {
                                if (s2.contains(string)) {
                                    this.dumpVideoList(this.hardCache, this.softCache, s2, this.getVideoStartIndexFromKey(s2, string));
                                }
                            }
                            for (final String s3 : keySet2) {
                                if (s3.contains(string)) {
                                    this.dumpVideoList(this.hardCache, this.softCache, s3, this.getVideoStartIndexFromKey(s3, string));
                                }
                            }
                        }
                    }
                    return;
                }
            }
            final List<LoMo> list = null;
            continue;
        }
    }
    
    public void dumpHomeLoMos() {
        while (true) {
            for (final String s : this.hardCache.getKeySet()) {
                if (s.contains(BrowseAgent.CACHE_KEY_PREFIX_LOMO)) {
                    final List<LoMo> list = (List<LoMo>)this.getFromCache(this.hardCache, this.softCache, s);
                    if (list != null) {
                        for (final LoMo loMo : list) {
                            Log.d("DataDumper", String.format("%s, %d, %s", loMo.getId(), loMo.getNumVideos(), loMo.getTitle()));
                        }
                    }
                    return;
                }
            }
            final List<LoMo> list = null;
            continue;
        }
    }
}
