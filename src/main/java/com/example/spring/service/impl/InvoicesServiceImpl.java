package com.example.spring.service.impl;

import com.example.spring.entity.User;
import com.example.spring.repository.UsersRepository;
import com.example.spring.service.InvoicesService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoicesServiceImpl implements InvoicesService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Value("${pdf.template.dir}")
    private String templateDir;

    @Value("${pdf.output.dir}")
    private String outputDir;

    @Override
    public byte[] generatePdf(Map<String, Object> parameters, String reportName) throws SQLException {
        try {
            JasperReport report = JasperCompileManager.compileReport(templateDir + reportName);
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport
                            (report,
                                    parameters,
                                    jdbcTemplate.getDataSource().getConnection());
//            JasperPrint jasperPrint = JasperFillManager
//                    .fillReport
//                            (reportName,
//                                    parameters,
//                                    dataSource.getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputDir + parameters.get("bookId") + "_ticket.pdf");
            byte[] result = JasperExportManager.exportReportToPdf(jasperPrint);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //close koneksi
            jdbcTemplate.getDataSource().getConnection().close();
        }
    }


    //    @Override
//    public ResponseEntity<byte[]> generateInvoice() throws Exception {
//        Map<String, Object> data = new HashMap<>();
//        User buyer = null;
////        User buyer = usersRepository.findAll();
//        try {
//            JasperReport report = JasperCompileManager.compileReport(reportName);
//            System.out.println("reportreport=" + reportName);
//            JasperPrint jasperPrint = JasperFillManager
//                    .fillReport
//                            (report,
//                                    parameters,
//                                    jdbcTemplate.getDataSource().getConnection());
////            JasperExportManager.exportReportToPdfFile(jasperPrint, "./cdn/out.pdf");
//            JRDocxExporter exporter = new JRDocxExporter();
//            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//            File exportReportFile = new File("./cdn/out.docx");
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
//            exporter.exportReport();
//            byte[] result = JasperExportManager.exportReportToPdf(jasperPrint);
//            return result;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            //close koneksi
//            jdbcTemplate.getDataSource().getConnection().close();
//        }
//        JasperReport report = JasperCompileManager
//        JasperPrint invoice = JasperFillManager.fillReport(
//                JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:static/invoice.jrxml").getAbsolutePath()),
//                data,
//                new JREmptyDataSource()
//        );
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("filename", "invoice.pdf");
//
//        return new ResponseEntity<>(JasperExportManager.exportReportToPdf(invoice), headers, HttpStatus.OK);
//    }
}
