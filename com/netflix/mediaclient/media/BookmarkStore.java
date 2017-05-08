// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import com.netflix.mediaclient.servicemgr.interface_.BasicVideo;
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

public class BookmarkStore
{
    private static final int MAX_BOOKMARKS_PER_PROFILE = 100;
    private static final String TAG = "nf_BookmarkStore";
    private BookmarkStore$BookmarkData mBookmarkData;
    private File mBookmarkStoreFile;
    private Context mContext;
    
    public BookmarkStore(final Context context) {
        this.mBookmarkData = new BookmarkStore$BookmarkData(this, null);
        this.init(context);
    }
    
    private void init(final Context context) {
        this.mBookmarkStoreFile = new File(context.getFilesDir() + "/bookmarkStore.json");
        new BackgroundTask().execute(new BookmarkStore$1(this, context));
    }
    
    private boolean isProfileStillValid(final String s, final List<UserProfile> list) {
        final Iterator<UserProfile> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (StringUtils.notEmptyAndEquals(iterator.next().getProfileGuid(), s)) {
                return true;
            }
        }
        return false;
    }
    
    private void persistBookmarkData() {
        new BackgroundTask().execute(new BookmarkStore$2(this));
    }
    
    private void saveBookmarkToFile() {
        synchronized (this) {
            final String json = NetflixApplication.getGson().toJson(this.mBookmarkData);
            Log.i("nf_BookmarkStore", "setBookmark saving to file result=%b data=%s", FileUtils.writeBytesToFile(this.mBookmarkStoreFile.getAbsolutePath(), json.getBytes()), json);
        }
    }
    
    private void setBookmarkNoPersist(final String s, final PlaybackBookmark playbackBookmark) {
        if (Log.isLoggable()) {
            Log.i("nf_BookmarkStore", "setBookmark videoId=" + playbackBookmark.mVideoId + " bookmarkTimeInSeconds=" + playbackBookmark.mBookmarkInSecond);
        }
        if (this.mBookmarkData.mBookmarkMap.get(s) == null) {
            this.mBookmarkData.mBookmarkMap.put(s, new HashMap<String, PlaybackBookmark>());
        }
        final Map<String, PlaybackBookmark> map = this.mBookmarkData.mBookmarkMap.get(s);
        this.trimSizeIfNeeded(map);
        map.put(playbackBookmark.mVideoId, playbackBookmark);
    }
    
    private void trimSizeIfNeeded(final Map<String, PlaybackBookmark> map) {
        Object o = null;
        Object o2 = null;
        long mBookmarkUpdateTimeInUTCMs = 2147483647L;
        if (map.size() > 100) {
            final Iterator<String> iterator = map.keySet().iterator();
            while (true) {
                o = o2;
                if (!iterator.hasNext()) {
                    break;
                }
                final String s = iterator.next();
                if (map.get(s).mBookmarkUpdateTimeInUTCMs >= mBookmarkUpdateTimeInUTCMs) {
                    continue;
                }
                mBookmarkUpdateTimeInUTCMs = map.get(s).mBookmarkUpdateTimeInUTCMs;
                o2 = s;
            }
        }
        if (o != null) {
            map.remove(o);
        }
    }
    
    public void createOrUpdateBookmark(final VideoDetails videoDetails, final String s) {
        if (videoDetails != null) {
            final Playable playable = videoDetails.getPlayable();
            if (playable != null) {
                FalkorVideo falkorVideo = null;
                if (playable instanceof FalkorVideo) {
                    falkorVideo = (FalkorVideo)playable;
                }
                int n;
                if (falkorVideo != null) {
                    n = falkorVideo.getBookmark().getBookmarkPosition();
                }
                else {
                    n = playable.getPlayableBookmarkPosition();
                }
                final long playableBookmarkUpdateTime = playable.getPlayableBookmarkUpdateTime();
                final PlaybackBookmark bookmark = this.getBookmark(s, playable.getPlayableId());
                int n2;
                if (bookmark == null) {
                    Log.i("nf_BookmarkStore", "createOrUpdateBookmark bookmarkStore has no bookmark");
                    n2 = 1;
                }
                else if (bookmark.mBookmarkUpdateTimeInUTCMs < playableBookmarkUpdateTime) {
                    Log.i("nf_BookmarkStore", "createOrUpdateBookmark bookmarkStore is older");
                    n2 = 1;
                }
                else {
                    Log.i("nf_BookmarkStore", "createOrUpdateBookmark bookmarkStore is newer");
                    n2 = 0;
                }
                if (n2 != 0) {
                    Log.i("nf_BookmarkStore", "createOrUpdateBookmark calling BookmarkStore.setBookmark time=%d", n);
                    this.setBookmark(s, new PlaybackBookmark(n, playableBookmarkUpdateTime, playable.getPlayableId()));
                }
            }
        }
    }
    
    public PlaybackBookmark getBookmark(final String s, final String s2) {
        while (true) {
            Object mContext = null;
            Label_0114: {
                synchronized (this) {
                    mContext = this.mContext;
                    PlaybackBookmark playbackBookmark;
                    if (mContext == null) {
                        playbackBookmark = null;
                    }
                    else {
                        final Map<String, PlaybackBookmark> map = this.mBookmarkData.mBookmarkMap.get(s);
                        if (map == null) {
                            playbackBookmark = null;
                        }
                        else {
                            mContext = (playbackBookmark = map.get(s2));
                            if (Log.isLoggable()) {
                                if (mContext == null) {
                                    break Label_0114;
                                }
                                Log.i("nf_BookmarkStore", "getBookmark videoId=" + s2 + " bookmarkTimeInSeconds=" + ((PlaybackBookmark)mContext).mBookmarkInSecond);
                                playbackBookmark = (PlaybackBookmark)mContext;
                            }
                        }
                    }
                    return playbackBookmark;
                }
            }
            Log.i("nf_BookmarkStore", "getBookmark no bookmark for videoId=" + s2);
            return (PlaybackBookmark)mContext;
        }
    }
    
    public void onCWVideosFetched(final List<CWVideo> list, final String s) {
        while (true) {
        Label_0122_Outer:
            while (true) {
            Label_0251:
                while (true) {
                    Label_0245: {
                        Label_0242: {
                            synchronized (this) {
                                Object mContext = this.mContext;
                                if (mContext != null && list != null && s != null) {
                                    final Iterator<CWVideo> iterator = list.iterator();
                                    boolean b = false;
                                    if (iterator.hasNext()) {
                                        mContext = iterator.next();
                                        if (Log.isLoggable()) {
                                            Log.i("nf_BookmarkStore", "-> cwVideo title=" + ((BasicVideo)mContext).getTitle());
                                        }
                                        final PlaybackBookmark bookmark = this.getBookmark(s, ((Playable)mContext).getPlayableId());
                                        int n;
                                        if (bookmark == null) {
                                            Log.i("nf_BookmarkStore", "got a new bookmark");
                                            n = 1;
                                        }
                                        else {
                                            final long seconds = TimeUnit.MILLISECONDS.toSeconds(bookmark.mBookmarkUpdateTimeInUTCMs - ((Playable)mContext).getPlayableBookmarkUpdateTime());
                                            Log.i("nf_BookmarkStore", "bookMarkStoreTimeIsNewBySeconds=" + seconds);
                                            if (seconds >= 0L) {
                                                break Label_0245;
                                            }
                                            n = 1;
                                        }
                                        if (n != 0) {
                                            this.setBookmarkNoPersist(s, new PlaybackBookmark(((Playable)mContext).getPlayableBookmarkPosition(), ((Playable)mContext).getPlayableBookmarkUpdateTime(), ((Playable)mContext).getPlayableId()));
                                            b = true;
                                            break Label_0251;
                                        }
                                        break Label_0242;
                                    }
                                    else if (b) {
                                        this.persistBookmarkData();
                                    }
                                }
                                return;
                            }
                        }
                        break Label_0251;
                    }
                    int n = 0;
                    continue;
                }
                continue Label_0122_Outer;
            }
        }
    }
    
    public void setBookmark(final String s, final PlaybackBookmark playbackBookmark) {
        while (true) {
            Label_0039: {
                synchronized (this) {
                    if (this.mContext != null) {
                        if (s != null && playbackBookmark != null) {
                            break Label_0039;
                        }
                        Log.e("nf_BookmarkStore", "setBookmark not valid data");
                    }
                    return;
                }
            }
            final String s2;
            this.setBookmarkNoPersist(s2, playbackBookmark);
            this.persistBookmarkData();
        }
    }
    
    public void updateBookmarkIfExists(final String s, final Video$Bookmark video$Bookmark, final String s2) {
        if (s != null && video$Bookmark != null) {
            final int bookmarkPosition = video$Bookmark.getBookmarkPosition();
            final long lastModified = video$Bookmark.getLastModified();
            final PlaybackBookmark bookmark = this.getBookmark(s2, s);
            if (bookmark != null) {
                Log.i("nf_BookmarkStore", "updateBookmarkIfExists playableId=%s falkorBookmarkPosition=%d falkorBookmarkUpdateTime=%d", s, bookmarkPosition, lastModified);
                if (bookmark.mBookmarkUpdateTimeInUTCMs < lastModified) {
                    Log.i("nf_BookmarkStore", "updateBookmarkIfExists updating");
                    this.setBookmark(s2, new PlaybackBookmark(bookmarkPosition, lastModified, s));
                    return;
                }
                Log.i("nf_BookmarkStore", "updateBookmarkIfExists storeTime=%d falkorBookmarkUpdateTime=%d", bookmark.mBookmarkUpdateTimeInUTCMs, lastModified);
            }
        }
    }
    
    public void updateValidProfiles(final List<UserProfile> list) {
        // monitorenter(this)
        Label_0174: {
            if (list != null) {
                ArrayList<String> list2;
                try {
                    if (list.size() <= 0 || this.mBookmarkData == null || this.mBookmarkData.mBookmarkMap == null) {
                        break Label_0174;
                    }
                    list2 = new ArrayList<String>();
                    for (final Map.Entry<String, Map<String, PlaybackBookmark>> entry : this.mBookmarkData.mBookmarkMap.entrySet()) {
                        if (!this.isProfileStillValid(entry.getKey(), list)) {
                            list2.add(entry.getKey());
                        }
                    }
                }
                finally {
                }
                // monitorexit(this)
                final Iterator<Object> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    this.mBookmarkData.mBookmarkMap.remove(iterator2.next());
                }
                if (list2.size() > 0) {
                    this.persistBookmarkData();
                }
            }
        }
    }
    // monitorexit(this)
}
