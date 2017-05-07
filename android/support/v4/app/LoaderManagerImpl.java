// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.util.DebugUtils;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.Log;
import android.support.v4.util.SparseArrayCompat;

class LoaderManagerImpl extends LoaderManager
{
    static boolean DEBUG;
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
