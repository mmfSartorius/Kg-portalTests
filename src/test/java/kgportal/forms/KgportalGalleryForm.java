package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Label;

import java.util.ArrayList;
import java.util.List;

public class KgportalGalleryForm extends BaseForm {
    private List<Label> newsList = new ArrayList<>();
    private Label lblImage;

    public KgportalGalleryForm() {
        super(By.xpath("//div[@itemtype = 'http://schema.org/Article']"), "Kg-portal ru Gallery ");
    }

    public void clickAllDetails(int numberOfElements) {
        newsList = BaseElement.findBaseElements("//div[@itemtype = 'http://schema.org/Article']"
                , "//a[text() = \"Подробности\"]", numberOfElements, "//a[@class = 'news_head']//span");
        for (Label temp : newsList) {
            temp.scrollWithOffset(0, -100);
            temp.click();
        }
    }

    public void checkImages(int numberOfElements) {
        int contains = 0;
        newsList = BaseElement.findBaseElements("//div[@itemtype = 'http://schema.org/Article']"
                , "", numberOfElements, "//a[@class = 'news_head']//span");
        for (Label temp : newsList) {
            lblImage = new Label(By.xpath(temp.getLocator().toString().replace("By.xpath: ", "") + "//img[@class = 'thumb']"), temp.getName());
            if (!lblImage.isPresent()) {
                lblImage = new Label(By.xpath(temp.getLocator().toString().replace("By.xpath: ", "")
                        + "//img[@class = 'newspic_sb']"), temp.getName());
                if (!lblImage.isPresent()) {
                    contains++;
                }
            }
        }
        info("Checked: " + newsList.size());
        doAssert(contains == 0, "All news contains images", String.valueOf(contains) + " News does not contains images");
    }

}
