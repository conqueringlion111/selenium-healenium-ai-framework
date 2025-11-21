package com.pullapart.pages;

import com.pullapart.locators.VIPClubPageLocators;
import com.pullapart.utils.SeleniumMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class VIPClubPage extends PageBase {

    public VIPClubPage(WebDriver driver) {
        super(driver);
    }

    public VIPClubPage verifySuccessfulNavigationToVIPClubPage() {
        boolean navigation = sel.isElementPresent(VIPClubPageLocators.VIP_CLUB_MEMBERSHIP_H1_XPATH);
        Assert.assertTrue(navigation, "navigation to VIP Club Page not successful");
        return new VIPClubPage(driver);
    }

}
