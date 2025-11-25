package com.pullapart.pages;

import com.pullapart.core.DriverManager;
import com.pullapart.locators.InventorySearchPageLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InventorySearchPage extends PageBase {

    public InventorySearchPage() {
        super();
    }

    public InventorySearchPage verifyNavigationToInventorySearchPageSuccessful() {
        boolean navigationSuccessful = sel.isElementPresent(
                InventorySearchPageLocators.INVENTORY_SEARCH_PAGE_CSS
        );
        Assert.assertTrue(navigationSuccessful, "Navigation to Inventory Search Page was not successful");
        return this;
    }

    public InventorySearchPage verifySearchSuccessful(String make, String model) {

        boolean exactMatch = sel.isElementPresent(
                InventorySearchPageLocators.EXACT_MATCH_LABEL_XPATH
        );

        if (exactMatch) {
            int total = sel.count(InventorySearchPageLocators.SEARCH_RESULT_ROLES_XPATH);

            for (int i = 1; i <= total; i++) {
                Assert.assertTrue(
                        sel.isElementPresent(InventorySearchPageLocators.findSearchedValue(String.valueOf(i), make)),
                        "Expected make not found in row: " + i
                );

                Assert.assertTrue(
                        sel.isElementPresent(InventorySearchPageLocators.findSearchedValue(String.valueOf(i), model)),
                        "Expected model not found in row: " + i
                );
            }

        } else {
            Assert.assertTrue(
                    sel.isElementPresent(InventorySearchPageLocators.INVENTORY_SEARCH_PAGE_CSS),
                    "Navigation to Inventory Search Page not successful"
            );
        }

        return this;
    }
}
