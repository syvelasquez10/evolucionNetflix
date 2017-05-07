// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.ArrayList;

class ObjectPathEvaluator$3 implements Watcher<PathBoundValue>
{
    final /* synthetic */ ObjectPathEvaluator this$0;
    final /* synthetic */ ArrayList val$output;
    
    ObjectPathEvaluator$3(final ObjectPathEvaluator this$0, final ArrayList val$output) {
        this.this$0 = this$0;
        this.val$output = val$output;
    }
    
    @Override
    public void onCompleted() {
    }
    
    @Override
    public void onError(final Exception ex) {
    }
    
    @Override
    public void onNext(final PathBoundValue pathBoundValue) {
        this.val$output.add(pathBoundValue);
    }
}
