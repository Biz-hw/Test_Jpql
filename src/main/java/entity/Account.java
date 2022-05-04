package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account", catalog = "test")
@Getter
@Setter
@ToString
@NamedQueries({
        @NamedQuery(name = "findAll", query = "SELECT a FROM Account a ORDER BY a.createdAt desc")
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "account_code")
    private String accountCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;


}
