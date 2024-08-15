package mini_project.spk.model;

import jakarta.persistence.*;
import lombok.*;
import mini_project.spk.model.enums.CharacterRating;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer age;
    private BigDecimal income;
    private Integer creditScore;

    @Enumerated(EnumType.STRING)
    private CharacterRating characterRating;
}
