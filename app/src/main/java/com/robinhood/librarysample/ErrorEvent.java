package com.robinhood.librarysample;

/**
 * Created by jh on 17. 7. 24.
 */

public class ErrorEvent {
    String errMessage;

    public ErrorEvent(String errMessage) {

        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
