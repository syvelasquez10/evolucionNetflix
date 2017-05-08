// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.util.DebugUtils;
import java.lang.reflect.Modifier;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.Log;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v4.content.Loader$OnLoadCompleteListener;
import android.support.v4.content.Loader$OnLoadCanceledListener;

final class LoaderManagerImpl$LoaderInfo implements Loader$OnLoadCanceledListener<Object>, Loader$OnLoadCompleteListener<Object>
{
    final Bundle mArgs;
    LoaderManager$LoaderCallbacks<Object> mCallbacks;
    Object mData;
    boolean mDeliveredData;
    boolean mDestroyed;
    boolean mHaveData;
    final int mId;
    boolean mListenerRegistered;
    Loader<Object> mLoader;
    LoaderManagerImpl$LoaderInfo mPendingLoader;
    boolean mReportNextStart;
    boolean mRetaining;
    boolean mRetainingStarted;
    boolean mStarted;
    final /* synthetic */ LoaderManagerImpl this$0;
    
    public LoaderManagerImpl$LoaderInfo(final LoaderManagerImpl this$0, final int mId, final Bundle mArgs, final LoaderManager$LoaderCallbacks<Object> mCallbacks) {
        this.this$0 = this$0;
        this.mId = mId;
        this.mArgs = mArgs;
        this.mCallbacks = mCallbacks;
    }
    
    void callOnLoadFinished(final Loader<Object> loader, final Object o) {
        if (this.mCallbacks == null) {
            return;
        }
        while (true) {
            Label_0158: {
                if (this.this$0.mHost == null) {
                    break Label_0158;
                }
                final String mNoTransactionsBecause = this.this$0.mHost.mFragmentManager.mNoTransactionsBecause;
                this.this$0.mHost.mFragmentManager.mNoTransactionsBecause = "onLoadFinished";
                try {
                    if (LoaderManagerImpl.DEBUG) {
                        Log.v("LoaderManager", "  onLoadFinished in " + loader + ": " + loader.dataToString(o));
                    }
                    this.mCallbacks.onLoadFinished(loader, o);
                    if (this.this$0.mHost != null) {
                        this.this$0.mHost.mFragmentManager.mNoTransactionsBecause = mNoTransactionsBecause;
                    }
                    this.mDeliveredData = true;
                    return;
                }
                finally {
                    if (this.this$0.mHost != null) {
                        this.this$0.mHost.mFragmentManager.mNoTransactionsBecause = mNoTransactionsBecause;
                    }
                }
            }
            final String mNoTransactionsBecause = null;
            continue;
        }
    }
    
    boolean cancel() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Canceling: " + this);
        }
        if (this.mStarted && this.mLoader != null && this.mListenerRegistered) {
            final boolean cancelLoad = this.mLoader.cancelLoad();
            if (!cancelLoad) {
                this.onLoadCanceled(this.mLoader);
            }
            return cancelLoad;
        }
        return false;
    }
    
    void destroy() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Destroying: " + this);
        }
        this.mDestroyed = true;
        final boolean mDeliveredData = this.mDeliveredData;
        this.mDeliveredData = false;
        while (true) {
            Label_0178: {
                if (this.mCallbacks == null || this.mLoader == null || !this.mHaveData || !mDeliveredData) {
                    break Label_0178;
                }
                if (LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Resetting: " + this);
                }
                if (this.this$0.mHost == null) {
                    break Label_0178;
                }
                final String mNoTransactionsBecause = this.this$0.mHost.mFragmentManager.mNoTransactionsBecause;
                this.this$0.mHost.mFragmentManager.mNoTransactionsBecause = "onLoaderReset";
                try {
                    this.mCallbacks.onLoaderReset(this.mLoader);
                    if (this.this$0.mHost != null) {
                        this.this$0.mHost.mFragmentManager.mNoTransactionsBecause = mNoTransactionsBecause;
                    }
                    this.mCallbacks = null;
                    this.mData = null;
                    this.mHaveData = false;
                    if (this.mLoader != null) {
                        if (this.mListenerRegistered) {
                            this.mListenerRegistered = false;
                            this.mLoader.unregisterListener(this);
                            this.mLoader.unregisterOnLoadCanceledListener(this);
                        }
                        this.mLoader.reset();
                    }
                    if (this.mPendingLoader != null) {
                        this.mPendingLoader.destroy();
                    }
                    return;
                }
                finally {
                    if (this.this$0.mHost != null) {
                        this.this$0.mHost.mFragmentManager.mNoTransactionsBecause = mNoTransactionsBecause;
                    }
                }
            }
            final String mNoTransactionsBecause = null;
            continue;
        }
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.print(s);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mArgs=");
        printWriter.println(this.mArgs);
        printWriter.print(s);
        printWriter.print("mCallbacks=");
        printWriter.println(this.mCallbacks);
        printWriter.print(s);
        printWriter.print("mLoader=");
        printWriter.println(this.mLoader);
        if (this.mLoader != null) {
            this.mLoader.dump(s + "  ", fileDescriptor, printWriter, array);
        }
        if (this.mHaveData || this.mDeliveredData) {
            printWriter.print(s);
            printWriter.print("mHaveData=");
            printWriter.print(this.mHaveData);
            printWriter.print("  mDeliveredData=");
            printWriter.println(this.mDeliveredData);
            printWriter.print(s);
            printWriter.print("mData=");
            printWriter.println(this.mData);
        }
        printWriter.print(s);
        printWriter.print("mStarted=");
        printWriter.print(this.mStarted);
        printWriter.print(" mReportNextStart=");
        printWriter.print(this.mReportNextStart);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        printWriter.print(s);
        printWriter.print("mRetaining=");
        printWriter.print(this.mRetaining);
        printWriter.print(" mRetainingStarted=");
        printWriter.print(this.mRetainingStarted);
        printWriter.print(" mListenerRegistered=");
        printWriter.println(this.mListenerRegistered);
        if (this.mPendingLoader != null) {
            printWriter.print(s);
            printWriter.println("Pending Loader ");
            printWriter.print(this.mPendingLoader);
            printWriter.println(":");
            this.mPendingLoader.dump(s + "  ", fileDescriptor, printWriter, array);
        }
    }
    
    void finishRetain() {
        if (this.mRetaining) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Finished Retaining: " + this);
            }
            this.mRetaining = false;
            if (this.mStarted != this.mRetainingStarted && !this.mStarted) {
                this.stop();
            }
        }
        if (this.mStarted && this.mHaveData && !this.mReportNextStart) {
            this.callOnLoadFinished(this.mLoader, this.mData);
        }
    }
    
    @Override
    public void onLoadCanceled(final Loader<Object> loader) {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "onLoadCanceled: " + this);
        }
        if (this.mDestroyed) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Ignoring load canceled -- destroyed");
            }
        }
        else if (this.this$0.mLoaders.get(this.mId) != this) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Ignoring load canceled -- not active");
            }
        }
        else {
            final LoaderManagerImpl$LoaderInfo mPendingLoader = this.mPendingLoader;
            if (mPendingLoader != null) {
                if (LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Switching to pending loader: " + mPendingLoader);
                }
                this.mPendingLoader = null;
                this.this$0.mLoaders.put(this.mId, null);
                this.destroy();
                this.this$0.installLoader(mPendingLoader);
            }
        }
    }
    
    @Override
    public void onLoadComplete(final Loader<Object> loader, final Object mData) {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "onLoadComplete: " + this);
        }
        if (this.mDestroyed) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
            }
        }
        else if (this.this$0.mLoaders.get(this.mId) != this) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Ignoring load complete -- not active");
            }
        }
        else {
            final LoaderManagerImpl$LoaderInfo mPendingLoader = this.mPendingLoader;
            if (mPendingLoader != null) {
                if (LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Switching to pending loader: " + mPendingLoader);
                }
                this.mPendingLoader = null;
                this.this$0.mLoaders.put(this.mId, null);
                this.destroy();
                this.this$0.installLoader(mPendingLoader);
                return;
            }
            if (this.mData != mData || !this.mHaveData) {
                this.mData = mData;
                this.mHaveData = true;
                if (this.mStarted) {
                    this.callOnLoadFinished(loader, mData);
                }
            }
            final LoaderManagerImpl$LoaderInfo loaderManagerImpl$LoaderInfo = this.this$0.mInactiveLoaders.get(this.mId);
            if (loaderManagerImpl$LoaderInfo != null && loaderManagerImpl$LoaderInfo != this) {
                loaderManagerImpl$LoaderInfo.mDeliveredData = false;
                loaderManagerImpl$LoaderInfo.destroy();
                this.this$0.mInactiveLoaders.remove(this.mId);
            }
            if (this.this$0.mHost != null && !this.this$0.hasRunningLoaders()) {
                this.this$0.mHost.mFragmentManager.startPendingDeferredFragments();
            }
        }
    }
    
    void reportStart() {
        if (this.mStarted && this.mReportNextStart) {
            this.mReportNextStart = false;
            if (this.mHaveData && !this.mRetaining) {
                this.callOnLoadFinished(this.mLoader, this.mData);
            }
        }
    }
    
    void retain() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Retaining: " + this);
        }
        this.mRetaining = true;
        this.mRetainingStarted = this.mStarted;
        this.mStarted = false;
        this.mCallbacks = null;
    }
    
    void start() {
        if (this.mRetaining && this.mRetainingStarted) {
            this.mStarted = true;
        }
        else if (!this.mStarted) {
            this.mStarted = true;
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            if (this.mLoader == null && this.mCallbacks != null) {
                this.mLoader = this.mCallbacks.onCreateLoader(this.mId, this.mArgs);
            }
            if (this.mLoader != null) {
                if (this.mLoader.getClass().isMemberClass() && !Modifier.isStatic(this.mLoader.getClass().getModifiers())) {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.mLoader);
                }
                if (!this.mListenerRegistered) {
                    this.mLoader.registerListener(this.mId, this);
                    this.mLoader.registerOnLoadCanceledListener(this);
                    this.mListenerRegistered = true;
                }
                this.mLoader.startLoading();
            }
        }
    }
    
    void stop() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Stopping: " + this);
        }
        this.mStarted = false;
        if (!this.mRetaining && this.mLoader != null && this.mListenerRegistered) {
            this.mListenerRegistered = false;
            this.mLoader.unregisterListener(this);
            this.mLoader.unregisterOnLoadCanceledListener(this);
            this.mLoader.stopLoading();
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        sb.append("LoaderInfo{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" #");
        sb.append(this.mId);
        sb.append(" : ");
        DebugUtils.buildShortClassTag(this.mLoader, sb);
        sb.append("}}");
        return sb.toString();
    }
}
