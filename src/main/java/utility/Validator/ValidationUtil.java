package utility.Validator;

import java.util.regex.Pattern;

public class ValidationUtil {

    // Valider que le nom n'est pas vide et qu'il contient seulement des lettres
    public boolean isValidName(String name) {
        return name != null && name.length() >= 2 && name.matches("[a-zA-Z ]+");
    }

    // Valider que l'e-mail est bien formé
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Valider que le numéro de téléphone a 10 chiffres
    public boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "\\d{10}";
        return phoneNumber != null && Pattern.compile(phoneRegex).matcher(phoneNumber).matches();
    }

    // Valider que le département est parmi une liste valide
    public boolean isValidDepartment(String department) {
        return department != null && !department.trim().isEmpty();
    }

    // Valider que le poste n'est pas vide
    public boolean isValidPosition(String position) {
        return position != null && !position.trim().isEmpty();
    }
}
