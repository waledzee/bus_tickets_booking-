package Entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter


@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;   // e.g. "BUS-101"

    private int capacity;

    private String kind; // e.g. "AC", "Mini", "Luxury"


}
