package com.tangbo.test.serivces.impl;

import com.tangbo.test.constants.EmailTemplateConstant;
import com.tangbo.test.serivces.EmailOptionService;
import com.tangbo.test.utils.DateUtils;
import com.tangbo.test.utils.ReadFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailOptionServiceImpl implements EmailOptionService {

    public static final String MOTH_DAY = "MMdd";
    public static final String YEAR_MOTH_DAY = "yyyy/MM/dd";

    @Value("job.employee.info")
    private String employeeInfo;

    @Value("job.email.template")
    private String emailTemplate;

    @Override
    public void sendEmailToBirthdayEmployee() {
        String fileContent = ReadFileUtil.readTextFileAndGetContent(employeeInfo);
        Map<String, String> employeeNameAndEmails = getBirthdayEmployeeNames(fileContent);

        Properties properties = ReadFileUtil.readPropertiesFile(emailTemplate);
        String subject = properties.getProperty(EmailTemplateConstant.SUBJECT);
        String content = properties.getProperty(EmailTemplateConstant.CONTENT);

        StringBuilder emailContent = new StringBuilder();
        employeeNameAndEmails.forEach((name, emailUrl) -> emailContent.append(subject).append("\n")
                .append(MessageFormat.format(content, name)).append("\n"));

        System.out.println(emailContent.toString());

    }

    private Map<String, String> getBirthdayEmployeeNames(String employeeInfoData) {
        String[] employeesDataArray = employeeInfoData.split("\n");
        Map<String, String> employeeNameAndEmails = new HashMap<>();
        for (String employeeInfo : employeesDataArray) {
            String[] infoArray = employeeInfo.split(",");
            String birthDayDate = infoArray[2];
            String dateFormat = MOTH_DAY;
            String nowDate = DateUtils.getCurrentDateTime(dateFormat);
            LocalDate localDate = DateUtils.getLocalDateTime(birthDayDate, YEAR_MOTH_DAY);
            String employeeBirthDayDate = DateUtils.getDateTime(localDate, dateFormat);
            if (nowDate.equals(employeeBirthDayDate)) {
                employeeNameAndEmails.put(infoArray[1], infoArray[3]);
            }

        }
        return employeeNameAndEmails;
    }

}
