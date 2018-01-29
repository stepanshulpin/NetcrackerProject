package com.shulpin.shared.model;

public class MyResponse_Generated_JsonEncoderDecoder_ extends org.fusesource.restygwt.client.AbstractJsonEncoderDecoder<com.shulpin.shared.model.MyResponse> {
  
  public static final MyResponse_Generated_JsonEncoderDecoder_ INSTANCE = new MyResponse_Generated_JsonEncoderDecoder_();
  
  public com.google.gwt.json.client.JSONValue encode(com.shulpin.shared.model.MyResponse value) {
    if( value==null ) {
      return getNullType();
    }
    com.google.gwt.json.client.JSONObject rc = new com.google.gwt.json.client.JSONObject();
    com.shulpin.shared.model.MyResponse parseValue = (com.shulpin.shared.model.MyResponse)value;
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.getResponse()), rc, "response");
    return rc;
  }
  
  public com.shulpin.shared.model.MyResponse decode(com.google.gwt.json.client.JSONValue value) {
    if( value == null || value.isNull()!=null ) {
      return null;
    }
    com.google.gwt.json.client.JSONObject object = toObject(value);
    com.shulpin.shared.model.MyResponse rc = new com.shulpin.shared.model.MyResponse();
    rc.setResponse(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("response")), null));
    return rc;
  }
  
}
