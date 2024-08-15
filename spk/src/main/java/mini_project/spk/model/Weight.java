package mini_project.spk.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "weight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String variableName;
    private BigDecimal weight;
}
