// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

import java.util.Collections;
import java.util.Iterator;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$ManifestRequestFlavor;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Looper;
import android.os.HandlerThread;
import java.util.Map;
import android.os.Handler;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import java.util.List;

public class NfManifestCache implements NfManifestCacheInterface
{
    private static final int DEFAULT_MANIFEST_COUNT = 20;
    private static final int DEFAULT_MANIFEST_COUNT_LOWMEM = 10;
    private static final String TAG;
    private List<Long> mAbortedMovies;
    private BladeRunnerClient mBladeRunnerClient;
    private int mCachedManifestCount;
    private Handler mCallbackHandler;
    private Map<Long, NfManifest> mManifestMap;
    private Map<Long, NfManifestCache$PendingMovie> mPendingMovies;
    private List<NfManifest> mPriorityList;
    private Handler mWorkHandler;
    private HandlerThread mWorkerThread;
    
    static {
        TAG = NfManifestCache.class.getSimpleName();
    }
    
    public NfManifestCache(final Handler handler, final Looper looper, final BladeRunnerClient mBladeRunnerClient, final boolean b) {
        this.mCachedManifestCount = 20;
        this.mManifestMap = new HashMap<Long, NfManifest>();
        this.mAbortedMovies = new ArrayList<Long>();
        this.mPendingMovies = new HashMap<Long, NfManifestCache$PendingMovie>();
        this.mPriorityList = new ArrayList<NfManifest>();
        this.mCallbackHandler = new Handler(looper);
        this.mBladeRunnerClient = mBladeRunnerClient;
        (this.mWorkerThread = new HandlerThread("NfManifestCacheWorkerThread", 0)).start();
        this.mWorkHandler = new Handler(this.mWorkerThread.getLooper());
        if (b) {
            this.mCachedManifestCount = 10;
        }
        NfManifest.configureManifestLife(72000000L);
    }
    
    private void fetchManifest(final Long n, final NfManifestCachePlaybackInterface$ManifestCacheCallback nfManifestCachePlaybackInterface$ManifestCacheCallback) {
        final NfManifestCache$PendingMovie nfManifestCache$PendingMovie = this.mPendingMovies.get(n);
        if (nfManifestCache$PendingMovie != null) {
            nfManifestCache$PendingMovie.addCallback(nfManifestCachePlaybackInterface$ManifestCacheCallback);
            if (Log.isLoggable()) {
                Log.d(NfManifestCache.TAG, "fetchManifests is pending " + n);
            }
            return;
        }
        this.mBladeRunnerClient.fetchManifests(new String[] { Long.toString(n) }, IBladeRunnerClient$ManifestRequestFlavor.STANDARD, new NfManifestCache$7(this, n, nfManifestCachePlaybackInterface$ManifestCacheCallback));
    }
    
    private void notifyFetchManifestResult(final Long n, final NfManifest nfManifest, final NfManifestCachePlaybackInterface$ManifestCacheCallback nfManifestCachePlaybackInterface$ManifestCacheCallback) {
        this.mCallbackHandler.post((Runnable)new NfManifestCache$9(this, n, nfManifest, nfManifestCachePlaybackInterface$ManifestCacheCallback));
    }
    
    private void prepareManifests(final List<Triple<Long, Integer, PlayContext>> list) {
        final ArrayList<Long> list2 = new ArrayList<Long>();
        for (final Triple<Long, Integer, PlayContext> triple : list) {
            final Long n = (Long)triple.first;
            if (this.mPendingMovies.get(n) != null) {
                if (Log.isLoggable()) {
                    Log.d(NfManifestCache.TAG, "skip pending movie manifest " + n);
                }
                this.mPendingMovies.get(n).updatePriority((int)triple.second);
            }
            else if (this.mManifestMap.get(n) != null) {
                if (Log.isLoggable()) {
                    Log.d(NfManifestCache.TAG, "skip cached movie manifest" + n);
                }
                this.mManifestMap.get(n).setPriority((int)triple.second);
            }
            else {
                list2.add(n);
                this.mPendingMovies.put(n, new NfManifestCache$PendingMovie(this, (int)triple.second, null));
                if (!Log.isLoggable()) {
                    continue;
                }
                Log.d(NfManifestCache.TAG, "will prepare for movie " + n);
            }
        }
        if (list2.size() == 0) {
            Log.d(NfManifestCache.TAG, "prepare does need fetch manifest");
            return;
        }
        final String[] array = new String[list2.size()];
        final Iterator<Object> iterator2 = list2.iterator();
        int n2 = 0;
        while (iterator2.hasNext()) {
            array[n2] = Long.toString(iterator2.next());
            ++n2;
        }
        this.mBladeRunnerClient.fetchManifests(array, IBladeRunnerClient$ManifestRequestFlavor.PREFETCH, new NfManifestCache$8(this, list2));
    }
    
    private void processManifestResponse(final JSONObject jsonObject) {
        for (final NfManifest nfManifest : NfManifest.parseManifestResponse(jsonObject)) {
            if (nfManifest == null) {
                if (!Log.isLoggable()) {
                    continue;
                }
                Log.d(NfManifestCache.TAG, "M-CACHE, processManifestResponse has error");
            }
            else {
                this.mManifestMap.put(nfManifest.getMovieId(), nfManifest);
                this.mPriorityList.add(nfManifest);
                if (!Log.isLoggable()) {
                    continue;
                }
                Log.d(NfManifestCache.TAG, "M-CACHE, add " + nfManifest.getMovieId());
            }
        }
    }
    
    private void processPendingMovieList(final Long n, final NfManifest nfManifest) {
        final NfManifestCache$PendingMovie nfManifestCache$PendingMovie = this.mPendingMovies.remove(n);
        if (nfManifestCache$PendingMovie != null) {
            final List access$1300 = nfManifestCache$PendingMovie.mCallbacks;
            if (access$1300 != null) {
                final Iterator<NfManifestCachePlaybackInterface$ManifestCacheCallback> iterator = access$1300.iterator();
                while (iterator.hasNext()) {
                    this.notifyFetchManifestResult(n, nfManifest, iterator.next());
                }
            }
        }
    }
    
    private void processPrefetchFailure(final List<Long> list) {
        final Iterator<Long> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.processPendingMovieList(iterator.next(), null);
        }
    }
    
    private void processPrefetchResponse(final List<Long> list, final JSONObject jsonObject) {
        this.processManifestResponse(jsonObject);
        for (final Long n : list) {
            final NfManifest nfManifest = this.mManifestMap.get(n);
            final NfManifestCache$PendingMovie nfManifestCache$PendingMovie = this.mPendingMovies.get(n);
            if (nfManifestCache$PendingMovie != null && nfManifest != null) {
                nfManifest.setPriority(nfManifestCache$PendingMovie.getmPriority());
            }
            this.processPendingMovieList(n, nfManifest);
        }
    }
    
    private void purgeManifestCache() {
        Collections.sort(this.mPriorityList);
        final int n = this.mPriorityList.size() - this.mCachedManifestCount;
        if (Log.isLoggable()) {
            Log.d(NfManifestCache.TAG, "M-CACHE, now has " + this.mManifestMap.size() + ", excessive entries " + n);
        }
        final Iterator<NfManifest> iterator = this.mPriorityList.iterator();
        int n2 = 0;
        while (iterator.hasNext()) {
            final NfManifest nfManifest = iterator.next();
            if (nfManifest.getRemainLife() > 0L && n2 >= n) {
                break;
            }
            iterator.remove();
            this.mManifestMap.remove(nfManifest.getMovieId());
            if (Log.isLoggable()) {
                Log.d(NfManifestCache.TAG, "M-CACHE, remove " + nfManifest.getMovieId());
            }
            ++n2;
        }
        if (Log.isLoggable()) {
            Log.d(NfManifestCache.TAG, "M-CACHE, now has " + this.mManifestMap.size());
        }
    }
    
    @Override
    public void abort(final Long n) {
        this.mCallbackHandler.post((Runnable)new NfManifestCache$3(this, n));
    }
    
    @Override
    public void clearAll() {
        Log.d(NfManifestCache.TAG, "clear all manifest");
        this.mWorkHandler.post((Runnable)new NfManifestCache$5(this));
        this.mCallbackHandler.post((Runnable)new NfManifestCache$6(this));
    }
    
    @Override
    public void getManifestAsync(final Long n, final NfManifestCachePlaybackInterface$ManifestCacheCallback nfManifestCachePlaybackInterface$ManifestCacheCallback) {
        if (Log.isLoggable()) {
            Log.d(NfManifestCache.TAG, "getManifestAsync for " + n);
        }
        this.mCallbackHandler.post((Runnable)new NfManifestCache$1(this, n));
        this.mWorkHandler.post((Runnable)new NfManifestCache$2(this, n, nfManifestCachePlaybackInterface$ManifestCacheCallback));
    }
    
    @Override
    public void onBackgroundTrimMem() {
        this.clearAll();
    }
    
    @Override
    public void prepare(final List<Triple<Long, Integer, PlayContext>> list) {
        if (list.size() == 0) {
            Log.d(NfManifestCache.TAG, "prepare has list contains no movie");
            return;
        }
        if (Log.isLoggable()) {
            Log.d(NfManifestCache.TAG, "prepare movies");
            for (final Triple<Long, Integer, PlayContext> triple : list) {
                Log.d(NfManifestCache.TAG, "movieId = " + triple.first + ", priority = " + triple.second);
            }
        }
        this.mWorkHandler.post((Runnable)new NfManifestCache$4(this, list));
    }
    
    @Override
    public void release() {
        this.mWorkerThread.quit();
    }
}
