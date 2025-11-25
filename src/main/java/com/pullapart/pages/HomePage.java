package com.pullapart.pages;

import com.pullapart.core.DriverManager;
import com.pullapart.locators.HomePageLocators;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {

    public HomePage() {
        super();
    }

    public HomePage clickOnSelectLocation() {
        sel.click(HomePageLocators.SELECT_LOCATION_CONTAINS_TEXT_XPATH);
        return this;
    }

    public HomePage fillInSearchField(String search) {
        sel.type(HomePageLocators.SELECT_LOCATION_INPUT_FIELD_XPATH, search);
        return this;
    }

    public HomePage selectSearchResult(String search) {
        sel.isElementPresent(HomePageLocators.selectInputResult(search));  // instead of sleep!
        sel.click(HomePageLocators.selectInputResult(search));
        return this;
    }

    public HomePage clickOnSelectMake() {
        sel.click(HomePageLocators.SELECT_MAKE_XPATH);
        return this;
    }

    public HomePage clickOnSelectModel() {
        sel.click(HomePageLocators.SELECT_MODEL_XPATH);
        return this;
    }

    public InventorySearchPage clickOnSearchButton() {
        sel.click(HomePageLocators.BASIC_SEARCH_BUTTON_XPATH);
        return new InventorySearchPage();
    }

    public FAQPage clickOnFAQLink() {
        sel.click(HomePageLocators.FAQ_LINK_XPATH);
        return new FAQPage();
    }
}
