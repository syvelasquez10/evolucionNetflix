// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.android.activity.NetflixActivity;

public interface RowAdapterCallbacks
{
    NetflixActivity getActivity();
    
    void notifyParentOfDataSetChange();
    
    void notifyParentOfError();
}
