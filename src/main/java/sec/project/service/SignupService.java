package sec.project.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

public class SignupService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void signUp(String name, String address, String signer, Long creditcard) {
        String queryString = "INSERT INTO Signup (name, address, signername, creditcardnumber) "
                + "VALUES (?, ?, ?, ?);";
        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter(1, name);
        query.setParameter(2, address);
        query.setParameter(3, signer);
        query.setParameter(4, creditcard);
        query.executeUpdate();
    }
}
