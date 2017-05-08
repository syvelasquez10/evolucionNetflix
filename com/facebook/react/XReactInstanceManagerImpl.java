// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.MemoryPressureListener;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.modules.debug.DeveloperSettings;
import com.facebook.react.devsupport.DevServerHelper$PackagerStatusCallback;
import android.os.AsyncTask;
import java.util.Iterator;
import com.facebook.react.bridge.JavaScriptModule;
import javax.inject.Provider;
import com.facebook.react.bridge.NativeModule;
import com.facebook.common.logging.FLog;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.bridge.JavaScriptModuleRegistry$Builder;
import com.facebook.react.module.model.ReactModuleInfo;
import java.util.Map;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.cxxbridge.JSCJavaScriptExecutor$Factory;
import com.facebook.soloader.SoLoader;
import com.facebook.react.uimanager.AppRegistry;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.cxxbridge.Arguments;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.cxxbridge.UiThreadUtil;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.cxxbridge.JavaScriptExecutor;
import com.facebook.react.cxxbridge.JavaScriptExecutor$Factory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.common.ApplicationHolder;
import android.app.Application;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.uimanager.UIImplementationProvider;
import java.util.Collection;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.DevSupportManager;
import com.facebook.react.devsupport.ReactInstanceDevCommandsHandler;
import com.facebook.react.bridge.ReactContext;
import android.app.Activity;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import java.util.List;
import android.content.Context;

class XReactInstanceManagerImpl extends ReactInstanceManager
{
    private static final String TAG;
    private final Context mApplicationContext;
    private final List<ReactRootView> mAttachedRootViews;
    private final DefaultHardwareBackBtnHandler mBackBtnHandler;
    private final NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    private final JSBundleLoader mBundleLoader;
    private Activity mCurrentActivity;
    private volatile ReactContext mCurrentReactContext;
    private DefaultHardwareBackBtnHandler mDefaultBackButtonImpl;
    private final ReactInstanceDevCommandsHandler mDevInterface;
    private final DevSupportManager mDevSupportManager;
    private volatile boolean mHasStartedCreatingInitialContext;
    private final JSCConfig mJSCConfig;
    private final String mJSMainModuleName;
    private final boolean mLazyNativeModulesEnabled;
    private final boolean mLazyViewManagersEnabled;
    private LifecycleState mLifecycleState;
    private final MemoryPressureRouter mMemoryPressureRouter;
    private final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private final List<ReactPackage> mPackages;
    private XReactInstanceManagerImpl$ReactContextInitParams mPendingReactContextInitParams;
    private XReactInstanceManagerImpl$ReactContextInitAsyncTask mReactContextInitAsyncTask;
    private final Collection<ReactInstanceManager$ReactInstanceEventListener> mReactInstanceEventListeners;
    private String mSourceUrl;
    private final UIImplementationProvider mUIImplementationProvider;
    private final boolean mUseDeveloperSupport;
    
    static {
        TAG = XReactInstanceManagerImpl.class.getSimpleName();
    }
    
    XReactInstanceManagerImpl(final Context mApplicationContext, final Activity mCurrentActivity, final DefaultHardwareBackBtnHandler mDefaultBackButtonImpl, final JSBundleLoader mBundleLoader, final String mjsMainModuleName, final List<ReactPackage> mPackages, final boolean mUseDeveloperSupport, final NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener, final LifecycleState mLifecycleState, final UIImplementationProvider muiImplementationProvider, final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler, final JSCConfig mjscConfig, final RedBoxHandler redBoxHandler, final boolean mLazyNativeModulesEnabled, final boolean mLazyViewManagersEnabled) {
        this.mAttachedRootViews = new ArrayList<ReactRootView>();
        this.mReactInstanceEventListeners = (Collection<ReactInstanceManager$ReactInstanceEventListener>)Collections.synchronizedSet(new HashSet<Object>());
        this.mHasStartedCreatingInitialContext = false;
        this.mDevInterface = new XReactInstanceManagerImpl$1(this);
        this.mBackBtnHandler = new XReactInstanceManagerImpl$2(this);
        initializeSoLoaderIfNecessary(mApplicationContext);
        ApplicationHolder.setApplication((Application)mApplicationContext.getApplicationContext());
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(mApplicationContext);
        this.mApplicationContext = mApplicationContext;
        this.mCurrentActivity = mCurrentActivity;
        this.mDefaultBackButtonImpl = mDefaultBackButtonImpl;
        this.mBundleLoader = mBundleLoader;
        this.mJSMainModuleName = mjsMainModuleName;
        this.mPackages = mPackages;
        this.mUseDeveloperSupport = mUseDeveloperSupport;
        this.mDevSupportManager = DevSupportManagerFactory.create(mApplicationContext, this.mDevInterface, this.mJSMainModuleName, mUseDeveloperSupport, redBoxHandler);
        this.mBridgeIdleDebugListener = mBridgeIdleDebugListener;
        this.mLifecycleState = mLifecycleState;
        this.mUIImplementationProvider = muiImplementationProvider;
        this.mMemoryPressureRouter = new MemoryPressureRouter(mApplicationContext);
        this.mNativeModuleCallExceptionHandler = mNativeModuleCallExceptionHandler;
        this.mJSCConfig = mjscConfig;
        this.mLazyNativeModulesEnabled = mLazyNativeModulesEnabled;
        this.mLazyViewManagersEnabled = mLazyViewManagersEnabled;
    }
    
    private void attachMeasuredRootViewToInstance(final ReactRootView reactRootView, final CatalystInstance catalystInstance) {
        Systrace.beginSection(0L, "attachMeasuredRootViewToInstance");
        UiThreadUtil.assertOnUiThread();
        reactRootView.removeAllViews();
        reactRootView.setId(-1);
        final int addMeasuredRootView = catalystInstance.getNativeModule(UIManagerModule.class).addMeasuredRootView(reactRootView);
        reactRootView.setRootViewTag(addMeasuredRootView);
        final WritableNativeMap nativeMap = Arguments.makeNativeMap(reactRootView.getLaunchOptions());
        final String jsModuleName = reactRootView.getJSModuleName();
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putDouble("rootTag", addMeasuredRootView);
        writableNativeMap.putMap("initialProps", nativeMap);
        catalystInstance.getJSModule(AppRegistry.class).runApplication(jsModuleName, writableNativeMap);
        reactRootView.onAttachedToReactInstance();
        Systrace.endSection(0L);
    }
    
    private ReactApplicationContext createReactContext(final JavaScriptExecutor p0, final JSBundleLoader p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc_w           "React"
        //     3: ldc_w           "Creating react context."
        //     6: invokestatic    com/facebook/common/logging/FLog.i:(Ljava/lang/String;Ljava/lang/String;)V
        //     9: ldc_w           "CREATE_REACT_CONTEXT_START"
        //    12: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //    15: aload_0        
        //    16: aload_2        
        //    17: invokevirtual   com/facebook/react/cxxbridge/JSBundleLoader.getSourceUrl:()Ljava/lang/String;
        //    20: putfield        com/facebook/react/XReactInstanceManagerImpl.mSourceUrl:Ljava/lang/String;
        //    23: new             Ljava/util/ArrayList;
        //    26: dup            
        //    27: invokespecial   java/util/ArrayList.<init>:()V
        //    30: astore_3       
        //    31: new             Ljava/util/HashMap;
        //    34: dup            
        //    35: invokespecial   java/util/HashMap.<init>:()V
        //    38: astore          6
        //    40: new             Lcom/facebook/react/bridge/JavaScriptModuleRegistry$Builder;
        //    43: dup            
        //    44: invokespecial   com/facebook/react/bridge/JavaScriptModuleRegistry$Builder.<init>:()V
        //    47: astore          5
        //    49: new             Lcom/facebook/react/bridge/ReactApplicationContext;
        //    52: dup            
        //    53: aload_0        
        //    54: getfield        com/facebook/react/XReactInstanceManagerImpl.mApplicationContext:Landroid/content/Context;
        //    57: invokespecial   com/facebook/react/bridge/ReactApplicationContext.<init>:(Landroid/content/Context;)V
        //    60: astore          4
        //    62: aload_0        
        //    63: getfield        com/facebook/react/XReactInstanceManagerImpl.mUseDeveloperSupport:Z
        //    66: ifeq            78
        //    69: aload           4
        //    71: aload_0        
        //    72: getfield        com/facebook/react/XReactInstanceManagerImpl.mDevSupportManager:Lcom/facebook/react/devsupport/DevSupportManager;
        //    75: invokevirtual   com/facebook/react/bridge/ReactApplicationContext.setNativeModuleCallExceptionHandler:(Lcom/facebook/react/bridge/NativeModuleCallExceptionHandler;)V
        //    78: ldc_w           "PROCESS_PACKAGES_START"
        //    81: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //    84: lconst_0       
        //    85: ldc_w           "createAndProcessCoreModulesPackage"
        //    88: invokestatic    com/facebook/systrace/Systrace.beginSection:(JLjava/lang/String;)V
        //    91: aload_0        
        //    92: new             Lcom/facebook/react/CoreModulesPackage;
        //    95: dup            
        //    96: aload_0        
        //    97: aload_0        
        //    98: getfield        com/facebook/react/XReactInstanceManagerImpl.mBackBtnHandler:Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;
        //   101: aload_0        
        //   102: getfield        com/facebook/react/XReactInstanceManagerImpl.mUIImplementationProvider:Lcom/facebook/react/uimanager/UIImplementationProvider;
        //   105: invokespecial   com/facebook/react/CoreModulesPackage.<init>:(Lcom/facebook/react/ReactInstanceManager;Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;Lcom/facebook/react/uimanager/UIImplementationProvider;)V
        //   108: aload           4
        //   110: aload_3        
        //   111: aload           6
        //   113: aload           5
        //   115: invokespecial   com/facebook/react/XReactInstanceManagerImpl.processPackage:(Lcom/facebook/react/ReactPackage;Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/util/List;Ljava/util/Map;Lcom/facebook/react/bridge/JavaScriptModuleRegistry$Builder;)V
        //   118: lconst_0       
        //   119: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   122: aload_0        
        //   123: getfield        com/facebook/react/XReactInstanceManagerImpl.mPackages:Ljava/util/List;
        //   126: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   131: astore          7
        //   133: aload           7
        //   135: invokeinterface java/util/Iterator.hasNext:()Z
        //   140: ifeq            196
        //   143: aload           7
        //   145: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   150: checkcast       Lcom/facebook/react/ReactPackage;
        //   153: astore          8
        //   155: lconst_0       
        //   156: ldc_w           "createAndProcessCustomReactPackage"
        //   159: invokestatic    com/facebook/systrace/Systrace.beginSection:(JLjava/lang/String;)V
        //   162: aload_0        
        //   163: aload           8
        //   165: aload           4
        //   167: aload_3        
        //   168: aload           6
        //   170: aload           5
        //   172: invokespecial   com/facebook/react/XReactInstanceManagerImpl.processPackage:(Lcom/facebook/react/ReactPackage;Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/util/List;Ljava/util/Map;Lcom/facebook/react/bridge/JavaScriptModuleRegistry$Builder;)V
        //   175: lconst_0       
        //   176: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   179: goto            133
        //   182: astore_1       
        //   183: lconst_0       
        //   184: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   187: aload_1        
        //   188: athrow         
        //   189: astore_1       
        //   190: lconst_0       
        //   191: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   194: aload_1        
        //   195: athrow         
        //   196: ldc_w           "PROCESS_PACKAGES_END"
        //   199: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   202: ldc_w           "BUILD_NATIVE_MODULE_REGISTRY_START"
        //   205: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   208: lconst_0       
        //   209: ldc_w           "buildNativeModuleRegistry"
        //   212: invokestatic    com/facebook/systrace/Systrace.beginSection:(JLjava/lang/String;)V
        //   215: new             Lcom/facebook/react/cxxbridge/NativeModuleRegistry;
        //   218: dup            
        //   219: aload_3        
        //   220: aload           6
        //   222: invokespecial   com/facebook/react/cxxbridge/NativeModuleRegistry.<init>:(Ljava/util/List;Ljava/util/Map;)V
        //   225: astore          6
        //   227: lconst_0       
        //   228: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   231: ldc_w           "BUILD_NATIVE_MODULE_REGISTRY_END"
        //   234: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   237: aload_0        
        //   238: getfield        com/facebook/react/XReactInstanceManagerImpl.mNativeModuleCallExceptionHandler:Lcom/facebook/react/bridge/NativeModuleCallExceptionHandler;
        //   241: ifnull          361
        //   244: aload_0        
        //   245: getfield        com/facebook/react/XReactInstanceManagerImpl.mNativeModuleCallExceptionHandler:Lcom/facebook/react/bridge/NativeModuleCallExceptionHandler;
        //   248: astore_3       
        //   249: new             Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   252: dup            
        //   253: invokespecial   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.<init>:()V
        //   256: invokestatic    com/facebook/react/bridge/queue/ReactQueueConfigurationSpec.createDefault:()Lcom/facebook/react/bridge/queue/ReactQueueConfigurationSpec;
        //   259: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setReactQueueConfigurationSpec:(Lcom/facebook/react/bridge/queue/ReactQueueConfigurationSpec;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   262: aload_1        
        //   263: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setJSExecutor:(Lcom/facebook/react/cxxbridge/JavaScriptExecutor;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   266: aload           6
        //   268: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setRegistry:(Lcom/facebook/react/cxxbridge/NativeModuleRegistry;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   271: aload           5
        //   273: invokevirtual   com/facebook/react/bridge/JavaScriptModuleRegistry$Builder.build:()Lcom/facebook/react/bridge/JavaScriptModuleRegistry;
        //   276: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setJSModuleRegistry:(Lcom/facebook/react/bridge/JavaScriptModuleRegistry;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   279: aload_2        
        //   280: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setJSBundleLoader:(Lcom/facebook/react/cxxbridge/JSBundleLoader;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   283: aload_3        
        //   284: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setNativeModuleCallExceptionHandler:(Lcom/facebook/react/bridge/NativeModuleCallExceptionHandler;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   287: astore_1       
        //   288: ldc_w           "CREATE_CATALYST_INSTANCE_START"
        //   291: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   294: lconst_0       
        //   295: ldc_w           "createCatalystInstance"
        //   298: invokestatic    com/facebook/systrace/Systrace.beginSection:(JLjava/lang/String;)V
        //   301: aload_1        
        //   302: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.build:()Lcom/facebook/react/cxxbridge/CatalystInstanceImpl;
        //   305: astore_1       
        //   306: lconst_0       
        //   307: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   310: ldc_w           "CREATE_CATALYST_INSTANCE_END"
        //   313: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   316: aload_0        
        //   317: getfield        com/facebook/react/XReactInstanceManagerImpl.mBridgeIdleDebugListener:Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;
        //   320: ifnull          333
        //   323: aload_1        
        //   324: aload_0        
        //   325: getfield        com/facebook/react/XReactInstanceManagerImpl.mBridgeIdleDebugListener:Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;
        //   328: invokeinterface com/facebook/react/bridge/CatalystInstance.addBridgeIdleDebugListener:(Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;)V
        //   333: aload           4
        //   335: aload_1        
        //   336: invokevirtual   com/facebook/react/bridge/ReactApplicationContext.initializeWithInstance:(Lcom/facebook/react/bridge/CatalystInstance;)V
        //   339: aload_1        
        //   340: invokeinterface com/facebook/react/bridge/CatalystInstance.runJSBundle:()V
        //   345: aload           4
        //   347: areturn        
        //   348: astore_1       
        //   349: lconst_0       
        //   350: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   353: ldc_w           "BUILD_NATIVE_MODULE_REGISTRY_END"
        //   356: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   359: aload_1        
        //   360: athrow         
        //   361: aload_0        
        //   362: getfield        com/facebook/react/XReactInstanceManagerImpl.mDevSupportManager:Lcom/facebook/react/devsupport/DevSupportManager;
        //   365: astore_3       
        //   366: goto            249
        //   369: astore_1       
        //   370: lconst_0       
        //   371: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   374: ldc_w           "CREATE_CATALYST_INSTANCE_END"
        //   377: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   380: aload_1        
        //   381: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  91     118    182    189    Any
        //  162    175    189    196    Any
        //  215    227    348    361    Any
        //  301    306    369    382    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0333:
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
    
    private void detachViewFromInstance(final ReactRootView reactRootView, final CatalystInstance catalystInstance) {
        UiThreadUtil.assertOnUiThread();
        catalystInstance.getJSModule(AppRegistry.class).unmountApplicationComponentAtRootTag(reactRootView.getId());
    }
    
    private static void initializeSoLoaderIfNecessary(final Context context) {
        SoLoader.init(context, false);
    }
    
    private void invokeDefaultOnBackPressed() {
        UiThreadUtil.assertOnUiThread();
        if (this.mDefaultBackButtonImpl != null) {
            this.mDefaultBackButtonImpl.invokeDefaultOnBackPressed();
        }
    }
    
    private void moveReactContextToCurrentLifecycleState() {
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            this.moveToResumedLifecycleState(true);
        }
    }
    
    private void moveToBeforeResumeLifecycleState() {
        if (this.mCurrentReactContext != null) {
            if (this.mLifecycleState == LifecycleState.BEFORE_CREATE) {
                this.mCurrentReactContext.onHostResume(this.mCurrentActivity);
                this.mCurrentReactContext.onHostPause();
            }
            else if (this.mLifecycleState == LifecycleState.RESUMED) {
                this.mCurrentReactContext.onHostPause();
            }
        }
        this.mLifecycleState = LifecycleState.BEFORE_RESUME;
    }
    
    private void moveToResumedLifecycleState(final boolean b) {
        if (this.mCurrentReactContext != null && (b || this.mLifecycleState == LifecycleState.BEFORE_RESUME || this.mLifecycleState == LifecycleState.BEFORE_CREATE)) {
            this.mCurrentReactContext.onHostResume(this.mCurrentActivity);
        }
        this.mLifecycleState = LifecycleState.RESUMED;
    }
    
    private void onJSBundleLoadedFromServer() {
        this.recreateReactContextInBackground(new JSCJavaScriptExecutor$Factory(this.mJSCConfig.getConfigMap()), JSBundleLoader.createCachedBundleFromNetworkLoader(this.mDevSupportManager.getSourceUrl(), this.mDevSupportManager.getDownloadedJSBundleFile()));
    }
    
    private void processPackage(final ReactPackage reactPackage, final ReactApplicationContext reactApplicationContext, final List<ModuleSpec> list, final Map<Class, ReactModuleInfo> map, final JavaScriptModuleRegistry$Builder javaScriptModuleRegistry$Builder) {
        SystraceMessage.beginSection(0L, "processPackage").arg("className", reactPackage.getClass().getSimpleName()).flush();
        if (this.mLazyNativeModulesEnabled && reactPackage instanceof LazyReactPackage) {
            final LazyReactPackage lazyReactPackage = (LazyReactPackage)reactPackage;
            final Map<Class, ReactModuleInfo> reactModuleInfos = lazyReactPackage.getReactModuleInfoProvider().getReactModuleInfos();
            if (!reactModuleInfos.isEmpty()) {
                map.putAll(reactModuleInfos);
            }
            list.addAll(lazyReactPackage.getNativeModules(reactApplicationContext));
        }
        else {
            FLog.d("React", reactPackage.getClass().getSimpleName() + " is not a LazyReactPackage, falling back to old version.");
            for (final NativeModule nativeModule : reactPackage.createNativeModules(reactApplicationContext)) {
                list.add(new ModuleSpec(nativeModule.getClass(), (Provider<? extends NativeModule>)new EagerModuleProvider(nativeModule)));
            }
        }
        final Iterator<Class<? extends JavaScriptModule>> iterator2 = reactPackage.createJSModules().iterator();
        while (iterator2.hasNext()) {
            javaScriptModuleRegistry$Builder.add(iterator2.next());
        }
        Systrace.endSection(0L);
    }
    
    private void recreateReactContextInBackground(final JavaScriptExecutor$Factory javaScriptExecutor$Factory, final JSBundleLoader jsBundleLoader) {
        UiThreadUtil.assertOnUiThread();
        final XReactInstanceManagerImpl$ReactContextInitParams mPendingReactContextInitParams = new XReactInstanceManagerImpl$ReactContextInitParams(this, javaScriptExecutor$Factory, jsBundleLoader);
        if (this.mReactContextInitAsyncTask == null) {
            (this.mReactContextInitAsyncTask = new XReactInstanceManagerImpl$ReactContextInitAsyncTask(this, null)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new XReactInstanceManagerImpl$ReactContextInitParams[] { mPendingReactContextInitParams });
            return;
        }
        this.mPendingReactContextInitParams = mPendingReactContextInitParams;
    }
    
    private void recreateReactContextInBackgroundFromBundleLoader() {
        this.recreateReactContextInBackground(new JSCJavaScriptExecutor$Factory(this.mJSCConfig.getConfigMap()), this.mBundleLoader);
    }
    
    private void recreateReactContextInBackgroundInner() {
        UiThreadUtil.assertOnUiThread();
        if (!this.mUseDeveloperSupport || this.mJSMainModuleName == null) {
            this.recreateReactContextInBackgroundFromBundleLoader();
            return;
        }
        final DeveloperSettings devSettings = this.mDevSupportManager.getDevSettings();
        if (this.mDevSupportManager.hasUpToDateJSBundleInCache() && !devSettings.isRemoteJSDebugEnabled()) {
            this.onJSBundleLoadedFromServer();
            return;
        }
        if (this.mBundleLoader == null) {
            this.mDevSupportManager.handleReloadJS();
            return;
        }
        this.mDevSupportManager.isPackagerRunning(new XReactInstanceManagerImpl$3(this, devSettings));
    }
    
    private void setupReactContext(final ReactApplicationContext reactApplicationContext) {
        int i = 0;
        ReactMarker.logMarker("SETUP_REACT_CONTEXT_START");
        Systrace.beginSection(0L, "setupReactContext");
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(this.mCurrentReactContext == null);
        this.mCurrentReactContext = Assertions.assertNotNull(reactApplicationContext);
        final CatalystInstance catalystInstance = Assertions.assertNotNull(reactApplicationContext.getCatalystInstance());
        catalystInstance.initialize();
        this.mDevSupportManager.onNewReactContextCreated(reactApplicationContext);
        this.mMemoryPressureRouter.addMemoryPressureListener(catalystInstance);
        this.moveReactContextToCurrentLifecycleState();
        final Iterator<ReactRootView> iterator = this.mAttachedRootViews.iterator();
        while (iterator.hasNext()) {
            this.attachMeasuredRootViewToInstance(iterator.next(), catalystInstance);
        }
        for (ReactInstanceManager$ReactInstanceEventListener[] array = this.mReactInstanceEventListeners.toArray(new ReactInstanceManager$ReactInstanceEventListener[this.mReactInstanceEventListeners.size()]); i < array.length; ++i) {
            array[i].onReactContextInitialized(reactApplicationContext);
        }
        Systrace.endSection(0L);
        ReactMarker.logMarker("SETUP_REACT_CONTEXT_END");
    }
    
    private void tearDownReactContext(final ReactContext reactContext) {
        UiThreadUtil.assertOnUiThread();
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            reactContext.onHostPause();
        }
        final Iterator<ReactRootView> iterator = this.mAttachedRootViews.iterator();
        while (iterator.hasNext()) {
            this.detachViewFromInstance(iterator.next(), reactContext.getCatalystInstance());
        }
        reactContext.destroy();
        this.mDevSupportManager.onReactInstanceDestroyed(reactContext);
        this.mMemoryPressureRouter.removeMemoryPressureListener(reactContext.getCatalystInstance());
    }
    
    @Override
    public void attachMeasuredRootView(final ReactRootView reactRootView) {
        UiThreadUtil.assertOnUiThread();
        this.mAttachedRootViews.add(reactRootView);
        if (this.mReactContextInitAsyncTask == null && this.mCurrentReactContext != null) {
            this.attachMeasuredRootViewToInstance(reactRootView, this.mCurrentReactContext.getCatalystInstance());
        }
    }
    
    @Override
    public List<ViewManager> createAllViewManagers(final ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker("CREATE_VIEW_MANAGERS_START");
        Systrace.beginSection(0L, "createAllViewManagers");
        ArrayList<Object> list;
        try {
            list = (ArrayList<Object>)new ArrayList<ViewManager>();
            final Iterator<ReactPackage> iterator = this.mPackages.iterator();
            while (iterator.hasNext()) {
                list.addAll(iterator.next().createViewManagers(reactApplicationContext));
            }
        }
        finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker("CREATE_VIEW_MANAGERS_END");
        }
        Systrace.endSection(0L);
        ReactMarker.logMarker("CREATE_VIEW_MANAGERS_END");
        return (List<ViewManager>)list;
    }
    
    @Override
    public void createReactContextInBackground() {
        Assertions.assertCondition(!this.mHasStartedCreatingInitialContext, "createReactContextInBackground should only be called when creating the react application for the first time. When reloading JS, e.g. from a new file, explicitlyuse recreateReactContextInBackground");
        this.mHasStartedCreatingInitialContext = true;
        this.recreateReactContextInBackgroundInner();
    }
    
    @Override
    public ReactContext getCurrentReactContext() {
        return this.mCurrentReactContext;
    }
    
    @Override
    public DevSupportManager getDevSupportManager() {
        return this.mDevSupportManager;
    }
    
    @Override
    public String getSourceUrl() {
        return Assertions.assertNotNull(this.mSourceUrl);
    }
    
    @Override
    public boolean hasStartedCreatingInitialContext() {
        return this.mHasStartedCreatingInitialContext;
    }
    
    @Override
    public void onHostPause() {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = null;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        this.moveToBeforeResumeLifecycleState();
    }
    
    @Override
    public void onHostResume(final Activity mCurrentActivity, final DefaultHardwareBackBtnHandler mDefaultBackButtonImpl) {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = mDefaultBackButtonImpl;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(true);
        }
        this.mCurrentActivity = mCurrentActivity;
        this.moveToResumedLifecycleState(false);
    }
}
