// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;

public final class KidsCharacter$Summary extends Video$Summary
{
    private static final String TAG = "KidsCharacter.Summary";
    public String characterImageUrl;
    
    public String getCharacterImageUrl() {
        return this.characterImageUrl;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        super.populate(jsonElement);
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable()) {
            Log.v("KidsCharacter.Summary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0118: {
                switch (s.hashCode()) {
                    case 1843715893: {
                        if (s.equals("characterImgUrl")) {
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
                    this.characterImageUrl = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "KidsCharacter Summary{characterImageUrl=" + this.characterImageUrl + '}';
    }
}
