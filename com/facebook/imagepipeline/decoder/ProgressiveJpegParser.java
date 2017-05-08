// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Closeables;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.memory.PooledByteArrayBufferedInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.common.util.StreamUtil;
import java.io.IOException;
import com.facebook.common.internal.Throwables;
import java.io.InputStream;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.memory.ByteArrayPool;

public class ProgressiveJpegParser
{
    private int mBestScanEndOffset;
    private int mBestScanNumber;
    private final ByteArrayPool mByteArrayPool;
    private int mBytesParsed;
    private int mLastByteRead;
    private int mNextFullScanNumber;
    private int mParserState;
    
    public ProgressiveJpegParser(final ByteArrayPool byteArrayPool) {
        this.mByteArrayPool = Preconditions.checkNotNull(byteArrayPool);
        this.mBytesParsed = 0;
        this.mLastByteRead = 0;
        this.mNextFullScanNumber = 0;
        this.mBestScanEndOffset = 0;
        this.mBestScanNumber = 0;
        this.mParserState = 0;
    }
    
    private boolean doParseMoreData(final InputStream inputStream) {
        final int mBestScanNumber = this.mBestScanNumber;
        int read = 0;
        int n;
        Label_0080_Outer:Label_0076_Outer:
        while (true) {
            while (true) {
                Label_0286: {
                    while (true) {
                        Label_0244: {
                            Label_0236: {
                                Label_0176: {
                                    Label_0161: {
                                        Label_0137: {
                                            Label_0113: {
                                                try {
                                                    if (this.mParserState == 6) {
                                                        break;
                                                    }
                                                    read = inputStream.read();
                                                    if (read == -1) {
                                                        break;
                                                    }
                                                    ++this.mBytesParsed;
                                                    switch (this.mParserState) {
                                                        case 0: {
                                                            break Label_0113;
                                                        }
                                                        case 1: {
                                                            break Label_0137;
                                                        }
                                                        case 2: {
                                                            break Label_0161;
                                                        }
                                                        case 3: {
                                                            break Label_0176;
                                                        }
                                                        case 4: {
                                                            break Label_0236;
                                                        }
                                                        case 5: {
                                                            break Label_0244;
                                                        }
                                                        default: {
                                                            break Label_0286;
                                                        }
                                                    }
                                                    this.mLastByteRead = read;
                                                    continue Label_0080_Outer;
                                                    Preconditions.checkState(false);
                                                    continue Label_0076_Outer;
                                                }
                                                catch (IOException ex) {
                                                    Throwables.propagate(ex);
                                                }
                                                break;
                                            }
                                            if (read == 255) {
                                                this.mParserState = 1;
                                                continue Label_0076_Outer;
                                            }
                                            this.mParserState = 6;
                                            continue Label_0076_Outer;
                                        }
                                        if (read == 216) {
                                            this.mParserState = 2;
                                            continue Label_0076_Outer;
                                        }
                                        this.mParserState = 6;
                                        continue Label_0076_Outer;
                                    }
                                    if (read == 255) {
                                        this.mParserState = 3;
                                        continue Label_0076_Outer;
                                    }
                                    continue Label_0076_Outer;
                                }
                                if (read == 255) {
                                    this.mParserState = 3;
                                    continue Label_0076_Outer;
                                }
                                if (read == 0) {
                                    this.mParserState = 2;
                                    continue Label_0076_Outer;
                                }
                                if (read == 218 || read == 217) {
                                    this.newScanOrImageEndFound(this.mBytesParsed - 2);
                                }
                                if (doesMarkerStartSegment(read)) {
                                    this.mParserState = 4;
                                    continue Label_0076_Outer;
                                }
                                this.mParserState = 2;
                                continue Label_0076_Outer;
                            }
                            this.mParserState = 5;
                            continue Label_0076_Outer;
                        }
                        n = (this.mLastByteRead << 8) + read - 2;
                        StreamUtil.skip(inputStream, n);
                        this.mBytesParsed += n;
                        this.mParserState = 2;
                        continue Label_0076_Outer;
                    }
                }
                continue;
            }
        }
        return this.mParserState != 6 && this.mBestScanNumber != mBestScanNumber;
    }
    
    private static boolean doesMarkerStartSegment(final int n) {
        boolean b = true;
        if (n != 1 && (n < 208 || n > 215)) {
            if (n == 217 || n == 216) {
                b = false;
            }
            return b;
        }
        return false;
    }
    
    private void newScanOrImageEndFound(int n) {
        if (this.mNextFullScanNumber > 0) {
            this.mBestScanEndOffset = n;
        }
        n = this.mNextFullScanNumber++;
        this.mBestScanNumber = n;
    }
    
    public int getBestScanEndOffset() {
        return this.mBestScanEndOffset;
    }
    
    public int getBestScanNumber() {
        return this.mBestScanNumber;
    }
    
    public boolean parseMoreData(EncodedImage encodedImage) {
        if (this.mParserState == 6) {
            return false;
        }
        if (encodedImage.getSize() <= this.mBytesParsed) {
            return false;
        }
        encodedImage = (EncodedImage)new PooledByteArrayBufferedInputStream(encodedImage.getInputStream(), this.mByteArrayPool.get(16384), this.mByteArrayPool);
        try {
            StreamUtil.skip((InputStream)encodedImage, this.mBytesParsed);
            return this.doParseMoreData((InputStream)encodedImage);
        }
        catch (IOException ex) {
            Throwables.propagate(ex);
            return false;
        }
        finally {
            Closeables.closeQuietly((InputStream)encodedImage);
        }
    }
}
