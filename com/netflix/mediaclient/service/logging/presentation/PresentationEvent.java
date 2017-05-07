// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation;

import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.Trackable;
import java.util.ArrayList;
import java.util.List;

public class PresentationEvent
{
    private static final String LOCATION = "location";
    private static final String RANK = "rank";
    private static final String REQUEST_ID = "request_id";
    private static final String ROW = "row";
    private static String TAG;
    private static final String TIME = "time";
    private static final String TRACK_ID = "track_id";
    private static final String VIDEO_ID = "video_id";
    private static final String VIDEO_IDS = "video_ids";
    private String location;
    private int rank;
    private String requestId;
    private int row;
    private long time;
    private int trackId;
    private List<String> videoIds;
    
    static {
        PresentationEvent.TAG = "nf_presentation";
    }
    
    public PresentationEvent() {
        this.videoIds = new ArrayList<String>();
    }
    
    public PresentationEvent(final Trackable trackable, final List<String> videoIds, final int rank, final UiLocation uiLocation) {
        this.videoIds = videoIds;
        this.requestId = trackable.getRequestId();
        this.trackId = trackable.getTrackId();
        this.row = trackable.getListPos();
        this.rank = rank;
        this.location = uiLocation.getValue();
        this.time = System.currentTimeMillis();
    }
    
    public static PresentationEvent createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final PresentationEvent presentationEvent = new PresentationEvent();
        try {
            presentationEvent.videoIds.add(JsonUtils.getString(jsonObject, "video_id", null));
            presentationEvent.requestId = JsonUtils.getString(jsonObject, "request_id", null);
            presentationEvent.trackId = JsonUtils.getInt(jsonObject, "track_id", 0);
            presentationEvent.row = JsonUtils.getInt(jsonObject, "row", 0);
            presentationEvent.rank = JsonUtils.getInt(jsonObject, "rank", 0);
            presentationEvent.location = JsonUtils.getString(jsonObject, "location", null);
            presentationEvent.time = JsonUtils.getLong(jsonObject, "time", 0L);
            return presentationEvent;
        }
        catch (Exception ex) {
            Log.d(PresentationEvent.TAG, String.format("fail to create PT event from json: %s ", jsonObject.toString()));
            return null;
        }
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public int getRank() {
        return this.rank;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public String getVideoIds() {
        return this.videoIds.toString();
    }
    
    public JSONArray toJSONArray() throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        int rank = this.rank;
        for (final String s : this.videoIds) {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.putOpt("video_id", (Object)s);
            jsonObject.putOpt("request_id", (Object)this.requestId);
            jsonObject.putOpt("track_id", (Object)this.trackId);
            jsonObject.putOpt("row", (Object)this.row);
            jsonObject.putOpt("rank", (Object)rank);
            jsonObject.putOpt("location", (Object)this.location);
            jsonObject.putOpt("time", (Object)this.time);
            ++rank;
            jsonArray.put((Object)jsonObject);
        }
        return jsonArray;
    }
    
    public JSONObject toJSONObject() throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<String> iterator = this.videoIds.iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)iterator.next());
        }
        final JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("video_ids", (Object)jsonArray.toString());
        jsonObject.putOpt("request_id", (Object)this.requestId);
        jsonObject.putOpt("track_id", (Object)this.trackId);
        jsonObject.putOpt("row", (Object)this.row);
        jsonObject.putOpt("rank", (Object)this.rank);
        jsonObject.putOpt("location", (Object)this.location);
        jsonObject.putOpt("time", (Object)this.time);
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "PresentationEvent [videoIds=" + this.videoIds + ", requestId=" + this.requestId + ", trackId=" + this.trackId + ", row=" + this.row + ", rank=" + this.rank + ", location=" + this.location + ", time=" + this.time + "]";
    }
}
