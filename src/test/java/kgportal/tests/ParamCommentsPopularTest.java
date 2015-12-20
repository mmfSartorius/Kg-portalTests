package kgportal.tests;

import kgportal.forms.KgportalCommentsForm;
import kgportal.forms.KgportalMainForm;
import kgportal.locators.KgCommentsFilters;
import kgportal.locators.KgMenuButtonsHrefs;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.BaseTest;

/**
 * Тест сортировки комментариев по популярности
 */
public class ParamCommentsPopularTest extends BaseTest {
    String name;
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
        kgportalMainForm.chooseTopNewsFromHeader(KgMenuButtonsHrefs.TV);
        name = kgportalMainForm.clickTopNews(1);

        logStep();
        KgportalCommentsForm kgportalCommentsForm = new KgportalCommentsForm(name);
        kgportalCommentsForm.clickFilter(KgCommentsFilters.POPULAR);
        kgportalCommentsForm.checkOnAllPagesPositivePopularity(numberOfElements);
    }
}