// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Fragment;

public final class b extends c$a
{
    private Fragment Sb;
    
    private b(final Fragment sb) {
        this.Sb = sb;
    }
    
    public static b a(final Fragment fragment) {
        if (fragment != null) {
            return new b(fragment);
        }
        return null;
    }
    
    public void d(final d d) {
        this.Sb.registerForContextMenu((View)e.f(d));
    }
    
    public void e(final d d) {
        this.Sb.unregisterForContextMenu((View)e.f(d));
    }
    
    public Bundle getArguments() {
        return this.Sb.getArguments();
    }
    
    public int getId() {
        return this.Sb.getId();
    }
    
    public boolean getRetainInstance() {
        return this.Sb.getRetainInstance();
    }
    
    public String getTag() {
        return this.Sb.getTag();
    }
    
    public int getTargetRequestCode() {
        return this.Sb.getTargetRequestCode();
    }
    
    public boolean getUserVisibleHint() {
        return this.Sb.getUserVisibleHint();
    }
    
    public d getView() {
        return e.k(this.Sb.getView());
    }
    
    public boolean isAdded() {
        return this.Sb.isAdded();
    }
    
    public boolean isDetached() {
        return this.Sb.isDetached();
    }
    
    public boolean isHidden() {
        return this.Sb.isHidden();
    }
    
    public boolean isInLayout() {
        return this.Sb.isInLayout();
    }
    
    public boolean isRemoving() {
        return this.Sb.isRemoving();
    }
    
    public boolean isResumed() {
        return this.Sb.isResumed();
    }
    
    public boolean isVisible() {
        return this.Sb.isVisible();
    }
    
    public d iu() {
        return e.k(this.Sb.getActivity());
    }
    
    public c iv() {
        return a(this.Sb.getParentFragment());
    }
    
    public d iw() {
        return e.k(this.Sb.getResources());
    }
    
    public c ix() {
        return a(this.Sb.getTargetFragment());
    }
    
    public void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.Sb.setHasOptionsMenu(hasOptionsMenu);
    }
    
    public void setMenuVisibility(final boolean menuVisibility) {
        this.Sb.setMenuVisibility(menuVisibility);
    }
    
    public void setRetainInstance(final boolean retainInstance) {
        this.Sb.setRetainInstance(retainInstance);
    }
    
    public void setUserVisibleHint(final boolean userVisibleHint) {
        this.Sb.setUserVisibleHint(userVisibleHint);
    }
    
    public void startActivity(final Intent intent) {
        this.Sb.startActivity(intent);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.Sb.startActivityForResult(intent, n);
    }
}
