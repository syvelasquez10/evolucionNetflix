// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.framing;

import java.util.Arrays;
import org.java_websocket.util.Charsetfunctions;
import java.nio.ByteBuffer;

public class FramedataImpl1 implements FrameBuilder
{
    protected static byte[] emptyarray;
    protected boolean fin;
    protected Framedata$Opcode optcode;
    protected boolean transferemasked;
    private ByteBuffer unmaskedpayload;
    
    static {
        FramedataImpl1.emptyarray = new byte[0];
    }
    
    public FramedataImpl1() {
    }
    
    public FramedataImpl1(final Framedata$Opcode optcode) {
        this.optcode = optcode;
        this.unmaskedpayload = ByteBuffer.wrap(FramedataImpl1.emptyarray);
    }
    
    public FramedataImpl1(final Framedata framedata) {
        this.fin = framedata.isFin();
        this.optcode = framedata.getOpcode();
        this.unmaskedpayload = framedata.getPayloadData();
        this.transferemasked = framedata.getTransfereMasked();
    }
    
    @Override
    public Framedata$Opcode getOpcode() {
        return this.optcode;
    }
    
    @Override
    public ByteBuffer getPayloadData() {
        return this.unmaskedpayload;
    }
    
    @Override
    public boolean getTransfereMasked() {
        return this.transferemasked;
    }
    
    @Override
    public boolean isFin() {
        return this.fin;
    }
    
    @Override
    public void setFin(final boolean fin) {
        this.fin = fin;
    }
    
    @Override
    public void setOptcode(final Framedata$Opcode optcode) {
        this.optcode = optcode;
    }
    
    @Override
    public void setPayload(final ByteBuffer unmaskedpayload) {
        this.unmaskedpayload = unmaskedpayload;
    }
    
    @Override
    public void setTransferemasked(final boolean transferemasked) {
        this.transferemasked = transferemasked;
    }
    
    @Override
    public String toString() {
        return "Framedata{ optcode:" + this.getOpcode() + ", fin:" + this.isFin() + ", payloadlength:" + this.unmaskedpayload.limit() + ", payload:" + Arrays.toString(Charsetfunctions.utf8Bytes(new String(this.unmaskedpayload.array()))) + "}";
    }
}
