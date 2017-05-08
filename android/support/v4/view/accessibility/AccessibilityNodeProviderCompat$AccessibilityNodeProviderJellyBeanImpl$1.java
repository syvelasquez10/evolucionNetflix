// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

class AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl$1 implements AccessibilityNodeProviderCompatJellyBean$AccessibilityNodeInfoBridge
{
    final /* synthetic */ AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl this$0;
    final /* synthetic */ AccessibilityNodeProviderCompat val$compat;
    
    AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl$1(final AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl this$0, final AccessibilityNodeProviderCompat val$compat) {
        this.this$0 = this$0;
        this.val$compat = val$compat;
    }
    
    @Override
    public Object createAccessibilityNodeInfo(final int n) {
        final AccessibilityNodeInfoCompat accessibilityNodeInfo = this.val$compat.createAccessibilityNodeInfo(n);
        if (accessibilityNodeInfo == null) {
            return null;
        }
        return accessibilityNodeInfo.getInfo();
    }
    
    @Override
    public List<Object> findAccessibilityNodeInfosByText(final String s, int i) {
        final List<AccessibilityNodeInfoCompat> accessibilityNodeInfosByText = this.val$compat.findAccessibilityNodeInfosByText(s, i);
        if (accessibilityNodeInfosByText == null) {
            return null;
        }
        final ArrayList<Object> list = new ArrayList<Object>();
        int size;
        for (size = accessibilityNodeInfosByText.size(), i = 0; i < size; ++i) {
            list.add(accessibilityNodeInfosByText.get(i).getInfo());
        }
        return list;
    }
    
    @Override
    public boolean performAction(final int n, final int n2, final Bundle bundle) {
        return this.val$compat.performAction(n, n2, bundle);
    }
}
