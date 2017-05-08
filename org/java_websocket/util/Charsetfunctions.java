// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.util;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharacterCodingException;
import org.java_websocket.exceptions.InvalidDataException;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.io.UnsupportedEncodingException;
import java.nio.charset.CodingErrorAction;

public class Charsetfunctions
{
    public static CodingErrorAction codingErrorAction;
    
    static {
        Charsetfunctions.codingErrorAction = CodingErrorAction.REPORT;
    }
    
    public static byte[] asciiBytes(final String s) {
        try {
            return s.getBytes("ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static String stringAscii(final byte[] array, final int n, final int n2) {
        try {
            return new String(array, n, n2, "ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static String stringUtf8(final ByteBuffer byteBuffer) {
        final CharsetDecoder decoder = Charset.forName("UTF8").newDecoder();
        decoder.onMalformedInput(Charsetfunctions.codingErrorAction);
        decoder.onUnmappableCharacter(Charsetfunctions.codingErrorAction);
        try {
            byteBuffer.mark();
            final String string = decoder.decode(byteBuffer).toString();
            byteBuffer.reset();
            return string;
        }
        catch (CharacterCodingException ex) {
            throw new InvalidDataException(1007, ex);
        }
    }
    
    public static byte[] utf8Bytes(final String s) {
        try {
            return s.getBytes("UTF8");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
