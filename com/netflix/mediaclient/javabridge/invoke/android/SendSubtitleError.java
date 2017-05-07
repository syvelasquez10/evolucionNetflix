// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SendSubtitleError extends BaseInvoke
{
    private static final String METHOD = "sendSubtitleError";
    private static final String PROPERTY_CDN_ID = "cdnid";
    private static final String PROPERTY_DOWNLOADABLE_ID = "did";
    private static final String PROPERTY_LANGUAGE = "language";
    private static final String PROPERTY_LANG_ISO_CODE_639_1 = "iso639_1";
    private static final String PROPERTY_REASON = "reason";
    private static final String PROPERTY_RETRY = "retry";
    private static final String PROPERTY_SUBTITLE_ID = "subtitleId";
    private static final String PROPERTY_SUBTITLE_TYPE = "subtitleType";
    private static final String PROPERTY_TRACK_TYPE = "trackType";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_XID = "xid";
    private static final String SUBTITLE_TYPE_IMAGE = "image";
    private static final String SUBTITLE_TYPE_TEXT = "text";
    private static final String TARGET = "android";
    
    public SendSubtitleError(final String s, final SubtitleUrl subtitleUrl, final IMedia$SubtitleFailure media$SubtitleFailure, final boolean b, final Subtitle subtitle) {
        super("android", "sendSubtitleError");
        this.setArguments(s, subtitleUrl, media$SubtitleFailure, b, subtitle);
    }
    
    private void setArguments(String s, final SubtitleUrl subtitleUrl, final IMedia$SubtitleFailure media$SubtitleFailure, final boolean b, final Subtitle subtitle) {
        if (s != null) {
            if (Log.isLoggable()) {
                Log.d("nf_invoke", "Subtitle data: " + subtitle);
            }
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("url", (Object)s);
                jsonObject.put("retry", b);
                if (media$SubtitleFailure != null) {
                    jsonObject.put("reason", (Object)media$SubtitleFailure.toString());
                }
                if (subtitleUrl != null) {
                    jsonObject.put("xid", subtitleUrl.getXid());
                    jsonObject.put("cdnid", subtitleUrl.getCdnId());
                    if (subtitleUrl.getDownloadableId() != null) {
                        jsonObject.put("did", (Object)subtitleUrl.getDownloadableId());
                    }
                    if (subtitleUrl.getProfile() != null) {
                        if (subtitleUrl.getProfile() == IMedia$SubtitleProfile.IMAGE) {
                            goto Label_0293;
                        }
                        s = "text";
                        jsonObject.put("subtitleType", (Object)s);
                    }
                }
                if (subtitle != null) {
                    jsonObject.put("trackType", subtitle.getTrackType());
                    if (subtitle.getId() != null) {
                        jsonObject.put("subtitleId", (Object)subtitle.getId());
                    }
                    if (subtitle.getLanguageDescription() != null) {
                        jsonObject.put("language", (Object)subtitle.getLanguageDescription());
                    }
                    if (subtitle.getLanguageCodeIso639_1() != null) {
                        jsonObject.put("iso639_1", (Object)subtitle.getLanguageCodeIso639_1());
                    }
                }
                this.arguments = jsonObject.toString();
                if (Log.isLoggable()) {
                    Log.d("nf_invoke", "Argument: " + this.arguments);
                }
            }
            catch (JSONException ex) {
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
            }
            catch (Exception ex2) {
                Log.e("nf_invoke", "Unable to Log failed subtitle ", ex2);
            }
        }
    }
}
