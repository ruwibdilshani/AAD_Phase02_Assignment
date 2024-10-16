package lk.ijse.gdse.aad.posusingspring.util;

import java.time.LocalDateTime;
import java.util.Random;

public class AppUtil {
    public static String createCusId(){
        return "C"+new Random().nextInt(10000);

    }

    public static String createItemCode(){
        return "I"+new Random().nextInt(10000);
    }

//    public static String toBase64P
//    rofilePic(byte [] profilePic){
//        return Base64.getEncoder().encodeToString(profilePic);
//    }

    public static String createOrderId(){
        return "OR"+new Random().nextInt(10000);
    }

    public static LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now();
    }

    public static String createOrderDetailsId(){
        return "OD"+new Random().nextInt(10000);
    }

}

