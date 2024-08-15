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
public class AnalysisResultDTO {
    private Integer loanId;
    private String name;
    private BigDecimal loanAmount;
    private BigDecimal income;
    private int creditScore;
    private int age;
    private int loanTerm;
    private BigDecimal loanToIncomeRatio;
    private BigDecimal collateralValue;
    private BigDecimal eligibilityScore;
    private String riskLevel;
}
