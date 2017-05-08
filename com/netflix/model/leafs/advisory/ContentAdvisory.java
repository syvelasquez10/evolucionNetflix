// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.advisory;

import android.content.Context;
import java.util.Iterator;
import java.util.Collection;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import android.annotation.SuppressLint;

@SuppressLint({ "ParcelCreator" })
public class ContentAdvisory extends Advisory
{
    private static final String TAG = "ContentAdvisory";
    public ArrayList<String> advisories;
    public String i18nAdvisories;
    public String i18nRating;
    public String rating;
    
    public ContentAdvisory() {
        this.advisories = new ArrayList<String>();
    }
    
    @Override
    public JsonObject getData(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("ContentAdvisory", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0138: {
                switch (s.hashCode()) {
                    case -938102371: {
                        if (s.equals("rating")) {
                            n = 0;
                            break Label_0138;
                        }
                        break;
                    }
                    case -648601833: {
                        if (s.equals("advisories")) {
                            n = 1;
                            break Label_0138;
                        }
                        break;
                    }
                    case -1729984165: {
                        if (s.equals("i18nRating")) {
                            n = 2;
                            break Label_0138;
                        }
                        break;
                    }
                    case 1146480597: {
                        if (s.equals("i18nAdvisories")) {
                            n = 3;
                            break Label_0138;
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
                    this.rating = jsonElement2.getAsString();
                    continue;
                }
                case 1: {
                    this.advisories = new ArrayList<String>(JsonUtils.createStringArray(jsonElement2.getAsJsonArray()));
                    continue;
                }
                case 2: {
                    this.i18nRating = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.i18nAdvisories = jsonElement2.getAsString();
                    continue;
                }
            }
        }
        return asJsonObject;
    }
    
    @Override
    public String getMessage(final Context context) {
        return this.i18nRating;
    }
    
    @Override
    public String getSecondaryMessage(final Context context) {
        return this.i18nAdvisories;
    }
    
    @Override
    public Advisory$Type getType() {
        return Advisory$Type.CONTENT_ADVISORY;
    }
}
