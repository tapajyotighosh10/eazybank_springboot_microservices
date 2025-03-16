package com.eazybank.accounts_service.controller;

import com.eazybank.accounts_service.dto.CustomerDetailsDto;
import com.eazybank.accounts_service.dto.ErrorResponseDto;
import com.eazybank.accounts_service.service.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST API for Customers in EazyBank",
        description = "REST APIs in EazyBank to FETCH customer details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }


    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("eazybank-correlation-id") String correlationId,
                                                                   @RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                   String mobileNumber){
        logger.debug("eazyBank-correlation-id found: {} ", correlationId);
        CustomerDetailsDto customerDetailsDto = customerServiceImpl.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);

    }

}
