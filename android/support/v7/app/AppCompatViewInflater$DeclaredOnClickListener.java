// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.lang.reflect.InvocationTargetException;
import android.content.ContextWrapper;
import java.lang.reflect.Method;
import android.content.Context;
import android.view.View;
import android.view.View$OnClickListener;

class AppCompatViewInflater$DeclaredOnClickListener implements View$OnClickListener
{
    private final View mHostView;
    private final String mMethodName;
    private Context mResolvedContext;
    private Method mResolvedMethod;
    
    public AppCompatViewInflater$DeclaredOnClickListener(final View mHostView, final String mMethodName) {
        this.mHostView = mHostView;
        this.mMethodName = mMethodName;
    }
    
    private void resolveMethod(Context baseContext, final String s) {
        while (baseContext != null) {
            try {
                if (!baseContext.isRestricted()) {
                    final Method method = baseContext.getClass().getMethod(this.mMethodName, View.class);
                    if (method != null) {
                        this.mResolvedMethod = method;
                        this.mResolvedContext = baseContext;
                        return;
                    }
                }
            }
            catch (NoSuchMethodException ex) {}
            if (baseContext instanceof ContextWrapper) {
                baseContext = ((ContextWrapper)baseContext).getBaseContext();
            }
            else {
                baseContext = null;
            }
        }
        final int id = this.mHostView.getId();
        String string;
        if (id == -1) {
            string = "";
        }
        else {
            string = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(id) + "'";
        }
        throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.mHostView.getClass() + string);
    }
    
    public void onClick(final View view) {
        if (this.mResolvedMethod == null) {
            this.resolveMethod(this.mHostView.getContext(), this.mMethodName);
        }
        try {
            this.mResolvedMethod.invoke(this.mResolvedContext, view);
        }
        catch (IllegalAccessException ex) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", ex);
        }
        catch (InvocationTargetException ex2) {
            throw new IllegalStateException("Could not execute method for android:onClick", ex2);
        }
    }
}
