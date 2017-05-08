// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation;

import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONArray;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import java.util.ArrayList;
import java.util.List;

public class PresentationEvent
{
    private static final String IMAGE_TYPE_KEY = "image_key";
    private static final String IS_HERO = "is_hero";
    private static final String LOCATION = "location";
    private static final String RANK = "rank";
    private static final String REQUEST_ID = "request_id";
    private static final String ROW = "row";
    private static String TAG;
    private static final String TIME = "time";
    private static final String TRACK_ID = "track_id";
    private static final String VIDEO_ID = "video_id";
    private boolean isHero;
    private String location;
    private int rank;
    private String requestId;
    private int row;
    private long time;
    private int trackId;
    private final List<String> videoIds;
    private final List<String> videoImageTypeIdentifierIds;
    
    static {
        PresentationEvent.TAG = "nf_presentation";
    }
    
    private PresentationEvent() {
        this.videoIds = new ArrayList<String>();
        this.videoImageTypeIdentifierIds = new ArrayList<String>();
    }
    
    public PresentationEvent(final Trackable trackable, final List<String> videoIds, final List<String> videoImageTypeIdentifierIds, final int rank, final UiLocation uiLocation) {
        this.videoIds = videoIds;
        this.videoImageTypeIdentifierIds = videoImageTypeIdentifierIds;
        this.requestId = trackable.getRequestId();
        int trackId;
        if (trackable.isHero()) {
            trackId = trackable.getHeroTrackId();
        }
        else {
            trackId = trackable.getTrackId();
        }
        this.trackId = trackId;
        this.row = trackable.getListPos();
        this.isHero = trackable.isHero();
        this.rank = rank;
        this.location = uiLocation.getValue();
        this.time = System.currentTimeMillis();
    }
    
    static PresentationEvent createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final PresentationEvent presentationEvent = new PresentationEvent();
        try {
            presentationEvent.videoIds.add(JsonUtils.getString(jsonObject, "video_id", null));
            final String string = JsonUtils.getString(jsonObject, "image_key", null);
            if (presentationEvent != null && presentationEvent.videoImageTypeIdentifierIds != null) {
                presentationEvent.videoImageTypeIdentifierIds.add(string);
            }
            presentationEvent.requestId = JsonUtils.getString(jsonObject, "request_id", null);
            presentationEvent.trackId = JsonUtils.getInt(jsonObject, "track_id", 0);
            presentationEvent.row = JsonUtils.getInt(jsonObject, "row", 0);
            presentationEvent.rank = JsonUtils.getInt(jsonObject, "rank", 0);
            presentationEvent.location = JsonUtils.getString(jsonObject, "location", null);
            presentationEvent.time = JsonUtils.getLong(jsonObject, "time", 0L);
            presentationEvent.isHero = JsonUtils.getBoolean(jsonObject, "is_hero", false);
            return presentationEvent;
        }
        catch (Exception ex) {
            Log.d(PresentationEvent.TAG, String.format("fail to create PT event from json: %s ", jsonObject.toString()));
            return null;
        }
    }
    
    String getLocation() {
        return this.location;
    }
    
    int getRank() {
        return this.rank;
    }
    
    int getRow() {
        return this.row;
    }
    
    long getTime() {
        return this.time;
    }
    
    String getVideoIds() {
        return this.videoIds.toString();
    }
    
    String getVideoImageTypeIdentifierIds() {
        return this.videoImageTypeIdentifierIds.toString();
    }
    
    JSONArray toJSONArray() {
        final JSONArray jsonArray = new JSONArray();
        int rank = this.rank;
        for (int i = 0; i < this.videoIds.size(); ++i) {
            final String s = this.videoIds.get(i);
            final JSONObject jsonObject = new JSONObject();
            jsonObject.putOpt("video_id", (Object)s);
            jsonObject.putOpt("request_id", (Object)this.requestId);
            jsonObject.putOpt("track_id", (Object)this.trackId);
            jsonObject.putOpt("row", (Object)this.row);
            jsonObject.putOpt("rank", (Object)rank);
            jsonObject.putOpt("location", (Object)this.location);
            jsonObject.putOpt("time", (Object)this.time);
            jsonObject.putOpt("is_hero", (Object)this.isHero);
            if (this.videoImageTypeIdentifierIds != null && this.videoImageTypeIdentifierIds.size() > i) {
                final String s2 = this.videoImageTypeIdentifierIds.get(i);
                if (StringUtils.isNotEmpty(s2)) {
                    jsonObject.putOpt("image_key", (Object)s2);
                }
            }
            ++rank;
            jsonArray.put((Object)jsonObject);
        }
        return jsonArray;
    }
    
    @Override
    public String toString() {
        return "PresentationEvent [videoIds=" + this.videoIds + ", videoImageTypeIdentifierIds=" + this.videoImageTypeIdentifierIds + ", requestId=" + this.requestId + ", trackId=" + this.trackId + ", row=" + this.row + ", rank=" + this.rank + ", location=" + this.location + ", time=" + this.time + ", isHero=" + this.isHero + "]";
    }
}
