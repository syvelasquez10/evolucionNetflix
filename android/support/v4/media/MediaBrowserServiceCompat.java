// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.support.v4.os.ResultReceiver;
import android.support.v4.os.BuildCompat;
import android.os.Build$VERSION;
import android.content.Intent;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import android.support.v4.util.Pair;
import java.util.List;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.app.Service;

public abstract class MediaBrowserServiceCompat extends Service
{
    static final boolean DEBUG;
    public static final String KEY_MEDIA_ITEM = "media_item";
    static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
    static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    static final String TAG = "MBServiceCompat";
    final ArrayMap<IBinder, MediaBrowserServiceCompat$ConnectionRecord> mConnections;
    MediaBrowserServiceCompat$ConnectionRecord mCurConnection;
    final MediaBrowserServiceCompat$ServiceHandler mHandler;
    private MediaBrowserServiceCompat$MediaBrowserServiceImpl mImpl;
    MediaSessionCompat$Token mSession;
    
    static {
        DEBUG = Log.isLoggable("MBServiceCompat", 3);
    }
    
    public MediaBrowserServiceCompat() {
        this.mConnections = new ArrayMap<IBinder, MediaBrowserServiceCompat$ConnectionRecord>();
        this.mHandler = new MediaBrowserServiceCompat$ServiceHandler(this);
    }
    
    void addSubscription(final String s, final MediaBrowserServiceCompat$ConnectionRecord mediaBrowserServiceCompat$ConnectionRecord, final IBinder binder, final Bundle bundle) {
        List<Pair<IBinder, Bundle>> list = mediaBrowserServiceCompat$ConnectionRecord.subscriptions.get(s);
        if (list == null) {
            list = new ArrayList<Pair<IBinder, Bundle>>();
        }
        for (final Pair<IBinder, Bundle> pair : list) {
            if (binder == pair.first && MediaBrowserCompatUtils.areSameOptions(bundle, pair.second)) {
                return;
            }
        }
        list.add(new Pair<IBinder, Bundle>(binder, bundle));
        mediaBrowserServiceCompat$ConnectionRecord.subscriptions.put(s, list);
        this.performLoadChildren(s, mediaBrowserServiceCompat$ConnectionRecord, bundle);
    }
    
    List<MediaBrowserCompat$MediaItem> applyOptions(final List<MediaBrowserCompat$MediaItem> list, final Bundle bundle) {
        List<MediaBrowserCompat$MediaItem> list2;
        if (list == null) {
            list2 = null;
        }
        else {
            final int int1 = bundle.getInt("android.media.browse.extra.PAGE", -1);
            final int int2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
            if (int1 == -1) {
                list2 = list;
                if (int2 == -1) {
                    return list2;
                }
            }
            final int n = int2 * int1;
            final int n2 = n + int2;
            if (int1 < 0 || int2 < 1 || n >= list.size()) {
                return (List<MediaBrowserCompat$MediaItem>)Collections.EMPTY_LIST;
            }
            int size;
            if ((size = n2) > list.size()) {
                size = list.size();
            }
            return list.subList(n, size);
        }
        return list2;
    }
    
    public void dump(final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    public final Bundle getBrowserRootHints() {
        return this.mImpl.getBrowserRootHints();
    }
    
    public MediaSessionCompat$Token getSessionToken() {
        return this.mSession;
    }
    
    boolean isValidPackage(final String s, int i) {
        if (s != null) {
            final String[] packagesForUid = this.getPackageManager().getPackagesForUid(i);
            int length;
            for (length = packagesForUid.length, i = 0; i < length; ++i) {
                if (packagesForUid[i].equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void notifyChildrenChanged(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        this.mImpl.notifyChildrenChanged(s, null);
    }
    
    public void notifyChildrenChanged(final String s, final Bundle bundle) {
        if (s == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        }
        this.mImpl.notifyChildrenChanged(s, bundle);
    }
    
    public IBinder onBind(final Intent intent) {
        return this.mImpl.onBind(intent);
    }
    
    public void onCreate() {
        super.onCreate();
        if (Build$VERSION.SDK_INT >= 24 || BuildCompat.isAtLeastN()) {
            this.mImpl = new MediaBrowserServiceCompat$MediaBrowserServiceImplApi24(this);
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaBrowserServiceCompat$MediaBrowserServiceImplApi23(this);
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaBrowserServiceCompat$MediaBrowserServiceImplApi21(this);
        }
        else {
            this.mImpl = new MediaBrowserServiceCompat$MediaBrowserServiceImplBase(this);
        }
        this.mImpl.onCreate();
    }
    
    public abstract MediaBrowserServiceCompat$BrowserRoot onGetRoot(final String p0, final int p1, final Bundle p2);
    
    public abstract void onLoadChildren(final String p0, final MediaBrowserServiceCompat$Result<List<MediaBrowserCompat$MediaItem>> p1);
    
    public void onLoadChildren(final String s, final MediaBrowserServiceCompat$Result<List<MediaBrowserCompat$MediaItem>> mediaBrowserServiceCompat$Result, final Bundle bundle) {
        mediaBrowserServiceCompat$Result.setFlags(1);
        this.onLoadChildren(s, mediaBrowserServiceCompat$Result);
    }
    
    public void onLoadItem(final String s, final MediaBrowserServiceCompat$Result<MediaBrowserCompat$MediaItem> mediaBrowserServiceCompat$Result) {
        mediaBrowserServiceCompat$Result.setFlags(2);
        mediaBrowserServiceCompat$Result.sendResult(null);
    }
    
    void performLoadChildren(final String s, final MediaBrowserServiceCompat$ConnectionRecord mCurConnection, final Bundle bundle) {
        final MediaBrowserServiceCompat$1 mediaBrowserServiceCompat$1 = new MediaBrowserServiceCompat$1(this, s, mCurConnection, s, bundle);
        this.mCurConnection = mCurConnection;
        if (bundle == null) {
            this.onLoadChildren(s, mediaBrowserServiceCompat$1);
        }
        else {
            this.onLoadChildren(s, mediaBrowserServiceCompat$1, bundle);
        }
        this.mCurConnection = null;
        if (!mediaBrowserServiceCompat$1.isDone()) {
            throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + mCurConnection.pkg + " id=" + s);
        }
    }
    
    void performLoadItem(final String s, final MediaBrowserServiceCompat$ConnectionRecord mCurConnection, final ResultReceiver resultReceiver) {
        final MediaBrowserServiceCompat$2 mediaBrowserServiceCompat$2 = new MediaBrowserServiceCompat$2(this, s, resultReceiver);
        this.mCurConnection = mCurConnection;
        this.onLoadItem(s, mediaBrowserServiceCompat$2);
        this.mCurConnection = null;
        if (!mediaBrowserServiceCompat$2.isDone()) {
            throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + s);
        }
    }
    
    boolean removeSubscription(final String s, final MediaBrowserServiceCompat$ConnectionRecord mediaBrowserServiceCompat$ConnectionRecord, final IBinder binder) {
        if (binder == null) {
            return mediaBrowserServiceCompat$ConnectionRecord.subscriptions.remove(s) != null;
        }
        final List<Pair<IBinder, Bundle>> list = mediaBrowserServiceCompat$ConnectionRecord.subscriptions.get(s);
        boolean b2;
        if (list != null) {
            final Iterator<Pair<IBinder, Bundle>> iterator = list.iterator();
            boolean b = false;
            while (iterator.hasNext()) {
                if (binder == iterator.next().first) {
                    iterator.remove();
                    b = true;
                }
            }
            b2 = b;
            if (list.size() == 0) {
                mediaBrowserServiceCompat$ConnectionRecord.subscriptions.remove(s);
                b2 = b;
            }
        }
        else {
            b2 = false;
        }
        return b2;
    }
    
    public void setSessionToken(final MediaSessionCompat$Token mediaSessionCompat$Token) {
        if (mediaSessionCompat$Token == null) {
            throw new IllegalArgumentException("Session token may not be null.");
        }
        if (this.mSession != null) {
            throw new IllegalStateException("The session token has already been set.");
        }
        this.mSession = mediaSessionCompat$Token;
        this.mImpl.setSessionToken(mediaSessionCompat$Token);
    }
}
