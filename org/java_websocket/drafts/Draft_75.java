// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.drafts;

import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.NotSendableException;
import java.util.Collections;
import org.java_websocket.util.Charsetfunctions;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.framing.Framedata$Opcode;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ClientHandshake;
import java.util.LinkedList;
import java.util.Random;
import org.java_websocket.framing.Framedata;
import java.util.List;
import java.nio.ByteBuffer;

public class Draft_75 extends Draft
{
    protected ByteBuffer currentFrame;
    private boolean inframe;
    protected boolean readingState;
    protected List<Framedata> readyframes;
    private final Random reuseableRandom;
    
    public Draft_75() {
        this.readingState = false;
        this.inframe = false;
        this.readyframes = new LinkedList<Framedata>();
        this.reuseableRandom = new Random();
    }
    
    @Override
    public Draft$HandshakeState acceptHandshakeAsClient(final ClientHandshake clientHandshake, final ServerHandshake serverHandshake) {
        if (clientHandshake.getFieldValue("WebSocket-Origin").equals(serverHandshake.getFieldValue("Origin")) && this.basicAccept(serverHandshake)) {
            return Draft$HandshakeState.MATCHED;
        }
        return Draft$HandshakeState.NOT_MATCHED;
    }
    
    @Override
    public Draft$HandshakeState acceptHandshakeAsServer(final ClientHandshake clientHandshake) {
        if (clientHandshake.hasFieldValue("Origin") && this.basicAccept(clientHandshake)) {
            return Draft$HandshakeState.MATCHED;
        }
        return Draft$HandshakeState.NOT_MATCHED;
    }
    
    @Override
    public Draft copyInstance() {
        return new Draft_75();
    }
    
    @Override
    public ByteBuffer createBinaryFrame(final Framedata framedata) {
        if (framedata.getOpcode() != Framedata$Opcode.TEXT) {
            throw new RuntimeException("only text frames supported");
        }
        final ByteBuffer payloadData = framedata.getPayloadData();
        final ByteBuffer allocate = ByteBuffer.allocate(payloadData.remaining() + 2);
        allocate.put((byte)0);
        payloadData.mark();
        allocate.put(payloadData);
        payloadData.reset();
        allocate.put((byte)(-1));
        allocate.flip();
        return allocate;
    }
    
    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(Draft_75.INITIAL_FAMESIZE);
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
        return Draft$CloseHandshakeType.NONE;
    }
    
    public ByteBuffer increaseBuffer(final ByteBuffer byteBuffer) {
        byteBuffer.flip();
        final ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.capacity() * 2);
        allocate.put(byteBuffer);
        return allocate;
    }
    
    @Override
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(final ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.put("Upgrade", "WebSocket");
        clientHandshakeBuilder.put("Connection", "Upgrade");
        if (!clientHandshakeBuilder.hasFieldValue("Origin")) {
            clientHandshakeBuilder.put("Origin", "random" + this.reuseableRandom.nextInt());
        }
        return clientHandshakeBuilder;
    }
    
    @Override
    public HandshakeBuilder postProcessHandshakeResponseAsServer(final ClientHandshake clientHandshake, final ServerHandshakeBuilder serverHandshakeBuilder) {
        serverHandshakeBuilder.setHttpStatusMessage("Web Socket Protocol Handshake");
        serverHandshakeBuilder.put("Upgrade", "WebSocket");
        serverHandshakeBuilder.put("Connection", clientHandshake.getFieldValue("Connection"));
        serverHandshakeBuilder.put("WebSocket-Origin", clientHandshake.getFieldValue("Origin"));
        serverHandshakeBuilder.put("WebSocket-Location", "ws://" + clientHandshake.getFieldValue("Host") + clientHandshake.getResourceDescriptor());
        return serverHandshakeBuilder;
    }
    
    @Override
    public void reset() {
        this.readingState = false;
        this.currentFrame = null;
    }
    
    @Override
    public List<Framedata> translateFrame(final ByteBuffer byteBuffer) {
        final List<Framedata> translateRegularFrame = this.translateRegularFrame(byteBuffer);
        if (translateRegularFrame == null) {
            throw new InvalidDataException(1002);
        }
        return translateRegularFrame;
    }
    
    protected List<Framedata> translateRegularFrame(final ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining()) {
            final byte value = byteBuffer.get();
            if (value == 0) {
                if (this.readingState) {
                    return null;
                }
                this.readingState = true;
            }
            else if (value == -1) {
                if (!this.readingState) {
                    return null;
                }
                if (this.currentFrame != null) {
                    this.currentFrame.flip();
                    final FramedataImpl1 framedataImpl1 = new FramedataImpl1();
                    framedataImpl1.setPayload(this.currentFrame);
                    framedataImpl1.setFin(true);
                    Framedata$Opcode optcode;
                    if (this.inframe) {
                        optcode = Framedata$Opcode.CONTINUOUS;
                    }
                    else {
                        optcode = Framedata$Opcode.TEXT;
                    }
                    framedataImpl1.setOptcode(optcode);
                    this.readyframes.add(framedataImpl1);
                    this.currentFrame = null;
                    byteBuffer.mark();
                }
                this.readingState = false;
                this.inframe = false;
            }
            else {
                if (!this.readingState) {
                    return null;
                }
                if (this.currentFrame == null) {
                    this.currentFrame = this.createBuffer();
                }
                else if (!this.currentFrame.hasRemaining()) {
                    this.currentFrame = this.increaseBuffer(this.currentFrame);
                }
                this.currentFrame.put(value);
            }
        }
        if (this.readingState) {
            final FramedataImpl1 framedataImpl2 = new FramedataImpl1();
            this.currentFrame.flip();
            framedataImpl2.setPayload(this.currentFrame);
            framedataImpl2.setFin(false);
            Framedata$Opcode optcode2;
            if (this.inframe) {
                optcode2 = Framedata$Opcode.CONTINUOUS;
            }
            else {
                optcode2 = Framedata$Opcode.TEXT;
            }
            framedataImpl2.setOptcode(optcode2);
            this.inframe = true;
            this.readyframes.add(framedataImpl2);
        }
        final List<Framedata> readyframes = this.readyframes;
        this.readyframes = new LinkedList<Framedata>();
        this.currentFrame = null;
        return readyframes;
    }
}
