// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

public class HTTPTokener extends JSONTokener
{
    public HTTPTokener(final String s) {
        super(s);
    }
    
    public String nextToken() {
        final StringBuffer sb = new StringBuffer();
        char next;
        do {
            next = this.next();
        } while (Character.isWhitespace(next));
        char next2;
        if (next != '\"' && (next2 = next) != '\'') {
            while (next2 != '\0' && !Character.isWhitespace(next2)) {
                sb.append(next2);
                next2 = this.next();
            }
            return sb.toString();
        }
        while (true) {
            final char next3 = this.next();
            if (next3 < ' ') {
                throw this.syntaxError("Unterminated string.");
            }
            if (next3 == next) {
                return sb.toString();
            }
            sb.append(next3);
        }
    }
}
