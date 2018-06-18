package io.skalogs.skaetl.generator.credit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditDBData {
    private String type;
    private String microService;
    private String timestamp;
    private String typeRequest;
    private String database;
    private String request;
    private String timeRequestMs;
    private String service;
    private String requestId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String provider;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer amount ;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer creditDuration ;
}