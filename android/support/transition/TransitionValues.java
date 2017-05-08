// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.Iterator;
import java.util.HashMap;
import android.view.View;
import java.util.Map;

public class TransitionValues
{
    public final Map<String, Object> values;
    public View view;
    
    public TransitionValues() {
        this.values = new HashMap<String, Object>();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof TransitionValues && this.view == ((TransitionValues)o).view && this.values.equals(((TransitionValues)o).values);
    }
    
    @Override
    public int hashCode() {
        return this.view.hashCode() * 31 + this.values.hashCode();
    }
    
    @Override
    public String toString() {
        String s = "TransitionValues@" + Integer.toHexString(this.hashCode()) + ":\n" + "    view = " + this.view + "\n" + "    values:";
        for (final String s2 : this.values.keySet()) {
            s = s + "    " + s2 + ": " + this.values.get(s2) + "\n";
        }
        return s;
    }
}
