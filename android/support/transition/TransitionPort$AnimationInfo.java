// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.View;

class TransitionPort$AnimationInfo
{
    String name;
    TransitionValues values;
    View view;
    WindowIdPort windowId;
    
    TransitionPort$AnimationInfo(final View view, final String name, final WindowIdPort windowId, final TransitionValues values) {
        this.view = view;
        this.name = name;
        this.values = values;
        this.windowId = windowId;
    }
}
