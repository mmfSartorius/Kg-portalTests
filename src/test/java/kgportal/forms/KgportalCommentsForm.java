package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.ArrayList;
import java.util.List;

public class KgportalCommentsForm extends BaseForm {
    private TextBox txbComment = new TextBox(By.id("text_comm"), "New comment");
    private Button btnPreview = new Button(By.xpath("//input[@class = 'bigbutton preview']"), "Preview");
    private Button btnNextPage = new Button(By.xpath("//a[text() = '→']"), "Next page");
    private Button btnFilter;
    private Label lblPreviewComment = new Label(By.id("comment_preview"), "Comment preview");
    private Label lblUsername;
    private Label lblText;
    private List<Label> newsList = new ArrayList<>();
    private Integer lastKarma = null;

    public KgportalCommentsForm(String name) {
        super(By.xpath("//h2[text() = 'Комментарии']"), name);
    }

    public void putText(String text) {
        txbComment.setText(text);
    }

    public void clickPreview() {
        btnPreview.click();
        lblPreviewComment.waitForIsElementPresent();
    }

    public void checkUsernameInPreview(String username) {
        lblUsername = new Label(By.xpath(String.format("//ul[@id = 'comment_preview']//span[text() = '%s']", username))
                , "Username in comment preview");
        doAssert(lblUsername.isPresent(), "Username matches", "Username does not match");
    }

    public void checkTextInPreview(String text) {
        lblText = new Label(By.xpath(String.format("//ul[@id = 'comment_preview']//p[text() = '%s']", text))
                , "Text in comment preview");
        doAssert(lblText.isPresent(), "Text matches", "Text does not match");
    }

    public void clickFilter(String filter) {
        btnFilter = new Button(By.xpath(String.format("//a[text() = '%s']", filter)), filter);
        btnFilter.scrollWithOffset(0, -100);
        btnFilter.clickAndWaitPage();
    }

    public void checkPositivePopularity(int numberOfElements) {
        newsList = BaseElement.findBaseElements("//li[contains(@id, 'comment')]", "//span[contains(@id, 'karma')]"
                , numberOfElements, "//a[contains(@class, 'user_name')]");
        int[] views = new int[newsList.size()];
        for (int i = 0; i < newsList.size(); i++) {
            String string = newsList.get(i).getText();
            string = string.replaceAll("\\S\\D+", "").replace("–", "-");
            if (string.isEmpty()) {
                views[i] = 0;
            } else {
                views[i] = Integer.parseInt(string);
            }
        }
        boolean complies = true;
        if (lastKarma != null) {
            if (lastKarma < views[0]) {
                complies = false;
                fatal("First karma and last karma from previous page does not matched");
            }
        }
        if (views.length > 1) {
            for (int i = 0; i < views.length - 1; i++) {
                if (views[i] < views[i + 1]) {
                    complies = false;
                    break;
                }
            }
        }
        lastKarma = views[views.length - 1];
        info("Checked karma: " + newsList.size());
        doAssert(complies, "All karma matched", "Some karma does not matched");
    }

    public void checkOnAllPagesPositivePopularity(int numberOfElements) {
        checkPositivePopularity(numberOfElements);
        while (btnNextPage.isPresent()) {
            btnNextPage.clickAndWaitPage();
            checkPositivePopularity(numberOfElements);
        }
    }
}
