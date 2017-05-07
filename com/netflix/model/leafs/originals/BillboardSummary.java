// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.originals;

import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.model.leafs.Rating;
import com.netflix.model.leafs.Delivery;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class BillboardSummary implements JsonPopulator, FalkorObject
{
    private static final String TAG = "BillboardSummary";
    private BillboardCTA action;
    private BillboardAssets assets;
    private String awardTrackId;
    private String billboardTheme;
    private String billboardType;
    private Delivery delivery;
    private String episodes;
    private String id;
    private String imageDescriptor;
    private Boolean isAward;
    private Boolean isOriginal;
    private String maturityRating;
    private String motionId;
    private Boolean motionShouldLoop;
    private String motionUrl;
    private Rating rating;
    private String runtime;
    private String seasons;
    private String supplementalMessage;
    private String synopsis;
    private String title;
    private String year;
    
    public BillboardCTA getAction() {
        return this.action;
    }
    
    public String getAwardTrackId() {
        return this.awardTrackId;
    }
    
    public BillboardBackground getBackground() {
        if (this.assets == null) {
            return null;
        }
        return this.assets.getBackground();
    }
    
    public String getBillboardTheme() {
        return this.billboardTheme;
    }
    
    public String getBillboardType() {
        return this.billboardType;
    }
    
    public Delivery getDelivery() {
        return this.delivery;
    }
    
    public String getEpisodes() {
        return this.episodes;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getImageDescriptor() {
        return this.imageDescriptor;
    }
    
    public BillboardLogo getLogo() {
        if (this.assets == null) {
            return null;
        }
        return this.assets.getLogo();
    }
    
    public String getMaturityRating() {
        return this.maturityRating;
    }
    
    public String getMotionId() {
        return this.motionId;
    }
    
    public Boolean getMotionShouldLoop() {
        return this.motionShouldLoop;
    }
    
    public String getMotionUrl() {
        return this.motionUrl;
    }
    
    public Rating getRating() {
        return this.rating;
    }
    
    public String getRuntime() {
        return this.runtime;
    }
    
    public String getSeasons() {
        return this.seasons;
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
    
    public String getYear() {
        return this.year;
    }
    
    public Boolean isAward() {
        return this.isAward;
    }
    
    public Boolean isOriginal() {
        return this.isOriginal;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("BillboardSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0274: {
                switch (s.hashCode()) {
                    case 3355: {
                        if (s.equals("id")) {
                            n = 0;
                            break Label_0274;
                        }
                        break;
                    }
                    case 585773339: {
                        if (s.equals("isOriginal")) {
                            n = 1;
                            break Label_0274;
                        }
                        break;
                    }
                    case 110371416: {
                        if (s.equals("title")) {
                            n = 2;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1828656532: {
                        if (s.equals("synopsis")) {
                            n = 3;
                            break Label_0274;
                        }
                        break;
                    }
                    case 417758403: {
                        if (s.equals("supplementalMessage")) {
                            n = 4;
                            break Label_0274;
                        }
                        break;
                    }
                    case -2030197974: {
                        if (s.equals("imageDescriptor")) {
                            n = 5;
                            break Label_0274;
                        }
                        break;
                    }
                    case 3704893: {
                        if (s.equals("year")) {
                            n = 6;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1362349198: {
                        if (s.equals("maturityRating")) {
                            n = 7;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1968370160: {
                        if (s.equals("seasons")) {
                            n = 8;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1550962648: {
                        if (s.equals("runtime")) {
                            n = 9;
                            break Label_0274;
                        }
                        break;
                    }
                    case -157174927: {
                        if (s.equals("motionId")) {
                            n = 10;
                            break Label_0274;
                        }
                        break;
                    }
                    case -577443367: {
                        if (s.equals("motionUrl")) {
                            n = 11;
                            break Label_0274;
                        }
                        break;
                    }
                    case -483381427: {
                        if (s.equals("motionShouldLoop")) {
                            n = 12;
                            break Label_0274;
                        }
                        break;
                    }
                    case 2054629203: {
                        if (s.equals("isAward")) {
                            n = 13;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1853554345: {
                        if (s.equals("awardTrackId")) {
                            n = 14;
                            break Label_0274;
                        }
                        break;
                    }
                    case -1037500982: {
                        if (s.equals("billboardTheme")) {
                            n = 15;
                            break Label_0274;
                        }
                        break;
                    }
                    case -171998439: {
                        if (s.equals("billboardType")) {
                            n = 16;
                            break Label_0274;
                        }
                        break;
                    }
                    case -1408207997: {
                        if (s.equals("assets")) {
                            n = 17;
                            break Label_0274;
                        }
                        break;
                    }
                    case 823466996: {
                        if (s.equals("delivery")) {
                            n = 18;
                            break Label_0274;
                        }
                        break;
                    }
                    case -1422950858: {
                        if (s.equals("action")) {
                            n = 19;
                            break Label_0274;
                        }
                        break;
                    }
                    case -938102371: {
                        if (s.equals("rating")) {
                            n = 20;
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
                    this.id = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 1: {
                    this.isOriginal = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 2: {
                    this.title = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 3: {
                    this.synopsis = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 4: {
                    this.supplementalMessage = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 5: {
                    this.imageDescriptor = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 6: {
                    this.year = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 7: {
                    this.maturityRating = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 8: {
                    this.seasons = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 9: {
                    this.runtime = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 10: {
                    this.motionId = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 11: {
                    this.motionUrl = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 12: {
                    this.motionShouldLoop = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 13: {
                    this.isAward = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 14: {
                    this.awardTrackId = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 15: {
                    this.billboardTheme = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 16: {
                    this.billboardType = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 17: {
                    this.assets = new BillboardAssets(jsonElement2);
                    continue;
                }
                case 18: {
                    this.delivery = new Delivery(jsonElement2);
                    continue;
                }
                case 19: {
                    this.action = new BillboardCTA(jsonElement2);
                    continue;
                }
                case 20: {
                    this.rating = new Rating(jsonElement2);
                    continue;
                }
            }
        }
    }
}
