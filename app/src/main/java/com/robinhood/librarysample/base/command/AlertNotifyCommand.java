package com.robinhood.librarysample.base.command;

import android.app.Activity;

import com.tapadoo.alerter.Alerter;

/**
 * Created by jh on 17. 7. 24.
 */

public class AlertNotifyCommand implements MessageNotifyCommand {
    private Activity activity;

    public AlertNotifyCommand(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void execute(String message) {
        Alerter.create(activity)
                .setTitle(message)
                .setText(message)
                .show();
    }
}
