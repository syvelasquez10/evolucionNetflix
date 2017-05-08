// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.switchview;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;

final class ReactSwitchManager$1 implements CompoundButton$OnCheckedChangeListener
{
    public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
        ((ReactContext)compoundButton.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(new ReactSwitchEvent(compoundButton.getId(), b));
    }
}
