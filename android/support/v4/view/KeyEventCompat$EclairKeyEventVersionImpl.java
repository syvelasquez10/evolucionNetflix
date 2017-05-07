// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.KeyEvent;

class KeyEventCompat$EclairKeyEventVersionImpl extends KeyEventCompat$BaseKeyEventVersionImpl
{
    @Override
    public void startTracking(final KeyEvent keyEvent) {
        KeyEventCompatEclair.startTracking(keyEvent);
    }
}
