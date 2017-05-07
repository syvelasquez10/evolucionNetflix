// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.content.Intent;
import android.content.ComponentName;

class IntentCompat$IntentCompatImplBase implements IntentCompat$IntentCompatImpl
{
    @Override
    public Intent makeMainActivity(final ComponentName component) {
        final Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(component);
        intent.addCategory("android.intent.category.LAUNCHER");
        return intent;
    }
}
