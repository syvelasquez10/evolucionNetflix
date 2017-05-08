// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.originals;

import java.util.Collection;
import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.Iterator;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import com.netflix.model.leafs.Rating;
import com.netflix.model.leafs.Delivery;
import java.util.List;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class BillboardSummary implements JsonMerger, JsonPopulator, FalkorObject
{
    private static final String TAG = "BillboardSummary";
    private List<BillboardCTA> actions;
    private String ancestorId;
    private BillboardAssets assets;
    private String awardTrackId;
    public List<String> badgeKeys;
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
    
    public BillboardSummary() {
        this.badgeKeys = new ArrayList<String>(3);
    }
    
    private void getMotionBillboardData(final JsonElement jsonElement) {
        for (final Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            if (entry.getKey().equals("horizontalBackground")) {
                for (final Map.Entry<String, JsonElement> entry2 : jsonElement2.getAsJsonObject().entrySet()) {
                    final JsonElement jsonElement3 = entry2.getValue();
                    final String s = entry2.getKey();
                    int n = 0;
                    Label_0158: {
                        switch (s.hashCode()) {
                            case -157174927: {
                                if (s.equals("motionId")) {
                                    n = 0;
                                    break Label_0158;
                                }
                                break;
                            }
                            case -577443367: {
                                if (s.equals("motionUrl")) {
                                    n = 1;
                                    break Label_0158;
                                }
                                break;
                            }
                            case -483381427: {
                                if (s.equals("motionShouldLoop")) {
                                    n = 2;
                                    break Label_0158;
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
                            this.motionId = JsonUtils.getAsStringSafe(jsonElement3);
                            continue;
                        }
                        case 1: {
                            this.motionUrl = JsonUtils.getAsStringSafe(jsonElement3);
                            continue;
                        }
                        case 2: {
                            this.motionShouldLoop = JsonUtils.getAsBoolSafe(jsonElement3);
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    public List<BillboardCTA> getActions() {
        return this.actions;
    }
    
    public String getAncestorId() {
        return this.ancestorId;
    }
    
    public String getAwardTrackId() {
        return this.awardTrackId;
    }
    
    public BillboardAwardsHeadline getAwardsHeadline() {
        if (this.assets == null) {
            return null;
        }
        return this.assets.getAwardsHeadline();
    }
    
    public BillboardBackground getBackground() {
        if (this.assets == null) {
            return null;
        }
        return this.assets.getBackground();
    }
    
    public BillboardBackgroundPortrait getBackgroundPortrait() {
        if (this.assets == null) {
            return null;
        }
        return this.assets.getBackgroundPortrait();
    }
    
    public List<String> getBadgeKeys() {
        return this.badgeKeys;
    }
    
    public String getBillboardTheme() {
        return this.billboardTheme;
    }
    
    public String getBillboardType() {
        return this.billboardType;
    }
    
    public BillboardDateBadge getDateBadge() {
        if (this.assets == null) {
            return null;
        }
        return this.assets.getDateBadge();
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
                    case 452553294: {
                        if (s.equals("ancestorId")) {
                            n = 1;
                            break Label_0274;
                        }
                        break;
                    }
                    case 585773339: {
                        if (s.equals("isOriginal")) {
                            n = 2;
                            break Label_0274;
                        }
                        break;
                    }
                    case 110371416: {
                        if (s.equals("title")) {
                            n = 3;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1828656532: {
                        if (s.equals("synopsis")) {
                            n = 4;
                            break Label_0274;
                        }
                        break;
                    }
                    case 417758403: {
                        if (s.equals("supplementalMessage")) {
                            n = 5;
                            break Label_0274;
                        }
                        break;
                    }
                    case -2030197974: {
                        if (s.equals("imageDescriptor")) {
                            n = 6;
                            break Label_0274;
                        }
                        break;
                    }
                    case 3704893: {
                        if (s.equals("year")) {
                            n = 7;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1362349198: {
                        if (s.equals("maturityRating")) {
                            n = 8;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1968370160: {
                        if (s.equals("seasons")) {
                            n = 9;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1550962648: {
                        if (s.equals("runtime")) {
                            n = 10;
                            break Label_0274;
                        }
                        break;
                    }
                    case 526707678: {
                        if (s.equals("videoAssets")) {
                            n = 11;
                            break Label_0274;
                        }
                        break;
                    }
                    case 2054629203: {
                        if (s.equals("isAward")) {
                            n = 12;
                            break Label_0274;
                        }
                        break;
                    }
                    case 1853554345: {
                        if (s.equals("awardTrackId")) {
                            n = 13;
                            break Label_0274;
                        }
                        break;
                    }
                    case -1037500982: {
                        if (s.equals("billboardTheme")) {
                            n = 14;
                            break Label_0274;
                        }
                        break;
                    }
                    case -171998439: {
                        if (s.equals("billboardType")) {
                            n = 15;
                            break Label_0274;
                        }
                        break;
                    }
                    case 2112468023: {
                        if (s.equals("badgeKeys")) {
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
                    case -1161803523: {
                        if (s.equals("actions")) {
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
                    this.ancestorId = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 2: {
                    this.isOriginal = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 3: {
                    this.title = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 4: {
                    this.synopsis = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 5: {
                    this.supplementalMessage = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 6: {
                    this.imageDescriptor = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 7: {
                    this.year = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 8: {
                    this.maturityRating = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 9: {
                    this.seasons = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 10: {
                    this.runtime = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 11: {
                    if (!jsonElement2.isJsonNull()) {
                        this.getMotionBillboardData(jsonElement2);
                        continue;
                    }
                    continue;
                }
                case 12: {
                    this.isAward = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 13: {
                    this.awardTrackId = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 14: {
                    this.billboardTheme = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 15: {
                    this.billboardType = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 16: {
                    if (!jsonElement2.isJsonNull()) {
                        final JsonArray asJsonArray = jsonElement2.getAsJsonArray();
                        for (int i = 0; i < asJsonArray.size(); ++i) {
                            this.badgeKeys.add(asJsonArray.get(i).getAsString());
                        }
                        continue;
                    }
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
                    this.actions = BillboardCTA.getListOfActions(jsonElement2);
                    continue;
                }
                case 20: {
                    this.rating = new Rating(jsonElement2);
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("BillboardSummary", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "id": {
                this.id = jsonParser.getValueAsString();
                break;
            }
            case "ancestorId": {
                this.ancestorId = jsonParser.getValueAsString();
                break;
            }
            case "isOriginal": {
                this.isOriginal = jsonParser.getValueAsBoolean();
                break;
            }
            case "title": {
                this.title = jsonParser.getValueAsString();
                break;
            }
            case "synopsis": {
                this.synopsis = jsonParser.getValueAsString();
                break;
            }
            case "supplementalMessage": {
                this.supplementalMessage = jsonParser.getValueAsString();
                break;
            }
            case "imageDescriptor": {
                this.imageDescriptor = jsonParser.getValueAsString();
                break;
            }
            case "year": {
                this.year = jsonParser.getValueAsString();
                break;
            }
            case "maturityRating": {
                this.maturityRating = jsonParser.getValueAsString();
                break;
            }
            case "seasons": {
                this.seasons = jsonParser.getValueAsString();
                break;
            }
            case "runtime": {
                this.runtime = jsonParser.getValueAsString();
                break;
            }
            case "motionId": {
                this.motionId = jsonParser.getValueAsString();
                break;
            }
            case "motionUrl": {
                this.motionUrl = jsonParser.getValueAsString();
                break;
            }
            case "motionShouldLoop": {
                this.motionShouldLoop = jsonParser.getValueAsBoolean();
                break;
            }
            case "isAward": {
                this.isAward = jsonParser.getValueAsBoolean();
                break;
            }
            case "awardTrackId": {
                this.awardTrackId = jsonParser.getValueAsString();
                break;
            }
            case "billboardTheme": {
                this.billboardTheme = jsonParser.getValueAsString();
                break;
            }
            case "billboardType": {
                this.billboardType = jsonParser.getValueAsString();
                break;
            }
            case "badgeKeys": {
                this.badgeKeys.addAll(BranchNodeUtils.getAsStringArray(jsonParser));
                break;
            }
            case "assets": {
                this.assets = new BillboardAssets(jsonParser);
                break;
            }
            case "delivery": {
                this.delivery = new Delivery(jsonParser);
                break;
            }
            case "actions": {
                this.actions = BillboardCTA.getListOfActions(jsonParser);
                break;
            }
            case "rating": {
                this.rating = new Rating(jsonParser);
                break;
            }
        }
        return true;
    }
}
