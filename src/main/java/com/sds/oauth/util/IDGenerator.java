package com.sds.oauth.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
 
 public class IDGenerator
 {
   private static final String TAG = "idgenv";
   private static final int version = 1;
   private static final int LENGTH = 26;
   static volatile IDGenerator instance = null;
   private Random rand;
   String prefixString;
   String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
   byte[] bytes = new byte[22];
 
   private IDGenerator() {
     init();
   }

   public static IDGenerator getInstance() {
     synchronized (IDGenerator.class) {
       if (instance == null) {
         instance = new IDGenerator();
       }
     }
     return instance;
   }
 
   public String getID() {
     StringBuffer result = new StringBuffer(this.prefixString);
     result.append(System.nanoTime());
     for (int i = result.toString().length(); i < 22; i++) {
       result.append(getOneMore());
     }

     CharSequence temp = result.toString();
     this.bytes[0] = (byte)temp.charAt(0);
     this.bytes[1] = (byte)temp.charAt(1);
     this.bytes[2] = (byte)temp.charAt(2);
     this.bytes[3] = (byte)temp.charAt(3);
     this.bytes[4] = (byte)temp.charAt(4);
    this.bytes[5] = (byte)temp.charAt(5);
     this.bytes[6] = (byte)temp.charAt(6);
     this.bytes[7] = (byte)temp.charAt(7);
     this.bytes[8] = (byte)temp.charAt(8);
     this.bytes[9] = (byte)temp.charAt(9);
     this.bytes[10] = (byte)temp.charAt(10);
     this.bytes[11] = (byte)temp.charAt(11);
     this.bytes[12] = (byte)temp.charAt(12);
     this.bytes[13] = (byte)temp.charAt(13);
     this.bytes[14] = (byte)temp.charAt(14);
     this.bytes[15] = (byte)temp.charAt(15);
     this.bytes[16] = (byte)temp.charAt(16);
     this.bytes[17] = (byte)temp.charAt(17);
     this.bytes[18] = (byte)temp.charAt(18);
     this.bytes[19] = (byte)temp.charAt(19);
     this.bytes[20] = (byte)temp.charAt(20);
     this.bytes[21] = (byte)temp.charAt(21);
 
     String temp2 = getOthers(this.bytes);
     result.append(temp2);
     return result.toString();
   }
 
   private String getOthers(byte[] bytes) {
     int sum1 = (bytes[2] & 0xF) + (bytes[4] & 0xF) + (bytes[6] & 0xFF);
     int sum2 = (bytes[6] & 0xF) + (bytes[9] & 0xFF) + (bytes[12] & 0xFF) + (bytes[15] & 0xFF);
     int sum3 = (bytes[5] & 0xFF) + (bytes[10] & 0xF) + (bytes[11] & 0xFF);
     int sum4 = (bytes[13] & 0xF) + (bytes[17] & 0xFF) + (bytes[20] & 0xF0);
 
     StringBuffer buffer = new StringBuffer();
     buffer.append((char)(sum1 % 20 + 65));
     buffer.append((char)(sum2 % 10 + 98));
     buffer.append((char)(sum3 % 10 + 67));
     buffer.append((char)(sum4 % 9 + 48));
 
     String result = buffer.toString();
 
     return result;
   }
 
   public boolean verifyID(String id) {
     if ((id == null) || (id.length() < 4) || (id.length() != 26)) {
       return false;
     }
 
     String original = id.substring(0, id.length() - 4);
 
     return id.equals(original + getOthers(original.getBytes()));
   }
 
   public String getTag() {
     return getRandomString();
  }
 
   private void init()
   {
     this.rand = new Random(System.nanoTime());
     try
     {
       InetAddress addr = InetAddress.getLocalHost();
       byte[] ipAddr = addr.getAddress();
       this.prefixString = UUIDUtil.base64URLSafeFromBytes(ipAddr);
     } catch (UnknownHostException e) {
       byte[] fakeIpAddr = new byte[4];
       for (int i = 0; i < fakeIpAddr.length; i++) {
         fakeIpAddr[i] = (byte)this.rand.nextInt(255);
      }
 
       this.prefixString = UUIDUtil.base64URLSafeFromBytes(fakeIpAddr);
       e.printStackTrace();
     }
   }
 
   private char getOneMore()
   {
     int idx = this.rand.nextInt(this.charset.length());
 
     return this.charset.charAt(idx);
   }
 
   private String getRandomString() {
     return "";
   }
 }