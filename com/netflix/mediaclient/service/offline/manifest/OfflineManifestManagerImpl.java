// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import java.io.File;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import java.util.List;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.player.exoplayback.logblob.OfflineErrorLogblob;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.Status;
import java.util.HashMap;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.service.pdslogging.PdsDownloadInterface;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;

public class OfflineManifestManagerImpl implements OfflineManifestManager
{
    private static final int MAX_MANIFEST_TO_CACHE = 10;
    private static final String TAG = "nf_offlineManifestMgr";
    private final BladeRunnerClient mBladeRunnerClient;
    private final IClientLogging mLoggingAgent;
    private final Map<String, NfManifest> mOfflineManifestCache;
    private final Map<String, OfflineManifestCallback> mOfflineManifestRequestMap;
    private final PdsDownloadInterface mPdsAgent;
    private final Handler mWorkHandler;
    
    public OfflineManifestManagerImpl(final BladeRunnerClient mBladeRunnerClient, final HandlerThread handlerThread, final PdsDownloadInterface mPdsAgent, final IClientLogging mLoggingAgent) {
        this.mOfflineManifestRequestMap = new HashMap<String, OfflineManifestCallback>();
        this.mOfflineManifestCache = new HashMap<String, NfManifest>();
        this.mBladeRunnerClient = mBladeRunnerClient;
        this.mPdsAgent = mPdsAgent;
        this.mLoggingAgent = mLoggingAgent;
        this.mWorkHandler = new Handler(handlerThread.getLooper());
    }
    
    private void addManifestToCache(final String s, final NfManifest nfManifest) {
        if (this.mOfflineManifestCache.size() >= 10) {
            this.removeOldestManifest();
        }
        this.mOfflineManifestCache.put(s, nfManifest);
    }
    
    private NfManifest getManifestFromMemoryOrPersistentStore(final String s, final String s2) {
        NfManifest nfManifest;
        if ((nfManifest = this.mOfflineManifestCache.get(s)) == null) {
            final NfManifest manifestFromPersistentStore = this.readManifestFromPersistentStore(s, s2);
            if ((nfManifest = manifestFromPersistentStore) != null) {
                this.addManifestToCache(s, manifestFromPersistentStore);
                nfManifest = manifestFromPersistentStore;
            }
        }
        return nfManifest;
    }
    
    private void handleManifestResponse(Status status, final String s, String persistManifest, final boolean b, final JSONObject jsonObject, final String s2, final String s3, final DownloadContext downloadContext) {
        final long currentTimeMillis = System.currentTimeMillis();
        final OfflineManifestCallback offlineManifestCallback = this.mOfflineManifestRequestMap.get(s);
        if (offlineManifestCallback == null) {
            Log.i("nf_offlineManifestMgr", "onManifestsFetched but no callback");
            return;
        }
        final Status status2 = null;
        while (true) {
            Label_0173: {
                if (!status.isSucces()) {
                    break Label_0173;
                }
            Label_0097_Outer:
                while (true) {
                    while (true) {
                        Object mStatus = null;
                        Label_0194: {
                            while (true) {
                                try {
                                    jsonObject.put("timestamp", currentTimeMillis);
                                    final OfflineManifestManagerImpl$ManifestParseResult manifest = this.parseManifest(jsonObject);
                                    mStatus = manifest.mStatus;
                                    final Object mNfManifest = manifest.mNfManifest;
                                    if (((Status)mStatus).isSucces()) {
                                        persistManifest = (String)this.persistManifest(s, persistManifest, jsonObject);
                                        if (status.isSucces()) {
                                            this.addManifestToCache(s, manifest.mNfManifest);
                                            if (b) {
                                                this.mPdsAgent.onDownloadOfFirstTimeOfflineManifest(s, s2, s3, downloadContext, manifest.mNfManifest.getLinks());
                                            }
                                        }
                                        status = (Status)mNfManifest;
                                        offlineManifestCallback.onOfflineManifestResponse((NfManifest)status, (Status)persistManifest);
                                        return;
                                    }
                                    break Label_0194;
                                }
                                catch (JSONException ex) {
                                    Log.e("nf_offlineManifestMgr", "Failed to save timestamp to manifest data.", (Throwable)ex);
                                    continue Label_0097_Outer;
                                }
                                break;
                            }
                            break;
                        }
                        persistManifest = (String)mStatus;
                        continue;
                    }
                }
            }
            OfflineErrorLogblob.sendBladerunnerError(this.mLoggingAgent, s, s2, s3, status);
            persistManifest = (String)status;
            status = status2;
            continue;
        }
    }
    
    private OfflineManifestManagerImpl$ManifestParseResult parseManifest(final JSONObject jsonObject) {
        final OfflineManifestManagerImpl$ManifestParseResult offlineManifestManagerImpl$ManifestParseResult = new OfflineManifestManagerImpl$ManifestParseResult(this);
        final List<NfManifest> manifestResponse = NfManifest.parseManifestResponse(jsonObject);
        if (manifestResponse != null && manifestResponse.size() > 0) {
            offlineManifestManagerImpl$ManifestParseResult.mStatus = CommonStatus.OK;
            offlineManifestManagerImpl$ManifestParseResult.mNfManifest = manifestResponse.get(0);
            return offlineManifestManagerImpl$ManifestParseResult;
        }
        Log.e("nf_offlineManifestMgr", "manifest parse error");
        offlineManifestManagerImpl$ManifestParseResult.mStatus = new NetflixStatus(StatusCode.RESPONSE_PARSE_ERROR);
        return offlineManifestManagerImpl$ManifestParseResult;
    }
    
    private Status persistManifest(final String s, String filePathOfflineManifest, final JSONObject jsonObject) {
        final NetflixImmutableStatus ok = CommonStatus.OK;
        filePathOfflineManifest = OfflinePathUtils.getFilePathOfflineManifest(filePathOfflineManifest, s);
        Log.i("nf_offlineManifestMgr", "filepath for manifest= " + filePathOfflineManifest);
        Status status = ok;
        if (!FileUtils.writeBytesToFile(filePathOfflineManifest, jsonObject.toString().getBytes())) {
            status = new NetflixStatus(StatusCode.DL_CANT_PERSIST_MANIFEST);
        }
        return status;
    }
    
    private NfManifest readManifestFromPersistentStore(final String s, final String s2) {
        final File file = new File(OfflinePathUtils.getFilePathOfflineManifest(s2, s));
        if (!file.exists()) {
            Log.e("nf_offlineManifestMgr", "readManifestFromPersistentStore file not found=" + file.getAbsolutePath());
            return null;
        }
        while (true) {
            try {
                final List<NfManifest> manifestResponse = NfManifest.parseManifestResponse(new JSONObject(StringUtils.byteArrayToString(FileUtils.readFileToByteArray(file), "utf-8")));
                if (manifestResponse != null && manifestResponse.size() > 0) {
                    return manifestResponse.get(0);
                }
            }
            catch (Exception ex) {
                Log.e("nf_offlineManifestMgr", "readManifestFromPersistentStore read error", ex);
            }
            return null;
        }
    }
    
    private void removeOldestManifest() {
        String s = null;
        long n = 2147483647L;
        for (final Map.Entry<String, NfManifest> entry : this.mOfflineManifestCache.entrySet()) {
            String s2;
            if ((s2 = s) == null) {
                s2 = entry.getKey();
            }
            final long manifestExpiryInEndPointTime = entry.getValue().getManifestExpiryInEndPointTime();
            if (entry.getValue().getManifestExpiryInEndPointTime() < n) {
                if (Log.isLoggable()) {
                    Log.i("nf_offlineManifestMgr", "removeOldestManifest manifestExpiryTime=" + manifestExpiryInEndPointTime);
                }
                s = entry.getKey();
                n = manifestExpiryInEndPointTime;
            }
            else {
                s = s2;
            }
        }
        if (s != null) {
            if (Log.isLoggable()) {
                Log.i("nf_offlineManifestMgr", "removeOldestManifest removing " + s);
            }
            this.mOfflineManifestCache.remove(s);
        }
    }
    
    @Override
    public void abortAllRequestsForPlayable(final String s) {
        this.mOfflineManifestRequestMap.remove(s);
        this.mOfflineManifestCache.remove(s);
    }
    
    @Override
    public void notifyDeletingPlayable(final String s) {
        this.mOfflineManifestCache.remove(s);
    }
    
    @Override
    public void onTrimMemory(final int n) {
        if (n >= 60) {
            this.mWorkHandler.post((Runnable)new OfflineManifestManagerImpl$3(this));
        }
    }
    
    @Override
    public void requestOfflineManifestFromCache(final String s, final String s2, final OfflineManifestCallback offlineManifestCallback) {
        Log.i("nf_offlineManifestMgr", "requestOfflineManifestFromCache playableId=" + s);
        final NetflixImmutableStatus ok = CommonStatus.OK;
        final NfManifest manifestFromMemoryOrPersistentStore = this.getManifestFromMemoryOrPersistentStore(s, s2);
        Status status = ok;
        if (manifestFromMemoryOrPersistentStore == null) {
            status = new NetflixStatus(StatusCode.DL_MANIFEST_NOT_FOUND_IN_CACHE);
        }
        if (offlineManifestCallback != null) {
            offlineManifestCallback.onOfflineManifestResponse(manifestFromMemoryOrPersistentStore, status);
        }
    }
    
    @Override
    public void requestOfflineManifestFromServer(final String s, final String s2, final String s3, final DownloadContext downloadContext, final String s4, final DownloadVideoQuality downloadVideoQuality, final OfflineManifestCallback offlineManifestCallback) {
        Log.i("nf_offlineManifestMgr", "requestOfflineManifestFromServer playableId=" + s);
        this.mOfflineManifestCache.remove(s);
        this.mOfflineManifestRequestMap.put(s, offlineManifestCallback);
        this.mBladeRunnerClient.fetchOfflineManifest(s, s2, s3, downloadVideoQuality, new OfflineManifestManagerImpl$1(this, s, s4, s2, s3, downloadContext));
    }
    
    @Override
    public void requestOfflineManifestRefreshFromServer(final String s, final String s2, final String s3, final String s4, final DownloadVideoQuality downloadVideoQuality, final OfflineManifestCallback offlineManifestCallback) {
        Log.i("nf_offlineManifestMgr", "requestOfflineManifestRefreshFromServer playableId=" + s);
        this.requestOfflineManifestFromCache(s, s4, new OfflineManifestManagerImpl$2(this, s, offlineManifestCallback, s2, s3, downloadVideoQuality, s4));
    }
}
