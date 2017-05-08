// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonArray;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class Video$Detail implements JsonMerger, JsonPopulator
{
    private static final String TAG = "Detail";
    public String actors;
    public int autoPlayMaxCount;
    public String baseUrl;
    public String baseUrlBig;
    public String bifUrl;
    public String certification;
    public String copyright;
    public String directors;
    public int endtime;
    public List<String> episodeBadges;
    public int episodeCount;
    public long expirationTime;
    public String genres;
    public boolean hasTrailers;
    public boolean hasWatched;
    public String hiResHorzUrl;
    public String horzDispUrl;
    public String interestingUrl;
    public boolean is3DAvailable;
    public boolean is5dot1Available;
    public boolean isAgeProtected;
    public boolean isAutoPlayEnabled;
    public boolean isAvailableToStream;
    public boolean isDolbyVisionAvailable;
    public boolean isExemptFromInterrupterLimit;
    public boolean isHdAvailable;
    public boolean isHdr10Avaiable;
    public boolean isNSRE;
    public boolean isNextPlayableEpisode;
    public boolean isOriginal;
    public boolean isPinProtected;
    public boolean isPreRelease;
    public boolean isSupplementalVideo;
    public boolean isUhdAvailable;
    public int logicalStart;
    public int maturityLevel;
    public String mdxVertUrl;
    public float predictedRating;
    public String quality;
    public String restUrl;
    public int runtime;
    public int seasonCount;
    public String seasonNumLabel;
    public String storyImgUrl;
    public String supplementalMessage;
    public String synopsis;
    public String synopsisNarrative;
    public String titleCroppedUrl;
    public String titleUrl;
    public String tvCardUrl;
    public int year;
    
    public Video$Detail() {
        this.autoPlayMaxCount = -1;
        this.episodeBadges = new ArrayList<String>(3);
    }
    
    public String getInterestingUrl() {
        return this.interestingUrl;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Detail", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0514: {
                switch (s.hashCode()) {
                    case 3704893: {
                        if (s.equals("year")) {
                            n = 0;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1828656532: {
                        if (s.equals("synopsis")) {
                            n = 1;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1522889671: {
                        if (s.equals("copyright")) {
                            n = 2;
                            break Label_0514;
                        }
                        break;
                    }
                    case -496641730: {
                        if (s.equals("synopsisNarrative")) {
                            n = 3;
                            break Label_0514;
                        }
                        break;
                    }
                    case 651215103: {
                        if (s.equals("quality")) {
                            n = 4;
                            break Label_0514;
                        }
                        break;
                    }
                    case -962584985: {
                        if (s.equals("directors")) {
                            n = 5;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1422944994: {
                        if (s.equals("actors")) {
                            n = 6;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1249499312: {
                        if (s.equals("genres")) {
                            n = 7;
                            break Label_0514;
                        }
                        break;
                    }
                    case -644524870: {
                        if (s.equals("certification")) {
                            n = 8;
                            break Label_0514;
                        }
                        break;
                    }
                    case 38526579: {
                        if (s.equals("maturityLevel")) {
                            n = 9;
                            break Label_0514;
                        }
                        break;
                    }
                    case -668327396: {
                        if (s.equals("expirationTime")) {
                            n = 10;
                            break Label_0514;
                        }
                        break;
                    }
                    case 417758403: {
                        if (s.equals("supplementalMessage")) {
                            n = 11;
                            break Label_0514;
                        }
                        break;
                    }
                    case 585773339: {
                        if (s.equals("isOriginal")) {
                            n = 12;
                            break Label_0514;
                        }
                        break;
                    }
                    case 135683246: {
                        if (s.equals("isPreRelease")) {
                            n = 13;
                            break Label_0514;
                        }
                        break;
                    }
                    case -144454054: {
                        if (s.equals("hasTrailers")) {
                            n = 14;
                            break Label_0514;
                        }
                        break;
                    }
                    case 113933357: {
                        if (s.equals("isSupplementalVideo")) {
                            n = 15;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1217996834: {
                        if (s.equals("horzDispUrl")) {
                            n = 16;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1097494779: {
                        if (s.equals("restUrl")) {
                            n = 17;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1389216784: {
                        if (s.equals("bifUrl")) {
                            n = 18;
                            break Label_0514;
                        }
                        break;
                    }
                    case -332625698: {
                        if (s.equals("baseUrl")) {
                            n = 19;
                            break Label_0514;
                        }
                        break;
                    }
                    case -762550462: {
                        if (s.equals("baseUrlBig")) {
                            n = 20;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1794520227: {
                        if (s.equals("tvCardUrl")) {
                            n = 21;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1062174849: {
                        if (s.equals("hiResHorzUrl")) {
                            n = 22;
                            break Label_0514;
                        }
                        break;
                    }
                    case 398159229: {
                        if (s.equals("mdxVertUrl")) {
                            n = 23;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1551264767: {
                        if (s.equals("storyImgUrl")) {
                            n = 24;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1590765524: {
                        if (s.equals("episodeCount")) {
                            n = 25;
                            break Label_0514;
                        }
                        break;
                    }
                    case -885502996: {
                        if (s.equals("seasonCount")) {
                            n = 26;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1550962648: {
                        if (s.equals("runtime")) {
                            n = 27;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1606289880: {
                        if (s.equals("endtime")) {
                            n = 28;
                            break Label_0514;
                        }
                        break;
                    }
                    case -602057255: {
                        if (s.equals("logicalStart")) {
                            n = 29;
                            break Label_0514;
                        }
                        break;
                    }
                    case -515828317: {
                        if (s.equals("isHdAvailable")) {
                            n = 30;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1944322078: {
                        if (s.equals("isUhdAvailable")) {
                            n = 31;
                            break Label_0514;
                        }
                        break;
                    }
                    case 558909422: {
                        if (s.equals("is3DAvailable")) {
                            n = 32;
                            break Label_0514;
                        }
                        break;
                    }
                    case -389852269: {
                        if (s.equals("isDolbyVisionAvailable")) {
                            n = 33;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1302288478: {
                        if (s.equals("isHdr10Avaiable")) {
                            n = 34;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1410856650: {
                        if (s.equals("is5dot1Available")) {
                            n = 35;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1077707340: {
                        if (s.equals("isAutoPlayEnabled")) {
                            n = 36;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1709910622: {
                        if (s.equals("isExemptFromInterrupterLimit")) {
                            n = 37;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1135089838: {
                        if (s.equals("autoPlayMaxCount")) {
                            n = 38;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1426350736: {
                        if (s.equals("isNextPlayableEpisode")) {
                            n = 39;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1494791097: {
                        if (s.equals("isAgeProtected")) {
                            n = 40;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1931492381: {
                        if (s.equals("isPinProtected")) {
                            n = 41;
                            break Label_0514;
                        }
                        break;
                    }
                    case 719120809: {
                        if (s.equals("isAvailableForED")) {
                            n = 42;
                            break Label_0514;
                        }
                        break;
                    }
                    case -263240971: {
                        if (s.equals("predictedRating")) {
                            n = 43;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1870009353: {
                        if (s.equals("titleUrl")) {
                            n = 44;
                            break Label_0514;
                        }
                        break;
                    }
                    case 1440369896: {
                        if (s.equals("titleCroppedUrl")) {
                            n = 45;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1865391343: {
                        if (s.equals("seasonNumLabel")) {
                            n = 46;
                            break Label_0514;
                        }
                        break;
                    }
                    case 2027019019: {
                        if (s.equals("episodeBadges")) {
                            n = 47;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1136616012: {
                        if (s.equals("hasWatched")) {
                            n = 48;
                            break Label_0514;
                        }
                        break;
                    }
                    case -1180295454: {
                        if (s.equals("isNSRE")) {
                            n = 49;
                            break Label_0514;
                        }
                        break;
                    }
                    case -23645737: {
                        if (s.equals("interestingUrl")) {
                            n = 50;
                            break Label_0514;
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
                    this.year = jsonElement2.getAsInt();
                    continue;
                }
                case 1: {
                    this.synopsis = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.copyright = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.synopsisNarrative = jsonElement2.getAsString();
                    continue;
                }
                case 4: {
                    this.quality = jsonElement2.getAsString();
                    continue;
                }
                case 5: {
                    this.directors = jsonElement2.getAsString();
                    continue;
                }
                case 6: {
                    this.actors = jsonElement2.getAsString();
                    continue;
                }
                case 7: {
                    this.genres = jsonElement2.getAsString();
                    continue;
                }
                case 8: {
                    this.certification = jsonElement2.getAsString();
                    continue;
                }
                case 9: {
                    this.maturityLevel = jsonElement2.getAsInt();
                    continue;
                }
                case 10: {
                    if (jsonElement2 != null && !jsonElement2.isJsonNull()) {
                        this.expirationTime = jsonElement2.getAsLong();
                        continue;
                    }
                    continue;
                }
                case 11: {
                    this.supplementalMessage = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 12: {
                    this.isOriginal = jsonElement2.getAsBoolean();
                    continue;
                }
                case 13: {
                    this.isPreRelease = jsonElement2.getAsBoolean();
                    continue;
                }
                case 14: {
                    this.hasTrailers = jsonElement2.getAsBoolean();
                    continue;
                }
                case 15: {
                    this.isSupplementalVideo = jsonElement2.getAsBoolean();
                    continue;
                }
                case 16: {
                    if (jsonElement2 != null && !jsonElement2.isJsonNull()) {
                        this.horzDispUrl = JsonUtils.getAsStringSafe(jsonElement2);
                        continue;
                    }
                    continue;
                }
                case 17: {
                    this.restUrl = jsonElement2.getAsString();
                    continue;
                }
                case 18: {
                    this.bifUrl = jsonElement2.getAsString();
                    continue;
                }
                case 19: {
                    this.baseUrl = jsonElement2.getAsString();
                    continue;
                }
                case 20: {
                    this.baseUrlBig = jsonElement2.getAsString();
                    continue;
                }
                case 21: {
                    this.tvCardUrl = jsonElement2.getAsString();
                    continue;
                }
                case 22: {
                    this.hiResHorzUrl = jsonElement2.getAsString();
                    continue;
                }
                case 23: {
                    this.mdxVertUrl = jsonElement2.getAsString();
                    continue;
                }
                case 24: {
                    this.storyImgUrl = jsonElement2.getAsString();
                    continue;
                }
                case 25: {
                    this.episodeCount = jsonElement2.getAsInt();
                    continue;
                }
                case 26: {
                    this.seasonCount = jsonElement2.getAsInt();
                    continue;
                }
                case 27: {
                    this.runtime = jsonElement2.getAsInt();
                    continue;
                }
                case 28: {
                    this.endtime = jsonElement2.getAsInt();
                    continue;
                }
                case 29: {
                    this.logicalStart = jsonElement2.getAsInt();
                    continue;
                }
                case 30: {
                    this.isHdAvailable = jsonElement2.getAsBoolean();
                    continue;
                }
                case 31: {
                    this.isUhdAvailable = jsonElement2.getAsBoolean();
                    continue;
                }
                case 32: {
                    this.is3DAvailable = jsonElement2.getAsBoolean();
                    continue;
                }
                case 33: {
                    this.isDolbyVisionAvailable = jsonElement2.getAsBoolean();
                    continue;
                }
                case 34: {
                    this.isHdr10Avaiable = jsonElement2.getAsBoolean();
                    continue;
                }
                case 35: {
                    this.is5dot1Available = jsonElement2.getAsBoolean();
                    continue;
                }
                case 36: {
                    this.isAutoPlayEnabled = jsonElement2.getAsBoolean();
                    continue;
                }
                case 37: {
                    this.isExemptFromInterrupterLimit = jsonElement2.getAsBoolean();
                    continue;
                }
                case 38: {
                    this.autoPlayMaxCount = jsonElement2.getAsInt();
                    continue;
                }
                case 39: {
                    this.isNextPlayableEpisode = jsonElement2.getAsBoolean();
                    continue;
                }
                case 40: {
                    this.isAgeProtected = jsonElement2.getAsBoolean();
                    continue;
                }
                case 41: {
                    this.isPinProtected = jsonElement2.getAsBoolean();
                    continue;
                }
                case 42: {
                    this.isAvailableToStream = jsonElement2.getAsBoolean();
                    continue;
                }
                case 43: {
                    this.predictedRating = jsonElement2.getAsFloat();
                    continue;
                }
                case 44: {
                    this.titleUrl = jsonElement2.getAsString();
                    continue;
                }
                case 45: {
                    this.titleCroppedUrl = jsonElement2.getAsString();
                    continue;
                }
                case 46: {
                    this.seasonNumLabel = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 47: {
                    final JsonArray asJsonArray = jsonElement2.getAsJsonArray();
                    for (int i = 0; i < asJsonArray.size(); ++i) {
                        this.episodeBadges.add(asJsonArray.get(i).getAsString());
                    }
                    continue;
                }
                case 48: {
                    this.hasWatched = jsonElement2.getAsBoolean();
                    continue;
                }
                case 49: {
                    this.isNSRE = jsonElement2.getAsBoolean();
                    continue;
                }
                case 50: {
                    if (jsonElement2 != null && !jsonElement2.isJsonNull()) {
                        this.interestingUrl = jsonElement2.getAsString();
                        continue;
                    }
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Detail", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "year": {
                this.year = jsonParser.getValueAsInt();
                break;
            }
            case "synopsis": {
                this.synopsis = jsonParser.getValueAsString();
                break;
            }
            case "copyright": {
                this.copyright = jsonParser.getValueAsString();
                break;
            }
            case "synopsisNarrative": {
                this.synopsisNarrative = jsonParser.getValueAsString();
                break;
            }
            case "quality": {
                this.quality = jsonParser.getValueAsString();
                break;
            }
            case "directors": {
                this.directors = jsonParser.getValueAsString();
                break;
            }
            case "actors": {
                this.actors = jsonParser.getValueAsString();
                break;
            }
            case "genres": {
                this.genres = jsonParser.getValueAsString();
                break;
            }
            case "certification": {
                this.certification = jsonParser.getValueAsString();
                break;
            }
            case "maturityLevel": {
                this.maturityLevel = jsonParser.getValueAsInt();
                break;
            }
            case "expirationTime": {
                if (jsonParser != null) {
                    this.expirationTime = jsonParser.getValueAsLong();
                    break;
                }
                break;
            }
            case "supplementalMessage": {
                this.supplementalMessage = jsonParser.getValueAsString();
                break;
            }
            case "isOriginal": {
                this.isOriginal = jsonParser.getValueAsBoolean();
                break;
            }
            case "isPreRelease": {
                this.isPreRelease = jsonParser.getValueAsBoolean();
                break;
            }
            case "hasTrailers": {
                this.hasTrailers = jsonParser.getValueAsBoolean();
                break;
            }
            case "isSupplementalVideo": {
                this.isSupplementalVideo = jsonParser.getValueAsBoolean();
                break;
            }
            case "horzDispUrl": {
                if (jsonParser != null) {
                    this.horzDispUrl = jsonParser.getValueAsString();
                    break;
                }
                break;
            }
            case "restUrl": {
                this.restUrl = jsonParser.getValueAsString();
                break;
            }
            case "bifUrl": {
                this.bifUrl = jsonParser.getValueAsString();
                break;
            }
            case "interestingUrl": {
                this.interestingUrl = jsonParser.getValueAsString();
                break;
            }
            case "baseUrl": {
                this.baseUrl = jsonParser.getValueAsString();
                break;
            }
            case "baseUrlBig": {
                this.baseUrlBig = jsonParser.getValueAsString();
                break;
            }
            case "tvCardUrl": {
                this.tvCardUrl = jsonParser.getValueAsString();
                break;
            }
            case "hiResHorzUrl": {
                this.hiResHorzUrl = jsonParser.getValueAsString();
                break;
            }
            case "mdxVertUrl": {
                this.mdxVertUrl = jsonParser.getValueAsString();
                break;
            }
            case "storyImgUrl": {
                this.storyImgUrl = jsonParser.getValueAsString();
                break;
            }
            case "episodeCount": {
                this.episodeCount = jsonParser.getValueAsInt();
                break;
            }
            case "seasonCount": {
                this.seasonCount = jsonParser.getValueAsInt();
                break;
            }
            case "runtime": {
                this.runtime = jsonParser.getValueAsInt();
                break;
            }
            case "endtime": {
                this.endtime = jsonParser.getValueAsInt();
                break;
            }
            case "logicalStart": {
                this.logicalStart = jsonParser.getValueAsInt();
                break;
            }
            case "isHdAvailable": {
                this.isHdAvailable = jsonParser.getValueAsBoolean();
                break;
            }
            case "isUhdAvailable": {
                this.isUhdAvailable = jsonParser.getValueAsBoolean();
                break;
            }
            case "is3DAvailable": {
                this.is3DAvailable = jsonParser.getValueAsBoolean();
                break;
            }
            case "isDolbyVisionAvailable": {
                this.isDolbyVisionAvailable = jsonParser.getValueAsBoolean();
                break;
            }
            case "isHdr10Avaiable": {
                this.isHdr10Avaiable = jsonParser.getValueAsBoolean();
                break;
            }
            case "is5dot1Available": {
                this.is5dot1Available = jsonParser.getValueAsBoolean();
                break;
            }
            case "isAutoPlayEnabled": {
                this.isAutoPlayEnabled = jsonParser.getValueAsBoolean();
                break;
            }
            case "isExemptFromInterrupterLimit": {
                this.isExemptFromInterrupterLimit = jsonParser.getValueAsBoolean();
                break;
            }
            case "autoPlayMaxCount": {
                this.autoPlayMaxCount = jsonParser.getValueAsInt();
                break;
            }
            case "isNextPlayableEpisode": {
                this.isNextPlayableEpisode = jsonParser.getValueAsBoolean();
                break;
            }
            case "isAgeProtected": {
                this.isAgeProtected = jsonParser.getValueAsBoolean();
                break;
            }
            case "isPinProtected": {
                this.isPinProtected = jsonParser.getValueAsBoolean();
                break;
            }
            case "isAvailableToStream": {
                this.isAvailableToStream = jsonParser.getValueAsBoolean();
                break;
            }
            case "predictedRating": {
                this.predictedRating = (float)jsonParser.getValueAsDouble();
                break;
            }
            case "titleUrl": {
                this.titleUrl = jsonParser.getValueAsString();
                break;
            }
            case "titleCroppedUrl": {
                this.titleCroppedUrl = jsonParser.getValueAsString();
                break;
            }
            case "seasonNumLabel": {
                this.seasonNumLabel = jsonParser.getValueAsString();
                break;
            }
            case "episodeBadges": {
                this.episodeBadges = (List<String>)BranchNodeUtils.readValue(jsonParser, JsonToken.START_ARRAY, true);
                break;
            }
            case "hasWatched": {
                this.hasWatched = jsonParser.getValueAsBoolean();
                break;
            }
            case "isNSRE": {
                this.isNSRE = jsonParser.getValueAsBoolean();
                break;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Detail{year=" + this.year + ", synopsis='" + this.synopsis + '\'' + ", synopsisNarrative='" + this.synopsisNarrative + '\'' + ", quality='" + this.quality + '\'' + ", directors='" + this.directors + '\'' + ", actors='" + this.actors + '\'' + ", genres='" + this.genres + '\'' + ", certification='" + this.certification + '\'' + ", copyright='" + this.copyright + '\'' + ", horzDispUrl='" + this.horzDispUrl + '\'' + ", restUrl='" + this.restUrl + '\'' + ", bifUrl='" + this.bifUrl + '\'' + ", baseUrl='" + this.baseUrl + '\'' + ", baseUrlBig='" + this.baseUrlBig + '\'' + ", tvCardUrl='" + this.tvCardUrl + '\'' + ", hiResHorzUrl='" + this.hiResHorzUrl + '\'' + ", mdxVertUrl='" + this.mdxVertUrl + '\'' + ", storyImgUrl='" + this.storyImgUrl + '\'' + ", titleUrl='" + this.titleUrl + '\'' + ", titleCroppedUrl='" + this.titleCroppedUrl + '\'' + ", seasonNumLabel='" + this.seasonNumLabel + '\'' + ", episodeCount=" + this.episodeCount + ", seasonCount=" + this.seasonCount + ", predictedRating=" + this.predictedRating + ", maturityLevel=" + this.maturityLevel + ", expirationTime=" + this.expirationTime + ", supplementalMessage='" + this.supplementalMessage + '\'' + ", isOriginal=" + this.isOriginal + ", isPreRelease=" + this.isPreRelease + ", hasTrailers=" + this.hasTrailers + ", isSupplementalVideo=" + this.isSupplementalVideo + ", isNSRE=" + this.isNSRE + ", isHdAvailable=" + this.isHdAvailable + ", isUhdAvailable=" + this.isUhdAvailable + ", is3DAvailable=" + this.is3DAvailable + ", isDolbyVisionAvailable=" + this.isDolbyVisionAvailable + ", isHdr10Avaiable=" + this.isHdr10Avaiable + ", is5dot1Available=" + this.is5dot1Available + ", isAutoPlayEnabled=" + this.isAutoPlayEnabled + ", isExemptFromInterrupterLimit=" + this.isExemptFromInterrupterLimit + ", isNextPlayableEpisode=" + this.isNextPlayableEpisode + ", isAgeProtected=" + this.isAgeProtected + ", isPinProtected=" + this.isPinProtected + ", hasWatched=" + this.hasWatched + ", autoPlayMaxCount=" + this.autoPlayMaxCount + ", episodeBadges=" + this.episodeBadges + ", isAvailableToStream=" + this.isAvailableToStream + ", runtime=" + this.runtime + ", endtime=" + this.endtime + ", logicalStart=" + this.logicalStart + '}';
    }
}
