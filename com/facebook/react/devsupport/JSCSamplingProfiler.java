// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReactMethod;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.HashSet;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class JSCSamplingProfiler extends ReactContextBaseJavaModule
{
    private static final HashSet<JSCSamplingProfiler> sRegisteredDumpers;
    private String mOperationError;
    private boolean mOperationInProgress;
    private int mOperationToken;
    private JSCSamplingProfiler$SamplingProfiler mSamplingProfiler;
    private String mSamplingProfilerResult;
    
    static {
        sRegisteredDumpers = new HashSet<JSCSamplingProfiler>();
    }
    
    public JSCSamplingProfiler(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mSamplingProfiler = null;
        this.mOperationInProgress = false;
        this.mOperationToken = 0;
        this.mOperationError = null;
        this.mSamplingProfilerResult = null;
    }
    
    private int getOperationToken() {
        if (this.mOperationInProgress) {
            throw new JSCSamplingProfiler$ProfilerException("Another operation already in progress.");
        }
        this.mOperationInProgress = true;
        return ++this.mOperationToken;
    }
    
    public static List<String> poke(final long n) {
        synchronized (JSCSamplingProfiler.class) {
            final LinkedList list = new LinkedList();
            if (JSCSamplingProfiler.sRegisteredDumpers.isEmpty()) {
                throw new JSCSamplingProfiler$ProfilerException("No JSC registered");
            }
        }
        final LinkedList<String> list2;
        for (final JSCSamplingProfiler jscSamplingProfiler : JSCSamplingProfiler.sRegisteredDumpers) {
            jscSamplingProfiler.pokeHelper(n);
            list2.add(jscSamplingProfiler.mSamplingProfilerResult);
        }
        // monitorexit(JSCSamplingProfiler.class)
        return list2;
    }
    
    private void pokeHelper(final long n) {
        synchronized (this) {
            if (this.mSamplingProfiler == null) {
                throw new JSCSamplingProfiler$ProfilerException("SamplingProfiler.js module not connected");
            }
        }
        this.mSamplingProfiler.poke(this.getOperationToken());
        this.waitForOperation(n);
    }
    // monitorexit(this)
    
    private static void registerSamplingProfiler(final JSCSamplingProfiler jscSamplingProfiler) {
        synchronized (JSCSamplingProfiler.class) {
            if (JSCSamplingProfiler.sRegisteredDumpers.contains(jscSamplingProfiler)) {
                throw new RuntimeException("a JSCSamplingProfiler registered more than once");
            }
        }
        final JSCSamplingProfiler jscSamplingProfiler2;
        JSCSamplingProfiler.sRegisteredDumpers.add(jscSamplingProfiler2);
    }
    // monitorexit(JSCSamplingProfiler.class)
    
    private static void unregisterSamplingProfiler(final JSCSamplingProfiler jscSamplingProfiler) {
        synchronized (JSCSamplingProfiler.class) {
            JSCSamplingProfiler.sRegisteredDumpers.remove(jscSamplingProfiler);
        }
    }
    
    private void waitForOperation(final long n) {
        try {
            this.wait(n);
            if (this.mOperationInProgress) {
                this.mOperationInProgress = false;
                throw new JSCSamplingProfiler$ProfilerException("heap capture timed out.");
            }
        }
        catch (InterruptedException ex) {
            throw new JSCSamplingProfiler$ProfilerException("Waiting for heap capture failed: " + ex.getMessage());
        }
        if (this.mOperationError != null) {
            throw new JSCSamplingProfiler$ProfilerException(this.mOperationError);
        }
    }
    
    @Override
    public String getName() {
        return "JSCSamplingProfiler";
    }
    
    @Override
    public void initialize() {
        super.initialize();
        this.mSamplingProfiler = this.getReactApplicationContext().getJSModule(JSCSamplingProfiler$SamplingProfiler.class);
        registerSamplingProfiler(this);
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        unregisterSamplingProfiler(this);
        this.mSamplingProfiler = null;
    }
    
    @ReactMethod
    public void operationComplete(final int n, final String mSamplingProfilerResult, final String mOperationError) {
        synchronized (this) {
            if (n == this.mOperationToken) {
                this.mOperationInProgress = false;
                this.mSamplingProfilerResult = mSamplingProfilerResult;
                this.mOperationError = mOperationError;
                this.notify();
                return;
            }
            throw new RuntimeException("Completed operation is not in progress.");
        }
    }
}
