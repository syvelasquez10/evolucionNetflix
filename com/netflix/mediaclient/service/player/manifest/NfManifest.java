// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

import com.netflix.mediaclient.media.manifest.NfManifestParser;
import com.netflix.mediaclient.media.manifest.VideoTrack;
import com.netflix.mediaclient.media.TrickplayUrl;
import com.netflix.mediaclient.media.SubtitleTrackData;
import com.netflix.mediaclient.ui.player.NccpSubtitle;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.ui.player.NccpAudioSource;
import com.netflix.mediaclient.media.AudioSource;
import android.util.Pair;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.HashMap;
import com.netflix.msl.util.Base64;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.Map;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;

public class NfManifest implements Comparable<NfManifest>
{
    private static final long DEFAULT_MANIFEST_LIFE_MS = 7200000L;
    private static final long DRM_MANIFEST_LIFE_MS = 900000L;
    public static final String EXPIRATION = "expiration";
    public static final String LOCAL_TIMESTAMP = "timestamp";
    private static final int MAX_NUM_STREAMS_PER_TRACK = 30;
    private static final String TAG;
    private static long sManifestLifeFromConfig;
    private int aspectRatioHeight;
    private int aspectRatioWidth;
    private long birthTimeMs;
    private long defaultLifeMs;
    private String drmContextId;
    private byte[] drmHeader;
    private long duration;
    private final long expiryTimeInEndPointTime;
    private boolean hasDrmProfile;
    private AudioSubtitleDefaultOrderInfo[] mDefaultTrackOrderList;
    private long mManifestFetchedTimeInMs;
    private int mNumVideoTracks;
    private Map<Integer, String> mPdsDownloadIds;
    private JSONObject manifest;
    private long movieId;
    private String playbackContextId;
    private int priority;
    
    static {
        TAG = NfManifest.class.getSimpleName();
        NfManifest.sManifestLifeFromConfig = 0L;
    }
    
    public NfManifest(final String s) {
        this.manifest = new JSONObject(s);
        this.mManifestFetchedTimeInMs = this.manifest.optLong("timestamp", System.currentTimeMillis());
        this.movieId = this.manifest.getLong("movieId");
        this.playbackContextId = this.manifest.getString("playbackContextId");
        this.drmContextId = this.manifest.getString("drmContextId");
        this.duration = this.manifest.getLong("duration");
        this.expiryTimeInEndPointTime = this.manifest.optLong("expiration", 0L);
        if (this.expiryTimeInEndPointTime < System.currentTimeMillis()) {
            Log.e(NfManifest.TAG, "expiryTime is less than currentTime " + this.expiryTimeInEndPointTime);
        }
        final JSONArray jsonArray = this.manifest.getJSONArray("video_tracks");
        this.mNumVideoTracks = jsonArray.length();
        if (jsonArray.length() > 0) {
            final JSONObject jsonObject = (JSONObject)jsonArray.get(0);
            final JSONObject optJSONObject = jsonObject.optJSONObject("drmHeader");
            if (optJSONObject != null) {
                if (Log.isLoggable()) {
                    Log.d(NfManifest.TAG, "drmHeader.bytes: " + optJSONObject.getString("bytes"));
                    Log.d(NfManifest.TAG, "drmHeader.systemType: " + optJSONObject.getString("systemType"));
                    Log.d(NfManifest.TAG, "drmHeader.keyId: " + optJSONObject.getString("keyId"));
                }
                this.drmHeader = Base64.decode(optJSONObject.getString("bytes"));
                this.hasDrmProfile = true;
            }
            final int optInt = jsonObject.optInt("maxWidth", 0);
            final int optInt2 = jsonObject.optInt("maxHeight", 0);
            final int optInt3 = jsonObject.optInt("pixelAspectX", 0);
            final int optInt4 = jsonObject.optInt("pixelAspectY", 0);
            if (optInt > 0 && optInt2 > 0 && optInt3 > 0 && optInt4 > 0) {
                this.aspectRatioHeight = optInt4 * optInt2;
                this.aspectRatioWidth = optInt * optInt3;
            }
            else {
                this.aspectRatioHeight = 9;
                this.aspectRatioWidth = 16;
            }
        }
        Log.d(NfManifest.TAG, "parsing defaultTrackOrderList");
        final JSONArray optJSONArray = this.manifest.optJSONArray("defaultTrackOrderList");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.mDefaultTrackOrderList = new AudioSubtitleDefaultOrderInfo[optJSONArray.length()];
            for (int i = 0; i < this.mDefaultTrackOrderList.length; ++i) {
                this.mDefaultTrackOrderList[i] = new AudioSubtitleDefaultOrderInfo(optJSONArray.getJSONObject(i), this.mManifestFetchedTimeInMs);
            }
            if (Log.isLoggable()) {
                Log.d(NfManifest.TAG, "defaultTrackOrderList has " + this.mDefaultTrackOrderList.length);
            }
        }
        long defaultLifeMs;
        if (this.hasDrmProfile) {
            defaultLifeMs = 900000L;
        }
        else {
            defaultLifeMs = 7200000L;
        }
        this.defaultLifeMs = defaultLifeMs;
        this.birthTimeMs = System.currentTimeMillis();
        this.mPdsDownloadIds = new HashMap<Integer, String>();
        this.buildPdsDownloadIdList();
        Log.d(NfManifest.TAG, "parsing manifest end.");
    }
    
    private void addToPdsDownloadIdList(final JSONArray jsonArray, final int n) {
        if (jsonArray != null) {
            int i = 0;
            try {
                while (i < jsonArray.length()) {
                    final JSONArray jsonArray2 = jsonArray.getJSONObject(i).getJSONArray("streams");
                    for (int length = jsonArray2.length(), j = 0; j < length; ++j) {
                        this.mPdsDownloadIds.put(this.getPdsDownloadIdIndex(n + i, j), ((JSONObject)jsonArray2.get(j)).getString("downloadable_id"));
                    }
                    ++i;
                }
            }
            catch (JSONException ex) {
                Log.e(NfManifest.TAG, "error add tracks to downloadIdList", (Throwable)ex);
            }
        }
    }
    
    private void buildPdsDownloadIdList() {
        try {
            final JSONArray jsonArray = this.manifest.getJSONArray("video_tracks");
            final JSONArray optJSONArray = this.manifest.optJSONArray("audio_tracks");
            this.addToPdsDownloadIdList(jsonArray, 0);
            this.addToPdsDownloadIdList(optJSONArray, jsonArray.length());
        }
        catch (JSONException ex) {
            Log.e(NfManifest.TAG, "unable to get tracks", (Throwable)ex);
        }
    }
    
    static void configureManifestLife(final long sManifestLifeFromConfig) {
        NfManifest.sManifestLifeFromConfig = sManifestLifeFromConfig;
    }
    
    private int getPdsDownloadIdIndex(final int n, final int n2) {
        return n * 30 + n2;
    }
    
    public static List<NfManifest> parseManifestResponse(final JSONObject jsonObject) {
        ArrayList<NfManifest> list;
        while (true) {
            list = new ArrayList<NfManifest>();
            Log.d(NfManifest.TAG, "parsing manifest response start ...");
            while (true) {
                String s = null;
                Label_0081: {
                    try {
                        final Iterator keys = jsonObject.keys();
                        while (keys.hasNext()) {
                            s = keys.next();
                            if (!StringUtils.safeEquals(s, "timestamp")) {
                                break Label_0081;
                            }
                            Log.d(NfManifest.TAG, "skip bad entry to break manifest fetch loop");
                        }
                    }
                    catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                if (Log.isLoggable()) {
                    Log.d(NfManifest.TAG, "manifest response has movieId: " + s);
                }
                final String string = jsonObject.getString(s);
                try {
                    list.add(new NfManifest(string));
                    continue;
                }
                catch (JSONException ex2) {
                    Log.d(NfManifest.TAG, "parsing error.");
                    continue;
                }
                continue;
            }
        }
        Log.d(NfManifest.TAG, "parsing manifest response end.");
        return list;
    }
    
    @Override
    public int compareTo(final NfManifest nfManifest) {
        if (this.getRemainLife() <= nfManifest.getRemainLife()) {
            if (this.getRemainLife() < nfManifest.getRemainLife()) {
                return -1;
            }
            if (this.getPriority() > nfManifest.getPriority()) {
                return -1;
            }
            if (this.getPriority() >= nfManifest.getPriority()) {
                return 0;
            }
        }
        return 1;
    }
    
    public Pair<Integer, Integer> getAspectWidthHeight() {
        return (Pair<Integer, Integer>)Pair.create((Object)this.aspectRatioWidth, (Object)this.aspectRatioHeight);
    }
    
    public AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo() {
        return this.mDefaultTrackOrderList;
    }
    
    public AudioSource[] getAudioTrackList() {
        Log.d(NfManifest.TAG, "parsing audio_tracks");
        final JSONArray optJSONArray = this.manifest.optJSONArray("audio_tracks");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            try {
                final AudioSource[] array = new AudioSource[optJSONArray.length()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = NccpAudioSource.newInstance(optJSONArray.getJSONObject(i), i);
                }
                if (Log.isLoggable()) {
                    Log.d(NfManifest.TAG, "audio_tracks has " + array.length);
                }
                return array;
            }
            catch (JSONException ex) {
                Log.w(NfManifest.TAG, " exception when parsing audio_tracks");
            }
        }
        return new AudioSource[0];
    }
    
    public String getDownloadId(final int n, final int n2) {
        return this.mPdsDownloadIds.get(this.getPdsDownloadIdIndex(n, n2));
    }
    
    public String getDrmContextId() {
        return this.drmContextId;
    }
    
    public byte[] getDrmHeader() {
        return this.drmHeader;
    }
    
    public long getDuration() {
        return this.duration;
    }
    
    public JSONObject getJSONObject() {
        return this.manifest;
    }
    
    public JSONObject getLicenseLinkJson() {
        final JSONObject links = this.getLinks();
        if (links != null) {
            return links.optJSONObject("license");
        }
        return null;
    }
    
    public JSONObject getLinks() {
        return this.manifest.optJSONObject("links");
    }
    
    public long getManifestExpiryInEndPointTime() {
        return this.expiryTimeInEndPointTime;
    }
    
    public long getManifestFetchedTimeInMs() {
        return this.mManifestFetchedTimeInMs;
    }
    
    public Long getMovieId() {
        return this.movieId;
    }
    
    public int getNumVideoTracks() {
        return this.mNumVideoTracks;
    }
    
    public String getPlaybackContextId() {
        return this.playbackContextId;
    }
    
    int getPriority() {
        return this.priority;
    }
    
    public long getRemainLife() {
        final long n = System.currentTimeMillis() - this.birthTimeMs;
        if (NfManifest.sManifestLifeFromConfig > 0L) {
            return NfManifest.sManifestLifeFromConfig - n;
        }
        return this.defaultLifeMs - n;
    }
    
    public Subtitle[] getSubtitleTrackList() {
        Log.d(NfManifest.TAG, "parsing timedtexttracks");
        final JSONArray optJSONArray = this.manifest.optJSONArray("timedtexttracks");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            try {
                final Subtitle[] array = new Subtitle[optJSONArray.length()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = NccpSubtitle.newInstance(optJSONArray.getJSONObject(i), i);
                }
                if (Log.isLoggable()) {
                    Log.d(NfManifest.TAG, "timedtexttracks has " + array.length);
                }
                return array;
            }
            catch (JSONException ex) {
                Log.w(NfManifest.TAG, "exception when parsing timedtexttracks");
            }
        }
        return new Subtitle[0];
    }
    
    public List<SubtitleTrackData> getSubtitleTracks(final long n) {
        Log.d(NfManifest.TAG, "parsing timedtexttracks");
        final JSONArray optJSONArray = this.manifest.optJSONArray("timedtexttracks");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            try {
                final ArrayList<SubtitleTrackData> list = new ArrayList<SubtitleTrackData>();
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    list.add(new SubtitleTrackData(optJSONArray.getJSONObject(i), i, n));
                }
                if (Log.isLoggable()) {
                    Log.d(NfManifest.TAG, "timedtexttracks has " + optJSONArray.length());
                }
                return list;
            }
            catch (JSONException ex) {
                Log.w(NfManifest.TAG, "exception when parsing timedtexttracks " + ex);
            }
        }
        return null;
    }
    
    public TrickplayUrl[] getTrickplayUrls() {
        Log.d(NfManifest.TAG, "parsing trickplays");
        final JSONArray optJSONArray = this.manifest.optJSONArray("trickplays");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            try {
                final TrickplayUrl[] array = new TrickplayUrl[optJSONArray.length()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = new TrickplayUrl(optJSONArray.getJSONObject(i));
                }
                if (Log.isLoggable()) {
                    Log.d(NfManifest.TAG, "trickplays has " + array.length);
                }
                return array;
            }
            catch (JSONException ex) {
                Log.w(NfManifest.TAG, " exception when parsing trickplays");
            }
        }
        return new TrickplayUrl[0];
    }
    
    public List<VideoTrack> getVideoTracks() {
        Log.d(NfManifest.TAG, "getVideoTracks");
        return this.parseVideoTracks();
    }
    
    public boolean hasDrm() {
        return this.hasDrmProfile;
    }
    
    public List<VideoTrack> parseVideoTracks() {
        Log.d(NfManifest.TAG, "parseVideoTracks");
        final ArrayList<VideoTrack> list = new ArrayList<VideoTrack>();
        while (true) {
            try {
                final JSONArray jsonArray = this.manifest.getJSONArray("video_tracks");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        final VideoTrack videoTrack = NfManifestParser.parseVideoTrack((JSONObject)jsonArray.opt(i));
                        if (videoTrack != null) {
                            list.add(videoTrack);
                        }
                    }
                }
            }
            catch (JSONException ex) {
                Log.d(NfManifest.TAG, "no video_tracks");
                final JSONArray jsonArray = null;
                continue;
            }
            break;
        }
        return list;
    }
    
    void setPriority(final int priority) {
        this.priority = priority;
    }
}
