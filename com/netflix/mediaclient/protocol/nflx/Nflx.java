// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

public interface Nflx
{
    public static final String BROWSE_PATH = "/browse";
    public static final String HOST = "www.netflix.com";
    public static final String SCHEME = "nflx";
    public static final String SOURCE_VALUE_UKNOWN = "uknown";
    public static final String URN_SUFFIX = "::urn";
    public static final String UUID_PREFIX = "uuid:";
    
    public interface Action
    {
        public static final String ADD_TO_INSTANT_QUEUE = "iq";
        public static final String EXTERNAL_WEBSITE = "l";
        public static final String FEEDBACK = "feedback";
        public static final String HOME = "home";
        public static final String PLAY = "play";
        public static final String PLAY_SHORT = "p";
        public static final String SEARCH = "search";
        public static final String SYNC = "sync";
        public static final String VIEW_DETAILS = "view_details";
        public static final String VIEW_DETAILS_SHORT = "v";
        public static final String VIEW_GENRE = "genre";
        public static final String VIEW_GENRE_SHORT = "g";
    }
    
    public interface Parameter
    {
        public static final String ACTION = "action";
        public static final String ACTION_SHORT = "a";
        public static final String EPISODE_ID = "episodeid";
        public static final String EPISODE_ID_URI_PATH_KEY = "programs/";
        public static final String GENRE_ID = "genreid";
        public static final String MODE = "mode";
        public static final String MOVIE_ID = "movieid";
        public static final String MOVIE_ID_MOVIE_URI_PATH_KEY = "movies/";
        public static final String MOVIE_ID_SERIES_URI_PATH_KEY = "series/";
        public static final String POST_ACTION = "post_action";
        public static final String PROFILE_GATE = "profileGate";
        public static final String PROFILE_ID = "msg_token";
        public static final String PROMO_CODE = "promoCode";
        public static final String QUERY = "query";
        public static final String RESPONSE = "response";
        public static final String SOURCE = "source";
        public static final String SOURCE_SHORT = "s";
        public static final String TARGET_ID = "targetid";
        public static final String TARGET_URL = "target_url";
        public static final String TARGET_URL_SHORT = "u";
        public static final String TRACKID = "trkid";
        public static final String TRACKID_SHORT = "t";
        public static final String TYPE = "type";
    }
}
