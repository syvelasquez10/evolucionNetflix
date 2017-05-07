// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import java.lang.ref.WeakReference;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import android.view.ViewTreeObserver$OnScrollChangedListener;

final class AppCompatPopupWindow$1 implements ViewTreeObserver$OnScrollChangedListener
{
    final /* synthetic */ Field val$fieldAnchor;
    final /* synthetic */ ViewTreeObserver$OnScrollChangedListener val$originalListener;
    final /* synthetic */ PopupWindow val$popup;
    
    AppCompatPopupWindow$1(final Field val$fieldAnchor, final PopupWindow val$popup, final ViewTreeObserver$OnScrollChangedListener val$originalListener) {
        this.val$fieldAnchor = val$fieldAnchor;
        this.val$popup = val$popup;
        this.val$originalListener = val$originalListener;
    }
    
    public void onScrollChanged() {
        try {
            final WeakReference weakReference = (WeakReference)this.val$fieldAnchor.get(this.val$popup);
            if (weakReference != null) {
                if (weakReference.get() == null) {
                    return;
                }
                this.val$originalListener.onScrollChanged();
            }
        }
        catch (IllegalAccessException ex) {}
    }
}
