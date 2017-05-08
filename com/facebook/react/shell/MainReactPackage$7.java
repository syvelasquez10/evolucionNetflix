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
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.datepicker.DatePickerDialogModule;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.camera.CameraRollManager;
import com.facebook.react.modules.storage.AsyncStorageModule;
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
import java.util.Collections;
import com.facebook.react.bridge.JavaScriptModule;
import java.util.List;
import com.facebook.react.LazyReactPackage;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.NativeModule;
import javax.inject.Provider;

class MainReactPackage$7 implements Provider<NativeModule>
{
    final /* synthetic */ MainReactPackage this$0;
    final /* synthetic */ ReactApplicationContext val$context;
    
    MainReactPackage$7(final MainReactPackage this$0, final ReactApplicationContext val$context) {
        this.this$0 = this$0;
        this.val$context = val$context;
    }
    
    public NativeModule get() {
        final ReactApplicationContext val$context = this.val$context;
        ImagePipelineConfig frescoConfig;
        if (this.this$0.mConfig != null) {
            frescoConfig = this.this$0.mConfig.getFrescoConfig();
        }
        else {
            frescoConfig = null;
        }
        return new FrescoModule(val$context, frescoConfig);
    }
}
