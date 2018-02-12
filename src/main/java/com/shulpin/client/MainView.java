package com.shulpin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.shulpin.shared.model.Message;
import com.shulpin.shared.model.MyResponse;
import com.shulpin.shared.model.Selector;
import com.shulpin.shared.model.UserInfo;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainView implements DialogBoxOpener {

    private Selector selector;
    private VerticalPanel selectorPanel = new VerticalPanel();
    private DecoratorPanel decSelectorPanel = new DecoratorPanel();
    private ListBox sortByListBox = new ListBox();
    private ListBox genderListBox = new ListBox();
    private ListBox minAgeListBox = new ListBox();
    private ListBox maxAgeListBox = new ListBox();
    private ListBox cityListBox = new ListBox();
    private Button selectButton = new Button("Select");

    private HorizontalPanel selectorAndUsers = new HorizontalPanel();

    private InputDialog dialogBox;
    private TabLayoutPanel mainPanel = new TabLayoutPanel(2.5, Style.Unit.EM);
    private VerticalPanel userPanel = new VerticalPanel();
    private TabLayoutPanel messagePanel = new TabLayoutPanel(2.5, Style.Unit.EM);
    private VerticalPanel incomingPanel = new VerticalPanel();
    private VerticalPanel outgoingPanel = new VerticalPanel();
    private VerticalPanel gridAndPages = new VerticalPanel();
    private HorizontalPanel pagesPanel = new HorizontalPanel();
    private HorizontalPanel pagesPanelIncomingMessages = new HorizontalPanel();
    private HorizontalPanel pagesPanelOutgoingMessages = new HorizontalPanel();
    private Grid users = new Grid(3,3);
    private Grid incomingMessages = new Grid(5,1);
    private Grid outgoingMessages = new Grid(5,1);
    private Service service = GWT.create(Service.class);
    private Long messageTo=null;
    private Long messageFrom=null;
    private VerticalPanel usersVerticalPanel = new VerticalPanel();
    private VerticalPanel incomingMessagesVerticalPanel = new VerticalPanel();
    private VerticalPanel outgoingMessagesVerticalPanel = new VerticalPanel();
    private VerticalPanel reloadPanel = new VerticalPanel();
    private Button reload = new Button("Reload");
    private Button logOutButton = new Button("LogOut");
    private Button logOutButtonIn = new Button("LogOut");
    private Button logOutButtonOut = new Button("LogOut");
    private String login;

    private int min;
    private int max;

    public MainView(String login) {
        this.login=login;
        int windowHeight = Window.getClientHeight();
        int windowWidth = Window.getClientWidth();
        selector = new Selector("name", "", "", 18, 99);
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
                        int pages = (sizeList-1)/5+1;
                        if(sizeList!=0) {
                            if (sizeList < 5) {
                                List<Message> messageList = messages.subList(0, sizeList);
                                addMessagesOnIncomingGrid(messageList);
                            } else {
                                List<Message> messageList = messages.subList(0, 5);
                                addMessagesOnIncomingGrid(messageList);
                            }
                            pagesPanelIncomingMessages.setSpacing(3);
                            for (int k = 1; k <= pages; k++) {
                                Button pageButton = new Button(String.valueOf(k));
                                pageButton.removeStyleName("gwt-Button");
                                pageButton.addStyleName("pageButton");
                                pageButton.addClickHandler(new ClickHandler() {
                                    @Override
                                    public void onClick(ClickEvent clickEvent) {
                                        incomingPanel.remove(incomingMessages);
                                        incomingPanel.remove(pagesPanelIncomingMessages);
                                        int p = Integer.valueOf(pageButton.getText());
                                        if (p < pages) {
                                            List<Message> messageList1 = messages.subList((p - 1) * 5, p * 5);
                                            addMessagesOnIncomingGrid(messageList1);
                                            incomingPanel.add(incomingMessages);
                                            incomingPanel.add(pagesPanelIncomingMessages);
                                        } else {
                                            incomingMessages.clear();
                                            List<Message> messageList1 = messages.subList((p - 1) * 5, sizeList);
                                            addMessagesOnIncomingGrid(messageList1);
                                            incomingPanel.add(incomingMessages);
                                            incomingPanel.add(pagesPanelIncomingMessages);
                                        }
                                    }
                                });
                                pagesPanelIncomingMessages.add(pageButton);
                            }
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
                        int pages = (sizeList-1)/5+1;
                        if(sizeList!=0) {
                            if (sizeList < 5) {
                                List<Message> messageList = messages.subList(0, sizeList);
                                addMessagesOnOutgoingGrid(messageList);
                            } else {
                                List<Message> messageList = messages.subList(0, 5);
                                addMessagesOnOutgoingGrid(messageList);
                            }
                            pagesPanelOutgoingMessages.setSpacing(3);
                            for (int k = 1; k <= pages; k++) {
                                Button pageButton = new Button(String.valueOf(k));
                                pageButton.removeStyleName("gwt-Button");
                                pageButton.addStyleName("pageButton");
                                pageButton.addClickHandler(new ClickHandler() {
                                    @Override
                                    public void onClick(ClickEvent clickEvent) {
                                        outgoingPanel.remove(outgoingMessages);
                                        outgoingPanel.remove(pagesPanelOutgoingMessages);
                                        int p = Integer.valueOf(pageButton.getText());
                                        if (p < pages) {
                                            List<Message> messageList1 = messages.subList((p - 1) * 5, p * 5);
                                            addMessagesOnOutgoingGrid(messageList1);
                                            outgoingPanel.add(outgoingMessages);
                                            outgoingPanel.add(pagesPanelOutgoingMessages);
                                        } else {
                                            outgoingMessages.clear();
                                            List<Message> messageList1 = messages.subList((p - 1) * 5, sizeList);
                                            addMessagesOnOutgoingGrid(messageList1);
                                            outgoingPanel.add(outgoingMessages);
                                            outgoingPanel.add(pagesPanelOutgoingMessages);
                                        }
                                    }
                                });
                                pagesPanelOutgoingMessages.add(pageButton);
                            }
                        }
                    }
                });


            }
        });

        usersRequest(0);



        users.addStyleName("usersPanel");
        incomingMessages.addStyleName("usersPanel");
        outgoingMessages.addStyleName("usersPanel");
        logOutButton.removeStyleName("gwt-Button");
        logOutButton.addStyleName("logoutButton");
        logOutButtonIn.removeStyleName("gwt-Button");
        logOutButtonIn.addStyleName("logoutButton");
        logOutButtonOut.removeStyleName("gwt-Button");
        logOutButtonOut.addStyleName("logoutButton");
        reload.removeStyleName("gwt-Button");
        reload.addStyleName("reloadButton");


        reload.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                drawMessages();
            }
        });

        //incomingPanel.setSize("100%","100%");
        incomingPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        incomingPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        reloadPanel.setWidth(windowWidth*0.8+"px");
        reloadPanel.add(reload);
        incomingPanel.setSpacing(0);
        incomingPanel.add(reloadPanel);
        incomingPanel.add(incomingMessages);
        incomingPanel.add(pagesPanelIncomingMessages);



        outgoingPanel.setSize("100%","100%");
        outgoingPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        outgoingPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        outgoingPanel.add(outgoingMessages);
        outgoingPanel.add(pagesPanelOutgoingMessages);





        incomingMessagesVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        incomingMessagesVerticalPanel.add(logOutButtonIn);
        VerticalPanel incomingWithoutLogout = new VerticalPanel();
        incomingWithoutLogout.setSize("100%", "100%");
        incomingWithoutLogout.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        incomingWithoutLogout.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        incomingWithoutLogout.add(incomingPanel);
        incomingMessagesVerticalPanel.add(incomingWithoutLogout);
        incomingMessagesVerticalPanel.setWidth("100%");
        incomingMessagesVerticalPanel.setHeight("100%");

        outgoingMessagesVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        outgoingMessagesVerticalPanel.add(logOutButtonOut);
        outgoingMessagesVerticalPanel.add(outgoingPanel);
        outgoingMessagesVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        outgoingMessagesVerticalPanel.setWidth("100%");
        outgoingMessagesVerticalPanel.setHeight("100%");


        messagePanel.add(incomingMessagesVerticalPanel, "Incoming");
        messagePanel.add(outgoingMessagesVerticalPanel, "Outgoing");


        selectorPanel.add(new Label("Sort by:"));
        sortByListBox.addItem("name");
        sortByListBox.addItem("age");
        sortByListBox.addItem("city");
        sortByListBox.setWidth(windowWidth/10+"px");
        sortByListBox.removeStyleName("gwt-ListBox");
        sortByListBox.addStyleName("listBox");
        selectorPanel.add(sortByListBox);
        selectorPanel.add(new Label("Gender:"));
        genderListBox.addItem("");
        genderListBox.addItem("Male");
        genderListBox.addItem("Female");
        genderListBox.setWidth(windowWidth/10+"px");
        genderListBox.removeStyleName("gwt-ListBox");
        genderListBox.addStyleName("listBox");
        selectorPanel.add(genderListBox);
        cityListBox.addItem("");
        service.getCities(new MethodCallback<List<String>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Error getCities");
            }

            @Override
            public void onSuccess(Method method, List<String> strings) {
                for (String city:strings) {
                    cityListBox.addItem(city);
                }
            }
        });
        cityListBox.setWidth(windowWidth/10+"px");
        cityListBox.removeStyleName("gwt-ListBox");
        cityListBox.addStyleName("listBox");
        selectorPanel.add(new Label("City:"));
        selectorPanel.add(cityListBox);
        /*int min=18;
        int max=100;*/
        for (int i = 18; i < 100; i++) {
            minAgeListBox.addItem(String.valueOf(i));
        }
        min=18;
        max=99;
        minAgeListBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                min = Integer.valueOf(minAgeListBox.getSelectedItemText());
                maxAgeListBox.clear();
                for (int i = 99; i >= min; i--) {
                    maxAgeListBox.addItem(String.valueOf(i));
                    maxAgeListBox.setSelectedIndex(99-max);
                }
            }
        });
        minAgeListBox.removeStyleName("gwt-ListBox");
        minAgeListBox.addStyleName("listBox");
        for (int i = 99; i > 17; i--) {
            maxAgeListBox.addItem(String.valueOf(i));
        }
        maxAgeListBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                max = Integer.valueOf(maxAgeListBox.getSelectedItemText());
                minAgeListBox.clear();
                for (int i = 18; i <= max; i++) {
                    minAgeListBox.addItem(String.valueOf(i));
                    minAgeListBox.setSelectedIndex(min-18);
                }
            }
        });
        maxAgeListBox.removeStyleName("gwt-ListBox");
        maxAgeListBox.addStyleName("listBox");
        selectorPanel.add(new Label("Age:"));
        HorizontalPanel ageHorizontalPanel = new HorizontalPanel();
        ageHorizontalPanel.setWidth(windowWidth/10+"px");
        ageHorizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        ageHorizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        ageHorizontalPanel.add(minAgeListBox);
        Label dash = new Label("  -  ");
        dash.addStyleName("dash");
        ageHorizontalPanel.add(dash);
        ageHorizontalPanel.add(maxAgeListBox);
        selectorPanel.add(ageHorizontalPanel);
        selectButton.setWidth(windowWidth/10+"px");
        selectButton.removeStyleName("gwt-Button");
        selectButton.addStyleName("selectButton");
        selectorPanel.add(selectButton);

        decSelectorPanel.add(selectorPanel);
        decSelectorPanel.removeStyleName("gwt-DecoratorPanel");
        decSelectorPanel.addStyleName("selectorPanel");

        selectButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                selector.setSortBy(sortByListBox.getSelectedItemText());
                selector.setCity(cityListBox.getSelectedItemText());
                selector.setGender(genderListBox.getSelectedItemText());
                selector.setMinAge(Integer.valueOf(minAgeListBox.getSelectedItemText()));
                selector.setMaxAge(Integer.valueOf(maxAgeListBox.getSelectedItemText()));

                usersRequest(1);
            }
        });




        selectorAndUsers.add(decSelectorPanel);
        selectorAndUsers.add(userPanel);

        usersVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        usersVerticalPanel.add(logOutButton);

        VerticalPanel usersWithoutLogout = new VerticalPanel();
        usersWithoutLogout.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        usersWithoutLogout.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        usersWithoutLogout.add(selectorAndUsers);
        usersWithoutLogout.setSize("100%", "100%");
        usersVerticalPanel.add(usersWithoutLogout);
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
                            drawMessages();
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
                int pages = (sizeList-1)/5+1;
                incomingMessages.clear();
                pagesPanelIncomingMessages.clear();
                incomingPanel.remove(incomingMessages);
                incomingPanel.remove(pagesPanelIncomingMessages);
                if(sizeList!=0) {
                    if (sizeList < 5) {
                        List<Message> messageList = messages.subList(0, sizeList);
                        addMessagesOnIncomingGrid(messageList);
                    } else {
                        List<Message> messageList = messages.subList(0, 5);
                        addMessagesOnIncomingGrid(messageList);
                    }

                    pagesPanelIncomingMessages.setSpacing(3);
                    for (int k = 1; k <= pages; k++) {
                        Button pageButton = new Button(String.valueOf(k));
                        pageButton.removeStyleName("gwt-Button");
                        pageButton.addStyleName("pageButton");
                        pageButton.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent clickEvent) {
                                incomingMessages.clear();
                                incomingPanel.remove(incomingMessages);
                                incomingPanel.remove(pagesPanelIncomingMessages);
                                int p = Integer.valueOf(pageButton.getText());
                                if (p < pages) {
                                    List<Message> messageList1 = messages.subList((p - 1) * 5, p * 5);
                                    addMessagesOnIncomingGrid(messageList1);
                                    incomingPanel.add(incomingMessages);
                                    incomingPanel.add(pagesPanelIncomingMessages);
                                } else {
                                    incomingMessages.clear();
                                    List<Message> messageList1 = messages.subList((p - 1) * 5, sizeList);
                                    addMessagesOnIncomingGrid(messageList1);
                                    incomingPanel.add(incomingMessages);
                                    incomingPanel.add(pagesPanelIncomingMessages);
                                }
                            }
                        });
                        pagesPanelIncomingMessages.add(pageButton);
                    }
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
                int pages = (sizeList-1)/5+1;
                outgoingMessages.clear();
                pagesPanelOutgoingMessages.clear();
                outgoingPanel.remove(outgoingMessages);
                outgoingPanel.remove(pagesPanelOutgoingMessages);
                if(sizeList!=0) {
                    if (sizeList < 5) {
                        List<Message> messageList = messages.subList(0, sizeList);
                        addMessagesOnOutgoingGrid(messageList);
                    } else {
                        List<Message> messageList = messages.subList(0, 5);
                        addMessagesOnOutgoingGrid(messageList);
                    }
                    pagesPanelOutgoingMessages.setSpacing(3);
                    for (int k = 1; k <= pages; k++) {
                        Button pageButton = new Button(String.valueOf(k));
                        pageButton.removeStyleName("gwt-Button");
                        pageButton.addStyleName("pageButton");
                        pageButton.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent clickEvent) {
                                outgoingMessages.clear();
                                outgoingPanel.remove(outgoingMessages);
                                outgoingPanel.remove(pagesPanelOutgoingMessages);
                                int p = Integer.valueOf(pageButton.getText());
                                if (p < pages) {
                                    List<Message> messageList1 = messages.subList((p - 1) * 5, p * 5);
                                    addMessagesOnOutgoingGrid(messageList1);
                                    outgoingPanel.add(outgoingMessages);
                                    outgoingPanel.add(pagesPanelOutgoingMessages);
                                } else {
                                    outgoingMessages.clear();
                                    List<Message> messageList1 = messages.subList((p - 1) * 5, sizeList);
                                    addMessagesOnOutgoingGrid(messageList1);
                                    outgoingPanel.add(outgoingMessages);
                                    outgoingPanel.add(pagesPanelOutgoingMessages);
                                }
                            }
                        });
                        pagesPanelOutgoingMessages.add(pageButton);
                    }
                }
                outgoingPanel.add(outgoingMessages);
                outgoingPanel.add(pagesPanelOutgoingMessages);
            }
        });
    }

    //type=1 перерисовка после селект
    private void usersRequest(int type){
        service.getAllUsersWithoutUsername(login, selector, new MethodCallback<List<UserInfo>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Error on load users");
            }

            @Override
            public void onSuccess(Method method, List<UserInfo> userInfos) {
                int sizeList = userInfos.size();
                int pages = (sizeList-1)/9+1;
                if(type==1){
                    gridAndPages.remove(users);
                    gridAndPages.remove(pagesPanel);
                    users.clear();
                    pagesPanel.clear();
                }
                if(sizeList!=0) {
                    if (sizeList < 9) {
                        List<UserInfo> userInfoList = userInfos.subList(0, sizeList);
                        addUsersOnGrid(userInfoList);
                    } else {
                        List<UserInfo> userInfoList = userInfos.subList(0, 9);
                        addUsersOnGrid(userInfoList);
                    }

                    pagesPanel.setSpacing(3);
                    for (int k = 1; k <= pages; k++) {
                        Button pageButton = new Button(String.valueOf(k));
                        pageButton.removeStyleName("gwt-Button");
                        pageButton.addStyleName("pageButton");
                        pageButton.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent clickEvent) {
                                gridAndPages.remove(users);
                                gridAndPages.remove(pagesPanel);
                                int p = Integer.valueOf(pageButton.getText());
                                if (p < pages) {
                                    users.clear();
                                    List<UserInfo> userInfoList = userInfos.subList((p - 1) * 9, p * 9);
                                    addUsersOnGrid(userInfoList);
                                    gridAndPages.add(users);
                                    gridAndPages.add(pagesPanel);
                                } else {
                                    users.clear();
                                    List<UserInfo> userInfoList = userInfos.subList((p - 1) * 9, sizeList);
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

                    //userPanel.setSize("100%", "100%");
                    userPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
                    userPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
                    userPanel.add(gridAndPages);
                }



            }
        });
    }
}
