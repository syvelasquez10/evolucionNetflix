// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonNull;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.List;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.falkor.PQL;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import java.util.HashMap;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class PostPlayAction implements JsonMerger, JsonPopulator, Trackable
{
    private static final String TAG = "PostPlayAction";
    private final Map<String, Integer> additionalTrackIds;
    private String ancestorTitle;
    private int bookmarkPosition;
    private String displayText;
    private boolean doNotIncrementInterrupter;
    private int episode;
    private boolean forceInterrupt;
    private boolean inMyList;
    private String interruptDisplayText;
    private int interruptThreshold;
    private int itemIndex;
    private long logicalStart;
    private long minutesRemaining;
    private String name;
    private ModelProxy<? extends BranchNode> proxy;
    private String requestId;
    private int seamlessStart;
    private int season;
    private String seasonSequenceAbbr;
    private String seasonSequenceLong;
    private int trackId;
    private PostPlayAction$CallToActionType type;
    private int videoId;
    private VideoType videoType;
    
    public PostPlayAction() {
        this(null, null);
    }
    
    public PostPlayAction(final JsonElement jsonElement) {
        this(jsonElement, null);
    }
    
    public PostPlayAction(final JsonElement jsonElement, final ModelProxy<? extends BranchNode> proxy) {
        this.additionalTrackIds = new HashMap<String, Integer>();
        this.inMyList = false;
        this.doNotIncrementInterrupter = false;
        this.seamlessStart = -1;
        this.proxy = proxy;
        if (jsonElement != null) {
            this.populate(jsonElement);
        }
    }
    
    public Map<String, Integer> getAdditionalTrackIds() {
        return this.additionalTrackIds;
    }
    
    public String getAncestorTitle() {
        return this.ancestorTitle;
    }
    
    public int getBookmarkPosition() {
        return this.bookmarkPosition;
    }
    
    public String getDisplayText() {
        return this.displayText;
    }
    
    public int getEpisode() {
        return this.episode;
    }
    
    @Override
    public int getHeroTrackId() {
        return 0;
    }
    
    public String getInterruptDisplayText() {
        return this.interruptDisplayText;
    }
    
    public int getInterruptThreshold() {
        return this.interruptThreshold;
    }
    
    public int getItemIndex() {
        return this.itemIndex;
    }
    
    @Override
    public int getListPos() {
        return this.itemIndex;
    }
    
    public long getLogicalStart() {
        return this.logicalStart;
    }
    
    public long getMinutesRemaining() {
        return this.minutesRemaining;
    }
    
    public String getName() {
        return this.name;
    }
    
    public PostPlayVideo getPlayBackVideo() {
        final List<FalkorObject> itemsAsList = this.proxy.getItemsAsList(PQL.create("videos", this.videoId, "summary"));
        if (itemsAsList.size() <= 0) {
            ErrorLoggingManager.logHandledException("SPY-10641 - Error getting playback video ID: " + this.videoId);
            return null;
        }
        final FalkorObject falkorObject = itemsAsList.get(0);
        if (falkorObject instanceof PostPlayVideo) {
            return (PostPlayVideo)falkorObject;
        }
        ErrorLoggingManager.logHandledException("SPY-10641 - Error casting playback video to the right type ID: " + this.videoId);
        return null;
    }
    
    @Override
    public String getRequestId() {
        return this.requestId;
    }
    
    public int getSeamlessStart() {
        return this.seamlessStart;
    }
    
    public int getSeason() {
        return this.season;
    }
    
    public String getSeasonSequenceAbbr() {
        return this.seasonSequenceAbbr;
    }
    
    public String getSeasonSequenceLong() {
        return this.seasonSequenceLong;
    }
    
    @Override
    public int getTrackId() {
        return this.trackId;
    }
    
    public PostPlayAction$CallToActionType getType() {
        return this.type;
    }
    
    public int getVideoId() {
        return this.videoId;
    }
    
    public VideoType getVideoType() {
        return this.videoType;
    }
    
    public boolean isDoNotIncrementInterrupter() {
        return this.doNotIncrementInterrupter;
    }
    
    public boolean isForceInterrupt() {
        return this.forceInterrupt;
    }
    
    @Override
    public boolean isHero() {
        return false;
    }
    
    public boolean isInMyList() {
        return this.inMyList;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("PostPlayAction", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            if (!(jsonElement2 instanceof JsonNull)) {
                final String s = entry.getKey();
                int n = 0;
                Label_0274: {
                    switch (s.hashCode()) {
                        case 1714331919: {
                            if (s.equals("displayText")) {
                                n = 0;
                                break Label_0274;
                            }
                            break;
                        }
                        case 3373707: {
                            if (s.equals("name")) {
                                n = 1;
                                break Label_0274;
                            }
                            break;
                        }
                        case 3575610: {
                            if (s.equals("type")) {
                                n = 2;
                                break Label_0274;
                            }
                            break;
                        }
                        case -1067396154: {
                            if (s.equals("trackId")) {
                                n = 3;
                                break Label_0274;
                            }
                            break;
                        }
                        case -906335517: {
                            if (s.equals("season")) {
                                n = 4;
                                break Label_0274;
                            }
                            break;
                        }
                        case -1681835499: {
                            if (s.equals("seasonSequenceAbbr")) {
                                n = 5;
                                break Label_0274;
                            }
                            break;
                        }
                        case -1681494944: {
                            if (s.equals("seasonSequenceLong")) {
                                n = 6;
                                break Label_0274;
                            }
                            break;
                        }
                        case -1544438277: {
                            if (s.equals("episode")) {
                                n = 7;
                                break Label_0274;
                            }
                            break;
                        }
                        case -1829827457: {
                            if (s.equals("bookmarkPosition")) {
                                n = 8;
                                break Label_0274;
                            }
                            break;
                        }
                        case -1739932713: {
                            if (s.equals("minutesRemaining")) {
                                n = 9;
                                break Label_0274;
                            }
                            break;
                        }
                        case -602057255: {
                            if (s.equals("logicalStart")) {
                                n = 10;
                                break Label_0274;
                            }
                            break;
                        }
                        case 1332961877: {
                            if (s.equals("videoType")) {
                                n = 11;
                                break Label_0274;
                            }
                            break;
                        }
                        case 1629608888: {
                            if (s.equals("forceInterrupt")) {
                                n = 12;
                                break Label_0274;
                            }
                            break;
                        }
                        case 846419272: {
                            if (s.equals("interruptThreshold")) {
                                n = 13;
                                break Label_0274;
                            }
                            break;
                        }
                        case -1775808852: {
                            if (s.equals("interruptDisplayText")) {
                                n = 14;
                                break Label_0274;
                            }
                            break;
                        }
                        case -563483980: {
                            if (s.equals("additionalTrackIds")) {
                                n = 15;
                                break Label_0274;
                            }
                            break;
                        }
                        case -528792081: {
                            if (s.equals("inMyList")) {
                                n = 16;
                                break Label_0274;
                            }
                            break;
                        }
                        case 771110345: {
                            if (s.equals("doNotIncrementInterrupter")) {
                                n = 17;
                                break Label_0274;
                            }
                            break;
                        }
                        case 452782838: {
                            if (s.equals("videoId")) {
                                n = 18;
                                break Label_0274;
                            }
                            break;
                        }
                        case -1324983797: {
                            if (s.equals("seamlessStart")) {
                                n = 19;
                                break Label_0274;
                            }
                            break;
                        }
                    }
                    n = -1;
                }
                switch (n) {
                    default: {
                        continue;
                    }
                    case 0: {
                        this.displayText = jsonElement2.getAsString();
                        continue;
                    }
                    case 1: {
                        this.name = jsonElement2.getAsString();
                        continue;
                    }
                    case 2: {
                        this.type = PostPlayAction$CallToActionType.valueOf(jsonElement2.getAsString());
                        continue;
                    }
                    case 3: {
                        this.trackId = jsonElement2.getAsInt();
                        continue;
                    }
                    case 4: {
                        this.season = jsonElement2.getAsInt();
                        continue;
                    }
                    case 5: {
                        this.seasonSequenceAbbr = jsonElement2.getAsString();
                        continue;
                    }
                    case 6: {
                        this.seasonSequenceLong = jsonElement2.getAsString();
                        continue;
                    }
                    case 7: {
                        this.episode = jsonElement2.getAsInt();
                        continue;
                    }
                    case 8: {
                        this.bookmarkPosition = jsonElement2.getAsInt();
                        continue;
                    }
                    case 9: {
                        this.minutesRemaining = jsonElement2.getAsInt();
                        continue;
                    }
                    case 10: {
                        this.logicalStart = jsonElement2.getAsInt();
                        continue;
                    }
                    case 11: {
                        this.videoType = VideoType.create(jsonElement2.getAsString());
                        continue;
                    }
                    case 12: {
                        this.forceInterrupt = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 13: {
                        this.interruptThreshold = jsonElement2.getAsInt();
                        continue;
                    }
                    case 14: {
                        this.interruptDisplayText = jsonElement2.getAsString();
                        continue;
                    }
                    case 15: {
                        if (!jsonElement2.isJsonNull()) {
                            for (final Map.Entry<String, JsonElement> entry2 : jsonElement2.getAsJsonObject().entrySet()) {
                                this.additionalTrackIds.put(entry2.getKey(), entry2.getValue().getAsInt());
                            }
                            continue;
                        }
                        continue;
                    }
                    case 16: {
                        this.inMyList = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 17: {
                        this.doNotIncrementInterrupter = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 18: {
                        this.videoId = jsonElement2.getAsInt();
                        continue;
                    }
                    case 19: {
                        this.seamlessStart = jsonElement2.getAsInt();
                        continue;
                    }
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        return false;
    }
    
    public void setAncestorTitle(final String ancestorTitle) {
        this.ancestorTitle = ancestorTitle;
    }
    
    public void setBookmarkPosition(final int bookmarkPosition) {
        this.bookmarkPosition = bookmarkPosition;
    }
    
    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }
    
    public void setDoNotIncrementInterrupter(final boolean doNotIncrementInterrupter) {
        this.doNotIncrementInterrupter = doNotIncrementInterrupter;
    }
    
    public void setEpisode(final int episode) {
        this.episode = episode;
    }
    
    public void setForceInterrupt(final boolean forceInterrupt) {
        this.forceInterrupt = forceInterrupt;
    }
    
    public void setInMyList(final boolean inMyList) {
        this.inMyList = inMyList;
    }
    
    public void setInterruptDisplayText(final String interruptDisplayText) {
        this.interruptDisplayText = interruptDisplayText;
    }
    
    public void setInterruptThreshold(final int interruptThreshold) {
        this.interruptThreshold = interruptThreshold;
    }
    
    public void setItemIndex(final int itemIndex) {
        this.itemIndex = itemIndex;
    }
    
    public void setLogicalStart(final long logicalStart) {
        this.logicalStart = logicalStart;
    }
    
    public void setMinutesRemaining(final long minutesRemaining) {
        this.minutesRemaining = minutesRemaining;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }
    
    public void setSeamlessStart(final int seamlessStart) {
        this.seamlessStart = seamlessStart;
    }
    
    public void setSeason(final int season) {
        this.season = season;
    }
    
    public void setSeasonSequenceAbbr(final String seasonSequenceAbbr) {
        this.seasonSequenceAbbr = seasonSequenceAbbr;
    }
    
    public void setSeasonSequenceLong(final String seasonSequenceLong) {
        this.seasonSequenceLong = seasonSequenceLong;
    }
    
    public void setTrackId(final int trackId) {
        this.trackId = trackId;
    }
    
    public void setType(final PostPlayAction$CallToActionType type) {
        this.type = type;
    }
    
    public void setVideoId(final int videoId) {
        this.videoId = videoId;
    }
    
    public void setVideoType(final VideoType videoType) {
        this.videoType = videoType;
    }
}
