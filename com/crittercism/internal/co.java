// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.PrintWriter;
import java.io.PrintStream;

public final class co extends cp
{
    public boolean a;
    private String[] c;
    
    @Override
    public final void printStackTrace(final PrintStream printStream) {
        final String[] c = this.c;
        for (int length = c.length, i = 0; i < length; ++i) {
            printStream.append(c[i]);
            printStream.append("\n");
        }
    }
    
    @Override
    public final void printStackTrace(final PrintWriter printWriter) {
        final String[] c = this.c;
        for (int length = c.length, i = 0; i < length; ++i) {
            printWriter.append(c[i]);
            printWriter.append("\n");
        }
    }
}
