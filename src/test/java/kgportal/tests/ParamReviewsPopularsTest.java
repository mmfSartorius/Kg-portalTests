package kgportal.tests;

import kgportal.forms.KgportalMainForm;
import kgportal.forms.KgportalPopularReviewsForm;
import kgportal.forms.KgportalReviewsListForm;
import kgportal.locators.KgMenuButtonsHrefs;
import kgportal.locators.KgReviewFilterButtonsHrefs;
import kgportal.locators.KgSubMenuButtonsHrefs;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.BaseTest;

/**
 * Тест сортировки наград по популярности
 */
public class ParamReviewsPopularsTest extends BaseTest {
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
        subLink = kgportalMainForm.moveMenuClickSubMenu(KgMenuButtonsHrefs.TV
                , KgSubMenuButtonsHrefs.REVIEWS);

        logStep();
        KgportalReviewsListForm kgportalReviewsForm = new KgportalReviewsListForm(subLink);

        logStep();
        combinedLink = kgportalReviewsForm.selectButton(KgReviewFilterButtonsHrefs.POPULAR);
        KgportalPopularReviewsForm kgportalPopularReviewsForm = new KgportalPopularReviewsForm(combinedLink);
        kgportalPopularReviewsForm.checkPopularity(numberOfElements);
    }
}
