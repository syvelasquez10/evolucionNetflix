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
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.interface_.IconFontGlyph;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class Video$Evidence implements JsonPopulator
{
    private static final String TAG = "Evidence";
    private String kind;
    private String text;
    
    public IconFontGlyph getIconFontGlyph() {
        if (this.kind == null) {
            return null;
        }
        final String lowerCase = this.kind.toLowerCase(Locale.US);
        switch (lowerCase) {
            default: {
                return IconFontGlyph.EVIDENCE_GENERIC;
            }
            case "awards": {
                return IconFontGlyph.EVIDENCE_AWARDS;
            }
            case "boxoffice": {
                return IconFontGlyph.EVIDENCE_BOX_OFFICE;
            }
            case "talent": {
                return IconFontGlyph.EVIDENCE_TALENT;
            }
            case "tvratings": {
                return IconFontGlyph.EVIDENCE_TV_RATINGS;
            }
        }
    }
    
    public String getText() {
        return this.text;
    }
    
    @Override
    public void populate(JsonElement value) {
        final JsonObject asJsonObject = value.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Evidence", "Populating with: " + asJsonObject);
        }
        value = asJsonObject.get("value");
        if (value != null && value.isJsonObject()) {
            for (final Map.Entry<String, JsonElement> entry : value.getAsJsonObject().entrySet()) {
                final JsonElement jsonElement = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0142: {
                    switch (s.hashCode()) {
                        case 3292052: {
                            if (s.equals("kind")) {
                                n = 0;
                                break Label_0142;
                            }
                            break;
                        }
                        case 3556653: {
                            if (s.equals("text")) {
                                n = 1;
                                break Label_0142;
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
                        this.kind = jsonElement.getAsString();
                        continue;
                    }
                    case 1: {
                        this.text = jsonElement.getAsString();
                        continue;
                    }
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "Evidence [kind=" + this.kind + ", text=" + this.text + "]";
    }
}
