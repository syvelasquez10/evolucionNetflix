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
public class ExpiringContentAdvisory extends Advisory
{
    private static final String TAG = "ExpiryAdvisory";
    public long expirationTime;
    public String localizedDate;
    private ExpiringContentAdvisory$ContentType type;
    public long videoId;
    public boolean willExpire;
    
    public ExpiringContentAdvisory() {
        this.type = ExpiringContentAdvisory$ContentType.SEASON;
    }
    
    @Override
    public JsonObject getData(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("ExpiryAdvisory", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0146: {
                switch (s.hashCode()) {
                    case 112202875: {
                        if (s.equals("video")) {
                            n = 0;
                            break Label_0146;
                        }
                        break;
                    }
                    case -18680943: {
                        if (s.equals("willExpire")) {
                            n = 1;
                            break Label_0146;
                        }
                        break;
                    }
                    case 3575610: {
                        if (s.equals("type")) {
                            n = 2;
                            break Label_0146;
                        }
                        break;
                    }
                    case -668327396: {
                        if (s.equals("expirationTime")) {
                            n = 3;
                            break Label_0146;
                        }
                        break;
                    }
                    case 696250825: {
                        if (s.equals("localizedDate")) {
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
                    this.videoId = jsonElement2.getAsLong();
                    continue;
                }
                case 1: {
                    this.willExpire = jsonElement2.getAsBoolean();
                    continue;
                }
                case 2: {
                    this.type = ExpiringContentAdvisory$ContentType.valueOf(jsonElement2.getAsString().toUpperCase());
                    continue;
                }
                case 3: {
                    this.expirationTime = jsonElement2.getAsLong();
                    continue;
                }
                case 4: {
                    this.localizedDate = jsonElement2.getAsString();
                    continue;
                }
            }
        }
        return asJsonObject;
    }
    
    @Override
    public String getMessage(final Context context) {
        int n = 0;
        switch (ExpiringContentAdvisory$1.$SwitchMap$com$netflix$model$leafs$advisory$ExpiringContentAdvisory$ContentType[this.type.ordinal()]) {
            default: {
                n = 2131230959;
                break;
            }
            case 1: {
                n = 2131230961;
                break;
            }
            case 2: {
                n = 2131230962;
                break;
            }
            case 3: {
                n = 2131230960;
                break;
            }
        }
        return context.getResources().getString(n, new Object[] { this.localizedDate });
    }
    
    @Override
    public String getSecondaryMessage(final Context context) {
        return null;
    }
    
    @Override
    public Advisory$Type getType() {
        return Advisory$Type.EXPIRY_NOTICE;
    }
}
