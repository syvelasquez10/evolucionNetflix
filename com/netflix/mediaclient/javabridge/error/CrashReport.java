// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.error;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.Asset;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CrashReport
{
    public static final String EP_ID = "NF_CrashReport.episodeId";
    public static final String ERROR_CODE = "NF_CrashReport.errorCode";
    public static final String ERROR_NUMBER = "NF_CrashReport.errorNumber";
    public static final String MOVIE_ID = "NF_CrashReport.movieId";
    public static final String PID = "NF_CrashReport.pid";
    public static final String SIGNAL = "NF_CrashReport.signal";
    public static final String SIG_NUMBER = "NF_CrashReport.sigNumber";
    public static final String TRACK_ID = "NF_CrashReport.trackId";
    public static final String TS = "NF_CrashReport.ts";
    private String episodeId;
    private long errorCode;
    private long errorNumber;
    private String movieId;
    private int pid;
    private long sigNumber;
    private Signal signal;
    private int trkId;
    private long ts;
    
    public CrashReport(final Signal signal, final long n, final long n2, final long n3, final int n4) {
        this(signal, n, n2, n3, null, null, 0, System.currentTimeMillis(), n4);
    }
    
    public CrashReport(final Signal signal, final long sigNumber, final long errorNumber, final long errorCode, final String s, final String s2, final int trkId, final long ts, final int pid) {
        this.movieId = "";
        this.episodeId = "";
        this.trkId = 0;
        this.signal = signal;
        this.sigNumber = sigNumber;
        this.errorNumber = errorNumber;
        this.errorCode = errorCode;
        this.ts = ts;
        this.movieId = this.toNoNullString(s);
        this.episodeId = this.toNoNullString(s2);
        this.trkId = trkId;
        this.pid = pid;
    }
    
    private String toGmtString(final Date date) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }
    
    private String toNoNullString(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        return s2;
    }
    
    public String getCrashTime() {
        return this.toGmtString(new Date(this.ts));
    }
    
    public String getEpisodeId() {
        return this.episodeId;
    }
    
    public long getErrorCode() {
        return this.errorCode;
    }
    
    public long getErrorNumber() {
        return this.errorNumber;
    }
    
    public String getMovieId() {
        return this.movieId;
    }
    
    public int getPid() {
        return this.pid;
    }
    
    public long getSigNumber() {
        return this.sigNumber;
    }
    
    public Signal getSignal() {
        return this.signal;
    }
    
    public long getTimestamp() {
        return this.ts;
    }
    
    public int getTrkId() {
        return this.trkId;
    }
    
    public void setAsset(final Asset asset) {
        if (asset == null) {
            return;
        }
        this.setTrkId(asset.getTrackId());
        if (asset.isEpisode()) {
            this.setMovieId(asset.getPlayableId());
            return;
        }
        this.setMovieId(asset.getParentId());
        this.setEpisodeId(asset.getPlayableId());
    }
    
    public void setEpisodeId(final String s) {
        this.episodeId = this.toNoNullString(s);
    }
    
    public void setMovieId(final String s) {
        this.movieId = this.toNoNullString(s);
    }
    
    public void setTrkId(final int trkId) {
        this.trkId = trkId;
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("signal", (Object)this.signal.getDescription());
        jsonObject.put("signumber", this.sigNumber);
        jsonObject.put("errorCode", this.errorCode);
        jsonObject.put("errorNumber", this.errorNumber);
        jsonObject.put("movieId", (Object)this.movieId);
        jsonObject.put("epId", (Object)this.episodeId);
        jsonObject.put("trkId", this.trkId);
        jsonObject.put("ts", this.ts);
        jsonObject.put("pid", this.pid);
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "CrashReport [errorCode=" + this.errorCode + ", errorNumber=" + this.errorNumber + ", sigNumber=" + this.sigNumber + ", signal=" + this.signal.getDescription() + ", ts=" + this.getCrashTime() + ", movieId=" + this.movieId + ", epId=" + this.episodeId + ", trkId=" + this.trkId + "]";
    }
}
