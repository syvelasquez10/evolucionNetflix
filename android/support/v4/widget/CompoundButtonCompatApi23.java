// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import android.annotation.TargetApi;

@TargetApi(23)
class CompoundButtonCompatApi23
{
    static Drawable getButtonDrawable(final CompoundButton compoundButton) {
        return compoundButton.getButtonDrawable();
    }
}
