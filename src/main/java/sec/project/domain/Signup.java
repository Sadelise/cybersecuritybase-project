package sec.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Signup extends AbstractPersistable<Long> {

    private String name;
    private String address;
    @Column(unique = true)
    private String signername;
    private Long creditcardnumber;

    public Signup() {
        super();
    }

    public Signup(String name, String address, String signername, Long creditcardnumber) {
        this();
        this.name = name;
        this.address = address;
        this.signername = signername;
        this.creditcardnumber = creditcardnumber;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSignername(String signername) {
        this.signername = signername;
    }

    public String getSignername() {
        return signername;
    }

    public Long getCreditcardnumber() {
        return creditcardnumber;
    }

    public void setCreditcardnumber(Long creditcardnumber) {
        this.creditcardnumber = creditcardnumber;
    }

}
