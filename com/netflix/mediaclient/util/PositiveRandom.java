// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Random;

class PositiveRandom extends Random
{
    public int nextPositive() {
        return this.next(31);
    }
}
