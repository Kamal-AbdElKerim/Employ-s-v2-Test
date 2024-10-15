package utility.Validator;

import model.Auth.Employee;

public class EmployeeValidator {

    public String validate(Employee employee) {
        // Validate name
        if (!isValidName(employee.getName())) {
            return "Nom invalide. Veuillez entrer un nom valide.";
        }

        // Validate email
        if (!isValidEmail(employee.getEmail())) {
            return "E-mail invalide. Veuillez entrer un e-mail valide.";
        }

        // Validate phone
        if (!isValidPhoneNumber(employee.getTelephone())) {
            return "Numéro de téléphone invalide. Il doit contenir 10 chiffres.";
        }

        // Validate department
        if (!isValidDepartment(employee.getDepartment())) {
            return "Département invalide. Veuillez choisir un département valide.";
        }

        // Validate position
        if (!isValidPosition(employee.getPosition())) {
            return "Poste invalide. Veuillez entrer un poste valide.";
        }

        return null; // No validation errors
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    private boolean isValidDepartment(String department) {
        return department != null && !department.trim().isEmpty();
    }

    private boolean isValidPosition(String position) {
        return position != null && !position.trim().isEmpty();
    }
}
