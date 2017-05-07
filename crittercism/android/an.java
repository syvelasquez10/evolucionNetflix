// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.RequestLine;
import org.apache.http.ParseException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public final class an extends af
{
    public an(final al al) {
        super(al);
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        final ParserCursor parserCursor = new ParserCursor(0, charArrayBuffer.length());
        try {
            final RequestLine requestLine = BasicLineParser.DEFAULT.parseRequestLine(charArrayBuffer, parserCursor);
            super.a.a(requestLine.getMethod(), requestLine.getUri());
            return true;
        }
        catch (ParseException ex) {
            return false;
        }
    }
    
    @Override
    public final af b() {
        return new am(this);
    }
    
    @Override
    public final af c() {
        return as.d;
    }
    
    @Override
    protected final int d() {
        return 64;
    }
    
    @Override
    protected final int e() {
        return 2048;
    }
}
