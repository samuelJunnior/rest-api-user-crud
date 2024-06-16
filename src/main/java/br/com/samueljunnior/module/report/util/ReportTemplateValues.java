package br.com.samueljunnior.module.report.util;

import br.com.samueljunnior.core.message.MessagePropertiesEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Getter
public class ReportTemplateValues {

    public static final String REPORT_PATH = "/report";
    public static final String USER_REPORT = REPORT_PATH + "/user-report-tamplates.jasper";

    public static final String PATH_IMAGE = "/report/images/";
    public static final String PATH_BACKGROUND_IMAGE = PATH_IMAGE + "background.png";

    private static ReportTemplateValues singleton;

    private final BufferedImage backgroundImage;

    private ReportTemplateValues(){
        this.backgroundImage = this.loaderImage(PATH_BACKGROUND_IMAGE);
    }

    public static synchronized ReportTemplateValues getInstance(){
        if (singleton == null) {
            singleton = new ReportTemplateValues();
        }
        return singleton;
    }

    private BufferedImage loaderImage(String path) {
        try (final InputStream inputStream = this.getClass().getResourceAsStream(path)) {
            if (inputStream != null) {
                return ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            log.error("loaderImage.error: ", e);
        }
        throw MessagePropertiesEnum.REPORT_IMAGE_ERROR.businessException();
    }

    public static HttpHeaders getHttpHeadersForPDFReport(String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        return headers;
    }
}
