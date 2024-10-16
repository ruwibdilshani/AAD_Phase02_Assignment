package lk.ijse.gdse.aad.posusingspring.customObj.Impl;

import lk.ijse.gdse.aad.posusingspring.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemErrorResponse implements ItemResponse {
    private int errorCode;
    private String errorMessage;
}
