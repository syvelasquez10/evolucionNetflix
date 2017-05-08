// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;

public class DownloadContext
{
    private static final String TAG;
    private long downloadInitTimeMs;
    private int listPos;
    private String oxid;
    private String profileGuid;
    private String requestId;
    private int trackId;
    private int videoPos;
    
    static {
        TAG = DownloadContext.class.getSimpleName();
    }
    
    public DownloadContext(final String oxid, final String profileGuid, final long downloadInitTimeMs, final String requestId, final int trackId, final int videoPos, final int listPos) {
        this.oxid = oxid;
        this.profileGuid = profileGuid;
        this.downloadInitTimeMs = downloadInitTimeMs;
        this.requestId = requestId;
        this.trackId = trackId;
        this.videoPos = videoPos;
        this.listPos = listPos;
    }
    
    public static DownloadContext createDownloadContext(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        return new DownloadContext(offlinePlayablePersistentData.mOxId, getProfileGuidFromOpd(offlinePlayablePersistentData), offlinePlayablePersistentData.mDownloadContextInitTimeMs, offlinePlayablePersistentData.mDownloadContextRequestId, offlinePlayablePersistentData.mDownloadContextTrackId, offlinePlayablePersistentData.mDownloadContextVideoPos, offlinePlayablePersistentData.mDownloadContextListPos);
    }
    
    public static DownloadContext createDownloadContext(final OfflinePlayableViewData offlinePlayableViewData) {
        return new DownloadContext(offlinePlayableViewData.getOxId(), offlinePlayableViewData.getProfileGuidOfDownloadRequester(), offlinePlayableViewData.getDownloadContextInitTimeMs(), offlinePlayableViewData.getDownloadContextRequestId(), offlinePlayableViewData.getDownloadContextTrackId(), offlinePlayableViewData.getDownloadContextVideoPos(), offlinePlayableViewData.getDownloadContextListPos());
    }
    
    private static String getProfileGuidFromOpd(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (offlinePlayablePersistentData.getProfileGuidList() != null && offlinePlayablePersistentData.getProfileGuidList().size() > 0) {
            return offlinePlayablePersistentData.getProfileGuidList().get(0);
        }
        return null;
    }
    
    public long getDownloadInitTimeMs() {
        return this.downloadInitTimeMs;
    }
    
    public JSONObject getJsonObject() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("track_id", this.getTrackId());
            jsonObject.put("rank", this.getVideoPos());
            jsonObject.put("row", this.getListPos());
            jsonObject.put("profile_guid", (Object)this.getProfileGuid());
            jsonObject.put("request_id", (Object)this.getRequestId());
            jsonObject.put("oxid", (Object)this.getOxid());
            jsonObject.put("download_utc_sec", this.getDownloadInitTimeMs() / 1000L);
            return jsonObject;
        }
        catch (JSONException ex) {
            Log.e(DownloadContext.TAG, "downloadContext jsonObject", (Throwable)ex);
            return jsonObject;
        }
    }
    
    public int getListPos() {
        return this.listPos;
    }
    
    public String getOxid() {
        return this.oxid;
    }
    
    public String getProfileGuid() {
        return this.profileGuid;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public int getTrackId() {
        return this.trackId;
    }
    
    public int getVideoPos() {
        return this.videoPos;
    }
    
    @Override
    public String toString() {
        return "DownloadContext{oxid='" + this.oxid + '\'' + ", profileGuid='" + this.profileGuid + '\'' + ", downloadInitTimeMs=" + this.downloadInitTimeMs + ", requestId='" + this.requestId + '\'' + ", trackId=" + this.trackId + ", videoPos=" + this.videoPos + ", listPos=" + this.listPos + '}';
    }
}
