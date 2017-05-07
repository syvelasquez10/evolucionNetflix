// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import java.lang.reflect.Method;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import android.os.Handler;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.view.View;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import java.util.HashSet;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfGenreSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialGroupPlaceholder;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialFriendPlaceholder;
import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialConnectPlaceholder;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;
import com.netflix.mediaclient.service.webclient.model.CWVideo;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import java.util.HashMap;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.List;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import java.util.concurrent.ExecutorService;
import java.util.Set;
import java.util.Map;

public class FalkorValidationActivity extends NetflixActivity
{
    private static final boolean DO_PREFETCH = false;
    private static final Map<Class<?>, Class<?>[]> INTERFACE_MAP;
    private static final Set<String> METHOD_IGNORE_SET;
    private static final ExecutorService SINGLE_THREAD_EXECUTOR;
    private static final String TAG = "FalkorValidationActivity";
    private static final long TASK_TIMEOUT_SECONDS = 60L;
    private static final float TEXT_SIZE = 24.0f;
    private static final int TO_LOLOMO = 19;
    private static final int TO_VIDEO = 99;
    private BrowseAccess browseAgent;
    private FalkorAccess falkorAgent;
    public List<LoMo> lomos;
    private ServiceManager manager;
    private TextView textView;
    public Map<LoMo, List<? extends Video>> videosMap;
    
    static {
        SINGLE_THREAD_EXECUTOR = Executors.newSingleThreadExecutor();
        INTERFACE_MAP = new HashMap<Class<?>, Class<?>[]>() {
            {
                ((HashMap<Class<ListOfMoviesSummary>, Class[]>)this).put(ListOfMoviesSummary.class, new Class[] { LoMo.class });
                ((HashMap<Class<CWVideo>, Class[]>)this).put(CWVideo.class, new Class[] { com.netflix.mediaclient.servicemgr.model.CWVideo.class });
                ((HashMap<Class<BillboardDetails>, Class[]>)this).put(BillboardDetails.class, new Class[] { Billboard.class });
                ((HashMap<Class<com.netflix.mediaclient.service.webclient.model.branches.Video.Summary>, Class[]>)this).put(com.netflix.mediaclient.service.webclient.model.branches.Video.Summary.class, new Class[] { Video.class });
                ((HashMap<Class<SocialConnectPlaceholder>, Class[]>)this).put(SocialConnectPlaceholder.class, new Class[] { Video.class, FriendProfilesProvider.class });
                ((HashMap<Class<SocialFriendPlaceholder>, Class[]>)this).put(SocialFriendPlaceholder.class, new Class[] { Video.class, FriendProfilesProvider.class });
                ((HashMap<Class<SocialGroupPlaceholder>, Class[]>)this).put(SocialGroupPlaceholder.class, new Class[] { Video.class, FriendProfilesProvider.class });
                ((HashMap<Class<ListOfGenreSummary>, Class[]>)this).put(ListOfGenreSummary.class, new Class[] { GenreList.class });
            }
        };
        METHOD_IGNORE_SET = new HashSet<String>() {
            {
                this.add(FalkorValidationActivity.createIgnoreKey(LoMo.class, "getId"));
                this.add(FalkorValidationActivity.createIgnoreKey(LoMo.class, "getRequestId"));
                this.add(FalkorValidationActivity.createIgnoreKey(Genre.class, "getId"));
                this.add(FalkorValidationActivity.createIgnoreKey(Genre.class, "getRequestId"));
                this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getTvCardUrl"));
                this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getHorzDispUrl"));
                this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getSquareUrl"));
            }
        };
    }
    
    public FalkorValidationActivity() {
        this.videosMap = new HashMap<LoMo, List<? extends Video>>();
    }
    
    public static String createIgnoreKey(final Class<?> clazz, final String s) {
        return clazz.getSimpleName() + "_" + s;
    }
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)FalkorValidationActivity.class);
    }
    
    private void handleResult(final Result result) {
        String string;
        if (result.isSucces()) {
            string = "Validation successful";
        }
        else {
            string = "Validation FAILED: " + result;
        }
        this.setStatus(string);
    }
    
    private boolean isSetupSuccessful() {
        Log.d("FalkorValidationActivity", "Falkor agent disabled");
        return false;
    }
    
    private void setStatus(final String text) {
        Log.d("FalkorValidationActivity", "Setting status message: " + text);
        this.textView.setText((CharSequence)text);
    }
    
    private void startValidation() {
        if (this.manager == null) {
            Log.w("FalkorValidationActivity", "Can't start validation - manager is null");
        }
        this.setContentView((View)this.textView);
        final NetflixService netflixService = (NetflixService)this.manager.getService();
        this.falkorAgent = netflixService.getFalkorAgent();
        this.browseAgent = netflixService.getBrowseAgent();
        if (!this.isSetupSuccessful()) {
            Log.i("FalkorValidationActivity", "Setup failed - can't continue validation");
            return;
        }
        Log.d("FalkorValidationActivity", "Flushing caches...");
        this.falkorAgent.flushCaches();
        this.browseAgent.flushCaches();
        ThreadUtils.assertOnMain();
        new BackgroundTask().execute(new Runnable() {
            final /* synthetic */ Handler val$handler = new Handler();
            
            private void postResult(final Result result) {
                this.val$handler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        FalkorValidationActivity.this.handleResult(result);
                    }
                });
            }
            
            private void postStatus(final String s) {
                this.val$handler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        FalkorValidationActivity.this.setStatus(s);
                    }
                });
            }
            
            private Result runTask(final TestRunnerTask<?> testRunnerTask) throws InterruptedException, ExecutionException, TimeoutException {
                this.postStatus("Running task: " + testRunnerTask.getName());
                return FalkorValidationActivity.SINGLE_THREAD_EXECUTOR.submit(testRunnerTask).get(60L, TimeUnit.SECONDS);
            }
            
            @Override
            public void run() {
                ThreadUtils.assertNotOnMain();
                Object o2;
                Object o = o2 = Result.UNKNOWN;
                try {
                    final Result result = (Result)(o = (o2 = this.runTask(new TestFetchGenreListTask())));
                    if (result.isError()) {
                        return;
                    }
                    o2 = result;
                    o = result;
                    final Result result2 = (Result)(o = (o2 = this.runTask(new TestFetchLomosTask())));
                    if (result2.isError()) {
                        return;
                    }
                    o2 = result2;
                    o = result2;
                    Result result3 = (Result)(o = (o2 = this.runTask(new TestFetchCwVideosTask())));
                    if (result3.isError()) {
                        return;
                    }
                    o2 = result3;
                    o = result3;
                    final Iterator<LoMo> iterator = FalkorValidationActivity.this.lomos.iterator();
                    boolean error;
                    do {
                        o2 = result3;
                        o = result3;
                        if (!iterator.hasNext()) {
                            o2 = result3;
                            o = result3;
                            Result.OK;
                            return;
                        }
                        o2 = result3;
                        o = result3;
                        final LoMo loMo = iterator.next();
                        o2 = result3;
                        o = result3;
                        final Result result4 = (Result)(o = (o2 = this.runTask(new TestFetchVideosTask(loMo))));
                        error = result4.isError();
                        result3 = result4;
                    } while (!error);
                }
                catch (Exception ex) {
                    o = o2;
                    Log.handleException("FalkorValidationActivity", ex);
                    o = o2;
                    Result.EXCEPTION.append(ex.getMessage());
                }
                finally {
                    this.postResult((Result)o);
                }
            }
        });
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                FalkorValidationActivity.this.manager = serviceManager;
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return null;
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Button contentView = new Button((Context)this);
        contentView.setText((CharSequence)"Tap to start validation\nNote that this will clear existing caches");
        contentView.setTextSize(24.0f);
        contentView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                FalkorValidationActivity.this.startValidation();
            }
        });
        (this.textView = new TextView((Context)this)).setGravity(17);
        this.textView.setPadding(50, 50, 50, 50);
        this.textView.setTextSize(24.0f);
        this.setContentView((View)contentView);
    }
    
    private static class ObjectNotifierCallback extends LoggingManagerCallback
    {
        private List<Billboard> bbVideos;
        private List<com.netflix.mediaclient.servicemgr.model.CWVideo> cwVideos;
        private List<GenreList> genres;
        private List<LoMo> lomos;
        private final Object objectToNotify;
        private List<Video> videos;
        
        public ObjectNotifierCallback(final Object objectToNotify) {
            super("FalkorValidationActivity");
            this.objectToNotify = objectToNotify;
        }
        
        private void notifyCaller() {
            synchronized (this.objectToNotify) {
                this.objectToNotify.notify();
            }
        }
        
        @Override
        public void onBBVideosFetched(final List<Billboard> bbVideos, final Status status) {
            super.onBBVideosFetched(bbVideos, status);
            this.bbVideos = bbVideos;
            this.notifyCaller();
        }
        
        @Override
        public void onCWVideosFetched(final List<com.netflix.mediaclient.servicemgr.model.CWVideo> cwVideos, final Status status) {
            super.onCWVideosFetched(cwVideos, status);
            this.cwVideos = cwVideos;
            this.notifyCaller();
        }
        
        @Override
        public void onGenreListsFetched(final List<GenreList> genres, final Status status) {
            super.onGenreListsFetched(genres, status);
            this.genres = genres;
            this.notifyCaller();
        }
        
        @Override
        public void onLoLoMoPrefetched(final Status status) {
            super.onLoLoMoPrefetched(status);
            this.notifyCaller();
        }
        
        @Override
        public void onLoMosFetched(final List<LoMo> lomos, final Status status) {
            super.onLoMosFetched(lomos, status);
            this.lomos = lomos;
            this.notifyCaller();
        }
        
        @Override
        public void onVideosFetched(final List<Video> videos, final Status status) {
            super.onVideosFetched(videos, status);
            this.videos = videos;
            this.notifyCaller();
        }
    }
    
    private static class Result
    {
        private static final Result EXCEPTION;
        private static final Result INTERFACE_NOT_FOUND_IN_MAP;
        private static final Result INTERFACE_NOT_IMPLEMENTED;
        private static final Result INVOCATION_EXCEPTION;
        private static final Result LIST_SIZE_MISMATCH;
        private static final Result NULL_LIST;
        private static final Result NULL_OBJECT;
        private static final Result OK;
        private static final Result UNKNOWN;
        private static final Result VALUE_MISMATCH;
        private final String msg;
        private final ResultType type;
        
        static {
            OK = new Result(ResultType.OK);
            UNKNOWN = new Result(ResultType.NULL);
            EXCEPTION = new Result(ResultType.EXCEPTION);
            NULL_OBJECT = new Result(ResultType.NULL_OBJECT);
            LIST_SIZE_MISMATCH = new Result(ResultType.LIST_SIZE_MISMATCH);
            NULL_LIST = new Result(ResultType.NULL_LIST);
            INVOCATION_EXCEPTION = new Result(ResultType.INVOCATION_EXCEPTION);
            VALUE_MISMATCH = new Result(ResultType.VALUE_MISMATCH);
            INTERFACE_NOT_FOUND_IN_MAP = new Result(ResultType.INTERFACE_NOT_FOUND_IN_MAP);
            INTERFACE_NOT_IMPLEMENTED = new Result(ResultType.INTERFACE_NOT_IMPLEMENTED);
        }
        
        public Result(final ResultType resultType) {
            this(resultType, null);
        }
        
        public Result(final ResultType type, final String msg) {
            this.type = type;
            this.msg = msg;
        }
        
        public Result append(final String s) {
            return new Result(this.type, s);
        }
        
        public boolean isError() {
            return !this.isSucces();
        }
        
        public boolean isSucces() {
            return this.type == ResultType.OK;
        }
        
        @Override
        public String toString() {
            return "Result [type=" + this.type + ", msg=" + this.msg + "]";
        }
        
        private enum ResultType
        {
            EXCEPTION, 
            INTERFACE_NOT_FOUND_IN_MAP, 
            INTERFACE_NOT_IMPLEMENTED, 
            INVOCATION_EXCEPTION, 
            LIST_SIZE_MISMATCH, 
            NULL, 
            NULL_LIST, 
            NULL_OBJECT, 
            OK, 
            TYPE_MISMATCH, 
            VALUE_MISMATCH;
        }
    }
    
    private class TestFetchCwVideosTask extends TestRunnerTask<List<? extends Video>>
    {
        protected List<? extends Video> getOutput(final ObjectNotifierCallback objectNotifierCallback) {
            return objectNotifierCallback.cwVideos;
        }
        
        @Override
        protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
            browseAccess.fetchCWVideos(0, 99, n, n2);
        }
        
        @Override
        protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
            falkorAccess.fetchCWVideos(0, 99, n, n2);
        }
        
        @Override
        protected void storeResults(final ObjectNotifierCallback objectNotifierCallback) {
        }
    }
    
    private class TestFetchGenreListTask extends TestRunnerTask<List<GenreList>>
    {
        protected List<GenreList> getOutput(final ObjectNotifierCallback objectNotifierCallback) {
            return objectNotifierCallback.genres;
        }
        
        @Override
        protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
            browseAccess.fetchGenreLists(n, n2);
        }
        
        @Override
        protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
            falkorAccess.fetchGenreLists(n, n2);
        }
        
        @Override
        protected void storeResults(final ObjectNotifierCallback objectNotifierCallback) {
        }
    }
    
    private class TestFetchLomosTask extends TestRunnerTask<List<LoMo>>
    {
        protected List<LoMo> getOutput(final ObjectNotifierCallback objectNotifierCallback) {
            return objectNotifierCallback.lomos;
        }
        
        @Override
        protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
            browseAccess.fetchLoMos(0, 19, n, n2);
        }
        
        @Override
        protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
            falkorAccess.fetchLoMos(0, 19, n, n2);
        }
        
        @Override
        protected void storeResults(final ObjectNotifierCallback objectNotifierCallback) {
            FalkorValidationActivity.this.lomos = objectNotifierCallback.lomos;
        }
    }
    
    private class TestFetchVideosTask extends TestRunnerTask<List<? extends Video>>
    {
        private final LoMo lomo;
        
        public TestFetchVideosTask(final LoMo lomo) {
            super("TestGetVideosTask [lomo: " + lomo.getTitle() + "]");
            this.lomo = lomo;
        }
        
        protected List<? extends Video> getOutput(final ObjectNotifierCallback objectNotifierCallback) {
            if (this.lomo.isBillboard()) {
                return objectNotifierCallback.bbVideos;
            }
            return objectNotifierCallback.videos;
        }
        
        @Override
        protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
            browseAccess.fetchVideos(this.lomo, 0, 99, n, n2);
        }
        
        @Override
        protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
            falkorAccess.fetchVideos(this.lomo, 0, 99, n, n2);
        }
        
        @Override
        protected void storeResults(final ObjectNotifierCallback objectNotifierCallback) {
            if (this.lomo.isBillboard()) {
                FalkorValidationActivity.this.videosMap.put(this.lomo, objectNotifierCallback.bbVideos);
            }
            FalkorValidationActivity.this.videosMap.put(this.lomo, objectNotifierCallback.videos);
        }
    }
    
    private class TestPrefetchTask extends TestRunnerTask<Void>
    {
        protected Void getOutput(final ObjectNotifierCallback objectNotifierCallback) {
            return null;
        }
        
        @Override
        protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
            browseAccess.prefetchLoLoMo(0, 19, 0, 99, 0, 99, false, false, n, n2);
        }
        
        @Override
        protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
            falkorAccess.prefetchLoLoMo(0, 19, 0, 99, 0, 99, false, false, n, n2);
        }
        
        @Override
        protected boolean shouldValidate() {
            return false;
        }
        
        @Override
        protected void storeResults(final ObjectNotifierCallback objectNotifierCallback) {
        }
    }
    
    private abstract class TestRunnerTask<T> implements Callable<Result>
    {
        private final String name;
        
        protected TestRunnerTask() {
            this.name = this.getClass().getSimpleName();
        }
        
        protected TestRunnerTask(final String name) {
            this.name = name;
        }
        
        private Result validate(final Object o, final Object o2) {
            if (o instanceof List) {
                return this.validateList((List<?>)o, (List<?>)o2);
            }
            if (o == null || o2 == null) {
                return Result.NULL_OBJECT;
            }
            Log.d("FalkorValidationActivity", "Validating o1: " + o.getClass() + ", vs. o2: " + o2.getClass());
            final Class[] array = FalkorValidationActivity.INTERFACE_MAP.get(o.getClass());
            if (array == null) {
                return Result.INTERFACE_NOT_FOUND_IN_MAP.append(o.getClass().getCanonicalName());
            }
            for (int length = array.length, i = 0; i < length; ++i) {
                final Class clazz = array[i];
                if (!clazz.isInstance(o)) {
                    return Result.INTERFACE_NOT_IMPLEMENTED.append(o.getClass().getName() + " does not implement " + clazz.getCanonicalName());
                }
                if (!clazz.isInstance(o2)) {
                    return Result.INTERFACE_NOT_IMPLEMENTED.append(o2.getClass().getName() + " does not implement " + clazz.getCanonicalName());
                }
            }
            for (int length2 = array.length, j = 0; j < length2; ++j) {
                final Class clazz2 = array[j];
                Log.d("FalkorValidationActivity", "Getting methods for interface: " + clazz2);
                final Method[] methods = clazz2.getMethods();
                for (int length3 = methods.length, k = 0; k < length3; ++k) {
                    final Method method = methods[k];
                    if (method.getGenericParameterTypes().length > 0) {
                        Log.d("FalkorValidationActivity", "Skipping method because it requires input params: " + method.getName());
                    }
                    else {
                        final String ignoreKey = FalkorValidationActivity.createIgnoreKey(clazz2, method.getName());
                        if (!FalkorValidationActivity.METHOD_IGNORE_SET.contains(ignoreKey)) {
                            try {
                                final Object invoke = method.invoke(o, new Object[0]);
                                final Object invoke2 = method.invoke(o2, new Object[0]);
                                final String string = "Testing method: " + method.getName() + ", return type: " + method.getReturnType() + ", values: " + invoke + ", " + invoke2;
                                Log.d("FalkorValidationActivity", string);
                                if (invoke == null) {
                                    if (invoke2 != null) {
                                        return Result.VALUE_MISMATCH.append(string);
                                    }
                                    continue;
                                }
                                else {
                                    if (!invoke.equals(invoke2)) {
                                        return Result.VALUE_MISMATCH.append(string);
                                    }
                                    continue;
                                }
                            }
                            catch (Exception ex) {
                                Log.d("FalkorValidationActivity", "Exception testing method: " + method.getName() + ", return type: " + method.getReturnType());
                                Log.handleException("FalkorValidationActivity", ex);
                                return Result.INVOCATION_EXCEPTION.append(ex.getMessage());
                            }
                            break;
                        }
                        Log.d("FalkorValidationActivity", "Skipping method due to override: " + ignoreKey);
                    }
                }
            }
            return Result.OK;
        }
        
        private Result validateList(final List<?> list, final List<?> list2) {
            Object o;
            if (list == null || list2 == null) {
                o = Result.NULL_LIST;
            }
            else {
                final String string = "List o1 size: " + list.size() + ", list o2 size: " + list2.size();
                if (list.size() != list2.size()) {
                    return Result.LIST_SIZE_MISMATCH.append(string);
                }
                Log.d("FalkorValidationActivity", string);
                for (int i = 0; i < list.size(); ++i) {
                    if (((Result)(o = this.validate(list.get(i), list2.get(i)))).isError()) {
                        return (Result)o;
                    }
                }
                return Result.OK;
            }
            return (Result)o;
        }
        
        @Override
        public Result call() throws Exception {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: new             Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;
            //     3: dup            
            //     4: aload_0        
            //     5: invokespecial   com/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback.<init>:(Ljava/lang/Object;)V
            //     8: astore_1       
            //     9: aload_0        
            //    10: aload_0        
            //    11: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
            //    14: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$1100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/service/falkor/FalkorAccess;
            //    17: aload_0        
            //    18: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
            //    21: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/servicemgr/ServiceManager;
            //    24: invokevirtual   com/netflix/mediaclient/servicemgr/ServiceManager.getClientId:()I
            //    27: aload_0        
            //    28: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
            //    31: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/servicemgr/ServiceManager;
            //    34: aload_1        
            //    35: invokevirtual   com/netflix/mediaclient/servicemgr/ServiceManager.getRequestId:(Lcom/netflix/mediaclient/servicemgr/ManagerCallback;)I
            //    38: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.makeFalkorRequest:(Lcom/netflix/mediaclient/service/falkor/FalkorAccess;II)V
            //    41: aload_0        
            //    42: monitorenter   
            //    43: aload_0        
            //    44: invokevirtual   java/lang/Object.wait:()V
            //    47: aload_0        
            //    48: monitorexit    
            //    49: new             Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;
            //    52: dup            
            //    53: aload_0        
            //    54: invokespecial   com/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback.<init>:(Ljava/lang/Object;)V
            //    57: astore_2       
            //    58: aload_0        
            //    59: aload_0        
            //    60: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
            //    63: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$1200:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/service/BrowseAccess;
            //    66: aload_0        
            //    67: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
            //    70: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/servicemgr/ServiceManager;
            //    73: invokevirtual   com/netflix/mediaclient/servicemgr/ServiceManager.getClientId:()I
            //    76: aload_0        
            //    77: getfield        com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.this$0:Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;
            //    80: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity.access$100:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity;)Lcom/netflix/mediaclient/servicemgr/ServiceManager;
            //    83: aload_2        
            //    84: invokevirtual   com/netflix/mediaclient/servicemgr/ServiceManager.getRequestId:(Lcom/netflix/mediaclient/servicemgr/ManagerCallback;)I
            //    87: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.makeBrowseRequest:(Lcom/netflix/mediaclient/service/BrowseAccess;II)V
            //    90: aload_0        
            //    91: monitorenter   
            //    92: aload_0        
            //    93: invokevirtual   java/lang/Object.wait:()V
            //    96: aload_0        
            //    97: monitorexit    
            //    98: aload_0        
            //    99: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.shouldValidate:()Z
            //   102: ifeq            144
            //   105: aload_0        
            //   106: aload_0        
            //   107: aload_2        
            //   108: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.getOutput:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;)Ljava/lang/Object;
            //   111: aload_0        
            //   112: aload_1        
            //   113: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.getOutput:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;)Ljava/lang/Object;
            //   116: invokespecial   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.validate:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$Result;
            //   119: astore_2       
            //   120: aload_2        
            //   121: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$Result.isSucces:()Z
            //   124: ifeq            132
            //   127: aload_0        
            //   128: aload_1        
            //   129: invokevirtual   com/netflix/mediaclient/android/activity/FalkorValidationActivity$TestRunnerTask.storeResults:(Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$ObjectNotifierCallback;)V
            //   132: aload_2        
            //   133: areturn        
            //   134: astore_1       
            //   135: aload_0        
            //   136: monitorexit    
            //   137: aload_1        
            //   138: athrow         
            //   139: astore_1       
            //   140: aload_0        
            //   141: monitorexit    
            //   142: aload_1        
            //   143: athrow         
            //   144: invokestatic    com/netflix/mediaclient/android/activity/FalkorValidationActivity$Result.access$600:()Lcom/netflix/mediaclient/android/activity/FalkorValidationActivity$Result;
            //   147: areturn        
            //    Exceptions:
            //  throws java.lang.Exception
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  43     49     134    139    Any
            //  92     98     139    144    Any
            //  135    137    134    139    Any
            //  140    142    139    144    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0132:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public String getName() {
            return this.name;
        }
        
        protected abstract T getOutput(final ObjectNotifierCallback p0);
        
        protected abstract void makeBrowseRequest(final BrowseAccess p0, final int p1, final int p2);
        
        protected abstract void makeFalkorRequest(final FalkorAccess p0, final int p1, final int p2);
        
        protected boolean shouldValidate() {
            return true;
        }
        
        protected abstract void storeResults(final ObjectNotifierCallback p0);
    }
}
