// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.drafts;

import org.java_websocket.framing.CloseFrameBuilder;
import org.java_websocket.exceptions.LimitExedeedException;
import java.math.BigInteger;
import java.util.LinkedList;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.NotSendableException;
import java.util.Collections;
import org.java_websocket.util.Charsetfunctions;
import org.java_websocket.framing.FramedataImpl1;
import java.util.List;
import org.java_websocket.WebSocket$Role;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.handshake.Handshakedata;
import java.security.NoSuchAlgorithmException;
import org.java_websocket.util.Base64;
import java.security.MessageDigest;
import org.java_websocket.framing.Framedata$Opcode;
import java.util.Random;
import java.nio.ByteBuffer;
import org.java_websocket.framing.Framedata;

public class Draft_10 extends Draft
{
    private Framedata fragmentedframe;
    private ByteBuffer incompleteframe;
    private final Random reuseableRandom;
    
    public Draft_10() {
        this.fragmentedframe = null;
        this.reuseableRandom = new Random();
    }
    
    private byte fromOpcode(final Framedata$Opcode framedata$Opcode) {
        if (framedata$Opcode == Framedata$Opcode.CONTINUOUS) {
            return 0;
        }
        if (framedata$Opcode == Framedata$Opcode.TEXT) {
            return 1;
        }
        if (framedata$Opcode == Framedata$Opcode.BINARY) {
            return 2;
        }
        if (framedata$Opcode == Framedata$Opcode.CLOSING) {
            return 8;
        }
        if (framedata$Opcode == Framedata$Opcode.PING) {
            return 9;
        }
        if (framedata$Opcode == Framedata$Opcode.PONG) {
            return 10;
        }
        throw new RuntimeException("Don't know how to handle " + framedata$Opcode.toString());
    }
    
    private String generateFinalKey(String s) {
        s = s.trim();
        s += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        try {
            return Base64.encodeBytes(MessageDigest.getInstance("SHA1").digest(s.getBytes()));
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int readVersion(final Handshakedata handshakedata) {
        int intValue = -1;
        final String fieldValue = handshakedata.getFieldValue("Sec-WebSocket-Version");
        if (fieldValue.length() <= 0) {
            return intValue;
        }
        try {
            intValue = new Integer(fieldValue.trim());
            return intValue;
        }
        catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    private byte[] toByteArray(final long n, final int n2) {
        final byte[] array = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = (byte)(n >>> n2 * 8 - 8 - i * 8);
        }
        return array;
    }
    
    private Framedata$Opcode toOpcode(final byte b) {
        switch (b) {
            default: {
                throw new InvalidFrameException("unknow optcode " + b);
            }
            case 0: {
                return Framedata$Opcode.CONTINUOUS;
            }
            case 1: {
                return Framedata$Opcode.TEXT;
            }
            case 2: {
                return Framedata$Opcode.BINARY;
            }
            case 8: {
                return Framedata$Opcode.CLOSING;
            }
            case 9: {
                return Framedata$Opcode.PING;
            }
            case 10: {
                return Framedata$Opcode.PONG;
            }
        }
    }
    
    @Override
    public Draft$HandshakeState acceptHandshakeAsClient(final ClientHandshake clientHandshake, final ServerHandshake serverHandshake) {
        if (!clientHandshake.hasFieldValue("Sec-WebSocket-Key") || !serverHandshake.hasFieldValue("Sec-WebSocket-Accept")) {
            return Draft$HandshakeState.NOT_MATCHED;
        }
        if (this.generateFinalKey(clientHandshake.getFieldValue("Sec-WebSocket-Key")).equals(serverHandshake.getFieldValue("Sec-WebSocket-Accept"))) {
            return Draft$HandshakeState.MATCHED;
        }
        return Draft$HandshakeState.NOT_MATCHED;
    }
    
    @Override
    public Draft$HandshakeState acceptHandshakeAsServer(final ClientHandshake clientHandshake) {
        final int version = readVersion(clientHandshake);
        if (version != 7 && version != 8) {
            return Draft$HandshakeState.NOT_MATCHED;
        }
        if (this.basicAccept(clientHandshake)) {
            return Draft$HandshakeState.MATCHED;
        }
        return Draft$HandshakeState.NOT_MATCHED;
    }
    
    @Override
    public Draft copyInstance() {
        return new Draft_10();
    }
    
    @Override
    public ByteBuffer createBinaryFrame(final Framedata framedata) {
        byte b = -128;
        final int n = 0;
        final ByteBuffer payloadData = framedata.getPayloadData();
        boolean b2;
        if (this.role == WebSocket$Role.CLIENT) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        int n2;
        if (payloadData.remaining() <= 125) {
            n2 = 1;
        }
        else if (payloadData.remaining() <= 65535) {
            n2 = 2;
        }
        else {
            n2 = 8;
        }
        int n3;
        if (n2 > 1) {
            n3 = n2 + 1;
        }
        else {
            n3 = n2;
        }
        int n4;
        if (b2) {
            n4 = 4;
        }
        else {
            n4 = 0;
        }
        final ByteBuffer allocate = ByteBuffer.allocate(n4 + (n3 + 1) + payloadData.remaining());
        final byte fromOpcode = this.fromOpcode(framedata.getOpcode());
        int n5;
        if (framedata.isFin()) {
            n5 = -128;
        }
        else {
            n5 = 0;
        }
        allocate.put((byte)((byte)n5 | fromOpcode));
        final byte[] byteArray = this.toByteArray(payloadData.remaining(), n2);
        assert byteArray.length == n2;
        if (n2 == 1) {
            final byte b3 = byteArray[0];
            if (!b2) {
                b = 0;
            }
            allocate.put((byte)(b3 | b));
        }
        else if (n2 == 2) {
            if (!b2) {
                b = 0;
            }
            allocate.put((byte)(b | 0x7E));
            allocate.put(byteArray);
        }
        else {
            if (n2 != 8) {
                throw new RuntimeException("Size representation not supported/specified");
            }
            if (!b2) {
                b = 0;
            }
            allocate.put((byte)(b | 0x7F));
            allocate.put(byteArray);
        }
        if (b2) {
            final ByteBuffer allocate2 = ByteBuffer.allocate(4);
            allocate2.putInt(this.reuseableRandom.nextInt());
            allocate.put(allocate2.array());
            for (int i = n; i < payloadData.limit(); ++i) {
                allocate.put((byte)(payloadData.get() ^ allocate2.get(i % 4)));
            }
        }
        else {
            allocate.put(payloadData);
        }
        assert allocate.remaining() == 0 : allocate.remaining();
        allocate.flip();
        return allocate;
    }
    
    @Override
    public List<Framedata> createFrames(final String s, final boolean transferemasked) {
        final FramedataImpl1 framedataImpl1 = new FramedataImpl1();
        try {
            framedataImpl1.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes(s)));
            framedataImpl1.setFin(true);
            framedataImpl1.setOptcode(Framedata$Opcode.TEXT);
            framedataImpl1.setTransferemasked(transferemasked);
            return (List<Framedata>)Collections.singletonList(framedataImpl1);
        }
        catch (InvalidDataException ex) {
            throw new NotSendableException(ex);
        }
    }
    
    @Override
    public Draft$CloseHandshakeType getCloseHandshakeType() {
        return Draft$CloseHandshakeType.TWOWAY;
    }
    
    @Override
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(final ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.put("Upgrade", "websocket");
        clientHandshakeBuilder.put("Connection", "Upgrade");
        clientHandshakeBuilder.put("Sec-WebSocket-Version", "8");
        final byte[] array = new byte[16];
        this.reuseableRandom.nextBytes(array);
        clientHandshakeBuilder.put("Sec-WebSocket-Key", Base64.encodeBytes(array));
        return clientHandshakeBuilder;
    }
    
    @Override
    public HandshakeBuilder postProcessHandshakeResponseAsServer(final ClientHandshake clientHandshake, final ServerHandshakeBuilder serverHandshakeBuilder) {
        serverHandshakeBuilder.put("Upgrade", "websocket");
        serverHandshakeBuilder.put("Connection", clientHandshake.getFieldValue("Connection"));
        serverHandshakeBuilder.setHttpStatusMessage("Switching Protocols");
        final String fieldValue = clientHandshake.getFieldValue("Sec-WebSocket-Key");
        if (fieldValue == null) {
            throw new InvalidHandshakeException("missing Sec-WebSocket-Key");
        }
        serverHandshakeBuilder.put("Sec-WebSocket-Accept", this.generateFinalKey(fieldValue));
        return serverHandshakeBuilder;
    }
    
    @Override
    public void reset() {
        this.incompleteframe = null;
    }
    
    @Override
    public List<Framedata> translateFrame(final ByteBuffer byteBuffer) {
        final LinkedList<Framedata> list = new LinkedList<Framedata>();
        Label_0130: {
            if (this.incompleteframe == null) {
                break Label_0130;
            }
            try {
                byteBuffer.mark();
                final int remaining = byteBuffer.remaining();
                final int remaining2 = this.incompleteframe.remaining();
                if (remaining2 > remaining) {
                    this.incompleteframe.put(byteBuffer.array(), byteBuffer.position(), remaining);
                    byteBuffer.position(remaining + byteBuffer.position());
                    return Collections.emptyList();
                }
                this.incompleteframe.put(byteBuffer.array(), byteBuffer.position(), remaining2);
                byteBuffer.position(byteBuffer.position() + remaining2);
                list.add(this.translateSingleFrame((ByteBuffer)this.incompleteframe.duplicate().position(0)));
                this.incompleteframe = null;
                while (byteBuffer.hasRemaining()) {
                    byteBuffer.mark();
                    try {
                        list.add(this.translateSingleFrame(byteBuffer));
                        continue;
                    }
                    catch (Draft_10$IncompleteException ex) {
                        byteBuffer.reset();
                        (this.incompleteframe = ByteBuffer.allocate(this.checkAlloc(ex.getPreferedSize()))).put(byteBuffer);
                    }
                    break;
                }
                return list;
            }
            catch (Draft_10$IncompleteException ex2) {
                this.incompleteframe.limit();
                final ByteBuffer allocate = ByteBuffer.allocate(this.checkAlloc(ex2.getPreferedSize()));
                assert allocate.limit() > this.incompleteframe.limit();
                this.incompleteframe.rewind();
                allocate.put(this.incompleteframe);
                this.incompleteframe = allocate;
                return this.translateFrame(byteBuffer);
            }
        }
    }
    
    public Framedata translateSingleFrame(final ByteBuffer byteBuffer) {
        int n = 2;
        final int n2 = 0;
        final int remaining = byteBuffer.remaining();
        if (remaining < 2) {
            throw new Draft_10$IncompleteException(this, 2);
        }
        final byte value = byteBuffer.get();
        final boolean fin = value >> 8 != 0;
        final byte b = (byte)((value & 0x7F) >> 4);
        if (b != 0) {
            throw new InvalidFrameException("bad rsv " + b);
        }
        final byte value2 = byteBuffer.get();
        final boolean b2 = (value2 & 0xFFFFFF80) != 0x0;
        int intValue = (byte)(value2 & 0x7F);
        final Framedata$Opcode opcode = this.toOpcode((byte)(value & 0xF));
        if (!fin && (opcode == Framedata$Opcode.PING || opcode == Framedata$Opcode.PONG || opcode == Framedata$Opcode.CLOSING)) {
            throw new InvalidFrameException("control frames may no be fragmented");
        }
        if (intValue < 0 || intValue > 125) {
            if (opcode == Framedata$Opcode.PING || opcode == Framedata$Opcode.PONG || opcode == Framedata$Opcode.CLOSING) {
                throw new InvalidFrameException("more than 125 octets");
            }
            if (intValue == 126) {
                if (remaining < 4) {
                    throw new Draft_10$IncompleteException(this, 4);
                }
                intValue = new BigInteger(new byte[] { 0, byteBuffer.get(), byteBuffer.get() }).intValue();
                n = 4;
            }
            else {
                if (remaining < 10) {
                    throw new Draft_10$IncompleteException(this, 10);
                }
                final byte[] array = new byte[8];
                for (int i = 0; i < 8; ++i) {
                    array[i] = byteBuffer.get();
                }
                final long longValue = new BigInteger(array).longValue();
                if (longValue > 2147483647L) {
                    throw new LimitExedeedException("Payloadsize is to big...");
                }
                intValue = (int)longValue;
                n = 10;
            }
        }
        int n3;
        if (b2) {
            n3 = 4;
        }
        else {
            n3 = 0;
        }
        final int n4 = n3 + n + intValue;
        if (remaining < n4) {
            throw new Draft_10$IncompleteException(this, n4);
        }
        final ByteBuffer allocate = ByteBuffer.allocate(this.checkAlloc(intValue));
        if (b2) {
            final byte[] array2 = new byte[4];
            byteBuffer.get(array2);
            for (int j = n2; j < intValue; ++j) {
                allocate.put((byte)(byteBuffer.get() ^ array2[j % 4]));
            }
        }
        else {
            allocate.put(byteBuffer.array(), byteBuffer.position(), allocate.limit());
            byteBuffer.position(byteBuffer.position() + allocate.limit());
        }
        FramedataImpl1 framedataImpl1;
        if (opcode == Framedata$Opcode.CLOSING) {
            framedataImpl1 = new CloseFrameBuilder();
        }
        else {
            framedataImpl1 = new FramedataImpl1();
            framedataImpl1.setFin(fin);
            framedataImpl1.setOptcode(opcode);
        }
        allocate.flip();
        framedataImpl1.setPayload(allocate);
        return framedataImpl1;
    }
}
