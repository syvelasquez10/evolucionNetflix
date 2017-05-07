// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error;

import android.app.Activity;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

public interface ErrorDescriptor
{
    Runnable getBackgroundTask();
    
    AlertDialogFactory$AlertDialogDescriptor getData();
    
    int getPriority();
    
    boolean shouldReportToUserAsDialog(final Activity p0);
}
