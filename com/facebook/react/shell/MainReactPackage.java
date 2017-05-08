// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.shell;

import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.timepicker.TimePickerDialogModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.netinfo.NetInfoModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.modules.location.LocationModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.camera.ImageEditingManager;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.datepicker.DatePickerDialogModule;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.camera.CameraRollManager;
import com.facebook.react.modules.storage.AsyncStorageModule;
import javax.inject.Provider;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.bridge.ModuleSpec;
import java.util.Collection;
import java.util.Arrays;
import com.facebook.react.flat.RCTModalHostManager;
import com.facebook.react.flat.FlatARTSurfaceViewManager;
import com.facebook.react.flat.RCTViewPagerManager;
import com.facebook.react.flat.RCTTextInputManager;
import com.facebook.react.flat.RCTImageViewManager;
import com.facebook.react.flat.RCTTextInlineImageManager;
import com.facebook.react.flat.RCTVirtualTextManager;
import com.facebook.react.flat.RCTRawTextManager;
import com.facebook.react.flat.RCTTextManager;
import com.facebook.react.flat.RCTViewManager;
import android.content.Context;
import android.preference.PreferenceManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.webview.ReactWebViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.viewpager.ReactViewPagerManager;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.react.views.toolbar.ReactToolbarManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.slider.ReactSliderManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.picker.ReactDropdownPickerManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.picker.ReactDialogPickerManager;
import com.facebook.react.views.art.ARTSurfaceViewManager;
import com.facebook.react.views.art.ARTRenderableViewManager;
import java.util.ArrayList;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Collections;
import com.facebook.react.bridge.JavaScriptModule;
import java.util.List;
import com.facebook.react.LazyReactPackage;

public class MainReactPackage extends LazyReactPackage
{
    private MainPackageConfig mConfig;
    
    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
    
    @Override
    public List<ViewManager> createViewManagers(final ReactApplicationContext reactApplicationContext) {
        final ArrayList<SwipeRefreshLayoutManager> list = (ArrayList<SwipeRefreshLayoutManager>)new ArrayList<ViewManager>();
        list.add(ARTRenderableViewManager.createARTGroupViewManager());
        list.add(ARTRenderableViewManager.createARTShapeViewManager());
        list.add(ARTRenderableViewManager.createARTTextViewManager());
        list.add(new ARTSurfaceViewManager());
        list.add(new ReactDialogPickerManager());
        list.add(new ReactDrawerLayoutManager());
        list.add(new ReactDropdownPickerManager());
        list.add(new ReactHorizontalScrollViewManager());
        list.add(new ReactImageManager());
        list.add(new ReactModalHostManager());
        list.add(new ReactProgressBarViewManager());
        list.add(new ReactRawTextManager());
        list.add(new ReactScrollViewManager());
        list.add(new ReactSliderManager());
        list.add(new ReactSwitchManager());
        list.add(new FrescoBasedReactTextInlineImageViewManager());
        list.add(new ReactTextInputManager());
        list.add(new ReactTextViewManager());
        list.add(new ReactToolbarManager());
        list.add(new ReactViewManager());
        list.add(new ReactViewPagerManager());
        list.add(new ReactVirtualTextViewManager());
        list.add(new ReactWebViewManager());
        list.add(new SwipeRefreshLayoutManager());
        if (PreferenceManager.getDefaultSharedPreferences((Context)reactApplicationContext).getBoolean("flat_uiimplementation", false)) {
            list.addAll((Collection<? extends ViewManager>)Arrays.asList(new RCTViewManager(), new RCTTextManager(), new RCTRawTextManager(), new RCTVirtualTextManager(), new RCTTextInlineImageManager(), new RCTImageViewManager(), new RCTTextInputManager(), new RCTViewPagerManager(), new FlatARTSurfaceViewManager(), new RCTModalHostManager()));
        }
        return (List<ViewManager>)list;
    }
    
    @Override
    public List<ModuleSpec> getNativeModules(final ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ModuleSpec(AppStateModule.class, (Provider<? extends NativeModule>)new MainReactPackage$1(this, reactApplicationContext)), new ModuleSpec(AsyncStorageModule.class, (Provider<? extends NativeModule>)new MainReactPackage$2(this, reactApplicationContext)), new ModuleSpec(CameraRollManager.class, (Provider<? extends NativeModule>)new MainReactPackage$3(this, reactApplicationContext)), new ModuleSpec(ClipboardModule.class, (Provider<? extends NativeModule>)new MainReactPackage$4(this, reactApplicationContext)), new ModuleSpec(DatePickerDialogModule.class, (Provider<? extends NativeModule>)new MainReactPackage$5(this, reactApplicationContext)), new ModuleSpec(DialogModule.class, (Provider<? extends NativeModule>)new MainReactPackage$6(this, reactApplicationContext)), new ModuleSpec(FrescoModule.class, (Provider<? extends NativeModule>)new MainReactPackage$7(this, reactApplicationContext)), new ModuleSpec(I18nManagerModule.class, (Provider<? extends NativeModule>)new MainReactPackage$8(this, reactApplicationContext)), new ModuleSpec(ImageEditingManager.class, (Provider<? extends NativeModule>)new MainReactPackage$9(this, reactApplicationContext)), new ModuleSpec(ImageLoaderModule.class, (Provider<? extends NativeModule>)new MainReactPackage$10(this, reactApplicationContext)), new ModuleSpec(ImageStoreManager.class, (Provider<? extends NativeModule>)new MainReactPackage$11(this, reactApplicationContext)), new ModuleSpec(IntentModule.class, (Provider<? extends NativeModule>)new MainReactPackage$12(this, reactApplicationContext)), new ModuleSpec(LocationModule.class, (Provider<? extends NativeModule>)new MainReactPackage$13(this, reactApplicationContext)), new ModuleSpec(NativeAnimatedModule.class, (Provider<? extends NativeModule>)new MainReactPackage$14(this, reactApplicationContext)), new ModuleSpec(NetworkingModule.class, (Provider<? extends NativeModule>)new MainReactPackage$15(this, reactApplicationContext)), new ModuleSpec(NetInfoModule.class, (Provider<? extends NativeModule>)new MainReactPackage$16(this, reactApplicationContext)), new ModuleSpec(PermissionsModule.class, (Provider<? extends NativeModule>)new MainReactPackage$17(this, reactApplicationContext)), new ModuleSpec(ShareModule.class, (Provider<? extends NativeModule>)new MainReactPackage$18(this, reactApplicationContext)), new ModuleSpec(StatusBarModule.class, (Provider<? extends NativeModule>)new MainReactPackage$19(this, reactApplicationContext)), new ModuleSpec(TimePickerDialogModule.class, (Provider<? extends NativeModule>)new MainReactPackage$20(this, reactApplicationContext)), new ModuleSpec(ToastModule.class, (Provider<? extends NativeModule>)new MainReactPackage$21(this, reactApplicationContext)), new ModuleSpec(VibrationModule.class, (Provider<? extends NativeModule>)new MainReactPackage$22(this, reactApplicationContext)), new ModuleSpec(WebSocketModule.class, (Provider<? extends NativeModule>)new MainReactPackage$23(this, reactApplicationContext)));
    }
    
    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return LazyReactPackage.getReactModuleInfoProviderViaReflection(this);
    }
}
