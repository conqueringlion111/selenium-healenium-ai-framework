package com.pullapart.locators;

import org.openqa.selenium.By;

public class InventorySearchPageLocators {

    public static final By INVENTORY_SEARCH_PAGE_CSS = By.cssSelector("#InventorySearchPage");
    public static final By EXACT_MATCH_LABEL_XPATH = By.xpath("//h3[contains(text(),'Exact Match')]");
    public static final By SEARCH_RESULT_ROLES_XPATH = By.xpath("//div[@data-sortable-table='inventorySearchExact4']//div[@data-sortable-table-row='bodyLong']");

    public static By findSearchedValue(String i, String search) {
        return By.xpath("//div[@data-sortable-table='inventorySearchExact4']//div[@data-sortable-table-row='bodyLong'][" + i + "]//div[contains(text(),'" + search + "')]");
    }
}
