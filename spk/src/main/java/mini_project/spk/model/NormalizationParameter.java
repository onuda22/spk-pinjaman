package mini_project.spk.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "normalization_params")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NormalizationParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String paramName;
    private BigDecimal minValue;
    private BigDecimal maxValue;
}
