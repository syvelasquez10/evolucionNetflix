// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonNull;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import java.util.List;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class PostPlayExperience implements JsonMerger, JsonPopulator, FalkorObject
{
    private static final String TAG = "PostPlayExperience";
    private final List<PostPlayAction> actions;
    private Integer actionsInitialIndex;
    private boolean autoplay;
    private int autoplaySeconds;
    private PostPlayAsset backgroundAsset;
    private final List<PostPlayAction> experienceTitle;
    private final List<PostPlayItem> items;
    private Integer itemsInitialIndex;
    private PostPlayAsset logoAsset;
    private String promotedSupplementalMessage;
    private String promotedTitle;
    private Integer promotedVideoId;
    ModelProxy<? extends BranchNode> proxy;
    private String requestId;
    private int seamlessCountdownSeconds;
    private int seamlessEnd;
    private String theme;
    private String type;
    
    public PostPlayExperience() {
        this(null);
    }
    
    public PostPlayExperience(final ModelProxy<? extends BranchNode> proxy) {
        this.experienceTitle = new ArrayList<PostPlayAction>(1);
        this.actions = new ArrayList<PostPlayAction>(5);
        this.items = new ArrayList<PostPlayItem>(5);
        this.proxy = proxy;
    }
    
    private void populateCTA(final JsonElement jsonElement, final List<PostPlayAction> list) {
        if (!jsonElement.isJsonNull()) {
            final JsonArray asJsonArray = jsonElement.getAsJsonArray();
            for (int i = 0; i < asJsonArray.size(); ++i) {
                list.add(new PostPlayAction(asJsonArray.get(i), this.proxy));
            }
        }
    }
    
    public List<PostPlayAction> getActions() {
        return this.actions;
    }
    
    public Integer getActionsInitialIndex() {
        return this.actionsInitialIndex;
    }
    
    public boolean getAutoplay() {
        return this.autoplay;
    }
    
    public int getAutoplaySeconds() {
        return this.autoplaySeconds;
    }
    
    public PostPlayAsset getBackgroundAsset() {
        return this.backgroundAsset;
    }
    
    public List<PostPlayAction> getExperienceTitle() {
        return this.experienceTitle;
    }
    
    public List<PostPlayItem> getItems() {
        return this.items;
    }
    
    public Integer getItemsInitialIndex() {
        return this.itemsInitialIndex;
    }
    
    public PostPlayAsset getLogoAsset() {
        return this.logoAsset;
    }
    
    public String getPromotedSupplementalMessage() {
        return this.promotedSupplementalMessage;
    }
    
    public String getPromotedTitle() {
        return this.promotedTitle;
    }
    
    public Integer getPromotedVideoId() {
        return this.promotedVideoId;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public int getSeamlessCountdownSeconds() {
        return this.seamlessCountdownSeconds;
    }
    
    public int getSeamlessEnd() {
        return this.seamlessEnd;
    }
    
    public String getTheme() {
        return this.theme;
    }
    
    public String getType() {
        return this.type;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("PostPlayExperience", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            if (!(jsonElement2 instanceof JsonNull)) {
                final String s = entry.getKey();
                int n = 0;
                Label_0234: {
                    switch (s.hashCode()) {
                        case 693933066: {
                            if (s.equals("requestId")) {
                                n = 0;
                                break Label_0234;
                            }
                            break;
                        }
                        case 3575610: {
                            if (s.equals("type")) {
                                n = 1;
                                break Label_0234;
                            }
                            break;
                        }
                        case 110327241: {
                            if (s.equals("theme")) {
                                n = 2;
                                break Label_0234;
                            }
                            break;
                        }
                        case 1439562083: {
                            if (s.equals("autoplay")) {
                                n = 3;
                                break Label_0234;
                            }
                            break;
                        }
                        case 49423260: {
                            if (s.equals("autoplaySeconds")) {
                                n = 4;
                                break Label_0234;
                            }
                            break;
                        }
                        case 1741756850: {
                            if (s.equals("promotedVideoId")) {
                                n = 5;
                                break Label_0234;
                            }
                            break;
                        }
                        case 1121767444: {
                            if (s.equals("promotedTitle")) {
                                n = 6;
                                break Label_0234;
                            }
                            break;
                        }
                        case 2065592703: {
                            if (s.equals("promotedSupplementalMessage")) {
                                n = 7;
                                break Label_0234;
                            }
                            break;
                        }
                        case -1408207997: {
                            if (s.equals("assets")) {
                                n = 8;
                                break Label_0234;
                            }
                            break;
                        }
                        case 2111580942: {
                            if (s.equals("experienceTitle")) {
                                n = 9;
                                break Label_0234;
                            }
                            break;
                        }
                        case 473683339: {
                            if (s.equals("actionsInitialIndex")) {
                                n = 10;
                                break Label_0234;
                            }
                            break;
                        }
                        case -1161803523: {
                            if (s.equals("actions")) {
                                n = 11;
                                break Label_0234;
                            }
                            break;
                        }
                        case -3512370: {
                            if (s.equals("itemsInitialIndex")) {
                                n = 12;
                                break Label_0234;
                            }
                            break;
                        }
                        case 100526016: {
                            if (s.equals("items")) {
                                n = 13;
                                break Label_0234;
                            }
                            break;
                        }
                        case 1668395075: {
                            if (s.equals("currentEpisodeSeamlessData")) {
                                n = 14;
                                break Label_0234;
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
                        this.requestId = jsonElement2.getAsString();
                        continue;
                    }
                    case 1: {
                        this.type = jsonElement2.getAsString();
                        continue;
                    }
                    case 2: {
                        this.theme = jsonElement2.getAsString();
                        continue;
                    }
                    case 3: {
                        this.autoplay = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 4: {
                        this.autoplaySeconds = jsonElement2.getAsInt();
                        continue;
                    }
                    case 5: {
                        this.promotedVideoId = jsonElement2.getAsInt();
                        continue;
                    }
                    case 6: {
                        this.promotedTitle = jsonElement2.getAsString();
                        continue;
                    }
                    case 7: {
                        this.promotedSupplementalMessage = jsonElement2.getAsString();
                        continue;
                    }
                    case 8: {
                        if (!jsonElement2.isJsonNull()) {
                            for (final Map.Entry<String, JsonElement> entry2 : jsonElement2.getAsJsonObject().entrySet()) {
                                final String s2 = entry2.getKey();
                                int n2 = 0;
                                Label_0722: {
                                    switch (s2.hashCode()) {
                                        case -1332194002: {
                                            if (s2.equals("background")) {
                                                n2 = 0;
                                                break Label_0722;
                                            }
                                            break;
                                        }
                                        case 3327403: {
                                            if (s2.equals("logo")) {
                                                n2 = 1;
                                                break Label_0722;
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
                                        this.logoAsset = new PostPlayAsset(PostPlayAsset$Type.LOGO, entry2.getValue());
                                        continue;
                                    }
                                }
                            }
                            continue;
                        }
                        continue;
                    }
                    case 9: {
                        this.populateCTA(jsonElement2, this.experienceTitle);
                        continue;
                    }
                    case 10: {
                        this.actionsInitialIndex = jsonElement2.getAsInt();
                        continue;
                    }
                    case 11: {
                        this.populateCTA(jsonElement2, this.actions);
                        continue;
                    }
                    case 12: {
                        this.itemsInitialIndex = jsonElement2.getAsInt();
                        continue;
                    }
                    case 13: {
                        if (!jsonElement2.isJsonNull()) {
                            final JsonArray asJsonArray = jsonElement2.getAsJsonArray();
                            for (int i = 0; i < asJsonArray.size(); ++i) {
                                this.items.add(new PostPlayItem(asJsonArray.get(i), this.proxy));
                            }
                            continue;
                        }
                        continue;
                    }
                    case 14: {
                        if (jsonElement2.getAsJsonObject().has("seamlessEnd")) {
                            this.seamlessEnd = jsonElement2.getAsJsonObject().get("seamlessEnd").getAsInt();
                        }
                        if (jsonElement2.getAsJsonObject().has("seamlessCountdownSeconds")) {
                            this.seamlessCountdownSeconds = jsonElement2.getAsJsonObject().get("seamlessCountdownSeconds").getAsInt();
                            continue;
                        }
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
    
    public void setActionsInitialIndex(final Integer actionsInitialIndex) {
        this.actionsInitialIndex = actionsInitialIndex;
    }
    
    public void setAutoplay(final boolean autoplay) {
        this.autoplay = autoplay;
    }
    
    public void setAutoplaySeconds(final int autoplaySeconds) {
        this.autoplaySeconds = autoplaySeconds;
    }
    
    public void setBackgroundAsset(final PostPlayAsset backgroundAsset) {
        this.backgroundAsset = backgroundAsset;
    }
    
    public void setItemsInitialIndex(final Integer itemsInitialIndex) {
        this.itemsInitialIndex = itemsInitialIndex;
    }
    
    public void setLogoAsset(final PostPlayAsset logoAsset) {
        this.logoAsset = logoAsset;
    }
    
    public void setPromotedSupplementalMessage(final String promotedSupplementalMessage) {
        this.promotedSupplementalMessage = promotedSupplementalMessage;
    }
    
    public void setPromotedTitle(final String promotedTitle) {
        this.promotedTitle = promotedTitle;
    }
    
    public void setPromotedVideoId(final Integer promotedVideoId) {
        this.promotedVideoId = promotedVideoId;
    }
    
    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }
    
    public void setSeamlessCountdownSeconds(final int seamlessCountdownSeconds) {
        this.seamlessCountdownSeconds = seamlessCountdownSeconds;
    }
    
    public void setSeamlessEnd(final int seamlessEnd) {
        this.seamlessEnd = seamlessEnd;
    }
    
    public void setTheme(final String theme) {
        this.theme = theme;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
}
