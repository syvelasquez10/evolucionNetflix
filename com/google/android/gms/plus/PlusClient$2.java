// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.BaseImplementation$b;

class PlusClient$2 implements BaseImplementation$b<Moments$LoadMomentsResult>
{
    final /* synthetic */ PlusClient$OnMomentsLoadedListener akT;
    final /* synthetic */ PlusClient akU;
    
    PlusClient$2(final PlusClient akU, final PlusClient$OnMomentsLoadedListener akT) {
        this.akU = akU;
        this.akT = akT;
    }
    
    public void a(final Moments$LoadMomentsResult moments$LoadMomentsResult) {
        this.akT.onMomentsLoaded(moments$LoadMomentsResult.getStatus().gu(), moments$LoadMomentsResult.getMomentBuffer(), moments$LoadMomentsResult.getNextPageToken(), moments$LoadMomentsResult.getUpdated());
    }
}
