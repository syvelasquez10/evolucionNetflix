// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReactMethod;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.util.LinkedList;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.HashSet;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class JSCHeapCapture extends ReactContextBaseJavaModule
{
    private static final HashSet<JSCHeapCapture> sRegisteredDumpers;
    private JSCHeapCapture$PerCaptureCallback mCaptureInProgress;
    private JSCHeapCapture$HeapCapture mHeapCapture;
    
    static {
        sRegisteredDumpers = new HashSet<JSCHeapCapture>();
    }
    
    public JSCHeapCapture(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mHeapCapture = null;
        this.mCaptureInProgress = null;
    }
    
    public static void captureHeap(final String s, final JSCHeapCapture$CaptureCallback jscHeapCapture$CaptureCallback) {
        while (true) {
            int n = 0;
            final LinkedList<File> list;
            final LinkedList<JSCHeapCapture$CaptureException> list2;
            synchronized (JSCHeapCapture.class) {
                list = new LinkedList<File>();
                list2 = new LinkedList<JSCHeapCapture$CaptureException>();
                if (JSCHeapCapture.sRegisteredDumpers.isEmpty()) {
                    list2.add(new JSCHeapCapture$CaptureException("No JSC registered"));
                    jscHeapCapture$CaptureCallback.onComplete(list, list2);
                    return;
                }
                for (File file = new File(s + "/capture" + Integer.toString(0) + ".json"); file.delete(); file = new File(s + "/capture" + Integer.toString(n) + ".json")) {
                    ++n;
                }
            }
            final int size = JSCHeapCapture.sRegisteredDumpers.size();
            final Iterator<JSCHeapCapture> iterator = JSCHeapCapture.sRegisteredDumpers.iterator();
            while (iterator.hasNext()) {
                final String s2;
                iterator.next().captureHeapHelper(new File(s2 + "/capture" + Integer.toString(0) + ".json"), new JSCHeapCapture$1(list, list2, size, jscHeapCapture$CaptureCallback));
            }
        }
    }
    
    private void captureHeapHelper(final File file, final JSCHeapCapture$PerCaptureCallback mCaptureInProgress) {
        while (true) {
            Label_0057: {
                synchronized (this) {
                    if (this.mHeapCapture == null) {
                        mCaptureInProgress.onFailure(new JSCHeapCapture$CaptureException("HeapCapture.js module not connected"));
                    }
                    else {
                        if (this.mCaptureInProgress == null) {
                            break Label_0057;
                        }
                        mCaptureInProgress.onFailure(new JSCHeapCapture$CaptureException("Heap capture already in progress"));
                    }
                    return;
                }
            }
            this.mCaptureInProgress = mCaptureInProgress;
            final File file2;
            this.mHeapCapture.captureHeap(file2.getPath());
        }
    }
    
    private static void registerHeapCapture(final JSCHeapCapture jscHeapCapture) {
        synchronized (JSCHeapCapture.class) {
            if (JSCHeapCapture.sRegisteredDumpers.contains(jscHeapCapture)) {
                throw new RuntimeException("a JSCHeapCapture registered more than once");
            }
        }
        final JSCHeapCapture jscHeapCapture2;
        JSCHeapCapture.sRegisteredDumpers.add(jscHeapCapture2);
    }
    // monitorexit(JSCHeapCapture.class)
    
    private static void unregisterHeapCapture(final JSCHeapCapture jscHeapCapture) {
        synchronized (JSCHeapCapture.class) {
            JSCHeapCapture.sRegisteredDumpers.remove(jscHeapCapture);
        }
    }
    
    @ReactMethod
    public void captureComplete(final String s, final String s2) {
        synchronized (this) {
            if (this.mCaptureInProgress != null) {
                if (s2 == null) {
                    this.mCaptureInProgress.onSuccess(new File(s));
                }
                else {
                    this.mCaptureInProgress.onFailure(new JSCHeapCapture$CaptureException(s2));
                }
                this.mCaptureInProgress = null;
            }
        }
    }
    
    @Override
    public String getName() {
        return "JSCHeapCapture";
    }
    
    @Override
    public void initialize() {
        super.initialize();
        this.mHeapCapture = this.getReactApplicationContext().getJSModule(JSCHeapCapture$HeapCapture.class);
        registerHeapCapture(this);
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        unregisterHeapCapture(this);
        this.mHeapCapture = null;
    }
}
