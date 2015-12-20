package kgportal.tests;

import kgportal.forms.KgportalAwardsReviewsForm;
import kgportal.forms.KgportalMainForm;
import kgportal.forms.KgportalReviewsListForm;
import kgportal.locators.KgMenuButtonsHrefs;
import kgportal.locators.KgReviewAwardsHrefs;
import kgportal.locators.KgReviewFilterButtonsHrefs;
import kgportal.locators.KgSubMenuButtonsHrefs;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.BaseTest;

/**
 * Тест сортировки рецензий по медалям
 */
public class ParamReviewsAwardsTest extends BaseTest {
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
        subLink = kgportalMainForm.moveMenuClickSubMenu(KgMenuButtonsHrefs.MOVIES
                , KgSubMenuButtonsHrefs.REVIEWS);

        logStep();
        KgportalReviewsListForm kgportalReviewsForm = new KgportalReviewsListForm(subLink);
        combinedLink = kgportalReviewsForm.selectButton(KgReviewFilterButtonsHrefs.AWARDS);

        logStep();
        KgportalAwardsReviewsForm kgportalAwardsReviewsForm = new KgportalAwardsReviewsForm(combinedLink, KgReviewAwardsHrefs.NEHUEVO, KgReviewAwardsHrefs.NEHUEVO_NAME);

        logStep();
        kgportalAwardsReviewsForm.checkAwards(numberOfElements);
    }
}
