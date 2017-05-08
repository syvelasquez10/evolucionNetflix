// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.advisory;

import android.content.Context;
import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import android.annotation.SuppressLint;

@SuppressLint({ "ParcelCreator" })
public class ProductPlacementAdvisory extends Advisory
{
    private static final String TAG = "ProductPlacementAdvisory";
    public String text;
    
    @Override
    public JsonObject getData(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("ProductPlacementAdvisory", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0114: {
                switch (s.hashCode()) {
                    case 3556653: {
                        if (s.equals("text")) {
                            n = 0;
                            break Label_0114;
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
                    this.text = jsonElement2.getAsString();
                    continue;
                }
            }
        }
        return asJsonObject;
    }
    
    @Override
    public String getMessage(final Context context) {
        return this.text;
    }
    
    @Override
    public String getSecondaryMessage(final Context context) {
        return null;
    }
    
    @Override
    public Advisory$Type getType() {
        return Advisory$Type.PRODUCT_PLACEMENT_ADVISORY;
    }
}
