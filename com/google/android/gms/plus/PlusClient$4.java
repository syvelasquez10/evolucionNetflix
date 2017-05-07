// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.BaseImplementation$b;

class PlusClient$4 implements BaseImplementation$b<People$LoadPeopleResult>
{
    final /* synthetic */ PlusClient akU;
    final /* synthetic */ PlusClient$OnPeopleLoadedListener akV;
    
    PlusClient$4(final PlusClient akU, final PlusClient$OnPeopleLoadedListener akV) {
        this.akU = akU;
        this.akV = akV;
    }
    
    public void a(final People$LoadPeopleResult people$LoadPeopleResult) {
        this.akV.onPeopleLoaded(people$LoadPeopleResult.getStatus().gu(), people$LoadPeopleResult.getPersonBuffer(), people$LoadPeopleResult.getNextPageToken());
    }
}
