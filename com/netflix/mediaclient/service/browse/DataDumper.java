// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.util.Iterator;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.servicemgr.IBrowseManager;

public class DataDumper
{
    private static final int BOXSHOT_HEIGHT = 118;
    private static final int BOXSHOT_WIDTH = 83;
    private static final String CR = "\n";
    private static final int MAX_ROWS_IN_LIST = 100;
    private static final int MAX_VIDEOS_IN_ROW = 250;
    private static final String TAG = "DataDumper";
    private final IBrowseManager browseClient;
    private boolean dumpErrorOccurred;
    private final LinkedHashMap<LoMo, List<? extends Video>> lomoVideos;
    private String title;
    
    public DataDumper(final IBrowseManager browseClient) {
        this.lomoVideos = new LinkedHashMap<LoMo, List<? extends Video>>();
        this.browseClient = browseClient;
    }
    
    private void dumpGenreToHtml(final String s) {
        Log.v("DataDumper", "Taking a dump for genre: " + s);
        this.browseClient.fetchGenres(s, 0, 100, new DataDumper$2(this));
    }
    
    private void dumpHomeLoLomoToHtml() {
        Log.v("DataDumper", "Taking a dump for home lolomo");
        this.browseClient.fetchLoMos(0, 100, new DataDumper$1(this));
    }
    
    private void handleDataLoadCompleted(final LinkedHashMap<LoMo, List<? extends Video>> linkedHashMap) {
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
                append.append("<td><img src=\"").append(video.getBoxshotUrl()).append("\" alt=\"").append(video.getTitle()).append("\"  width=\"").append(83).append("\" height=\"").append(118).append("\"></td>").append("\n");
            }
            append.append("</tr>").append("\n");
            append.append("</table>").append("\n");
        }
        append.append("</body></html>").append("\n");
        Log.v("DataDumper", "Writing to file...");
        FileUtils.writeStringToExternalStorageDirectory("DataDumper", append.toString(), "lolomo.html");
        Log.v("DataDumper", "Writing to file complete");
    }
    
    public void dumpHomeLoLoMosAndVideosToHtml(final String s, final String title) {
        this.dumpErrorOccurred = false;
        this.lomoVideos.clear();
        if ("lolomo".equals(s)) {
            this.title = "Home LoLoMo";
            this.dumpHomeLoLomoToHtml();
            return;
        }
        this.title = title;
        this.dumpGenreToHtml(s);
    }
}
