// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.Event;
import android.content.DialogInterface;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactModalHostManager$1 implements ReactModalHostView$OnRequestCloseListener
{
    final /* synthetic */ ReactModalHostManager this$0;
    final /* synthetic */ EventDispatcher val$dispatcher;
    final /* synthetic */ ReactModalHostView val$view;
    
    ReactModalHostManager$1(final ReactModalHostManager this$0, final EventDispatcher val$dispatcher, final ReactModalHostView val$view) {
        this.this$0 = this$0;
        this.val$dispatcher = val$dispatcher;
        this.val$view = val$view;
    }
    
    @Override
    public void onRequestClose(final DialogInterface dialogInterface) {
        this.val$dispatcher.dispatchEvent(new RequestCloseEvent(this.val$view.getId()));
    }
}
