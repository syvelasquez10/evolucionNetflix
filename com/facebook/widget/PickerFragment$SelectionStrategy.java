// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Bundle;
import java.util.Collection;

abstract class PickerFragment$SelectionStrategy
{
    final /* synthetic */ PickerFragment this$0;
    
    PickerFragment$SelectionStrategy(final PickerFragment this$0) {
        this.this$0 = this$0;
    }
    
    abstract void clear();
    
    abstract Collection<String> getSelectedIds();
    
    abstract boolean isEmpty();
    
    abstract boolean isSelected(final String p0);
    
    abstract void readSelectionFromBundle(final Bundle p0, final String p1);
    
    abstract void saveSelectionToBundle(final Bundle p0, final String p1);
    
    abstract boolean shouldShowCheckBoxIfUnselected();
    
    abstract void toggleSelection(final String p0);
}
