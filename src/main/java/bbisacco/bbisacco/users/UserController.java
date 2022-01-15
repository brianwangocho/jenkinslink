package bbisacco.bbisacco.users;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/download_pdf")
    public void downloadPdf(HttpServletResponse response) throws IOException, JRException {


            File file    = ResourceUtils.getFile("classpath:leave_template.jrxml");
            JasperDesign design = JRXmlLoader.load(file);
            String outFileNamePDF = "c:\\JasperSimpleReport.pdf";


            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("v_company_name","Ngenx");
            parameters.put("v_name","Brian Wangocho");
            parameters.put("v_remain_leave","19");
            parameters.put("v_department","Programming");
            parameters.put("v_leave_day_requested","2");
            parameters.put("v_leave_day_balance","17");
            parameters.put("v_start_date","17");
            parameters.put("v_resumption_date","17");
            parameters.put("v_contact_no","0704687633");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.setContentType("application/pdf;charset=utf-8");
            // httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "."+extension+"\"");
            response.setHeader("Content-Disposition","filename=brianleave.pdf");
            BufferedOutputStream ostream = new BufferedOutputStream(response.getOutputStream());
            SimpleOutputStreamExporterOutput c = new SimpleOutputStreamExporterOutput(ostream);
            //    httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename=brianleave.pdf");
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            pdfExporter.setExporterOutput(c);
            pdfExporter.exportReport();





    }

}
