package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class KgportalReviewsListForm extends BaseForm {
    String baseLink;
    String combinedLink;
    private Button btnFilter;

    public KgportalReviewsListForm(String subLink) {
        super(By.xpath("//h2[text() = \"Рецензии\"]"), "Kg-portal ru Reviews");
        this.baseLink = subLink;
    }

    public String selectButton(String link) {
        combinedLink = String.format("%s%s", baseLink, link);
        btnFilter = new Button(By.xpath(String.format("//a[@href = '%s']", combinedLink)), combinedLink);
        btnFilter.clickAndWaitPage();
        return combinedLink;
    }

}
