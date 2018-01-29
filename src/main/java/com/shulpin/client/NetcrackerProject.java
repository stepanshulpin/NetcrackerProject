package com.shulpin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.shulpin.shared.model.MyResponse;
import com.shulpin.shared.model.User;
import com.shulpin.shared.model.UserInfo;
import org.fusesource.restygwt.client.Defaults;
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
                    Window.alert("Login is too short!");
                }
                else{
                    RegExp validReg = RegExp.compile("^[A-Za-z]");
                    if(!validReg.test(login)){
                        Window.alert("Login must contain only letters!");
                    }
                    else{
                        if(password.length()<4){
                            Window.alert("Password is too short!");
                        }
                        else{
                            service.findUser(new User(login, password), new MethodCallback<MyResponse>() {
                                @Override
                                public void onFailure(Method method, Throwable throwable) {
                                    Window.alert("Error send user!");
                                }

                                @Override
                                public void onSuccess(Method method, MyResponse myResponse) {
                                    if(myResponse.getResponse().equals("Not found")){
                                        Window.alert("Wrong username!");
                                    }
                                    else {
                                        if (myResponse.getResponse().equals("Wrong password")) {
                                            Window.alert("Wrong password");
                                        } else {
                                            if (myResponse.getResponse().equals("Correct password")) {
                                                RootPanel.get("content").remove(loginView.getMainPanel());
                                                MainView mainView = new MainView(login);
                                                RootPanel.get("content").add(mainView.getMainPanel());
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
                    Window.alert("Login is too short!");
                }
                else{
                    RegExp validReg = RegExp.compile("^[A-Za-z]");
                    if(!validReg.test(login)){
                        Window.alert("Login must contain only letters!");
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
                                        Window.alert("Password is too short!");
                                    }
                                    else{
                                        if(password.equals(confirmPassword)){
                                            if(city.length()==0){
                                                Window.alert("Enter the name of the city!");
                                            }
                                            else{
                                                service.saveUser(new User(login, password), new MethodCallback<MyResponse>() {
                                                    @Override
                                                    public void onFailure(Method method, Throwable throwable) {
                                                        Window.alert("Error save user!");
                                                    }

                                                    @Override
                                                    public void onSuccess(Method method, MyResponse myResponse) {
                                                        if(myResponse.getResponse().equals("Save user completed")) {
                                                            service.saveUserInfo(new UserInfo(login, gender, age, city), new MethodCallback<MyResponse>() {
                                                                @Override
                                                                public void onFailure(Method method, Throwable throwable) {
                                                                    Window.alert("Error save userInfo!");
                                                                }

                                                                @Override
                                                                public void onSuccess(Method method, MyResponse myResponse) {
                                                                    if(myResponse.getResponse().equals("Save userInfo completed")) {
                                                                        Window.alert("Account created");
                                                                        RootPanel.get("content").remove(registrationView.getMainPanel());
                                                                        RootPanel.get("content").add(loginView.getMainPanel());
                                                                    }
                                                                }
                                                            });



                                                        }
                                                    }
                                                });
                                            }
                                        }
                                        else{
                                            Window.alert("Passwords do not match!");
                                        }
                                    }
                                }
                                else{
                                    Window.alert("Such username already exists!");
                                }
                            }
                        });
                    }
                }
            }
        });



    }
}
