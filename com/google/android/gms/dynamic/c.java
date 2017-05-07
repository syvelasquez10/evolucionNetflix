// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;

public interface c extends IInterface
{
    void d(final d p0);
    
    void e(final d p0);
    
    Bundle getArguments();
    
    int getId();
    
    boolean getRetainInstance();
    
    String getTag();
    
    int getTargetRequestCode();
    
    boolean getUserVisibleHint();
    
    d getView();
    
    boolean isAdded();
    
    boolean isDetached();
    
    boolean isHidden();
    
    boolean isInLayout();
    
    boolean isRemoving();
    
    boolean isResumed();
    
    boolean isVisible();
    
    d iu();
    
    c iv();
    
    d iw();
    
    c ix();
    
    void setHasOptionsMenu(final boolean p0);
    
    void setMenuVisibility(final boolean p0);
    
    void setRetainInstance(final boolean p0);
    
    void setUserVisibleHint(final boolean p0);
    
    void startActivity(final Intent p0);
    
    void startActivityForResult(final Intent p0, final int p1);
}
