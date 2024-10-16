package lk.ijse.gdse.aad.posusingspring.customObj.Impl;

import lk.ijse.gdse.aad.posusingspring.customObj.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerErrorResponse implements CustomerResponse {
    private int errorCode;
    private String errorMessage;
}
