// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import java.util.ArrayList;
import com.netflix.model.leafs.Video$Bookmark;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.Iterator;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import java.util.List;
import android.content.Context;
import java.io.File;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import java.util.Map;
import java.util.HashMap;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.NetflixApplication;

class BookmarkStore$1 implements Runnable
{
    final /* synthetic */ BookmarkStore this$0;
    
    BookmarkStore$1(final BookmarkStore this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        synchronized (BookmarkStore.class) {
            while (true) {
                try {
                    if (this.this$0.mBookmarkStoreFile.exists()) {
                        this.this$0.mBookmarkData = NetflixApplication.getGson().fromJson(StringUtils.byteArrayToString(FileUtils.readFileToByteArray(this.this$0.mBookmarkStoreFile), "utf-8"), BookmarkStore$BookmarkData.class);
                    }
                    if (this.this$0.mBookmarkData == null || this.this$0.mBookmarkData.mBookmarkMap == null) {
                        this.this$0.mBookmarkData = new BookmarkStore$BookmarkData(this.this$0, null);
                        this.this$0.mBookmarkData.mBookmarkMap = new HashMap<String, Map<String, PlaybackBookmark>>();
                    }
                }
                catch (IOException ex) {
                    Log.e("nf_BookmarkStore", ex, "init error", new Object[0]);
                    continue;
                }
                break;
            }
        }
    }
}
