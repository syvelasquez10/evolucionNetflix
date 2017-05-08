// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.originals;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class BillboardAssets implements JsonMerger, JsonPopulator
{
    private static final String TAG = "Assets";
    private BillboardAwardsHeadline awardsHeadline;
    private BillboardBackground background;
    private BillboardBackgroundPortrait backgroundPortrait;
    private BillboardDateBadge dateBadge;
    private BillboardLogo logo;
    
    public BillboardAssets(final JsonParser jsonParser) {
        BranchNodeUtils.merge(this, jsonParser, jsonParser.getCurrentToken(), false, 10);
    }
    
    public BillboardAssets(final JsonElement jsonElement) {
        this.populate(jsonElement);
    }
    
    public BillboardAwardsHeadline getAwardsHeadline() {
        return this.awardsHeadline;
    }
    
    public BillboardBackground getBackground() {
        return this.background;
    }
    
    public BillboardBackgroundPortrait getBackgroundPortrait() {
        return this.backgroundPortrait;
    }
    
    public BillboardDateBadge getDateBadge() {
        return this.dateBadge;
    }
    
    public BillboardLogo getLogo() {
        return this.logo;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Assets", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0146: {
                switch (s.hashCode()) {
                    case 3327403: {
                        if (s.equals("logo")) {
                            n = 0;
                            break Label_0146;
                        }
                        break;
                    }
                    case -1332194002: {
                        if (s.equals("background")) {
                            n = 1;
                            break Label_0146;
                        }
                        break;
                    }
                    case 621897449: {
                        if (s.equals("backgroundPortrait")) {
                            n = 2;
                            break Label_0146;
                        }
                        break;
                    }
                    case -276211563: {
                        if (s.equals("dateBadge")) {
                            n = 3;
                            break Label_0146;
                        }
                        break;
                    }
                    case 1961536938: {
                        if (s.equals("awardsHeadline")) {
                            n = 4;
                            break Label_0146;
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
                    this.logo = new BillboardLogo(jsonElement2);
                    continue;
                }
                case 1: {
                    this.background = new BillboardBackground(jsonElement2);
                    continue;
                }
                case 2: {
                    this.backgroundPortrait = new BillboardBackgroundPortrait(jsonElement2);
                    continue;
                }
                case 3: {
                    this.dateBadge = new BillboardDateBadge(jsonElement2);
                    continue;
                }
                case 4: {
                    this.awardsHeadline = new BillboardAwardsHeadline(jsonElement2);
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Assets", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "logo": {
                this.logo = new BillboardLogo(jsonParser);
                break;
            }
            case "background": {
                this.background = new BillboardBackground(jsonParser);
                break;
            }
            case "dateBadge": {
                this.dateBadge = new BillboardDateBadge(jsonParser);
                break;
            }
            case "backgroundPortrait": {
                this.backgroundPortrait = new BillboardBackgroundPortrait(jsonParser);
                break;
            }
            case "awardsHeadline": {
                this.awardsHeadline = new BillboardAwardsHeadline(jsonParser);
                break;
            }
        }
        return true;
    }
}
