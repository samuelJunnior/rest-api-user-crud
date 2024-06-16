package br.com.samueljunnior.module.report.service;

import br.com.samueljunnior.core.message.MessagePropertiesEnum;
import br.com.samueljunnior.module.report.util.ReportTemplateValues;
import br.com.samueljunnior.module.user.dto.UserDTO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generateUserReport(UserDTO userData) {
        try(final InputStream inputStream = this.getClass().getResourceAsStream(ReportTemplateValues.USER_REPORT)){

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("IMG_BACKGROUND", ReportTemplateValues.getInstance().getBackgroundImage());
            parameters.put("USER_NAME", userData.getName());

            JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw MessagePropertiesEnum.USER_REPORT_ERROR.businessException();
    }
}
