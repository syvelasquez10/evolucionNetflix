// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.interfaces;

import android.view.MotionEvent;

public interface DraweeController
{
    DraweeHierarchy getHierarchy();
    
    void onAttach();
    
    void onDetach();
    
    boolean onTouchEvent(final MotionEvent p0);
    
    void setHierarchy(final DraweeHierarchy p0);
}
