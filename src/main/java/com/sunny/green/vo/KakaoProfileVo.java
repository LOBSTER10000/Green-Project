package com.sunny.green.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfileVo {

    public String id;
    public String connectedAt;
    public Kakao_Account Kakao_account;
    public Properties properties;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Kakao_Account{
        public String email;
        /*
         * public Boolean hasEmail;
         * public Boolean emailNeedsAgreement;
         * public Boolean isEmailValid;
         * public Boolean isEmailVerified;
         * public Boolean hasAgeRange;
         * public Boolean ageRangeNeedsAgreement;
         * public Boolean hasBirthday;
         * public Boolean birthdayNeedsAgreement;
         */

    };

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Properties{
        public String nickname;

    }

}


