// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.permissions;

import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactMethod;
import android.content.Context;
import android.os.Process;
import android.os.Build$VERSION;
import com.facebook.react.bridge.Promise;
import android.app.Activity;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.Callback;
import android.util.SparseArray;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class PermissionsModule extends ReactContextBaseJavaModule implements PermissionListener
{
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    private final String DENIED;
    private final String GRANTED;
    private final String NEVER_ASK_AGAIN;
    private final SparseArray<Callback> mCallbacks;
    private int mRequestCode;
    
    public PermissionsModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mRequestCode = 0;
        this.GRANTED = "granted";
        this.DENIED = "denied";
        this.NEVER_ASK_AGAIN = "never_ask_again";
        this.mCallbacks = (SparseArray<Callback>)new SparseArray();
    }
    
    private PermissionAwareActivity getPermissionAwareActivity() {
        final Activity currentActivity = this.getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        }
        if (!(currentActivity instanceof PermissionAwareActivity)) {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
        return (PermissionAwareActivity)currentActivity;
    }
    
    @ReactMethod
    public void checkPermission(final String s, final Promise promise) {
        final boolean b = true;
        boolean b2 = true;
        final Context baseContext = this.getReactApplicationContext().getBaseContext();
        if (Build$VERSION.SDK_INT < 23) {
            if (baseContext.checkPermission(s, Process.myPid(), Process.myUid()) != 0) {
                b2 = false;
            }
            promise.resolve(b2);
            return;
        }
        promise.resolve(baseContext.checkSelfPermission(s) == 0 && b);
    }
    
    @Override
    public String getName() {
        return "PermissionsAndroid";
    }
    
    @Override
    public boolean onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        ((Callback)this.mCallbacks.get(n)).invoke(array2, this.getPermissionAwareActivity());
        this.mCallbacks.remove(n);
        return this.mCallbacks.size() == 0;
    }
    
    @ReactMethod
    public void requestMultiplePermissions(final ReadableArray readableArray, final Promise promise) {
        int i = 0;
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final ArrayList<String> list = new ArrayList<String>();
        final Context baseContext = this.getReactApplicationContext().getBaseContext();
        int n = 0;
        while (i < readableArray.size()) {
            final String string = readableArray.getString(i);
            if (Build$VERSION.SDK_INT < 23) {
                String s;
                if (baseContext.checkPermission(string, Process.myPid(), Process.myUid()) == 0) {
                    s = "granted";
                }
                else {
                    s = "denied";
                }
                writableNativeMap.putString(string, s);
                ++n;
            }
            else if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, "granted");
                ++n;
            }
            else {
                list.add(string);
            }
            ++i;
        }
        if (readableArray.size() == n) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            final PermissionAwareActivity permissionAwareActivity = this.getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, (Object)new PermissionsModule$2(this, list, writableNativeMap, promise));
            permissionAwareActivity.requestPermissions(list.toArray(new String[0]), this.mRequestCode, this);
            ++this.mRequestCode;
        }
        catch (IllegalStateException ex) {
            promise.reject("E_INVALID_ACTIVITY", ex);
        }
    }
    
    @ReactMethod
    public void requestPermission(final String s, final Promise promise) {
        boolean b = true;
        final Context baseContext = this.getReactApplicationContext().getBaseContext();
        if (Build$VERSION.SDK_INT < 23) {
            if (baseContext.checkPermission(s, Process.myPid(), Process.myUid()) != 0) {
                b = false;
            }
            promise.resolve(b);
            return;
        }
        if (baseContext.checkSelfPermission(s) == 0) {
            promise.resolve("granted");
            return;
        }
        try {
            final PermissionAwareActivity permissionAwareActivity = this.getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, (Object)new PermissionsModule$1(this, promise, s));
            permissionAwareActivity.requestPermissions(new String[] { s }, this.mRequestCode, this);
            ++this.mRequestCode;
        }
        catch (IllegalStateException ex) {
            promise.reject("E_INVALID_ACTIVITY", ex);
        }
    }
    
    @ReactMethod
    public void shouldShowRequestPermissionRationale(final String s, final Promise promise) {
        if (Build$VERSION.SDK_INT < 23) {
            promise.resolve(false);
            return;
        }
        try {
            promise.resolve(this.getPermissionAwareActivity().shouldShowRequestPermissionRationale(s));
        }
        catch (IllegalStateException ex) {
            promise.reject("E_INVALID_ACTIVITY", ex);
        }
    }
}
