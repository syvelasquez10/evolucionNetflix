// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import java.io.File;
import java.util.List;
import java.util.LinkedList;

final class JSCHeapCapture$1 implements JSCHeapCapture$PerCaptureCallback
{
    final /* synthetic */ JSCHeapCapture$CaptureCallback val$callback;
    final /* synthetic */ LinkedList val$captureFailures;
    final /* synthetic */ LinkedList val$captureFiles;
    final /* synthetic */ int val$numRegisteredDumpers;
    
    JSCHeapCapture$1(final LinkedList val$captureFiles, final LinkedList val$captureFailures, final int val$numRegisteredDumpers, final JSCHeapCapture$CaptureCallback val$callback) {
        this.val$captureFiles = val$captureFiles;
        this.val$captureFailures = val$captureFailures;
        this.val$numRegisteredDumpers = val$numRegisteredDumpers;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onFailure(final JSCHeapCapture$CaptureException ex) {
        this.val$captureFailures.add(ex);
        if (this.val$captureFiles.size() + this.val$captureFailures.size() == this.val$numRegisteredDumpers) {
            this.val$callback.onComplete(this.val$captureFiles, this.val$captureFailures);
        }
    }
    
    @Override
    public void onSuccess(final File file) {
        this.val$captureFiles.add(file);
        if (this.val$captureFiles.size() + this.val$captureFailures.size() == this.val$numRegisteredDumpers) {
            this.val$callback.onComplete(this.val$captureFiles, this.val$captureFailures);
        }
    }
}
