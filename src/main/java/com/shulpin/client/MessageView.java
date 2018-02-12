package com.shulpin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.shulpin.shared.model.Message;
import com.shulpin.shared.model.UserInfo;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;


public class MessageView extends Composite {

    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable messageLayout = new FlexTable();
    private HorizontalPanel buttons = new HorizontalPanel();
    private DecoratorPanel decPanel = new DecoratorPanel();
    private Button write = new Button("Write");
    private Button delete = new Button("Delete");
    private Message message;

    private Service service = GWT.create(Service.class);

    public MessageView(Message message, int type) {
        //0-без ответа
        int windowHeight = Window.getClientHeight();
        int windowWidth = Window.getClientWidth();

        delete.removeStyleName("gwt-Button");
        delete.addStyleName("messageButton");
        write.removeStyleName("gwt-Button");
        write.addStyleName("messageButton");
        this.message = message;

        messageLayout.setCellSpacing(6);
        FlexTable.FlexCellFormatter cellFormatter = messageLayout.getFlexCellFormatter();

        if(type==1) {
            service.findUserInfoById(message.getFromId(), new MethodCallback<UserInfo>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                    Window.alert("Error find userInfo by id");
                }

                @Override
                public void onSuccess(Method method, UserInfo userInfo) {
                    messageLayout.setHTML(0, 0, "From: " + userInfo.getName());
                }
            });
        }
        else{
            service.findUserInfoById(message.getToId(), new MethodCallback<UserInfo>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                    Window.alert("Error find userInfo by id");
                }

                @Override
                public void onSuccess(Method method, UserInfo userInfo) {
                    messageLayout.setHTML(0, 0, "To: " + userInfo.getName());
                }
            });
        }
        messageLayout.setHTML(0,1,message.getYear()+"-"+message.getMonth()+"-"+message.getDay()+" "+message.getHour()+":"+message.getMinute()+":"+message.getSecond());
        cellFormatter.setHorizontalAlignment(0,0,HasHorizontalAlignment.ALIGN_LEFT);
        cellFormatter.setHorizontalAlignment(0,1,HasHorizontalAlignment.ALIGN_RIGHT);

        messageLayout.setHTML(1,0,message.getContent());
        cellFormatter.setStyleName(1,0,"messageContent");
        cellFormatter.setColSpan(1,0,2);
        cellFormatter.setHorizontalAlignment(1,0,HasHorizontalAlignment.ALIGN_LEFT);

        buttons.setSpacing(0);
        if(type==1) {
            buttons.add(write);
        }
        buttons.add(delete);
        messageLayout.setWidget(2,0,buttons);
        cellFormatter.setColSpan(2,0,2);
        decPanel.removeStyleName("gwt-DecoratorPanel");
        decPanel.addStyleName("messagePanel");
        decPanel.setWidget(messageLayout);
        messageLayout.setWidth(windowWidth*0.8+"px");
        mainPanel.add(decPanel);



    }

    public Button getWrite() {
        return write;
    }

    public Button getDelete() {
        return delete;
    }

    public VerticalPanel getMainPanel() {
        return mainPanel;
    }

    public Message getMessage() {
        return message;
    }
}
