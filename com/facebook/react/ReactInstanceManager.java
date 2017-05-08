// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import android.net.Uri;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.common.logging.FLog;
import android.content.Intent;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.MemoryPressureListener;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.modules.debug.DeveloperSettings;
import com.facebook.react.devsupport.DevServerHelper$PackagerStatusCallback;
import android.os.AsyncTask;
import java.util.Iterator;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.bridge.JavaScriptModuleRegistry$Builder;
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

public class ReactInstanceManager
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
    private ReactInstanceManager$ReactContextInitParams mPendingReactContextInitParams;
    private ReactInstanceManager$ReactContextInitAsyncTask mReactContextInitAsyncTask;
    private final Collection<ReactInstanceManager$ReactInstanceEventListener> mReactInstanceEventListeners;
    private final UIImplementationProvider mUIImplementationProvider;
    private final boolean mUseDeveloperSupport;
    
    static {
        TAG = ReactInstanceManager.class.getSimpleName();
    }
    
    ReactInstanceManager(final Context mApplicationContext, final Activity mCurrentActivity, final DefaultHardwareBackBtnHandler mDefaultBackButtonImpl, final JSBundleLoader mBundleLoader, final String mjsMainModuleName, final List<ReactPackage> mPackages, final boolean mUseDeveloperSupport, final NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener, final LifecycleState mLifecycleState, final UIImplementationProvider muiImplementationProvider, final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler, final JSCConfig mjscConfig, final RedBoxHandler redBoxHandler, final boolean mLazyNativeModulesEnabled, final boolean mLazyViewManagersEnabled) {
        this.mAttachedRootViews = new ArrayList<ReactRootView>();
        this.mReactInstanceEventListeners = (Collection<ReactInstanceManager$ReactInstanceEventListener>)Collections.synchronizedSet(new HashSet<Object>());
        this.mHasStartedCreatingInitialContext = false;
        this.mDevInterface = new ReactInstanceManager$1(this);
        this.mBackBtnHandler = new ReactInstanceManager$2(this);
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
    
    public static ReactInstanceManagerBuilder builder() {
        return new ReactInstanceManagerBuilder();
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
        //    15: new             Lcom/facebook/react/bridge/ReactApplicationContext;
        //    18: dup            
        //    19: aload_0        
        //    20: getfield        com/facebook/react/ReactInstanceManager.mApplicationContext:Landroid/content/Context;
        //    23: invokespecial   com/facebook/react/bridge/ReactApplicationContext.<init>:(Landroid/content/Context;)V
        //    26: astore          4
        //    28: new             Lcom/facebook/react/NativeModuleRegistryBuilder;
        //    31: dup            
        //    32: aload           4
        //    34: aload_0        
        //    35: getfield        com/facebook/react/ReactInstanceManager.mLazyNativeModulesEnabled:Z
        //    38: invokespecial   com/facebook/react/NativeModuleRegistryBuilder.<init>:(Lcom/facebook/react/bridge/ReactApplicationContext;Z)V
        //    41: astore_3       
        //    42: new             Lcom/facebook/react/bridge/JavaScriptModuleRegistry$Builder;
        //    45: dup            
        //    46: invokespecial   com/facebook/react/bridge/JavaScriptModuleRegistry$Builder.<init>:()V
        //    49: astore          5
        //    51: aload_0        
        //    52: getfield        com/facebook/react/ReactInstanceManager.mUseDeveloperSupport:Z
        //    55: ifeq            67
        //    58: aload           4
        //    60: aload_0        
        //    61: getfield        com/facebook/react/ReactInstanceManager.mDevSupportManager:Lcom/facebook/react/devsupport/DevSupportManager;
        //    64: invokevirtual   com/facebook/react/bridge/ReactApplicationContext.setNativeModuleCallExceptionHandler:(Lcom/facebook/react/bridge/NativeModuleCallExceptionHandler;)V
        //    67: ldc_w           "PROCESS_PACKAGES_START"
        //    70: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //    73: lconst_0       
        //    74: ldc_w           "createAndProcessCoreModulesPackage"
        //    77: invokestatic    com/facebook/systrace/Systrace.beginSection:(JLjava/lang/String;)V
        //    80: aload_0        
        //    81: new             Lcom/facebook/react/CoreModulesPackage;
        //    84: dup            
        //    85: aload_0        
        //    86: aload_0        
        //    87: getfield        com/facebook/react/ReactInstanceManager.mBackBtnHandler:Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;
        //    90: aload_0        
        //    91: getfield        com/facebook/react/ReactInstanceManager.mUIImplementationProvider:Lcom/facebook/react/uimanager/UIImplementationProvider;
        //    94: aload_0        
        //    95: getfield        com/facebook/react/ReactInstanceManager.mLazyViewManagersEnabled:Z
        //    98: invokespecial   com/facebook/react/CoreModulesPackage.<init>:(Lcom/facebook/react/ReactInstanceManager;Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;Lcom/facebook/react/uimanager/UIImplementationProvider;Z)V
        //   101: aload_3        
        //   102: aload           5
        //   104: invokespecial   com/facebook/react/ReactInstanceManager.processPackage:(Lcom/facebook/react/ReactPackage;Lcom/facebook/react/NativeModuleRegistryBuilder;Lcom/facebook/react/bridge/JavaScriptModuleRegistry$Builder;)V
        //   107: lconst_0       
        //   108: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   111: aload_0        
        //   112: getfield        com/facebook/react/ReactInstanceManager.mPackages:Ljava/util/List;
        //   115: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   120: astore          6
        //   122: aload           6
        //   124: invokeinterface java/util/Iterator.hasNext:()Z
        //   129: ifeq            181
        //   132: aload           6
        //   134: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   139: checkcast       Lcom/facebook/react/ReactPackage;
        //   142: astore          7
        //   144: lconst_0       
        //   145: ldc_w           "createAndProcessCustomReactPackage"
        //   148: invokestatic    com/facebook/systrace/Systrace.beginSection:(JLjava/lang/String;)V
        //   151: aload_0        
        //   152: aload           7
        //   154: aload_3        
        //   155: aload           5
        //   157: invokespecial   com/facebook/react/ReactInstanceManager.processPackage:(Lcom/facebook/react/ReactPackage;Lcom/facebook/react/NativeModuleRegistryBuilder;Lcom/facebook/react/bridge/JavaScriptModuleRegistry$Builder;)V
        //   160: lconst_0       
        //   161: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   164: goto            122
        //   167: astore_1       
        //   168: lconst_0       
        //   169: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   172: aload_1        
        //   173: athrow         
        //   174: astore_1       
        //   175: lconst_0       
        //   176: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   179: aload_1        
        //   180: athrow         
        //   181: ldc_w           "PROCESS_PACKAGES_END"
        //   184: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   187: ldc_w           "BUILD_NATIVE_MODULE_REGISTRY_START"
        //   190: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   193: lconst_0       
        //   194: ldc_w           "buildNativeModuleRegistry"
        //   197: invokestatic    com/facebook/systrace/Systrace.beginSection:(JLjava/lang/String;)V
        //   200: aload_3        
        //   201: invokevirtual   com/facebook/react/NativeModuleRegistryBuilder.build:()Lcom/facebook/react/cxxbridge/NativeModuleRegistry;
        //   204: astore          6
        //   206: lconst_0       
        //   207: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   210: ldc_w           "BUILD_NATIVE_MODULE_REGISTRY_END"
        //   213: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   216: aload_0        
        //   217: getfield        com/facebook/react/ReactInstanceManager.mNativeModuleCallExceptionHandler:Lcom/facebook/react/bridge/NativeModuleCallExceptionHandler;
        //   220: ifnull          340
        //   223: aload_0        
        //   224: getfield        com/facebook/react/ReactInstanceManager.mNativeModuleCallExceptionHandler:Lcom/facebook/react/bridge/NativeModuleCallExceptionHandler;
        //   227: astore_3       
        //   228: new             Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   231: dup            
        //   232: invokespecial   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.<init>:()V
        //   235: invokestatic    com/facebook/react/bridge/queue/ReactQueueConfigurationSpec.createDefault:()Lcom/facebook/react/bridge/queue/ReactQueueConfigurationSpec;
        //   238: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setReactQueueConfigurationSpec:(Lcom/facebook/react/bridge/queue/ReactQueueConfigurationSpec;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   241: aload_1        
        //   242: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setJSExecutor:(Lcom/facebook/react/cxxbridge/JavaScriptExecutor;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   245: aload           6
        //   247: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setRegistry:(Lcom/facebook/react/cxxbridge/NativeModuleRegistry;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   250: aload           5
        //   252: invokevirtual   com/facebook/react/bridge/JavaScriptModuleRegistry$Builder.build:()Lcom/facebook/react/bridge/JavaScriptModuleRegistry;
        //   255: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setJSModuleRegistry:(Lcom/facebook/react/bridge/JavaScriptModuleRegistry;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   258: aload_2        
        //   259: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setJSBundleLoader:(Lcom/facebook/react/cxxbridge/JSBundleLoader;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   262: aload_3        
        //   263: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.setNativeModuleCallExceptionHandler:(Lcom/facebook/react/bridge/NativeModuleCallExceptionHandler;)Lcom/facebook/react/cxxbridge/CatalystInstanceImpl$Builder;
        //   266: astore_1       
        //   267: ldc_w           "CREATE_CATALYST_INSTANCE_START"
        //   270: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   273: lconst_0       
        //   274: ldc_w           "createCatalystInstance"
        //   277: invokestatic    com/facebook/systrace/Systrace.beginSection:(JLjava/lang/String;)V
        //   280: aload_1        
        //   281: invokevirtual   com/facebook/react/cxxbridge/CatalystInstanceImpl$Builder.build:()Lcom/facebook/react/cxxbridge/CatalystInstanceImpl;
        //   284: astore_1       
        //   285: lconst_0       
        //   286: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   289: ldc_w           "CREATE_CATALYST_INSTANCE_END"
        //   292: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   295: aload_0        
        //   296: getfield        com/facebook/react/ReactInstanceManager.mBridgeIdleDebugListener:Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;
        //   299: ifnull          312
        //   302: aload_1        
        //   303: aload_0        
        //   304: getfield        com/facebook/react/ReactInstanceManager.mBridgeIdleDebugListener:Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;
        //   307: invokeinterface com/facebook/react/bridge/CatalystInstance.addBridgeIdleDebugListener:(Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;)V
        //   312: aload           4
        //   314: aload_1        
        //   315: invokevirtual   com/facebook/react/bridge/ReactApplicationContext.initializeWithInstance:(Lcom/facebook/react/bridge/CatalystInstance;)V
        //   318: aload_1        
        //   319: invokeinterface com/facebook/react/bridge/CatalystInstance.runJSBundle:()V
        //   324: aload           4
        //   326: areturn        
        //   327: astore_1       
        //   328: lconst_0       
        //   329: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   332: ldc_w           "BUILD_NATIVE_MODULE_REGISTRY_END"
        //   335: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   338: aload_1        
        //   339: athrow         
        //   340: aload_0        
        //   341: getfield        com/facebook/react/ReactInstanceManager.mDevSupportManager:Lcom/facebook/react/devsupport/DevSupportManager;
        //   344: astore_3       
        //   345: goto            228
        //   348: astore_1       
        //   349: lconst_0       
        //   350: invokestatic    com/facebook/systrace/Systrace.endSection:(J)V
        //   353: ldc_w           "CREATE_CATALYST_INSTANCE_END"
        //   356: invokestatic    com/facebook/react/bridge/ReactMarker.logMarker:(Ljava/lang/String;)V
        //   359: aload_1        
        //   360: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  80     107    167    174    Any
        //  151    160    174    181    Any
        //  200    206    327    340    Any
        //  280    285    348    361    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0312:
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
    
    private void moveToBeforeCreateLifecycleState() {
        if (this.mCurrentReactContext != null) {
            if (this.mLifecycleState == LifecycleState.RESUMED) {
                this.mCurrentReactContext.onHostPause();
                this.mLifecycleState = LifecycleState.BEFORE_RESUME;
            }
            if (this.mLifecycleState == LifecycleState.BEFORE_RESUME) {
                this.mCurrentReactContext.onHostDestroy();
            }
        }
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
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
    
    private void processPackage(final ReactPackage reactPackage, final NativeModuleRegistryBuilder nativeModuleRegistryBuilder, final JavaScriptModuleRegistry$Builder javaScriptModuleRegistry$Builder) {
        SystraceMessage.beginSection(0L, "processPackage").arg("className", reactPackage.getClass().getSimpleName()).flush();
        if (reactPackage instanceof ReactPackageLogger) {
            ((ReactPackageLogger)reactPackage).startProcessPackage();
        }
        nativeModuleRegistryBuilder.processPackage(reactPackage);
        final Iterator<Class<? extends JavaScriptModule>> iterator = reactPackage.createJSModules().iterator();
        while (iterator.hasNext()) {
            javaScriptModuleRegistry$Builder.add(iterator.next());
        }
        if (reactPackage instanceof ReactPackageLogger) {
            ((ReactPackageLogger)reactPackage).endProcessPackage();
        }
        Systrace.endSection(0L);
    }
    
    private void recreateReactContextInBackground(final JavaScriptExecutor$Factory javaScriptExecutor$Factory, final JSBundleLoader jsBundleLoader) {
        UiThreadUtil.assertOnUiThread();
        final ReactInstanceManager$ReactContextInitParams mPendingReactContextInitParams = new ReactInstanceManager$ReactContextInitParams(this, javaScriptExecutor$Factory, jsBundleLoader);
        if (this.mReactContextInitAsyncTask == null) {
            (this.mReactContextInitAsyncTask = new ReactInstanceManager$ReactContextInitAsyncTask(this, null)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new ReactInstanceManager$ReactContextInitParams[] { mPendingReactContextInitParams });
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
        this.mDevSupportManager.isPackagerRunning(new ReactInstanceManager$3(this, devSettings));
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
    
    public void attachMeasuredRootView(final ReactRootView reactRootView) {
        UiThreadUtil.assertOnUiThread();
        this.mAttachedRootViews.add(reactRootView);
        if (this.mReactContextInitAsyncTask == null && this.mCurrentReactContext != null) {
            this.attachMeasuredRootViewToInstance(reactRootView, this.mCurrentReactContext.getCatalystInstance());
        }
    }
    
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
    
    public void createReactContextInBackground() {
        Assertions.assertCondition(!this.mHasStartedCreatingInitialContext, "createReactContextInBackground should only be called when creating the react application for the first time. When reloading JS, e.g. from a new file, explicitlyuse recreateReactContextInBackground");
        this.mHasStartedCreatingInitialContext = true;
        this.recreateReactContextInBackgroundInner();
    }
    
    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        this.moveToBeforeCreateLifecycleState();
        if (this.mReactContextInitAsyncTask != null) {
            this.mReactContextInitAsyncTask.cancel(true);
        }
        this.mMemoryPressureRouter.destroy(this.mApplicationContext);
        if (this.mCurrentReactContext != null) {
            this.mCurrentReactContext.destroy();
            this.mCurrentReactContext = null;
            this.mHasStartedCreatingInitialContext = false;
        }
        this.mCurrentActivity = null;
        ResourceDrawableIdHelper.getInstance().clear();
    }
    
    public void detachRootView(final ReactRootView reactRootView) {
        UiThreadUtil.assertOnUiThread();
        if (this.mAttachedRootViews.remove(reactRootView) && this.mCurrentReactContext != null && this.mCurrentReactContext.hasActiveCatalystInstance()) {
            this.detachViewFromInstance(reactRootView, this.mCurrentReactContext.getCatalystInstance());
        }
    }
    
    public ReactContext getCurrentReactContext() {
        return this.mCurrentReactContext;
    }
    
    public DevSupportManager getDevSupportManager() {
        return this.mDevSupportManager;
    }
    
    public boolean hasStartedCreatingInitialContext() {
        return this.mHasStartedCreatingInitialContext;
    }
    
    public void onActivityResult(final Activity activity, final int n, final int n2, final Intent intent) {
        if (this.mCurrentReactContext != null) {
            this.mCurrentReactContext.onActivityResult(activity, n, n2, intent);
        }
    }
    
    public void onBackPressed() {
        UiThreadUtil.assertOnUiThread();
        final ReactContext mCurrentReactContext = this.mCurrentReactContext;
        if (this.mCurrentReactContext == null) {
            FLog.w("React", "Instance detached from instance manager");
            this.invokeDefaultOnBackPressed();
            return;
        }
        Assertions.assertNotNull(mCurrentReactContext).getNativeModule(DeviceEventManagerModule.class).emitHardwareBackPressed();
    }
    
    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        this.moveToBeforeCreateLifecycleState();
        this.mCurrentActivity = null;
    }
    
    public void onHostDestroy(final Activity activity) {
        if (activity == this.mCurrentActivity) {
            this.onHostDestroy();
        }
    }
    
    public void onHostPause() {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = null;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        this.moveToBeforeResumeLifecycleState();
    }
    
    public void onHostPause(final Activity activity) {
        Assertions.assertNotNull(this.mCurrentActivity);
        Assertions.assertCondition(activity == this.mCurrentActivity, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + this.mCurrentActivity.getClass().getSimpleName() + " " + "Paused activity: " + activity.getClass().getSimpleName());
        this.onHostPause();
    }
    
    public void onHostResume(final Activity mCurrentActivity, final DefaultHardwareBackBtnHandler mDefaultBackButtonImpl) {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = mDefaultBackButtonImpl;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(true);
        }
        this.mCurrentActivity = mCurrentActivity;
        this.moveToResumedLifecycleState(false);
    }
    
    public void onNewIntent(final Intent intent) {
        if (this.mCurrentReactContext == null) {
            FLog.w("React", "Instance detached from instance manager");
            return;
        }
        final String action = intent.getAction();
        final Uri data = intent.getData();
        if ("android.intent.action.VIEW".equals(action) && data != null) {
            Assertions.assertNotNull(this.mCurrentReactContext).getNativeModule(DeviceEventManagerModule.class).emitNewIntentReceived(data);
        }
        this.mCurrentReactContext.onNewIntent(this.mCurrentActivity, intent);
    }
    
    public void showDevOptionsDialog() {
        UiThreadUtil.assertOnUiThread();
        this.mDevSupportManager.showDevOptionsDialog();
    }
}
