package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class KgportalGamesSeasonPreviewForm extends BaseForm {
    private Button lblAnyDiscussion = new Button(By.xpath("//a[@itemprop = 'discussionUrl']"), "discussion news label");

    public KgportalGamesSeasonPreviewForm() {
        super(By.xpath("//span[contains(text(), 'Релизы игр на')]"), "Kg games season preview ru");
    }

    public void moveToDiscussion() {
        lblAnyDiscussion.scrollWithOffset(0, -100);
        lblAnyDiscussion.clickAndWaitPage();
    }

}
