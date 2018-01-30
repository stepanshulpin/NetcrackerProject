package com.shulpin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.shulpin.shared.model.Message;
import com.shulpin.shared.model.MyResponse;
import com.shulpin.shared.model.UserInfo;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainView implements DialogBoxOpener {

    private InputDialog dialogBox;
    private TabLayoutPanel mainPanel = new TabLayoutPanel(1.5, Style.Unit.EM);
    private VerticalPanel userPanel = new VerticalPanel();
    private TabLayoutPanel messagePanel = new TabLayoutPanel(1.5, Style.Unit.EM);
    private VerticalPanel incomingPanel = new VerticalPanel();
    private VerticalPanel outgoingPanel = new VerticalPanel();
    private VerticalPanel gridAndPages = new VerticalPanel();
    private HorizontalPanel pagesPanel = new HorizontalPanel();
    private HorizontalPanel pagesPanelIncomingMessages = new HorizontalPanel();
    private HorizontalPanel pagesPanelOutgoingMessages = new HorizontalPanel();
    private Grid users = new Grid(3,3);
    private Grid incomingMessages = new Grid(6,1);
    private Grid outgoingMessages = new Grid(6,1);
    private Service service = GWT.create(Service.class);
    private Long messageTo=null;
    private Long messageFrom=null;
    private VerticalPanel usersVerticalPanel = new VerticalPanel();
    private VerticalPanel incomingMessagesVerticalPanel = new VerticalPanel();
    private VerticalPanel outgoingMessagesVerticalPanel = new VerticalPanel();
    private Button logOutButton = new Button("LogOut");
    private Button logOutButtonIn = new Button("LogOut");
    private Button logOutButtonOut = new Button("LogOut");
    private String login;

    public MainView(String login) {
        this.login=login;
        int windowHeight = Window.getClientHeight();
        int windowWidth = Window.getClientWidth();

        service.findUserInfoId(login, new MethodCallback<Long>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Error load userInfo!");
            }

            @Override
            public void onSuccess(Method method, Long id) {
                messageFrom=id;
                service.getMessagesFrom(messageFrom, new MethodCallback<List<Message>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Error load incoming messages");
                    }

                    @Override
                    public void onSuccess(Method method, List<Message> messages) {
                        int sizeList =messages.size();
                        int pages = sizeList/6+1;
                        if(sizeList<6){
                            List<Message> messageList=messages.subList(0,sizeList);
                            addMessagesOnIncomingGrid(messageList);
                        }
                        else {
                            List<Message> messageList = messages.subList(0, 6);
                            addMessagesOnIncomingGrid(messageList);
                        }
                        pagesPanelIncomingMessages.setSpacing(3);
                        for (int k = 1; k < pages; k++) {
                            Button pageButton = new Button(String.valueOf(k));
                            pageButton.addClickHandler(new ClickHandler() {
                                @Override
                                public void onClick(ClickEvent clickEvent) {
                                    incomingPanel.remove(incomingMessages);
                                    incomingPanel.remove(pagesPanelIncomingMessages);
                                    int p =Integer.valueOf(pageButton.getText());
                                    if(p<pages){
                                        List<Message> messageList1=messages.subList((p-1)*6,p*6);
                                        addMessagesOnIncomingGrid(messageList1);
                                        incomingPanel.add(incomingMessages);
                                        incomingPanel.add(pagesPanelIncomingMessages);
                                    }
                                    else{
                                        incomingMessages.clear();
                                        List<Message> messageList1=messages.subList((p-1)*6,sizeList);
                                        addMessagesOnIncomingGrid(messageList1);
                                        incomingPanel.add(incomingMessages);
                                        incomingPanel.add(pagesPanelIncomingMessages);
                                    }
                                }
                            });
                            pagesPanelIncomingMessages.add(pageButton);
                        }
                    }
                });
                service.getMessagesTo(messageFrom, new MethodCallback<List<Message>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Error load incoming messages");
                    }

                    @Override
                    public void onSuccess(Method method, List<Message> messages) {
                        int sizeList =messages.size();
                        int pages = sizeList/6+1;
                        if(sizeList<6){
                            List<Message> messageList=messages.subList(0,sizeList);
                            addMessagesOnOutgoingGrid(messageList);
                        }
                        else {
                            List<Message> messageList = messages.subList(0, 6);
                            addMessagesOnOutgoingGrid(messageList);
                        }
                        pagesPanelOutgoingMessages.setSpacing(3);
                        for (int k = 1; k < pages; k++) {
                            Button pageButton = new Button(String.valueOf(k));
                            pageButton.addClickHandler(new ClickHandler() {
                                @Override
                                public void onClick(ClickEvent clickEvent) {
                                    outgoingPanel.remove(outgoingMessages);
                                    outgoingPanel.remove(pagesPanelOutgoingMessages);
                                    int p =Integer.valueOf(pageButton.getText());
                                    if(p<pages){
                                        List<Message> messageList1=messages.subList((p-1)*6,p*6);
                                        addMessagesOnOutgoingGrid(messageList1);
                                        outgoingPanel.add(outgoingMessages);
                                        outgoingPanel.add(pagesPanelOutgoingMessages);
                                    }
                                    else{
                                        outgoingMessages.clear();
                                        List<Message> messageList1=messages.subList((p-1)*6,sizeList);
                                        addMessagesOnOutgoingGrid(messageList1);
                                        outgoingPanel.add(outgoingMessages);
                                        outgoingPanel.add(pagesPanelOutgoingMessages);
                                    }
                                }
                            });
                            pagesPanelOutgoingMessages.add(pageButton);
                        }
                    }
                });


            }
        });

        service.getAllUsersWithoutUsername(login, new MethodCallback<List<UserInfo>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Error on load users");
            }

            @Override
            public void onSuccess(Method method, List<UserInfo> userInfos) {
                int sizeList = userInfos.size();
                int pages = sizeList/9+1;
                if(sizeList<9){
                    List<UserInfo> userInfoList=userInfos.subList(0,sizeList);
                    addUsersOnGrid(userInfoList);
                }
                else{
                    List<UserInfo> userInfoList=userInfos.subList(0,9);
                    addUsersOnGrid(userInfoList);
                }

                pagesPanel.setSpacing(3);
                for(int k = 1; k<=pages; k++){
                    Button pageButton = new Button(String.valueOf(k));
                    pageButton.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            gridAndPages.remove(users);
                            gridAndPages.remove(pagesPanel);
                            int p =Integer.valueOf(pageButton.getText());
                            if(p<pages){
                                List<UserInfo> userInfoList=userInfos.subList((p-1)*9,p*9);
                                addUsersOnGrid(userInfoList);
                                gridAndPages.add(users);
                                gridAndPages.add(pagesPanel);
                            }
                            else{
                                users.clear();
                                List<UserInfo> userInfoList=userInfos.subList((p-1)*9,sizeList);
                                addUsersOnGrid(userInfoList);
                                gridAndPages.add(users);
                                gridAndPages.add(pagesPanel);
                            }
                        }
                    });
                    pagesPanel.add(pageButton);
                }
                gridAndPages.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
                gridAndPages.add(users);
                gridAndPages.add(pagesPanel);

                userPanel.setSize("100%","100%");
                userPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
                userPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
                userPanel.add(gridAndPages);



            }
        });






        incomingPanel.setSize("100%","100%");
        incomingPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        incomingPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        incomingPanel.add(incomingMessages);
        incomingPanel.add(pagesPanelIncomingMessages);



        outgoingPanel.setSize("100%","100%");
        outgoingPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        outgoingPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        outgoingPanel.add(outgoingMessages);
        outgoingPanel.add(pagesPanelOutgoingMessages);


        incomingMessagesVerticalPanel.add(logOutButtonIn);
        incomingMessagesVerticalPanel.add(incomingPanel);
        incomingMessagesVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        incomingMessagesVerticalPanel.setWidth("100%");
        incomingMessagesVerticalPanel.setHeight("100%");

        outgoingMessagesVerticalPanel.add(logOutButtonOut);
        outgoingMessagesVerticalPanel.add(outgoingPanel);
        outgoingMessagesVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        outgoingMessagesVerticalPanel.setWidth("100%");
        outgoingMessagesVerticalPanel.setHeight("100%");


        messagePanel.add(incomingMessagesVerticalPanel, "Incoming");
        messagePanel.add(outgoingMessagesVerticalPanel, "Outgoing");


        usersVerticalPanel.add(logOutButton);
        usersVerticalPanel.add(userPanel);
        usersVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        usersVerticalPanel.setWidth("100%");
        usersVerticalPanel.setHeight("100%");


        mainPanel.add(usersVerticalPanel,"Users");
        mainPanel.add(messagePanel, "Messages");
        mainPanel.setWidth(windowWidth+"px");
        mainPanel.setHeight(windowHeight+"px");


    }

    private void addMessagesOnOutgoingGrid(List<Message> messageList1) {
        int i=0;
        for (Message message:messageList1) {
            MessageView messageView = new MessageView(message,0);
            messageView.getDelete().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    service.deleteMessage(message, new MethodCallback<MyResponse>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            Window.alert("Error delete message");
                        }

                        @Override
                        public void onSuccess(Method method, MyResponse myResponse) {
                            drawMessages();
                        }
                    });
                }
            });
            outgoingMessages.setWidget(i,0,messageView.getMainPanel());
            i++;
        }


    }

    private void addMessagesOnIncomingGrid(List<Message> messageList) {
        int i=0;
        for (Message message:messageList) {
            MessageView messageView = new MessageView(message,1);
            messageView.getDelete().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    service.deleteMessage(message, new MethodCallback<MyResponse>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            Window.alert("Error delete message");
                        }

                        @Override
                        public void onSuccess(Method method, MyResponse myResponse) {
                            Window.alert("delete");
                        }
                    });
                }
            });
            messageView.getWrite().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    messageTo=messageView.getMessage().getFromId();
                    openInputDialog();
                }
            });
            incomingMessages.setWidget(i,0,messageView.getMainPanel());
            i++;
        }
    }

    private void addUsersOnGrid(List<UserInfo> userInfoList) {
        int i=0;
        for (UserInfo userInfo:userInfoList) {
            UserView userView = new UserView(userInfo);
            userView.getWrite().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    messageTo = userView.getUserInfo().getId();
                    openInputDialog();
                }
            });
            users.setWidget(i/3, i%3, userView.getMainPanel());
            i++;
        }
    }

    private void openInputDialog() {
        dialogBox = new InputDialog();
        dialogBox.setOpener(this);
        dialogBox.center ();
        dialogBox.show();
    }

    public TabLayoutPanel getMainPanel() {
        return mainPanel;
    }

    public Button getLogOutButton() {
        return logOutButton;
    }

    public Button getLogOutButtonIn() {
        return logOutButtonIn;
    }

    public Button getLogOutButtonOut() {
        return logOutButtonOut;
    }

    @Override
    public void dialogBoxCancelled() {
        Date date = new Date();
        service.addMessage(new Message(messageFrom, messageTo, dialogBox.getTextArea().getText(), date.getYear() + 1900, date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()), new MethodCallback<MyResponse>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Error send message!");
            }

            @Override
            public void onSuccess(Method method, MyResponse myResponse) {
                Window.alert("The message is sent!");
                drawMessages();
            }
        });
    }

    private void drawMessages() {
        service.getMessagesFrom(messageFrom, new MethodCallback<List<Message>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Error load incoming messages");
            }

            @Override
            public void onSuccess(Method method, List<Message> messages) {
                int sizeList =messages.size();
                int pages = sizeList/6+1;
                incomingMessages.clear();
                incomingPanel.remove(incomingMessages);
                incomingPanel.remove(pagesPanelIncomingMessages);
                if(sizeList<6){
                    List<Message> messageList=messages.subList(0,sizeList);
                    addMessagesOnIncomingGrid(messageList);
                }
                else {
                    List<Message> messageList = messages.subList(0, 6);
                    addMessagesOnIncomingGrid(messageList);
                }

                pagesPanelIncomingMessages.setSpacing(3);
                for (int k = 1; k < pages; k++) {
                    Button pageButton = new Button(String.valueOf(k));
                    pageButton.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            incomingPanel.remove(incomingMessages);
                            incomingPanel.remove(pagesPanelIncomingMessages);
                            int p =Integer.valueOf(pageButton.getText());
                            if(p<pages){
                                List<Message> messageList1=messages.subList((p-1)*6,p*6);
                                addMessagesOnIncomingGrid(messageList1);
                                incomingPanel.add(incomingMessages);
                                incomingPanel.add(pagesPanelIncomingMessages);
                            }
                            else{
                                incomingMessages.clear();
                                List<Message> messageList1=messages.subList((p-1)*6,sizeList);
                                addMessagesOnIncomingGrid(messageList1);
                                incomingPanel.add(incomingMessages);
                                incomingPanel.add(pagesPanelIncomingMessages);
                            }
                        }
                    });
                    pagesPanelIncomingMessages.add(pageButton);
                }
                incomingPanel.add(incomingMessages);
                incomingPanel.add(pagesPanelIncomingMessages);
            }
        });
        service.getMessagesTo(messageFrom, new MethodCallback<List<Message>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Error load incoming messages");
            }

            @Override
            public void onSuccess(Method method, List<Message> messages) {
                int sizeList =messages.size();
                int pages = sizeList/6+1;
                outgoingMessages.clear();
                outgoingPanel.remove(outgoingMessages);
                outgoingPanel.remove(pagesPanelOutgoingMessages);
                if(sizeList<6){
                    List<Message> messageList=messages.subList(0,sizeList);
                    addMessagesOnOutgoingGrid(messageList);
                }
                else {
                    List<Message> messageList = messages.subList(0, 6);
                    addMessagesOnOutgoingGrid(messageList);
                }
                pagesPanelOutgoingMessages.setSpacing(3);
                for (int k = 1; k < pages; k++) {
                    Button pageButton = new Button(String.valueOf(k));
                    pageButton.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            outgoingPanel.remove(outgoingMessages);
                            outgoingPanel.remove(pagesPanelOutgoingMessages);
                            int p =Integer.valueOf(pageButton.getText());
                            if(p<pages){
                                List<Message> messageList1=messages.subList((p-1)*6,p*6);
                                addMessagesOnOutgoingGrid(messageList1);
                                outgoingPanel.add(outgoingMessages);
                                outgoingPanel.add(pagesPanelOutgoingMessages);
                            }
                            else{
                                outgoingMessages.clear();
                                List<Message> messageList1=messages.subList((p-1)*6,sizeList);
                                addMessagesOnOutgoingGrid(messageList1);
                                outgoingPanel.add(outgoingMessages);
                                outgoingPanel.add(pagesPanelOutgoingMessages);
                            }
                        }
                    });
                    pagesPanelOutgoingMessages.add(pageButton);
                }
                outgoingPanel.add(outgoingMessages);
                outgoingPanel.add(pagesPanelOutgoingMessages);
            }
        });
    }
}
