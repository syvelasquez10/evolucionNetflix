// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.drafts;

import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import java.util.Iterator;
import java.util.Collections;
import java.util.List;
import org.java_websocket.framing.Framedata;
import org.java_websocket.exceptions.InvalidDataException;
import java.util.Locale;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.HandshakedataImpl1;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.handshake.HandshakeBuilder;
import java.nio.ByteBuffer;
import org.java_websocket.util.Charsetfunctions;
import org.java_websocket.WebSocket$Role;

public abstract class Draft
{
    public static final byte[] FLASH_POLICY_REQUEST;
    public static int INITIAL_FAMESIZE;
    public static int MAX_FAME_SIZE;
    protected WebSocket$Role role;
    
    static {
        Draft.MAX_FAME_SIZE = 1000;
        Draft.INITIAL_FAMESIZE = 64;
        FLASH_POLICY_REQUEST = Charsetfunctions.utf8Bytes("<policy-file-request/>\u0000");
    }
    
    public Draft() {
        this.role = null;
    }
    
    public static ByteBuffer readLine(final ByteBuffer byteBuffer) {
        final ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        int n = 48;
        while (byteBuffer.hasRemaining()) {
            final byte value = byteBuffer.get();
            allocate.put(value);
            if (n == 13 && value == 10) {
                allocate.limit(allocate.position() - 2);
                allocate.position(0);
                return allocate;
            }
            n = value;
        }
        byteBuffer.position(byteBuffer.position() - allocate.position());
        return null;
    }
    
    public static String readStringLine(ByteBuffer line) {
        line = readLine(line);
        if (line == null) {
            return null;
        }
        return Charsetfunctions.stringAscii(line.array(), 0, line.limit());
    }
    
    public static HandshakeBuilder translateHandshakeHttp(final ByteBuffer byteBuffer, final WebSocket$Role webSocket$Role) {
        final String stringLine = readStringLine(byteBuffer);
        if (stringLine == null) {
            throw new IncompleteHandshakeException(byteBuffer.capacity() + 128);
        }
        final String[] split = stringLine.split(" ", 3);
        if (split.length != 3) {
            throw new InvalidHandshakeException();
        }
        HandshakedataImpl1 handshakedataImpl1;
        if (webSocket$Role == WebSocket$Role.CLIENT) {
            handshakedataImpl1 = new HandshakeImpl1Server();
            final HandshakeImpl1Server handshakeImpl1Server = (HandshakeImpl1Server)handshakedataImpl1;
            handshakeImpl1Server.setHttpStatus(Short.parseShort(split[1]));
            handshakeImpl1Server.setHttpStatusMessage(split[2]);
        }
        else {
            handshakedataImpl1 = new HandshakeImpl1Client();
            ((ClientHandshakeBuilder)handshakedataImpl1).setResourceDescriptor(split[1]);
        }
        String s;
        for (s = readStringLine(byteBuffer); s != null && s.length() > 0; s = readStringLine(byteBuffer)) {
            final String[] split2 = s.split(":", 2);
            if (split2.length != 2) {
                throw new InvalidHandshakeException("not an http header");
            }
            handshakedataImpl1.put(split2[0], split2[1].replaceFirst("^ +", ""));
        }
        if (s == null) {
            throw new IncompleteHandshakeException();
        }
        return handshakedataImpl1;
    }
    
    public abstract Draft$HandshakeState acceptHandshakeAsClient(final ClientHandshake p0, final ServerHandshake p1);
    
    public abstract Draft$HandshakeState acceptHandshakeAsServer(final ClientHandshake p0);
    
    protected boolean basicAccept(final Handshakedata handshakedata) {
        return handshakedata.getFieldValue("Upgrade").equalsIgnoreCase("websocket") && handshakedata.getFieldValue("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
    }
    
    public int checkAlloc(final int n) {
        if (n < 0) {
            throw new InvalidDataException(1002, "Negative count");
        }
        return n;
    }
    
    public abstract Draft copyInstance();
    
    public abstract ByteBuffer createBinaryFrame(final Framedata p0);
    
    public abstract List<Framedata> createFrames(final String p0, final boolean p1);
    
    public List<ByteBuffer> createHandshake(final Handshakedata handshakedata, final WebSocket$Role webSocket$Role) {
        return this.createHandshake(handshakedata, webSocket$Role, true);
    }
    
    public List<ByteBuffer> createHandshake(final Handshakedata handshakedata, final WebSocket$Role webSocket$Role, final boolean b) {
        final StringBuilder sb = new StringBuilder(100);
        if (handshakedata instanceof ClientHandshake) {
            sb.append("GET ");
            sb.append(((ClientHandshake)handshakedata).getResourceDescriptor());
            sb.append(" HTTP/1.1");
        }
        else {
            if (!(handshakedata instanceof ServerHandshake)) {
                throw new RuntimeException("unknow role");
            }
            sb.append("HTTP/1.1 101 " + ((ServerHandshake)handshakedata).getHttpStatusMessage());
        }
        sb.append("\r\n");
        final Iterator<String> iterateHttpFields = handshakedata.iterateHttpFields();
        while (iterateHttpFields.hasNext()) {
            final String s = iterateHttpFields.next();
            final String fieldValue = handshakedata.getFieldValue(s);
            sb.append(s);
            sb.append(": ");
            sb.append(fieldValue);
            sb.append("\r\n");
        }
        sb.append("\r\n");
        final byte[] asciiBytes = Charsetfunctions.asciiBytes(sb.toString());
        byte[] content;
        if (b) {
            content = handshakedata.getContent();
        }
        else {
            content = null;
        }
        int length;
        if (content == null) {
            length = 0;
        }
        else {
            length = content.length;
        }
        final ByteBuffer allocate = ByteBuffer.allocate(length + asciiBytes.length);
        allocate.put(asciiBytes);
        if (content != null) {
            allocate.put(content);
        }
        allocate.flip();
        return Collections.singletonList(allocate);
    }
    
    public abstract Draft$CloseHandshakeType getCloseHandshakeType();
    
    public abstract ClientHandshakeBuilder postProcessHandshakeRequestAsClient(final ClientHandshakeBuilder p0);
    
    public abstract HandshakeBuilder postProcessHandshakeResponseAsServer(final ClientHandshake p0, final ServerHandshakeBuilder p1);
    
    public abstract void reset();
    
    public void setParseMode(final WebSocket$Role role) {
        this.role = role;
    }
    
    public abstract List<Framedata> translateFrame(final ByteBuffer p0);
    
    public Handshakedata translateHandshake(final ByteBuffer byteBuffer) {
        return translateHandshakeHttp(byteBuffer, this.role);
    }
}
