package kgportal.tests;

import kgportal.forms.KgportalKgeneratorForm;
import kgportal.forms.KgportalMainForm;
import kgportal.forms.KgportalMainFormWithLogin;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.BaseTest;

/**
 * Тест вставки картинки в КГенератор
 */
public class ParamKgeneratorTest extends BaseTest {
    private String login;
    private String password;
    private String image;

    @Test
    @Parameters({"login", "password", "image"})
    public void readParams(String login, String password, String image) throws Throwable {
        this.login = login;
        this.password = password;
        this.image = image;
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
        kgportalMainForm.login(login, password);
        kgportalMainForm.checkLogin(login);

        logStep();
        KgportalMainFormWithLogin kgportalMainFormWithLogin = new KgportalMainFormWithLogin(login);
        kgportalMainFormWithLogin.enterKgenerator();

        logStep();
        KgportalKgeneratorForm kgportalKgeneratorForm = new KgportalKgeneratorForm();
        kgportalKgeneratorForm.insertImg(image);
        kgportalKgeneratorForm.checkImage(image);
    }
}
