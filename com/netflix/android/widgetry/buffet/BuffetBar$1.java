// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.os.Message;
import android.os.Handler$Callback;

final class BuffetBar$1 implements Handler$Callback
{
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                ((BuffetBar)message.obj).showView(true);
                return true;
            }
            case 2: {
                ((BuffetBar)message.obj).showView(false);
                return true;
            }
            case 1: {
                ((BuffetBar)message.obj).hideView(message.arg1);
                return true;
            }
        }
    }
}
