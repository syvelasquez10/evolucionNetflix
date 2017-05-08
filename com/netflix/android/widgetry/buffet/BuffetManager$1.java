// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.os.Message;
import android.os.Handler$Callback;

class BuffetManager$1 implements Handler$Callback
{
    final /* synthetic */ BuffetManager this$0;
    
    BuffetManager$1(final BuffetManager this$0) {
        this.this$0 = this$0;
    }
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                this.this$0.handleTimeout((BuffetManager$SnackbarRecord)message.obj);
                return true;
            }
        }
    }
}
