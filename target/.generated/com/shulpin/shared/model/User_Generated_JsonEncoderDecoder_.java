package com.shulpin.shared.model;

public class User_Generated_JsonEncoderDecoder_ extends org.fusesource.restygwt.client.AbstractJsonEncoderDecoder<com.shulpin.shared.model.User> {
  
  public static final User_Generated_JsonEncoderDecoder_ INSTANCE = new User_Generated_JsonEncoderDecoder_();
  
  public com.google.gwt.json.client.JSONValue encode(com.shulpin.shared.model.User value) {
    if( value==null ) {
      return getNullType();
    }
    com.google.gwt.json.client.JSONObject rc = new com.google.gwt.json.client.JSONObject();
    com.shulpin.shared.model.User parseValue = (com.shulpin.shared.model.User)value;
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.encode(parseValue.getId()), rc, "id");
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.getUserName()), rc, "userName");
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.getUserPassword()), rc, "userPassword");
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.getConfirmUserPassword()), rc, "confirmUserPassword");
    return rc;
  }
  
  public com.shulpin.shared.model.User decode(com.google.gwt.json.client.JSONValue value) {
    if( value == null || value.isNull()!=null ) {
      return null;
    }
    com.google.gwt.json.client.JSONObject object = toObject(value);
    com.shulpin.shared.model.User rc = new com.shulpin.shared.model.User();
    rc.setId(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.decode(object.get("id")), null));
    rc.setUserName(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("userName")), null));
    rc.setUserPassword(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("userPassword")), null));
    rc.setConfirmUserPassword(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("confirmUserPassword")), null));
    return rc;
  }
  
}
