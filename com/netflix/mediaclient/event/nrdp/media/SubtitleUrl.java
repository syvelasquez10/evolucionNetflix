// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;

public class SubtitleUrl
{
    private static final String ATTR_CDN_ID = "cdnId";
    private static final String ATTR_DECRYPTION_KEY = "decryptionKey";
    private static final String ATTR_DOWNLOADABLE_ID = "downloadableId";
    private static final String ATTR_HEIGHT = "height";
    private static final String ATTR_MASTER_INDEX_OFFSET = "masterIndexOffset";
    private static final String ATTR_MASTER_INDEX_SIZE = "masterIndexSize";
    private static final String ATTR_PROFILE = "profile";
    private static final String ATTR_URL = "url";
    private static final String ATTR_WIDTH = "width";
    private static final String ATTR_XID = "xid";
    private static final String TAG = "nf_dns";
    private long mCdnId;
    private String mDecryptionKey;
    private String mDownloadableId;
    private int mHeight;
    private int mMasterIndexOffset;
    private int mMasterIndexSize;
    private IMedia$SubtitleProfile mProfile;
    private String mUrl;
    private int mWidth;
    private long mXid;
    
    public SubtitleUrl(final JSONObject jsonObject) {
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
                    if (this.mHeight == subtitleUrl.mHeight) {
                        b3 = b2;
                        if (this.mWidth == subtitleUrl.mWidth) {
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
    
    public int getHeight() {
        return this.mHeight;
    }
    
    public int getMasterIndexOffset() {
        return this.mMasterIndexOffset;
    }
    
    public int getMasterIndexSize() {
        return this.mMasterIndexSize;
    }
    
    public IMedia$SubtitleProfile getProfile() {
        return this.mProfile;
    }
    
    public int getWidth() {
        return this.mWidth;
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
        final int mHeight = this.mHeight;
        final int mWidth = this.mWidth;
        final int mMasterIndexSize = this.mMasterIndexSize;
        final int mMasterIndexOffset = this.mMasterIndexOffset;
        if (this.mProfile != null) {
            hashCode = this.mProfile.hashCode();
        }
        return (((((((hashCode3 + hashCode2 * 31) * 31 + n) * 31 + n2) * 31 + mHeight) * 31 + mWidth) * 31 + mMasterIndexSize) * 31 + mMasterIndexOffset) * 31 + hashCode;
    }
    
    protected void populate(final JSONObject jsonObject) {
        this.mUrl = JsonUtils.getString(jsonObject, "url", null);
        this.mProfile = IMedia$SubtitleProfile.fromNccpCode(JsonUtils.getString(jsonObject, "profile", null));
        this.mXid = JsonUtils.getLong(jsonObject, "xid", 0L);
        this.mCdnId = JsonUtils.getLong(jsonObject, "cdnId", 0L);
        this.mDownloadableId = JsonUtils.getString(jsonObject, "downloadableId", null);
        if (this.mProfile == IMedia$SubtitleProfile.IMAGE || this.mProfile == IMedia$SubtitleProfile.IMAGE_ENC) {
            this.mHeight = JsonUtils.getInt(jsonObject, "height", 0);
            this.mWidth = JsonUtils.getInt(jsonObject, "width", 0);
            this.mMasterIndexSize = JsonUtils.getInt(jsonObject, "masterIndexSize", 0);
            this.mMasterIndexOffset = JsonUtils.getInt(jsonObject, "masterIndexOffset", 0);
        }
        this.mDecryptionKey = JsonUtils.getString(jsonObject, "decryptionKey", null);
    }
    
    @Override
    public String toString() {
        return "SubtitleUrl{mUrl='" + this.mUrl + '\'' + ", mDownloadableId='" + this.mDownloadableId + '\'' + ", mCdnId=" + this.mCdnId + ", mXid=" + this.mXid + ", mHeight=" + this.mHeight + ", mWidth=" + this.mWidth + ", mMasterIndexSize=" + this.mMasterIndexSize + ", mMasterIndexOffset=" + this.mMasterIndexOffset + ", mDecryptionKey='" + this.mDecryptionKey + '\'' + ", mProfile=" + this.mProfile + '}';
    }
}
