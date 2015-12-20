package kgportal.forms;

import kgportal.locators.KgSubMenuButtonsHrefs;
import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class KgportalMainForm extends BaseForm {
    private Label lblMenu;
    private Label lblSubMenu;
    private TextBox txbLogin = new TextBox(By.id("ten_login_username"), "login");
    private TextBox txbPassword = new TextBox(By.id("ten_login_password"), "password");
    private Button enter = new Button(By.className("b_login"), "enter");
    private Label lblLogin;
    private Button btnTopNews;
    private Button btnAnyTopNews;

    public KgportalMainForm() {
        super(By.id("alltabs"), "Kg-portal ru");
    }

    public String moveMenuClickSubMenu(String linkMenu, String linkSubMenu) {
        lblMenu = new Label(By.className(linkMenu), linkMenu);

        String baseLink;
        if (linkSubMenu.equals(KgSubMenuButtonsHrefs.TRAILERS)
                || linkSubMenu.equals(KgSubMenuButtonsHrefs.REVIEWS)
                || linkSubMenu.equals(KgSubMenuButtonsHrefs.PODCASTS)) {
            baseLink = String.format("%s%s/", linkSubMenu, linkMenu);
        } else {
            baseLink = linkSubMenu;
        }
        String name = String.format("Submenu %s", baseLink);
        lblSubMenu = new Label(By.xpath(String.format("//a[@href = '%s']", baseLink)), name);
        lblMenu.showSubElement(lblSubMenu);
        lblSubMenu.clickAndWaitPage();
        return baseLink;
    }

    public void moveSecondMenuClickSubMenu(String linkMenu, String linkSubMenu) {
        lblMenu = new Label(By.xpath(String.format("//div[@id = 'tabs']//a[@href = '%s/']", linkMenu)), linkMenu);
        lblSubMenu = new Label(By.xpath(String.format("//a[@href = '%s%s']", linkMenu, linkSubMenu)), linkSubMenu);
        lblMenu.showSubElement(lblSubMenu);
        lblSubMenu.clickAndWaitPage();
    }

    public void login(String login, String password) {
        txbLogin.setText(login);
        txbPassword.setText(password);
        enter.clickAndWaitPage();
    }

    public void checkLogin(String login) {
        lblLogin = new Label(By.xpath(String.format("//div[@class = 'header' and contains(text(), '%s')]", login)));
        if (lblLogin.isPresent()) {
            logger.info("Login successful");
        } else {
            logger.info("login is not successful");
        }
    }

    public void chooseTopNewsFromHeader(String linkMenu) {
        btnTopNews = new Button(By.xpath(String.format("//span[@id = 'topnews_header_%s']", linkMenu)), linkMenu);
        btnTopNews.scrollWithOffset(0, -100);
        btnTopNews.click();
    }

    public String clickTopNews(int itemNumber) {
        btnAnyTopNews = new Button(By.xpath(String.format("//div[@id = 'topnews_list_tv']//li[%s]//a", itemNumber)));
        btnAnyTopNews.setName(btnAnyTopNews.getText());
        btnAnyTopNews.clickAndWaitPage();
        return btnAnyTopNews.getName();
    }
}
