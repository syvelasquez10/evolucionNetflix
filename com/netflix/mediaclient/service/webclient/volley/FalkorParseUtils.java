// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import java.util.Locale;
import java.util.Iterator;
import java.util.Map;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.service.webclient.model.branches.Episode$Detail;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Video$BookmarkStill;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Bookmark;
import com.netflix.mediaclient.service.webclient.model.branches.Video$InQueue;
import com.netflix.mediaclient.service.webclient.model.branches.Video$UserRating;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Detail;
import com.netflix.mediaclient.service.webclient.model.PlayableVideo;
import com.netflix.mediaclient.service.webclient.model.branches.Video$KubrickSummary;
import com.netflix.mediaclient.service.webclient.model.branches.KubrickVideo;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.model.branches.Season$Detail;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Evidence;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.NetflixApplication;
import com.google.gson.Gson;

public class FalkorParseUtils
{
    private static final String FIELD_PATH = "path";
    private static final String FIELD_VALUE = "value";
    private static final String KUBRICK_JSON_KEY = "kubrick";
    public static final String METHOD_TYPE_CALL = "call";
    public static final String METHOD_TYPE_GET = "get";
    private static final String TAG = "FalkorParseUtils";
    public static final String URL_PARAM_KEY_PARAM = "param";
    private static final Gson gson;
    
    static {
        gson = NetflixApplication.getGson();
    }
    
    public static String buildEmptyValueMessage() {
        return new String("{\"value\":{},\"paths\":[[\"topGenres\",\"null\"]]}");
    }
    
    public static String buildErrorMessage() {
        return new String("{\"value\":{},\"error\":{\"message\":\"For input string: null\",\"pql\":[\"topGenre\",[\"null\"]]}}");
    }
    
    public static Video$Evidence buildEvidence(final JsonObject jsonObject) {
        final Video$Evidence video$Evidence = null;
        Video$Evidence video$Evidence2;
        if (!jsonObject.has("evidence")) {
            video$Evidence2 = video$Evidence;
        }
        else {
            final JsonObject asJsonObject = jsonObject.getAsJsonObject("evidence");
            if (Log.isLoggable("FalkorParseUtils", 2)) {
                Log.v("FalkorParseUtils", "Handling evidence leaf: " + asJsonObject);
            }
            video$Evidence2 = video$Evidence;
            if (asJsonObject.has("value")) {
                final JsonElement value = asJsonObject.get("value");
                if (value == null || value.isJsonNull()) {
                    Log.v("FalkorParseUtils", "null evidence found");
                    return null;
                }
                if (value.isJsonArray()) {
                    Log.v("FalkorParseUtils", "json array found in evidence - skipping");
                    return null;
                }
                final Video$Evidence video$Evidence3 = video$Evidence2 = FalkorParseUtils.gson.fromJson(value.getAsJsonObject(), Video$Evidence.class);
                if (Log.isLoggable("FalkorParseUtils", 2)) {
                    Log.v("FalkorParseUtils", "Parsed evidence: " + video$Evidence3);
                    return video$Evidence3;
                }
            }
        }
        return video$Evidence2;
    }
    
    public static String buildInnerErrorMessage() {
        return new String("{ \"value\": {},  \"error\": { \"innerErrors\": [{\"message\": \"Not authorized.\",\"pql\": [\"topGenre\", [\"7462\"]],\"unauthorized\": true }, {\"message\": \"Not authorized.\",\"pql\": [\"topGenre\", [\"7462\"]], \"unauthorized\": true }]}}");
    }
    
    public static String buildMapErrorMessage() {
        return new String("");
    }
    
    public static String buildNotAuthorizedMessage() {
        return new String("{\"value\":{},\"error\":{\"message\":\"Not authorized.\",\"pql\":[\"topGenre\",[\"null\"]],\"unauthorized\":true}}");
    }
    
    public static String buildNullPointerMessage() {
        return new String("{\"value\":{},\"error\":{\"innerErrors\":[{\"error\":\"java.lang.NullPointerException\"},{\"error\":\"java.lang.NullPointerException\"}]}}");
    }
    
    public static void buildSeasonsList(final String s, final JsonObject jsonObject, final List<SeasonDetails> list, final int n, int i) {
        int n2 = 0;
        while (i >= n) {
            final String string = Integer.toString(i);
            int n3;
            if (jsonObject.has(string)) {
                final JsonObject asJsonObject = jsonObject.getAsJsonObject(string);
                final com.netflix.mediaclient.service.webclient.model.SeasonDetails seasonDetails = new com.netflix.mediaclient.service.webclient.model.SeasonDetails();
                seasonDetails.detail = getPropertyObject(asJsonObject, "detail", Season$Detail.class);
                list.add(0, seasonDetails);
                n3 = 1;
            }
            else if ((n3 = n2) != 0) {
                if (Log.isLoggable(s, 3)) {
                    Log.d(s, String.format("Adding sentinel at index %s", string));
                }
                list.add(0, BrowseVideoSentinels.getUnavailableSeasonsDetails());
                n3 = n2;
            }
            --i;
            n2 = n3;
        }
    }
    
    public static void buildSimilarVideosList(JsonObject asJsonObject, final Video video, final int n) {
        final ArrayList<Video$Summary> similarVideos = new ArrayList<Video$Summary>();
        if (asJsonObject.has("similars")) {
            asJsonObject = asJsonObject.getAsJsonObject("similars");
            video.similarListSummary = getPropertyObject(asJsonObject, "summary", TrackableListSummary.class);
            for (int i = 0; i <= n; ++i) {
                final String string = Integer.toString(i);
                if (asJsonObject.has(string)) {
                    similarVideos.add(getPropertyObject(asJsonObject.getAsJsonObject(string), "summary", Video$Summary.class));
                }
            }
        }
        video.similarVideos = (List<com.netflix.mediaclient.servicemgr.model.Video>)similarVideos;
    }
    
    public static Object createObjectFromJson(final String s, final JsonElement jsonElement, final Class<?> clazz) {
        if (Log.isLoggable(s, 2)) {
            Log.v(s, "Creating object from json of type: " + clazz);
        }
        return FalkorParseUtils.gson.fromJson(jsonElement, clazz);
    }
    
    public static com.netflix.mediaclient.servicemgr.model.Video createVideoSummaryObject(final JsonObject jsonObject) {
        if (jsonObject.has("kubrick")) {
            final KubrickVideo kubrickVideo = new KubrickVideo();
            kubrickVideo.summary = getPropertyObject(jsonObject, "summary", Video$Summary.class);
            kubrickVideo.kubrick = getPropertyObject(jsonObject, "kubrick", Video$KubrickSummary.class);
            return kubrickVideo;
        }
        return getPropertyObject(jsonObject, "summary", Video$Summary.class);
    }
    
    public static void fillPlayableVideo(JsonObject jsonObject, final PlayableVideo playableVideo, final boolean userConnectedToFacebook) {
        playableVideo.summary = getPropertyObject(jsonObject, "summary", Video$Summary.class);
        playableVideo.detail = getPropertyObject(jsonObject, "detail", Video$Detail.class);
        playableVideo.rating = getPropertyObject(jsonObject, "rating", Video$UserRating.class);
        playableVideo.inQueue = getPropertyObject(jsonObject, "inQueue", Video$InQueue.class);
        playableVideo.bookmark = getPropertyObject(jsonObject, "bookmark", Video$Bookmark.class);
        playableVideo.bookmarkStill = getPropertyObject(jsonObject, "bookmarkStill", Video$BookmarkStill.class);
        playableVideo.socialEvidence = getPropertyObject(jsonObject, "socialEvidence", SocialEvidence.class);
        playableVideo.userConnectedToFacebook = userConnectedToFacebook;
        if (playableVideo.summary != null && !VideoType.MOVIE.equals(playableVideo.summary.getType()) && jsonObject.has("episodes")) {
            jsonObject = jsonObject.getAsJsonObject("episodes");
            if (jsonObject.has("current")) {
                jsonObject = jsonObject.getAsJsonObject("current");
                playableVideo.currentEpisode = getPropertyObject(jsonObject, "detail", Episode$Detail.class);
                playableVideo.currentEpisodeBookmark = getPropertyObject(jsonObject, "bookmark", Video$Bookmark.class);
            }
        }
    }
    
    public static JsonObject getDataObj(final String s, final String s2) {
        JsonObject asJsonObject;
        try {
            asJsonObject = new JsonParser().parse(s2).getAsJsonObject();
            if (hasErrors(asJsonObject)) {
                throw new FalkorServerException(getErrorMessage(asJsonObject));
            }
        }
        catch (Exception ex) {
            Log.v(s, "String response to parse = " + s2);
            throw new FalkorParseException("Error in creating JsonObject", ex);
        }
        return asJsonObject.getAsJsonObject("value");
    }
    
    public static String getErrorMessage(JsonObject asJsonObject) {
        if (hasErrors(asJsonObject)) {
            asJsonObject = asJsonObject.getAsJsonObject("error");
            if (asJsonObject.has("message")) {
                return asJsonObject.get("message").toString();
            }
            if (asJsonObject.has("innerErrors")) {
                return asJsonObject.get("innerErrors").toString();
            }
        }
        return null;
    }
    
    public static JsonObject getFirstJsonObject(JsonObject asJsonObject) {
        if (asJsonObject == null) {
            return null;
        }
        final Iterator<Map.Entry<String, JsonElement>> iterator = asJsonObject.entrySet().iterator();
        JsonElement jsonElement;
        if (iterator.hasNext()) {
            jsonElement = ((Map.Entry<String, JsonElement>)iterator.next()).getValue();
        }
        else {
            jsonElement = null;
        }
        if (jsonElement != null) {
            asJsonObject = jsonElement.getAsJsonObject();
        }
        else {
            asJsonObject = null;
        }
        return asJsonObject;
    }
    
    public static Gson getGson() {
        return FalkorParseUtils.gson;
    }
    
    public static String getIdFromPath(final String s, final JsonObject jsonObject) {
        try {
            return jsonObject.getAsJsonArray("path").get(1).getAsString();
        }
        catch (Exception ex) {
            Log.v(s, "String jsonObj to parse = " + jsonObject.toString());
            throw new FalkorParseException("Missing path/id in jsonObj", ex);
        }
    }
    
    public static <T> T getPropertyObject(final JsonObject jsonObject, final String s, final Class<T> clazz) {
        if (jsonObject.has(s)) {
            return FalkorParseUtils.gson.fromJson(jsonObject.get(s), clazz);
        }
        return null;
    }
    
    public static VideoType getTypeFromPath(final String s, final JsonObject jsonObject) {
        try {
            return VideoType.create(jsonObject.getAsJsonArray("path").get(0).getAsString());
        }
        catch (Exception ex) {
            Log.v(s, "String jsonObj to parse = " + jsonObject.toString());
            throw new FalkorParseException("Missing path/type in jsonObj", ex);
        }
    }
    
    public static boolean hasErrors(final JsonObject jsonObject) {
        return jsonObject.has("error") || jsonObject.has("innerErrors");
    }
    
    public static boolean isAlreadyInQueue(final String s) {
        return s != null && s.toLowerCase(Locale.US).contains("alreadyinqueue");
    }
    
    public static boolean isDeletedProfile(final String s) {
        return s.toLowerCase(Locale.US).contains("deleted profile");
    }
    
    public static boolean isEmpty(final JsonObject jsonObject) {
        return jsonObject.isJsonNull() || jsonObject.toString().equals("{}");
    }
    
    public static boolean isInvalidCounty(final String s) {
        return s.toLowerCase(Locale.US).contains("failurereason=invalidcountry");
    }
    
    public static boolean isMapCacheError(final String s) {
        return s.contains("cache miss") || s.contains("mapgetcachedlistclient failed") || s.contains("cachemiss");
    }
    
    public static boolean isMapError(final String s) {
        return s.toLowerCase(Locale.US).contains("map error");
    }
    
    public static boolean isNotAuthorized(final String s) {
        return s.toLowerCase(Locale.US).contains("not authorized") || s.toLowerCase(Locale.US).contains("unauthorized");
    }
    
    public static boolean isNotInQueue(final String s) {
        return s != null && s.toLowerCase(Locale.US).contains("notinqueue");
    }
    
    public static boolean isNullPointerException(final String s) {
        return s.toLowerCase(Locale.US).contains("nullpointerexception");
    }
    
    public static boolean isWrongState(final String s) {
        return s.contains("wrong state");
    }
    
    public static boolean wasRequestNotValid(final String s) {
        return s != null && s.toLowerCase(Locale.US).contains("notvalidrequest");
    }
}
