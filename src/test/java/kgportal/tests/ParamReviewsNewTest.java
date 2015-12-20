package kgportal.tests;

import kgportal.forms.KgportalMainForm;
import kgportal.forms.KgportalNewReviewForm;
import kgportal.forms.KgportalReviewsListForm;
import kgportal.locators.KgMenuButtonsHrefs;
import kgportal.locators.KgReviewFilterButtonsHrefs;
import kgportal.locators.KgSubMenuButtonsHrefs;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.BaseTest;

/**
 * Тест сортировки рецензий по дате
 */
public class ParamReviewsNewTest extends BaseTest {
    String subLink;
    String combinedLink;
    int numberOfElements;

    @Test
    @Parameters({"numberOfElements"})
    public void readParams(int numberOfElements) throws Throwable {
        this.numberOfElements = numberOfElements;
        xTest();
    }

    @Test(enabled = false)
    public void xTest() throws Throwable {
        super.xTest();
    }

    @Override
    public void runTest() {
        logStep();
        KgportalMainForm kgportalMainForm = new KgportalMainForm();

        logStep();
        subLink = kgportalMainForm.moveMenuClickSubMenu(KgMenuButtonsHrefs.ANIME, KgSubMenuButtonsHrefs.REVIEWS);

        logStep();
        KgportalReviewsListForm kgportalReviewsForm = new KgportalReviewsListForm(subLink);

        combinedLink = kgportalReviewsForm.selectButton(KgReviewFilterButtonsHrefs.NEW);

        logStep();
        KgportalNewReviewForm kgportalNewReviewForm = new KgportalNewReviewForm(combinedLink);
        kgportalNewReviewForm.checkDates(numberOfElements);
    }
}
