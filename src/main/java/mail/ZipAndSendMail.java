package mail;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

public class ZipAndSendMail {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    static String[] toEmails = {"san590dev@gmail.com", "san589dev@gmail.com","san588dev@gmail.com"};
    static String fromUser = "samk587587@gmail.com";
    static String password = "Honda@5875";

    public static void main(String args[]) throws Exception {
        String reportFolder = System.getProperty("user.dir") + "//reports//";

        File dir = new File(reportFolder);
        File[] files = dir.listFiles();
        File lastModified = Arrays.stream(files).filter(File::isDirectory).max(Comparator.comparing(File::lastModified)).orElse(null);
        System.out.println(lastModified.getName());

        Zip.zipDir(reportFolder + "//" + lastModified.getName(), reportFolder + "//" + lastModified.getName() + ".zip");

        Mail javaEmail = new Mail();
        javaEmail.setMailServerProperties();

        javaEmail.createEmailMessage(
                "Automation Test Reports",
                "Please find the reports in attached",
                reportFolder + "//" + lastModified.getName() + ".zip",
                "Reports.zip",
                toEmails

        );
        javaEmail.sendEmail(fromUser, password);

    }

}
