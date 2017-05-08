// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.Event;
import android.content.DialogInterface;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.content.DialogInterface$OnShowListener;

class ReactModalHostManager$2 implements DialogInterface$OnShowListener
{
    final /* synthetic */ ReactModalHostManager this$0;
    final /* synthetic */ EventDispatcher val$dispatcher;
    final /* synthetic */ ReactModalHostView val$view;
    
    ReactModalHostManager$2(final ReactModalHostManager this$0, final EventDispatcher val$dispatcher, final ReactModalHostView val$view) {
        this.this$0 = this$0;
        this.val$dispatcher = val$dispatcher;
        this.val$view = val$view;
    }
    
    public void onShow(final DialogInterface dialogInterface) {
        this.val$dispatcher.dispatchEvent(new ShowEvent(this.val$view.getId()));
    }
}
