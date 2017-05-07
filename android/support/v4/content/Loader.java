// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.support.v4.util.DebugUtils;

public class Loader<D>
{
    boolean mAbandoned;
    boolean mContentChanged;
    int mId;
    Loader$OnLoadCompleteListener<D> mListener;
    Loader$OnLoadCanceledListener<D> mOnLoadCanceledListener;
    boolean mProcessingChange;
    boolean mReset;
    boolean mStarted;
    
    public String dataToString(final D n) {
        final StringBuilder sb = new StringBuilder(64);
        DebugUtils.buildShortClassTag(n, sb);
        sb.append("}");
        return sb.toString();
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.print(s);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mListener=");
        printWriter.println(this.mListener);
        if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
            printWriter.print(s);
            printWriter.print("mStarted=");
            printWriter.print(this.mStarted);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.mContentChanged);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.mProcessingChange);
        }
        if (this.mAbandoned || this.mReset) {
            printWriter.print(s);
            printWriter.print("mAbandoned=");
            printWriter.print(this.mAbandoned);
            printWriter.print(" mReset=");
            printWriter.println(this.mReset);
        }
    }
    
    protected void onReset() {
    }
    
    protected void onStartLoading() {
    }
    
    protected void onStopLoading() {
    }
    
    public void registerListener(final int mId, final Loader$OnLoadCompleteListener<D> mListener) {
        if (this.mListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mListener = mListener;
        this.mId = mId;
    }
    
    public void registerOnLoadCanceledListener(final Loader$OnLoadCanceledListener<D> mOnLoadCanceledListener) {
        if (this.mOnLoadCanceledListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mOnLoadCanceledListener = mOnLoadCanceledListener;
    }
    
    public void reset() {
        this.onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }
    
    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        this.onStartLoading();
    }
    
    public void stopLoading() {
        this.mStarted = false;
        this.onStopLoading();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        DebugUtils.buildShortClassTag(this, sb);
        sb.append(" id=");
        sb.append(this.mId);
        sb.append("}");
        return sb.toString();
    }
    
    public void unregisterListener(final Loader$OnLoadCompleteListener<D> loader$OnLoadCompleteListener) {
        if (this.mListener == null) {
            throw new IllegalStateException("No listener register");
        }
        if (this.mListener != loader$OnLoadCompleteListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mListener = null;
    }
    
    public void unregisterOnLoadCanceledListener(final Loader$OnLoadCanceledListener<D> loader$OnLoadCanceledListener) {
        if (this.mOnLoadCanceledListener == null) {
            throw new IllegalStateException("No listener register");
        }
        if (this.mOnLoadCanceledListener != loader$OnLoadCanceledListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mOnLoadCanceledListener = null;
    }
}
