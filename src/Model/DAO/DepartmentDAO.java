package Model.DAO;

import Model.Entities.Department;

import java.util.List;

public interface DepartmentDAO {

    void insert(Department dep);

    void update(Department dep);

    void deleteById(Integer id);

    void deleteByName(String name);

    Department findById(Integer id);

    Department findByDepartment(String name);

    List <Department> findAll();

}