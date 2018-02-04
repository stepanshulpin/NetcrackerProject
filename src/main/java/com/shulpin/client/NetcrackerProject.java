package com.shulpin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.shulpin.shared.model.MyResponse;
import com.shulpin.shared.model.User;
import com.shulpin.shared.model.UserInfo;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;


public class NetcrackerProject implements EntryPoint {

    private LoginView loginView = new LoginView();
    private RegistrationView registrationView = new RegistrationView();
    private Service service = GWT.create(Service.class);


    public void onModuleLoad() {

        /*String root = Defaults.getServiceRoot();
        root = root.replace("bein/", "");
        Defaults.setServiceRoot(root);*/

        RootPanel.get("content").add(loginView.getMainPanel());
        loginView.getRegistrationButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                RootPanel.get("content").remove(loginView.getMainPanel());
                RootPanel.get("content").add(registrationView.getMainPanel());
            }
        });

        loginView.getLoginButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String login = loginView.getUsername().getText();
                String password = loginView.getPassword().getText();
                if(login.length()<4){
                    validationLogin(0,"Login is too short!");
                }
                else{

                    RegExp validReg = RegExp.compile("^[A-Za-z]");
                    if(!validReg.test(login)){
                        validationLogin(0,"Login must contain only letters!");
                    }
                    else{
                        if(password.length()<4){
                            validationLogin(1, "Password is too short!");
                        }
                        else{
                            service.findUser(new User(login, password), new MethodCallback<MyResponse>() {
                                MainView mainView;
                                class LogOutClickHandler implements ClickHandler {
                                    @Override
                                    public void onClick(ClickEvent clickEvent) {
                                        RootPanel.get("content").remove(mainView.getMainPanel());
                                        RootPanel.get("content").add(loginView.getMainPanel());
                                    }
                                }

                                @Override
                                public void onFailure(Method method, Throwable throwable) {
                                    Window.alert("Error send user!");
                                }

                                @Override
                                public void onSuccess(Method method, MyResponse myResponse) {
                                    if(myResponse.getResponse().equals("Not found")){
                                        validationLogin(0,"Wrong username!");
                                    }
                                    else {
                                        if (myResponse.getResponse().equals("Wrong password!")) {
                                            validationLogin(1,"Wrong password");
                                        } else {
                                            if (myResponse.getResponse().equals("Correct password")) {
                                                validationLogin(-1,"");
                                                RootPanel.get("content").remove(loginView.getMainPanel());
                                                mainView = new MainView(login);
                                                RootPanel.get("content").add(mainView.getMainPanel());
                                                mainView.getLogOutButton().addClickHandler(new LogOutClickHandler());
                                                mainView.getLogOutButtonIn().addClickHandler(new LogOutClickHandler());
                                                mainView.getLogOutButtonOut().addClickHandler(new LogOutClickHandler());



                                            }
                                        }
                                    }
                                }
                            });

                        }
                    }
                }
            }
        });

        registrationView.getOkButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String login = registrationView.getUsername().getText();
                String password = registrationView.getPassword().getText();
                String confirmPassword = registrationView.getPasswordConfirm().getText();
                String gender = registrationView.getGender().getSelectedItemText();
                Integer age = Integer.valueOf(registrationView.getAge().getSelectedItemText());
                String city = registrationView.getCity().getText();
                if(login.length()<4){
                    validationRegistration(0,"Login is too short!");
                }
                else{
                    RegExp validReg = RegExp.compile("^[A-Za-z]");
                    if(!validReg.test(login)){
                        validationRegistration(0,"Login must contain only letters!");
                    }
                    else{
                        service.findUser(new User(login, ""), new MethodCallback<MyResponse>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {
                                Window.alert("Error send user!");
                            }

                            @Override
                            public void onSuccess(Method method, MyResponse myResponse) {
                                if(myResponse.getResponse().equals("Not found")){
                                    if(password.length()<4){
                                        validationRegistration(1,"Password is too short!");
                                    }
                                    else{
                                        if(password.equals(confirmPassword)){
                                            if(city.length()==0){
                                                validationRegistration(3,"Enter the name of the city!");
                                            }
                                            else{

                                                if(registrationView.getLoadImage().getFilename().length()==0){
                                                    validationRegistration(4, "No File Specified!");
                                                }
                                                else{
                                                    String extension="";
                                                    String fileName = registrationView.getLoadImage().getFilename();
                                                    if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                                                        extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                                                    }
                                                    if(extension.equals("png")||extension.equals("jpg")||extension.equals("bmp")){
                                                        service.saveUser(new User(login, password), new MethodCallback<MyResponse>() {
                                                            @Override
                                                            public void onFailure(Method method, Throwable throwable) {
                                                                Window.alert("Error save user!");
                                                            }

                                                            @Override
                                                            public void onSuccess(Method method, MyResponse myResponse) {
                                                                if(myResponse.getResponse().equals("Save user completed")) {
                                                                    service.saveUserInfo(new UserInfo(login, gender, age, city, null), new MethodCallback<MyResponse>() {
                                                                        @Override
                                                                        public void onFailure(Method method, Throwable throwable) {
                                                                            Window.alert("Error save userInfo!");
                                                                        }

                                                                        @Override
                                                                        public void onSuccess(Method method, MyResponse myResponse) {
                                                                            if(myResponse.getResponse().equals("Save userInfo completed")) {
                                                                                registrationView.getLoadFile().submit();
                                                                                registrationView.getLoadFile().addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
                                                                                    @Override
                                                                                    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                                                                                        Window.alert("Account created");
                                                                                        RootPanel.get("content").remove(registrationView.getMainPanel());
                                                                                        RootPanel.get("content").add(loginView.getMainPanel());
                                                                                    }
                                                                                });
                                                                            }
                                                                        }
                                                                    });



                                                                }
                                                            }
                                                        });
                                                    }
                                                    else{
                                                        validationRegistration(4,"You must select the image!");
                                                    }
                                                }



                                            }
                                        }
                                        else{
                                            validationRegistration(2,"Passwords do not match!");
                                        }
                                    }
                                }
                                else{
                                    validationRegistration(0,"Such username already exists!");
                                }
                            }
                        });
                    }
                }
            }
        });

        registrationView.getBackButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                RootPanel.get("content").remove(registrationView.getMainPanel());
                RootPanel.get("content").add(loginView.getMainPanel());
            }
        });



    }


    public void validationLogin(int textBox, String msg){
        loginView.getUsername().removeStyleName("redOutline");
        loginView.getPassword().removeStyleName("redOutline");
        loginView.getValidLabel().setText("");
        //textBox==1 password
        //textBox==0 login
        //textBox==-1 valid
        if(textBox==0){
            loginView.getUsername().addStyleName("redOutline");
        }
        if(textBox==1){
            loginView.getPassword().addStyleName("redOutline");
        }
        loginView.getValidLabel().setText(msg);
    }

    public void validationRegistration(int textBox, String msg){
        //textBox==0 login
        //textBox==1 password
        //textBox==2 confirmPassword
        //textBox==3 city
        //textBox==4 fileUpload
        //textBox==-1 valid
        registrationView.getUsername().removeStyleName("redOutline");
        registrationView.getPassword().removeStyleName("redOutline");
        registrationView.getPasswordConfirm().removeStyleName("redOutline");
        registrationView.getCity().removeStyleName("redOutline");
        registrationView.getLoadImage().removeStyleName("redOutline");

        if(textBox==0){
            registrationView.getUsername().addStyleName("redOutline");
        }
        if(textBox==1){
            registrationView.getPassword().addStyleName("redOutline");
        }
        if(textBox==2){
            registrationView.getPasswordConfirm().addStyleName("redOutline");
        }
        if(textBox==3){
            registrationView.getCity().addStyleName("redOutline");
        }
        if(textBox==4){
            registrationView.getLoadImage().addStyleName("redOutline");
        }
        registrationView.getValidLabel().setText(msg);

    }

}
