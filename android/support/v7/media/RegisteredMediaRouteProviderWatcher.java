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
    private final RegisteredMediaRouteProviderWatcher$Callback mCallback;
    private final Context mContext;
    private final Handler mHandler;
    private final PackageManager mPackageManager;
    private final ArrayList<RegisteredMediaRouteProvider> mProviders;
    private boolean mRunning;
    private final BroadcastReceiver mScanPackagesReceiver;
    private final Runnable mScanPackagesRunnable;
    
    public RegisteredMediaRouteProviderWatcher(final Context mContext, final RegisteredMediaRouteProviderWatcher$Callback mCallback) {
        this.mProviders = new ArrayList<RegisteredMediaRouteProvider>();
        this.mScanPackagesReceiver = new RegisteredMediaRouteProviderWatcher$1(this);
        this.mScanPackagesRunnable = new RegisteredMediaRouteProviderWatcher$2(this);
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
    
    void scanPackages() {
        if (this.mRunning) {
            final Iterator<ResolveInfo> iterator = (Iterator<ResolveInfo>)this.mPackageManager.queryIntentServices(new Intent("android.media.MediaRouteProviderService"), 0).iterator();
            int n = 0;
        Label_0153_Outer:
            while (iterator.hasNext()) {
                final ServiceInfo serviceInfo = iterator.next().serviceInfo;
                if (serviceInfo == null) {
                    continue Label_0153_Outer;
                }
                final int provider = this.findProvider(serviceInfo.packageName, serviceInfo.name);
                if (provider < 0) {
                    final RegisteredMediaRouteProvider registeredMediaRouteProvider = new RegisteredMediaRouteProvider(this.mContext, new ComponentName(serviceInfo.packageName, serviceInfo.name));
                    registeredMediaRouteProvider.start();
                    final ArrayList<RegisteredMediaRouteProvider> mProviders = this.mProviders;
                    final int n2 = n + 1;
                    mProviders.add(n, registeredMediaRouteProvider);
                    this.mCallback.addProvider(registeredMediaRouteProvider);
                    n = n2;
                }
                else {
                    if (provider < n) {
                        continue Label_0153_Outer;
                    }
                    final RegisteredMediaRouteProvider registeredMediaRouteProvider2 = this.mProviders.get(provider);
                    registeredMediaRouteProvider2.start();
                    registeredMediaRouteProvider2.rebindIfDisconnected();
                    final ArrayList<RegisteredMediaRouteProvider> mProviders2 = this.mProviders;
                    final int n3 = n + 1;
                    Collections.swap(mProviders2, provider, n);
                    n = n3;
                }
                while (true) {
                    continue Label_0153_Outer;
                    continue;
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
}
