// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.moments;

import com.netflix.mediaclient.ui.iko.kong.model.KongInteractiveMomentsModel$KongInteractiveMoment;
import java.util.Comparator;

class KongInteractiveMomentsManager$1 implements Comparator<KongInteractiveMomentsModel$KongInteractiveMoment>
{
    final /* synthetic */ KongInteractiveMomentsManager this$0;
    
    KongInteractiveMomentsManager$1(final KongInteractiveMomentsManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final KongInteractiveMomentsModel$KongInteractiveMoment kongInteractiveMomentsModel$KongInteractiveMoment, final KongInteractiveMomentsModel$KongInteractiveMoment kongInteractiveMomentsModel$KongInteractiveMoment2) {
        return kongInteractiveMomentsModel$KongInteractiveMoment.getMomentStartTimeMS() - kongInteractiveMomentsModel$KongInteractiveMoment2.getMomentStartTimeMS();
    }
}
