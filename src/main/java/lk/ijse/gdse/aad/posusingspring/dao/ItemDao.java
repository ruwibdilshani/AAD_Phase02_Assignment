package lk.ijse.gdse.aad.posusingspring.dao;



import lk.ijse.gdse.aad.posusingspring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item, String> {
    Item getItemByItemCode(String itemCode);
}
