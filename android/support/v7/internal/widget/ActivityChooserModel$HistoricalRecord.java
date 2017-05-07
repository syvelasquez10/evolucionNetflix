// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import java.math.BigDecimal;
import android.content.ComponentName;

public final class ActivityChooserModel$HistoricalRecord
{
    public final ComponentName activity;
    public final long time;
    public final float weight;
    
    public ActivityChooserModel$HistoricalRecord(final ComponentName activity, final long time, final float weight) {
        this.activity = activity;
        this.time = time;
        this.weight = weight;
    }
    
    public ActivityChooserModel$HistoricalRecord(final String s, final long n, final float n2) {
        this(ComponentName.unflattenFromString(s), n, n2);
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
            final ActivityChooserModel$HistoricalRecord activityChooserModel$HistoricalRecord = (ActivityChooserModel$HistoricalRecord)o;
            if (this.activity == null) {
                if (activityChooserModel$HistoricalRecord.activity != null) {
                    return false;
                }
            }
            else if (!this.activity.equals((Object)activityChooserModel$HistoricalRecord.activity)) {
                return false;
            }
            if (this.time != activityChooserModel$HistoricalRecord.time) {
                return false;
            }
            if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(activityChooserModel$HistoricalRecord.weight)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.activity == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.activity.hashCode();
        }
        return ((hashCode + 31) * 31 + (int)(this.time ^ this.time >>> 32)) * 31 + Float.floatToIntBits(this.weight);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("; activity:").append(this.activity);
        sb.append("; time:").append(this.time);
        sb.append("; weight:").append(new BigDecimal(this.weight));
        sb.append("]");
        return sb.toString();
    }
}
