// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Message;
import android.os.Handler$Callback;

final class Snackbar$1 implements Handler$Callback
{
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                ((Snackbar)message.obj).showView();
                return true;
            }
            case 1: {
                ((Snackbar)message.obj).hideView(message.arg1);
                return true;
            }
        }
    }
}
