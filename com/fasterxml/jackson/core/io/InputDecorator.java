// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.io;

import java.io.Reader;
import java.io.Serializable;

public abstract class InputDecorator implements Serializable
{
    public abstract Reader decorate(final IOContext p0, final Reader p1);
}
