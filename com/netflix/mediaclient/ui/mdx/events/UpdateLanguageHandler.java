// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;
import com.netflix.mediaclient.ui.mdx.MdxSubtitle;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.mdx.MdxAudioSource;
import org.json.JSONArray;

public class UpdateLanguageHandler extends EventHandler
{
    UpdateLanguageHandler() {
        super("com.netflix.mediaclient.intent.action.MDXUPDATE_AUDIOSUB");
    }
    
    private MdxAudioSource[] getAudioSources(final JSONArray jsonArray) throws JSONException {
        MdxAudioSource[] array;
        if (jsonArray == null || jsonArray.length() < 1) {
            Log.w("mdxui", "Empty audio list");
            array = new MdxAudioSource[0];
        }
        else {
            final MdxAudioSource[] array2 = new MdxAudioSource[jsonArray.length()];
            int n = 0;
            while (true) {
                array = array2;
                if (n >= array2.length) {
                    break;
                }
                array2[n] = MdxAudioSource.newInstance(jsonArray.getJSONObject(n), n);
                if (Log.isLoggable("mdxui", 3)) {
                    Log.d("mdxui", "" + n + ": " + array2[n]);
                }
                ++n;
            }
        }
        return array;
    }
    
    private int getCurrentAudioIndex(final MdxAudioSource[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].isSelected()) {
                return i;
            }
        }
        Log.d("mdxui", "None is selected, default to 0");
        return 0;
    }
    
    private int getCurrentSubtitleIndex(final MdxSubtitle[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].isSelected()) {
                return i;
            }
        }
        Log.d("mdxui", "None is selected, default to 0");
        return 0;
    }
    
    private MdxSubtitle[] getSubtitles(final JSONArray jsonArray) throws JSONException {
        MdxSubtitle[] array;
        if (jsonArray == null || jsonArray.length() < 1) {
            Log.w("mdxui", "Empty subtitle list");
            array = new MdxSubtitle[0];
        }
        else {
            final MdxSubtitle[] array2 = new MdxSubtitle[jsonArray.length()];
            int n = 0;
            while (true) {
                array = array2;
                if (n >= array2.length) {
                    break;
                }
                array2[n] = MdxSubtitle.newInstance(jsonArray.getJSONObject(n), n);
                if (Log.isLoggable("mdxui", 3)) {
                    Log.d("mdxui", "" + n + ": " + array2[n]);
                }
                ++n;
            }
        }
        return array;
    }
    
    @Override
    public void handle(final RemotePlaybackListener remotePlaybackListener, final Intent intent) {
        Log.d("mdxui", "Update audio/subtitles...");
        final String stringExtra = intent.getStringExtra("stringBlob");
        if (Log.isLoggable("mdxui", 3)) {
            Log.d("mdxui", "Blob: " + stringExtra);
        }
        while (true) {
            while (true) {
                JSONArray jsonArray2 = null;
                Label_0220: {
                    try {
                        final JSONObject jsonObject = new JSONObject(stringExtra);
                        JSONArray jsonArray;
                        if ((jsonArray = JsonUtils.getJSONArray(jsonObject, "timed_text_tracks")) == null) {
                            jsonArray = JsonUtils.getJSONArray(jsonObject, "timed_text_track");
                        }
                        if ((jsonArray2 = JsonUtils.getJSONArray(jsonObject, "audio_tracks")) == null) {
                            jsonArray2 = JsonUtils.getJSONArray(jsonObject, "audio_track");
                        }
                        if (Log.isLoggable("mdxui", 3)) {
                            if (jsonArray == null) {
                                Log.d("mdxui", "subtitleTrackListJsonArray is null");
                            }
                            else {
                                Log.d("mdxui", "subtitleTrackListJsonArray.lenght: " + jsonArray.length());
                            }
                            if (jsonArray2 != null) {
                                break Label_0220;
                            }
                            Log.d("mdxui", "audioTrackListJsonArray is null");
                        }
                        final MdxSubtitle[] subtitles = this.getSubtitles(jsonArray);
                        final MdxAudioSource[] audioSources = this.getAudioSources(jsonArray2);
                        remotePlaybackListener.updateLanguage(new Language(audioSources, this.getCurrentAudioIndex(audioSources), subtitles, this.getCurrentSubtitleIndex(subtitles), true));
                        return;
                    }
                    catch (Exception ex) {
                        Log.e("mdxui", "Failed to extract capability data ", ex);
                        return;
                    }
                }
                Log.d("mdxui", "audioTrackListJsonArray.lenght: " + jsonArray2.length());
                continue;
            }
        }
    }
}
