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
import java.util.Map;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.NetflixApplication;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import java.util.List;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.Context;
import java.io.File;

class BookmarkStore$2 implements Runnable
{
    final /* synthetic */ BookmarkStore this$0;
    
    BookmarkStore$2(final BookmarkStore this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.saveBookmarkToFile();
    }
}
