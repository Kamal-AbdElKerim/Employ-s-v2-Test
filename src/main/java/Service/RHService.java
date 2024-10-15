package Service;

import Repository.RHRepo;
import model.Auth.RH;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class RHService {
    private RHRepo rHRepo = new RHRepo();

    public RH findRHByEmail(String email) {
       return rHRepo.findRHByEmail(email);
    }
}
