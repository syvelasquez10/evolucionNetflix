// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.Fragment;

public final class h extends c.a
{
    private Fragment Hz;
    
    private h(final Fragment hz) {
        this.Hz = hz;
    }
    
    public static h a(final Fragment fragment) {
        if (fragment != null) {
            return new h(fragment);
        }
        return null;
    }
    
    public void b(final d d) {
        this.Hz.registerForContextMenu(e.d(d));
    }
    
    public void c(final d d) {
        this.Hz.unregisterForContextMenu(e.d(d));
    }
    
    public d fX() {
        return e.h(this.Hz.getActivity());
    }
    
    public c fY() {
        return a(this.Hz.getParentFragment());
    }
    
    public d fZ() {
        return e.h(this.Hz.getResources());
    }
    
    public c ga() {
        return a(this.Hz.getTargetFragment());
    }
    
    public Bundle getArguments() {
        return this.Hz.getArguments();
    }
    
    public int getId() {
        return this.Hz.getId();
    }
    
    public boolean getRetainInstance() {
        return this.Hz.getRetainInstance();
    }
    
    public String getTag() {
        return this.Hz.getTag();
    }
    
    public int getTargetRequestCode() {
        return this.Hz.getTargetRequestCode();
    }
    
    public boolean getUserVisibleHint() {
        return this.Hz.getUserVisibleHint();
    }
    
    public d getView() {
        return e.h(this.Hz.getView());
    }
    
    public boolean isAdded() {
        return this.Hz.isAdded();
    }
    
    public boolean isDetached() {
        return this.Hz.isDetached();
    }
    
    public boolean isHidden() {
        return this.Hz.isHidden();
    }
    
    public boolean isInLayout() {
        return this.Hz.isInLayout();
    }
    
    public boolean isRemoving() {
        return this.Hz.isRemoving();
    }
    
    public boolean isResumed() {
        return this.Hz.isResumed();
    }
    
    public boolean isVisible() {
        return this.Hz.isVisible();
    }
    
    public void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.Hz.setHasOptionsMenu(hasOptionsMenu);
    }
    
    public void setMenuVisibility(final boolean menuVisibility) {
        this.Hz.setMenuVisibility(menuVisibility);
    }
    
    public void setRetainInstance(final boolean retainInstance) {
        this.Hz.setRetainInstance(retainInstance);
    }
    
    public void setUserVisibleHint(final boolean userVisibleHint) {
        this.Hz.setUserVisibleHint(userVisibleHint);
    }
    
    public void startActivity(final Intent intent) {
        this.Hz.startActivity(intent);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.Hz.startActivityForResult(intent, n);
    }
}
