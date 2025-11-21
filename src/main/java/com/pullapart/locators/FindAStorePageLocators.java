package com.pullapart.locators;

import org.openqa.selenium.By;

public class FindAStorePageLocators {

    public static final By SEARCH_CITY_STATE_ZIP_FIELD_XPATH = By.xpath("//div[@class='container locations-landing bg-white']//input[@placeholder='City and State, or ZIP']");

    public static By getListedCityByStringXpath (String city) {
        return By.xpath("//a[contains(text(),'" + city + "')]");
    }

    public static final By GO_BUTTON_XPATH = By.xpath("//main//button[contains(text(),'Go')]");

    public static final By LOCATION_SEARCH_RESULTS_DIV_XPATH = By.xpath("//div[@class='container locations-landing bg-white']//div[@class='location-search-result']");

    public static By getSearchResultsDivByStringXpath(String search) {
        return By.xpath("//div[contains(text(),'" + search + "')]");
    }
}
