/*
* @(#)Compressor.java   1.0 2010/01/20
 */

package lee.common.util;

import java.util.zip.*;
import java.io.*;

/**
 * 바이너리 압축 유틸리티 클래스
 *
 * @author  주성진
 * @version 1.0, 2010/01/20
 */
public class Compressor {
  /**
   * Compressing a Byte Array
   *
   * @param input byte[]
   * @return byte[]
   */
  public static byte[] compress(byte[] input) {
    // Create the compressor with highest level of compression
    Deflater compressor = new Deflater();
    compressor.setLevel(Deflater.BEST_COMPRESSION);

    // Give the compressor the data to compress
    compressor.setInput(input);
    compressor.finish();

    // Create an expandable byte array to hold the compressed data.
    // You cannot use an array that's the same size as the orginal because
    // there is no guarantee that the compressed data will be smaller than
    // the uncompressed data.
    ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);

    // Compress the data
    byte[] buf = new byte[1024];
    while (!compressor.finished()) {
        int count = compressor.deflate(buf);
        bos.write(buf, 0, count);
    }

    try {
        bos.close();
    } catch (IOException e) {
      // ignore
    }

    // Get the compressed data
    byte[] compressedData = bos.toByteArray();
    return compressedData;
  }

  /**
   * Decompressing a Byte Array
   *
   * @param compressedData byte[]
   * @return byte[]
   */
  public static byte[] decompress(byte[] compressedData) {
    // Create the decompressor and give it the data to compress
    Inflater decompressor = new Inflater();
    decompressor.setInput(compressedData);

    // Create an expandable byte array to hold the decompressed data
    ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedData.length);

    // Decompress the data
    byte[] buf = new byte[1024];
    while (!decompressor.finished()) {
        try {
            int count = decompressor.inflate(buf);
            bos.write(buf, 0, count);
        } catch (DataFormatException e) {
        }
    }
    try {
        bos.close();
    } catch (IOException e) {
      // ignore
    }

    // Get the decompressed data
    byte[] decompressedData = bos.toByteArray();
    return decompressedData;
  }
}
