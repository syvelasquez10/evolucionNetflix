// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Bundle;

public class LegacyHelper
{
    @Deprecated
    public static void extendTokenCompleted(final Session session, final Bundle bundle) {
        session.extendTokenCompleted(bundle);
    }
}
