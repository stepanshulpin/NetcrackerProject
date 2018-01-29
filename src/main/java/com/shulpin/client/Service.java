package com.shulpin.client;


import com.shulpin.shared.model.Message;
import com.shulpin.shared.model.MyResponse;
import com.shulpin.shared.model.User;
import com.shulpin.shared.model.UserInfo;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.hibernate.annotations.GeneratorType;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("bein")
public interface Service extends RestService {


    //при логине проверка пользователя на наличие и правильность пароля
    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    void findUser(User user, MethodCallback<MyResponse> callback);

    @POST
    @Path("/userInfo/{username}")
    void findUserInfoId(@PathParam("username") String username, MethodCallback<Long> callback);

    @POST
    @Path("/saveUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    void saveUser(User user, MethodCallback<MyResponse> callback);

    @POST
    @Path("/saveUserInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    void saveUserInfo(UserInfo userInfo, MethodCallback<MyResponse> callback);

    @GET
    @Path("/users/{username}")
    void getAllUsersWithoutUsername(@PathParam("username") String username, MethodCallback<List<UserInfo>> callback);

    @GET
    @Path("/test")
    void test(MethodCallback callback);

    @POST
    @Path("/addMessage")
    void addMessage(Message message, MethodCallback<MyResponse> callback);

    @GET
    @Path("/messagesTo/{id}")
    void getMessagesFrom(@PathParam("id")Long id, MethodCallback<List<Message>> callback);

    @GET
    @Path("/messagesFrom/{id}")
    void getMessagesTo(@PathParam("id")Long id, MethodCallback<List<Message>> callback);

    @DELETE
    @Path("/deleteMessage")
    void deleteMessage(Message message, MethodCallback<MyResponse> callback);

    @GET
    @Path("userInfo/{id}")
    void findUserInfoById(@PathParam("id") Long id, MethodCallback<UserInfo> callback);

}
