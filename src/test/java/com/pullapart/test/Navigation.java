package com.pullapart.test;

import org.testng.annotations.Test;

public class Navigation extends TestBase {

    @Test(groups = {"navigation"}, description = "test to perform basic navigation to FAQ Used Cars and VIP Club pages")
    public void navigateToFAQPage() throws InterruptedException {
        homepage.clickOnFAQLink()
                .verifySuccessfulNavigationToFAQPage()
                .navigateToUsedCarsPage()
                .verifyNavigationToUsedCarPageSuccessful()
                .navigateToVIPClubPage()
                .verifySuccessfulNavigationToVIPClubPage();
    }
}
