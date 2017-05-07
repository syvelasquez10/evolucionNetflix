// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.util.DebugUtils;
import android.support.v4.content.Loader;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.Log;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;

class LoaderManagerImpl extends LoaderManager
{
    static boolean DEBUG;
    boolean mCreatingLoader;
    private FragmentHostCallback mHost;
    final SparseArrayCompat<LoaderManagerImpl$LoaderInfo> mInactiveLoaders;
    final SparseArrayCompat<LoaderManagerImpl$LoaderInfo> mLoaders;
    boolean mRetaining;
    boolean mStarted;
    final String mWho;
    
    static {
        LoaderManagerImpl.DEBUG = false;
    }
    
    LoaderManagerImpl(final String mWho, final FragmentHostCallback mHost, final boolean mStarted) {
        this.mLoaders = new SparseArrayCompat<LoaderManagerImpl$LoaderInfo>();
        this.mInactiveLoaders = new SparseArrayCompat<LoaderManagerImpl$LoaderInfo>();
        this.mWho = mWho;
        this.mHost = mHost;
        this.mStarted = mStarted;
    }
    
    private LoaderManagerImpl$LoaderInfo createAndInstallLoader(final int n, final Bundle bundle, final LoaderManager$LoaderCallbacks<Object> loaderManager$LoaderCallbacks) {
        try {
            this.mCreatingLoader = true;
            final LoaderManagerImpl$LoaderInfo loader = this.createLoader(n, bundle, loaderManager$LoaderCallbacks);
            this.installLoader(loader);
            return loader;
        }
        finally {
            this.mCreatingLoader = false;
        }
    }
    
    private LoaderManagerImpl$LoaderInfo createLoader(final int n, final Bundle bundle, final LoaderManager$LoaderCallbacks<Object> loaderManager$LoaderCallbacks) {
        final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo = new LoaderManagerImpl$LoaderInfo(n, bundle, loaderManager$LoaderCallbacks);
        loaderManagerImpl$LoaderInfo.mLoader = loaderManager$LoaderCallbacks.onCreateLoader(n, bundle);
        return loaderManagerImpl$LoaderInfo;
    }
    
    @Override
    public void destroyLoader(int indexOfKey) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "destroyLoader in " + this + " of " + indexOfKey);
        }
        final int indexOfKey2 = this.mLoaders.indexOfKey(indexOfKey);
        if (indexOfKey2 >= 0) {
            final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo = this.mLoaders.valueAt(indexOfKey2);
            this.mLoaders.removeAt(indexOfKey2);
            loaderManagerImpl$LoaderInfo.destroy();
        }
        indexOfKey = this.mInactiveLoaders.indexOfKey(indexOfKey);
        if (indexOfKey >= 0) {
            final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo2 = this.mInactiveLoaders.valueAt(indexOfKey);
            this.mInactiveLoaders.removeAt(indexOfKey);
            loaderManagerImpl$LoaderInfo2.destroy();
        }
        if (this.mHost != null && !this.hasRunningLoaders()) {
            this.mHost.mFragmentManager.startPendingDeferredFragments();
        }
    }
    
    void doDestroy() {
        if (!this.mRetaining) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (int i = this.mLoaders.size() - 1; i >= 0; --i) {
                this.mLoaders.valueAt(i).destroy();
            }
            this.mLoaders.clear();
        }
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (int j = this.mInactiveLoaders.size() - 1; j >= 0; --j) {
            this.mInactiveLoaders.valueAt(j).destroy();
        }
        this.mInactiveLoaders.clear();
    }
    
    void doReportNextStart() {
        for (int i = this.mLoaders.size() - 1; i >= 0; --i) {
            this.mLoaders.valueAt(i).mReportNextStart = true;
        }
    }
    
    void doReportStart() {
        for (int i = this.mLoaders.size() - 1; i >= 0; --i) {
            this.mLoaders.valueAt(i).reportStart();
        }
    }
    
    void doRetain() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (!this.mStarted) {
            final RuntimeException ex = new RuntimeException("here");
            ex.fillInStackTrace();
            Log.w("LoaderManager", "Called doRetain when not started: " + this, (Throwable)ex);
        }
        else {
            this.mRetaining = true;
            this.mStarted = false;
            for (int i = this.mLoaders.size() - 1; i >= 0; --i) {
                this.mLoaders.valueAt(i).retain();
            }
        }
    }
    
    void doStart() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.mStarted) {
            final RuntimeException ex = new RuntimeException("here");
            ex.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, (Throwable)ex);
        }
        else {
            this.mStarted = true;
            for (int i = this.mLoaders.size() - 1; i >= 0; --i) {
                this.mLoaders.valueAt(i).start();
            }
        }
    }
    
    void doStop() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (!this.mStarted) {
            final RuntimeException ex = new RuntimeException("here");
            ex.fillInStackTrace();
            Log.w("LoaderManager", "Called doStop when not started: " + this, (Throwable)ex);
            return;
        }
        for (int i = this.mLoaders.size() - 1; i >= 0; --i) {
            this.mLoaders.valueAt(i).stop();
        }
        this.mStarted = false;
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        final int n = 0;
        if (this.mLoaders.size() > 0) {
            printWriter.print(s);
            printWriter.println("Active Loaders:");
            final String string = s + "    ";
            for (int i = 0; i < this.mLoaders.size(); ++i) {
                final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo = this.mLoaders.valueAt(i);
                printWriter.print(s);
                printWriter.print("  #");
                printWriter.print(this.mLoaders.keyAt(i));
                printWriter.print(": ");
                printWriter.println(loaderManagerImpl$LoaderInfo.toString());
                loaderManagerImpl$LoaderInfo.dump(string, fileDescriptor, printWriter, array);
            }
        }
        if (this.mInactiveLoaders.size() > 0) {
            printWriter.print(s);
            printWriter.println("Inactive Loaders:");
            final String string2 = s + "    ";
            for (int j = n; j < this.mInactiveLoaders.size(); ++j) {
                final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo2 = this.mInactiveLoaders.valueAt(j);
                printWriter.print(s);
                printWriter.print("  #");
                printWriter.print(this.mInactiveLoaders.keyAt(j));
                printWriter.print(": ");
                printWriter.println(loaderManagerImpl$LoaderInfo2.toString());
                loaderManagerImpl$LoaderInfo2.dump(string2, fileDescriptor, printWriter, array);
            }
        }
    }
    
    void finishRetain() {
        if (this.mRetaining) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.mRetaining = false;
            for (int i = this.mLoaders.size() - 1; i >= 0; --i) {
                this.mLoaders.valueAt(i).finishRetain();
            }
        }
    }
    
    @Override
    public <D> Loader<D> getLoader(final int n) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        }
        final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo = this.mLoaders.get(n);
        if (loaderManagerImpl$LoaderInfo == null) {
            return null;
        }
        if (loaderManagerImpl$LoaderInfo.mPendingLoader != null) {
            return (Loader<D>)loaderManagerImpl$LoaderInfo.mPendingLoader.mLoader;
        }
        return (Loader<D>)loaderManagerImpl$LoaderInfo.mLoader;
    }
    
    @Override
    public boolean hasRunningLoaders() {
        final int size = this.mLoaders.size();
        int i = 0;
        boolean b = false;
        while (i < size) {
            final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo = this.mLoaders.valueAt(i);
            b |= (loaderManagerImpl$LoaderInfo.mStarted && !loaderManagerImpl$LoaderInfo.mDeliveredData);
            ++i;
        }
        return b;
    }
    
    @Override
    public <D> Loader<D> initLoader(final int n, final Bundle bundle, final LoaderManager$LoaderCallbacks<D> mCallbacks) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        }
        final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo = this.mLoaders.get(n);
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
        }
        LoaderManagerImpl$LoaderInfo andInstallLoader;
        if (loaderManagerImpl$LoaderInfo == null) {
            final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo2 = andInstallLoader = this.createAndInstallLoader(n, bundle, (LoaderManager$LoaderCallbacks<Object>)mCallbacks);
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Created new loader " + loaderManagerImpl$LoaderInfo2);
                andInstallLoader = loaderManagerImpl$LoaderInfo2;
            }
        }
        else {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Re-using existing loader " + loaderManagerImpl$LoaderInfo);
            }
            loaderManagerImpl$LoaderInfo.mCallbacks = (LoaderManager$LoaderCallbacks<Object>)mCallbacks;
            andInstallLoader = loaderManagerImpl$LoaderInfo;
        }
        if (andInstallLoader.mHaveData && this.mStarted) {
            andInstallLoader.callOnLoadFinished(andInstallLoader.mLoader, andInstallLoader.mData);
        }
        return (Loader<D>)andInstallLoader.mLoader;
    }
    
    void installLoader(final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo) {
        this.mLoaders.put(loaderManagerImpl$LoaderInfo.mId, loaderManagerImpl$LoaderInfo);
        if (this.mStarted) {
            loaderManagerImpl$LoaderInfo.start();
        }
    }
    
    @Override
    public <D> Loader<D> restartLoader(final int n, final Bundle bundle, final LoaderManager$LoaderCallbacks<D> loaderManager$LoaderCallbacks) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        }
        final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo = this.mLoaders.get(n);
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "restartLoader in " + this + ": args=" + bundle);
        }
        if (loaderManagerImpl$LoaderInfo != null) {
            final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo2 = this.mInactiveLoaders.get(n);
            if (loaderManagerImpl$LoaderInfo2 != null) {
                if (loaderManagerImpl$LoaderInfo.mHaveData) {
                    if (LoaderManagerImpl.DEBUG) {
                        Log.v("LoaderManager", "  Removing last inactive loader: " + loaderManagerImpl$LoaderInfo);
                    }
                    loaderManagerImpl$LoaderInfo2.mDeliveredData = false;
                    loaderManagerImpl$LoaderInfo2.destroy();
                    loaderManagerImpl$LoaderInfo.mLoader.abandon();
                    this.mInactiveLoaders.put(n, loaderManagerImpl$LoaderInfo);
                }
                else {
                    if (loaderManagerImpl$LoaderInfo.mStarted) {
                        if (LoaderManagerImpl.DEBUG) {
                            Log.v("LoaderManager", "  Current loader is running; attempting to cancel");
                        }
                        loaderManagerImpl$LoaderInfo.cancel();
                        if (loaderManagerImpl$LoaderInfo.mPendingLoader != null) {
                            if (LoaderManagerImpl.DEBUG) {
                                Log.v("LoaderManager", "  Removing pending loader: " + loaderManagerImpl$LoaderInfo.mPendingLoader);
                            }
                            loaderManagerImpl$LoaderInfo.mPendingLoader.destroy();
                            loaderManagerImpl$LoaderInfo.mPendingLoader = null;
                        }
                        if (LoaderManagerImpl.DEBUG) {
                            Log.v("LoaderManager", "  Enqueuing as new pending loader");
                        }
                        loaderManagerImpl$LoaderInfo.mPendingLoader = this.createLoader(n, bundle, (LoaderManager$LoaderCallbacks<Object>)loaderManager$LoaderCallbacks);
                        return (Loader<D>)loaderManagerImpl$LoaderInfo.mPendingLoader.mLoader;
                    }
                    if (LoaderManagerImpl.DEBUG) {
                        Log.v("LoaderManager", "  Current loader is stopped; replacing");
                    }
                    this.mLoaders.put(n, null);
                    loaderManagerImpl$LoaderInfo.destroy();
                }
            }
            else {
                if (LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Making last loader inactive: " + loaderManagerImpl$LoaderInfo);
                }
                loaderManagerImpl$LoaderInfo.mLoader.abandon();
                this.mInactiveLoaders.put(n, loaderManagerImpl$LoaderInfo);
            }
        }
        return (Loader<D>)this.createAndInstallLoader(n, bundle, (LoaderManager$LoaderCallbacks<Object>)loaderManager$LoaderCallbacks).mLoader;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        DebugUtils.buildShortClassTag(this.mHost, sb);
        sb.append("}}");
        return sb.toString();
    }
    
    void updateHostController(final FragmentHostCallback mHost) {
        this.mHost = mHost;
    }
}
