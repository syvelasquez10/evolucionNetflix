// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

public class NetflixErrorThrowableImpl implements NetflixError
{
    private String mErrorCode;
    private boolean mFatal;
    private String mStrackTrace;
    private Throwable mThrowable;
    private long mTimestamp;
    
    public NetflixErrorThrowableImpl(final Throwable t, final String s) {
        this(t, true, s, System.currentTimeMillis());
    }
    
    public NetflixErrorThrowableImpl(final Throwable t, final String s, final long n) {
        this(t, true, s, n);
    }
    
    public NetflixErrorThrowableImpl(final Throwable mThrowable, final boolean mFatal, final String mErrorCode, final long mTimestamp) {
        if (mThrowable == null) {
            throw new IllegalArgumentException("Throwable must be provided!");
        }
        this.mThrowable = mThrowable;
        this.mFatal = mFatal;
        this.mTimestamp = mTimestamp;
        final StringBuilder sb = new StringBuilder();
        this.printStackTrace(sb);
        this.mStrackTrace = sb.toString();
        this.mErrorCode = mErrorCode;
    }
    
    private void printStackTrace(final StringBuilder sb) {
        if (this.mThrowable.getStackTrace() != null || this.mThrowable.getStackTrace().length > 0) {
            final StackTraceElement[] stackTrace = this.mThrowable.getStackTrace();
            for (int i = 0; i < stackTrace.length; ++i) {
                sb.append("\tat " + stackTrace[i]).append('\n');
            }
            final Throwable cause = this.mThrowable.getCause();
            if (cause != null) {
                printStackTraceAsCause(cause, sb, stackTrace);
            }
        }
    }
    
    private static void printStackTraceAsCause(Throwable cause, final StringBuilder sb, final StackTraceElement[] array) {
        final StackTraceElement[] stackTrace = cause.getStackTrace();
        if (stackTrace != null) {
            int n = stackTrace.length - 1;
            for (int n2 = array.length - 1; n >= 0 && n2 >= 0 && stackTrace[n].equals(array[n2]); --n, --n2) {}
            final int n3 = stackTrace.length - 1 - n;
            sb.append("Caused by: ").append(cause);
            for (int i = 0; i <= n; ++i) {
                sb.append("\tat ").append(stackTrace[i]);
            }
            if (n3 != 0) {
                sb.append("\t... ").append(n3).append(" more");
            }
            cause = cause.getCause();
            if (cause != null) {
                printStackTraceAsCause(cause, sb, stackTrace);
            }
        }
    }
    
    @Override
    public String getErrorCode() {
        return this.mErrorCode;
    }
    
    @Override
    public String getErrorMessage() {
        return this.mThrowable.getMessage();
    }
    
    @Override
    public String getStackTrace() {
        return this.mStrackTrace;
    }
    
    @Override
    public long getTimestamp() {
        return this.mTimestamp;
    }
    
    @Override
    public boolean isFatal() {
        return this.mFatal;
    }
    
    @Override
    public String toString() {
        return "NetflixErrorThrowableImpl [mThrowable=" + this.mThrowable + ", mFatal=" + this.mFatal + ", mStrackTrace=" + this.mStrackTrace + ", mErrorCode=" + this.mErrorCode + ", mTimestamp=" + this.mTimestamp + "]";
    }
}
