package com.shulpin.client;

import com.google.gwt.i18n.server.testing.Gender;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class RegistrationView extends Composite {

    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable registrationLayout = new FlexTable();
    private DecoratorPanel decPanel = new DecoratorPanel();


    private String headline = "Create account";
    private String usernameLabel = "Username: ";
    private String passwordLabel = "Password: ";
    private String passwordConfirmLabel = "Confirm password: ";
    private String headlinePersonalInformation = "Personal information";
    private String genderLabel = "Gender: ";
    private String ageLabel = "Age: ";
    private String cityLabel = "City: ";
    private TextBox username = new TextBox();
    private PasswordTextBox password = new PasswordTextBox();
    private PasswordTextBox passwordConfirm = new PasswordTextBox();
    private ListBox gender = new ListBox();
    private ListBox age = new ListBox();
    private TextBox city = new TextBox();
    private Button okButton = new Button("Ok");
    private Button backButton = new Button("Back");

    public RegistrationView() {

        int windowHeight = Window.getClientHeight();
        int windowWidth = Window.getClientWidth();


        registrationLayout.setCellSpacing(6);
        FlexTable.FlexCellFormatter cellFormatter = registrationLayout.getFlexCellFormatter();

        registrationLayout.setHTML(0, 0, this.headline);
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        username.setWidth("150px");
        password.setWidth("150px");
        passwordConfirm.setWidth("150px");
        city.setWidth("150px");

        registrationLayout.setHTML(1, 0, this.usernameLabel);
        registrationLayout.setWidget(1, 1, username);
        registrationLayout.setHTML(2, 0, passwordLabel);
        registrationLayout.setWidget(2, 1, password);
        registrationLayout.setHTML(3, 0, passwordConfirmLabel);
        registrationLayout.setWidget(3, 1, passwordConfirm);

        registrationLayout.setHTML(4, 0, this.headlinePersonalInformation);
        cellFormatter.setColSpan(4, 0, 2);
        cellFormatter.setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);

        gender.addItem("Female");
        gender.addItem("Male");
        registrationLayout.setHTML(5,0,genderLabel);
        registrationLayout.setWidget(5,1,gender);

        for(int i=18; i<100; i++)
            age.addItem(String.valueOf(i));
        registrationLayout.setHTML(6,0,ageLabel);
        registrationLayout.setWidget(6,1,age);

        registrationLayout.setHTML(7,0,cityLabel);
        registrationLayout.setWidget(7,1,city);

        okButton.setWidth("75px");
        registrationLayout.setWidget(8, 0, okButton);
        cellFormatter.setColSpan(8, 0, 2);
        cellFormatter.setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_CENTER);

        backButton.setWidth("75px");
        registrationLayout.setWidget(9, 0, backButton);
        cellFormatter.setColSpan(9, 0, 2);
        cellFormatter.setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_CENTER);

        decPanel.setWidget(registrationLayout);

        mainPanel.setWidth(windowWidth + "px");
        mainPanel.setHeight(windowHeight + "px");
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        mainPanel.add(decPanel);

    }

    public VerticalPanel getMainPanel() {
        return mainPanel;
    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public TextBox getUsername() {
        return username;
    }

    public PasswordTextBox getPassword() {
        return password;
    }

    public PasswordTextBox getPasswordConfirm() {
        return passwordConfirm;
    }

    public ListBox getGender() {
        return gender;
    }

    public ListBox getAge() {
        return age;
    }

    public TextBox getCity() {
        return city;
    }
}
