// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.framing;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.util.Charsetfunctions;
import org.java_websocket.exceptions.InvalidFrameException;
import java.nio.ByteBuffer;

public class CloseFrameBuilder extends FramedataImpl1 implements CloseFrame
{
    static final ByteBuffer emptybytebuffer;
    private int code;
    private String reason;
    
    static {
        emptybytebuffer = ByteBuffer.allocate(0);
    }
    
    public CloseFrameBuilder() {
        super(Framedata$Opcode.CLOSING);
        this.setFin(true);
    }
    
    public CloseFrameBuilder(final int n) {
        super(Framedata$Opcode.CLOSING);
        this.setFin(true);
        this.setCodeAndMessage(n, "");
    }
    
    public CloseFrameBuilder(final int n, final String s) {
        super(Framedata$Opcode.CLOSING);
        this.setFin(true);
        this.setCodeAndMessage(n, s);
    }
    
    private void initCloseCode() {
        this.code = 1005;
        final ByteBuffer payloadData = super.getPayloadData();
        payloadData.mark();
        if (payloadData.remaining() >= 2) {
            final ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.position(2);
            allocate.putShort(payloadData.getShort());
            allocate.position(0);
            this.code = allocate.getInt();
            if (this.code == 1006 || this.code == 1015 || this.code == 1005 || this.code > 4999 || this.code < 1000 || this.code == 1004) {
                throw new InvalidFrameException("closecode must not be sent over the wire: " + this.code);
            }
        }
        payloadData.reset();
    }
    
    private void initMessage() {
        if (this.code == 1005) {
            this.reason = Charsetfunctions.stringUtf8(super.getPayloadData());
            return;
        }
        final ByteBuffer payloadData = super.getPayloadData();
        final int position = payloadData.position();
        try {
            payloadData.position(payloadData.position() + 2);
            this.reason = Charsetfunctions.stringUtf8(payloadData);
        }
        catch (IllegalArgumentException ex) {
            throw new InvalidFrameException(ex);
        }
        finally {
            payloadData.position(position);
        }
    }
    
    private void setCodeAndMessage(final int n, String s) {
        if (s == null) {
            s = "";
        }
        int n2 = n;
        if (n == 1015) {
            s = "";
            n2 = 1005;
        }
        if (n2 == 1005) {
            if (s.length() > 0) {
                throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
            }
        }
        else {
            final byte[] utf8Bytes = Charsetfunctions.utf8Bytes(s);
            final ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.putInt(n2);
            allocate.position(2);
            final ByteBuffer allocate2 = ByteBuffer.allocate(utf8Bytes.length + 2);
            allocate2.put(allocate);
            allocate2.put(utf8Bytes);
            allocate2.rewind();
            this.setPayload(allocate2);
        }
    }
    
    @Override
    public int getCloseCode() {
        return this.code;
    }
    
    @Override
    public String getMessage() {
        return this.reason;
    }
    
    @Override
    public ByteBuffer getPayloadData() {
        if (this.code == 1005) {
            return CloseFrameBuilder.emptybytebuffer;
        }
        return super.getPayloadData();
    }
    
    @Override
    public void setPayload(final ByteBuffer payload) {
        super.setPayload(payload);
        this.initCloseCode();
        this.initMessage();
    }
    
    @Override
    public String toString() {
        return super.toString() + "code: " + this.code;
    }
}
