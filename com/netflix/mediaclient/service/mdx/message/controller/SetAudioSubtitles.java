// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class SetAudioSubtitles extends MdxMessage
{
    private static String PROPERTY_audio_track_id;
    private static String PROPERTY_timed_text_track_id;
    private String mAudioTrackId;
    private String mTimedTextTrackId;
    
    static {
        SetAudioSubtitles.PROPERTY_timed_text_track_id = "timed_text_track_id";
        SetAudioSubtitles.PROPERTY_audio_track_id = "audio_track_id";
    }
    
    public SetAudioSubtitles(final String mAudioTrackId, final String mTimedTextTrackId) {
        super("SET_AUDIO_SUBTITLES");
        this.mTimedTextTrackId = mTimedTextTrackId;
        this.mAudioTrackId = mAudioTrackId;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put(SetAudioSubtitles.PROPERTY_audio_track_id, (Object)this.mAudioTrackId);
            this.mJson.put(SetAudioSubtitles.PROPERTY_timed_text_track_id, (Object)this.mTimedTextTrackId);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
