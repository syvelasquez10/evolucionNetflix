// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.util.l10n.LanguageUtils$SelectedLanguage;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.l10n.LanguageUtils;
import com.netflix.mediaclient.service.player.manifest.NfManifestUtils;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import com.netflix.mediaclient.service.offline.download.DownloadableType;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.media.manifest.VideoTrack;
import java.util.List;
import com.netflix.mediaclient.media.Subtitle;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import org.json.JSONObject;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import android.content.Context;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import android.util.Pair;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;

public class OfflinePlayableManifestImpl implements OfflinePlaybackInterface$OfflineManifest
{
    private static final String TAG = "nf_offline_manifest";
    private final Pair<Integer, Integer> mAspectRatio;
    private final AudioSource[] mAudioSourceArray;
    private final AudioSubtitleDefaultOrderInfo[] mAudioSubtitleDefaultOrderInfoArray;
    private final Context mContext;
    private final DownloadContext mDc;
    private final String mDxId;
    private final byte[] mKeySetId;
    private final JSONObject mLinks;
    private final MediaPresentationDescription mMpd;
    private final String mOxId;
    private final Subtitle[] mSubtitleArray;
    private final String mTrickplayUrl;
    private final List<VideoTrack> mVideoTrackList;
    
    public OfflinePlayableManifestImpl(final Context mContext, final String s, final NfManifest nfManifest, final byte[] mKeySetId, final List<String> list, final List<String> list2, final List<String> list3, final List<String> list4, final String mOxId, final String mDxId, final DownloadContext mDc) {
        this.mContext = mContext;
        this.mKeySetId = mKeySetId;
        this.mOxId = mOxId;
        this.mDxId = mDxId;
        this.mAudioSourceArray = OfflineUtils.getAudioSourceArrayForDownloadables(nfManifest, list);
        this.mVideoTrackList = OfflineUtils.getVideoTracks(nfManifest, list2);
        this.mSubtitleArray = OfflineUtils.getSubTitleArrayForDownloadables(nfManifest, list3, s);
        if (list4.size() > 0) {
            this.mTrickplayUrl = OfflinePathUtils.getFilePathForDownloadable(s, list4.get(0), DownloadableType.TrickPlay);
        }
        else {
            this.mTrickplayUrl = null;
        }
        this.mAspectRatio = nfManifest.getAspectWidthHeight();
        this.mAudioSubtitleDefaultOrderInfoArray = nfManifest.getAudioSubtitleDefaultOrderInfo();
        String downloadableForAudioTrackId = null;
        if (this.mAudioSubtitleDefaultOrderInfoArray != null) {
            downloadableForAudioTrackId = downloadableForAudioTrackId;
            if (this.mAudioSubtitleDefaultOrderInfoArray.length > 0) {
                downloadableForAudioTrackId = NfManifestUtils.getDownloadableForAudioTrackId(nfManifest, this.getDefaultAudioTrackId());
            }
        }
        final MpdBuilder mpdBuilder = new MpdBuilder(s, nfManifest, list, list2, downloadableForAudioTrackId);
        this.mLinks = nfManifest.getLinks();
        this.mDc = mDc;
        this.mMpd = mpdBuilder.buildMpd();
        this.dump();
    }
    
    private void dump() {
    }
    
    private String getDefaultAudioTrackId() {
        final LanguageUtils$SelectedLanguage selectedLanguage = LanguageUtils.getSelectedLanguage(this.mContext);
        if (selectedLanguage == null) {
            return this.mAudioSubtitleDefaultOrderInfoArray[0].getAudioTrackId();
        }
        final LanguageChoice languageChoice = LanguageUtils.toLanguageChoice(selectedLanguage, this.getSubtitleTrackList(), this.getAudioTrackList(), this.getAudioSubtitleDefaultOrderInfo());
        if (languageChoice == null || languageChoice.getAudio() == null) {
            Log.w("nf_offline_manifest", "Unable to find user forced audio, use default one");
            return this.mAudioSubtitleDefaultOrderInfoArray[0].getAudioTrackId();
        }
        Log.d("nf_offline_manifest", "Found forced audio, use it %s", languageChoice.getAudio().getId());
        return languageChoice.getAudio().getId();
    }
    
    @Override
    public Pair<Integer, Integer> getAspectWidthHeight() {
        return this.mAspectRatio;
    }
    
    @Override
    public AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo() {
        return this.mAudioSubtitleDefaultOrderInfoArray;
    }
    
    @Override
    public AudioSource[] getAudioTrackList() {
        return this.mAudioSourceArray;
    }
    
    @Override
    public String getBifFile() {
        return this.mTrickplayUrl;
    }
    
    @Override
    public DownloadContext getDownloadContext() {
        return this.mDc;
    }
    
    @Override
    public String getDxId() {
        return this.mDxId;
    }
    
    @Override
    public JSONObject getLinks() {
        return this.mLinks;
    }
    
    @Override
    public MediaPresentationDescription getMpd() {
        return this.mMpd;
    }
    
    @Override
    public byte[] getOfflineKeySetId() {
        return this.mKeySetId;
    }
    
    @Override
    public String getOxId() {
        return this.mOxId;
    }
    
    @Override
    public Subtitle[] getSubtitleTrackList() {
        return this.mSubtitleArray;
    }
    
    @Override
    public List<VideoTrack> getVideoTrackList() {
        return this.mVideoTrackList;
    }
}
