// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.PrintWriter;
import java.io.PrintStream;

public final class co extends cp
{
    private static final long serialVersionUID = -5983887553268578751L;
    public boolean a;
    private String[] c;
    
    public co(final String s, final String s2, final String s3, final boolean a) {
        super(s, s2, s3);
        this.c = null;
        this.c = s3.split("\\r\\n");
        this.a = a;
    }
    
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
