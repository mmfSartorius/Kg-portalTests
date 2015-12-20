package kgportal.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Label;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KgportalNewReviewForm extends BaseForm {
    SimpleDateFormat format = new SimpleDateFormat("d MMMMM yyyy");
    private List<Label> reviewsList = new ArrayList<>();

    public KgportalNewReviewForm(String baseLink) {
        super(By.xpath(String.format("//a[@class = 'active' and @href = '%s']", baseLink)), "Kg popular reviews");
    }

    public void checkDates(int numberOfElements) {
        reviewsList = BaseElement.findBaseElements("//div[@id = 'listing_item_']", "//div[@class = 'output']", numberOfElements, "//u[@class = 'nobr']");
        Date[] dates = new Date[reviewsList.size()];
        for (int i = 0; i < reviewsList.size(); i++) {
            String temp[] = reviewsList.get(i).getText().split(",");
            String string[] = temp[temp.length - 1].split("\\n");
            String date = string[0];
            if (!date.contains("года")) {
                date = date.concat(" 2015");
            }
            try {
                dates[i] = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        boolean complies = true;
        for (int i = 0; i < dates.length - 1; i++) {
            if (dates[i].before(dates[i + 1])) {
                complies = false;
                break;
            }
        }
        info("Checked date: " + reviewsList.size());
        doAssert(complies, "All date sorted", "Some date does not sorted");
    }
}
