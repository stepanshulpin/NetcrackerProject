package com.shulpin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.server.testing.Gender;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class RegistrationView extends Composite {

    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable registrationLayout = new FlexTable();
    private DecoratorPanel decPanel = new DecoratorPanel();
    private VerticalPanel labelAndGrid = new VerticalPanel();
    private Label validLabel = new Label("");

    private String headline = "Create account";
    private String usernameLabel = "Username: ";
    private String passwordLabel = "Password: ";
    private String passwordConfirmLabel = "Confirm password: ";
    private String headlinePersonalInformation = "Personal information";
    private String genderLabel = "Gender: ";
    private String ageLabel = "Age: ";
    private String cityLabel = "City: ";
    private String imageLabel ="Image: ";
    private TextBox username = new TextBox();
    private PasswordTextBox password = new PasswordTextBox();
    private PasswordTextBox passwordConfirm = new PasswordTextBox();
    private ListBox gender = new ListBox();
    private ListBox age = new ListBox();
    private TextBox city = new TextBox();
    private FormPanel loadFile = new FormPanel();
    private FileUpload loadImage = new FileUpload();
    private Button okButton = new Button("Ok");
    private Button backButton = new Button("Back");

    public RegistrationView() {

        int windowHeight = Window.getClientHeight();
        int windowWidth = Window.getClientWidth();

        mainPanel.setStyleName("container");
        decPanel.setStyleName("decPanel");
        validLabel.setStyleName("redText");

        loadFile.setAction("/NetcrackerProject-1.0-SNAPSHOT/NetcrackerProject/bein/loadFile");
        // set form to use the POST method, and multipart MIME encoding.
        loadFile.setEncoding(FormPanel.ENCODING_MULTIPART);
        loadFile.setMethod(FormPanel.METHOD_POST);
        loadImage.setName("file");
        registrationLayout.setCellSpacing(6);
        FlexTable.FlexCellFormatter cellFormatter = registrationLayout.getFlexCellFormatter();

        registrationLayout.setHTML(0, 0, this.headline);
        cellFormatter.setStyleName(0,0,"header");
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        username.setWidth(windowWidth/4+"px");
        password.setWidth(windowWidth/4+"px");
        passwordConfirm.setWidth(windowWidth/4+"px");
        city.setWidth(windowWidth/4+"px");
        gender.setWidth(windowWidth/4+"px");
        age.setWidth(windowWidth/4+"px");

        registrationLayout.setHTML(1, 0, this.usernameLabel);
        username.removeStyleName("gwt-TextBox");
        username.setStyleName("textBox");
        registrationLayout.setWidget(1, 1, username);
        registrationLayout.setHTML(2, 0, passwordLabel);
        password.removeStyleName("gwt-PasswordTextBox");
        password.setStyleName("passwordBox");
        registrationLayout.setWidget(2, 1, password);
        registrationLayout.setHTML(3, 0, passwordConfirmLabel);
        passwordConfirm.removeStyleName("gwt-PasswordTextBox");
        passwordConfirm.setStyleName("passwordBox");
        registrationLayout.setWidget(3, 1, passwordConfirm);

        registrationLayout.setHTML(4, 0, this.headlinePersonalInformation);
        cellFormatter.setStyleName(4,0,"headerSmall");
        cellFormatter.setColSpan(4, 0, 2);
        cellFormatter.setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);

        gender.addItem("Female");
        gender.addItem("Male");
        gender.removeStyleName("gwt-ListBox");
        gender.setStyleName("listBox");
        registrationLayout.setHTML(5,0,genderLabel);
        registrationLayout.setWidget(5,1,gender);

        for(int i=18; i<100; i++)
            age.addItem(String.valueOf(i));
        age.removeStyleName("gwt-ListBox");
        age.setStyleName("listBox");
        registrationLayout.setHTML(6,0,ageLabel);
        registrationLayout.setWidget(6,1,age);

        registrationLayout.setHTML(7,0,cityLabel);
        city.removeStyleName("gwt-TextBox");
        city.setStyleName("smallTextBox");
        registrationLayout.setWidget(7,1,city);

        loadImage.setWidth(windowWidth/4+"px");

        VerticalPanel upload = new VerticalPanel();
        upload.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        Label uploadLabel = new Label("Upload");
        uploadLabel.setWidth("100%");
        upload.add(uploadLabel);
        upload.add(loadImage);
        loadFile.add(upload);
        upload.setStyleName("file_upload");
        //loadImage.removeStyleName("gwt-FileUpload");
        registrationLayout.setHTML(8,0,imageLabel);
        registrationLayout.setWidget(8,1,loadFile);


        okButton.removeStyleName("gwt-Button");
        okButton.addStyleName("loginButton");
        registrationLayout.setWidget(9, 0, okButton);
        cellFormatter.setColSpan(9, 0, 2);
        cellFormatter.setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_CENTER);


        backButton.removeStyleName("gwt-Button");
        backButton.addStyleName("loginButton");
        registrationLayout.setWidget(10, 0, backButton);
        cellFormatter.setColSpan(10, 0, 2);
        cellFormatter.setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_CENTER);


        decPanel.setWidget(registrationLayout);




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

    public Label getValidLabel() {
        return validLabel;
    }

    public FormPanel getLoadFile() {
        return loadFile;
    }

    public FileUpload getLoadImage() {
        return loadImage;
    }
}
