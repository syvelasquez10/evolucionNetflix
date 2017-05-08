// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.annotation.TargetApi;

@TargetApi(17)
class CardViewJellybeanMr1 extends CardViewGingerbread
{
    @Override
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new CardViewJellybeanMr1$1(this);
    }
}
