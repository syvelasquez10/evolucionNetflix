// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.mediaclient.service.falkor.Falkor$Creator;
import com.netflix.falkor.Ref;
import com.netflix.falkor.BranchMap;
import com.netflix.falkor.Func;

class PostPlayExperienceMap$1 implements Func<BranchMap<Ref>>
{
    final /* synthetic */ PostPlayExperienceMap this$0;
    
    PostPlayExperienceMap$1(final PostPlayExperienceMap this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public BranchMap<Ref> call() {
        return new BranchMap<Ref>(Falkor$Creator.Ref);
    }
}
