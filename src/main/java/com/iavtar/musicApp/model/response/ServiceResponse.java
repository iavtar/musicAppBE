package com.iavtar.musicApp.model.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter(onMethod_ = @JsonGetter)
@Setter(onMethod_ = @JsonSetter)
@Builder
public class ServiceResponse {

  private String message;
  private String code;

}
