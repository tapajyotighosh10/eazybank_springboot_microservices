package com.eazybank.accounts_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "ResponseDto",
        description = "Response object"
)
@Data
@AllArgsConstructor
public class ResponseDto {

    @Schema(
        description = "Status code of the response"
    )
    private String statusCode;
    @Schema(
        description = "Status message of the response"
    )
    private String statusMessage;
}
