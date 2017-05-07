// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

class AccessibilityEventCompat$AccessibilityEventIcsImpl extends AccessibilityEventCompat$AccessibilityEventStubImpl
{
    @Override
    public void appendRecord(final AccessibilityEvent accessibilityEvent, final Object o) {
        AccessibilityEventCompatIcs.appendRecord(accessibilityEvent, o);
    }
    
    @Override
    public Object getRecord(final AccessibilityEvent accessibilityEvent, final int n) {
        return AccessibilityEventCompatIcs.getRecord(accessibilityEvent, n);
    }
    
    @Override
    public int getRecordCount(final AccessibilityEvent accessibilityEvent) {
        return AccessibilityEventCompatIcs.getRecordCount(accessibilityEvent);
    }
}
