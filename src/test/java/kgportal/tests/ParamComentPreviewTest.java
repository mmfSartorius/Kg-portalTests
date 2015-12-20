package kgportal.tests;

import kgportal.forms.KgportalCommentsForm;
import kgportal.forms.KgportalGamesSeasonPreviewForm;
import kgportal.forms.KgportalMainForm;
import kgportal.locators.KgMenuButtonsHrefs;
import kgportal.locators.KgSubMenuButtonsHrefs;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.BaseTest;

/**
 * Тест предпросмотра комментария
 */
public class ParamComentPreviewTest extends BaseTest {
    private String login;
    private String password;
    private String text;

    @Test
    @Parameters({"login", "password", "text"})
    public void readParams(String login, String password, String text) throws Throwable {
        this.login = login;
        this.password = password;
        this.text = text;
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
        kgportalMainForm.moveMenuClickSubMenu(KgMenuButtonsHrefs.GAMES
                , KgSubMenuButtonsHrefs.PREVIEW_GAMES);

        logStep();
        KgportalGamesSeasonPreviewForm kgportalGamesSeasonPreviewForm = new KgportalGamesSeasonPreviewForm();

        logStep();
        kgportalGamesSeasonPreviewForm.moveToDiscussion();

        logStep();
        KgportalCommentsForm kgportalCommentsForm = new KgportalCommentsForm("Kg comments");
        kgportalCommentsForm.putText(text);
        kgportalCommentsForm.clickPreview();

        logStep();
        kgportalCommentsForm.checkUsernameInPreview(login);
        kgportalCommentsForm.checkTextInPreview(text);
    }
}
