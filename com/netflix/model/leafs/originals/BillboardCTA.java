// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.originals;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class BillboardCTA implements JsonPopulator
{
    private static final String TAG = "BillboardCTA";
    private String bookmarkPosition;
    private Boolean ignoreBookmark;
    private String name;
    private String sequenceNumber;
    private Boolean suppressPostPlay;
    private String type;
    private String videoId;
    
    public BillboardCTA(final JsonElement jsonElement) {
        this.populate(jsonElement);
    }
    
    public BillboardCTA(final String bookmarkPosition, final Boolean ignoreBookmark, final String name, final String sequenceNumber, final Boolean suppressPostPlay, final String type, final String videoId) {
        this.bookmarkPosition = bookmarkPosition;
        this.ignoreBookmark = ignoreBookmark;
        this.name = name;
        this.sequenceNumber = sequenceNumber;
        this.suppressPostPlay = suppressPostPlay;
        this.type = type;
        this.videoId = videoId;
    }
    
    public static List<BillboardCTA> getListOfActions(final JsonElement jsonElement) {
        final ArrayList<BillboardCTA> list = new ArrayList<BillboardCTA>();
        final Iterator<JsonElement> iterator = jsonElement.getAsJsonArray().iterator();
        while (iterator.hasNext()) {
            list.add(new BillboardCTA(iterator.next()));
        }
        return list;
    }
    
    public String getBookmarkPosition() {
        return this.bookmarkPosition;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSequenceNumber() {
        return this.sequenceNumber;
    }
    
    public Boolean getSuppressPostPlay() {
        return this.suppressPostPlay;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getVideoId() {
        return this.videoId;
    }
    
    public boolean ignoreBookmark() {
        return this.ignoreBookmark;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("BillboardCTA", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0162: {
                switch (s.hashCode()) {
                    case 3373707: {
                        if (s.equals("name")) {
                            n = 0;
                            break Label_0162;
                        }
                        break;
                    }
                    case 452782838: {
                        if (s.equals("videoId")) {
                            n = 1;
                            break Label_0162;
                        }
                        break;
                    }
                    case 1144180169: {
                        if (s.equals("suppressPostPlay")) {
                            n = 2;
                            break Label_0162;
                        }
                        break;
                    }
                    case 3575610: {
                        if (s.equals("type")) {
                            n = 3;
                            break Label_0162;
                        }
                        break;
                    }
                    case 994317608: {
                        if (s.equals("ignoreBookmark")) {
                            n = 4;
                            break Label_0162;
                        }
                        break;
                    }
                    case -1353995670: {
                        if (s.equals("sequenceNumber")) {
                            n = 5;
                            break Label_0162;
                        }
                        break;
                    }
                    case -1829827457: {
                        if (s.equals("bookmarkPosition")) {
                            n = 6;
                            break Label_0162;
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
                    this.name = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 1: {
                    this.videoId = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 2: {
                    this.suppressPostPlay = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 3: {
                    this.type = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 4: {
                    this.ignoreBookmark = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 5: {
                    this.sequenceNumber = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 6: {
                    this.bookmarkPosition = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
            }
        }
    }
}
