package com.iavtar.musicApp.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Getter(onMethod_ = @JsonGetter)
@Setter(onMethod_ = @JsonSetter)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class UserSignupRequest {

    private String username;

    private String fullName;

    private String email;

    private String countryCode;

    private String mobileNumber;

    private String password;

}
