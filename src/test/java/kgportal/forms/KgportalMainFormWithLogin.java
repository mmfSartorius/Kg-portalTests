package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;


public class KgportalMainFormWithLogin extends BaseForm {
    private Label lblKgenerator = new Label(By.xpath("//img[@src = '/i/kgenerator_side_01.png']"), "KGenerator");

    public KgportalMainFormWithLogin(String login) {
        super(By.xpath(String.format("//div[@class = 'header' and contains(text(), '%s')]", login)), "Kg-portal ru");
    }

    /**
     * Перейти на страницу Кгенератора
     */
    public void enterKgenerator() {
        lblKgenerator.clickAndWaitPage();
    }
}
