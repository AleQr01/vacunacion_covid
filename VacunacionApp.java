package com.vacunacion;

import java.util.*;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class VacunacionApp {

    public static void main(String[] args) {
        // 1. Generar 500 ciudadanos
        Set<String> todos = new HashSet<>();
        for (int i = 1; i <= 500; i++) {
            todos.add("Ciudadano " + i);
        }

        // 2. Generar vacunados Pfizer y AstraZeneca
        List<String> lista = new ArrayList<>(todos);
        Collections.shuffle(lista);

        Set<String> pfizer = new HashSet<>(lista.subList(0, 75));
        Set<String> astra = new HashSet<>(lista.subList(75, 150));

        // 3. Aplicar operaciones de teoría de conjuntos
        Set<String> noVacunados = new HashSet<>(todos);
        noVacunados.removeAll(pfizer);
        noVacunados.removeAll(astra);

        Set<String> ambasDosis = new HashSet<>(pfizer);
        ambasDosis.retainAll(astra);

        Set<String> soloPfizer = new HashSet<>(pfizer);
        soloPfizer.removeAll(astra);

        Set<String> soloAstra = new HashSet<>(astra);
        soloAstra.removeAll(pfizer);

        // 4. Imprimir resultados en consola
        System.out.println("No vacunados: " + noVacunados.size());
        System.out.println("Ambas dosis: " + ambasDosis.size());
        System.out.println("Solo Pfizer: " + soloPfizer.size());
        System.out.println("Solo AstraZeneca: " + soloAstra.size());

        // 5. Generar PDF
        generarReportePDF(noVacunados, ambasDosis, soloPfizer, soloAstra);
    }

    public static void generarReportePDF(Set<String> noVacunados, Set<String> ambasDosis,
                                         Set<String> soloPfizer, Set<String> soloAstra) {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("ReporteVacunacion.pdf"));
            doc.open();

            Font titulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font contenido = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            doc.add(new Paragraph("Reporte de Vacunación COVID-19\n\n", titulo));

            doc.add(new Paragraph("No vacunados (" + noVacunados.size() + "):", titulo));
            for (String c : noVacunados) {
                doc.add(new Paragraph(c, contenido));
            }

            doc.add(new Paragraph("\nAmbas dosis (" + ambasDosis.size() + "):", titulo));
            for (String c : ambasDosis) {
                doc.add(new Paragraph(c, contenido));
            }

            doc.add(new Paragraph("\nSolo Pfizer (" + soloPfizer.size() + "):", titulo));
            for (String c : soloPfizer) {
                doc.add(new Paragraph(c, contenido));
            }

            doc.add(new Paragraph("\nSolo AstraZeneca (" + soloAstra.size() + "):", titulo));
            for (String c : soloAstra) {
                doc.add(new Paragraph(c, contenido));
            }

            doc.close();
            System.out.println("PDF generado exitosamente: ReporteVacunacion.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
