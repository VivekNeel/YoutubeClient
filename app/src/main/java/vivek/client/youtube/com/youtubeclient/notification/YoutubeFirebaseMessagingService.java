package vivek.client.youtube.com.youtubeclient.notification;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by vivek on 02/10/17.
 */

public class YoutubeFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String key = remoteMessage.getData().get("videoId");
    }
}
