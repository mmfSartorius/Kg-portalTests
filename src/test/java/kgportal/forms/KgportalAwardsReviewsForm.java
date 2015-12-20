package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.Label;

import java.util.ArrayList;
import java.util.List;

public class KgportalAwardsReviewsForm extends BaseForm {
    String award;
    private Button btnAward;
    private List<Label> reviewsList = new ArrayList<>();

    public KgportalAwardsReviewsForm(String baseLink, String award, String nameOfAward) {
        super(By.xpath(String.format("//a[@class = 'active' and @href = '%s']", baseLink)), "Kg awards reviews");
        selectAward(award, nameOfAward);
    }

    /**
     * Выбор награды для сортировки
     *
     * @param award       - последняя часть локатора награды
     * @param nameOfAward - имя награды
     */
    public void selectAward(String award, String nameOfAward) {
        this.award = award;
        btnAward = new Button(By.xpath(String.format("//a[@href = '/reviews/movies/award/%s']", award)), nameOfAward);
        btnAward.clickAndWaitPage();
    }

    /**
     * Проверка чтобы все рецензии отображали выбранную награду
     * @param numberOfElements - количество рецензий на странице
     */
    public void checkAwards(int numberOfElements) {
        reviewsList = BaseElement.findBaseElements("//div[@id = 'listing_item_']", "//div[@class = 'img']", numberOfElements, "//u[@class = 'nobr']");
        int numberOfMatches = 0;
        for (Label temp : reviewsList) {
            temp.click();
            browser.waitForPageToLoad();
            KgportalReviewForm kgportalReviewForm = new KgportalReviewForm(temp.getName());
            if (kgportalReviewForm.checkAward(award)) {
                numberOfMatches++;
            } else {
                info(temp.getName() + " Award does not matched");
            }
            browser.getDriver().navigate().back();
            browser.waitForPageToLoad();
        }
        info("Checked reviews: " + reviewsList.size());
        doAssert(numberOfMatches == reviewsList.size(), "All awards matched", "Some awards does not matched");
    }
}
