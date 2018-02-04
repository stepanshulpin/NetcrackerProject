package com.shulpin.client;


import com.github.nmorel.gwtjackson.client.utils.Base64Utils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.shulpin.shared.model.UserInfo;

public class UserView extends Composite {

    private UserInfo userInfo;
    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable userLayout = new FlexTable();
    private HorizontalPanel imagePanel = new HorizontalPanel();
    private HorizontalPanel imageAndInfo = new HorizontalPanel();
    private Image image = new Image();
    private DecoratorPanel decPanel = new DecoratorPanel();
    private Button write = new Button("write");

    public UserView(UserInfo userInfo) {
        this.userInfo = userInfo;

        int windowHeight = Window.getClientHeight();
        int windowWidth = Window.getClientWidth();
        String base64 = Base64Utils.toBase64(userInfo.getImage());
        image.setUrl("data:image/png;base64," + base64);
        userLayout.setCellSpacing(6);
        FlexTable.FlexCellFormatter cellFormatter = userLayout.getFlexCellFormatter();

        int imgHeight = image.getHeight();
        int imgWidth = image.getWidth();
        double scale = Math.min((double) (windowWidth/8)/(double) imgWidth,(double) (windowHeight/4)/(double) imgHeight);
        image.setSize(imgWidth*scale+"px",imgHeight*scale+"px");

        userLayout.setHTML(0, 0, userInfo.getName());
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        userLayout.setHTML(1,0,"Gender:");
        userLayout.setHTML(1,1,userInfo.getGender());
        cellFormatter.setWidth(1,0,"30%");
        cellFormatter.setWidth(1,1,"70%");
        cellFormatter.setHorizontalAlignment(1,0,HasHorizontalAlignment.ALIGN_RIGHT);
        cellFormatter.setHorizontalAlignment(1,1,HasHorizontalAlignment.ALIGN_LEFT);

        userLayout.setHTML(2,0,"Age:");
        userLayout.setHTML(2,1,String.valueOf(userInfo.getAge()));
        cellFormatter.setWidth(2,0,"30%");
        cellFormatter.setWidth(2,1,"70%");
        cellFormatter.setHorizontalAlignment(2,0,HasHorizontalAlignment.ALIGN_RIGHT);
        cellFormatter.setHorizontalAlignment(2,1,HasHorizontalAlignment.ALIGN_LEFT);

        userLayout.setHTML(3,0,"City:");
        userLayout.setHTML(3,1,userInfo.getCity());
        cellFormatter.setWidth(3,0,"30%");
        cellFormatter.setWidth(3,1,"70%");
        cellFormatter.setHorizontalAlignment(3,0,HasHorizontalAlignment.ALIGN_RIGHT);
        cellFormatter.setHorizontalAlignment(3,1,HasHorizontalAlignment.ALIGN_LEFT);

        userLayout.setWidget(4,0, write);
        cellFormatter.setColSpan(4, 0, 2);
        cellFormatter.setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);

        imagePanel.setSize(windowWidth/8+"px",windowHeight/4+"px");
        imagePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        imagePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        imagePanel.add(image);


        imageAndInfo.add(imagePanel);
        imageAndInfo.add(userLayout);
        decPanel.setWidget(imageAndInfo);

        userLayout.setWidth(windowWidth/8+"px");
        userLayout.setHeight(windowHeight/4+"px");
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        mainPanel.add(decPanel);

    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public VerticalPanel getMainPanel() {
        return mainPanel;
    }

    public Button getWrite() {
        return write;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
