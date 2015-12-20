package kgportal.forms;

import kgportal.locators.KgAwardsSrc;
import kgportal.locators.KgReviewAwardsHrefs;
import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

public class KgportalReviewForm extends BaseForm {
    private Label award;

    public KgportalReviewForm(String formTitle) {
        super(By.xpath("//div[@id = 'movies_hd']"), formTitle);
    }

    /**
     * Проверка наличия награды
     *
     * @param award - src награды
     * @return
     */
    public boolean checkAward(String award) {
        String xpath = "//img[@src = '%s']";
        switch (award) {
            case KgReviewAwardsHrefs.OHUITELNO:
                xpath = String.format(xpath, KgAwardsSrc.OHUITELNO);
                break;
            case KgReviewAwardsHrefs.NEHUEVO:
                xpath = String.format(xpath, KgAwardsSrc.NEHUEVO);
                break;
            case KgReviewAwardsHrefs.KLINIKA:
                xpath = String.format(xpath, KgAwardsSrc.KLINIKA);
        }
        this.award = new Label(By.xpath(xpath), "Award");
        return this.award.isPresent();
    }
}
