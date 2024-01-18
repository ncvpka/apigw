package vn.supperapp.apigw.restful.models;

import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.utils.ErrorCode;

/**
 * @author TruongLe
 * @Date 14/03/2022
 * @see NotificationResponse
 */

public class NotificationResponse extends BaseResponse {

    private PagingResult messages;
    private int totalNumberUnread;

    public NotificationResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public PagingResult getMessages() {
        return messages;
    }

    public void setMessages(PagingResult messages) {
        this.messages = messages;
    }

    public int getTotalNumberUnread() {
        return totalNumberUnread;
    }

    public void setTotalNumberUnread(int totalNumberUnread) {
        this.totalNumberUnread = totalNumberUnread;
    }
}
