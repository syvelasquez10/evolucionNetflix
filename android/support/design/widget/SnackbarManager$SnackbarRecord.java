// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import java.lang.ref.WeakReference;

class SnackbarManager$SnackbarRecord
{
    private final WeakReference<SnackbarManager$Callback> callback;
    private int duration;
    
    SnackbarManager$SnackbarRecord(final int duration, final SnackbarManager$Callback snackbarManager$Callback) {
        this.callback = new WeakReference<SnackbarManager$Callback>(snackbarManager$Callback);
        this.duration = duration;
    }
    
    boolean isSnackbar(final SnackbarManager$Callback snackbarManager$Callback) {
        return snackbarManager$Callback != null && this.callback.get() == snackbarManager$Callback;
    }
}
