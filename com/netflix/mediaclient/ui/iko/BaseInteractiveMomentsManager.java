// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.facebook.device.yearclass.YearClass;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.view.TextureView;
import android.content.Context;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.util.ThreadUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import java.util.Map;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import java.util.HashSet;

public abstract class BaseInteractiveMomentsManager implements InteractiveMomentsManager
{
    private static final String TAG = "BaseInteractiveMomentsManager";
    HashSet<String> assetsRequestSet;
    HashSet<String> assetsResponseSet;
    private int failureCount;
    protected PlayerFragment fragment;
    private BitmapFactory$Options options;
    private BitmapFactory$Options optionsWithSubSampling;
    private int resourceRequestCounter;
    private int resourceResponseCounter;
    protected Map<String, LocalCachedFileMetadata> resourceToLocalCacheFileMap;
    protected ServiceManager svcManager;
    protected HashMap<String, MediaPlayerWrapper> urlToMediaPlayerMap;
    
    public BaseInteractiveMomentsManager() {
        this.resourceToLocalCacheFileMap = new HashMap<String, LocalCachedFileMetadata>();
        this.options = new BitmapFactory$Options();
        this.optionsWithSubSampling = new BitmapFactory$Options();
        this.assetsRequestSet = new HashSet<String>();
        this.assetsResponseSet = new HashSet<String>();
    }
    
    protected boolean areResourcesCached() {
        return this.assetsResponseSet.size() >= this.assetsRequestSet.size();
    }
    
    protected void cacheResources(final String s) {
        if (this.fragment == null || !this.fragment.isActivityValid()) {
            Log.d("BaseInteractiveMomentsManager", "Player Fragment is null or activity is not valid.");
        }
        else {
            if (StringUtils.isEmpty(s)) {
                Log.e("BaseInteractiveMomentsManager", "Invalid request to cache resource for an empty or null url.");
                return;
            }
            if (this.svcManager != null) {
                if (Log.isLoggable()) {
                    Log.d("BaseInteractiveMomentsManager", "Fetching and caching resource " + s);
                }
                this.assetsRequestSet.add(s);
                this.svcManager.fetchAndCacheResource(s, IClientLogging$AssetType.interactiveContent, new BaseInteractiveMomentsManager$1(this));
                return;
            }
            if (Log.isLoggable()) {
                Log.d("BaseInteractiveMomentsManager", "Service manager is null. Cannot fetch resource - " + s);
            }
        }
    }
    
    protected void cachingResourcesComplete() {
    }
    
    protected Bitmap fetchImageFromCache(final String s, final BitmapFactory$Options bitmapFactory$Options) {
        ThreadUtils.assertNotOnMain();
        if (StringUtils.isEmpty(s)) {
            Log.d("BaseInteractiveMomentsManager", " Empty or null request url to load image from cache");
            return null;
        }
        final LocalCachedFileMetadata localCachedFileMetadata = this.resourceToLocalCacheFileMap.get(s);
        if (localCachedFileMetadata == null || StringUtils.isEmpty(localCachedFileMetadata.getLocalUrl())) {
            Log.d("BaseInteractiveMomentsManager", "Resource not downloaded to disk cache. Ignore request to load image.");
            return null;
        }
        final String localUrl = localCachedFileMetadata.getLocalUrl();
        if (Log.isLoggable()) {
            Log.d("BaseInteractiveMomentsManager", "Loading image from cache for url " + s + " Local url = " + localUrl);
        }
        final File file = new File(localUrl);
        if (!file.exists()) {
            return null;
        }
        try {
            return BitmapFactory.decodeByteArray(FileUtils.readFileToByteArray(file), (int)localCachedFileMetadata.getByteOffset(), (int)localCachedFileMetadata.getByteSize(), bitmapFactory$Options);
        }
        catch (IOException ex2) {
            Log.e("BaseInteractiveMomentsManager", "Error loading image from cache for url " + localUrl);
            return null;
        }
        catch (Exception ex) {
            Log.e("BaseInteractiveMomentsManager", "Error loading image from cache for url " + localUrl + " Exception - " + ex.getMessage());
            return null;
        }
        return null;
    }
    
    protected Bitmap fetchImageFromCache(final String s, final boolean b) {
        BitmapFactory$Options bitmapFactory$Options;
        if (b) {
            bitmapFactory$Options = this.optionsWithSubSampling;
        }
        else {
            bitmapFactory$Options = this.options;
        }
        return this.fetchImageFromCache(s, bitmapFactory$Options);
    }
    
    public Context getContext() {
        if (this.fragment == null) {
            return null;
        }
        return (Context)this.fragment.getNetflixActivity();
    }
    
    protected LocalCachedFileMetadata getLocalCachedFileMetadata(final String s) {
        if (StringUtils.isEmpty(s) || this.resourceToLocalCacheFileMap == null) {
            Log.d("BaseInteractiveMomentsManager", "Request for a null url from resources map.");
            return null;
        }
        return this.resourceToLocalCacheFileMap.get(s);
    }
    
    protected MediaPlayerWrapper initMediaPlayer(final String s, final float n, final boolean b, final BaseInteractiveMomentsManager$PlaybackCompleteListener baseInteractiveMomentsManager$PlaybackCompleteListener) {
        if (this.urlToMediaPlayerMap == null) {
            this.urlToMediaPlayerMap = new HashMap<String, MediaPlayerWrapper>();
        }
        int n2;
        if (b) {
            n2 = -1;
        }
        else {
            n2 = 0;
        }
        final MediaPlayerWrapper mediaPlayerWrapper = new MediaPlayerWrapper(null, b, n2, n, IClientLogging$AssetType.interactiveContent, new BaseInteractiveMomentsManager$2(this, s, baseInteractiveMomentsManager$PlaybackCompleteListener));
        this.urlToMediaPlayerMap.put(s, mediaPlayerWrapper);
        return mediaPlayerWrapper;
    }
    
    protected boolean isActivityInvalid() {
        if (this.fragment == null || !this.fragment.isActivityValid()) {
            Log.d("BaseInteractiveMomentsManager", "Fragment is either null or activity is finishing or destroyed");
            return true;
        }
        return false;
    }
    
    public void playMediaPlayerAudio(final String s, final float n, final boolean b, final BaseInteractiveMomentsManager$PlaybackCompleteListener baseInteractiveMomentsManager$PlaybackCompleteListener) {
        if (Log.isLoggable()) {
            Log.d("BaseInteractiveMomentsManager", "playMediaPlayerAudio: url = " + s + " volume = " + n);
        }
        if (this.isActivityInvalid()) {
            return;
        }
        if (StringUtils.isEmpty(s)) {
            Log.e("BaseInteractiveMomentsManager", "Not starting media player for background sound");
            baseInteractiveMomentsManager$PlaybackCompleteListener.onComplete(s);
            return;
        }
        final LocalCachedFileMetadata localCachedFileMetadata = this.resourceToLocalCacheFileMap.get(s);
        if (localCachedFileMetadata == null) {
            Log.e("BaseInteractiveMomentsManager", "Media player audio is cached locally, but metadata information is null.");
            baseInteractiveMomentsManager$PlaybackCompleteListener.onComplete(s);
            return;
        }
        final MediaPlayerWrapper initMediaPlayer = this.initMediaPlayer(s, n, b, baseInteractiveMomentsManager$PlaybackCompleteListener);
        initMediaPlayer.setDataSource(localCachedFileMetadata.getLocalUrl(), localCachedFileMetadata.getByteOffset(), localCachedFileMetadata.getByteSize());
        initMediaPlayer.initializeMediaPlayer();
    }
    
    public void releaseBitmaps(final Bitmap... array) {
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                final Bitmap bitmap = array[i];
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }
    
    protected void resetCounters(final int resourceRequestCounter) {
        if (Log.isLoggable()) {
            Log.d("BaseInteractiveMomentsManager", "resetCounters: size = " + resourceRequestCounter);
        }
        this.resourceRequestCounter = resourceRequestCounter;
        this.resourceResponseCounter = 0;
        this.failureCount = 0;
        this.assetsRequestSet.clear();
        this.assetsResponseSet.clear();
    }
    
    public void setBitmapFactoryOptions(final Context context) {
        int n = 1;
        if (context == null) {
            return;
        }
        int n2;
        if (DeviceUtils.getScreenResolutionDpi(context) <= 160) {
            n2 = 2;
        }
        else {
            n2 = 1;
        }
        final Integer value = YearClass.get(this.getContext());
        if (value != null && Log.isLoggable()) {
            Log.d("BaseInteractiveMomentsManager", "YearClass is - " + value);
        }
        if (value <= 2012) {
            n = 2;
        }
        final int inSampleSize = n2 * n;
        if (Log.isLoggable()) {
            Log.d("BaseInteractiveMomentsManager", "setBitmapFactoryOptions: inSampleSize = " + inSampleSize);
        }
        this.optionsWithSubSampling.inSampleSize = inSampleSize;
    }
    
    public void stopAudioPlayback(final String s) {
        if (StringUtils.isEmpty(s)) {
            if (Log.isLoggable()) {
                Log.d("BaseInteractiveMomentsManager", "stopAudioPlayback: url is null or empty");
            }
        }
        else if (this.urlToMediaPlayerMap == null || this.urlToMediaPlayerMap.isEmpty()) {
            if (Log.isLoggable()) {
                Log.d("BaseInteractiveMomentsManager", "stopAudioPlayback: urlToMediaPlayerMap is null or empty");
            }
        }
        else {
            final MediaPlayerWrapper mediaPlayerWrapper = this.urlToMediaPlayerMap.get(s);
            if (mediaPlayerWrapper != null) {
                mediaPlayerWrapper.clearCallbacks();
                mediaPlayerWrapper.releaseMediaPlayer();
            }
        }
    }
}
