// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.NumberUtils;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;

public class DataContext
{
    public static final String RANK = "rank";
    public static final String REQUEST_ID = "requestId";
    public static final String ROW = "row";
    private static final String TAG = "DataContext";
    public static final String TRACK_ID = "trackId";
    public static final String VIDEO_ID = "videoId";
    public static final String XID = "xid";
    @SerializedName("rank")
    @Since(1.0)
    private Integer rank;
    @SerializedName("requestId")
    @Since(1.0)
    private String requestId;
    @SerializedName("row")
    @Since(1.0)
    private Integer row;
    @SerializedName("trackId")
    @Since(1.0)
    private Integer trackId;
    @SerializedName("videoId")
    @Since(1.0)
    private Integer videoId;
    @SerializedName("xid")
    @Since(1.0)
    private String xid;
    
    public DataContext() {
    }
    
    public DataContext(final Trackable trackable) {
        this(trackable, 0, null);
    }
    
    public DataContext(final Trackable trackable, final Integer rank, final String s) {
        if (trackable != null) {
            this.setRow(trackable.getListPos());
            this.setRequestId(trackable.getRequestId());
            this.setTrackId(trackable.getTrackId());
        }
        this.setRank(rank);
        this.setVideoId(NumberUtils.toIntegerSafely(s, null));
    }
    
    public DataContext(final PlayContext playContext, final String s) {
        int videoPos;
        if (playContext == null) {
            videoPos = 0;
        }
        else {
            videoPos = playContext.getVideoPos();
        }
        this(playContext, videoPos, s);
        if (Log.isLoggable("DataContext", 5) && playContext == null) {
            Log.w("DataContext", "playContext is null!");
        }
    }
    
    public static DataContext createInstance(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        final DataContext dataContext = new DataContext();
        final int int1 = JsonUtils.getInt(jsonObject, "rank", -1);
        Integer value;
        if (int1 < 0) {
            value = null;
        }
        else {
            value = int1;
        }
        dataContext.rank = value;
        final int int2 = JsonUtils.getInt(jsonObject, "videoId", -1);
        Integer value2;
        if (int2 < 0) {
            value2 = null;
        }
        else {
            value2 = int2;
        }
        dataContext.videoId = value2;
        final int int3 = JsonUtils.getInt(jsonObject, "row", -1);
        Integer value3;
        if (int3 < 0) {
            value3 = null;
        }
        else {
            value3 = int3;
        }
        dataContext.row = value3;
        final int int4 = JsonUtils.getInt(jsonObject, "trackId", -1);
        Integer value4;
        if (int4 < 0) {
            value4 = null;
        }
        else {
            value4 = int4;
        }
        dataContext.trackId = value4;
        dataContext.requestId = JsonUtils.getString(jsonObject, "requestId", null);
        dataContext.xid = JsonUtils.getString(jsonObject, "xid", null);
        return dataContext;
    }
    
    public Integer getRank() {
        return this.rank;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public Integer getRow() {
        return this.row;
    }
    
    public Integer getTrackId() {
        return this.trackId;
    }
    
    public Integer getVideoId() {
        return this.videoId;
    }
    
    public String getXid() {
        return this.xid;
    }
    
    public void setRank(final Integer rank) {
        this.rank = rank;
    }
    
    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }
    
    public void setRow(final Integer row) {
        this.row = row;
    }
    
    public void setTrackId(final Integer trackId) {
        this.trackId = trackId;
    }
    
    public void setVideoId(final Integer videoId) {
        this.videoId = videoId;
    }
    
    public void setXid(final String xid) {
        this.xid = xid;
    }
    
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (this.requestId != null) {
            jsonObject.put("requestId", (Object)this.requestId);
        }
        if (this.trackId != null) {
            jsonObject.put("trackId", (Object)this.trackId);
        }
        if (this.videoId != null) {
            jsonObject.put("videoId", (Object)this.videoId);
        }
        if (this.xid != null) {
            jsonObject.put("xid", (Object)this.xid);
        }
        if (this.row != null) {
            jsonObject.put("row", (Object)this.row);
        }
        if (this.rank != null) {
            jsonObject.put("rank", (Object)this.rank);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "DataContext [requestId=" + this.requestId + ", trackId=" + this.trackId + ", videoId=" + this.videoId + ", xid=" + this.xid + ", row=" + this.row + ", rank=" + this.rank + "]";
    }
}
