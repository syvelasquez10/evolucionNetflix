// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.manifest;

import org.json.JSONArray;
import org.json.JSONObject;

public class NfManifestParser
{
    private static Location parseLocation(final JSONObject jsonObject) {
        Location location = null;
        if (jsonObject != null) {
            location = new Location();
            location.id = jsonObject.optString("id", "");
            location.rank = jsonObject.optInt("rank", 0);
            location.level = jsonObject.optInt("level", 0);
            location.weight = jsonObject.optInt("weight", 0);
        }
        return location;
    }
    
    private static Store parseStore(final JSONObject jsonObject) {
        Store store = null;
        if (jsonObject != null) {
            store = new Store();
            store.name = jsonObject.optString("name", "");
            store.rank = jsonObject.optInt("rank", 0);
            store.type = jsonObject.optString("type", "");
            store.lowgrade = jsonObject.optBoolean("trackType", false);
            store.location = parseLocation(jsonObject.optJSONObject("location"));
            store.cdn_id = jsonObject.optInt("cdn_id", 0);
        }
        return store;
    }
    
    public static Stream parseStream(final JSONObject jsonObject) {
        Stream stream = null;
        if (jsonObject != null) {
            stream = new Stream();
            final JSONArray optJSONArray = jsonObject.optJSONArray("urls");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final Url url = parseUrl((JSONObject)optJSONArray.opt(i));
                    if (url != null) {
                        stream.urls.add(url);
                    }
                }
            }
            stream.new_stream_id = jsonObject.optString("new_stream_id", "");
            stream.type = jsonObject.optInt("type", 0);
            stream.bitrate = jsonObject.optInt("bitrate", 0);
            stream.trackType = jsonObject.optString("trackType", "");
            stream.downloadable_id = jsonObject.optString("downloadable_id", "");
            stream.size = jsonObject.optLong("size", 0L);
            stream.isDrm = jsonObject.optBoolean("isDrm", false);
        }
        return stream;
    }
    
    private static Url parseUrl(final JSONObject jsonObject) {
        Url url = null;
        if (jsonObject != null) {
            url = new Url();
            url.cdn_id = jsonObject.optInt("cdn_id", 0);
            url.url = jsonObject.optString("url", "");
            url.store = parseStore(jsonObject.optJSONObject("store"));
        }
        return url;
    }
    
    public static VideoTrack parseVideoTrack(final JSONObject jsonObject) {
        final VideoTrack videoTrack = new VideoTrack();
        videoTrack.type = jsonObject.optInt("type", 0);
        videoTrack.new_track_id = jsonObject.optString("new_track_id", "");
        videoTrack.track_id = jsonObject.optString("track_id", "");
        final JSONArray optJSONArray = jsonObject.optJSONArray("streams");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); ++i) {
                final Stream stream = parseStream((JSONObject)optJSONArray.opt(i));
                if (stream != null) {
                    videoTrack.streams.add(stream);
                }
            }
        }
        return videoTrack;
    }
}
