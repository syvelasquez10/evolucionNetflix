// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import android.content.Context;

public class DevSupportManagerFactory
{
    public static DevSupportManager create(final Context context, final ReactInstanceDevCommandsHandler reactInstanceDevCommandsHandler, final String s, final boolean b, final RedBoxHandler redBoxHandler) {
        if (!b) {
            return new DisabledDevSupportManager();
        }
        try {
            return (DevSupportManager)Class.forName("com.facebook.react.devsupport" + "." + "DevSupportManagerImpl").getConstructor(Context.class, ReactInstanceDevCommandsHandler.class, String.class, Boolean.TYPE, RedBoxHandler.class).newInstance(context, reactInstanceDevCommandsHandler, s, true, redBoxHandler);
        }
        catch (Exception ex) {
            throw new RuntimeException("Requested enabled DevSupportManager, but DevSupportManagerImpl class was not found or could not be created", ex);
        }
    }
}
