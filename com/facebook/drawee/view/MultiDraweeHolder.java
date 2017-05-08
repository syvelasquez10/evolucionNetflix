// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.view;

import com.facebook.common.internal.Preconditions;
import java.util.ArrayList;
import com.facebook.drawee.interfaces.DraweeHierarchy;

public class MultiDraweeHolder<DH extends DraweeHierarchy>
{
    ArrayList<DraweeHolder<DH>> mHolders;
    boolean mIsAttached;
    
    public MultiDraweeHolder() {
        this.mIsAttached = false;
        this.mHolders = new ArrayList<DraweeHolder<DH>>();
    }
    
    public void add(final int n, final DraweeHolder<DH> draweeHolder) {
        Preconditions.checkNotNull(draweeHolder);
        Preconditions.checkElementIndex(n, this.mHolders.size() + 1);
        this.mHolders.add(n, draweeHolder);
        if (this.mIsAttached) {
            draweeHolder.onAttach();
        }
    }
    
    public void add(final DraweeHolder<DH> draweeHolder) {
        this.add(this.mHolders.size(), draweeHolder);
    }
    
    public void clear() {
        if (this.mIsAttached) {
            for (int i = 0; i < this.mHolders.size(); ++i) {
                this.mHolders.get(i).onDetach();
            }
        }
        this.mHolders.clear();
    }
    
    public void onAttach() {
        if (!this.mIsAttached) {
            this.mIsAttached = true;
            for (int i = 0; i < this.mHolders.size(); ++i) {
                this.mHolders.get(i).onAttach();
            }
        }
    }
    
    public void onDetach() {
        if (this.mIsAttached) {
            this.mIsAttached = false;
            for (int i = 0; i < this.mHolders.size(); ++i) {
                this.mHolders.get(i).onDetach();
            }
        }
    }
}
