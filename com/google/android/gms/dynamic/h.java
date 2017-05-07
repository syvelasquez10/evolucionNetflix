// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.Fragment;

public final class h extends c$a
{
    private Fragment Ll;
    
    private h(final Fragment ll) {
        this.Ll = ll;
    }
    
    public static h a(final Fragment fragment) {
        if (fragment != null) {
            return new h(fragment);
        }
        return null;
    }
    
    public void d(final d d) {
        this.Ll.registerForContextMenu(e.f(d));
    }
    
    public void e(final d d) {
        this.Ll.unregisterForContextMenu(e.f(d));
    }
    
    public Bundle getArguments() {
        return this.Ll.getArguments();
    }
    
    public int getId() {
        return this.Ll.getId();
    }
    
    public boolean getRetainInstance() {
        return this.Ll.getRetainInstance();
    }
    
    public String getTag() {
        return this.Ll.getTag();
    }
    
    public int getTargetRequestCode() {
        return this.Ll.getTargetRequestCode();
    }
    
    public boolean getUserVisibleHint() {
        return this.Ll.getUserVisibleHint();
    }
    
    public d getView() {
        return e.k(this.Ll.getView());
    }
    
    public boolean isAdded() {
        return this.Ll.isAdded();
    }
    
    public boolean isDetached() {
        return this.Ll.isDetached();
    }
    
    public boolean isHidden() {
        return this.Ll.isHidden();
    }
    
    public boolean isInLayout() {
        return this.Ll.isInLayout();
    }
    
    public boolean isRemoving() {
        return this.Ll.isRemoving();
    }
    
    public boolean isResumed() {
        return this.Ll.isResumed();
    }
    
    public boolean isVisible() {
        return this.Ll.isVisible();
    }
    
    public d iu() {
        return e.k(this.Ll.getActivity());
    }
    
    public c iv() {
        return a(this.Ll.getParentFragment());
    }
    
    public d iw() {
        return e.k(this.Ll.getResources());
    }
    
    public c ix() {
        return a(this.Ll.getTargetFragment());
    }
    
    public void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.Ll.setHasOptionsMenu(hasOptionsMenu);
    }
    
    public void setMenuVisibility(final boolean menuVisibility) {
        this.Ll.setMenuVisibility(menuVisibility);
    }
    
    public void setRetainInstance(final boolean retainInstance) {
        this.Ll.setRetainInstance(retainInstance);
    }
    
    public void setUserVisibleHint(final boolean userVisibleHint) {
        this.Ll.setUserVisibleHint(userVisibleHint);
    }
    
    public void startActivity(final Intent intent) {
        this.Ll.startActivity(intent);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.Ll.startActivityForResult(intent, n);
    }
}
