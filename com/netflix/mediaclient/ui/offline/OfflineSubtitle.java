// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import com.netflix.mediaclient.ui.player.NccpSubtitle;
import org.json.JSONObject;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.media.Subtitle;

public abstract class OfflineSubtitle implements Subtitle
{
    protected static final String ATTR_LOCAL_PATH = "localPath";
    protected static final String ATTR_PROFILE = "profile";
    protected static final String ATTR_SUBTITLE_URL = "subtitleUrl";
    protected static final String TAG = "nf_subtitles_offline";
    protected String mLocalFilePath;
    protected Subtitle mSubtitle;
    private SubtitleUrl mSubtitleUrl;
    
    protected OfflineSubtitle(final Subtitle mSubtitle, final SubtitleUrl mSubtitleUrl, final String mLocalFilePath) {
        this.mSubtitle = mSubtitle;
        this.mSubtitleUrl = mSubtitleUrl;
        this.mLocalFilePath = mLocalFilePath;
    }
    
    public OfflineSubtitle(final JSONObject jsonObject) {
        this.mSubtitle = NccpSubtitle.newInstance(jsonObject);
        this.mLocalFilePath = jsonObject.optString("localPath");
        this.mSubtitleUrl = new SubtitleUrl(jsonObject.getJSONObject("subtitleUrl"));
    }
    
    public static OfflineSubtitle newInstance(final Subtitle subtitle, final SubtitleUrl subtitleUrl, final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        if (subtitleUrl.getProfile() == ISubtitleDef$SubtitleProfile.IMAGE || subtitleUrl.getProfile() == ISubtitleDef$SubtitleProfile.IMAGE_ENC) {
            Log.d("nf_subtitles_offline", "OfflineSubtitle::newInstance: image, path %s", s2);
            return new OfflineImageSubtitle(subtitle, subtitleUrl, s2);
        }
        Log.d("nf_subtitles_offline", "OfflineSubtitle::newInstance: text, path %s", s2);
        return new OfflineTextSubtitle(subtitle, subtitleUrl, s2);
    }
    
    @Override
    public boolean canDeviceRender() {
        return this.mSubtitle.canDeviceRender();
    }
    
    @Override
    public int compareTo(final Subtitle subtitle) {
        final int n = -1;
        int compare;
        if (subtitle == null) {
            compare = n;
        }
        else {
            compare = n;
            if (this.mSubtitle != null) {
                compare = n;
                if (this.mSubtitle.getLanguageDescription() != null) {
                    if (subtitle.getLanguageDescription() == null) {
                        return 1;
                    }
                    if ((compare = String.CASE_INSENSITIVE_ORDER.compare(this.mSubtitle.getLanguageDescription(), subtitle.getLanguageDescription())) == 0) {
                        return this.mSubtitle.getLanguageDescription().compareTo(subtitle.getLanguageDescription());
                    }
                }
            }
        }
        return compare;
    }
    
    @Override
    public String getDownloadableId() {
        return this.mSubtitleUrl.getDownloadableId();
    }
    
    @Override
    public String getId() {
        return this.mSubtitle.getId();
    }
    
    protected abstract int getImplementation();
    
    @Override
    public String getLanguageCodeIso639_1() {
        return this.mSubtitle.getLanguageCodeIso639_1();
    }
    
    @Override
    public String getLanguageCodeIso639_2() {
        return this.mSubtitle.getLanguageCodeIso639_2();
    }
    
    @Override
    public String getLanguageDescription() {
        return this.mSubtitle.getLanguageDescription();
    }
    
    public String getLocalFilePath() {
        return this.mLocalFilePath;
    }
    
    @Override
    public int getNccpOrderNumber() {
        return this.mSubtitle.getNccpOrderNumber();
    }
    
    public ISubtitleDef$SubtitleProfile getProfile() {
        return this.mSubtitleUrl.getProfile();
    }
    
    public SubtitleUrl getSubtitleUrl() {
        return this.mSubtitleUrl;
    }
    
    @Override
    public int getTrackType() {
        return this.mSubtitle.getTrackType();
    }
    
    @Override
    public boolean isCC() {
        return this.mSubtitle.isCC();
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject json = this.mSubtitle.toJson();
        json.put("impl", this.getImplementation());
        json.put("localPath", (Object)this.mLocalFilePath);
        json.put("subtitleUrl", (Object)this.mSubtitleUrl.toJson());
        return json;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{subtitle=" + this.mSubtitle + ", SubtitleUrl=" + this.mSubtitleUrl + ", LocalFilePath='" + this.mLocalFilePath + '\'' + '}';
    }
    
    public String toString2() {
        try {
            return this.getClass().getSimpleName() + ": " + this.toJson().toString();
        }
        catch (JSONException ex) {
            return null;
        }
    }
}
