package Service;

import Repository.OffreRepo;
import model.OffreEmploi;
import model.Auth.RH;
import model.StatutOffre;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OffreService {
    private OffreRepo offreRepo = new OffreRepo();

    // Create a new job offer
    public boolean createOffre(OffreEmploi offre) {
       return offreRepo.createOffre(offre);
    }

    // Read/Get a single job offer by ID
    public OffreEmploi findOffreById(Long id) {
        return offreRepo.findOffreById(id);
    }

    // Read/Get all job offers
    public List<OffreEmploi> getAllOffres() {
       return offreRepo.getAllOffres();
    }


    // Delete a job offer by ID
    public boolean deleteOffre(Long id) {
       return offreRepo.deleteOffre(id);
    }
}
