// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.drm.ExoMediaCrypto;
import android.media.MediaCrypto;
import android.media.MediaDrm$OnEventListener;
import com.netflix.mediaclient.util.MediaDrmUtils;
import android.media.MediaDrm;
import java.util.UUID;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.drm.DrmSessionManager;

public class OfflineDrmSession implements DrmSessionManager<FrameworkMediaCrypto>
{
    private static final String TAG = "OfflinePlayback_DrmSession";
    public static final UUID WIDEVINE_SCHEME;
    private MediaDrm mDrm;
    private FrameworkMediaCrypto mMediaCrypto;
    private byte[] mSessionId;
    private int mState;
    
    static {
        WIDEVINE_SCHEME = MediaDrmUtils.WIDEVINE_SCHEME;
    }
    
    OfflineDrmSession(final byte[] array) {
        this.mDrm = MediaDrmUtils.getNewMediaDrmInstance(null);
        this.mSessionId = this.mDrm.openSession();
        this.mDrm.restoreKeys(this.mSessionId, array);
        this.mMediaCrypto = new FrameworkMediaCrypto(new MediaCrypto(OfflineDrmSession.WIDEVINE_SCHEME, this.mSessionId));
        this.mState = 4;
        MediaDrmUtils.dumpKeyStatus("OfflinePlayback_DrmSession", this.mDrm, this.mSessionId);
    }
    
    @Override
    public void close() {
        this.mDrm.closeSession(this.mSessionId);
        this.mDrm.release();
        this.mState = 1;
    }
    
    @Override
    public Exception getError() {
        return null;
    }
    
    @Override
    public FrameworkMediaCrypto getMediaCrypto() {
        return this.mMediaCrypto;
    }
    
    @Override
    public int getState() {
        return this.mState;
    }
    
    @Override
    public void open(final DrmInitData drmInitData) {
    }
    
    @Override
    public boolean requiresSecureDecoderComponent(final String s) {
        return this.mMediaCrypto == null || this.mMediaCrypto.requiresSecureDecoderComponent(s);
    }
}
