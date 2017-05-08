// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import java.io.File;

interface JSCHeapCapture$PerCaptureCallback
{
    void onFailure(final JSCHeapCapture$CaptureException p0);
    
    void onSuccess(final File p0);
}
