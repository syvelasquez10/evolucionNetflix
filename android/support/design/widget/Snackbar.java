// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.widget.TextView;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.support.design.internal.SnackbarContentLayout;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.view.View;
import android.view.ViewGroup;

public final class Snackbar extends BaseTransientBottomBar<Snackbar>
{
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    private BaseTransientBottomBar$BaseCallback<Snackbar> mCallback;
    
    private Snackbar(final ViewGroup viewGroup, final View view, final BaseTransientBottomBar$ContentViewCallback baseTransientBottomBar$ContentViewCallback) {
        super(viewGroup, view, baseTransientBottomBar$ContentViewCallback);
    }
    
    private static ViewGroup findSuitableParent(View view) {
        ViewGroup viewGroup = null;
        View view2 = view;
        while (!(view2 instanceof CoordinatorLayout)) {
            ViewGroup viewGroup2 = viewGroup;
            if (view2 instanceof FrameLayout) {
                if (view2.getId() == 16908290) {
                    return (ViewGroup)view2;
                }
                viewGroup2 = (ViewGroup)view2;
            }
            if ((view = view2) != null) {
                final ViewParent parent = view2.getParent();
                if (parent instanceof View) {
                    view = (View)parent;
                }
                else {
                    view = null;
                }
            }
            view2 = view;
            viewGroup = viewGroup2;
            if (view == null) {
                return viewGroup2;
            }
        }
        return (ViewGroup)view2;
    }
    
    public static Snackbar make(final View view, final int n, final int n2) {
        return make(view, view.getResources().getText(n), n2);
    }
    
    public static Snackbar make(final View view, final CharSequence text, final int duration) {
        final ViewGroup suitableParent = findSuitableParent(view);
        final SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout)LayoutInflater.from(suitableParent.getContext()).inflate(R$layout.design_layout_snackbar_include, suitableParent, false);
        final Snackbar snackbar = new Snackbar(suitableParent, (View)snackbarContentLayout, snackbarContentLayout);
        snackbar.setText(text);
        snackbar.setDuration(duration);
        return snackbar;
    }
    
    public Snackbar setAction(final int n, final View$OnClickListener view$OnClickListener) {
        return this.setAction(this.getContext().getText(n), view$OnClickListener);
    }
    
    public Snackbar setAction(final CharSequence text, final View$OnClickListener view$OnClickListener) {
        final Button actionView = ((SnackbarContentLayout)this.mView.getChildAt(0)).getActionView();
        if (TextUtils.isEmpty(text) || view$OnClickListener == null) {
            ((TextView)actionView).setVisibility(8);
            ((TextView)actionView).setOnClickListener((View$OnClickListener)null);
            return this;
        }
        ((TextView)actionView).setVisibility(0);
        ((TextView)actionView).setText(text);
        ((TextView)actionView).setOnClickListener((View$OnClickListener)new Snackbar$1(this, view$OnClickListener));
        return this;
    }
    
    public Snackbar setActionTextColor(final int textColor) {
        ((TextView)((SnackbarContentLayout)this.mView.getChildAt(0)).getActionView()).setTextColor(textColor);
        return this;
    }
    
    public Snackbar setActionTextColor(final ColorStateList textColor) {
        ((TextView)((SnackbarContentLayout)this.mView.getChildAt(0)).getActionView()).setTextColor(textColor);
        return this;
    }
    
    @Deprecated
    public Snackbar setCallback(final Snackbar$Callback mCallback) {
        if (this.mCallback != null) {
            this.removeCallback(this.mCallback);
        }
        if (mCallback != null) {
            this.addCallback(mCallback);
        }
        this.mCallback = mCallback;
        return this;
    }
    
    public Snackbar setText(final int n) {
        return this.setText(this.getContext().getText(n));
    }
    
    public Snackbar setText(final CharSequence text) {
        ((SnackbarContentLayout)this.mView.getChildAt(0)).getMessageView().setText(text);
        return this;
    }
}
