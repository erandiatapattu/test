package com.example.ashantha.connectme;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.google.android.gms.nearby.messages.Message;
import com.google.gson.Gson;

import java.nio.charset.Charset;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Used to prepare the payload for a
 * {@link com.google.android.gms.nearby.messages.Message Nearby Message}. Adds a unique id
 * to the Message payload, which helps Nearby distinguish between multiple devices with
 * the same model name.
 */
public class DeviceMessage {
    private static final Gson gson = new Gson();

    private final String mUUID;
    private final String mMessageBody;

    public static Message newNearbyMessage(String instanceId, String number) {
        DeviceMessage deviceMessage = new DeviceMessage(instanceId, number);
        return new Message(gson.toJson(deviceMessage).getBytes(Charset.forName("UTF-8")));
    }

    public static DeviceMessage fromNearbyMessage(Message message) {
        String nearbyMessageString = new String(message.getContent()).trim();
        return gson.fromJson(
                (new String(nearbyMessageString.getBytes(Charset.forName("UTF-8")))),
                DeviceMessage.class);
    }

    private DeviceMessage(String uuid, String number) {
        mUUID = uuid;
        mMessageBody = number;
    }

    protected String getMessageBody() {
        return mMessageBody;
    }
}