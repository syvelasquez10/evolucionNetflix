// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Fragment;

public final class b extends c.a
{
    private Fragment Hv;
    
    private b(final Fragment hv) {
        this.Hv = hv;
    }
    
    public static b a(final Fragment fragment) {
        if (fragment != null) {
            return new b(fragment);
        }
        return null;
    }
    
    public void b(final d d) {
        this.Hv.registerForContextMenu((View)e.d(d));
    }
    
    public void c(final d d) {
        this.Hv.unregisterForContextMenu((View)e.d(d));
    }
    
    public d fX() {
        return e.h(this.Hv.getActivity());
    }
    
    public c fY() {
        return a(this.Hv.getParentFragment());
    }
    
    public d fZ() {
        return e.h(this.Hv.getResources());
    }
    
    public c ga() {
        return a(this.Hv.getTargetFragment());
    }
    
    public Bundle getArguments() {
        return this.Hv.getArguments();
    }
    
    public int getId() {
        return this.Hv.getId();
    }
    
    public boolean getRetainInstance() {
        return this.Hv.getRetainInstance();
    }
    
    public String getTag() {
        return this.Hv.getTag();
    }
    
    public int getTargetRequestCode() {
        return this.Hv.getTargetRequestCode();
    }
    
    public boolean getUserVisibleHint() {
        return this.Hv.getUserVisibleHint();
    }
    
    public d getView() {
        return e.h(this.Hv.getView());
    }
    
    public boolean isAdded() {
        return this.Hv.isAdded();
    }
    
    public boolean isDetached() {
        return this.Hv.isDetached();
    }
    
    public boolean isHidden() {
        return this.Hv.isHidden();
    }
    
    public boolean isInLayout() {
        return this.Hv.isInLayout();
    }
    
    public boolean isRemoving() {
        return this.Hv.isRemoving();
    }
    
    public boolean isResumed() {
        return this.Hv.isResumed();
    }
    
    public boolean isVisible() {
        return this.Hv.isVisible();
    }
    
    public void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.Hv.setHasOptionsMenu(hasOptionsMenu);
    }
    
    public void setMenuVisibility(final boolean menuVisibility) {
        this.Hv.setMenuVisibility(menuVisibility);
    }
    
    public void setRetainInstance(final boolean retainInstance) {
        this.Hv.setRetainInstance(retainInstance);
    }
    
    public void setUserVisibleHint(final boolean userVisibleHint) {
        this.Hv.setUserVisibleHint(userVisibleHint);
    }
    
    public void startActivity(final Intent intent) {
        this.Hv.startActivity(intent);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.Hv.startActivityForResult(intent, n);
    }
}
