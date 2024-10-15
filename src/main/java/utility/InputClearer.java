package utility;

import javax.servlet.http.HttpServletRequest;

public class InputClearer {

    public static void clearInputs(HttpServletRequest request) {
        request.setAttribute("name", "");
        request.setAttribute("department", "");
        request.setAttribute("email", "");
        request.setAttribute("position", "");
        request.setAttribute("phone", "");

    }
}
