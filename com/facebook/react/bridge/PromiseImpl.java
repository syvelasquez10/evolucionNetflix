// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public class PromiseImpl implements Promise
{
    private Callback mReject;
    private Callback mResolve;
    
    public PromiseImpl(final Callback mResolve, final Callback mReject) {
        this.mResolve = mResolve;
        this.mReject = mReject;
    }
    
    @Deprecated
    @Override
    public void reject(final String s) {
        this.reject("EUNSPECIFIED", s, null);
    }
    
    @Override
    public void reject(final String s, final String s2) {
        this.reject(s, s2, null);
    }
    
    @Override
    public void reject(final String s, final String s2, final Throwable t) {
        if (this.mReject != null) {
            String s3;
            if ((s3 = s) == null) {
                s3 = "EUNSPECIFIED";
            }
            final WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("code", s3);
            writableNativeMap.putString("message", s2);
            this.mReject.invoke(writableNativeMap);
        }
    }
    
    @Override
    public void reject(final String s, final Throwable t) {
        this.reject(s, t.getMessage(), t);
    }
    
    @Override
    public void reject(final Throwable t) {
        this.reject("EUNSPECIFIED", t.getMessage(), t);
    }
    
    @Override
    public void resolve(final Object o) {
        if (this.mResolve != null) {
            this.mResolve.invoke(o);
        }
    }
}
