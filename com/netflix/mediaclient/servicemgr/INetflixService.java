// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.List;

public interface INetflixService
{
    public static final String EXTRA_STATUS_CODE = "status_code";
    public static final String INTENT_CATEGORY_NETFLIX_SERVICE = "com.netflix.mediaclient.intent.category.NETFLIX_SERVICE";
    public static final String INTENT_NAME_DESTROYED = "com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_DESTROYED";
    public static final String INTENT_NAME_INIT_COMPLETE = "com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_INIT_COMPLETE";
    
    void addToQueue(final String p0, final int p1, final int p2, final int p3);
    
    void connectWithFacebook(final String p0, final int p1, final int p2);
    
    void dumpHomeLoLoMosAndVideos(final String p0, final String p1);
    
    void dumpHomeLoLoMosAndVideosToLog();
    
    void dumpHomeLoMos();
    
    void fetchCWVideos(final int p0, final int p1, final int p2, final int p3);
    
    void fetchEpisodeDetails(final String p0, final int p1, final int p2);
    
    void fetchEpisodes(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchGenreLists(final int p0, final int p1);
    
    void fetchGenreVideos(final LoMo p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchGenres(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchIQVideos(final int p0, final int p1, final int p2, final int p3);
    
    void fetchLoLoMoSummary(final String p0, final int p1, final int p2);
    
    void fetchLoMos(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchMovieDetails(final String p0, final int p1, final int p2);
    
    void fetchPostPlayVideos(final String p0, final int p1, final int p2);
    
    void fetchResource(final String p0, final IClientLogging.AssetType p1, final int p2, final int p3);
    
    void fetchSeasonDetails(final String p0, final int p1, final int p2);
    
    void fetchSeasons(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchShowDetails(final String p0, final String p1, final int p2, final int p3);
    
    void fetchSimilarVideosForPerson(final String p0, final int p1, final int p2, final int p3);
    
    void fetchSimilarVideosForQuerySuggestion(final String p0, final int p1, final int p2, final int p3);
    
    void fetchVideos(final LoMo p0, final int p1, final int p2, final int p3, final int p4);
    
    void flushCaches();
    
    List<? extends UserProfile> getAllProfiles();
    
    IClientLogging getClientLogging();
    
    ServiceAgent.ConfigurationAgentInterface getConfiguration();
    
    String getCurrentAppLocale();
    
    UserProfile getCurrentProfile();
    
    String getCurrentProfileEmail();
    
    String getCurrentProfileFirstName();
    
    String getCurrentProfileLastName();
    
    String getCurrentProfileUserId();
    
    DeviceCategory getDeviceCategory();
    
    EsnProvider getESN();
    
    IMdx getMdx();
    
    IPlayer getNflxPlayer();
    
    String getNrdpComponentVersion(final NrdpComponent p0);
    
    IPushNotification getPushNotification();
    
    SignUpParams getSignUpParams();
    
    String getSoftwareVersion();
    
    String getUserId();
    
    void hideVideo(final String p0, final int p1, final int p2);
    
    boolean isCurrentProfileFacebookConnected();
    
    boolean isCurrentProfileIQEnabled();
    
    boolean isDeviceHd();
    
    boolean isProfileSwitchingDisabled();
    
    boolean isTablet();
    
    boolean isUserLoggedIn();
    
    void logBillboardActivity(final Video p0, final BrowseAgent.BillboardActivityType p1);
    
    void loginUser(final String p0, final String p1, final int p2, final int p3);
    
    void loginUserByTokens(final ActivationTokens p0, final int p1, final int p2);
    
    void logoutUser(final int p0, final int p1);
    
    void prefetchGenreLoLoMo(final String p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final int p6, final int p7);
    
    void prefetchLoLoMo(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final boolean p6, final int p7, final int p8);
    
    void refreshProfileSwitchingStatus();
    
    void registerCallback(final INetflixServiceCallback p0);
    
    void removeFromQueue(final String p0, final int p1, final int p2);
    
    void searchNetflix(final String p0, final int p1, final int p2);
    
    void selectProfile(final String p0);
    
    void setCurrentAppLocale(final String p0);
    
    void setVideoRating(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void unregisterCallback(final INetflixServiceCallback p0);
}
