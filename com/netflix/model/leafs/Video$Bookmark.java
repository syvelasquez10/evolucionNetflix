// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public final class Video$Bookmark implements JsonMerger, JsonPopulator
{
    private static final String TAG = "Bookmark";
    private int bookmarkPosition;
    private long lastModified;
    
    public void deepCopy(final Video$Bookmark video$Bookmark) {
        this.bookmarkPosition = video$Bookmark.bookmarkPosition;
        this.lastModified = video$Bookmark.lastModified;
    }
    
    public int getBookmarkPosition() {
        return this.bookmarkPosition;
    }
    
    public long getLastModified() {
        return this.lastModified;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Bookmark", "Populating with: " + asJsonObject);
        }
        final int bookmarkPosition = this.bookmarkPosition;
        final long lastModified = this.lastModified;
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0134: {
                switch (s.hashCode()) {
                    case -1829827457: {
                        if (s.equals("bookmarkPosition")) {
                            n = 0;
                            break Label_0134;
                        }
                        break;
                    }
                    case 1959003007: {
                        if (s.equals("lastModified")) {
                            n = 1;
                            break Label_0134;
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
                    this.bookmarkPosition = jsonElement2.getAsInt();
                    continue;
                }
                case 1: {
                    this.lastModified = jsonElement2.getAsLong();
                    continue;
                }
            }
        }
        if (lastModified > this.lastModified) {
            Log.d("Bookmark", String.format("restoring bookmark and time (%d - %d) to older values (%d - %d)", this.bookmarkPosition, this.lastModified, bookmarkPosition, lastModified));
            this.bookmarkPosition = bookmarkPosition;
            this.lastModified = lastModified;
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Bookmark", "Populating with: " + jsonParser);
        }
        final int bookmarkPosition = this.bookmarkPosition;
        final long lastModified = this.lastModified;
        switch (s) {
            default: {
                return false;
            }
            case "bookmarkPosition": {
                this.bookmarkPosition = jsonParser.getValueAsInt();
                break;
            }
            case "lastModified": {
                this.lastModified = jsonParser.getValueAsLong();
                break;
            }
        }
        if (lastModified > this.lastModified) {
            Log.d("Bookmark", String.format("restoring bookmark and time (%d - %d) to older values (%d - %d)", this.bookmarkPosition, this.lastModified, bookmarkPosition, lastModified));
            this.bookmarkPosition = bookmarkPosition;
            this.lastModified = lastModified;
        }
        return true;
    }
    
    public void setBookmarkPosition(final int bookmarkPosition) {
        this.bookmarkPosition = bookmarkPosition;
    }
    
    public void setLastModified(final long lastModified) {
        this.lastModified = lastModified;
    }
    
    @Override
    public String toString() {
        return "Bookmark [bookmarkPosition=" + this.bookmarkPosition + ", lastModified=" + this.lastModified + "]";
    }
}
