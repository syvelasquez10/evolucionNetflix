// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.drafts;

import java.nio.BufferUnderflowException;
import org.java_websocket.WebSocket$Role;
import org.java_websocket.framing.CloseFrameBuilder;
import java.util.LinkedList;
import org.java_websocket.exceptions.InvalidFrameException;
import java.util.List;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.framing.Framedata$Opcode;
import java.nio.ByteBuffer;
import org.java_websocket.framing.Framedata;
import java.util.Arrays;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.exceptions.InvalidHandshakeException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Random;

public class Draft_76 extends Draft_75
{
    private static final byte[] closehandshake;
    private boolean failed;
    private final Random reuseableRandom;
    
    static {
        closehandshake = new byte[] { -1, 0 };
    }
    
    public Draft_76() {
        this.failed = false;
        this.reuseableRandom = new Random();
    }
    
    public static byte[] createChallenge(final String s, final String s2, final byte[] array) {
        final byte[] part = getPart(s);
        final byte[] part2 = getPart(s2);
        final byte b = part[0];
        final byte b2 = part[1];
        final byte b3 = part[2];
        final byte b4 = part[3];
        final byte b5 = part2[0];
        final byte b6 = part2[1];
        final byte b7 = part2[2];
        final byte b8 = part2[3];
        final byte b9 = array[0];
        final byte b10 = array[1];
        final byte b11 = array[2];
        final byte b12 = array[3];
        final byte b13 = array[4];
        final byte b14 = array[5];
        final byte b15 = array[6];
        final byte b16 = array[7];
        try {
            return MessageDigest.getInstance("MD5").digest(new byte[] { b, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16 });
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static String generateKey() {
        final Random random = new Random();
        final long n = random.nextInt(12) + 1;
        String s = Long.toString((random.nextInt(Math.abs((int)(Object)new Long(4294967295L / n))) + 1) * n);
        for (int nextInt = random.nextInt(12), i = 0; i < nextInt + 1; ++i) {
            final int abs = Math.abs(random.nextInt(s.length()));
            char c2;
            final char c = c2 = (char)(random.nextInt(95) + 33);
            if (c >= '0' && (c2 = c) <= '9') {
                c2 = (char)(c - '\u000f');
            }
            s = new StringBuilder(s).insert(abs, c2).toString();
        }
        for (int n2 = 0; n2 < n; ++n2) {
            s = new StringBuilder(s).insert(Math.abs(random.nextInt(s.length() - 1) + 1), " ").toString();
        }
        return s;
    }
    
    private static byte[] getPart(final String s) {
        long long1;
        long n;
        try {
            long1 = Long.parseLong(s.replaceAll("[^0-9]", ""));
            n = s.split(" ").length - 1;
            if (n == 0L) {
                throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key2/)");
            }
        }
        catch (NumberFormatException ex) {
            throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key1/ or /key2/)");
        }
        final long longValue = new Long(long1 / n);
        return new byte[] { (byte)(longValue >> 24), (byte)(longValue << 8 >> 24), (byte)(longValue << 16 >> 24), (byte)(longValue << 24 >> 24) };
    }
    
    @Override
    public Draft$HandshakeState acceptHandshakeAsClient(final ClientHandshake clientHandshake, final ServerHandshake serverHandshake) {
        if (this.failed) {
            return Draft$HandshakeState.NOT_MATCHED;
        }
        byte[] content;
        try {
            if (!serverHandshake.getFieldValue("Sec-WebSocket-Origin").equals(clientHandshake.getFieldValue("Origin")) || !this.basicAccept(serverHandshake)) {
                return Draft$HandshakeState.NOT_MATCHED;
            }
            content = serverHandshake.getContent();
            if (content == null || content.length == 0) {
                throw new IncompleteHandshakeException();
            }
        }
        catch (InvalidHandshakeException ex) {
            throw new RuntimeException("bad handshakerequest", ex);
        }
        if (Arrays.equals(content, createChallenge(clientHandshake.getFieldValue("Sec-WebSocket-Key1"), clientHandshake.getFieldValue("Sec-WebSocket-Key2"), clientHandshake.getContent()))) {
            return Draft$HandshakeState.MATCHED;
        }
        return Draft$HandshakeState.NOT_MATCHED;
    }
    
    @Override
    public Draft$HandshakeState acceptHandshakeAsServer(final ClientHandshake clientHandshake) {
        if (clientHandshake.getFieldValue("Upgrade").equals("WebSocket") && clientHandshake.getFieldValue("Connection").contains("Upgrade") && clientHandshake.getFieldValue("Sec-WebSocket-Key1").length() > 0 && !clientHandshake.getFieldValue("Sec-WebSocket-Key2").isEmpty() && clientHandshake.hasFieldValue("Origin")) {
            return Draft$HandshakeState.MATCHED;
        }
        return Draft$HandshakeState.NOT_MATCHED;
    }
    
    @Override
    public Draft copyInstance() {
        return new Draft_76();
    }
    
    @Override
    public ByteBuffer createBinaryFrame(final Framedata framedata) {
        if (framedata.getOpcode() == Framedata$Opcode.CLOSING) {
            return ByteBuffer.wrap(Draft_76.closehandshake);
        }
        return super.createBinaryFrame(framedata);
    }
    
    @Override
    public Draft$CloseHandshakeType getCloseHandshakeType() {
        return Draft$CloseHandshakeType.ONEWAY;
    }
    
    @Override
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(final ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.put("Upgrade", "WebSocket");
        clientHandshakeBuilder.put("Connection", "Upgrade");
        clientHandshakeBuilder.put("Sec-WebSocket-Key1", generateKey());
        clientHandshakeBuilder.put("Sec-WebSocket-Key2", generateKey());
        if (!clientHandshakeBuilder.hasFieldValue("Origin")) {
            clientHandshakeBuilder.put("Origin", "random" + this.reuseableRandom.nextInt());
        }
        final byte[] content = new byte[8];
        this.reuseableRandom.nextBytes(content);
        clientHandshakeBuilder.setContent(content);
        return clientHandshakeBuilder;
    }
    
    @Override
    public HandshakeBuilder postProcessHandshakeResponseAsServer(final ClientHandshake clientHandshake, final ServerHandshakeBuilder serverHandshakeBuilder) {
        serverHandshakeBuilder.setHttpStatusMessage("WebSocket Protocol Handshake");
        serverHandshakeBuilder.put("Upgrade", "WebSocket");
        serverHandshakeBuilder.put("Connection", clientHandshake.getFieldValue("Connection"));
        serverHandshakeBuilder.put("Sec-WebSocket-Origin", clientHandshake.getFieldValue("Origin"));
        serverHandshakeBuilder.put("Sec-WebSocket-Location", "ws://" + clientHandshake.getFieldValue("Host") + clientHandshake.getResourceDescriptor());
        final String fieldValue = clientHandshake.getFieldValue("Sec-WebSocket-Key1");
        final String fieldValue2 = clientHandshake.getFieldValue("Sec-WebSocket-Key2");
        final byte[] content = clientHandshake.getContent();
        if (fieldValue == null || fieldValue2 == null || content == null || content.length != 8) {
            throw new InvalidHandshakeException("Bad keys");
        }
        serverHandshakeBuilder.setContent(createChallenge(fieldValue, fieldValue2, content));
        return serverHandshakeBuilder;
    }
    
    @Override
    public List<Framedata> translateFrame(final ByteBuffer byteBuffer) {
        byteBuffer.mark();
        List<Framedata> list;
        if ((list = super.translateRegularFrame(byteBuffer)) == null) {
            byteBuffer.reset();
            list = this.readyframes;
            this.readingState = true;
            if (this.currentFrame != null) {
                throw new InvalidFrameException();
            }
            this.currentFrame = ByteBuffer.allocate(2);
            if (byteBuffer.remaining() > this.currentFrame.remaining()) {
                throw new InvalidFrameException();
            }
            this.currentFrame.put(byteBuffer);
            if (this.currentFrame.hasRemaining()) {
                this.readyframes = new LinkedList<Framedata>();
                return list;
            }
            if (!Arrays.equals(this.currentFrame.array(), Draft_76.closehandshake)) {
                throw new InvalidFrameException();
            }
            list.add(new CloseFrameBuilder(1000));
        }
        return list;
    }
    
    @Override
    public Handshakedata translateHandshake(final ByteBuffer byteBuffer) {
        final HandshakeBuilder translateHandshakeHttp = Draft.translateHandshakeHttp(byteBuffer, this.role);
        if ((!translateHandshakeHttp.hasFieldValue("Sec-WebSocket-Key1") && this.role != WebSocket$Role.CLIENT) || translateHandshakeHttp.hasFieldValue("Sec-WebSocket-Version")) {
            return translateHandshakeHttp;
        }
        Label_0077: {
            if (this.role != WebSocket$Role.SERVER) {
                break Label_0077;
            }
            int n = 8;
            while (true) {
                final byte[] content = new byte[n];
                try {
                    byteBuffer.get(content);
                    translateHandshakeHttp.setContent(content);
                    return translateHandshakeHttp;
                    n = 16;
                }
                catch (BufferUnderflowException ex) {
                    throw new IncompleteHandshakeException(byteBuffer.capacity() + 16);
                }
            }
        }
    }
}
