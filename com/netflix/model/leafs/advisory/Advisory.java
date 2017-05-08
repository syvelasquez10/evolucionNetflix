// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.advisory;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import android.content.Context;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public abstract class Advisory implements JsonMerger, JsonPopulator
{
    private static final String TAG = "Advisory";
    public float displayDelay;
    public float displayDuration;
    public Advisory$DisplayLocation timeLocation;
    
    public Advisory() {
        this.timeLocation = Advisory$DisplayLocation.START;
    }
    
    public static ArrayList<Advisory> asList(final JsonArray jsonArray) {
        final ArrayList<Advisory> list = new ArrayList<Advisory>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); ++i) {
                final JsonObject asJsonObject = jsonArray.get(i).getAsJsonObject();
                final Advisory make = make(Advisory$Type.fromString(asJsonObject.get("type").getAsString()), asJsonObject);
                if (make != null) {
                    list.add(make);
                }
            }
        }
        return list;
    }
    
    public static Advisory getConcreteInstance(final Advisory$Type advisory$Type) {
        switch (Advisory$1.$SwitchMap$com$netflix$model$leafs$advisory$Advisory$Type[advisory$Type.ordinal()]) {
            default: {
                throw new IllegalArgumentException("Advisory ... Unknown Type");
            }
            case 1: {
                return new ProductPlacementAdvisory();
            }
            case 2: {
                return new ContentAdvisory();
            }
            case 3: {
                return new ExpiringContentAdvisory();
            }
        }
    }
    
    public static Advisory make(final Advisory$Type advisory$Type, final JsonObject jsonObject) {
        final Advisory concreteInstance = getConcreteInstance(advisory$Type);
        concreteInstance.populate(jsonObject);
        return concreteInstance;
    }
    
    public abstract JsonObject getData(final JsonElement p0);
    
    public float getDelay() {
        return this.displayDelay;
    }
    
    public float getDuration() {
        return this.displayDuration;
    }
    
    public abstract String getMessage(final Context p0);
    
    public abstract String getSecondaryMessage(final Context p0);
    
    public abstract Advisory$Type getType();
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Advisory", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0138: {
                switch (s.hashCode()) {
                    case -1416457340: {
                        if (s.equals("displayTimeLocation")) {
                            n = 0;
                            break Label_0138;
                        }
                        break;
                    }
                    case 1107814518: {
                        if (s.equals("displayDuration")) {
                            n = 1;
                            break Label_0138;
                        }
                        break;
                    }
                    case 310064551: {
                        if (s.equals("displayTimeGap")) {
                            n = 2;
                            break Label_0138;
                        }
                        break;
                    }
                    case 3076010: {
                        if (s.equals("data")) {
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
                    this.timeLocation = Advisory$DisplayLocation.fromString(jsonElement2.getAsString());
                    continue;
                }
                case 1: {
                    this.displayDuration = jsonElement2.getAsFloat();
                    continue;
                }
                case 2: {
                    this.displayDelay = jsonElement2.getAsFloat();
                    continue;
                }
                case 3: {
                    this.getData(jsonElement2);
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        return false;
    }
}
