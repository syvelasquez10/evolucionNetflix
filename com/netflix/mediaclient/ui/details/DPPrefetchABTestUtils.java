// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.service.falkor.FalkorAgentStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.logging.perf.Events;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.persistent.PersistentConfigurable;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.service.configuration.persistent.DPPrefetchABTestConfig;
import android.content.Context;
import java.util.NoSuchElementException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Iterator;
import java.util.Collections;
import java.util.HashSet;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.ArrayDeque;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.List;
import java.util.Map;

public final class DPPrefetchABTestUtils
{
    private static final int CELL_FIVE_PREFETCH_COUNT = 3;
    private static final int CELL_FOUR_PREFETCH_COUNT = 1;
    public static final int DP_PREFETCH_REQUEST_LATCH_TIMEOUT_MS = 20000;
    public static final String DP_TTI_COMPLETION_REASON_PARAM_KEY = "reason";
    public static final int MAX_PARALLEL_PREFETCH_REQUEST_COUNT = 2;
    public static final String PARAM_KEY_IS_FROM_CACHE = "isFromCache";
    private static final Object REQUEST_TAG;
    private static final String TAG = "DPPrefetchABTestUtils";
    private static volatile Map<String, List<CountDownLatch>> pendingDetailsRequestsMap;
    private static volatile Set<String> pendingDetailsRequestsSet;
    private static AtomicInteger prefetchCounter;
    private static HashMap<Video, BrowseAgentCallback> prefetchDPCallbackMap;
    private static ArrayDeque<Video> prefetchDPQueue;
    
    static {
        REQUEST_TAG = new Object();
        DPPrefetchABTestUtils.prefetchDPQueue = new ArrayDeque<Video>();
        DPPrefetchABTestUtils.prefetchDPCallbackMap = new HashMap<Video, BrowseAgentCallback>();
        DPPrefetchABTestUtils.prefetchCounter = new AtomicInteger();
    }
    
    public static void addToPendingDetailsRequest(final String s) {
        ThreadUtils.assertNotOnMain();
        if (DPPrefetchABTestUtils.pendingDetailsRequestsSet == null) {
            DPPrefetchABTestUtils.pendingDetailsRequestsSet = Collections.synchronizedSet(new HashSet<String>());
        }
        DPPrefetchABTestUtils.pendingDetailsRequestsSet.add(s);
    }
    
    public static void addToQueue(final List<? extends Video> list, final BrowseAgentCallback browseAgentCallback) {
        for (final Video video : list) {
            if (DPPrefetchABTestUtils.prefetchDPQueue.contains(video)) {
                DPPrefetchABTestUtils.prefetchDPQueue.remove(video);
                DPPrefetchABTestUtils.prefetchDPCallbackMap.remove(video);
            }
            DPPrefetchABTestUtils.prefetchDPQueue.addFirst(video);
            DPPrefetchABTestUtils.prefetchDPCallbackMap.put(video, browseAgentCallback);
        }
    }
    
    public static void cancelPrefetchDPRequests(final ServiceManager serviceManager) {
        if (serviceManager != null && isInTest(serviceManager.getContext())) {
            serviceManager.cancelRequests(getRequestTag());
        }
        DPPrefetchABTestUtils.prefetchDPQueue.clear();
        DPPrefetchABTestUtils.prefetchDPCallbackMap.clear();
    }
    
    public static int decrementPrefetchCounter() {
        return DPPrefetchABTestUtils.prefetchCounter.getAndDecrement();
    }
    
    public static Video getNextPrefetchVideo() {
        try {
            if (DPPrefetchABTestUtils.prefetchDPQueue.isEmpty()) {
                return null;
            }
            return DPPrefetchABTestUtils.prefetchDPQueue.pop();
        }
        catch (NoSuchElementException ex) {
            if (Log.isLoggable()) {
                Log.d("DPPrefetchABTestUtils", "getNextPrefetchVideo(): ignore NoSuchElementException - " + ex);
            }
            return null;
        }
    }
    
    public static int getPrefetchCounter() {
        return DPPrefetchABTestUtils.prefetchCounter.get();
    }
    
    public static Object getRequestTag() {
        return DPPrefetchABTestUtils.REQUEST_TAG;
    }
    
    public static int incrementPrefetchCounter() {
        return DPPrefetchABTestUtils.prefetchCounter.getAndIncrement();
    }
    
    public static boolean isInTest(final Context context) {
        return context != null && PersistentConfig.getCellForTest(DPPrefetchABTestConfig.class, context) != ABTestConfig$Cell.CELL_ONE;
    }
    
    private static boolean isInputValid(final ServiceManager serviceManager, final List<? extends Video> list) {
        if (list == null || list.isEmpty()) {
            Log.d("DPPrefetchABTestUtils", "isInputValid: videos list is empty");
            return false;
        }
        if (serviceManager == null || !serviceManager.isReady() || !isInTest(serviceManager.getContext())) {
            Log.d("DPPrefetchABTestUtils", "isInputValid: manager not ready or not in AB test");
            return false;
        }
        return true;
    }
    
    public static boolean isPrefetchDPRequestInFlight(final String s) {
        return DPPrefetchABTestUtils.pendingDetailsRequestsSet != null && DPPrefetchABTestUtils.pendingDetailsRequestsSet.contains(s);
    }
    
    public static boolean isPrefetchQueueEmpty() {
        return DPPrefetchABTestUtils.prefetchDPQueue.isEmpty();
    }
    
    public static void latchToPendingRequestsIfExists(final String s) {
        ThreadUtils.assertNotOnMain();
        if (!isPrefetchDPRequestInFlight(s)) {
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        if (DPPrefetchABTestUtils.pendingDetailsRequestsMap == null) {
            DPPrefetchABTestUtils.pendingDetailsRequestsMap = Collections.synchronizedMap(new HashMap<String, List<CountDownLatch>>());
        }
        Label_0159: {
            if (!DPPrefetchABTestUtils.pendingDetailsRequestsMap.containsKey(s)) {
                break Label_0159;
            }
            List<CountDownLatch> list;
            if ((list = DPPrefetchABTestUtils.pendingDetailsRequestsMap.get(s)) == null) {
                list = new ArrayList<CountDownLatch>();
            }
            list.add(countDownLatch);
            try {
                while (true) {
                    if (Log.isLoggable()) {
                        Log.d("DPPrefetchABTestUtils", "Waiting on prefetch DP response for videoId - " + s);
                    }
                    PerformanceProfiler.getInstance().logEvent(Events.DP_PREFETCH_REQUEST_IN_FLIGHT_EVENT, null);
                    countDownLatch.await(20000L, TimeUnit.MILLISECONDS);
                    Log.d("DPPrefetchABTestUtils", "latchToPendingRequestsIfExists: latch timed out");
                    return;
                    final ArrayList<CountDownLatch> list2 = new ArrayList<CountDownLatch>();
                    list2.add(countDownLatch);
                    DPPrefetchABTestUtils.pendingDetailsRequestsMap.put(s, list2);
                    continue;
                }
            }
            catch (InterruptedException ex) {
                Log.d("DPPrefetchABTestUtils", "latchToPendingRequestsIfExists: latch interrupted");
            }
            finally {
                DPPrefetchABTestUtils.pendingDetailsRequestsMap.remove(s);
            }
        }
    }
    
    public static void prefetchDPForLomoRow(final ServiceManager serviceManager, final BasicLoMo basicLoMo, final List<? extends Video> list, int min) {
        if (!isInputValid(serviceManager, list) || basicLoMo == null || basicLoMo.getType() == LoMoType.CHARACTERS) {
            Log.d("DPPrefetchABTestUtils", "Lomo is null or type characters");
            return;
        }
        int n = 0;
        final ABTestConfig$Cell cellForTest = PersistentConfig.getCellForTest(DPPrefetchABTestConfig.class, serviceManager.getContext());
        switch (DPPrefetchABTestUtils$2.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[cellForTest.ordinal()]) {
            case 1: {
                n = 1;
                break;
            }
            case 2: {
                n = 3;
                break;
            }
        }
        int n2 = n;
        Label_0129: {
            if (cellForTest.getCellId() >= ABTestConfig$Cell.CELL_THREE.getCellId()) {
                if (basicLoMo.getType() != LoMoType.CONTINUE_WATCHING) {
                    n2 = n;
                    if (basicLoMo.getType() != LoMoType.BILLBOARD) {
                        break Label_0129;
                    }
                }
                n2 = min;
            }
        }
        min = Math.min(n2, list.size());
        if (Log.isLoggable()) {
            Log.d("DPPrefetchABTestUtils", "prefetchDPForLomoRow: " + basicLoMo.getType() + " max videos = " + min);
        }
        prefetchVideoListDetails(serviceManager, list, min);
    }
    
    public static void prefetchDPForSearch(final ServiceManager serviceManager, final List<? extends Video> list) {
        if (!isInputValid(serviceManager, list)) {
            return;
        }
        prefetchVideoListDetails(serviceManager, list, Math.min(3, list.size()));
    }
    
    public static void prefetchDPForSimilars(final ServiceManager serviceManager, final List<? extends Video> list) {
        if (!isInputValid(serviceManager, list)) {
            return;
        }
        int n = 0;
        switch (DPPrefetchABTestUtils$2.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getCellForTest(DPPrefetchABTestConfig.class, serviceManager.getContext()).ordinal()]) {
            case 1: {
                n = 1;
                break;
            }
            case 2: {
                n = 3;
                break;
            }
        }
        prefetchVideoListDetails(serviceManager, list, Math.min(n, list.size()));
    }
    
    private static void prefetchVideoListDetails(final ServiceManager serviceManager, final List<? extends Video> list, int i) {
        if (isInputValid(serviceManager, list)) {
            List<? extends Video> subList;
            for (i = Math.min(i, list.size()); i > 0; --i) {
                subList = list.subList(i - 1, i);
                if (Log.isLoggable()) {
                    Log.d("DPPrefetchABTestUtils", "Prefetch DP submit request to queue - " + subList);
                }
                serviceManager.getBrowse().prefetchVideoListDetails(subList, new DPPrefetchABTestUtils$1());
            }
        }
    }
    
    public static void removeFromPendingDetailsRequest(final String s) {
        if (DPPrefetchABTestUtils.pendingDetailsRequestsSet != null) {
            DPPrefetchABTestUtils.pendingDetailsRequestsSet.remove(s);
            if (Log.isLoggable()) {
                Log.d("DPPrefetchABTestUtils", "Received prefetch DP response for videoId - " + s);
            }
            if (DPPrefetchABTestUtils.pendingDetailsRequestsMap != null && DPPrefetchABTestUtils.pendingDetailsRequestsMap.containsKey(s)) {
                final List<CountDownLatch> list = DPPrefetchABTestUtils.pendingDetailsRequestsMap.get(s);
                if (list != null) {
                    for (int i = 0; i < list.size(); ++i) {
                        final CountDownLatch countDownLatch = list.get(i);
                        if (countDownLatch != null) {
                            countDownLatch.countDown();
                        }
                    }
                }
                DPPrefetchABTestUtils.pendingDetailsRequestsMap.remove(s);
            }
        }
    }
    
    public static BrowseAgentCallback removePrefetchDPCallback(final Video video) {
        return DPPrefetchABTestUtils.prefetchDPCallbackMap.remove(video);
    }
    
    public static void reportDPMetadataFetchedEvent(final Status status) {
        boolean wasAllDataLocalToCache = false;
        if (status instanceof FalkorAgentStatus) {
            wasAllDataLocalToCache = ((FalkorAgentStatus)status).wasAllDataLocalToCache();
        }
        PerformanceProfiler.getInstance().logEvent(Events.DP_METADATA_FETCHED_EVENT, Collections.singletonMap("isFromCache", String.valueOf(wasAllDataLocalToCache)));
    }
}
