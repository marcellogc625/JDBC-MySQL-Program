package Model.DAO;

import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.List;

public interface SellerDAO {

    void insert(Seller seller);

    void update(Seller seller);

    void deleteById(Integer id);

    Seller findById(Integer id);

    Seller findByName(String name);

    Seller findByEmail(String email);

    List <Seller> findAll();

    List <Seller> findByDepartment(Department dep);

}