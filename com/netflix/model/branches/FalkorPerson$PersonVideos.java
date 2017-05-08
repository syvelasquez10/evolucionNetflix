// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class FalkorPerson$PersonVideos implements JsonMerger, JsonPopulator
{
    private static final String TAG = "PersonVideos";
    final /* synthetic */ FalkorPerson this$0;
    public List<String> videoIds;
    
    public FalkorPerson$PersonVideos(final FalkorPerson this$0) {
        this.this$0 = this$0;
        this.videoIds = new ArrayList<String>();
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        this.videoIds.clear();
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("PersonVideos", "Populating with: " + asJsonObject);
        }
        final Iterator<Map.Entry<String, JsonElement>> iterator = asJsonObject.entrySet().iterator();
        while (iterator.hasNext()) {
            final JsonElement jsonElement2 = ((Map.Entry<String, JsonElement>)iterator.next()).getValue();
            if (jsonElement2.isJsonArray() && jsonElement2.getAsJsonArray().get(1).isJsonArray()) {
                final Iterator<JsonElement> iterator2 = ((JsonArray)jsonElement2.getAsJsonArray().get(1)).iterator();
                while (iterator2.hasNext()) {
                    this.videoIds.add(iterator2.next().toString());
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        this.videoIds.clear();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("PersonVideos", "Populating with: " + jsonParser);
        }
        if (jsonParser.getCurrentToken().equals(JsonToken.START_ARRAY)) {
            final JsonToken nextToken = jsonParser.nextToken();
            if (!nextToken.equals(JsonToken.END_ARRAY)) {
                if (nextToken.equals(JsonToken.START_ARRAY)) {
                    this.videoIds.add(String.valueOf(((List)BranchNodeUtils.readValue(jsonParser, nextToken, true)).get(1)));
                }
                jsonParser.nextToken();
            }
        }
        return false;
    }
}
