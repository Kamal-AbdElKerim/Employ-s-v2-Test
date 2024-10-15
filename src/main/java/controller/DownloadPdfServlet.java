package controller;

import Service.CongeService;
import model.Conge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/downloadPdf")
public class DownloadPdfServlet extends HttpServlet {
    private CongeService congeService = new CongeService(); // Service to get Conge details

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String congeIdParam = request.getParameter("congeId");
        if (congeIdParam == null || congeIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing congeId parameter.");
            return;
        }

        long congeId = Long.parseLong(congeIdParam);
        Conge conge = congeService.getCongeById(congeId);

        if (conge == null || conge.getPdf() == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Conge or PDF not found.");
            return;
        }

        byte[] pdfData = conge.getPdf();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=conge_" + congeId + ".pdf");
        response.setContentLength(pdfData.length);

        try (OutputStream out = response.getOutputStream()) {
            out.write(pdfData);
        }
    }
}

