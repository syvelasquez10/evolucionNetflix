// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;
import java.util.ArrayList;

class AbstractPathEvaluator$5$1 implements Func1<PathBoundValue, Iterable<PathBoundValue>>
{
    final /* synthetic */ AbstractPathEvaluator$5 this$0;
    
    AbstractPathEvaluator$5$1(final AbstractPathEvaluator$5 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Iterable<PathBoundValue> call(final PathBoundValue pathBoundValue) {
        final Option value = pathBoundValue.getValue();
        if (value.getHasValue() && value.getValue() instanceof PQL) {
            this.this$0.pathMap.add((PQL)pathBoundValue.getValue().getValue(), pathBoundValue.getPath());
        }
        final ArrayList<PathBoundValue> list = new ArrayList<PathBoundValue>();
        final Iterator<PQL> iterator = this.this$0.pathMap.translate(pathBoundValue.getPath()).iterator();
        while (iterator.hasNext()) {
            list.add(new PathBoundValue(iterator.next(), value));
        }
        return (Iterable<PathBoundValue>)list;
    }
}
