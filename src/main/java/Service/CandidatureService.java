package Service;

import Repository.CandidatureRepo;
import model.Candidature;
import model.Auth.StatusCandidature;
import model.OffreEmploi;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CandidatureService {
    private CandidatureRepo candidatureRepo = new CandidatureRepo();

    public boolean isEmailUniqueForOffre(String email, long offreId) {
       return candidatureRepo.isEmailUniqueForOffre(email, offreId);
    }

    // Create (Add) a new Candidature
    public void addCandidature(Candidature candidature) {
       candidatureRepo.addCandidature(candidature);
    }

    // Read (Find by ID) a Candidature
    public Candidature getCandidatureById(Long id) {
        return candidatureRepo.getCandidatureById(id);
    }

    // Read (Find all) Candidatures
    public List<Candidature> getAllCandidatures() {
        return candidatureRepo.getAllCandidatures();
    }



    // Update an existing Candidature by ID
    public void acceptCandidature(Long id) {
        candidatureRepo.acceptCandidature(id);
    }

    public void rejectCandidature(Long id) {
        candidatureRepo.rejectCandidature(id);
    }

    // Delete a Candidature by ID

    // Filter by skills
    public List<Candidature> filterBySkills(List<String> skills) {
      return candidatureRepo.filterBySkills(skills);
    }
}
