package pl.codeme.jeeb.e2.bank.backend.model;

import java.io.Serializable;
import javax.persistence.*;

import pl.codeme.jeeb.e2.bank.backend.db.QuerisNames;
import pl.codeme.jeeb.e2.bank.backend.dto.Account;

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "accountMapper",
                classes = {
                        @ConstructorResult(
                                targetClass = Account.class,
                                columns = {
                                        @ColumnResult(name = "account_number", type = String.class),
                                        @ColumnResult(name = "customer", type = String.class),
                                        @ColumnResult(name = "amount", type = Double.class)
                                }
                        )
                }
        )
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = QuerisNames.ACCOUNT_BY_CN,
                query = "" +
                        "SELECT a.account_number, a.amount, concat(cu.name, ' ', cu.surname) customer " +
                        "FROM " +
                        "  accounts a INNER JOIN " +
                        "  customers cu ON cu.id = a.customer_id INNER JOIN " +
                        "  cards c ON c.account_id = a.id " +
                        "WHERE c.card_number = :cn",
                resultSetMapping = "accountMapper"
        )
})
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED COMMENT 'Identyfikator tabeli'")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
