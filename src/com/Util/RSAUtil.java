package com.Util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAUtil {
    //公钥字符串
    public static final String PUBLIC_KEY_STR = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlbf3Efn5+Cv6X2JY9xbZUf5AwlIoCDKoTn5zgl1v2uqnm6UZ7kIic5edqmVtAobTjyaOs0ljl4+qnPfzQ/O7FizXQc38euPBULS4iLWZLCxS7mzOdErY2XOZEmXSSUk4aCihck81/hoHPIbLzbiQxJbXCwKkBN9hCBXsI1b8+UdwtnbhKzUVtzXDE7kTYkuKm/SnEiHPL9BadOfYdltb6Ea1+Xk21LkJJzyAGmWY+PQNIvxYtZKpAUbsev0sA7hE0x22pAUkl3eaiI2TWPdphK6JB8e/XNXPC2MKcksCLtUomRCjga2jYAfn/1P+taQ1NFTqrpVemdoFlqEFtV7sfQIDAQAB";
    //私钥字符串
    public static final String PRIVATE_KEY_STR = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCVt/cR+fn4K/pfYlj3FtlR/kDCUigIMqhOfnOCXW/a6qebpRnuQiJzl52qZW0ChtOPJo6zSWOXj6qc9/ND87sWLNdBzfx648FQtLiItZksLFLubM50StjZc5kSZdJJSThoKKFyTzX+Ggc8hsvNuJDEltcLAqQE32EIFewjVvz5R3C2duErNRW3NcMTuRNiS4qb9KcSIc8v0Fp059h2W1voRrX5eTbUuQknPIAaZZj49A0i/Fi1kqkBRux6/SwDuETTHbakBSSXd5qIjZNY92mErokHx79c1c8LYwpySwIu1SiZEKOBraNgB+f/U/61pDU0VOqulV6Z2gWWoQW1Xux9AgMBAAECggEAYYQHOwA0nj4Wv5XRGG8bcVpHV8onJN/J3AZkud6eAlqgfLIWp6cPxXZVTSzuyZyDo1Ob0o7UfV0n5UFigzn2+0SNl+DQuEIsIaL0JqcRc0ITahQYjIpLn6a5ZtBHHVDBmqYMMeYB5tWRLQ0nJYE3WbRdsGAx8LKGArw0p/f6oWmz9eqT6syZ5/ClbVc1BXOHgojP6/NlkWg7J4hfcjnmZA2UA2vWkg2SxnDld/VoUuWg1b7AvCt5YQyn1GW7BvU1Kz+3gkMRVaL99VSWH2lALZ12CZctIlJfCbvwRn4i+Oqd7RgVlaWSzEkl3/lrRZvVEa+muM0AmexfOkTAuUhwSQKBgQDbwZXzyNGRJgR5VjBSErGhkCCFlBD7mLLgnXrI0uwxx7Per7dJ/31EcsmZp6U8FZ+sPBXllpSVDxKx5Q/f+kkMjSWYo6yMOUavDG9R+xdoDDdx+hXZHuchrAEjCTZmD4eDIN/DX5NRu1anbpzOAZA23BEn9cMS65tE87iQs9Z+9wKBgQCuaUttkvCLs1+swW26rIs2Ey3rXk7mupKA6GCOj7HyVCylmcMc1fns3NMSuiA1sBz0B/Kb+yO1I08/UTNifI6PlDp6g/ujxUIokjufDmcdb8uYBlXAtsS/gfscR6lC/xB7JHTwy5dPxeTHp0HyqU/2WOh3sXhvmUv5wCmzl+nvKwKBgHdKxxPnV5vrf1I+r0xeSX7itkTM88sgQTodQqHxnbYIKmQgDudkgaO+jCGVF+Vd96ePlnNjIpUn8YYQSmXTgAQIOvh5IRmdnfk4TtW58CLTPuXoVCPg2qc0sAlM3vLYSz+rgMyV/WrIv2l0ajqKLrcZD1cZ2iNTMp5jjzU96ppRAoGAcL6WSN3s65HnrihYA41YbHtXesg7HwHKhNQy3tVbBbiOJ/rO2wwjhI93TqrM+YfAH6wnk9dzzLtnlhmqquazzXLXnq1E9SglzU5Chl5sH+ZN6RdnRa2gX9/pcO2OKEXz5Ha7tZEWXjYh30Jq6i+zBh+RHxa1MQcayvcCvhrc2YMCgYAD6Rbsy/XnwwUy3i76gb5jM3ILrVyeVIaXB7bCd41EfN/9a0EWmaCGxy332zr3tPb43Ut7nXYBarAKMGyu31A89nZrP/gf/6Qv6lGLDou6/UW1qWyHru8nuqvyoBknQCoMvu/Gje1YyUwDnP03XoNF0T+9uvwbbueleu7bsOKByA==";
    //生成秘钥对

    //获取公钥(Base64编码)
    public static String getPublicKey(KeyPair keyPair){
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    //获取私钥(Base64编码)
    public static String getPrivateKey(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    //将Base64编码后的公钥转换成PublicKey对象
    public static PublicKey string2PublicKey(String pubStr) throws Exception{
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    //将Base64编码后的私钥转换成PrivateKey对象
    public static PrivateKey string2PrivateKey(String priStr) throws Exception{
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //公钥加密
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    //私钥解密
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    //字节数组转Base64编码
    public static String byte2Base64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    //Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    public static PublicKey keyStrToPublicKey(String publicKeyStr) throws IOException {

        PublicKey publicKey = null;

        byte[] keyBytes = base642Byte(publicKeyStr);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        try {

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(keySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return publicKey;

    }

        /*
        将字符串形式的私钥，转换为私钥对象
     */

    public static PrivateKey keyStrToPrivate(String privateKeyStr) throws IOException{

        PrivateKey privateKey = null;

        byte[] keyBytes = base642Byte(privateKeyStr);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        try {

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            privateKey = keyFactory.generatePrivate(keySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return privateKey;

    }





}
