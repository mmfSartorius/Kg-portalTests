package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Label;

import java.util.ArrayList;
import java.util.List;

public class KgportalPopularReviewsForm extends BaseForm {
    private List<Label> reviewsList = new ArrayList<>();

    public KgportalPopularReviewsForm(String baseLink) {
        super(By.xpath(String.format("//a[@class = 'active' and @href = '%s']", baseLink)), "Kg popular reviews");
    }

    public void checkPopularity(int numberOfElements) {
        reviewsList = BaseElement.findBaseElements("//div[@id = 'listing_item_']"
                , "//u[contains(text(), \"просмотр\")]", numberOfElements, "//u[@class = 'nobr']");
        int[] views = new int[reviewsList.size()];
        for (int i = 0; i < reviewsList.size(); i++) {
            String string[] = reviewsList.get(i).getText().split(",");
            string[1] = string[1].replaceAll("\\W+", "");
            views[i] = Integer.parseInt(string[1]);
        }
        boolean complies = true;
        for (int i = 0; i < views.length - 1; i++) {
            if (views[i] < views[i + 1]) {
                complies = false;
                break;
            }
        }
        info("Checked reviews: " + reviewsList.size());
        doAssert(complies, "All views matched", "Some views does not matched");
    }
}
