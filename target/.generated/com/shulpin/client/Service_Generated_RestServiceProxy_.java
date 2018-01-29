package com.shulpin.client;

public class Service_Generated_RestServiceProxy_ implements com.shulpin.client.Service, org.fusesource.restygwt.client.RestServiceProxy {
  private org.fusesource.restygwt.client.Resource resource = null;
  
  public void setResource(org.fusesource.restygwt.client.Resource resource) {
    this.resource = resource;
  }
  public org.fusesource.restygwt.client.Resource getResource() {
    if (this.resource == null) {
      String serviceRoot = org.fusesource.restygwt.client.Defaults.getServiceRoot();
      this.resource = new org.fusesource.restygwt.client.Resource(serviceRoot).resolve("bein");
    }
    return this.resource;
  }
  private org.fusesource.restygwt.client.Dispatcher dispatcher = null;
  
  public void setDispatcher(org.fusesource.restygwt.client.Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }
  
  public org.fusesource.restygwt.client.Dispatcher getDispatcher() {
    return this.dispatcher;
  }
  public void findUser(com.shulpin.shared.model.User user, org.fusesource.restygwt.client.MethodCallback<com.shulpin.shared.model.MyResponse> callback) {
    final com.shulpin.shared.model.User final_user = user;
    final org.fusesource.restygwt.client.Method __method =
    getResource()
    .resolve("/user")
    .post();
    __method.setDispatcher(this.dispatcher);
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_ACCEPT, "application/json");
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_CONTENT_TYPE, "application/json");
    __method.json(com.shulpin.shared.model.User_Generated_JsonEncoderDecoder_.INSTANCE.encode(user));
    try {
      __method.send(new org.fusesource.restygwt.client.AbstractRequestCallback<com.shulpin.shared.model.MyResponse>(__method, callback) {
        protected com.shulpin.shared.model.MyResponse parseResult() throws Exception {
          try {
            return com.shulpin.shared.model.MyResponse_Generated_JsonEncoderDecoder_.INSTANCE.decode(com.google.gwt.json.client.JSONParser.parse(__method.getResponse().getText()));
          } catch (Throwable __e) {
            throw new org.fusesource.restygwt.client.ResponseFormatException("Response was NOT a valid JSON document", __e);
          }
        }
      });
    } catch (com.google.gwt.http.client.RequestException __e) {
      callback.onFailure(__method,__e);
    }
  }
  public void getAllUsersWithoutUsername(java.lang.String username, org.fusesource.restygwt.client.MethodCallback<java.util.List<com.shulpin.shared.model.UserInfo>> callback) {
    final java.lang.String final_username = username;
    final org.fusesource.restygwt.client.Method __method =
    getResource()
    .resolve("/users/"+(username== null? null : com.google.gwt.http.client.URL.encodePathSegment(username))+"")
    .get();
    __method.setDispatcher(this.dispatcher);
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_ACCEPT, org.fusesource.restygwt.client.Resource.CONTENT_TYPE_JSON);
    try {
      __method.send(new org.fusesource.restygwt.client.AbstractRequestCallback<java.util.List<com.shulpin.shared.model.UserInfo>>(__method, callback) {
        protected java.util.List<com.shulpin.shared.model.UserInfo> parseResult() throws Exception {
          try {
            return org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.toList(com.google.gwt.json.client.JSONParser.parse(__method.getResponse().getText()), com.shulpin.shared.model.UserInfo_Generated_JsonEncoderDecoder_.INSTANCE);
          } catch (Throwable __e) {
            throw new org.fusesource.restygwt.client.ResponseFormatException("Response was NOT a valid JSON document", __e);
          }
        }
      });
    } catch (com.google.gwt.http.client.RequestException __e) {
      callback.onFailure(__method,__e);
    }
  }
  public void saveUser(com.shulpin.shared.model.User user, org.fusesource.restygwt.client.MethodCallback<com.shulpin.shared.model.MyResponse> callback) {
    final com.shulpin.shared.model.User final_user = user;
    final org.fusesource.restygwt.client.Method __method =
    getResource()
    .resolve("/saveUser")
    .post();
    __method.setDispatcher(this.dispatcher);
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_ACCEPT, "application/json");
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_CONTENT_TYPE, "application/json");
    __method.json(com.shulpin.shared.model.User_Generated_JsonEncoderDecoder_.INSTANCE.encode(user));
    try {
      __method.send(new org.fusesource.restygwt.client.AbstractRequestCallback<com.shulpin.shared.model.MyResponse>(__method, callback) {
        protected com.shulpin.shared.model.MyResponse parseResult() throws Exception {
          try {
            return com.shulpin.shared.model.MyResponse_Generated_JsonEncoderDecoder_.INSTANCE.decode(com.google.gwt.json.client.JSONParser.parse(__method.getResponse().getText()));
          } catch (Throwable __e) {
            throw new org.fusesource.restygwt.client.ResponseFormatException("Response was NOT a valid JSON document", __e);
          }
        }
      });
    } catch (com.google.gwt.http.client.RequestException __e) {
      callback.onFailure(__method,__e);
    }
  }
  public void saveUserInfo(com.shulpin.shared.model.UserInfo userInfo, org.fusesource.restygwt.client.MethodCallback<com.shulpin.shared.model.MyResponse> callback) {
    final com.shulpin.shared.model.UserInfo final_userInfo = userInfo;
    final org.fusesource.restygwt.client.Method __method =
    getResource()
    .resolve("/saveUserInfo")
    .post();
    __method.setDispatcher(this.dispatcher);
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_ACCEPT, "application/json");
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_CONTENT_TYPE, "application/json");
    __method.json(com.shulpin.shared.model.UserInfo_Generated_JsonEncoderDecoder_.INSTANCE.encode(userInfo));
    try {
      __method.send(new org.fusesource.restygwt.client.AbstractRequestCallback<com.shulpin.shared.model.MyResponse>(__method, callback) {
        protected com.shulpin.shared.model.MyResponse parseResult() throws Exception {
          try {
            return com.shulpin.shared.model.MyResponse_Generated_JsonEncoderDecoder_.INSTANCE.decode(com.google.gwt.json.client.JSONParser.parse(__method.getResponse().getText()));
          } catch (Throwable __e) {
            throw new org.fusesource.restygwt.client.ResponseFormatException("Response was NOT a valid JSON document", __e);
          }
        }
      });
    } catch (com.google.gwt.http.client.RequestException __e) {
      callback.onFailure(__method,__e);
    }
  }
  public void test(org.fusesource.restygwt.client.MethodCallback callback) {
    final org.fusesource.restygwt.client.Method __method =
    getResource()
    .resolve("/test")
    .get();
    __method.setDispatcher(this.dispatcher);
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_ACCEPT, org.fusesource.restygwt.client.Resource.CONTENT_TYPE_JSON);
    try {
      __method.send(new org.fusesource.restygwt.client.AbstractRequestCallback<java.lang.Object>(__method, callback) {
        protected java.lang.Object parseResult() throws Exception {
          try {
            return org.fusesource.restygwt.client.ObjectEncoderDecoder.INSTANCE.decode(com.google.gwt.json.client.JSONParser.parse(__method.getResponse().getText()));
          } catch (Throwable __e) {
            throw new org.fusesource.restygwt.client.ResponseFormatException("Response was NOT a valid JSON document", __e);
          }
        }
      });
    } catch (com.google.gwt.http.client.RequestException __e) {
      callback.onFailure(__method,__e);
    }
  }
}
