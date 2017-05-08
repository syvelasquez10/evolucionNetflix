// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.MslInternalException;
import java.util.Collections;
import com.netflix.msl.MslException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslEncodingException;
import java.io.IOException;
import java.util.ArrayList;
import com.netflix.msl.util.MslContext;
import java.util.List;
import java.io.ByteArrayOutputStream;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.MslConstants$CompressionAlgorithm;
import java.nio.charset.Charset;
import java.io.OutputStream;

public class MessageOutputStream extends OutputStream
{
    private boolean caching;
    private final MessageCapabilities capabilities;
    private final Charset charset;
    private boolean closeDestination;
    private boolean closed;
    private MslConstants$CompressionAlgorithm compressionAlgo;
    private final ICryptoContext cryptoContext;
    private ByteArrayOutputStream currentPayload;
    private final OutputStream destination;
    private final Header header;
    private long payloadSequenceNumber;
    private final List<PayloadChunk> payloads;
    
    public MessageOutputStream(final MslContext mslContext, final OutputStream destination, final Charset charset, final ErrorHeader header) {
        this.payloadSequenceNumber = 1L;
        this.currentPayload = new ByteArrayOutputStream();
        this.closed = false;
        this.closeDestination = false;
        this.caching = true;
        this.payloads = new ArrayList<PayloadChunk>();
        this.destination = destination;
        this.charset = charset;
        this.capabilities = mslContext.getMessageCapabilities();
        this.header = header;
        this.compressionAlgo = null;
        this.cryptoContext = null;
        this.destination.write(this.header.toJSONString().getBytes(charset));
        this.destination.flush();
    }
    
    public MessageOutputStream(final MslContext mslContext, final OutputStream destination, final Charset charset, final MessageHeader header, final ICryptoContext cryptoContext) {
        this.payloadSequenceNumber = 1L;
        this.currentPayload = new ByteArrayOutputStream();
        this.closed = false;
        this.closeDestination = false;
        this.caching = true;
        this.payloads = new ArrayList<PayloadChunk>();
        final MessageCapabilities intersection = MessageCapabilities.intersection(mslContext.getMessageCapabilities(), header.getMessageCapabilities());
        MslConstants$CompressionAlgorithm preferredAlgorithm;
        if (intersection != null) {
            preferredAlgorithm = MslConstants$CompressionAlgorithm.getPreferredAlgorithm(intersection.getCompressionAlgorithms());
        }
        else {
            preferredAlgorithm = null;
        }
        this.destination = destination;
        this.charset = charset;
        this.capabilities = intersection;
        this.header = header;
        this.compressionAlgo = preferredAlgorithm;
        this.cryptoContext = cryptoContext;
        this.destination.write(this.header.toJSONString().getBytes(charset));
        this.destination.flush();
    }
    
    @Override
    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.flush();
            this.currentPayload = null;
            if (this.closeDestination) {
                this.destination.close();
            }
        }
    }
    
    public void closeDestination(final boolean closeDestination) {
        this.closeDestination = closeDestination;
    }
    
    @Override
    protected void finalize() {
        this.close();
        super.finalize();
    }
    
    @Override
    public void flush() {
        if (this.currentPayload != null && (this.closed || this.currentPayload.size() != 0)) {
            final MessageHeader messageHeader = this.getMessageHeader();
            if (messageHeader != null && !messageHeader.isHandshake()) {
                try {
                    if (this.currentPayload == null) {
                        goto Label_0188;
                    }
                    final PayloadChunk payloadChunk = new PayloadChunk(this.payloadSequenceNumber, messageHeader.getMessageId(), this.closed, this.compressionAlgo, this.currentPayload.toByteArray(), this.cryptoContext);
                    if (this.caching) {
                        this.payloads.add(payloadChunk);
                    }
                    this.destination.write(payloadChunk.toJSONString().getBytes(this.charset));
                    this.destination.flush();
                    ++this.payloadSequenceNumber;
                    if (this.closed) {
                        this.currentPayload = null;
                        return;
                    }
                    goto Label_0195;
                }
                catch (MslEncodingException ex) {
                    throw new IOException("Error encoding payload chunk [sequence number " + this.payloadSequenceNumber + "].", ex);
                }
                catch (MslCryptoException ex2) {
                    throw new IOException("Error encrypting payload chunk [sequence number " + this.payloadSequenceNumber + "].", ex2);
                }
                catch (MslException ex3) {
                    throw new IOException("Error compressing payload chunk [sequence number " + this.payloadSequenceNumber + "].", ex3);
                }
            }
        }
    }
    
    public ErrorHeader getErrorHeader() {
        if (this.header instanceof ErrorHeader) {
            return (ErrorHeader)this.header;
        }
        return null;
    }
    
    public MessageHeader getMessageHeader() {
        if (this.header instanceof MessageHeader) {
            return (MessageHeader)this.header;
        }
        return null;
    }
    
    List<PayloadChunk> getPayloads() {
        return Collections.unmodifiableList((List<? extends PayloadChunk>)this.payloads);
    }
    
    public boolean setCompressionAlgorithm(final MslConstants$CompressionAlgorithm compressionAlgo) {
        if (this.getMessageHeader() == null) {
            throw new MslInternalException("Cannot write payload data for an error message.");
        }
        if (compressionAlgo == null || (this.capabilities != null && this.capabilities.getCompressionAlgorithms().contains(compressionAlgo))) {
            if (this.compressionAlgo != compressionAlgo) {
                this.flush();
            }
            this.compressionAlgo = compressionAlgo;
            return true;
        }
        return false;
    }
    
    void stopCaching() {
        this.caching = false;
        this.payloads.clear();
    }
    
    @Override
    public void write(final int n) {
        this.write(new byte[] { (byte)(n & 0xFF) });
    }
    
    @Override
    public void write(final byte[] array) {
        this.write(array, 0, array.length);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        if (this.closed) {
            throw new IOException("Message output stream already closed.");
        }
        final MessageHeader messageHeader = this.getMessageHeader();
        if (messageHeader == null) {
            throw new MslInternalException("Cannot write payload data for an error message.");
        }
        if (messageHeader.isHandshake()) {
            throw new MslInternalException("Cannot write payload data for a handshake message.");
        }
        this.currentPayload.write(array, n, n2);
    }
}
