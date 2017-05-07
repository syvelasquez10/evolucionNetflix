// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.widget.Toast;
import android.support.v4.view.ViewCompat;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.content.res.TypedArray;
import android.content.res.Resources;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$bool;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.support.v7.widget.ActionMenuView$ActionMenuChildView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.support.v7.widget.ListPopupWindow$ForwardingListener;

class b extends ListPopupWindow$ForwardingListener
{
    final /* synthetic */ ActionMenuItemView a;
    
    public b(final ActionMenuItemView a) {
        this.a = a;
        super((View)a);
    }
    
    @Override
    public ListPopupWindow getPopup() {
        if (this.a.f != null) {
            return this.a.f.getPopup();
        }
        return null;
    }
    
    @Override
    protected boolean onForwardingStarted() {
        boolean b2;
        final boolean b = b2 = false;
        if (this.a.d != null) {
            b2 = b;
            if (this.a.d.invokeItem(this.a.a)) {
                final ListPopupWindow popup = this.getPopup();
                b2 = b;
                if (popup != null) {
                    b2 = b;
                    if (popup.isShowing()) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
}
