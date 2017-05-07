// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.util.Set;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
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
        this.mBrowseWebClient.fetchGenres(s, 0, 100, new SimpleBrowseAgentCallback() {
            @Override
            public void onGenresFetched(final List<Genre> list, final Status status) {
                super.onGenresFetched(list, status);
                Log.v("DataDumper", "genres fetched, count: " + list.size());
                final ArrayList<LoMo> list2 = new ArrayList<LoMo>(list);
                for (final Genre genre : list) {
                    DataDumper.this.lomoVideos.put(genre, null);
                    DataDumper.this.mBrowseWebClient.fetchGenreVideos(genre, 0, 250, new VideosCallback(genre, list2));
                }
            }
        });
    }
    
    private void dumpHomeLoLomo() {
        Log.v("DataDumper", "Taking a dump for home lolomo");
        this.mBrowseWebClient.fetchLoMos(0, 100, new SimpleBrowseAgentCallback() {
            @Override
            public void onLoMosFetched(final List<LoMo> list, final Status status) {
                super.onLoMosFetched(list, status);
                Log.v("DataDumper", "lomos fetched, count: " + list.size());
                final ArrayList<LoMo> list2 = new ArrayList<LoMo>(list);
                for (final LoMo loMo : list) {
                    DataDumper.this.lomoVideos.put(loMo, null);
                    DataDumper.this.mBrowseWebClient.fetchVideos(loMo, 0, 250, new VideosCallback(loMo, list2));
                }
            }
        });
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
        final String[] split = s.substring(s2.length() + 1).split("_");
        int int1;
        final int n = int1 = 0;
        if (split.length > 0) {
            int1 = n;
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
        final List<LoMo> list = null;
        final Set<?> keySet = this.hardCache.getKeySet();
        final Set<?> keySet2 = this.softCache.getKeySet();
        final Iterator<?> iterator = keySet.iterator();
        while (true) {
            String s;
            do {
                final List<LoMo> list2 = list;
                if (!iterator.hasNext()) {
                    if (list2 != null) {
                        for (final LoMo loMo : list2) {
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
                s = (String)iterator.next();
            } while (!s.contains(BrowseAgent.CACHE_KEY_PREFIX_LOMO));
            final List<LoMo> list2 = (List<LoMo>)this.getFromCache(this.hardCache, this.softCache, s);
            continue;
        }
    }
    
    public void dumpHomeLoMos() {
        final List<LoMo> list = null;
        final Iterator<?> iterator = this.hardCache.getKeySet().iterator();
        while (true) {
            String s;
            do {
                final List<LoMo> list2 = list;
                if (!iterator.hasNext()) {
                    if (list2 != null) {
                        for (final LoMo loMo : list2) {
                            Log.d("DataDumper", String.format("%s, %d, %s", loMo.getId(), loMo.getNumVideos(), loMo.getTitle()));
                        }
                    }
                    return;
                }
                s = (String)iterator.next();
            } while (!s.contains(BrowseAgent.CACHE_KEY_PREFIX_LOMO));
            final List<LoMo> list2 = (List<LoMo>)this.getFromCache(this.hardCache, this.softCache, s);
            continue;
        }
    }
    
    private class VideosCallback extends SimpleBrowseAgentCallback
    {
        private final LoMo lomo;
        private final List<LoMo> todo;
        
        public VideosCallback(final LoMo lomo, final List<LoMo> todo) {
            this.lomo = lomo;
            this.todo = todo;
        }
        
        @Override
        public void onVideosFetched(final List<Video> list, final Status status) {
            if (DataDumper.this.dumpErrorOccurred) {
                return;
            }
            super.onVideosFetched(list, status);
            if (status.isError()) {
                Log.e("DataDumper", "Bummer!  Invalid status code during data dump :(");
                DataDumper.this.dumpErrorOccurred = true;
                this.todo.clear();
            }
            else {
                DataDumper.this.lomoVideos.put(this.lomo, list);
                this.todo.remove(this.lomo);
            }
            if (this.todo.size() == 0) {
                Log.v("DataDumper", "--LoMo data collection complete--");
                DataDumper.this.handleDataLoadCompleted(DataDumper.this.lomoVideos);
                return;
            }
            Log.v("DataDumper", "Remaining request count: " + this.todo.size());
        }
    }
}
