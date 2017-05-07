// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.view.View;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.PopupWindow;

public class AppCompatPopupWindow extends PopupWindow
{
    private final boolean mOverlapAnchor;
    
    public AppCompatPopupWindow(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R$styleable.PopupWindow, n, 0);
        this.mOverlapAnchor = obtainStyledAttributes.getBoolean(R$styleable.PopupWindow_overlapAnchor, false);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R$styleable.PopupWindow_android_popupBackground));
        obtainStyledAttributes.recycle();
    }
    
    public void showAsDropDown(final View view, final int n, final int n2) {
        int n3 = n2;
        if (Build$VERSION.SDK_INT < 21) {
            n3 = n2;
            if (this.mOverlapAnchor) {
                n3 = n2 - view.getHeight();
            }
        }
        super.showAsDropDown(view, n, n3);
    }
    
    @TargetApi(19)
    public void showAsDropDown(final View view, final int n, final int n2, final int n3) {
        int n4 = n2;
        if (Build$VERSION.SDK_INT < 21) {
            n4 = n2;
            if (this.mOverlapAnchor) {
                n4 = n2 - view.getHeight();
            }
        }
        super.showAsDropDown(view, n, n4, n3);
    }
    
    public void update(final View view, final int n, int n2, final int n3, final int n4) {
        if (Build$VERSION.SDK_INT < 21 && this.mOverlapAnchor) {
            n2 -= view.getHeight();
        }
        super.update(view, n, n2, n3, n4);
    }
}
