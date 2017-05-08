// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;

public class SubtitleUrl
{
    private static final String ATTR_CDN_ID = "cdnId";
    private static final String ATTR_DECRYPTION_KEY = "decryptionKey";
    private static final String ATTR_DOWNLOADABLE_ID = "downloadableId";
    private static final String ATTR_MASTER_INDEX_OFFSET = "masterIndexOffset";
    private static final String ATTR_MASTER_INDEX_SIZE = "masterIndexSize";
    private static final String ATTR_PROFILE = "profile";
    private static final String ATTR_URL = "url";
    private static final String ATTR_XID = "xid";
    private static final String TAG = "nf_subtitles";
    private long mCdnId;
    private String mDecryptionKey;
    private String mDownloadableId;
    private int mMasterIndexOffset;
    private int mMasterIndexSize;
    private ISubtitleDef$SubtitleProfile mProfile;
    private long mSize;
    private String mUrl;
    private long mXid;
    
    public SubtitleUrl(final String mUrl, final ISubtitleDef$SubtitleProfile mProfile, final long mXid, final long mCdnId, final String mDownloadableId, final long mSize) {
        this.mSize = -1L;
        this.mUrl = mUrl;
        this.mProfile = mProfile;
        this.mXid = mXid;
        this.mCdnId = mCdnId;
        this.mDownloadableId = mDownloadableId;
        this.mSize = mSize;
    }
    
    public SubtitleUrl(final JSONObject jsonObject) {
        this.mSize = -1L;
        this.populate(jsonObject);
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (this.getClass() == o.getClass()) {
                    final SubtitleUrl subtitleUrl = (SubtitleUrl)o;
                    b3 = b2;
                    if (this.mMasterIndexSize == subtitleUrl.mMasterIndexSize) {
                        b3 = b2;
                        if (this.mMasterIndexOffset == subtitleUrl.mMasterIndexOffset) {
                            if (this.mUrl != null) {
                                b3 = b2;
                                if (!this.mUrl.equals(subtitleUrl.mUrl)) {
                                    return b3;
                                }
                            }
                            else if (subtitleUrl.mUrl != null) {
                                return false;
                            }
                            if (this.mDownloadableId != null) {
                                b3 = b2;
                                if (!this.mDownloadableId.equals(subtitleUrl.mDownloadableId)) {
                                    return b3;
                                }
                            }
                            else if (subtitleUrl.mDownloadableId != null) {
                                return false;
                            }
                            b3 = b2;
                            if (this.mCdnId == subtitleUrl.mCdnId) {
                                b3 = b2;
                                if (this.mXid == subtitleUrl.mXid) {
                                    return this.mProfile == subtitleUrl.mProfile && b;
                                }
                            }
                        }
                    }
                }
            }
        }
        return b3;
    }
    
    public long getCdnId() {
        return this.mCdnId;
    }
    
    public String getDecryptionKey() {
        return this.mDecryptionKey;
    }
    
    public String getDownloadUrl() {
        return this.mUrl;
    }
    
    public String getDownloadableId() {
        return this.mDownloadableId;
    }
    
    public int getMasterIndexOffset() {
        return this.mMasterIndexOffset;
    }
    
    public int getMasterIndexSize() {
        return this.mMasterIndexSize;
    }
    
    public ISubtitleDef$SubtitleProfile getProfile() {
        return this.mProfile;
    }
    
    public long getSize() {
        return this.mSize;
    }
    
    public String getSubtitleProfile() {
        return this.mProfile.getNccpCode();
    }
    
    public long getXid() {
        return this.mXid;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.mUrl != null) {
            hashCode2 = this.mUrl.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int hashCode3;
        if (this.mDownloadableId != null) {
            hashCode3 = this.mDownloadableId.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n = (int)this.mCdnId;
        final int n2 = (int)this.mXid;
        final int mMasterIndexSize = this.mMasterIndexSize;
        final int mMasterIndexOffset = this.mMasterIndexOffset;
        if (this.mProfile != null) {
            hashCode = this.mProfile.hashCode();
        }
        return (((((hashCode3 + hashCode2 * 31) * 31 + n) * 31 + n2) * 31 + mMasterIndexSize) * 31 + mMasterIndexOffset) * 31 + hashCode;
    }
    
    protected void populate(final JSONObject jsonObject) {
        this.mUrl = JsonUtils.getString(jsonObject, "url", null);
        this.mProfile = ISubtitleDef$SubtitleProfile.fromNccpCode(JsonUtils.getString(jsonObject, "profile", null));
        this.mXid = JsonUtils.getLong(jsonObject, "xid", 0L);
        this.mCdnId = JsonUtils.getLong(jsonObject, "cdnId", 0L);
        this.mDownloadableId = JsonUtils.getString(jsonObject, "downloadableId", null);
        if (this.mProfile == ISubtitleDef$SubtitleProfile.IMAGE || this.mProfile == ISubtitleDef$SubtitleProfile.IMAGE_ENC) {
            this.mMasterIndexSize = JsonUtils.getInt(jsonObject, "masterIndexSize", 0);
            this.mMasterIndexOffset = JsonUtils.getInt(jsonObject, "masterIndexOffset", 0);
        }
        this.mDecryptionKey = JsonUtils.getString(jsonObject, "decryptionKey", null);
    }
    
    public void setDecryptionKey(final String mDecryptionKey) {
        this.mDecryptionKey = mDecryptionKey;
    }
    
    public void setMasterIndex(final int mMasterIndexSize, final int mMasterIndexOffset) {
        this.mMasterIndexSize = mMasterIndexSize;
        this.mMasterIndexOffset = mMasterIndexOffset;
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        if (this.mUrl != null) {
            jsonObject.put("url", (Object)this.mUrl);
        }
        jsonObject.put("profile", (Object)this.mProfile.getNccpCode());
        jsonObject.put("xid", this.mXid);
        jsonObject.put("cdnId", this.mCdnId);
        jsonObject.put("downloadableId", (Object)this.mDownloadableId);
        if (this.mProfile == ISubtitleDef$SubtitleProfile.IMAGE || this.mProfile == ISubtitleDef$SubtitleProfile.IMAGE_ENC) {
            jsonObject.put("masterIndexOffset", this.mMasterIndexOffset);
            jsonObject.put("masterIndexSize", this.mMasterIndexSize);
        }
        if (this.mDecryptionKey != null) {
            jsonObject.put("decryptionKey", (Object)this.mDecryptionKey);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "SubtitleUrl{mUrl='" + this.mUrl + '\'' + ", mDownloadableId='" + this.mDownloadableId + '\'' + ", mCdnId=" + this.mCdnId + ", mXid=" + this.mXid + ", mMasterIndexSize=" + this.mMasterIndexSize + ", mMasterIndexOffset=" + this.mMasterIndexOffset + ", mDecryptionKey='" + this.mDecryptionKey + '\'' + ", mProfile=" + this.mProfile + '}';
    }
}
