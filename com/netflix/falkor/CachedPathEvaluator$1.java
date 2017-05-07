// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

final class CachedPathEvaluator$1 implements Func1<PathBoundValue, Boolean>
{
    @Override
    public Boolean call(final PathBoundValue pathBoundValue) {
        final Option value = pathBoundValue.getValue();
        return !value.getHasValue() || !(value.getValue() instanceof CachedPathEvaluator$MissingMember);
    }
}
