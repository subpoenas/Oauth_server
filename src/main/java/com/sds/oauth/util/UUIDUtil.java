package com.sds.oauth.util;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class UUIDUtil
 {
   public static String base64URLSafeFromBytes(byte[] input)
   {
     return Base64.encodeBase64URLSafeString(input);
   }
 
   public static String base64URLSafeFromString(String input)
   {
     byte[] bytes = input.getBytes();
    return Base64.encodeBase64URLSafeString(bytes);
   }
 
   public static String base64URLSafeOfUUIDObject(UUID uuid) {
     byte[] bytes = asByteArray(uuid);
    return Base64.encodeBase64URLSafeString(bytes);
   }
 
   public static String base64URLSafeOfUUIDString(String uuidString)
   {
     UUID uuid = UUID.fromString(uuidString);
     return base64URLSafeOfUUIDObject(uuid);
   }
 
   public static byte[] asByteArray(UUID uuid) {
     long msb = uuid.getMostSignificantBits();
     long lsb = uuid.getLeastSignificantBits();
 
     byte[] bytes = ByteBuffer.allocate(16).putLong(0, msb).putLong(8, lsb).array();
     return bytes;
   }
 
   public static UUID toUUID(byte[] byteArray)
   {
     long msb = 0L;
     long lsb = 0L;
 
     ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
     msb = byteBuffer.getLong();
     lsb = byteBuffer.getLong();
 
     return new UUID(msb, lsb);
   }
 }
