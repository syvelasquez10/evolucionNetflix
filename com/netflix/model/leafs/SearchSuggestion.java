// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public class SearchSuggestion implements JsonPopulator, com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion
{
    private static final String TAG = "SearchSuggestion";
    private String title;
    
    @Override
    public String getTitle() {
        return StringUtils.capitalizeFirstLetter(this.title);
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable("SearchSuggestion", 2)) {
            Log.v("SearchSuggestion", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0118: {
                switch (s.hashCode()) {
                    case 110371416: {
                        if (s.equals("title")) {
                            n = 0;
                            break Label_0118;
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
                    this.title = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "SearchSuggestion [title=" + this.title + "]";
    }
}
