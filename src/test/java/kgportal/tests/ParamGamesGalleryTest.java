package kgportal.tests;

import kgportal.forms.KgportalGalleryForm;
import kgportal.forms.KgportalMainForm;
import kgportal.locators.KgSecondMenuButtonsHref;
import kgportal.locators.KgSubMenuButtonsHrefs;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.BaseTest;

public class ParamGamesGalleryTest extends BaseTest {
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
        kgportalMainForm.moveSecondMenuClickSubMenu(KgSecondMenuButtonsHref.GAMES, KgSubMenuButtonsHrefs.SECOND_MENU_GALLERY);
        KgportalGalleryForm kgportalGalleryForm = new KgportalGalleryForm();
        kgportalGalleryForm.clickAllDetails(numberOfElements);

        logStep();
        kgportalGalleryForm.checkImages(numberOfElements);
    }
}
