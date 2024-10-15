package Service;
import Repository.AdminRepo;
import model.Auth.Admin;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class AdminService {
    private AdminRepo AdminRepo = new AdminRepo();

    public Admin findAdminByEmail(String email) {

        return AdminRepo.findAdminByEmail(email);
    }





}
