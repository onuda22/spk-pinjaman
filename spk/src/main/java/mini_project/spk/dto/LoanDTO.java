package mini_project.spk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    private Integer customerId;
    private BigDecimal loanAmount;
    private Integer loanTerm;
    private String collateralType;
    private BigDecimal collateralValue;
}
