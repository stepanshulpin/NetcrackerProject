package com.shulpin.client;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class LoginView extends Composite{

    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable loginLayout = new FlexTable();
    private DecoratorPanel decPanel = new DecoratorPanel();

    private VerticalPanel labelAndGrid = new VerticalPanel();
    private Label validLabel = new Label("");
    private String headline = "Log into";
    private String usernameLabel = "Username: ";
    private String passwordLabel = "Password: ";
    private TextBox username = new TextBox();
    private PasswordTextBox password = new PasswordTextBox();
    private Button loginButton = new Button("Login");
    private Button registrationButton = new Button("Create account");

    public LoginView (){

        int windowHeight = Window.getClientHeight();
        int windowWidth = Window.getClientWidth();

        validLabel.setStyleName("redText");

        loginLayout.setCellSpacing(6);
        FlexCellFormatter cellFormatter = loginLayout.getFlexCellFormatter();

        loginLayout.setHTML(0, 0, this.headline);
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        username.setWidth("150px");
        password.setWidth("150px");
        loginLayout.setHTML(1, 0, this.usernameLabel);
        loginLayout.setWidget(1, 1, username);
        loginLayout.setHTML(2, 0, passwordLabel);
        loginLayout.setWidget(2, 1, password);


        loginLayout.setWidget(3, 0, loginButton);
        cellFormatter.setColSpan(3, 0, 2);
        cellFormatter.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
        loginLayout.setWidget(4, 0, registrationButton);
        cellFormatter.setColSpan(4, 0, 2);
        cellFormatter.setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);

        decPanel.setWidget(loginLayout);


        mainPanel.setWidth(windowWidth + "px");
        mainPanel.setHeight(windowHeight + "px");
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        labelAndGrid.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelAndGrid.add(validLabel);
        labelAndGrid.add(decPanel);
        mainPanel.add(labelAndGrid);
    }

    public VerticalPanel getMainPanel() {
        return mainPanel;
    }

    public TextBox getUsername() {
        return username;
    }

    public PasswordTextBox getPassword() {
        return password;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegistrationButton() {
        return registrationButton;
    }

    public Label getValidLabel() {
        return validLabel;
    }
}
