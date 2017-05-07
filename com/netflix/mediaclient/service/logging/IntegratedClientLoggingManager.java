// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.EventQueue;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.os.SystemClock;
import com.netflix.mediaclient.service.logging.client.ClientLoggingWebClientFactory;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import java.util.Iterator;
import com.netflix.mediaclient.service.logging.client.model.SessionKey;
import com.netflix.mediaclient.service.logging.client.model.SessionEvent;
import com.netflix.mediaclient.service.logging.client.model.LoggingRequest;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.service.logging.client.ClientLoggingWebCallback;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.NetflixService;
import java.util.concurrent.atomic.AtomicLong;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.android.app.UserInputManager;
import java.util.concurrent.ScheduledExecutorService;
import java.util.Random;
import java.util.Map;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.service.logging.client.ClientLoggingWebClient;
import com.netflix.mediaclient.android.app.ApplicationStateListener;

class IntegratedClientLoggingManager implements EventHandler, ApplicationStateListener
{
    private static final int CL_MAX_TIME_THAN_EVENT_CAN_STAY_IN_QUEUE_MS = 60000;
    private static final int CL_MIN_NUMBER_OF_EVENTS_TO_POST = 30;
    private static final int DEFAULT_USER_SESSION_TIMEOUT_MS = 1800000;
    static final String REPOSITORY_DIR = "iclevents";
    private static final String TAG = "nf_log";
    private static final int USER_SESSION_START_DELTA = 2000;
    private UserActionLoggingImpl mActionLogging;
    private ApmLoggingImpl mApmLogging;
    private ClientLoggingWebClient mClientLoggingWebClient;
    private final Context mContext;
    private DataRepository mDataRepository;
    private final Map<String, Random> mEventPerSessionRndGeneratorMap;
    private final ClientLoggingEventQueue mEventQueue;
    private ScheduledExecutorService mExecutor;
    private UserInputManager mInputManager;
    private AtomicBoolean mLocalPlaybackInProgress;
    private final List<LoggingSession> mLoggingSessions;
    private final LoggingAgent mOwner;
    private final BroadcastReceiver mPlayerReceiver;
    private SearchLogging mSearchLogging;
    private final AtomicLong mSequence;
    private final NetflixService mService;
    private SocialLoggingImpl mSocialLogging;
    private SuspendLoggingImpl mSuspendLogging;
    private UIViewLoggingImpl mUIViewLogging;
    private final ServiceAgent.UserAgentInterface mUser;
    private final Map<String, Boolean> mUserSessionEnabledStatusMap;
    
    IntegratedClientLoggingManager(final Context mContext, final LoggingAgent mOwner, final ServiceAgent.UserAgentInterface mUser, final NetflixService mService) {
        this.mSequence = new AtomicLong(1L);
        this.mLoggingSessions = Collections.synchronizedList(new ArrayList<LoggingSession>());
        this.mEventQueue = new ClientLoggingEventQueue();
        this.mUserSessionEnabledStatusMap = new HashMap<String, Boolean>();
        this.mEventPerSessionRndGeneratorMap = new HashMap<String, Random>();
        this.mLocalPlaybackInProgress = new AtomicBoolean(false);
        this.mPlayerReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (Log.isLoggable("nf_log", 2)) {
                    Log.v("nf_log", "Received intent " + intent);
                }
                final String action = intent.getAction();
                if ("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_STARTED".equals(action)) {
                    if (Log.isLoggable("nf_log", 3)) {
                        Log.d("nf_log", "Local playback started, was started " + IntegratedClientLoggingManager.this.mLocalPlaybackInProgress.get());
                    }
                    IntegratedClientLoggingManager.this.mLocalPlaybackInProgress.set(true);
                }
                else {
                    if ("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_ENDED".equals(action)) {
                        if (Log.isLoggable("nf_log", 3)) {
                            Log.d("nf_log", "Local playback ended, was started " + IntegratedClientLoggingManager.this.mLocalPlaybackInProgress.get());
                        }
                        IntegratedClientLoggingManager.this.mLocalPlaybackInProgress.set(false);
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_PAUSED".equals(action)) {
                        if (Log.isLoggable("nf_log", 3)) {
                            Log.d("nf_log", "Local playback paused, was playing " + IntegratedClientLoggingManager.this.mLocalPlaybackInProgress.get());
                        }
                        IntegratedClientLoggingManager.this.mLocalPlaybackInProgress.set(false);
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_UNPAUSED".equals(action)) {
                        if (Log.isLoggable("nf_log", 3)) {
                            Log.d("nf_log", "Local playback unpaused, was playing " + IntegratedClientLoggingManager.this.mLocalPlaybackInProgress.get());
                        }
                        IntegratedClientLoggingManager.this.mLocalPlaybackInProgress.set(true);
                        return;
                    }
                    if (Log.isLoggable("nf_log", 3)) {
                        Log.d("nf_log", "We do not support action " + action);
                    }
                }
            }
        };
        this.mOwner = mOwner;
        this.mContext = mContext;
        this.mUser = mUser;
        this.mService = mService;
    }
    
    private void checkUserSessionState() {
        if (this.mLocalPlaybackInProgress.get()) {
            Log.d("nf_log", "Local playback is in progress, consider that user just interacted with UI. Exit.");
            this.mInputManager.updateUserInteraction();
        }
        else {
            Log.d("nf_log", "Local playback is NOT in progress, check last user interaction");
            final long timeSinceLastUserInteraction = this.mInputManager.getTimeSinceLastUserInteraction();
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "Since last user interaction elapsed (sec): " + timeSinceLastUserInteraction / 1000L);
            }
            final long userSessionDurationInMs = this.getUserSessionDurationInMs();
            if (timeSinceLastUserInteraction >= userSessionDurationInMs && this.mApmLogging.isUserSessionExist()) {
                Log.d("nf_log", "It is more than 30 minutes and user session exist. End user session");
                this.mApmLogging.endUserSession(ApplicationPerformanceMetricsLogging.EndReason.timeout, System.currentTimeMillis() - timeSinceLastUserInteraction);
                return;
            }
            if (timeSinceLastUserInteraction < userSessionDurationInMs && !this.mApmLogging.isUserSessionExist()) {
                Log.d("nf_log", "It is less than 30 minutes and user session does NOT exist. Start user session");
                this.mApmLogging.startUserSession(ApplicationPerformanceMetricsLogging.Trigger.inputEvent);
            }
        }
    }
    
    private void deliverSavedPayloads(final DataRepository.Entry[] array) {
        if (array == null || array.length < 1) {
            Log.d("nf_log", "No saved events found");
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "Found " + array.length + " payloads waiting");
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            this.mExecutor.schedule(new Runnable() {
                final /* synthetic */ String val$deliveryRequestId = array[i].getKey();
                
                @Override
                public void run() {
                    IntegratedClientLoggingManager.this.loadAndSendEvent(this.val$deliveryRequestId);
                }
            }, this.mOwner.getNextTimeToDeliverAfterFailure(), TimeUnit.MILLISECONDS);
        }
    }
    
    private void flushEventQueueIfCriteriaIsFulfilled() {
        if (this.mEventQueue.flushIfCriteriaIsFulfilled()) {
            Log.d("nf_log", "Events were send recently. We reached timeout, force send");
        }
    }
    
    private long getUserSessionDurationInMs() {
        final ServiceAgent.ConfigurationAgentInterface configuration = this.mService.getConfiguration();
        if (configuration == null) {
            return 1800000L;
        }
        return 1000L * configuration.getApmUserSessionDurationInSeconds();
    }
    
    private void handleAppSessionMissing(final Event event) {
        Log.e("nf_log", "Application session is missing!");
        final ApplicationSession applicationSession = this.mApmLogging.getApplicationSession();
        if (applicationSession != null) {
            if (event.getTime() < applicationSession.getStarted()) {
                final String string = event.getName() + " event is created BEFORE application session";
                Log.e("nf_log", string);
                this.mOwner.getErrorLogging().logHandledException(string);
            }
            event.addActiveSession(applicationSession.getKey());
            return;
        }
        final String string2 = "Application session does NOT exist, can not be added to event " + event.getName();
        Log.e("nf_log", string2);
        this.mOwner.getErrorLogging().logHandledException(string2);
    }
    
    private void handleUserSessionMissing(final Event event) {
        Log.d("nf_log", "User session is missing! This may be legit");
        final UserSession userSession = this.mApmLogging.getUserSession();
        if (userSession == null) {
            final String string = "User session does NOT exist, can not be added to event " + event.getName();
            Log.e("nf_log", string);
            this.mOwner.getErrorLogging().logHandledException(string);
            return;
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "Event created: " + event.getTime());
            Log.d("nf_log", "User session created: " + userSession.getStarted());
            Log.d("nf_log", "Diff: " + (event.getTime() - userSession.getStarted()));
        }
        if (event.getTime() + 2000L < userSession.getStarted()) {
            final String string2 = event.getName() + " event is created BEFORE user session";
            Log.e("nf_log", string2);
            this.mOwner.getErrorLogging().logHandledException(string2);
            return;
        }
        event.addActiveSession(userSession.getKey());
    }
    
    private void initDataRepository() {
        Log.d("nf_log", "ICLManager::init data repository started ");
        final File file = new File(this.mContext.getFilesDir(), "iclevents");
        file.mkdirs();
        this.mDataRepository = new FileSystemDataRepositoryImpl(file);
        this.mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_log", "Check if we have not delivered events from last time our app was runnung...");
                IntegratedClientLoggingManager.this.mDataRepository.loadAll((DataRepository.LoadedCallback)new DataRepository.LoadedCallback() {
                    @Override
                    public void onLoaded(final Entry[] array) {
                        if (array != null && array.length > 0) {
                            IntegratedClientLoggingManager.this.deliverSavedPayloads(array);
                            return;
                        }
                        Log.d("nf_log", "No saved payloads found.");
                    }
                });
            }
        });
        Log.d("nf_log", "ICLManager::init data repository done ");
    }
    
    private boolean isEventSuppressed(final String s, final String s2) {
        boolean b = true;
        boolean b2 = true;
        final String sessionLookupKey = ConsolidatedLoggingUtils.createSessionLookupKey(s, s2);
        final ServiceAgent.ConfigurationAgentInterface configuration = this.mService.getConfiguration();
        if (configuration == null) {
            b2 = false;
        }
        else {
            final ConsolidatedLoggingSessionSpecification consolidatedLoggingSessionSpecification = configuration.getConsolidatedLoggingSessionSpecification(sessionLookupKey);
            if (consolidatedLoggingSessionSpecification == null) {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "CL session specification overide not found. Event can be sent for " + sessionLookupKey);
                    return true;
                }
            }
            else {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "CL session specification overide found: " + consolidatedLoggingSessionSpecification);
                }
                if (consolidatedLoggingSessionSpecification.getDisableChancePercentagePerUserSession() <= 0) {
                    Log.d("nf_log", "Event should NOT be suppressed.");
                    return true;
                }
                if (consolidatedLoggingSessionSpecification.getDisableChancePercentagePerUserSession() >= 100) {
                    Log.d("nf_log", "Event is fully suppressed.");
                    return true;
                }
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Event is suppressed with restriction that " + consolidatedLoggingSessionSpecification.getSuppressPercentagePerEvent() + " of created events will not be logged.");
                }
                Random random;
                if ((random = this.mEventPerSessionRndGeneratorMap.get(sessionLookupKey)) == null) {
                    random = new Random();
                    this.mEventPerSessionRndGeneratorMap.put(sessionLookupKey, random);
                }
                final int nextInt = random.nextInt(100);
                if (nextInt < consolidatedLoggingSessionSpecification.getSuppressPercentagePerEvent()) {
                    b = false;
                }
                b2 = b;
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Rnd value " + nextInt + ", event can be sent" + b);
                    return b;
                }
            }
        }
        return b2;
    }
    
    private boolean isKids() {
        final ServiceAgent.UserAgentInterface mUser = this.mUser;
        if (mUser == null) {
            Log.w("nf_log", "getUiMode:: getUserAgent is null! isKids() = false");
            return false;
        }
        if (!mUser.isUserLoggedIn()) {
            Log.d("nf_log", "getUiMode:: user is NOT logged in. isKids() = false");
            return false;
        }
        final UserProfile currentProfile = mUser.getCurrentProfile();
        if (currentProfile == null) {
            Log.w("nf_log", "getUiMode:: user is logged in, but profile is null. isKids() = false");
            return false;
        }
        return currentProfile.isKidsProfile();
    }
    
    private void loadAndSendEvent(final String s) {
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "Load event " + s);
        }
        this.mDataRepository.load(s, (DataRepository.DataLoadedCallback)new DataRepository.DataLoadedCallback() {
            @Override
            public void onDataLoaded(String s, final byte[] array, final long n) {
                if (array == null || array.length < 1) {
                    Log.e("nf_log", "We failed to retrieve payload. Trying to delete it");
                    IntegratedClientLoggingManager.this.removeSavedEvents(s);
                    return;
                }
                try {
                    s = new String(array, "utf-8");
                    IntegratedClientLoggingManager.this.mClientLoggingWebClient.sendLoggingEvents(s, s, new ClientLoggingWebCallbackImpl(s));
                }
                catch (Throwable t) {
                    Log.e("nf_log", "Failed to send events. Try to delete it.", t);
                    IntegratedClientLoggingManager.this.removeSavedEvents(s);
                }
            }
        });
    }
    
    private void registerReceivers() {
        IntentUtils.registerSafelyLocalBroadcastReceiver(this.mContext, this.mPlayerReceiver, "com.netflix.mediaclient.intent.category.PLAYER", "com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_STARTED", "com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_ENDED", "com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_PAUSED", "com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_UNPAUSED");
    }
    
    private void removeSavedEvents(final String s) {
        try {
            this.mDataRepository.remove(s);
        }
        catch (Throwable t) {
            Log.e("nf_log", "Failed to remove payload from repository", t);
        }
    }
    
    private String saveEvents(String save) {
        try {
            save = this.mDataRepository.save(String.valueOf(System.currentTimeMillis()), save.getBytes("utf-8"));
            return save;
        }
        catch (Throwable t) {
            Log.e("nf_log", "Failed to save payload to repository", t);
            return null;
        }
    }
    
    private void sendEvents(final List<Event> list) {
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "Send events " + list.size());
        }
        final LoggingRequest loggingRequest = new LoggingRequest(this.mContext, this.mService.getConfiguration(), this.mUser, this.mService.getCurrentAppLocale());
        loggingRequest.addAllEvent(list);
        try {
            final String string = loggingRequest.toJSONObject().toString();
            if (Log.isLoggable("nf_log", 2)) {
                Log.v("nf_log", "Payload for log request: ");
                Log.dumpVerbose("nf_log", string);
            }
            this.mClientLoggingWebClient.sendLoggingEvents(this.saveEvents(string), string, new ClientLoggingWebCallbackImpl(string));
        }
        catch (Exception ex) {
            Log.e("nf_log", "Failed to create JSON object for logging request", ex);
        }
    }
    
    private void unRegisterReceivers() {
        IntentUtils.unregisterSafelyLocalBroadcastReceiver(this.mContext, this.mPlayerReceiver);
    }
    
    private void validateActiveSession(final Event event) {
        if (event instanceof SessionEvent && "appSession".equals(((SessionEvent)event).getSessionName())) {
            Log.d("nf_log", "Do not check app session start event, skip");
            return;
        }
        final List<SessionKey> allActiveSessions = event.getAllActiveSessions();
        int n = 0;
        int n2 = 0;
        final ArrayList<SessionKey> list = new ArrayList<SessionKey>();
        for (final SessionKey sessionKey : allActiveSessions) {
            int n3 = n;
            if ("appSession".equals(sessionKey.getName())) {
                if (sessionKey.getId() == null) {
                    Log.e("nf_log", "Application session id was missing! Remove session key!");
                    list.add(sessionKey);
                    n3 = n;
                }
                else {
                    n3 = n + 1;
                }
            }
            n = n3;
            if ("userSession".equals(sessionKey.getName())) {
                if (sessionKey.getId() == null) {
                    Log.e("nf_log", "User session id is missing!! Remove session key!");
                    list.add(sessionKey);
                    n = n3;
                }
                else {
                    ++n2;
                    n = n3;
                }
            }
        }
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            event.removeActiveSession(iterator2.next());
        }
        if (n < 1) {
            this.handleAppSessionMissing(event);
        }
        if (n2 < 1) {
            this.handleUserSessionMissing(event);
        }
        if (n < 1 || n2 < 1) {
            Log.w("nf_log", "validate session found error");
            return;
        }
        Log.d("nf_log", "validate session done with no errors");
    }
    
    private void validateActiveSessions(final List<Event> list) {
        final Iterator<Event> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.validateActiveSession(iterator.next());
        }
    }
    
    @Override
    public void addSession(final LoggingSession loggingSession) {
        if (loggingSession == null) {
            return;
        }
        this.mLoggingSessions.add(loggingSession);
    }
    
    @Override
    public boolean canSendEvent(final String s, final String s2) {
        return this.isConsolidatedLoggingSessionEnabled(s, s2) && this.isEventSuppressed(s, s2);
    }
    
    void checkState() {
        this.flushEventQueueIfCriteriaIsFulfilled();
        this.checkUserSessionState();
    }
    
    @Override
    public void createUserSession(final com.netflix.mediaclient.javabridge.ui.Log.ResetSessionIdCallback resetSessionIdCallback) {
        this.mOwner.getNrdController().getNrdp().getLog().resetSessionID(resetSessionIdCallback);
        this.newUserSession();
    }
    
    void destroy() {
        this.unRegisterReceivers();
    }
    
    @Override
    public void executeInBackground(final Runnable runnable) {
        this.mExecutor.execute(runnable);
    }
    
    void flush() {
        this.mEventQueue.flushEvents();
    }
    
    public UserActionLogging getActionLogging() {
        return this.mActionLogging;
    }
    
    List<SessionKey> getActiveSessions() {
        final ArrayList<SessionKey> list = new ArrayList<SessionKey>();
        synchronized (this.mLoggingSessions) {
            final Iterator<LoggingSession> iterator = this.mLoggingSessions.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().getKey());
            }
        }
        // monitorexit(list2)
        return;
    }
    
    public ApplicationPerformanceMetricsLogging getApmLogging() {
        return this.mApmLogging;
    }
    
    @Override
    public String getApplicationId() {
        return this.mOwner.getNrdController().getNrdp().getLog().getAppId();
    }
    
    long getNextSequence() {
        return this.mSequence.getAndAdd(1L);
    }
    
    public SocialLoggingImpl getSocialLogging() {
        return this.mSocialLogging;
    }
    
    public UIViewLogging getUiViewLogging() {
        return this.mUIViewLogging;
    }
    
    @Override
    public String getUserSessionId() {
        return this.mOwner.getNrdController().getNrdp().getLog().getSessionId();
    }
    
    public void handleConnectivityChange(final Intent intent) {
        this.mApmLogging.handleConnectivityChange(this.mContext);
    }
    
    void handleIntent(final Intent intent) {
        final boolean portrait = DeviceUtils.isPortrait(this.mContext);
        if (this.mApmLogging.handleIntent(intent, portrait)) {
            Log.d("nf_log", "Handled by APM logger");
            return;
        }
        if (this.mActionLogging.handleIntent(intent, portrait)) {
            Log.d("nf_log", "Handled by UI Action logger");
            return;
        }
        if (this.mUIViewLogging.handleIntent(intent, portrait)) {
            Log.d("nf_log", "Handled by UI View logger");
            return;
        }
        if (this.mSearchLogging.handleIntent(intent)) {
            Log.d("nf_log", "Handled by Search logger");
            return;
        }
        if (this.mSuspendLogging.handleIntent(intent)) {
            Log.d("nf_log", "Handled by suspend logging logger");
            return;
        }
        if (this.mSocialLogging.handleIntent(intent)) {
            Log.d("nf_log", "Handled by social logging logger");
            return;
        }
        Log.w("nf_log", "Action not handled!");
    }
    
    void init(final ScheduledExecutorService mExecutor) {
        this.mExecutor = mExecutor;
        this.mInputManager = this.mOwner.getApplication().getUserInput();
        Log.d("nf_log", "ClientLoggingAgent::init web client start ");
        this.mClientLoggingWebClient = ClientLoggingWebClientFactory.create(this.mOwner.getResourceFetcher().getApiNextWebClient());
        Log.d("nf_log", "ClientLoggingAgent::init web client done ");
        this.mApmLogging = new ApmLoggingImpl(this);
        this.mActionLogging = new UserActionLoggingImpl(this);
        this.mUIViewLogging = new UIViewLoggingImpl(this);
        Log.d("nf_log", "Add ICL manager as listener on user input...");
        this.mOwner.getApplication().getUserInput().addListener(this);
        Log.d("nf_log", "Add ICL manager as listener on user input done.");
        this.mSuspendLogging = new SuspendLoggingImpl(this);
        this.mSearchLogging = new SearchLogging(this);
        this.mSocialLogging = new SocialLoggingImpl(this);
        this.initDataRepository();
        this.registerReceivers();
    }
    
    public boolean isConsolidatedLoggingSessionEnabled(String sessionLookupKey, final String s) {
        final boolean b = true;
        boolean booleanValue = true;
        sessionLookupKey = ConsolidatedLoggingUtils.createSessionLookupKey(sessionLookupKey, s);
        final Boolean b2 = this.mUserSessionEnabledStatusMap.get(sessionLookupKey);
        if (b2 != null) {
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "CL session specification overide exist and status enabled : " + b2);
            }
            booleanValue = b2;
        }
        else {
            Log.d("nf_log", "CL session cached status not found, check if overide exist");
            final ServiceAgent.ConfigurationAgentInterface configuration = this.mService.getConfiguration();
            if (configuration == null) {
                return false;
            }
            final ConsolidatedLoggingSessionSpecification consolidatedLoggingSessionSpecification = configuration.getConsolidatedLoggingSessionSpecification(sessionLookupKey);
            if (consolidatedLoggingSessionSpecification == null) {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "CL session specification overide not found. Session is enabled for " + sessionLookupKey);
                    return true;
                }
            }
            else {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "CL session specification overide found: " + consolidatedLoggingSessionSpecification);
                }
                if (consolidatedLoggingSessionSpecification.getDisableChancePercentagePerUserSession() <= 0) {
                    Log.d("nf_log", "CL session is enabled without restrictions");
                    return true;
                }
                if (consolidatedLoggingSessionSpecification.getDisableChancePercentagePerUserSession() >= 100) {
                    Log.d("nf_log", "CL session is disabled");
                    return true;
                }
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "CL session is enabled with restriction that " + consolidatedLoggingSessionSpecification.getDisableChancePercentagePerUserSession() + " of user sessions will not be logged.");
                }
                final int nextInt = new Random().nextInt(100);
                final boolean b3 = nextInt >= consolidatedLoggingSessionSpecification.getDisableChancePercentagePerUserSession() && b;
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Rnd value " + nextInt + ", session is enabled " + b3);
                }
                this.mUserSessionEnabledStatusMap.put(sessionLookupKey, b3);
                return b3;
            }
        }
        return booleanValue;
    }
    
    void newUserSession() {
        this.mEventPerSessionRndGeneratorMap.clear();
        this.mUserSessionEnabledStatusMap.clear();
    }
    
    @Override
    public void onBackground(final UserInputManager userInputManager) {
        Log.d("nf_log", "App in background");
        this.mSuspendLogging.startBackgroundingSession();
        this.mSuspendLogging.endBackgroundingSession();
        this.mSuspendLogging.startBackgroundSession();
    }
    
    @Override
    public void onForeground(final UserInputManager userInputManager) {
        Log.d("nf_log", "App in foreground");
        final long n = SystemClock.elapsedRealtime() - userInputManager.getTimeSinceLastUserInteraction();
        if (n > 0L) {
            this.mApmLogging.startUserSession(ApplicationPerformanceMetricsLogging.Trigger.resumeFromBackground, n);
        }
        else {
            this.mApmLogging.startUserSession(ApplicationPerformanceMetricsLogging.Trigger.resumeFromBackground);
        }
        this.mSuspendLogging.startResumingSession();
        this.mSuspendLogging.endResumingSession();
        this.mSuspendLogging.endBackgroundSession();
    }
    
    @Override
    public void onUiGone(final UserInputManager userInputManager) {
        Log.d("nf_log", "App ui gone");
    }
    
    @Override
    public void onUiStarted(final UserInputManager userInputManager) {
        Log.d("nf_log", "App ui started");
        final long n = SystemClock.elapsedRealtime() - userInputManager.getTimeSinceLastUserInteraction();
        if (n > 0L) {
            this.mApmLogging.startUserSession(ApplicationPerformanceMetricsLogging.Trigger.appStart, n);
            return;
        }
        this.mApmLogging.startUserSession(ApplicationPerformanceMetricsLogging.Trigger.appStart);
    }
    
    void pauseDelivery() {
        this.mEventQueue.pauseDelivery();
    }
    
    @Override
    public void post(final Event event) {
        event.addAllActiveSession(this.getActiveSessions());
        event.setSequence(this.getNextSequence());
        event.setUptime(this.mOwner.getUptime());
        event.setKids(this.isKids());
        if (event.getModalView() == null) {
            final IClientLogging.ModalView currentUiView = this.mApmLogging.getCurrentUiView();
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "UI modalView is not preset, set it to " + currentUiView);
            }
            event.setModalView(currentUiView);
        }
        else if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "UI modalView is preset to " + event.getModalView());
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "Event received " + event);
        }
        this.mEventQueue.post(event);
    }
    
    @Override
    public void removeSession(final LoggingSession loggingSession) {
        if (loggingSession != null) {
            if (this.mLoggingSessions.remove(loggingSession)) {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Session " + loggingSession.getName() + " was removed from active sessions");
                }
            }
            else if (Log.isLoggable("nf_log", 5)) {
                Log.w("nf_log", "Session " + loggingSession.getName() + " was not found in active sessions");
            }
        }
    }
    
    void resumeDelivery(final boolean b) {
        this.mEventQueue.resumeDelivery(b);
    }
    
    public void setDataContext(final DataContext dataContext) {
        this.mApmLogging.setDataContext(dataContext);
        this.mActionLogging.setDataContext(dataContext);
    }
    
    private class ClientLoggingEventQueue extends EventQueue<Event>
    {
        public ClientLoggingEventQueue() {
            super("nf_icl_queue", 30, 60000L, true, true);
        }
        
        @Override
        protected void doFlush(final List<Event> list) {
            IntegratedClientLoggingManager.this.validateActiveSessions(list);
            IntegratedClientLoggingManager.this.sendEvents(list);
        }
    }
    
    private class ClientLoggingWebCallbackImpl implements ClientLoggingWebCallback
    {
        private String payload;
        
        public ClientLoggingWebCallbackImpl(final String s) {
        }
        
        @Override
        public void onEventsDelivered(final String s) {
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "Events delivered for  " + s);
            }
            IntegratedClientLoggingManager.this.mOwner.clearFailureCounter();
            IntegratedClientLoggingManager.this.removeSavedEvents(s);
        }
        
        @Override
        public void onEventsDeliveryFailed(final String s) {
            if (Log.isLoggable("nf_log", 6)) {
                Log.e("nf_log", "Events delivery failed for  " + s);
            }
            if (StringUtils.isEmpty(s)) {
                return;
            }
            IntegratedClientLoggingManager.this.mExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    IntegratedClientLoggingManager.this.loadAndSendEvent(s);
                }
            }, IntegratedClientLoggingManager.this.mOwner.getNextTimeToDeliverAfterFailure(), TimeUnit.MILLISECONDS);
        }
    }
}
