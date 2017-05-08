// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.Window$Callback;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.content.res.Configuration;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.ComponentName;
import android.app.Activity;
import android.view.Window;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(14)
class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11
{
    private boolean mApplyDayNightCalled;
    private AppCompatDelegateImplV14$AutoNightModeManager mAutoNightModeManager;
    private boolean mHandleNativeActionModes;
    private int mLocalNightMode;
    
    AppCompatDelegateImplV14(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.mLocalNightMode = -100;
        this.mHandleNativeActionModes = true;
    }
    
    private void ensureAutoNightModeManager() {
        if (this.mAutoNightModeManager == null) {
            this.mAutoNightModeManager = new AppCompatDelegateImplV14$AutoNightModeManager(this, TwilightManager.getInstance(this.mContext));
        }
    }
    
    private int getNightMode() {
        if (this.mLocalNightMode != -100) {
            return this.mLocalNightMode;
        }
        return AppCompatDelegate.getDefaultNightMode();
    }
    
    private boolean shouldRecreateOnNightModeChange() {
        if (this.mApplyDayNightCalled && this.mContext instanceof Activity) {
            final PackageManager packageManager = this.mContext.getPackageManager();
            try {
                return (packageManager.getActivityInfo(new ComponentName(this.mContext, (Class)this.mContext.getClass()), 0).configChanges & 0x200) == 0x0;
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", (Throwable)ex);
                return true;
            }
        }
        return false;
    }
    
    private boolean updateForNightMode(int n) {
        final Resources resources = this.mContext.getResources();
        final Configuration configuration = resources.getConfiguration();
        final int uiMode = configuration.uiMode;
        if (n == 2) {
            n = 32;
        }
        else {
            n = 16;
        }
        if ((uiMode & 0x30) != n) {
            if (this.shouldRecreateOnNightModeChange()) {
                ((Activity)this.mContext).recreate();
            }
            else {
                final Configuration configuration2 = new Configuration(configuration);
                final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                configuration2.uiMode = (n | (configuration2.uiMode & 0xFFFFFFCF));
                resources.updateConfiguration(configuration2, displayMetrics);
                ResourcesFlusher.flush(resources);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public boolean applyDayNight() {
        boolean updateForNightMode = false;
        final int nightMode = this.getNightMode();
        final int mapNightMode = this.mapNightMode(nightMode);
        if (mapNightMode != -1) {
            updateForNightMode = this.updateForNightMode(mapNightMode);
        }
        if (nightMode == 0) {
            this.ensureAutoNightModeManager();
            this.mAutoNightModeManager.setup();
        }
        this.mApplyDayNightCalled = true;
        return updateForNightMode;
    }
    
    @Override
    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }
    
    int mapNightMode(final int n) {
        switch (n) {
            default: {
                return n;
            }
            case 0: {
                this.ensureAutoNightModeManager();
                return this.mAutoNightModeManager.getApplyableNightMode();
            }
            case -100: {
                return -1;
            }
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && this.mLocalNightMode == -100) {
            this.mLocalNightMode = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mLocalNightMode != -100) {
            bundle.putInt("appcompat:local_night_mode", this.mLocalNightMode);
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        this.applyDayNight();
    }
    
    @Override
    public void onStop() {
        super.onStop();
        if (this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }
    
    @Override
    Window$Callback wrapWindowCallback(final Window$Callback window$Callback) {
        return (Window$Callback)new AppCompatDelegateImplV14$AppCompatWindowCallbackV14(this, window$Callback);
    }
}
