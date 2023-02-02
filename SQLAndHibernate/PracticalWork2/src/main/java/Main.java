import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = Hibernate.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<PurchaseList> linkedList = session.createQuery("from PurchaseList").getResultList();
        for (PurchaseList list : linkedList) {
         LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
         linkedPurchaseList.setId(list.getId());
         session.persist(linkedPurchaseList);
        }
        transaction.commit();
        session.close();
    }
}
