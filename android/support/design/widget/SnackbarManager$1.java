// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Message;
import android.os.Handler$Callback;

class SnackbarManager$1 implements Handler$Callback
{
    final /* synthetic */ SnackbarManager this$0;
    
    SnackbarManager$1(final SnackbarManager this$0) {
        this.this$0 = this$0;
    }
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                this.this$0.handleTimeout((SnackbarManager$SnackbarRecord)message.obj);
                return true;
            }
        }
    }
}
