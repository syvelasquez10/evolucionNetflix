// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;

public class MdxPostplayState
{
    private static final String KEY_POSTPLATSTATE_STATE = "state";
    private static final String KEY_POSTPLATSTATE_TITLE = "title";
    private static final String KEY_POSTPLATSTATE_TITLE_ID = "id";
    private static final String KEY_POSTPLATSTATE_TITLE_TYPE = "type";
    private static final String POSTPLAYSTATE_COUNTDOWN = "POST_PLAY_COUNTDOWN";
    private static final String POSTPLAYSTATE_PROMPT = "POST_PLAY_PROMPT";
    private static final String TITLE_TYPE_EPISODE = "episode";
    private PostplayTitle[] mPostplatTitleArray;
    private String mState;
    
    public MdxPostplayState(final String s) {
        Log.i(MdxPostplayState.class.getSimpleName(), s);
        try {
            final JSONObject jsonObject = new JSONObject(s);
            this.mState = jsonObject.optString("state");
            final JSONObject jsonObject2 = jsonObject.getJSONObject("title");
            (this.mPostplatTitleArray = new PostplayTitle[1])[0] = new PostplayTitle(jsonObject2.optInt("id", -1), jsonObject2.optString("type"));
        }
        catch (JSONException ex) {
            Log.e(MdxPostplayState.class.getSimpleName(), "JSON error " + s);
        }
    }
    
    public PostplayTitle[] getPostplayTitle() {
        return this.mPostplatTitleArray;
    }
    
    public boolean isInCountdown() {
        return "POST_PLAY_COUNTDOWN".equals(this.mState);
    }
    
    public boolean isInPrompt() {
        return "POST_PLAY_PROMPT".equals(this.mState);
    }
    
    public class PostplayTitle
    {
        private int mTitleId;
        private String mTitleType;
        
        PostplayTitle(final int mTitleId, final String mTitleType) {
            this.mTitleId = -1;
            this.mTitleId = mTitleId;
            this.mTitleType = mTitleType;
        }
        
        public int getId() {
            return this.mTitleId;
        }
        
        public boolean isEpisode() {
            return this.mTitleType != null && this.mTitleType.contains("episode");
        }
    }
}
