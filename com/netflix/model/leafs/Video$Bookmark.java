// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public final class Video$Bookmark implements JsonPopulator
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
        if (Log.isLoggable("Bookmark", 2)) {
            Log.v("Bookmark", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0126: {
                switch (s.hashCode()) {
                    case -1829827457: {
                        if (s.equals("bookmarkPosition")) {
                            n = 0;
                            break Label_0126;
                        }
                        break;
                    }
                    case 1959003007: {
                        if (s.equals("lastModified")) {
                            n = 1;
                            break Label_0126;
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
