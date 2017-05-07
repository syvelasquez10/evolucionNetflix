// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.StatusLine;
import org.apache.http.ParseException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public final class ap extends af
{
    private int d;
    
    public ap(final al al) {
        super(al);
        this.d = -1;
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        final ParserCursor parserCursor = new ParserCursor(0, charArrayBuffer.length());
        try {
            final StatusLine statusLine = BasicLineParser.DEFAULT.parseStatusLine(charArrayBuffer, parserCursor);
            this.d = statusLine.getStatusCode();
            super.a.a(statusLine.getStatusCode());
            return true;
        }
        catch (ParseException ex) {
            return false;
        }
    }
    
    @Override
    public final af b() {
        return new ao(this, this.d);
    }
    
    @Override
    public final af c() {
        return as.d;
    }
    
    @Override
    protected final int d() {
        return 20;
    }
    
    @Override
    protected final int e() {
        return 64;
    }
}
