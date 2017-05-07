// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import java.math.BigDecimal;
import android.content.pm.ResolveInfo;

public final class ActivityChooserModel$ActivityResolveInfo implements Comparable<ActivityChooserModel$ActivityResolveInfo>
{
    public final ResolveInfo resolveInfo;
    final /* synthetic */ ActivityChooserModel this$0;
    public float weight;
    
    public ActivityChooserModel$ActivityResolveInfo(final ActivityChooserModel this$0, final ResolveInfo resolveInfo) {
        this.this$0 = this$0;
        this.resolveInfo = resolveInfo;
    }
    
    @Override
    public int compareTo(final ActivityChooserModel$ActivityResolveInfo activityChooserModel$ActivityResolveInfo) {
        return Float.floatToIntBits(activityChooserModel$ActivityResolveInfo.weight) - Float.floatToIntBits(this.weight);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(((ActivityChooserModel$ActivityResolveInfo)o).weight)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Float.floatToIntBits(this.weight) + 31;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("resolveInfo:").append(this.resolveInfo.toString());
        sb.append("; weight:").append(new BigDecimal(this.weight));
        sb.append("]");
        return sb.toString();
    }
}
