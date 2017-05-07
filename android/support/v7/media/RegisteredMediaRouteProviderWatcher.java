// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.IntentFilter;
import android.content.pm.ServiceInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.BroadcastReceiver;
import java.util.ArrayList;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.content.Context;

final class RegisteredMediaRouteProviderWatcher
{
    private final Callback mCallback;
    private final Context mContext;
    private final Handler mHandler;
    private final PackageManager mPackageManager;
    private final ArrayList<RegisteredMediaRouteProvider> mProviders;
    private boolean mRunning;
    private final BroadcastReceiver mScanPackagesReceiver;
    private final Runnable mScanPackagesRunnable;
    
    public RegisteredMediaRouteProviderWatcher(final Context mContext, final Callback mCallback) {
        this.mProviders = new ArrayList<RegisteredMediaRouteProvider>();
        this.mScanPackagesReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                RegisteredMediaRouteProviderWatcher.this.scanPackages();
            }
        };
        this.mScanPackagesRunnable = new Runnable() {
            @Override
            public void run() {
                RegisteredMediaRouteProviderWatcher.this.scanPackages();
            }
        };
        this.mContext = mContext;
        this.mCallback = mCallback;
        this.mHandler = new Handler();
        this.mPackageManager = mContext.getPackageManager();
    }
    
    private int findProvider(final String s, final String s2) {
        for (int size = this.mProviders.size(), i = 0; i < size; ++i) {
            if (this.mProviders.get(i).hasComponentName(s, s2)) {
                return i;
            }
        }
        return -1;
    }
    
    private void scanPackages() {
        if (this.mRunning) {
            int n = 0;
            final Iterator<ResolveInfo> iterator = this.mPackageManager.queryIntentServices(new Intent("android.media.MediaRouteProviderService"), 0).iterator();
            while (iterator.hasNext()) {
                final ServiceInfo serviceInfo = iterator.next().serviceInfo;
                if (serviceInfo != null) {
                    final int provider = this.findProvider(serviceInfo.packageName, serviceInfo.name);
                    if (provider < 0) {
                        final RegisteredMediaRouteProvider registeredMediaRouteProvider = new RegisteredMediaRouteProvider(this.mContext, new ComponentName(serviceInfo.packageName, serviceInfo.name));
                        registeredMediaRouteProvider.start();
                        this.mProviders.add(n, registeredMediaRouteProvider);
                        this.mCallback.addProvider(registeredMediaRouteProvider);
                        ++n;
                    }
                    else {
                        if (provider < n) {
                            continue;
                        }
                        final RegisteredMediaRouteProvider registeredMediaRouteProvider2 = this.mProviders.get(provider);
                        registeredMediaRouteProvider2.start();
                        registeredMediaRouteProvider2.rebindIfDisconnected();
                        Collections.swap(this.mProviders, provider, n);
                        ++n;
                    }
                }
            }
            if (n < this.mProviders.size()) {
                for (int i = this.mProviders.size() - 1; i >= n; --i) {
                    final RegisteredMediaRouteProvider registeredMediaRouteProvider3 = this.mProviders.get(i);
                    this.mCallback.removeProvider(registeredMediaRouteProvider3);
                    this.mProviders.remove(registeredMediaRouteProvider3);
                    registeredMediaRouteProvider3.stop();
                }
            }
        }
    }
    
    public void start() {
        if (!this.mRunning) {
            this.mRunning = true;
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
            intentFilter.addDataScheme("package");
            this.mContext.registerReceiver(this.mScanPackagesReceiver, intentFilter, (String)null, this.mHandler);
            this.mHandler.post(this.mScanPackagesRunnable);
        }
    }
    
    public void stop() {
        if (this.mRunning) {
            this.mRunning = false;
            this.mContext.unregisterReceiver(this.mScanPackagesReceiver);
            this.mHandler.removeCallbacks(this.mScanPackagesRunnable);
            for (int i = this.mProviders.size() - 1; i >= 0; --i) {
                this.mProviders.get(i).stop();
            }
        }
    }
    
    public interface Callback
    {
        void addProvider(final MediaRouteProvider p0);
        
        void removeProvider(final MediaRouteProvider p0);
    }
}
