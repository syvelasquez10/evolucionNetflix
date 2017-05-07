// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.app.Activity;

interface PagerAdapterCallbacks
{
    Activity getActivity();
    
    void notifyParentOfDataSetChange();
    
    void notifyParentOfError();
}
