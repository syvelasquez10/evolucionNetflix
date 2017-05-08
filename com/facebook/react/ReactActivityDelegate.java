// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import android.annotation.TargetApi;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import android.view.KeyEvent;
import com.facebook.common.logging.FLog;
import android.net.Uri;
import android.widget.Toast;
import android.provider.Settings;
import android.os.Build$VERSION;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.PermissionListener;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import android.app.Activity;

public class ReactActivityDelegate
{
    private static final String REDBOX_PERMISSION_GRANTED_MESSAGE = "Overlay permissions have been granted.";
    private static final String REDBOX_PERMISSION_MESSAGE = "Overlay permissions needs to be granted in order for react native apps to run in dev mode";
    private final int REQUEST_OVERLAY_PERMISSION_CODE;
    private final Activity mActivity;
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    private final FragmentActivity mFragmentActivity;
    private final String mMainComponentName;
    private PermissionListener mPermissionListener;
    private Callback mPermissionsCallback;
    private ReactRootView mReactRootView;
    
    public ReactActivityDelegate(final Activity mActivity, final String mMainComponentName) {
        this.REQUEST_OVERLAY_PERMISSION_CODE = 1111;
        this.mActivity = mActivity;
        this.mMainComponentName = mMainComponentName;
        this.mFragmentActivity = null;
    }
    
    public ReactActivityDelegate(final FragmentActivity mFragmentActivity, final String mMainComponentName) {
        this.REQUEST_OVERLAY_PERMISSION_CODE = 1111;
        this.mFragmentActivity = mFragmentActivity;
        this.mMainComponentName = mMainComponentName;
        this.mActivity = null;
    }
    
    private Context getContext() {
        if (this.mActivity != null) {
            return (Context)this.mActivity;
        }
        return Assertions.assertNotNull((Context)this.mFragmentActivity);
    }
    
    private Activity getPlainActivity() {
        return (Activity)this.getContext();
    }
    
    protected ReactRootView createRootView() {
        return new ReactRootView(this.getContext());
    }
    
    protected Bundle getLaunchOptions() {
        return null;
    }
    
    public ReactInstanceManager getReactInstanceManager() {
        return this.getReactNativeHost().getReactInstanceManager();
    }
    
    protected ReactNativeHost getReactNativeHost() {
        return ((ReactApplication)this.getPlainActivity().getApplication()).getReactNativeHost();
    }
    
    protected void loadApp(final String s) {
        if (this.mReactRootView != null) {
            throw new IllegalStateException("Cannot loadApp while app is already running.");
        }
        (this.mReactRootView = this.createRootView()).startReactApplication(this.getReactNativeHost().getReactInstanceManager(), s, this.getLaunchOptions());
        this.getPlainActivity().setContentView((View)this.mReactRootView);
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        if (this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onActivityResult(this.getPlainActivity(), n, n2, intent);
        }
        else if (n == 1111 && Build$VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(this.getContext())) {
            if (this.mMainComponentName != null) {
                this.loadApp(this.mMainComponentName);
            }
            Toast.makeText(this.getContext(), (CharSequence)"Overlay permissions have been granted.", 1).show();
        }
    }
    
    public boolean onBackPressed() {
        if (this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onBackPressed();
            return true;
        }
        return false;
    }
    
    protected void onCreate(final Bundle bundle) {
        boolean b2;
        final boolean b = b2 = false;
        if (this.getReactNativeHost().getUseDeveloperSupport()) {
            b2 = b;
            if (Build$VERSION.SDK_INT >= 23) {
                b2 = b;
                if (!Settings.canDrawOverlays(this.getContext())) {
                    final Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + this.getContext().getPackageName()));
                    FLog.w("React", "Overlay permissions needs to be granted in order for react native apps to run in dev mode");
                    Toast.makeText(this.getContext(), (CharSequence)"Overlay permissions needs to be granted in order for react native apps to run in dev mode", 1).show();
                    ((Activity)this.getContext()).startActivityForResult(intent, 1111);
                    b2 = true;
                }
            }
        }
        if (this.mMainComponentName != null && !b2) {
            this.loadApp(this.mMainComponentName);
        }
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    }
    
    protected void onDestroy() {
        if (this.mReactRootView != null) {
            this.mReactRootView.unmountReactApplication();
            this.mReactRootView = null;
        }
        if (this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onHostDestroy(this.getPlainActivity());
        }
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        if (this.getReactNativeHost().hasInstance() && this.getReactNativeHost().getUseDeveloperSupport()) {
            if (n == 82) {
                this.getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
                return true;
            }
            if (Assertions.assertNotNull(this.mDoubleTapReloadRecognizer).didDoubleTapR(n, this.getPlainActivity().getCurrentFocus())) {
                this.getReactNativeHost().getReactInstanceManager().getDevSupportManager().handleReloadJS();
                return true;
            }
        }
        return false;
    }
    
    public boolean onNewIntent(final Intent intent) {
        if (this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onNewIntent(intent);
            return true;
        }
        return false;
    }
    
    protected void onPause() {
        if (this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onHostPause(this.getPlainActivity());
        }
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        this.mPermissionsCallback = new ReactActivityDelegate$1(this, n, array, array2);
    }
    
    protected void onResume() {
        if (this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onHostResume(this.getPlainActivity(), (DefaultHardwareBackBtnHandler)this.getPlainActivity());
        }
        if (this.mPermissionsCallback != null) {
            this.mPermissionsCallback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }
    
    @TargetApi(23)
    public void requestPermissions(final String[] array, final int n, final PermissionListener mPermissionListener) {
        this.mPermissionListener = mPermissionListener;
        this.getPlainActivity().requestPermissions(array, n);
    }
}
