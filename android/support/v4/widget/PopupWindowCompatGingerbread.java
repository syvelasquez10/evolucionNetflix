// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.PopupWindow;
import java.lang.reflect.Method;

class PopupWindowCompatGingerbread
{
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;
    
    static void setWindowLayoutType(final PopupWindow ex, final int n) {
        Label_0037: {
            if (PopupWindowCompatGingerbread.sSetWindowLayoutTypeMethodAttempted) {
                break Label_0037;
            }
            while (true) {
                try {
                    (PopupWindowCompatGingerbread.sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE)).setAccessible(true);
                    PopupWindowCompatGingerbread.sSetWindowLayoutTypeMethodAttempted = true;
                    if (PopupWindowCompatGingerbread.sSetWindowLayoutTypeMethod == null) {
                        return;
                    }
                    try {
                        PopupWindowCompatGingerbread.sSetWindowLayoutTypeMethod.invoke(ex, n);
                    }
                    catch (Exception ex) {}
                }
                catch (Exception ex2) {
                    continue;
                }
                break;
            }
        }
    }
}
