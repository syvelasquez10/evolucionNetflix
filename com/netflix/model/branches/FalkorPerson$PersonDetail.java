// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.PersonDetail;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public final class FalkorPerson$PersonDetail implements JsonMerger, JsonPopulator, PersonDetail
{
    private static final String TAG = "PersonDetail.Detail";
    public String born;
    public String id;
    public String imgUrl;
    public String knownFor;
    public String name;
    public String spouse;
    
    @Override
    public String getBorn() {
        return this.born;
    }
    
    @Override
    public String getHeadshotImageUrl() {
        return this.imgUrl;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public String getKnownFor() {
        return this.knownFor;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getSpouse() {
        return this.spouse;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable()) {
            Log.v("PersonDetail.Detail", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0154: {
                switch (s.hashCode()) {
                    case -895757675: {
                        if (s.equals("spouse")) {
                            n = 0;
                            break Label_0154;
                        }
                        break;
                    }
                    case 3373707: {
                        if (s.equals("name")) {
                            n = 1;
                            break Label_0154;
                        }
                        break;
                    }
                    case 3029833: {
                        if (s.equals("born")) {
                            n = 2;
                            break Label_0154;
                        }
                        break;
                    }
                    case -1185088852: {
                        if (s.equals("imgUrl")) {
                            n = 3;
                            break Label_0154;
                        }
                        break;
                    }
                    case 3355: {
                        if (s.equals("id")) {
                            n = 4;
                            break Label_0154;
                        }
                        break;
                    }
                    case -365615482: {
                        if (s.equals("knownFor")) {
                            n = 5;
                            break Label_0154;
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
                    this.spouse = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 1: {
                    this.name = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.born = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 3: {
                    this.imgUrl = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 4: {
                    this.id = jsonElement2.getAsString();
                    continue;
                }
                case 5: {
                    this.knownFor = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Log.isLoggable()) {
            Log.v("PersonDetail.Detail", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "spouse": {
                this.spouse = jsonParser.getValueAsString();
                break;
            }
            case "name": {
                jsonParser.getValueAsString();
                break;
            }
            case "born": {
                this.born = jsonParser.getValueAsString();
                break;
            }
            case "imgUrl": {
                this.imgUrl = jsonParser.getValueAsString();
                break;
            }
            case "id": {
                this.id = jsonParser.getValueAsString();
            }
            case "knownFor": {
                this.knownFor = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
    }
}
