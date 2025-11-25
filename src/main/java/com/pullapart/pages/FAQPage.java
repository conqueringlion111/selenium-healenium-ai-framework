package com.pullapart.pages;

import com.pullapart.core.DriverManager;
import com.pullapart.locators.CommonLocators;
import com.pullapart.locators.FAQPageLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FAQPage extends PageBase {

    public FAQPage() {
        super();
    }


    public FAQPage verifySuccessfulNavigationToFAQPage() {
        boolean navigationSuccess = sel.isElementPresent(FAQPageLocators.FAQ_DIV_XPATH);
        Assert.assertTrue(navigationSuccess, "navigation to FAQ page not successful");
        return new FAQPage();
    }

    public UsedCarsPage navigateToUsedCarsPage() {
        sel.click(CommonLocators.USED_CARS_LINK_XPATH);
        return new UsedCarsPage();
    }
}
