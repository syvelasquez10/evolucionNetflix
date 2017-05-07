// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Error;

public interface SocialLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_ACTION_RESPONSE", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_IMPRESSION", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_SESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_IMPRESSION_SESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_IMPRESSION_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_MESSAGE_ADDED", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_SCROLLED", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_SEARCHED", "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_READ" };
    public static final String EXTRA_CHANNEL = "channel";
    public static final String EXTRA_CMD = "cmd";
    public static final String EXTRA_DATA_CONTEXT = "dataContext";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_FRIEND_POSITIONS = "friendPositions";
    public static final String EXTRA_MSG_ID = "msgId";
    public static final String EXTRA_SOURCE = "source";
    public static final String EXTRA_STORY_ID = "storyId";
    public static final String EXTRA_SUCCESS = "success";
    public static final String EXTRA_TRACK_ID = "trackId";
    public static final String EXTRA_VIDEO_ID = "videoId";
    public static final String EXTRA_VIEW = "view";
    public static final String SOCIAL_CONNECT_ACTION_RESPONSE = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_ACTION_RESPONSE";
    public static final String SOCIAL_CONNECT_IMPRESSION = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_IMPRESSION";
    public static final String SOCIAL_CONNECT_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_SESSION_ENDED";
    public static final String SOCIAL_CONNECT_SESSION_STARTED = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_SESSION_STARTED";
    public static final String SOCIAL_IMPRESSION_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_IMPRESSION_SESSION_ENDED";
    public static final String SOCIAL_IMPRESSION_SESSION_STARTED = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_IMPRESSION_SESSION_STARTED";
    public static final String SOCIAL_RECOMMEND = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND";
    public static final String SOCIAL_RECOMMEND_MESSAGE_ADDED = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_MESSAGE_ADDED";
    public static final String SOCIAL_RECOMMEND_READ = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_READ";
    public static final String SOCIAL_RECOMMEND_SCROLLED = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_SCROLLED";
    public static final String SOCIAL_RECOMMEND_SEARCHED = "com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_SEARCHED";
    
    void createRecommendFriendSelectedEvent(final IClientLogging.ModalView p0, final FriendPosition[] p1, final int p2);
    
    void createRecommendImplicitFeedbackReadEvent(final String p0, final String p1, final int p2);
    
    void createRecommendMessageAddedEvent(final IClientLogging.ModalView p0, final int p1);
    
    void createRecommendPanelScrolledEvent(final IClientLogging.ModalView p0, final int p1);
    
    void createRecommendPanelSearchedEvent(final IClientLogging.ModalView p0, final int p1);
    
    void createSocialConnectActionResponseEvent(final Channel p0, final Source p1, final boolean p2, final Error p3);
    
    void createSocialConnectImpressionEvent(final IClientLogging.ModalView p0);
    
    void endSocialConnectSession();
    
    void endSocialImpressionSession(final boolean p0, final Error p1);
    
    boolean handleIntent(final Intent p0);
    
    void startSocialConnectSession(final Channel p0);
    
    void startSocialImpressionSession(final IClientLogging.ModalView p0, final String p1, final int p2);
    
    public enum Channel
    {
        Facebook;
    }
    
    public static class FriendPosition
    {
        private String id;
        private int position;
        private boolean searched;
        
        public FriendPosition(final String id, final int position, final boolean searched) {
            this.id = id;
            this.position = position;
            this.searched = searched;
        }
        
        public static FriendPosition fromJson(final JSONObject jsonObject) throws JSONException {
            if (jsonObject == null) {
                return null;
            }
            return new FriendPosition(jsonObject.optString("id"), jsonObject.optInt("position", 0), jsonObject.optBoolean("searched", false));
        }
        
        public static FriendPosition[] fromJsonArray(final JSONArray jsonArray) throws JSONException {
            FriendPosition[] array;
            if (jsonArray == null) {
                array = new FriendPosition[0];
            }
            else {
                final FriendPosition[] array2 = new FriendPosition[jsonArray.length()];
                int n = 0;
                while (true) {
                    array = array2;
                    if (n >= array2.length) {
                        break;
                    }
                    array2[n] = fromJson(jsonArray.getJSONObject(n));
                    ++n;
                }
            }
            return array;
        }
        
        public JSONObject toJson() throws JSONException {
            final JSONObject jsonObject = new JSONObject();
            if (this.id != null) {
                jsonObject.put("id", (Object)this.id);
            }
            jsonObject.put("searched", this.searched);
            jsonObject.put("position", this.position);
            return jsonObject;
        }
    }
    
    public enum Source
    {
        BOB("BOB"), 
        MDP("MDP"), 
        NotificationCenter("NotificationCenter"), 
        OnBoarding("On-boarding"), 
        Play("Play"), 
        PostPlay("PostPlay"), 
        Postcard("Postcard"), 
        Signup("Signup"), 
        SocialFriendGallery("SocialFriendGallery"), 
        SocialRow("SocialRow");
        
        private String mValue;
        
        private Source(final String mValue) {
            this.mValue = mValue;
        }
        
        public String getValue() {
            return this.mValue;
        }
    }
}
