// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonNull;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.Iterator;
import com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.JsonElement;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class PostPlayItem implements JsonMerger, JsonPopulator, Ratable
{
    private static final String TAG = "PostPlayItem";
    private final List<PostPlayAction> actions;
    private String ancestorSynopsis;
    private String ancestorTitle;
    private boolean autoPlay;
    private int autoPlaySeconds;
    private String availabilityDateMessaging;
    private PostPlayAsset backgroundAsset;
    private List<String> badgeKeys;
    private Map<String, String> delivery;
    private PostPlayAsset displayArtAsset;
    private int episodes;
    private String experienceType;
    private String impressionData;
    private boolean isNSRE;
    private PostPlayAsset logoAsset;
    private String maturityRating;
    private boolean nextEpisodeAutoPlay;
    ModelProxy<? extends BranchNode> proxy;
    private Map<String, Float> rating;
    private int runtime;
    private String seasonSequenceAbbr;
    private int seasons;
    private String seasonsLabel;
    private String supplementalMessage;
    private String synopsis;
    private String title;
    private String type;
    private Integer videoId;
    private Integer year;
    
    public PostPlayItem() {
        this(null, null);
    }
    
    public PostPlayItem(final JsonElement jsonElement) {
        this(jsonElement, null);
    }
    
    public PostPlayItem(final JsonElement jsonElement, final ModelProxy<? extends BranchNode> proxy) {
        this.rating = new HashMap<String, Float>();
        this.delivery = new HashMap<String, String>();
        this.actions = new ArrayList<PostPlayAction>(5);
        this.isNSRE = false;
        this.badgeKeys = new ArrayList<String>(2);
        this.proxy = proxy;
        if (jsonElement != null) {
            this.populate(jsonElement);
        }
    }
    
    private void populateCTA(final JsonElement jsonElement, final List<PostPlayAction> list) {
        if (!jsonElement.isJsonNull()) {
            final JsonArray asJsonArray = jsonElement.getAsJsonArray();
            for (int i = 0; i < asJsonArray.size(); ++i) {
                list.add(new PostPlayAction(asJsonArray.get(i), this.proxy));
            }
        }
    }
    
    private void populateFloatMap(final JsonElement jsonElement, final Map<String, Float> map) {
        if (!jsonElement.isJsonNull()) {
            for (final Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                map.put(entry.getKey(), entry.getValue().getAsFloat());
            }
        }
    }
    
    private void populateMap(final JsonElement jsonElement, final Map<String, String> map) {
        if (!jsonElement.isJsonNull()) {
            for (final Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                map.put(entry.getKey(), entry.getValue().getAsString());
            }
        }
    }
    
    public List<PostPlayAction> getActions() {
        return this.actions;
    }
    
    public String getAncestorSynopsis() {
        return this.ancestorSynopsis;
    }
    
    public String getAncestorTitle() {
        return this.ancestorTitle;
    }
    
    public int getAutoPlaySeconds() {
        return this.autoPlaySeconds;
    }
    
    public String getAvailabilityDateMessaging() {
        return this.availabilityDateMessaging;
    }
    
    public PostPlayAsset getBackgroundAsset() {
        return this.backgroundAsset;
    }
    
    public List<String> getBadgeKeys() {
        return this.badgeKeys;
    }
    
    public Map<String, String> getDelivery() {
        return this.delivery;
    }
    
    public PostPlayAsset getDisplayArtAsset() {
        return this.displayArtAsset;
    }
    
    public int getEpisodes() {
        return this.episodes;
    }
    
    public String getExperienceType() {
        return this.experienceType;
    }
    
    public String getImpressionData() {
        return this.impressionData;
    }
    
    public PostPlayAsset getLogoAsset() {
        return this.logoAsset;
    }
    
    public String getMaturityRating() {
        return this.maturityRating;
    }
    
    public PostPlayAction getMoreInfoAction() {
        for (int i = 0; i < this.actions.size(); ++i) {
            if (this.actions.get(i).getType().equals(PostPlayAction$CallToActionType.mdp)) {
                return this.actions.get(i);
            }
        }
        return null;
    }
    
    public PostPlayAction getPlayAction() {
        for (int i = 0; i < this.actions.size(); ++i) {
            if (this.actions.get(i).getType().equals(PostPlayAction$CallToActionType.play)) {
                return this.actions.get(i);
            }
        }
        return null;
    }
    
    @Override
    public float getPredictedRating() {
        if (this.rating.get("predicted") != null) {
            return this.rating.get("userRating");
        }
        return 0.0f;
    }
    
    public Map<String, Float> getRating() {
        return this.rating;
    }
    
    public int getRuntime() {
        return this.runtime;
    }
    
    public String getSeasonSequenceAbbr() {
        return this.seasonSequenceAbbr;
    }
    
    public int getSeasons() {
        return this.seasons;
    }
    
    public String getSeasonsLabel() {
        return this.seasonsLabel;
    }
    
    public String getSupplementalMessage() {
        return this.supplementalMessage;
    }
    
    public String getSynopsis() {
        return this.synopsis;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getType() {
        return this.type;
    }
    
    @Override
    public float getUserRating() {
        if (this.rating.get("userRating") != null) {
            return this.rating.get("userRating");
        }
        return 0.0f;
    }
    
    public Integer getVideoId() {
        return this.videoId;
    }
    
    public Integer getYear() {
        return this.year;
    }
    
    public boolean hasNewBadge() {
        return this.badgeKeys.contains("NEW");
    }
    
    public boolean isAutoPlay() {
        return this.autoPlay;
    }
    
    public boolean isNSRE() {
        return this.isNSRE;
    }
    
    public boolean isNextEpisodeAutoPlay() {
        return this.nextEpisodeAutoPlay;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("PostPlayItem", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            if (!(jsonElement2 instanceof JsonNull)) {
                final String s = entry.getKey();
                int n = 0;
                Label_0290: {
                    switch (s.hashCode()) {
                        case 452782838: {
                            if (s.equals("videoId")) {
                                n = 0;
                                break Label_0290;
                            }
                            break;
                        }
                        case 3575610: {
                            if (s.equals("type")) {
                                n = 1;
                                break Label_0290;
                            }
                            break;
                        }
                        case 110371416: {
                            if (s.equals("title")) {
                                n = 2;
                                break Label_0290;
                            }
                            break;
                        }
                        case 123262021: {
                            if (s.equals("ancestorTitle")) {
                                n = 3;
                                break Label_0290;
                            }
                            break;
                        }
                        case 417758403: {
                            if (s.equals("supplementalMessage")) {
                                n = 4;
                                break Label_0290;
                            }
                            break;
                        }
                        case 1828656532: {
                            if (s.equals("synopsis")) {
                                n = 5;
                                break Label_0290;
                            }
                            break;
                        }
                        case -694386553: {
                            if (s.equals("ancestorSynopsis")) {
                                n = 6;
                                break Label_0290;
                            }
                            break;
                        }
                        case 3704893: {
                            if (s.equals("year")) {
                                n = 7;
                                break Label_0290;
                            }
                            break;
                        }
                        case 973762056: {
                            if (s.equals("raiting")) {
                                n = 8;
                                break Label_0290;
                            }
                            break;
                        }
                        case 1362349198: {
                            if (s.equals("maturityRating")) {
                                n = 9;
                                break Label_0290;
                            }
                            break;
                        }
                        case -1681835499: {
                            if (s.equals("seasonSequenceAbbr")) {
                                n = 10;
                                break Label_0290;
                            }
                            break;
                        }
                        case -339520796: {
                            if (s.equals("seasonsLabel")) {
                                n = 11;
                                break Label_0290;
                            }
                            break;
                        }
                        case 1968370160: {
                            if (s.equals("seasons")) {
                                n = 12;
                                break Label_0290;
                            }
                            break;
                        }
                        case -632946216: {
                            if (s.equals("episodes")) {
                                n = 13;
                                break Label_0290;
                            }
                            break;
                        }
                        case 1550962648: {
                            if (s.equals("runtime")) {
                                n = 14;
                                break Label_0290;
                            }
                            break;
                        }
                        case 823466996: {
                            if (s.equals("delivery")) {
                                n = 15;
                                break Label_0290;
                            }
                            break;
                        }
                        case -1161803523: {
                            if (s.equals("actions")) {
                                n = 16;
                                break Label_0290;
                            }
                            break;
                        }
                        case -1180295454: {
                            if (s.equals("isNSRE")) {
                                n = 17;
                                break Label_0290;
                            }
                            break;
                        }
                        case 1034293595: {
                            if (s.equals("availabilityDateMessaging")) {
                                n = 18;
                                break Label_0290;
                            }
                            break;
                        }
                        case 2112468023: {
                            if (s.equals("badgeKeys")) {
                                n = 19;
                                break Label_0290;
                            }
                            break;
                        }
                        case -1408207997: {
                            if (s.equals("assets")) {
                                n = 20;
                                break Label_0290;
                            }
                            break;
                        }
                        case -113850029: {
                            if (s.equals("impressionData")) {
                                n = 21;
                                break Label_0290;
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
                        this.videoId = jsonElement2.getAsInt();
                        continue;
                    }
                    case 1: {
                        this.type = jsonElement2.getAsString();
                        continue;
                    }
                    case 2: {
                        this.title = jsonElement2.getAsString();
                        continue;
                    }
                    case 3: {
                        this.ancestorTitle = jsonElement2.getAsString();
                        continue;
                    }
                    case 4: {
                        this.supplementalMessage = jsonElement2.getAsString();
                        continue;
                    }
                    case 5: {
                        this.synopsis = jsonElement2.getAsString();
                        continue;
                    }
                    case 6: {
                        this.ancestorSynopsis = jsonElement2.getAsString();
                        continue;
                    }
                    case 7: {
                        this.year = jsonElement2.getAsInt();
                        continue;
                    }
                    case 8: {
                        this.populateFloatMap(jsonElement2, this.rating);
                        continue;
                    }
                    case 9: {
                        this.maturityRating = jsonElement2.getAsString();
                        continue;
                    }
                    case 10: {
                        this.seasonSequenceAbbr = jsonElement2.getAsString();
                        continue;
                    }
                    case 11: {
                        this.seasonsLabel = jsonElement2.getAsString();
                        continue;
                    }
                    case 12: {
                        this.seasons = jsonElement2.getAsInt();
                        continue;
                    }
                    case 13: {
                        this.episodes = jsonElement2.getAsInt();
                        continue;
                    }
                    case 14: {
                        this.runtime = jsonElement2.getAsInt();
                        continue;
                    }
                    case 15: {
                        this.populateMap(jsonElement2, this.delivery);
                        continue;
                    }
                    case 16: {
                        this.populateCTA(jsonElement2, this.actions);
                        continue;
                    }
                    case 17: {
                        this.isNSRE = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 18: {
                        this.availabilityDateMessaging = jsonElement2.getAsString();
                        continue;
                    }
                    case 19: {
                        if (!jsonElement2.isJsonNull()) {
                            final JsonArray asJsonArray = jsonElement2.getAsJsonArray();
                            for (int i = 0; i < asJsonArray.size(); ++i) {
                                this.badgeKeys.add(asJsonArray.get(i).getAsString());
                            }
                            continue;
                        }
                        continue;
                    }
                    case 20: {
                        for (final Map.Entry<String, JsonElement> entry2 : jsonElement2.getAsJsonObject().entrySet()) {
                            final String s2 = entry2.getKey();
                            int n2 = 0;
                            Label_1114: {
                                switch (s2.hashCode()) {
                                    case -847101650: {
                                        if (s2.equals("BACKGROUND")) {
                                            n2 = 0;
                                            break Label_1114;
                                        }
                                        break;
                                    }
                                    case -426397402: {
                                        if (s2.equals("DISPLAY_ART")) {
                                            n2 = 1;
                                            break Label_1114;
                                        }
                                        break;
                                    }
                                    case 2342315: {
                                        if (s2.equals("LOGO")) {
                                            n2 = 2;
                                            break Label_1114;
                                        }
                                        break;
                                    }
                                }
                                n2 = -1;
                            }
                            switch (n2) {
                                default: {
                                    continue;
                                }
                                case 0: {
                                    this.backgroundAsset = new PostPlayAsset(PostPlayAsset$Type.BACKGROUND, entry2.getValue());
                                    continue;
                                }
                                case 1: {
                                    this.displayArtAsset = new PostPlayAsset(PostPlayAsset$Type.DISPLAY_ART, entry2.getValue());
                                    continue;
                                }
                                case 2: {
                                    this.logoAsset = new PostPlayAsset(PostPlayAsset$Type.LOGO, entry2.getValue());
                                    continue;
                                }
                            }
                        }
                        continue;
                    }
                    case 21: {
                        this.impressionData = jsonElement2.getAsString();
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
    
    public void setAncestorSynopsis(final String ancestorSynopsis) {
        this.ancestorSynopsis = ancestorSynopsis;
    }
    
    public void setAncestorTitle(final String ancestorTitle) {
        this.ancestorTitle = ancestorTitle;
    }
    
    public void setAutoPlay(final boolean autoPlay) {
        this.autoPlay = autoPlay;
    }
    
    public void setAutoPlaySeconds(final int autoPlaySeconds) {
        this.autoPlaySeconds = autoPlaySeconds;
    }
    
    public void setAvailabilityDateMessaging(final String availabilityDateMessaging) {
        this.availabilityDateMessaging = availabilityDateMessaging;
    }
    
    public void setBackgroundAsset(final PostPlayAsset backgroundAsset) {
        this.backgroundAsset = backgroundAsset;
    }
    
    public void setDelivery(final Map<String, String> delivery) {
        this.delivery = delivery;
    }
    
    public void setDisplayArtAsset(final PostPlayAsset displayArtAsset) {
        this.displayArtAsset = displayArtAsset;
    }
    
    public void setEpisodes(final int episodes) {
        this.episodes = episodes;
    }
    
    public void setExperienceType(final String experienceType) {
        this.experienceType = experienceType;
    }
    
    public void setImpressionData(final String impressionData) {
        this.impressionData = impressionData;
    }
    
    public void setLogoAsset(final PostPlayAsset logoAsset) {
        this.logoAsset = logoAsset;
    }
    
    public void setMaturityRating(final String maturityRating) {
        this.maturityRating = maturityRating;
    }
    
    public void setNSRE(final boolean isNSRE) {
        this.isNSRE = isNSRE;
    }
    
    public void setNextEpisodeAutoPlay(final boolean nextEpisodeAutoPlay) {
        this.nextEpisodeAutoPlay = nextEpisodeAutoPlay;
    }
    
    public void setRating(final Map<String, Float> rating) {
        this.rating = rating;
    }
    
    public void setRuntime(final int runtime) {
        this.runtime = runtime;
    }
    
    public void setSeasonSequenceAbbr(final String seasonSequenceAbbr) {
        this.seasonSequenceAbbr = seasonSequenceAbbr;
    }
    
    public void setSeasons(final int seasons) {
        this.seasons = seasons;
    }
    
    public void setSeasonsLabel(final String seasonsLabel) {
        this.seasonsLabel = seasonsLabel;
    }
    
    public void setSupplementalMessage(final String supplementalMessage) {
        this.supplementalMessage = supplementalMessage;
    }
    
    public void setSynopsis(final String synopsis) {
        this.synopsis = synopsis;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    @Override
    public void setUserRating(final float n) {
        this.rating.put("userRating", n);
    }
    
    public void setVideoId(final Integer videoId) {
        this.videoId = videoId;
    }
    
    public void setYear(final Integer year) {
        this.year = year;
    }
}
