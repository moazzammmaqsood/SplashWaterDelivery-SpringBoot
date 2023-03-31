package com.splash.utils;

import com.splash.entity.model.UploadInfo;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Constants {

    public static final String APP_KEY_ID = "0056071b179a0870000000002";
    public static final String APP_KEY = "K0054zHx9CcNQ51bGLaAZC5aBGUIoJ8";
    public static String ApiKey = "10834223d4e15463f5c1ef6e275b74f8";
    public static String Phone = "8583";
    public static String VeevoUrl = "https://api.veevotech.com/sendsms?";
    public static String sendpk = "https://sendpk.com/api/sms.php?";

    public static String bucketId = "1630b7b16b2117598a700817";
    public static String bucketName="splash-invoice";
    public static String bucketEndpoint="s3.us-east-005.backblazeb2.com";

    public static Queue<UploadInfo> UploadQueue =new LinkedList<>();

}
