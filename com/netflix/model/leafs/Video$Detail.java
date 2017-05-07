// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class Video$Detail implements JsonPopulator
{
    private static final String TAG = "Detail";
    public String actors;
    public String baseUrl;
    public String bifUrl;
    public String certification;
    public String directors;
    public int endtime;
    public int episodeCount;
    public String genres;
    public String hiResHorzUrl;
    public String horzDispUrl;
    public String intrUrl;
    public boolean is3DAvailable;
    public boolean is5dot1Available;
    public boolean isAutoPlayEnabled;
    public boolean isAvailableToStream;
    public boolean isHdAvailable;
    public boolean isNextPlayableEpisode;
    public boolean isPinProtected;
    public boolean isUhdAvailable;
    public int logicalStart;
    public String mdxVertUrl;
    public float predictedRating;
    public String quality;
    public String restUrl;
    public int runtime;
    public int seasonCount;
    public String storyImgUrl;
    public String synopsis;
    public String synopsisNarrative;
    public String titleUrl;
    public String tvCardUrl;
    public int year;
    
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
            Label_0362: {
                switch (s.hashCode()) {
                    case 3704893: {
                        if (s.equals("year")) {
                            n = 0;
                            break Label_0362;
                        }
                        break;
                    }
                    case 1828656532: {
                        if (s.equals("synopsis")) {
                            n = 1;
                            break Label_0362;
                        }
                        break;
                    }
                    case -496641730: {
                        if (s.equals("synopsisNarrative")) {
                            n = 2;
                            break Label_0362;
                        }
                        break;
                    }
                    case 651215103: {
                        if (s.equals("quality")) {
                            n = 3;
                            break Label_0362;
                        }
                        break;
                    }
                    case -962584985: {
                        if (s.equals("directors")) {
                            n = 4;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1422944994: {
                        if (s.equals("actors")) {
                            n = 5;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1249499312: {
                        if (s.equals("genres")) {
                            n = 6;
                            break Label_0362;
                        }
                        break;
                    }
                    case -644524870: {
                        if (s.equals("certification")) {
                            n = 7;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1217996834: {
                        if (s.equals("horzDispUrl")) {
                            n = 8;
                            break Label_0362;
                        }
                        break;
                    }
                    case 1097494779: {
                        if (s.equals("restUrl")) {
                            n = 9;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1389216784: {
                        if (s.equals("bifUrl")) {
                            n = 10;
                            break Label_0362;
                        }
                        break;
                    }
                    case -332625698: {
                        if (s.equals("baseUrl")) {
                            n = 11;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1794520227: {
                        if (s.equals("tvCardUrl")) {
                            n = 12;
                            break Label_0362;
                        }
                        break;
                    }
                    case 1062174849: {
                        if (s.equals("hiResHorzUrl")) {
                            n = 13;
                            break Label_0362;
                        }
                        break;
                    }
                    case 398159229: {
                        if (s.equals("mdxVertUrl")) {
                            n = 14;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1551264767: {
                        if (s.equals("storyImgUrl")) {
                            n = 15;
                            break Label_0362;
                        }
                        break;
                    }
                    case 1958422540: {
                        if (s.equals("intrUrl")) {
                            n = 16;
                            break Label_0362;
                        }
                        break;
                    }
                    case 1590765524: {
                        if (s.equals("episodeCount")) {
                            n = 17;
                            break Label_0362;
                        }
                        break;
                    }
                    case -885502996: {
                        if (s.equals("seasonCount")) {
                            n = 18;
                            break Label_0362;
                        }
                        break;
                    }
                    case 1550962648: {
                        if (s.equals("runtime")) {
                            n = 19;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1606289880: {
                        if (s.equals("endtime")) {
                            n = 20;
                            break Label_0362;
                        }
                        break;
                    }
                    case -602057255: {
                        if (s.equals("logicalStart")) {
                            n = 21;
                            break Label_0362;
                        }
                        break;
                    }
                    case -515828317: {
                        if (s.equals("isHdAvailable")) {
                            n = 22;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1944322078: {
                        if (s.equals("isUhdAvailable")) {
                            n = 23;
                            break Label_0362;
                        }
                        break;
                    }
                    case 558909422: {
                        if (s.equals("is3DAvailable")) {
                            n = 24;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1410856650: {
                        if (s.equals("is5dot1Available")) {
                            n = 25;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1077707340: {
                        if (s.equals("isAutoPlayEnabled")) {
                            n = 26;
                            break Label_0362;
                        }
                        break;
                    }
                    case 1426350736: {
                        if (s.equals("isNextPlayableEpisode")) {
                            n = 27;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1931492381: {
                        if (s.equals("isPinProtected")) {
                            n = 28;
                            break Label_0362;
                        }
                        break;
                    }
                    case 719120809: {
                        if (s.equals("isAvailableForED")) {
                            n = 29;
                            break Label_0362;
                        }
                        break;
                    }
                    case -263240971: {
                        if (s.equals("predictedRating")) {
                            n = 30;
                            break Label_0362;
                        }
                        break;
                    }
                    case -1870009353: {
                        if (s.equals("titleUrl")) {
                            n = 31;
                            break Label_0362;
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
                    this.synopsisNarrative = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.quality = jsonElement2.getAsString();
                    continue;
                }
                case 4: {
                    this.directors = jsonElement2.getAsString();
                    continue;
                }
                case 5: {
                    this.actors = jsonElement2.getAsString();
                    continue;
                }
                case 6: {
                    this.genres = jsonElement2.getAsString();
                    continue;
                }
                case 7: {
                    this.certification = jsonElement2.getAsString();
                    continue;
                }
                case 8: {
                    this.horzDispUrl = jsonElement2.getAsString();
                    continue;
                }
                case 9: {
                    this.restUrl = jsonElement2.getAsString();
                    continue;
                }
                case 10: {
                    this.bifUrl = jsonElement2.getAsString();
                    continue;
                }
                case 11: {
                    this.baseUrl = jsonElement2.getAsString();
                    continue;
                }
                case 12: {
                    this.tvCardUrl = jsonElement2.getAsString();
                    continue;
                }
                case 13: {
                    this.hiResHorzUrl = jsonElement2.getAsString();
                    continue;
                }
                case 14: {
                    this.mdxVertUrl = jsonElement2.getAsString();
                    continue;
                }
                case 15: {
                    this.storyImgUrl = jsonElement2.getAsString();
                    continue;
                }
                case 16: {
                    this.intrUrl = jsonElement2.getAsString();
                    continue;
                }
                case 17: {
                    this.episodeCount = jsonElement2.getAsInt();
                    continue;
                }
                case 18: {
                    this.seasonCount = jsonElement2.getAsInt();
                    continue;
                }
                case 19: {
                    this.runtime = jsonElement2.getAsInt();
                    continue;
                }
                case 20: {
                    this.endtime = jsonElement2.getAsInt();
                    continue;
                }
                case 21: {
                    this.logicalStart = jsonElement2.getAsInt();
                    continue;
                }
                case 22: {
                    this.isHdAvailable = jsonElement2.getAsBoolean();
                    continue;
                }
                case 23: {
                    this.isUhdAvailable = jsonElement2.getAsBoolean();
                    continue;
                }
                case 24: {
                    this.is3DAvailable = jsonElement2.getAsBoolean();
                    continue;
                }
                case 25: {
                    this.is5dot1Available = jsonElement2.getAsBoolean();
                    continue;
                }
                case 26: {
                    this.isAutoPlayEnabled = jsonElement2.getAsBoolean();
                    continue;
                }
                case 27: {
                    this.isNextPlayableEpisode = jsonElement2.getAsBoolean();
                    continue;
                }
                case 28: {
                    this.isPinProtected = jsonElement2.getAsBoolean();
                    continue;
                }
                case 29: {
                    this.isAvailableToStream = jsonElement2.getAsBoolean();
                    continue;
                }
                case 30: {
                    this.predictedRating = jsonElement2.getAsFloat();
                    continue;
                }
                case 31: {
                    this.titleUrl = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "Detail [year=" + this.year + ", synopsis=" + this.synopsis + ", synopsisNarrative=" + this.synopsisNarrative + ", quality=" + this.quality + ", directors=" + this.directors + ", actors=" + this.actors + ", genres=" + this.genres + ", certification=" + this.certification + ", horzDispUrl=" + this.horzDispUrl + ", restUrl=" + this.restUrl + ", bifUrl=" + this.bifUrl + ", baseUrl=" + this.baseUrl + ", tvCardUrl=" + this.tvCardUrl + ", hiResHorzUrl=" + this.hiResHorzUrl + ", mdxVertUrl=" + this.mdxVertUrl + ", storyImgUrl=" + this.storyImgUrl + ", intrUrl=" + this.intrUrl + ", titleUrl=" + this.titleUrl + ", episodeCount=" + this.episodeCount + ", seasonCount=" + this.seasonCount + ", predictedRating=" + this.predictedRating + ", isHdAvailable=" + this.isHdAvailable + ", isUhdAvailable=" + this.isUhdAvailable + ", is3DAvailable=" + this.is3DAvailable + ", is5dot1Available=" + this.is5dot1Available + ", isAutoPlayEnabled=" + this.isAutoPlayEnabled + ", isNextPlayableEpisode=" + this.isNextPlayableEpisode + ", isPinProtected=" + this.isPinProtected + ", isAvailableToStream=" + this.isAvailableToStream + ", runtime=" + this.runtime + ", endtime=" + this.endtime + ", logicalStart=" + this.logicalStart + "]";
    }
}
