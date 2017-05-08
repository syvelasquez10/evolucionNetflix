// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import java.util.HashMap;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import java.util.Map;

class BookmarkStore$BookmarkData
{
    @SerializedName("bookmarks")
    public Map<String, Map<String, PlaybackBookmark>> mBookmarkMap;
    final /* synthetic */ BookmarkStore this$0;
    
    private BookmarkStore$BookmarkData(final BookmarkStore this$0) {
        this.this$0 = this$0;
        this.mBookmarkMap = new HashMap<String, Map<String, PlaybackBookmark>>();
    }
}
