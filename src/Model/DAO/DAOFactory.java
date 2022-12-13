package Model.DAO;

import DB.DB;
import Model.DAO.Impls.DepartmentDAOJDBC;
import Model.DAO.Impls.SellerDAOJDBC;

public class DAOFactory {

    public static SellerDAO createSellerDAO(){
        return new SellerDAOJDBC(DB.getConnection());
    }

    public static DepartmentDAO createDepartmentDAO(){
        return new DepartmentDAOJDBC(DB.getConnection());
    }

}