package mini_project.spk.model;

import jakarta.persistence.*;
import lombok.*;
import mini_project.spk.model.enums.RiskLevel;

import java.math.BigDecimal;

@Entity
@Table(name = "analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    private BigDecimal loanToIncomeRatio;
    private BigDecimal eligibilityScore;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
}
