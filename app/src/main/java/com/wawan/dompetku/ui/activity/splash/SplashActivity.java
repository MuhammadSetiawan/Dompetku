package com.wawan.dompetku.ui.activity.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.wawan.dompetku.R;
import com.wawan.dompetku.service.AppReceiver;
import com.wawan.dompetku.service.NotifService;
import com.wawan.dompetku.ui.activity.mainMenuActivity.MainMenuActivity;
import static com.wawan.dompetku.service.AppReceiver.ACTION_RESTART_SERVICE;
import static com.wawan.dompetku.util.UtilFunction.isMyServiceRunning;

public class SplashActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initWidget();
    }

    private void initWidget(){
        this.context = this;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!isMyServiceRunning(context,NotifService.class)) {

                    // tell app receiver to restart
                    // notif service
                     Intent i = new Intent(ACTION_RESTART_SERVICE);
                     i.setClass(getBaseContext(), AppReceiver.class);
                     getBaseContext().sendBroadcast(i);
                }

                startActivity(new Intent(context, MainMenuActivity.class));
                finish();
            }
        },3000);

    }
}