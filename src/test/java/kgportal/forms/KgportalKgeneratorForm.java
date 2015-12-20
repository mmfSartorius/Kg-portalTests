package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class KgportalKgeneratorForm extends BaseForm {
    private TextBox txbLink = new TextBox(By.className("input_url"), "TextBox for link of img");
    private Button btnOk = new Button(By.className("kgen15_enter"), "OK");
    private Label lblImage;

    public KgportalKgeneratorForm() {
        super(By.xpath("//h4[text() = \"КГенератор 9001\"]"), "KGenerator");
    }

    /**
     * Вставка ссылки в текстбокс
     *
     * @param link - ссылка картки
     */
    public void insertImg(String link) {
        txbLink.setText(link);
        btnOk.clickAndWaitPage();
    }

    /**
     * Проверка что картинка вставилась
     * @param link - ссылка картинки
     */
    public void checkImage(String link) {
        lblImage = new Label(By.id("image_1"));
        Boolean boolContains = lblImage.getElement().getAttribute("style").contains(link);
        doAssert(boolContains, "Images upload", "Images not loaded");
    }
}
